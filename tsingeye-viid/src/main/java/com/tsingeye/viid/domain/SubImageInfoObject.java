package com.tsingeye.viid.domain;

import lombok.*;

import java.util.List;

/**
 * @author 姜风
 * 图片集合
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SubImageInfoObject {

    /**
     * 图像列表
     */
    List<SubImage> SubImageInfoObject;
}
