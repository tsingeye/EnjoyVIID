package com.tsingeye.viid.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SubImage {
    private String id;

    private String faceId;

    private byte[] dataBytes;

    private String imageUrl;

    private String createTime;

    private String ImageID;
    private String EventSort;
    private String DeviceID;
    private String StoragePath;
    private String Type;
    private String FileFormat;
    private String ShotTime;
    private String Width;
    private String Height;
    private String Data;
}
