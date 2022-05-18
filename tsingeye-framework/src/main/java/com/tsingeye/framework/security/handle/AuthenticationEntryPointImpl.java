package com.tsingeye.framework.security.handle;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.tsingeye.common.constant.HttpStatus;
import com.tsingeye.common.core.domain.Result;
import com.tsingeye.common.utils.ServletUtils;
import com.tsingeye.common.utils.StringUtils;

/**
 * 认证失败处理类 返回未授权
 *
 * @author tsingeye
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源" , request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(Result.error(code, msg)));
    }
}
