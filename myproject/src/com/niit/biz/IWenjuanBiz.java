package com.niit.biz;

import java.util.ArrayList;
import java.util.List;

import com.niit.entity.User;
import com.niit.entity.Wenjuan;

public interface IWenjuanBiz {
	
	public void save(Wenjuan w);
	public void update(Wenjuan w);
	public void delete(Integer id);
	public List<Wenjuan> findAll();
	public Wenjuan findById(Integer id);
	public int findByname(String name);
	public List<Wenjuan> findByuid(String u_id);
	
}

