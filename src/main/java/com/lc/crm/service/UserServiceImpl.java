package com.lc.crm.service;

import java.util.List;
import java.util.Map;

import com.lc.crm.entity.User;
import com.lc.crm.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * 用户管理功能的service接口的实现
 * 
 * @author Administrator
 *
 */
@Service
@CacheConfig
public class UserServiceImpl implements UserService {
	
	public static Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	public UserServiceImpl(){}

	@Override
	@CachePut(value = "user", key="#user.username")
	public User save(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return this.userRepository.save(user);
	}


	@Override
	@CacheEvict(value = "user", key="#name")
	public void delete(String name) {
		this.userRepository.delete(name);
	}

	@Override
	public List<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
	}

	@Override
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}

	@Override
	public User findOne(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUser(username);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return this.userRepository.count();
	}
	


	

	
	private static RestTemplate restTemplate = null;
	
	/**
	 * 调用天翼账号开放平台的认证服务实现对用户的认证
	 * 
	 * @param requestData
	 * @return
	 * @throws Exception
	 */
	@Override
	@Cacheable(value = "EAccountAuth", key="#key")
	public String authenticateInEAccount(Map<String, String> requestData, String key) throws Exception {
		
	/*	log.info("调用天翼账号开放平台API认证用户："+key);

		// 设置各请求参数并生成签名
		RequestParasUtil.setParas(requestData, OpenApiConstants.appId, OpenApiConstants.appSecret,
				OpenApiConstants.version, "json", OpenApiConstants.clientType);

		return HttpHelper.post(OpenApiConstants.LOGIN_URL, requestData,proxyHost,proxyPort,enableProxy);
		*/
	return "Hello";
//		return RequestParasUtil.getDataFromOpenAPIServer(OpenApiConstants.LOGIN_URL,
//				requestData);
	}

	@Override
	public List<User> find1(String username, String name, String dept,Integer state) {
		return this.userRepository.find1(username, name, dept,state);
	}

	@Override
	public List<User> find2(String username, String name,Integer state) {
		return this.userRepository.find2(username, name,state);
	}

	@Override
	public List<User> find3(String username, String dept,Integer state) {
		return this.userRepository.find3(username, dept,state);
	}

	@Override
	public List<User> find4(String username,Integer state) {
		return this.userRepository.find4(username,state);
	}

	@Override
	public List<User> find5(String name, String dept, Integer state, long pageSize, long currentPage) {
		if (currentPage == 1){
			List<User> users = userRepository.getFirst5(name, dept,state,pageSize);
			return users;
		}else{
			long lastNPages = (currentPage - 1)*pageSize;
			List<User> lastN = userRepository.getFirst5(name, dept,state,lastNPages);
			String name1 = lastN.get(lastN.size() - 1).getUsername();
			List<User> users = userRepository.getLast5(name, dept,state,name1,pageSize);
			return users;
		}
	}

	@Override
	public List<User> find6(String name, Integer state, long pageSize, long currentPage) {
		if (currentPage == 1){
			List<User> users = userRepository.getFirst6(name,state,pageSize);
			return users;
		}else{
			long lastNPages = (currentPage - 1)*pageSize;
			List<User> lastN = userRepository.getFirst6(name,state,lastNPages);
			String name1 = lastN.get(lastN.size() - 1).getUsername();
			List<User> users = userRepository.getLast6(name,state,name1,pageSize);
			return users;
		}
	}

	@Override
	public List<User> find7(String dept, Integer state, long pageSize, long currentPage) {
		if (currentPage == 1){
			List<User> users = userRepository.getFirst7(dept,state,pageSize);
			return users;
		}else{
			long lastNPages = (currentPage - 1)*pageSize;
			List<User> lastN = userRepository.getFirst7(dept,state,lastNPages);
			String name1 = lastN.get(lastN.size() - 1).getUsername();
			List<User> users = userRepository.getLast7(dept,state,name1,pageSize);
			return users;
		}
	}

	@Override
	public List<User> find(Integer state, long pageSize, long currentPage) {
		if (currentPage == 1){
			List<User> users = userRepository.getFirst(state,pageSize);
			return users;
		}else{
			long lastNPages = (currentPage - 1)*pageSize;
			List<User> lastN = userRepository.getFirst(state,lastNPages);
			if(null == lastN || lastN.size()<1){
				return null;
			}
			String name1 = lastN.get(lastN.size() - 1).getUsername();
			List<User> users = userRepository.getLast(state,name1,pageSize);
			return users;
		}
	}

}
