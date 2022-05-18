package com.tsingeye.viid.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 返回的设备Vo
 * @author 姜风
 */
@Data
public class ViidDeviceVo implements Serializable {

    /**
     * 编号
     */
    private String id;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 最后一次注册时间
     */
    private Timestamp registerTime;

    /**
     * 最后一次保活时间
     */
    private Timestamp keepAliveTime;

    /**
     * 在线状态
     * 1、在线
     * 2、不在线
     */
    private Integer status;

    /**
     * 是否启用
     * 1、启用
     * 2、禁用
     */
    private Integer enable;

    /**
     * ip地址
     */
    private String ip;

}
