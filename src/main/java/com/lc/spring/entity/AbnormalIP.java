package com.lc.spring.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 异常IP
 * 
 * @author hecheng
 *
 */
@Entity
@Table(name = "abnormal_ip")
public class AbnormalIP {
	/**
	 * ID
	 */
	@Id
	private int id;

	/**
	 * ip
	 */
	@Column(name = "ip")
	private String ip;
	/**
	 * Unix时间戳
	 */
	@Column(name = "time_at")
	private int timeAt;

	/**
	 * 时间粒度，直接已分钟为单位，值是具体多少分钟，例如15
	 */
	@Column(name = "time_size")
	private int timeSize;

	/**
	 * 出现次数
	 */
	@Column(name = "access_count")
	private int accessCount;
	/**
	 * 标签
	 */
	@Column(name = "feature")
	private String feature;

	/**
	 * 描述
	 */
	@Column(name = "descri")
	private String descri;
	/**
	 * 状态码
	 */
	@Column(name = "state")
	private int state;
	/**
	 * IP地址所属的区域、单位，由web系统负责计算
	 */
	@Column(name = "region")
	private String region;
	/**
	 * 
	 */
	@Column(name = "region_count")
	private int region_count;

	@Column(name = "type")
	private String type;

	@Column(name = "access_appkeys")
	private String accessAppkeys;
	
	@Column(name = "start_time")
	private Date startTime;

	@Column(name = "end_time")
	private Date endTime;

	@Column(name = "pop_end_time")
	private Date popEndTime;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getAccessAppkeys() {
		return accessAppkeys;
	}

	public void setAccessAppkeys(String accessAppkeys) {
		this.accessAppkeys = accessAppkeys;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getTimeAt() {
		return timeAt;
	}
	public void setTimeAt(int timeAt) {
		this.timeAt = timeAt;
	}
	public int getTimeSize() {
		return timeSize;
	}
	public void setTimeSize(int timeSize) {
		this.timeSize = timeSize;
	}
	public int getAccessCount() {
		return accessCount;
	}
	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getDescri() {
		return descri;
	}
	public void setDescri(String descri) {
		this.descri = descri;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getRegion_count() {
		return region_count;
	}
	public void setRegion_count(int region_count) {
		this.region_count = region_count;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getPopEndTime() {
		return popEndTime;
	}

	public void setPopEndTime(Date popEndTime) {
		this.popEndTime = popEndTime;
	}

	@Override
	public String toString() {
		return "AbnormalIP{" +
				"id=" + id +
				", ip='" + ip + '\'' +
				", timeAt=" + timeAt +
				", timeSize=" + timeSize +
				", accessCount=" + accessCount +
				", feature='" + feature + '\'' +
				", descri='" + descri + '\'' +
				", state=" + state +
				", region='" + region + '\'' +
				", region_count=" + region_count +
				", type='" + type + '\'' +
				", accessAppkeys='" + accessAppkeys + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", popEndTime=" + popEndTime +
				'}';
	}
}
