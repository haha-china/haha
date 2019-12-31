package com.niit.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.niit.entity.User;
import com.niit.entity.Wenjuan;

@Repository
public class WenjuanDao implements IWenjuan {
	@Resource(name="ht")
	private HibernateTemplate ht;
	

	@Override
	public void save(Wenjuan w) {
		ht.save(w);

	}

	@Override
	public void update(Wenjuan w) {
		ht.update(w);
		

	}

	@Override
	public void delete(Integer id) {
			ht.delete(id);
	}

	@Override
	public List<Wenjuan> findAll() {
		List<Wenjuan> list =	ht.loadAll(Wenjuan.class);
		return ht.loadAll(Wenjuan.class);
	}

	@Override
	public Wenjuan findById(Integer id) {
		return ht.get(Wenjuan.class, id);
	}
	@Override
	public List<Wenjuan> findByuid(String u_id) {
		
	try {
			
			System.out.println("1");
			 String hql = "from Wenjuan w where w.u_id=?";
			 List<Wenjuan> rsList = (List<Wenjuan>) ht.find(hql,u_id);
					return rsList;
		} catch (Exception e) {
			System.out.println("Èô²éÑ¯Îª¿Õ±¨´í");
			return null;
		
	}
	}
}
