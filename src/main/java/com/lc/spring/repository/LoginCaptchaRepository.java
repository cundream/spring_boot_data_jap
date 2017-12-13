package com.lc.spring.repository;

import com.lc.spring.entity.LoginCaptcha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface LoginCaptchaRepository extends JpaRepository<LoginCaptcha, Long>, JpaSpecificationExecutor<LoginCaptcha> {




	@Query(value = "SELECT DATE_FORMAT(l.time, '%Y-%m-%d'),count(1),CASE l.interface_address WHEN 'http://open.e.189.cn/api/account/mailLogin.do' THEN 'POP' ELSE  'WEB' END AS address FROM login_captcha l WHERE interface_address != '/api/logbox/oauth2/needcaptcha.do' AND interface_address != '/api/common/needcaptcha.do' AND is_intercept = 0 AND time >= from_unixtime(?1) AND time <= from_unixtime(?2) GROUP BY  address, DATE_FORMAT(l.time, '%Y-%m-%d') ASC", nativeQuery = true)
	public List<Object> getLoginRateCount(long start, long end);

	@Query(value = "SELECT DATE_FORMAT(l.time,'%Y-%m-%d') as time ,count(1) from login_captcha l WHERE interface_address != '/api/logbox/oauth2/needcaptcha.do' AND interface_address != '/api/common/needcaptcha.do' AND is_intercept = 0 AND time >= from_unixtime(?1) AND time <= from_unixtime(?2) GROUP BY DATE_FORMAT(l.time,'%Y-%m-%d')  asc", nativeQuery = true)
	public List<Object> getLoginRateAllCount(long start, long end);


}
