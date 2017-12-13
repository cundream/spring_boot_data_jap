package com.lc.crm.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JpaUtil {

    /**
     * hech 2017/6/6
     */
    public static void like(Map<String, Object> m, List<Predicate> list, CriteriaBuilder criteriaBuilder, Root<?> root,
                            String key, String field) {
        if (null != m.get(key) && !"".equals(m.get(key))) {
            list.add(criteriaBuilder.like(root.get(field).as(String.class), "%" + ((String) m.get(key)).trim() + "%"));
        }
}

    public static void greaterThanOrEqualTo(Map<String, Object> m, List<Predicate> list,
                                            CriteriaBuilder criteriaBuilder, Root<?> root, String key, String field, Date startTime) {
        if (null != m.get(key) && !"".equals(m.get(key))) {
            list.add(criteriaBuilder.greaterThanOrEqualTo(root.get(field).as(Date.class), startTime));
        }
    }

    public static void lessThanOrEqualTo(Map<String, Object> m, List<Predicate> list, CriteriaBuilder criteriaBuilder,
                                         Root<?> root, String key, String field, Date endTime) {
        if (null != m.get(key) && !"".equals(m.get(key))) {
            list.add(criteriaBuilder.lessThanOrEqualTo(root.get(field).as(Date.class), endTime));
        }
    }

    public static void equal(Map<String, Object> m, List<Predicate> list, CriteriaBuilder criteriaBuilder, Root<?> root,
                             String key, String field) {
        if (null != m.get(key) && !"".equals(m.get(key))) {
            list.add(criteriaBuilder.equal(root.get(field).as(String.class), (String) m.get(key)));
        }
    }

    public static void equalInt(Map<String, Object> m, List<Predicate> list, CriteriaBuilder criteriaBuilder, Root<?> root,
                                String key, String field) {
        if (null != m.get(key) && !"".equals(m.get(key))) {
            list.add(criteriaBuilder.equal(root.get(field).as(Integer.class), (Integer.parseInt(m.get(key).toString()))));
        }
    }

    public static void greaterThanOrEqualToInt(Map<String, Object> m, List<Predicate> list,
                                               CriteriaBuilder criteriaBuilder, Root<?> root, String key, String field) {
        if (null != m.get(key) && !"".equals(m.get(key))) {
			list.add(criteriaBuilder.greaterThanOrEqualTo(root.get(field).as(Integer.class),
					Integer.parseInt(String.valueOf(m.get(key)))));
        }
    }

    public static void lessThanOrEqualToInt(Map<String, Object> m, List<Predicate> list, CriteriaBuilder criteriaBuilder,
                                            Root<?> root, String key, String field) {
        if (null != m.get(key) && !"".equals(m.get(key))) {
            list.add(criteriaBuilder.lessThanOrEqualTo(root.get(field).as(Integer.class), Integer.parseInt(m.get(key).toString())));
        }
    }

}
