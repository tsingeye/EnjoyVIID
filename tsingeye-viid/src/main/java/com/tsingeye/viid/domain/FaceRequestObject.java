package com.tsingeye.viid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 接收的人脸参数
 */
@Data
public class FaceRequestObject {

    /**
     * 人脸接口请求的参数
     */
    @JsonProperty("FaceListObject")
    private FaceListObject faceListObject;
}
