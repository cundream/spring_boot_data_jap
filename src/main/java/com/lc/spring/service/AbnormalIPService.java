package com.lc.spring.service;


import com.lc.spring.entity.Metric;
import com.lc.spring.entity.Metrics;

import java.util.List;


/**
 * 异常账号功能的service的接口
 * 
 * @author Administrator
 *
 */
public interface AbnormalIPService {

	List<Metrics> getAbnormalIPCount(long start, long end);

	List<Metric> getIpCount(long start, long end);
}
