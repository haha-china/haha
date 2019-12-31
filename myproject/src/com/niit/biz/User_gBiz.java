package com.niit.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.dao.IUserDao;
import com.niit.dao.IUser_gDao;
import com.niit.entity.User;
import com.niit.entity.User_g;
@Service
public class User_gBiz implements IUser_gBiz {
	@Resource(name="user_gDao")
	private IUser_gDao user_gDao;
	@Override
	public void save(User_g u_g) {
		 user_gDao.save(u_g);

	}

	@Override
	public void update(User_g u_g) {
		user_gDao.update(u_g);

	}

	@Override
	public void delete(Integer id) {
		user_gDao.delete(id);
	}

	@Override
	public List<User_g> findAll() {
		
		return user_gDao.findAll();
	}

	@Override
	public User_g findById(Integer id) {
		
		return user_gDao.findById(id);
	}

	@Override
	public int findByname(String name) {
		
		List<User_g>  list= (List<User_g>) user_gDao.findByname(name);
		System.out.println("-----------------------------------"+list.size());

		if(list.size()>0) {
			System.out.println("”√ªß¥Ê‘⁄");
			return list.get(0).getId_g();
			
			
		}
		return 0;
	}

}
