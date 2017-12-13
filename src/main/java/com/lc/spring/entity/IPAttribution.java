package com.lc.spring.entity;

import javax.persistence.*;

/**
 * IP归属地
 * 
 * @author hecheng
 *
 */
@Entity
@Table(name = "ip_attribution")
public class IPAttribution {
	/**
	 * ID
	 */
	@Id
	private int id;
	
	/**
	 * small_number
	 */
	@Column(name = "small_number")
	private Long smallNumber;
	
	@Column(name="big_number")
	private Long bigNumber;
	
	@Column(name="small_ip")
	private String small_ip;
	
	@Column(name="big_ip")
	private String big_ip;
	
	@Column(name="country")
	private String country;
	
	@Column(name="province")
	private String province;
	
	@Column(name="city")
	private String city;
	
	@Column(name="network")
	private String network;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getSmallNumber() {
		return smallNumber;
	}

	public void setSmallNumber(Long smallNumber) {
		this.smallNumber = smallNumber;
	}

	public Long getBigNumber() {
		return bigNumber;
	}

	public void setBigNumber(Long bigNumber) {
		this.bigNumber = bigNumber;
	}

	public String getSmall_ip() {
		return small_ip;
	}

	public void setSmall_ip(String small_ip) {
		this.small_ip = small_ip;
	}

	public String getBig_ip() {
		return big_ip;
	}

	public void setBig_ip(String big_ip) {
		this.big_ip = big_ip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	@Override
	public String toString() {
		return "IPAttribution [id=" + id + ", smallNumber=" + smallNumber + ", bigNumber=" + bigNumber + ", smallIP="
				+ small_ip + ", big_ip=" + big_ip + ", country=" + country + ", province=" + province + ", city=" + city
				+ ", network=" + network + "]";
	}
	
	

}
