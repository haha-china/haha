package com.niit.dao;

import java.util.ArrayList;
import java.util.List;

import com.niit.entity.User;
import com.niit.entity.Wenjuan;

public interface IWenjuan {
//	1:��Ӳ���
	public void save(Wenjuan w);
//	2:���²���
	public void update(Wenjuan w);
//	3:ɾ������
	public void delete(Integer id);
//	4:��ѯ����
	public List<Wenjuan> findAll();
//	5:����ID��ѯ
	public Wenjuan findById(Integer id);
	
	public List<Wenjuan> findByuid(String u_id);
	
	

}
