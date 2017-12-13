package com.lc.spring.service;


import com.lc.crm.util.MetricTranslator;
import com.lc.spring.entity.Metric;
import com.lc.spring.repository.HitAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig
public class HitAccountServiceImpl implements HitAccountService{
    @Autowired
   private HitAccountRepository hitAccountRepository;


	@Override
	public List<Metric> getAccountCount(long start, long end) {
		List<Object> list =hitAccountRepository.getAccountCount(start, end);
		return MetricTranslator.translate(list, 0, null, 1);
	}
}
