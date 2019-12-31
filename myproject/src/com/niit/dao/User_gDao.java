package com.niit.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.niit.entity.User;
import com.niit.entity.User_g;
@Repository
public class User_gDao implements IUser_gDao {

	@Resource(name="ht")
	private HibernateTemplate ht;
	
	@Override
	public void save(User_g u_g) {
		ht.save(u_g);

	}

	@Override
	public void update(User_g u_g) {
			ht.update(u_g);
	}

	@Override
	public void delete(Integer id) {
				ht.delete(id);
	}

	@Override
	public List<User_g> findAll() {
		
		return ht.loadAll(User_g.class);
	}

	@Override
	public User_g findById(Integer id) {
		return ht.get(User_g.class, id);
		
	}

	@Override
	public  List<User_g> findByname(String name) {
	try {
			
			System.out.println("1");
			 String hql = "from User_g u_g where u_g.username_g=?";
			 List<User_g> rsList = (List<User_g>) ht.find(hql,name);
					return rsList;
		} catch (Exception e) {
			System.out.println("Èô²éÑ¯Îª¿Õ±¨´í");
			return null;
		}
		
	}

}
