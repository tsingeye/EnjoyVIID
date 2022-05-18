package com.tsingeye.framework.web.domain.server;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统相关信息
 *
 * @author tsingeye
 */
@ApiModel(value = "Sys", description = "系统对象")
public class Sys {
    /**
     * 服务器名称
     */
    @ApiModelProperty(value = "服务器名称")
    private String computerName;

    /**
     * 服务器Ip
     */
    @ApiModelProperty(value = "服务器Ip")
    private String computerIp;

    /**
     * 项目路径
     */
    @ApiModelProperty(value = "项目路径")
    private String userDir;

    /**
     * 操作系统
     */
    @ApiModelProperty(value = "操作系统")
    private String osName;

    /**
     * 系统架构
     */
    @ApiModelProperty(value = "系统架构")
    private String osArch;

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getComputerIp() {
        return computerIp;
    }

    public void setComputerIp(String computerIp) {
        this.computerIp = computerIp;
    }

    public String getUserDir() {
        return userDir;
    }

    public void setUserDir(String userDir) {
        this.userDir = userDir;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }
}
