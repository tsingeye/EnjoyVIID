package com.tsingeye.web.controller.system;

import com.tsingeye.common.core.controller.BaseController;
import com.tsingeye.common.core.domain.Result;
import com.tsingeye.common.utils.StringUtils;
import com.tsingeye.system.service.ISysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author 姜风
 * @date 2021/10/27 10:12
 */
@RestController
@RequestMapping("/system/file")
public class SysFileController extends BaseController {

    @Autowired
    private ISysFileService sysFileService;

    /**
     * 图片上传minio
     *
     * @param file 图片文件
     * @return 返回
     */
    @RequestMapping(value = "/upload" , method = RequestMethod.POST)
    public Result uploadFileMinio(MultipartFile file) {
        String url = sysFileService.uploadFileMinio(file);
        if (StringUtils.isNotEmpty(url)) {
            return Result.success(url);
        }
        return Result.error("上传失败！");
    }

    /**
     * 文件上传
     * @param file 文件
     * @return 返回上传地址
     */
    @RequestMapping(value = "/storage",method = RequestMethod.POST)
    public Result storage(@RequestBody MultipartFile file){
        return success(sysFileService.storageFile(file));
    }

}
