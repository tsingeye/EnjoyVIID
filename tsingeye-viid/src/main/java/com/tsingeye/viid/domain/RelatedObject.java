package com.tsingeye.viid.domain;

import lombok.*;

import java.util.List;

/**
 * @author 1h
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RelatedObject {
    private List<Related> RelatedObject;
}
