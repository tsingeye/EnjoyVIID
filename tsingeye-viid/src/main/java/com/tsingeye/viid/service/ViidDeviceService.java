package com.tsingeye.viid.service;

import com.tsingeye.viid.domain.ViidDevice;
import com.tsingeye.viid.domain.ViidDeviceVo;

import java.util.List;

/**
 * 设备接口
 * @author 姜风
 */
public interface ViidDeviceService {

    /**
     * 获取设备列表
     *
     * @return 设备列表
     */
    List<ViidDeviceVo> getDeviceList();

    /**
     * 注册设备
     * @param device 设备
     * @return 返回
     */
    int registerViidDevice(ViidDeviceVo device);

    /**
     * 根据设备编号查询设备
     * @param deviceId 设备编号
     * @return 设备
     */
    ViidDeviceVo getDeviceByDeviceId(String deviceId);

    /**
     * 更新设备
     * @param device 设备
     * @return 返回
     */
    int updateById(ViidDeviceVo device);

    /**
     * 查询启用的设备列表
     * @param enable 启用状态
     * @return 设备编号列表
     */
    List<ViidDeviceVo> findDeviceByEnable(int enable);

    /**
     * 查询设备信息
     *
     * @param id 设备信息主键
     * @return 设备信息
     */
    public ViidDevice selectViidDeviceById(String id);

    /**
     * 查询设备信息列表
     *
     * @param viidDevice 设备信息
     * @return 设备信息集合
     */
    public List<ViidDevice> selectViidDeviceList(ViidDevice viidDevice);

    /**
     * 新增设备信息
     *
     * @param viidDevice 设备信息
     * @return 结果
     */
    public int insertViidDevice(ViidDevice viidDevice);

    /**
     * 修改设备信息
     *
     * @param viidDevice 设备信息
     * @return 结果
     */
    public int updateViidDevice(ViidDevice viidDevice);

    /**
     * 批量删除设备信息
     *
     * @param ids 需要删除的设备信息主键集合
     * @return 结果
     */
    public int deleteViidDeviceByIds(String[] ids);

    /**
     * 删除设备信息信息
     *
     * @param id 设备信息主键
     * @return 结果
     */
    public int deleteViidDeviceById(String id);
}
