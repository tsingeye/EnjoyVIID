package com.tsingeye.framework.web.domain.server;

import com.tsingeye.common.utils.Arith;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 內存相关信息
 *
 * @author tsingeye
 */
@ApiModel(value = "Mem",description = "内存对象")
public class Mem {
    /**
     * 内存总量
     */
    @ApiModelProperty(value = "内存总量")
    private double total;

    /**
     * 已用内存
     */
    @ApiModelProperty(value = "已用内存")
    private double used;

    /**
     * 剩余内存
     */
    @ApiModelProperty(value = "剩余内存")
    private double free;

    public double getTotal() {
        return Arith.div(total, (1024 * 1024 * 1024), 2);
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public double getUsed() {
        return Arith.div(used, (1024 * 1024 * 1024), 2);
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public double getFree() {
        return Arith.div(free, (1024 * 1024 * 1024), 2);
    }

    public void setFree(long free) {
        this.free = free;
    }

    public double getUsage() {
        return Arith.mul(Arith.div(used, total, 4), 100);
    }
}
