package com.tsingeye.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 姜风
 * @date 2021/9/8 14:04
 */
@Configuration
@ConfigurationProperties(prefix = "service")
@Data
public class ServiceUrlConfig {

    private String url;

    private String register;

    private String keepalive;

    private String unRegister;
}
