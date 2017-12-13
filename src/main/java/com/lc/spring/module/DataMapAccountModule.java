package com.lc.spring.module;

import com.lc.crm.util.TimeUtil;
import com.lc.spring.entity.Metric;
import com.lc.spring.service.AbnormalIPService;
import com.lc.spring.service.IPAttributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dataMapAccount")
public class DataMapAccountModule {
    @Autowired
    private AbnormalIPService abnormalIPService;

    @Autowired
    private IPAttributionService iPAttributionService;

    @RequestMapping(path = "/getCountryView", method = RequestMethod.POST)
    public Map<String, Object> getCountryView(@RequestBody Map<String, Object> m) {
        Map<String, Object> response = new HashMap<>();
        if (null == m.get("startTime") || "".equals(m.get("startTime")) || null == m.get("endTime") ||
            "".equals(m.get("endTime"))) {
            Date date = new Date();
            m.put("startTime", TimeUtil.getOnWeekStartTime(date).getTime() / 1000);
            m.put("endTime", TimeUtil.getEndTime(date).getTime() / 1000);
        }

        long startTime = Long.valueOf(String.valueOf(m.get("startTime"))).longValue();
        long endTime = Long.valueOf(String.valueOf(m.get("endTime"))).longValue();
        
        List<Metric> ipList = abnormalIPService.getIpCount(startTime, endTime);
        response.put("data", statisticsCountry(ipList));
        return response;
    }

    @RequestMapping(path = "/getProvinceView", method = RequestMethod.POST)
    public Map<String, Object> getProvinceView(@RequestBody Map<String, Object> m) {
        Map<String, Object> response = new HashMap<>();
        if (null == m.get("startTime") || "".equals(m.get("startTime")) || null == m.get("endTime") ||
            "".equals(m.get("endTime"))) {
            Date date = new Date();
            m.put("startTime", TimeUtil.getOnWeekStartTime(date).getTime() / 1000);
            m.put("endTime", TimeUtil.getEndTime(date).getTime() / 1000);
        }

        long startTime = Long.valueOf(String.valueOf(m.get("startTime"))).longValue();
        long endTime = Long.valueOf(String.valueOf(m.get("endTime"))).longValue();
        List<Metric> ipList = abnormalIPService.getIpCount(startTime, endTime);
        response.put("data", statisticsProvince(ipList));
        return response;
    }

    // 查询返回国家集合
    public Map<String, Integer> statisticsCountry(List<Metric> list) {
        Map<String, Integer> response = new HashMap<>();

        for (Metric metric: list) {
            String country = iPAttributionService.getIpAttributionCountry(metric.getName());
            if (response.get(country) != null && !"".equals(response.get(country))) {
                int countryNum = Integer.parseInt(String.valueOf(response.get(country)));
                int metrNum = Integer.parseInt(String.valueOf(metric.getValue()));
                int allNum = countryNum + metrNum;
                response.put(country, allNum);
            } else {
                response.put(country, Integer.parseInt(String.valueOf(metric.getValue())));
            }
        }
        return response;
    }

    // 查询返回省集合
    public Map<String, Integer> statisticsProvince(List<Metric> list) {
        Map<String, Integer> response = new HashMap<>();

        for (Metric metric: list) {
            String country = iPAttributionService.getIpAttributionProvince(metric.getName());
            if (response.get(country) != null && !"".equals(response.get(country))) {
                int countryNum = Integer.parseInt(String.valueOf(response.get(country)));
                int metrNum = Integer.parseInt(String.valueOf(metric.getValue()));
                int allNum = countryNum + metrNum;
                response.put(country, allNum);
            } else {
                response.put(country, Integer.parseInt(String.valueOf(metric.getValue())));
            }
        }
        return response;
    }

}
