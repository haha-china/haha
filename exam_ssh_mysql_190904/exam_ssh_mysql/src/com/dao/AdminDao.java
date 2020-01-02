package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Admin;

/**
 * 用户数据操作类
 * 负责用户相关的数据操作
 */
@Repository//注册dao曾bean等同于@Component
public class AdminDao extends BaseDao{

	//根据id查找用户
	public Admin getAdmin(int id){
		return (Admin) getSession().get(Admin.class,id);
	}
	
	//根据用户名查找用户
	public Admin findAdmin(String username){
		return getSession().createQuery("from Admin where username=:username", Admin.class)
				.setParameter("username", username).uniqueResult();
	}
	
	//根据用户名和密码查找用户
	public Admin findAdmin(String username, String password) {
		return getSession().createQuery("from Admin where username=:username and password=:password", Admin.class)
				.setParameter("username", username).setParameter("password", password).uniqueResult();
	}

	//获取所有用户
	public List<Admin> getAllAdmin() {
		return getSession().createQuery("from Admin", Admin.class).list();
	}
	
	//分页查询部分用户//先排序再分页
	public List<Admin> getPartAdmin(String sortname, String sortorder, int begin, int rows) {
		return getSession().createQuery("from Admin order by "+sortname+" "+sortorder, Admin.class)
				.setFirstResult(begin).setMaxResults(rows).list();
	}

	//通过用户名查找用户
	public Admin getAdminByName(String username) {
		return getSession().createQuery("from Admin where username=:username", Admin.class)
				.setParameter("username", username).uniqueResult();
	}

	
}
