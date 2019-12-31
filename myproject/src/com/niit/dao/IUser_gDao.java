package com.niit.dao;

import java.util.List;

import com.niit.entity.User;
import com.niit.entity.User_g;

public interface IUser_gDao {
//	1:添加操作
	public void save(User_g u);
//	2:更新操作
	public void update(User_g u);
//	3:删除操作
	public void delete(Integer id);
//	4:查询所有
	public List<User_g> findAll();
//	5:根据ID查询
	public User_g findById(Integer id);
	
	public List<User_g> findByname(String name);
}

