package com.lc.spring.service;

public interface IPAttributionService {
	public String getIPAttribution(String ip);

	String getIPAttributionFromMemory(String ip);

	String getIpAttributionCountry(String ip);

	String getIpAttributionProvince(String ip);
}
