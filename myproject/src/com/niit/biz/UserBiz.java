package com.niit.biz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.dao.IUserDao;
import com.niit.entity.User;

@Service
public class UserBiz implements IUserBiz {
	//注入dao对象
	@Resource(name="userDao")
	private IUserDao userDao;
	

	@Override
	public void save(User u) {
		userDao.save(u);
	}

	@Override
	public void update(User u) {
		userDao.update(u);
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);
	}

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		list= userDao.findAll();
		System.out.println("-------"  +  list);
		return  list;
	}

	@Override
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	@Override
	public int findByname(String name) {
		List<User>  list= (List<User>) userDao.findByname(name);
		if(list.size()>0) {
			System.out.println("用户存在");
			return list.get(0).getU_id();
			
		}
		return 0;
		
		
		
	}

}

