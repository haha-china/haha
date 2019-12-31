package com.niit.biz;

import java.util.List;

import com.niit.entity.User;
import com.niit.entity.User_g;

public interface IUser_gBiz {
	
	public void save(User_g u_g);
	public void update(User_g u_g);
	public void delete(Integer id);
	public List<User_g> findAll();
	public User_g findById(Integer id);
	public int findByname(String name);
	
}

