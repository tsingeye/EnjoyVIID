package com.tsingeye.viid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RelatedList {

    @JsonProperty("RelatedObject")
    private List<Related> relatedObjects;
}
