package com.lc.crm.config;

//import org.springframework.boot.orm.jpa.EntityScan;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.lc.spring.repository")
@EntityScan("com.lc.spring.entity")
public class JpaConfiguration {

}
