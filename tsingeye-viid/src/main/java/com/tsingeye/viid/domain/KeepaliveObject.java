package com.tsingeye.viid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 姜风
 * @date 2021/9/7 15:50
 */
@Data
public class KeepaliveObject implements Serializable {

    @JsonProperty("DeviceID")
    private String deviceId;
}
