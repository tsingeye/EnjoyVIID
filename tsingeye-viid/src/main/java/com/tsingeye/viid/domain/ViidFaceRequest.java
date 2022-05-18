package com.tsingeye.viid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

/**
 * 人脸对象信息
 * @author 姜风
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ViidFaceRequest {

    /**
     * 主键
     */
    private String id;

    /**
     * 人脸标识
     */
    @JsonProperty("FaceID")
    private String faceId;

    /**
     * 信息分类：人工采集/自动采集
     */
    @JsonProperty("InfoKind")
    private String infoKind;

    /**
     * 来源标识：来源图像信息标识
     */
    @JsonProperty("SourceID")
    private String sourceId;

    /**
     * 设备编码，自动采集必选
     */
    @JsonProperty("DeviceID")
    private String deviceId;

    /**
     * 拍摄时间
     */
    @JsonProperty("ShotTime")
    private String shotTime;

    /**
     * 左上角X坐标
     */
    @JsonProperty("LeftTopX")
    private String leftTopX;

    /**
     * 左上角Y坐标
     */
    @JsonProperty("LeftTopY")
    private String leftTopY;

    /**
     * 右下角X坐标
     */
    @JsonProperty("RightBtmX")
    private String rightBtmX;

    /**
     * 右下角Y坐标
     */
    @JsonProperty("RightBtmY")
    private String rightBtmY;

    /**
     * 位置标记时间
     */
    @JsonProperty("LocationMarkTime")
    private String locationMarkTime;

    /**
     * 人脸出现时间
     */
    @JsonProperty("FaceAppearTime")
    private String faceAppearTime;

    /**
     * 人脸消失时间
     */
    @JsonProperty("FaceDisAppearTime")
    private String faceDisAppearTime;

    /**
     * 性别代码
     */
    @JsonProperty("GenderCode")
    private String genderCode;

    /**
     * 年龄上限
     */
    @JsonProperty("AgeUpLimit")
    private String ageUpLimit;

    /**
     * 年龄下限
     */
    @JsonProperty("AgeLowerLimit")
    private String ageLowerLimit;

    /**
     * 眼镜款式
     */
    @JsonProperty("GlassStyle")
    private String glassStyle;

    /**
     * 不清楚字段含义
     */
    @JsonProperty("Emotion")
    private String emotion;

    /**
     * 是否驾驶员
     * 0：否、1：是、2：不确定
     */
    @JsonProperty("IsDriver")
    private String isDriver;

    /**
     * 是否涉外人员
     * 0：否、1：是、2：不确定
     */
    @JsonProperty("IsForeigner")
    private String isForeigner;

    /**
     * 是否涉恐人员
     * 0：否、1：是、2：不确定
     */
    @JsonProperty("IsSuspectedTerrorist")
    private String isSuspectedTerrorist;

    /**
     * 是否涉案人员
     * 0：否、1：是、2：不确定
     */
    @JsonProperty("IsCriminalInvolved")
    private String isCriminalInvolved;

    /**
     * 是否在押人员
     * 0：否、1：是、2：不确定  人工采集必填
     */
    @JsonProperty("IsDetainees")
    private String isDetainess;

    /**
     * 是否被害人
     * 0：否、1：是、2：不确定  人工采集必填
     */
    @JsonProperty("isVictim")
    private String isVictim;

    /**
     * 是否可疑人员
     * 0：否、1：是、2：不确定
     */
    @JsonProperty("IsSuspiciousPerson")
    private String isSuspiciousPerson;

    /**
     * 相似度：人脸相似度[0,1]
     */
    @JsonProperty("Similaritydegree")
    private String similaritydegree;

    /**
     * 图片信息列表
     */
    @JsonProperty("SubImageList")
    private SubImageInfoObject subImageInfoObject;

    @JsonProperty("RelatedType")
    private String relatedType;

    @JsonProperty("RelatedList")
    private RelatedList relatedList;

    @JsonProperty("CreateTime")
    private Timestamp createTime;

    @JsonProperty("image")
    private Image image;

    @JsonProperty("serviceNo")
    private String serviceNo;

    @JsonProperty("size")
    private long size;

    private String remark;
}
