package com.tsingeye.viid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author 姜风
 * 图片具体消息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SubImageInfo {

    private String id;

    private String faceId;

    /**
     * 图像标识
     */
    @JsonProperty("ImageID")
    private String imageId;

    /**
     * 事件分类：自动分析事件类型，设备采集必选
     */
    @JsonProperty("EventSort")
    private String eventSort;

    /**
     * 设备编码
     */
    @JsonProperty("DeviceID")
    private String deviceId;

    /**
     * 存储路径：图像文件的存储路径，采用URI命名规则
     */
    @JsonProperty("StoragePath")
    private String storagePath;

    /**
     * 不清楚字段含义
     */
    @JsonProperty("Type")
    private String type;

    /**
     * 图像文件格式
     */
    @JsonProperty("FileFormat")
    private String fileFormat;

    /**
     * 拍摄时间
     */
    @JsonProperty("ShotTime")
    private String shotTime;

    /**
     * 宽度
     */
    @JsonProperty("Width")
    private String width;

    /**
     * 高度
     */
    @JsonProperty("Height")
    private String height;

    /**
     * 图片数据，使用BASE64加密
     */
    @JsonProperty("Data")
    private String data;

    /**
     * base64存储在blob
     */
    private byte[] dataBytes;

    private String imageUrl;

    private String createTime;
}
