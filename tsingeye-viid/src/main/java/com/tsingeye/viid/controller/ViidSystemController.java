package com.tsingeye.viid.controller;

import com.alibaba.fastjson.JSONObject;
import com.tsingeye.common.config.ServiceUrlConfig;
import com.tsingeye.common.utils.DateUtils;
import com.tsingeye.common.utils.ip.IpUtils;
import com.tsingeye.viid.domain.*;
import com.tsingeye.viid.service.ViidDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author 姜风
 */
@RestController
@RequestMapping("/VIID/System")
@Slf4j
public class ViidSystemController {

    @Autowired
    private ViidDeviceService viidDeviceService;

    /**
     * 注册接口
     *
     * @param map     注册的信息
     * @param request 请求
     * @return 返回
     */
    @RequestMapping(value = "/Register", method = RequestMethod.POST, produces = "application/VIID+JSON;charset=utf-8")
    public String register(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        Object object = map.get("RegisterObject");
        String json = JSONObject.toJSONString(object);
        RegisterObject registerObject = JSONObject.parseObject(json, RegisterObject.class);
        log.info("注册的设备信息：{}", registerObject);
        // 保存设备注册时间
        ViidDeviceVo device = new ViidDeviceVo();
        device.setDeviceId(registerObject.getDeviceId());
        device.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        device.setStatus(1);
        device.setIp(IpUtils.getIpAddr(request));
        int register = viidDeviceService.registerViidDevice(device);

        // 拼接url
        StringBuilder sb = new StringBuilder();
        sb.append("/VIID/System/Register");

        // 构造返回的json
        JSONObject result = new JSONObject();
        JSONObject ju = new JSONObject();

        ju.put("Id", registerObject.getDeviceId());
        if (register > 0) {
            ju.put("StatusCode", 0);
            ju.put("StatusString", "OK");
        } else {
            ju.put("StatusCode", 1);
            ju.put("StatusString", "OtherError");
        }
        ju.put("RequestURL", sb.toString());
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyyMMddHHmmss");
        ju.put("LocalTime", sdfTime.format(DateUtils.getNowDate()));

        result.put("ResponseStatusObject", ju);
        log.info("打印注册返回的信息：{}", result.toJSONString());
        return result.toJSONString();
    }

    /**
     * 保活接口
     *
     * @param map 保活的设备信息
     * @return 返回
     */
    @RequestMapping(value = "/Keepalive", method = RequestMethod.POST, produces = "application/VIID+JSON;charset=utf-8")
    public String keepalive(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        Object object = map.get("KeepaliveObject");
        String str = JSONObject.toJSONString(object);
        KeepaliveObject keepaliveObject = JSONObject.parseObject(str, KeepaliveObject.class);
        log.info("对方发送的心跳的信息：{}", keepaliveObject);

        // 拼接url
        StringBuilder sb = new StringBuilder();
        sb.append("/VIID/System/Keepalive");

        // 构造返回的实体
        JSONObject result = new JSONObject();
        JSONObject ju = new JSONObject();

        // 设置返回参数
        ju.put("Id", keepaliveObject.getDeviceId());
        ju.put("RequestURL", sb.toString());
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyyMMddHHmmss");
        ju.put("LocalTime", sdfTime.format(DateUtils.getNowDate()));

        // 根据设备编号获取设备信息
        ViidDeviceVo device = viidDeviceService.getDeviceByDeviceId(keepaliveObject.getDeviceId());

        // 判断设备状态
        if (device == null) {
            // 不存在设备就注册
            ViidDeviceVo deviceNew = new ViidDeviceVo();
            deviceNew.setDeviceId(keepaliveObject.getDeviceId());
            deviceNew.setRegisterTime(new Timestamp(System.currentTimeMillis()));
            deviceNew.setKeepAliveTime(new Timestamp(System.currentTimeMillis()));
            deviceNew.setStatus(1);
            deviceNew.setIp(IpUtils.getIpAddr(request));
            viidDeviceService.registerViidDevice(deviceNew);
            ju.put("StatusCode", 3);
            ju.put("StatusString", "Device Error");
        } else if (new Integer(2).equals(device.getStatus())) {
            // 设备不在线
            device.setStatus(1);
            device.setKeepAliveTime(new Timestamp(System.currentTimeMillis()));
            viidDeviceService.updateById(device);
            ju.put("StatusCode", 4);
            ju.put("StatusString", "Invalid Operation");
        } else {
            device.setStatus(1);
            device.setKeepAliveTime(new Timestamp(System.currentTimeMillis()));
            viidDeviceService.updateById(device);
            ju.put("StatusCode", 0);
            ju.put("StatusString", "OK");
        }
        log.info("已经解析过的心跳信息：{}", keepaliveObject);
        result.put("ResponseStatusObject", ju);
        log.info("打印保活返回的结果：{}", result.toJSONString());
        return result.toJSONString();
    }

    /**
     * 注销设备
     *
     * @param map 参数
     * @return 返回
     */
    @RequestMapping(value = "/UnRegister", method = RequestMethod.POST, produces = "application/VIID+JSON;charset=utf-8")
    public String unRegister(@RequestBody Map<String, Object> map) {
        // 获取设备id
        Object object = map.get("UnRegisterObject");
        String str = JSONObject.toJSONString(object);
        UnRegisterObject unRegisterObject = JSONObject.parseObject(str, UnRegisterObject.class);
        String deviceId = unRegisterObject.getDeviceId();
        log.info("获取的注销的请求参数：{}", unRegisterObject);

        // 拼接url
        StringBuilder sb = new StringBuilder();
        sb.append("/VIID/System/UnRegister");

        // 构造返回实体
        JSONObject result = new JSONObject();
        JSONObject ju = new JSONObject();

        ju.put("RequestURL", sb.toString());
        ju.put("Id", deviceId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        ju.put("LocalTime", sdf.format(DateUtils.getNowDate()));
        ju.put("StatusCode", 0);
        ju.put("StatusString", "OK");
        // 首先查询该设备是否存在
        ViidDeviceVo device = viidDeviceService.getDeviceByDeviceId(deviceId);
        // 判断
        if (device == null) {
            result.put("ResponseStatusObject", ju);
            /*log.info("打印注销返回的参数：{}", viidResponseObject);*/
            log.info("打印注销返回的参数：{}", result.toJSONString());
            return result.toJSONString();
        }
        // 注销就是让设备下线
        // 存在该设备就让该设备下线
        device.setStatus(2);
        int update = viidDeviceService.updateById(device);
        result.put("ResponseStatusObject", ju);
        log.info("打印注销返回的参数：{}", result.toJSONString());
        return result.toJSONString();
    }

    /**
     * 校时接口
     *
     * @return 返回
     */
    @RequestMapping(value = "/Time", method = RequestMethod.GET, produces = "application/VIID+JSON;charset=utf-8")
    public String time() {
        // 构造返回的参数
        JSONObject result = new JSONObject();
        // 构造参数
        JSONObject res = new JSONObject();
        // 服务器标识
        res.put("VIIDServerID", "1680011668");
        /**
         * 校时模式
         * 1、网络
         * 2、手动
         */
        res.put("TimeMode", 2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // 日期时间
        res.put("LocalTime", sdf.format(DateUtils.getNowDate()));
        // 时区
        res.put("TimeZone", TimeZone.getTimeZone("Asia/Shanghai"));
        result.put("SystemTimeObject", res);
        log.info("打印校时返回的参数：{}", result.toJSONString());
        return result.toJSONString();
    }
}
