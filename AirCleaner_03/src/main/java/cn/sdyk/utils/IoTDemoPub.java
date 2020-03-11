package cn.sdyk.utils;

import java.util.HashMap;
import java.util.Map;

import cn.sdyk.pojo.Device;
import cn.sdyk.pojo.adjust;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "IoTDemoPub")
public class IoTDemoPub {
/*
 * 初始化客户端
 */
@Autowired
Device device;
@Autowired
adjust a;

	String text1;

	String text2;

	 static MqttClient mqttClient;
	//上报方法
	 public void initAliyunIoTClient(Device device, adjust a) {

	        try {
	        	System.err.println("开始连接");
	            // 构造连接需要的参数
	            String clientId = "java" + System.currentTimeMillis();
	            Map<String, String> params = new HashMap<>(16);
	            params.put("productKey", device.getProductKey());
	            params.put("deviceName", device.getDeviceName());
	            params.put("clientId", clientId);
	            String timestamp = String.valueOf(System.currentTimeMillis());
	            params.put("timestamp", timestamp);
	            // cn-shanghai
	            String targetServer = "tcp://" +  device.getProductKey() + ".iot-as-mqtt."+"cn-shanghai"+".aliyuncs.com:1883";

	            String mqttclientId = clientId + "|securemode=3,signmethod=hmacsha1,timestamp=" + timestamp + "|";
	            String mqttUsername = device.getDeviceName() + "&" +  device.getProductKey();
	            String mqttPassword = AliyunIoTSignUtil.sign(params, device.getDeviceSecret(), "hmacsha1");

	      	  System.err.println("开始初始化客户端");
//connectMqtt
	            MemoryPersistence persistence = new MemoryPersistence();
	            mqttClient = new MqttClient(targetServer, clientId, persistence);
	            MqttConnectOptions connOpts = new MqttConnectOptions();
	            // MQTT 3.1.1
	            connOpts.setMqttVersion(4);
	            connOpts.setAutomaticReconnect(false);
	            connOpts.setCleanSession(true);

	            connOpts.setUserName(mqttUsername);
	            connOpts.setPassword(mqttPassword.toCharArray());
	            connOpts.setKeepAliveInterval(60);

	            mqttClient.connect(connOpts);
	      	  System.err.println("客户端初始化完毕");
	            
	         // 订阅Topic
	            String subTopic = "/"+device.getProductKey() + "/" + device.getDeviceName()+"/user/s_data";
	      	  System.err.println("订阅Topic");
	            mqttClient.subscribe(subTopic);
	            //上报数据
	            //高级版 物模型-属性上报payload
	            System.out.println("上报属性值");
	          //  String payloadJson = "{\"params\":{\"Status\":0,\"FAN\":\""+data+"\"}}";

	            String payloadJson = a.getName()+"=" + a.getDataStr();
	            MqttMessage message = new MqttMessage(payloadJson.getBytes("utf-8"));
	            message.setQos(0);
	            String pubTopic = "/" + device.getProductKey() + "/" + device.getDeviceName() + "/user/p_data";
	            
	            mqttClient.publish(pubTopic, message);

	            
	         
	        } catch (Exception e) {
	            System.out.println("initAliyunIoTClient error " + e.getMessage());
	        }
	    }

	 
	 
	//订阅方法，返回json字符串 2
	    public  String monitor(){
	  System.err.println("监听方法");
	        // 设置订阅监听
	        mqttClient.setCallback(new MqttCallback() {
	          
	            @Override
	            public void connectionLost(Throwable throwable) {
	                System.out.println("connection Lost");

	            }

	            @Override
	            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
	                System.out.println("Sub message");
	                System.out.println("Topic : " + s);
	                 text1 = new String(mqttMessage.getPayload());
	               System.out.println(new String(mqttMessage.getPayload())); //打印输出消息payLoad

	            }

	            @Override
	            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

	            }


	        });

	       
			return text1;
	    }

	 
}
