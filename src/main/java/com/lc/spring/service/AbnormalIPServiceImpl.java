package com.lc.spring.service;

import com.lc.crm.util.MetricTranslator;
import com.lc.spring.entity.Metric;
import com.lc.spring.entity.Metrics;
import com.lc.spring.repository.AbnormalIP1Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 
 * 
 * @author Administrator
 *
 */
@Service
public class AbnormalIPServiceImpl implements AbnormalIPService {

	private static Logger logger = LoggerFactory.getLogger("AbnormalIpLevelLogger");

	@Autowired
	public AbnormalIP1Repository abnormalIPRepository;

	@Override
	public List<Metrics> getAbnormalIPCount(long start, long end) {
		List<Object>  list=abnormalIPRepository.getAbnormalIPCount(start, end);
		return MetricTranslator.translate(list, 0, null, 1, 2);
	}


	@Override
	public List<Metric> getIpCount(long start, long end) {
		List<Object>  list =abnormalIPRepository.getIpCount(start, end);
		return MetricTranslator.translate(list, 0, null, 1);
	}

}
