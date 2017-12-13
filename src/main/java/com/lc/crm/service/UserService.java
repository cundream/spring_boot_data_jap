package com.lc.crm.service;


import com.lc.crm.entity.User;

import java.util.List;
import java.util.Map;


/**
 * 用户管理功能的service的接口
 * 
 * @author Administrator
 *
 */
public interface UserService {
	/**
	 * 新增数据到持久存储
	 * 
	 * @param
	 * @return
	 */
	User save(User user);
	
	/**
	 * 修改持久存储中的数据
	 * 
	 * @param
	 * @return
	 */
	User update(User user);
	
	/**
	 * 查找持久存储中的数据
	 * 
	 * @param
	 * @return
	 */
	List<User> findByUsername(String username);
	
	/**
	 * 删除持久存储中的一条记录。
	 * 
	 * @param
	 * @return
	 */
	void delete(String name);
	
	Iterable<User> findAll();

	User findOne(String username);
	
	long count();
	
	public String authenticateInEAccount(Map<String, String> requestData, String key) throws Exception;

	List<User> find1(String username, String name, String dept, Integer state);

	List<User> find2(String username, String name, Integer state);

	List<User> find3(String username, String dept, Integer state);

	List<User> find4(String username, Integer state);

	List<User> find5(String name, String dept, Integer state, long pageSize, long currentPage);

	List<User> find6(String name, Integer state, long pageSize, long currentPage);

	List<User> find7(String dept, Integer state, long pageSize, long currentPage);

	List<User> find(Integer state, long pageSize, long currentPage);

	
	
}
