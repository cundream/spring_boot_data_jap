package com.lc.spring.service;



import com.lc.spring.entity.HitAccount;
import com.lc.spring.entity.Metric;

import java.util.List;

public interface HitAccountService {


    List<Metric> getAccountCount(long start, long end);
}
