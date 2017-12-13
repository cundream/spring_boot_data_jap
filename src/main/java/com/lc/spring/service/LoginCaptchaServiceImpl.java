package com.lc.spring.service;

import com.lc.crm.util.MetricTranslator;
import com.lc.spring.entity.Metric;
import com.lc.spring.entity.Metrics;
import com.lc.spring.repository.LoginCaptchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.math.BigInteger;
import java.util.*;

@Service
public class LoginCaptchaServiceImpl implements LoginCaptchaService {

    @Autowired
    private LoginCaptchaRepository loginCaptchaRepository;



	@Override
	public List<Metric> getLoginRateAllCount(long start, long end) {
		List<Object> list=loginCaptchaRepository.getLoginRateAllCount(start, end);
		return MetricTranslator.translate(list, 0, null, 1 );
	}

	@Override
	public List<Metrics> getLoginRateCount(long start, long end) {
		List<Object>  list=loginCaptchaRepository.getLoginRateCount(start, end);
		return  MetricTranslator.translate(list, 0, null, 1,2 );
	}


}
