package com.lc.crm.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lc.crm.entity.User;
import com.lc.crm.repository.UserRepository;
import com.lc.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com._21cn.open.common.cipher.StringUtil;
import com.mysql.jdbc.StringUtils;

/**
 * 用户管理功能的Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/users")
public class UserModule {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(path = "/getAll", method = RequestMethod.POST)
	public Map<String, Object> get(@RequestBody Map<String,Integer> u) {

		System.out.println("333333333333333333333");
		Map<String, Object> response = new HashMap<>();
		List<User> data = new ArrayList<>();
		long count = this.userService.count();
		long pageSize = u.get("pageSize");
		long currentPage = u.get("currentPage");
		Integer state = u.get("state");
		if (currentPage == 1){
			data.addAll(userRepository.getFirst(state,pageSize));
			response.put("data", data);
			response.put("count", count);
			return response;
		} else {
			long lastNPages = (currentPage - 1)*pageSize;
			List<User> lastN = userRepository.getFirst(state,lastNPages);
			String username = lastN.get(lastN.size() - 1).getUsername();
			data = userRepository.getLast(state,username,pageSize);
		}
		response.put("data", data);
		response.put("count", count);
		return response;
	}

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @CachePut(value = "user", key="#u.username")
    public Map<String, Object> add(@RequestBody User u) {
    	Map<String, Object> hash = new HashMap<String, Object>();
    	User user = (User) userService.findOne(u.getUsername());
        if (null!=user) {
            hash.put("msg", "相同用户账号记录已经存在！");
            hash.put("ok", false);
            return hash;
        }

        userService.save(u);

        hash.put("ok", true);
        return hash;
    }
    
    /**
	 * 修改时在前端页面上控制用户不能修改用户账号
	 * 
	 * @param u User对象
	 * @return
	 */
	@RequestMapping(value = "/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody User u) {
        HashMap<String, Object> hash = new HashMap<String, Object>();
        
        userService.save(u);

        hash.put("ok", true);
        return hash;
	}
    
    @RequestMapping(path = "/del", method = RequestMethod.POST)
    @CacheEvict(value = "user", key="#u.username")
	public Map<String, Object> del(@RequestBody User u) {
		Map<String, Object> response = new HashMap<>();
		
		this.userService.delete(u.getUsername());
		response.put("ok", true);
		
		return response;
	}
	
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public Map<String, Object> deleteb(@RequestBody User u) {
		Map<String, Object> response = new HashMap<>();
		
		String[] str = u.getUsername().split(",");
		for(int i=0;i<str.length;i++){
			this.userService.delete(str[i]);
		}
		response.put("ok", true);
		return response;
	}
	
	@RequestMapping(path = "/findByUsername", method = RequestMethod.POST)
	public Map<String, Object> findByUsername(@RequestBody Map<String, String> m) {
		Map<String, Object> response = new HashMap<>();
		long pageSize = Long.valueOf(m.get("pageSize"));
		long currentPage = Long.valueOf(m.get("currentPage"));
		Integer state = Integer.valueOf(m.get("state"));
		List<User> users = null;
		if(!StringUtil.isEmpty(m.get("username"))){
			if(!StringUtil.isEmpty(m.get("name"))){
				if(!StringUtil.isEmpty(m.get("dept"))){
					users = this.userService.find1(m.get("username"),m.get("name"),m.get("dept"),state);
				}else{
					users = this.userService.find2(m.get("username"),m.get("name"),state);
				}
			}else{
				if(!StringUtil.isEmpty(m.get("dept"))){
					users = this.userService.find3(m.get("username"),m.get("dept"),state);
				}else{
					users = this.userService.find4(m.get("username"),state);
				}
			}
		}else{
			if(!StringUtil.isEmpty(m.get("name"))){
				if(!StringUtil.isEmpty(m.get("dept"))){
					users = this.userService.find5(m.get("name"),m.get("dept"),state,pageSize,currentPage);
				}else{
					users = this.userService.find6(m.get("name"),state,pageSize,currentPage);
				}
			}else{
				if(!StringUtil.isEmpty(m.get("dept"))){
					users = this.userService.find7(m.get("dept"),state,pageSize,currentPage);
				}else{
					users = this.userService.find(state,pageSize,currentPage);
				}
			}
		}
		
		response.put("data", users);
		
		return response;
	}
	
	@RequestMapping(path = "/getUsernames", method = RequestMethod.GET)
	public Map<String, Object> getUsernames(){
		Map<String, Object> resp = new HashMap<>();
		try {
			resp.put("ok", Boolean.TRUE);
			resp.put("data", userRepository.getUsernames());
		} catch (Exception e) {
			e.printStackTrace();
			resp.put("ok", Boolean.FALSE);
		}
		return resp;
	}
	
	@RequestMapping(path = "/getNames", method = RequestMethod.GET)
	public Map<String, Object> getNames(){
		Map<String, Object> resp = new HashMap<>();
		try {
			resp.put("ok", Boolean.TRUE);
			resp.put("data", userRepository.getNames());
		} catch (Exception e) {
			e.printStackTrace();
			resp.put("ok", Boolean.FALSE);
		}
		return resp;
	}
	
	@RequestMapping(path = "/getDepts", method = RequestMethod.GET)
	public Map<String, Object> getDepts(){
		Map<String, Object> resp = new HashMap<>();
		try {
			resp.put("ok", Boolean.TRUE);
			
			List<User> depts = userRepository.getDepts();
			Set<String> deptSet=new HashSet<>();
			if(null != depts && depts.size()>0){
				for(int i = 0;i < depts.size();i++){
					User user = depts.get(i);
					if(!StringUtils.isNullOrEmpty(user.getDept())){
						deptSet.add(user.getDept());
					}
				}
			}
			
			resp.put("data", deptSet);
		} catch (Exception e) {
			e.printStackTrace();
			resp.put("ok", Boolean.FALSE);
		}
		return resp;
	}
    
//	@RequestMapping(path = "/save", method = RequestMethod.POST)
//	public Map<String, Object> add(@RequestBody User u) {
//		Map<String, Object> response = new HashMap<>();
//		
//		this.userService.save(u);
//		response.put("ok", true);
//		return response;
//	}
//	
//	@RequestMapping(path = "/judge", method = RequestMethod.POST)
//	public Map<String, Object> judge(@RequestBody User u) {
//		Map<String, Object> response = new HashMap<>();
//		List<User> usernameList;
//		
//		usernameList = this.userService.findByUsername(u.getName());
//		if(usernameList==null || usernameList.isEmpty()==true){
//			response.put("diff", true);
//		} else{
//			response.put("diff", false);
//		}
//		return response;
//	}
}
