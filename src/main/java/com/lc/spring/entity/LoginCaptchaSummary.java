package com.lc.spring.entity;

public class LoginCaptchaSummary {

	private String ip;

	private String interfaceAddress;

	private String appkey;

	private int count;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getInterfaceAddress() {
		return interfaceAddress;
	}

	public void setInterfaceAddress(String interfaceAddress) {
		this.interfaceAddress = interfaceAddress;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "LoginCaptchaSummary{" +
				"ip='" + ip + '\'' +
				", interfaceAddress='" + interfaceAddress + '\'' +
				", appkey='" + appkey + '\'' +
				", count=" + count +
				'}';
	}
}
