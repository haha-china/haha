package com.niit.dao;

import java.util.ArrayList;
import java.util.List;

import com.niit.entity.User;
import com.niit.entity.Wenjuan;

public interface IWenjuan {
//	1:添加操作
	public void save(Wenjuan w);
//	2:更新操作
	public void update(Wenjuan w);
//	3:删除操作
	public void delete(Integer id);
//	4:查询所有
	public List<Wenjuan> findAll();
//	5:根据ID查询
	public Wenjuan findById(Integer id);
	
	public List<Wenjuan> findByuid(String u_id);
	
	

}
