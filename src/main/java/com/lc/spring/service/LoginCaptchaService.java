package com.lc.spring.service;



import com.lc.spring.entity.LoginCaptcha;
import com.lc.spring.entity.LoginCaptchaSummary;
import com.lc.spring.entity.Metric;
import com.lc.spring.entity.Metrics;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LoginCaptchaService {


	List<Metric> getLoginRateAllCount(long start, long end);

	List<Metrics> getLoginRateCount(long start, long end);
}
