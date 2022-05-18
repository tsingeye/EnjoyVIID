package com.tsingeye.viid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 姜风
 * @date 2021/9/7 15:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatusObject implements Serializable {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("StatusCode")
    private Integer statusCode;

    @JsonProperty("RequestURL")
    private String requestUrl;

    @JsonProperty("StatusString")
    private String statusString;

    @JsonProperty("LocalTime")
    private String localTime;

}
