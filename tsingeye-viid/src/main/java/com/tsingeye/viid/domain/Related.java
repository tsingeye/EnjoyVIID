package com.tsingeye.viid.domain;

import lombok.*;

import java.sql.Timestamp;

/**
 * @author 1h
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Related {
    private String RelatedType;
    private String RelatedID;
    private String FaceID;
    private Timestamp CreateTime;
    private String Type;
    private String Data;
}
