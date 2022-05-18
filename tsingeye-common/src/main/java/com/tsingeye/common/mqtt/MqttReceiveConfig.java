package com.tsingeye.common.mqtt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 配置项
 *
 * @author 1h
 */
@Component
@ConfigurationProperties(prefix = "mqtt")
@Getter
@Setter
public class MqttReceiveConfig {

    @Autowired
    private MqttPushClient mqttPushClient;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 连接地址
     */
    private String host;
    /**
     * 客户Id
     */
    private String clientId;
    /**
     * 超时时间
     */
    private int timeout;
    /**
     * 保持连接数
     */
    private int keepalive;

    @Bean
    public MqttPushClient getMqttPushClient() {
        System.out.println("hostUrl: " + host);
        System.out.println("clientID: " + clientId);
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        System.out.println("timeout: " + timeout);
        System.out.println("keepalive: " + keepalive);
        mqttPushClient.connect(host, clientId, username, password, timeout, keepalive);
        // 以/#结尾表示订阅所有以test开头的主题
        mqttPushClient.subscribe("test", 0);
        return mqttPushClient;
    }

}
