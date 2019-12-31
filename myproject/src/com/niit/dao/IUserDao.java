package com.niit.dao;

import java.util.List;

import com.niit.entity.User;

public interface IUserDao {
//	1:添加操作
	public void save(User u);
//	2:更新操作
	public void update(User u);
//	3:删除操作
	public void delete(Integer id);
//	4:查询所有
	public List<User> findAll();
//	5:根据ID查询
	public User findById(Integer id);
	
	public List<User> findByname(String name);
}

