package com.tsingeye.web.controller.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.tsingeye.common.config.TsingeyeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.code.kaptcha.Producer;
import com.tsingeye.common.constant.Constants;
import com.tsingeye.common.core.domain.Result;
import com.tsingeye.common.core.redis.RedisCache;
import com.tsingeye.common.utils.sign.Base64;
import com.tsingeye.common.utils.uuid.IdUtils;
import com.tsingeye.system.service.ISysConfigService;

/**
 * 验证码操作处理
 *
 * @author tsingeye
 */
@RestController
public class CaptchaController {
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysConfigService configService;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public Result getCode(HttpServletResponse response) throws IOException {
        Result ajax = Result.success();
        boolean captchaOnOff = configService.selectCaptchaOnOff();
        ajax.put("captchaOnOff" , captchaOnOff);
        if (!captchaOnOff) {
            return ajax;
        }

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        String captchaType = TsingeyeConfig.getCaptchaType();
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg" , os);
        } catch (IOException e) {
            return Result.error(e.getMessage());
        }

        ajax.put("uuid" , uuid);
        ajax.put("img" , Base64.encode(os.toByteArray()));
        return ajax;
    }
}
