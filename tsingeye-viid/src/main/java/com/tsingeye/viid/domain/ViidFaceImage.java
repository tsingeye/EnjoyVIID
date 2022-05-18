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
 * 人脸照片对象 viid_face_image
 *
 * @author 姜风
 * @date 2022-04-21
 */
@ApiModel(value = "viidFaceImage", description = "人脸照片对象")
public class ViidFaceImage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
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
     * 图像标识
     */
    @ApiModelProperty(value = "图像标识")
    @Excel(name = "图像标识")
    private String imageId;

    /**
     * 事件分类：自动分析事件类型，设备采集必选
     */
    @ApiModelProperty(value = "事件分类")
    @Excel(name = "事件分类：自动分析事件类型，设备采集必选")
    private String eventSort;

    /**
     * 设备编码
     */
    @ApiModelProperty(value = "设备编码")
    @Excel(name = "设备编码")
    private String deviceId;

    /**
     * 存储路径：图像文件的存储路径，采用URI命名规则
     */
    @ApiModelProperty(value = "存储路径")
    @Excel(name = "存储路径：图像文件的存储路径，采用URI命名规则")
    private String storagePath;

    /**
     * 不清楚自动含义
     */
    @ApiModelProperty(value = "暂不清楚字段含义")
    @Excel(name = "不清楚自动含义")
    private String type;

    /**
     * 图像文件格式
     */
    @ApiModelProperty(value = "图像文件格式")
    @Excel(name = "图像文件格式")
    private String fileFormat;

    /**
     * 拍摄时间
     */
    @ApiModelProperty(value = "拍摄时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "拍摄时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shotTime;

    /**
     * 宽度
     */
    @ApiModelProperty(value = "宽度")
    @Excel(name = "宽度")
    private String width;

    /**
     * 高度
     */
    @ApiModelProperty(value = "高度")
    @Excel(name = "高度")
    private String height;

    /**
     * 图片数据，采用base64加密
     */
    @ApiModelProperty(value = "图片数据")
    @Excel(name = "图片数据，采用base64加密")
    private String data;

    /**
     * byte[] 形式的base64图片
     */
    @Excel(name = "byte[] 形式的base64图片")
    private String dataBytes;

    /**
     * minio上传后的图片地址
     */
    @ApiModelProperty(value = "图片上传后在minio中的地址")
    @Excel(name = "minio上传后的图片地址")
    private String imageUrl;

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

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setEventSort(String eventSort) {
        this.eventSort = eventSort;
    }

    public String getEventSort() {
        return eventSort;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setShotTime(Date shotTime) {
        this.shotTime = shotTime;
    }

    public Date getShotTime() {
        return shotTime;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getWidth() {
        return width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHeight() {
        return height;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDataBytes() {
        return dataBytes;
    }

    public void setDataBytes(String dataBytes) {
        this.dataBytes = dataBytes;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("faceId", getFaceId())
                .append("imageId", getImageId())
                .append("eventSort", getEventSort())
                .append("deviceId", getDeviceId())
                .append("storagePath", getStoragePath())
                .append("type", getType())
                .append("fileFormat", getFileFormat())
                .append("shotTime", getShotTime())
                .append("width", getWidth())
                .append("height", getHeight())
                .append("data", getData())
                .append("dataBytes", getDataBytes())
                .append("imageUrl", getImageUrl())
                .append("createTime", getCreateTime())
                .toString();
    }
}
