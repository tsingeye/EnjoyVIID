package com.tsingeye.web.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.StartDocument;

@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 请求前缀
     */
    private String pathMapping;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 作者
     */
    private String author;

    /**
     * 版本
     */
    private String version;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 扫描的包
     */
    private String basePackage;


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPathMapping() {
        return pathMapping;
    }

    public void setPathMapping(String pathMapping) {
        this.pathMapping = pathMapping;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
