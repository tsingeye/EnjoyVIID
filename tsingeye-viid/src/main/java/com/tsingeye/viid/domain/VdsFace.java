package com.tsingeye.viid.domain;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author 1h
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class VdsFace {

    private String ID;
    private String FaceID;
    private String InfoKind;
    private String SourceID;
    private String DeviceID;
    private String ShotTime;
    private String LeftTopX;
    private String LeftTopY;
    private String RightBtmX;
    private String RightBtmY;
    private String LocationMarkTime;
    private String FaceAppearTime;
    private String FaceDisAppearTime;
    private String GenderCode;
    private String AgeUpLimit;
    private String AgeLowerLimit;
    private String GlassStyle;
    private String Emotion;
    private String IsDriver;
    private String IsForeigner;
    private String IsSuspectedTerrorist;
    private String IsCriminalInvolved;
    private String IsDetainees;
    private String IsVictim;
    private String IsSuspiciousPerson;
    private String Similaritydegree;
    private SubImageInfoObject SubImageList;
    private String RelatedType;
    private List<RelatedObject> RelatedList;
    private Timestamp CreateTime;
    private Image image;
    private String serviceNo;
    private long size;

}
