package com.tsingeye.viid.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tsingeye.common.utils.DateUtils;
import com.tsingeye.common.utils.SnowflakeGenerateIdUtil;
import com.tsingeye.viid.domain.ViidDevice;
import com.tsingeye.viid.domain.ViidDeviceVo;
import com.tsingeye.viid.mapper.ViidDeviceMapper;
import com.tsingeye.viid.service.ViidDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ViidDeviceServiceImpl implements ViidDeviceService {

    @Autowired
    private ViidDeviceMapper viidDeviceMapper;

    /**
     * 获取设备列表
     *
     * @return 设备列表
     */
    @Override
    public List<ViidDeviceVo> getDeviceList() {
        return viidDeviceMapper.getDeviceList();
    }

    /**
     * 注册设备
     *
     * @param device 设备
     * @return 返回
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registerViidDevice(ViidDeviceVo device) {
        // 根据设备编号查询设备
        ViidDeviceVo oldDevice = viidDeviceMapper.getDeviceByDeviceId(device.getDeviceId());
        // 判断该设备是否已经注册过
        if (ObjectUtil.isNotEmpty(oldDevice)) {
            oldDevice.setRegisterTime(device.getRegisterTime());
            oldDevice.setStatus(device.getStatus());
            oldDevice.setIp(device.getIp());
            return viidDeviceMapper.updateById(oldDevice);
        }
        device.setId(String.valueOf(SnowflakeGenerateIdUtil.getFlowIdInstance().nextId()));
        return viidDeviceMapper.insert(device);
    }

    /**
     * 根据设备编号查询设备
     *
     * @param deviceId 设备编号
     * @return 设备
     */
    @Override
    public ViidDeviceVo getDeviceByDeviceId(String deviceId) {
        return viidDeviceMapper.getDeviceByDeviceId(deviceId);
    }

    @Override
    public int updateById(ViidDeviceVo device) {
        return viidDeviceMapper.updateById(device);
    }

    /**
     * 查询启用的设备列表
     *
     * @param enable 启用状态
     * @return 启用停用设备列表
     */
    @Override
    public List<ViidDeviceVo> findDeviceByEnable(int enable) {
        return viidDeviceMapper.findDeviceByEnable(enable);
    }

    /**
     * 查询设备信息
     *
     * @param id 设备信息主键
     * @return 设备信息
     */
    @Override
    public ViidDevice selectViidDeviceById(String id) {
        return viidDeviceMapper.selectViidDeviceById(id);
    }

    /**
     * 查询设备信息列表
     *
     * @param viidDevice 设备信息
     * @return 设备信息
     */
    @Override
    public List<ViidDevice> selectViidDeviceList(ViidDevice viidDevice) {
        return viidDeviceMapper.selectViidDeviceList(viidDevice);
    }

    /**
     * 新增设备信息
     *
     * @param viidDevice 设备信息
     * @return 结果
     */
    @Override
    public int insertViidDevice(ViidDevice viidDevice) {
        viidDevice.setCreateTime(DateUtils.getNowDate());
        return viidDeviceMapper.insertViidDevice(viidDevice);
    }

    /**
     * 修改设备信息
     *
     * @param viidDevice 设备信息
     * @return 结果
     */
    @Override
    public int updateViidDevice(ViidDevice viidDevice) {
        return viidDeviceMapper.updateViidDevice(viidDevice);
    }

    /**
     * 批量删除设备信息
     *
     * @param ids 需要删除的设备信息主键
     * @return 结果
     */
    @Override
    public int deleteViidDeviceByIds(String[] ids) {
        return viidDeviceMapper.deleteViidDeviceByIds(ids);
    }

    /**
     * 删除设备信息信息
     *
     * @param id 设备信息主键
     * @return 结果
     */
    @Override
    public int deleteViidDeviceById(String id) {
        return viidDeviceMapper.deleteViidDeviceById(id);
    }
}
