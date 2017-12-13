package com.lc.crm.util;


import com.lc.spring.entity.Metric;
import com.lc.spring.entity.Metrics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MetricTranslator {

	public static List<Metric> translate(List<Object> list, int nameIdx, Map<String, String> translator, int valueIdx) {

		List<Metric> result = new ArrayList<Metric>();

		for (Object o : list) {
			Object obj[] = (Object[]) o;

			Metric metric = new Metric();
			String name = (String) obj[nameIdx];

			if (null != translator && translator.containsKey(name)) {
				name = translator.get(name);
			}

			metric.setName(name);
			metric.setValue(obj[valueIdx]);

			result.add(metric);
		}

		return result;
	}

	/**
	 * 为创建类别视图
	 * @param list
	 * @param nameIdx
	 * @param translator
	 * @param valueIdx
	 * @param category
	 * @return
	 */


	public static List<Metrics> translate(List<Object> list, int nameIdx, Map<String, String> translator, int valueIdx, int category) {

		List<Metrics> result = new ArrayList<Metrics>();

		for (Object o : list) {
			Object obj[] = (Object[]) o;

			Metrics metrics = new Metrics();
			String name = (String) obj[nameIdx];

			if (null != translator && translator.containsKey(name)) {
				name = translator.get(name);
			}

			metrics.setName(name);
			metrics.setValue(obj[valueIdx]);
			metrics.setCategory(obj[category]);

			result.add(metrics);
		}

		return result;
	}

}
