package com.tsingeye.viid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 人脸接口请求参数中的人脸
 */
@Data
public class FaceListObject {

    /**
     * 人脸列表
     */
    @JsonProperty("FaceObject")
    private List<ViidFaceRequest> faceObject;
}
