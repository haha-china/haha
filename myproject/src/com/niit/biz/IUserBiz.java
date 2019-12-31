package com.niit.biz;

import java.util.List;

import com.niit.entity.User;

public interface IUserBiz {
	
	public void save(User u);
	public void update(User u);
	public void delete(Integer id);
	public List<User> findAll();
	public User findById(Integer id);
	public int findByname(String name);
	
}

