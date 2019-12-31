package com.niit.dao;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.niit.dao.IUserDao;
import com.niit.entity.User;
import com.niit.entity.User_g;

import jdk.nashorn.internal.ir.annotations.Reference;

/**
 * 持久层
 * @author Andy
 *@Repository(value="userDao")=@Repository
 */
@Repository
public class UserDao implements IUserDao {
	
	//注入HibernateTemplate
	@Resource(name="ht")
	private HibernateTemplate ht;
	

	@Override
	public void save(User u) {
		ht.save(u);
		
	}

	@Override
	public void update(User u) {
		ht.update(u);
	}

	@Override
	public void delete(Integer id) {
		ht.delete(findById(id));
	}

	@Override
	public List<User> findAll() {
		List<User> list =ht.loadAll(User.class);
		return ht.loadAll(User.class);
	}

	@Override
	public User findById(Integer id) {
		return ht.get(User.class, id);
	}
	@Override
	public  List<User> findByname(String name) {
		try {
			
			System.out.println("1");
			 String hql = "from User u where u.username=?";
			 List<User> rsList = (List<User>) ht.find(hql,name);
					return rsList;
		} catch (Exception e) {
			System.out.println("若查询为空报错");
			return null;
		}
		
		
		
		
	}
}

