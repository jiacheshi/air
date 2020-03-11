package cn.sdyk.utils;


import org.springframework.stereotype.Component;

/**
 * 上报实体类
 * @author Lenovo
 *
 */
@Component(value = "IoTDemoPubSub")
public class IoTDemoPubSub {
private String productKey;
private String deviceName;
private String deviceSecret;
public String getProductKey() {
	return productKey;
}
public void setProductKey(String productKey) {
	this.productKey = productKey;
}
public String getDeviceName() {
	return deviceName;
}
public void setDeviceName(String deviceName) {
	this.deviceName = deviceName;
}
public String getDeviceSecret() {
	return deviceSecret;
}
public void setDeviceSecret(String deviceSecret) {
	this.deviceSecret = deviceSecret;
}
@Override
public String toString() {
	return "IoTDemoPubSub [productKey=" + productKey + ", deviceName=" + deviceName + ", deviceSecret=" + deviceSecret
			+ "]";
}

	
}
