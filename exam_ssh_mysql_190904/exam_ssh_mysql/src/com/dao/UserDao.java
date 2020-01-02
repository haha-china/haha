package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.User;

/**
 * 用户数据操作类
 * 负责用户相关的数据操作
 */
@Repository//注册dao曾bean等同于@Component
public class UserDao extends BaseDao{

	//根据id查找用户
	public User getUser(int id){
		return getSession().get(User.class,id);
	}
	
	//根据用户名查找用户
	public User findUser(String username){
		return getSession().createQuery("from User where username=:username", User.class)
				.setParameter("username", username).uniqueResult();
	}
	
	//根据用户名和密码查找用户
	public User findUser(String username, String password) {
		return getSession().createQuery("from User where username=:username and password=:password", User.class)
				.setParameter("username", username).setParameter("password", password).uniqueResult();
	}

	//获取所有用户
	public List<User> getAllUser() {
		return getSession().createQuery("from User", User.class).list();
	}
	
	//分页查询部分用户//先排序再分页
	public List<User> getPartUser(String sortname, String sortorder, int begin, int rows) {
		return getSession().createQuery("from User order by "+sortname+" "+sortorder, User.class)
			.setFirstResult(begin).setMaxResults(rows).list();
	}

	//通过用户名查找用户
	public User getUserByName(String username) {
		return getSession().createQuery("from User where username=:username", User.class)
				.setParameter("username", username).uniqueResult();
	}

	
}
