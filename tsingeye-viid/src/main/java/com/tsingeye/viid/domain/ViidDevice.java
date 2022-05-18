package com.tsingeye.viid.domain;

import java.math.BigDecimal;

import com.tsingeye.common.annotation.Excel;
import com.tsingeye.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备信息对象 viid_device
 *
 * @author 姜风
 * @date 2022-04-22
 */
@ApiModel(value = "ViidDevice" , description = "设备信息对象")
public class ViidDevice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "${comment}")
    private String id;

    /**
     * 设备id
     */
    @Excel(name = "设备id")
    @ApiModelProperty(value = "设备id")
    private String deviceId;

    /**
     * 最后一次注册时间
     */
    @Excel(name = "最后一次注册时间")
    @ApiModelProperty(value = "最后一次注册时间")
    private String registerTime;

    /**
     * 最后一次保活时间
     */
    @Excel(name = "最后一次保活时间")
    @ApiModelProperty(value = "最后一次保活时间")
    private String keepAliveTime;

    /**
     * 在线状态 1、在线 2、不在线
     */
    @Excel(name = "在线状态 1、在线 2、不在线")
    @ApiModelProperty(value = "在线状态 1、在线 2、不在线")
    private Integer status;

    /**
     * 启用状态 1、启用  2、不启用
     */
    @Excel(name = "启用状态 1、启用  2、不启用")
    @ApiModelProperty(value = "启用状态 1、启用  2、不启用")
    private Integer enable;

    /**
     * 设备ip
     */
    @Excel(name = "设备ip")
    @ApiModelProperty(value = "设备ip")
    private String ip;

    /**
     * 字典转换（1、开，2、关）dictzhuanhuan
     */
    @Excel(name = "字典转换" , readConverterExp = "1=、开，2、关")
    @ApiModelProperty(value = "字典转换")
    private Integer dictConver;

    /**
     * 格式清洗（1、开，2、关）geshiqingxi
     */
    @Excel(name = "格式清洗" , readConverterExp = "1=、开，2、关")
    @ApiModelProperty(value = "格式清洗")
    private Integer formatClean;

    /**
     * 加密推送（1、开，2、关）jiamituisong
     */
    @Excel(name = "加密推送" , readConverterExp = "1=、开，2、关")
    @ApiModelProperty(value = "加密推送")
    private Integer encryptPush;

    /**
     * 解密推送（1、开，2、关）jiemituisong
     */
    @Excel(name = "解密推送" , readConverterExp = "1=、开，2、关")
    @ApiModelProperty(value = "解密推送")
    private Integer decryptPush;

    /**
     * 点位全称
     */
    @Excel(name = "点位全称")
    @ApiModelProperty(value = "点位全称")
    private String name;

    /**
     * 经度
     */
    @Excel(name = "经度")
    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    /**
     * 纬度
     */
    @Excel(name = "纬度")
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    /**
     * 派出所
     */
    @Excel(name = "派出所")
    @ApiModelProperty(value = "派出所")
    private String police;

    /**
     * 类型
     */
    @Excel(name = "类型")
    @ApiModelProperty(value = "类型")
    private String type;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setKeepAliveTime(String keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public String getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setDictConver(Integer dictConver) {
        this.dictConver = dictConver;
    }

    public Integer getDictConver() {
        return dictConver;
    }

    public void setFormatClean(Integer formatClean) {
        this.formatClean = formatClean;
    }

    public Integer getFormatClean() {
        return formatClean;
    }

    public void setEncryptPush(Integer encryptPush) {
        this.encryptPush = encryptPush;
    }

    public Integer getEncryptPush() {
        return encryptPush;
    }

    public void setDecryptPush(Integer decryptPush) {
        this.decryptPush = decryptPush;
    }

    public Integer getDecryptPush() {
        return decryptPush;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public String getPolice() {
        return police;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("deviceId" , getDeviceId())
                .append("registerTime" , getRegisterTime())
                .append("keepAliveTime" , getKeepAliveTime())
                .append("status" , getStatus())
                .append("enable" , getEnable())
                .append("ip" , getIp())
                .append("dictConver" , getDictConver())
                .append("formatClean" , getFormatClean())
                .append("encryptPush" , getEncryptPush())
                .append("decryptPush" , getDecryptPush())
                .append("name" , getName())
                .append("longitude" , getLongitude())
                .append("latitude" , getLatitude())
                .append("police" , getPolice())
                .append("type" , getType())
                .append("createTime" , getCreateTime())
                .toString();
    }
}
