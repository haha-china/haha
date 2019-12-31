package com.niit.dao;

import java.util.List;

import com.niit.entity.User;
import com.niit.entity.User_g;

public interface IUser_gDao {
//	1:��Ӳ���
	public void save(User_g u);
//	2:���²���
	public void update(User_g u);
//	3:ɾ������
	public void delete(Integer id);
//	4:��ѯ����
	public List<User_g> findAll();
//	5:����ID��ѯ
	public User_g findById(Integer id);
	
	public List<User_g> findByname(String name);
}

