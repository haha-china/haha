package com.niit.dao;

import java.util.List;

import com.niit.entity.*;

public interface IQuestionDao {
	
//		1:添加操作
		public void save(Questions w);
//		2:更新操作
		public void update(Questions w);
//		3:删除操作
		public void delete(Integer q_id);
//		4:查询所有
		public List<Questions> findAll();
//		5:根据ID查询
		public Questions findById(Integer id);
		
		public List<Questions> findBywid(Integer w_id);
		
		
		

	
}
