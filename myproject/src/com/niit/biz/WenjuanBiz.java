package com.niit.biz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.sym.Name;
import com.niit.dao.IWenjuan;
import com.niit.dao.WenjuanDao;
import com.niit.entity.Wenjuan;
@Service
public class WenjuanBiz implements IWenjuanBiz {

	@Resource(name="wenjuanDao")
	private WenjuanDao wenjuanDao;

//	public void setWenjuandao (WenjuanDao wenjuandao) {
//		this.wenjuandao=wenjuandao;
//		
//	}

	
	@Override
	public void save(Wenjuan w) {
		wenjuanDao.save(w);
	}

	@Override
	public void update(Wenjuan w) {
		wenjuanDao.update(w);
	}

	@Override
	public void delete(Integer id) {
		wenjuanDao.delete(id);
	}

	@Override
	public List<Wenjuan> findAll() {
		List<Wenjuan> list = new ArrayList<>();
		list= wenjuanDao.findAll();
		System.out.println("-------"  +  list);
		
		return  list;
	}

	@Override
	public Wenjuan findById(Integer id) {
		return wenjuanDao.findById(id);
	}

	@Override
	public int findByname(String name) {
		return 0;
	}
	@Override
	public List<Wenjuan> findByuid(String u_id){
		return wenjuanDao.findByuid(u_id);
		
	}

}
