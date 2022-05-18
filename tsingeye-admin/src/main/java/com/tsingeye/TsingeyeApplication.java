package com.tsingeye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author tsingeye
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TsingeyeApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(TsingeyeApplication.class, args);
    }
}
