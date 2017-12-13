package com.lc.spring.repository;

import com.lc.spring.entity.AbnormalIP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbnormalIP1Repository extends JpaRepository<AbnormalIP, Long>, JpaSpecificationExecutor<AbnormalIP> {



	@Query(value = "SELECT from_unixtime(a.time_at,'%Y-%m-%d') as  time_at ,count(1), descri from abnormal_ip  a  WHERE time_at >= ?1 AND time_at <= ?2  GROUP BY descri,from_unixtime(a.time_at,'%Y-%m-%d')  ASC", nativeQuery = true)
	public List<Object> getAbnormalIPCount(long start, long end);

	//统计当前时间段每个ip出现的个数
	@Query(value = "select ip,count(1) from abnormal_ip WHERE time_at >= ?1 AND time_at <= ?2 GROUP BY ip", nativeQuery = true)
	public List<Object> getIpCount(long start, long end);
}
