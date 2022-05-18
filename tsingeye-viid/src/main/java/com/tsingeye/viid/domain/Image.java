package com.tsingeye.viid.domain;

import lombok.*;

/**
 * @author 1h
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Image {

    private Integer id;
    private String sourceId;
    private String image;
    private Integer type;

}
