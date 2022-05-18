package com.tsingeye.viid.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tsingeye.common.annotation.Excel;
import com.tsingeye.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 人脸信息对象 viid_face
 *
 * @author 姜风
 * @date 2022-04-21
 */
@ApiModel(value = "ViidFace",description = "人脸信息对象")
public class ViidFace extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "编号")
    private String id;

    /**
     * 人脸标识
     */
    @ApiModelProperty(value = "人脸标识")
    @Excel(name = "人脸标识")
    private String faceId;

    /**
     * 信息分类：人工采集、自动采集
     */
    @ApiModelProperty(value = "信息分类")
    @Excel(name = "信息分类")
    private String infoKind;

    /**
     * 来源标识：来源图像信息标识
     */
    @ApiModelProperty(value = "来源标识")
    @Excel(name = "来源标识")
    private String sourceId;

    /**
     * 设备编码
     */
    @ApiModelProperty(value = "设备编码")
    @Excel(name = "设备编码")
    private String deviceId;

    /**
     * 拍摄时间
     */
    @ApiModelProperty(value = "拍摄时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "拍摄时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shotTime;

    /**
     * 左上角X坐标
     */
    @ApiModelProperty(value = "左上角X坐标")
    @Excel(name = "左上角X坐标")
    private String leftTopX;

    /**
     * 左上角Y坐标
     */
    @ApiModelProperty(value = "左上角Y坐标")
    @Excel(name = "左上角Y坐标")
    private String leftTopY;

    /**
     * 右下角X坐标
     */
    @ApiModelProperty(value = "右下角X坐标")
    @Excel(name = "右下角X坐标")
    private String rightBtmX;

    /**
     * 右下角Y坐标
     */
    @ApiModelProperty(value = "右下角Y坐标")
    @Excel(name = "右下角Y坐标")
    private String rightBtmY;

    /**
     * 位置标记时间
     */
    @ApiModelProperty(value = "位置标记时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "位置标记时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date locationMarkTime;

    /**
     * 人脸出现时间
     */
    @ApiModelProperty(value = "人脸出现时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "人脸出现时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date faceAppearTime;

    /**
     * 人脸消失时间
     */
    @ApiModelProperty(value = "人脸消失时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "人脸消失时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date faceDisAppearTime;

    /**
     * 性别代码
     */
    @ApiModelProperty(value = "性别代码")
    @Excel(name = "性别代码")
    private String genderCode;

    /**
     * 年龄上限
     */
    @ApiModelProperty(value = "年龄上限")
    @Excel(name = "年龄上限")
    private String ageUpLimit;

    /**
     * 年龄下限
     */
    @ApiModelProperty(value = "年龄下限")
    @Excel(name = "年龄下限")
    private String ageLowerLimit;

    /**
     * 眼镜款式
     */
    @ApiModelProperty(value = "眼镜款式")
    @Excel(name = "眼镜款式")
    private String glassStyle;

    /**
     * 不清楚字段含义
     */
    @ApiModelProperty(value = "暂不清楚字段含义")
    @Excel(name = "不清楚字段含义")
    private String emotion;

    /**
     * 是否驾驶员 0：否、1：是、2：不确定
     */
    @ApiModelProperty(value = "是否驾驶员")
    @Excel(name = "是否驾驶员 0：否、1：是、2：不确定")
    private String isDriver;

    /**
     * 是否涉外人员 0：否、1：是、2：不确定
     */
    @ApiModelProperty(value = "是否涉外人员")
    @Excel(name = "是否涉外人员 0：否、1：是、2：不确定")
    private String isForeigner;

    /**
     * 是否涉恐人员 0：否、1：是、2：不确定
     */
    @ApiModelProperty(value = "是否涉恐人员")
    @Excel(name = "是否涉恐人员 0：否、1：是、2：不确定")
    private String isSuspectedTerrorist;

    /**
     * 是否涉案人员 0：否、1：是、2：不确定
     */
    @ApiModelProperty(value = "是否涉案人员")
    @Excel(name = "是否涉案人员 0：否、1：是、2：不确定")
    private String isCriminalInvolved;

    /**
     * 是否在押人员 0：否、1：是、2：不确定
     */
    @ApiModelProperty(value = "是否在押人员")
    @Excel(name = "是否在押人员 0：否、1：是、2：不确定")
    private String isDetainess;

    /**
     * 是否被害人 0：否、1：是、2：不确定
     */
    @ApiModelProperty(value = "是否被害人")
    @Excel(name = "是否被害人 0：否、1：是、2：不确定")
    private String isVictim;

    /**
     * 是否可疑人员 0：否、1：是、2：不确定
     */
    @ApiModelProperty(value = "是否可疑人员")
    @Excel(name = "是否可疑人员 0：否、1：是、2：不确定")
    private String isSuspiciousPerson;

    /**
     * 相似度[0,1]
     */
    @ApiModelProperty(value = "相似度")
    @Excel(name = "相似度[0,1]")
    private String similaritydegree;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setInfoKind(String infoKind) {
        this.infoKind = infoKind;
    }

    public String getInfoKind() {
        return infoKind;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setShotTime(Date shotTime) {
        this.shotTime = shotTime;
    }

    public Date getShotTime() {
        return shotTime;
    }

    public void setLeftTopX(String leftTopX) {
        this.leftTopX = leftTopX;
    }

    public String getLeftTopX() {
        return leftTopX;
    }

    public void setLeftTopY(String leftTopY) {
        this.leftTopY = leftTopY;
    }

    public String getLeftTopY() {
        return leftTopY;
    }

    public void setRightBtmX(String rightBtmX) {
        this.rightBtmX = rightBtmX;
    }

    public String getRightBtmX() {
        return rightBtmX;
    }

    public void setRightBtmY(String rightBtmY) {
        this.rightBtmY = rightBtmY;
    }

    public String getRightBtmY() {
        return rightBtmY;
    }

    public void setLocationMarkTime(Date locationMarkTime) {
        this.locationMarkTime = locationMarkTime;
    }

    public Date getLocationMarkTime() {
        return locationMarkTime;
    }

    public void setFaceAppearTime(Date faceAppearTime) {
        this.faceAppearTime = faceAppearTime;
    }

    public Date getFaceAppearTime() {
        return faceAppearTime;
    }

    public void setFaceDisAppearTime(Date faceDisAppearTime) {
        this.faceDisAppearTime = faceDisAppearTime;
    }

    public Date getFaceDisAppearTime() {
        return faceDisAppearTime;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setAgeUpLimit(String ageUpLimit) {
        this.ageUpLimit = ageUpLimit;
    }

    public String getAgeUpLimit() {
        return ageUpLimit;
    }

    public void setAgeLowerLimit(String ageLowerLimit) {
        this.ageLowerLimit = ageLowerLimit;
    }

    public String getAgeLowerLimit() {
        return ageLowerLimit;
    }

    public void setGlassStyle(String glassStyle) {
        this.glassStyle = glassStyle;
    }

    public String getGlassStyle() {
        return glassStyle;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setIsDriver(String isDriver) {
        this.isDriver = isDriver;
    }

    public String getIsDriver() {
        return isDriver;
    }

    public void setIsForeigner(String isForeigner) {
        this.isForeigner = isForeigner;
    }

    public String getIsForeigner() {
        return isForeigner;
    }

    public void setIsSuspectedTerrorist(String isSuspectedTerrorist) {
        this.isSuspectedTerrorist = isSuspectedTerrorist;
    }

    public String getIsSuspectedTerrorist() {
        return isSuspectedTerrorist;
    }

    public void setIsCriminalInvolved(String isCriminalInvolved) {
        this.isCriminalInvolved = isCriminalInvolved;
    }

    public String getIsCriminalInvolved() {
        return isCriminalInvolved;
    }

    public void setIsDetainess(String isDetainess) {
        this.isDetainess = isDetainess;
    }

    public String getIsDetainess() {
        return isDetainess;
    }

    public void setIsVictim(String isVictim) {
        this.isVictim = isVictim;
    }

    public String getIsVictim() {
        return isVictim;
    }

    public void setIsSuspiciousPerson(String isSuspiciousPerson) {
        this.isSuspiciousPerson = isSuspiciousPerson;
    }

    public String getIsSuspiciousPerson() {
        return isSuspiciousPerson;
    }

    public void setSimilaritydegree(String similaritydegree) {
        this.similaritydegree = similaritydegree;
    }

    public String getSimilaritydegree() {
        return similaritydegree;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("faceId", getFaceId())
                .append("infoKind", getInfoKind())
                .append("sourceId", getSourceId())
                .append("deviceId", getDeviceId())
                .append("shotTime", getShotTime())
                .append("leftTopX", getLeftTopX())
                .append("leftTopY", getLeftTopY())
                .append("rightBtmX", getRightBtmX())
                .append("rightBtmY", getRightBtmY())
                .append("locationMarkTime", getLocationMarkTime())
                .append("faceAppearTime", getFaceAppearTime())
                .append("faceDisAppearTime", getFaceDisAppearTime())
                .append("genderCode", getGenderCode())
                .append("ageUpLimit", getAgeUpLimit())
                .append("ageLowerLimit", getAgeLowerLimit())
                .append("glassStyle", getGlassStyle())
                .append("emotion", getEmotion())
                .append("isDriver", getIsDriver())
                .append("isForeigner", getIsForeigner())
                .append("isSuspectedTerrorist", getIsSuspectedTerrorist())
                .append("isCriminalInvolved", getIsCriminalInvolved())
                .append("isDetainess", getIsDetainess())
                .append("isVictim", getIsVictim())
                .append("isSuspiciousPerson", getIsSuspiciousPerson())
                .append("similaritydegree", getSimilaritydegree())
                .append("createTime", getCreateTime())
                .append("remark", getRemark())
                .toString();
    }
}
