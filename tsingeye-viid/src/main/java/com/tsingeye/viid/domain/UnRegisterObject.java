package com.tsingeye.viid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 姜风
 * @date 2021/9/7 14:17
 */
@Data
public class UnRegisterObject {
    @JsonProperty("DeviceID")
    private String deviceId;
}
