package com.lc.spring.repository;

import com.lc.spring.entity.IPAttribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface IPAttributionRepository
		extends JpaRepository<IPAttribution, Long>, JpaSpecificationExecutor<IPAttribution> {

	@Query(value = "SELECT * FROM `ip_attribution` WHERE small_number <= ?1 AND ?1<=big_number", nativeQuery = true)
	public IPAttribution getIPAttribution(Long ipNumber);

}
