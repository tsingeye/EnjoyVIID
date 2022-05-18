package com.tsingeye.viid.domain;

import lombok.Data;

/**
 * @author 返回给设备的对象
 */
@Data
public class ViidResponseObject {
    /**
     * 返回的参数
     */
    private ResponseStatusObject responseStatusObject;
}
