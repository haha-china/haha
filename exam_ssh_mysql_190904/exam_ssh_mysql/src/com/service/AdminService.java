package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AdminDao;
import com.entity.Admin;

/**
 * 用户服务类
 * 处理用户相关业务
 */
@Service
@Transactional	//将此类中所有方法加入事务处理
public class AdminService {

	@Resource
	private AdminDao adminDao;


	//是否有此用户名
	public boolean isAdmin(Admin admin){
		if (adminDao.findAdmin(admin.getUsername())==null) {
			return false;
		}return true;
	}
	
	//用户和密码是否正确
	public boolean isValidAdmin(Admin admin){
		if (adminDao.findAdmin(admin.getUsername(), admin.getPassword())==null) {
			return false;
		}return true;
	}

	//获取所有用户
	public List<Admin> getAdmins() {
		return adminDao.getAllAdmin();
	}

	//获取用户总数
	public int getAdminTotal(){
		return getAdmins().size();
	}

	//获取用户列表
	public List<Admin> getAdminList(String sortname, String sortorder,int begin, int rows) {
		return adminDao.getPartAdmin(sortname, sortorder, begin, rows);
	}

	//添加用户
	public boolean add(Admin admin) {
		return adminDao.save(admin);
	}

	//删除用户
	public boolean delete(Admin admin) {
		return adminDao.delete(admin);
	}

	//更新用户
	public boolean update(Admin admin) {
		Admin adminOld = adminDao.findAdmin(admin.getUsername()); //先去处该用户其他数据, 以免hibernate将其他字段设为空
		adminOld.setPassword(admin.getPassword());	//设置新密码
		if (adminDao.update(adminOld)) return true;
		return false;
	}

	//通过用户名查找用户
	public Admin get(String username) {
		return adminDao.getAdminByName(username);
	}

	//通过id查找用户
	public Admin get(int id) {
		return adminDao.getAdmin(id);
	}

}
