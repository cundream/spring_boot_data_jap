package com.lc.crm.repository;

import java.util.List;

import com.lc.crm.entity.User;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;


/**
 * 用户管理功能的DAO接口
 * 
 * @author Administrator
 *
 */
public interface UserRepository extends CrudRepository<User, String> {
	@Query("Select * from user where username=?0")
	public List<User> findByUsername(String username);
	
	@Query("Select * from user where username=?0")
	public User findByUser(String username);
	
	@Query("Select * from user where state=?0 limit ?1 allow filtering")
	public List<User> getFirst(long state, long pagesize);
	
	@Query("Select * from user where token(username) > token(?1) and state=?0 limit ?2 allow filtering")
	public List<User> getLast(long state, String username, long pageSize);

	@Query("Select * from user where username=?0 and name=?1 and dept=?2 and state=?3 allow filtering")
	public List<User> find1(String username, String name, String dept, Integer state);

	@Query("Select * from user where username=?0 and name=?1 and state=?2 allow filtering")
	public List<User> find2(String username, String name, Integer state);

	@Query("Select * from user where username=?0 and dept=?1 and state=?2 allow filtering")
	public List<User> find3(String username, String dept, Integer state);

	@Query("Select * from user where username=?0 and state=?1 allow filtering")
	public List<User> find4(String username, Integer state);

	@Query("Select * from user where name=?0 and dept=?1 and state=?2 limit ?3 allow filtering")
	public List<User> getFirst5(String name, String dept, Integer state, long pageSize);

	@Query("Select * from user where name=?0 and dept=?1 and state=?2 and token(username) > token(?3) limit ?4 allow filtering")
	public List<User> getLast5(String name, String dept, Integer state, String name1, long pageSize);

	@Query("Select * from user where name=?0 and state=?1 limit ?2 allow filtering")
	public List<User> getFirst6(String name, Integer state, long pageSize);

	@Query("Select * from user where name=?0 and state=?1 and token(username) > token(?2) limit ?3 allow filtering")
	public List<User> getLast6(String name, Integer state, String name1, long pageSize);

	@Query("Select * from user where dept=?0 and state=?1 limit ?2 allow filtering")
	public List<User> getFirst7(String dept, Integer state, long pageSize);

	@Query("Select * from user where dept=?0 and state=?1 and token(username) > token(?2) limit ?3 allow filtering")
	public List<User> getLast7(String dept, Integer state, String name1, long pageSize);

	@Query("Select username from user")
	public List<String> getUsernames();

	@Query("Select name from user")
	public List<String> getNames();

	@Query("Select dept from user")
	public List<User> getDepts();

	
}
