package com.lc.spring.repository;

import com.lc.spring.entity.HitAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface HitAccountRepository extends JpaRepository<HitAccount, Long>, JpaSpecificationExecutor<HitAccount> {

	@Query(value = "SELECT  from_unixtime(time_at,'%Y-%m-%d') as  time_at,count(1) as timeacount FROM (select MIN(time_at) as time_at ,account,COUNT(DISTINCT account) from hit_account where time_at >= ?1 and time_at <= ?2  GROUP BY account) t GROUP BY from_unixtime(t.time_at,'%Y-%m-%d')  ASC ", nativeQuery = true)
	public List<Object> getAccountCount(long start, long end);

}
