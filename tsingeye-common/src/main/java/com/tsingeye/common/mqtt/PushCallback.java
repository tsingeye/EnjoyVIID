package com.tsingeye.common.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 推送回调
 *
 * @author 1h
 */
@Configuration
public class PushCallback implements MqttCallback {

    @Value("${address}")
    private String address;

    @Override
    public void connectionLost(Throwable throwable) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
        throwable.printStackTrace();
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题:" + s);
        System.out.println("接收消息Qos:" + mqttMessage.getQos());
        System.out.println("接收消息内容:" + new String(mqttMessage.getPayload()));
        // 解析payload 然后发http请求
//        HttpUtil.post(address, new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // publish后会执行到这里
        System.out.println("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
    }

}
