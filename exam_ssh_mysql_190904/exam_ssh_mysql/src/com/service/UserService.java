package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.Grade;
import com.entity.User;

/**
 * 用户服务类
 * 处理用户相关业务
 */
@Service
@Transactional	//将此类中所有方法加入事务处理
public class UserService {

	@Resource
	private UserDao userDao;
	@Resource
	private GradeService gradeService;


	//是否有此用户名
	public boolean isUser(User user){
		if (userDao.findUser(user.getUsername())==null) {
			return false;
		}return true;
	}
	
	//用户和密码是否正确
	public boolean isValidUser(User user){
		if (userDao.findUser(user.getUsername(), user.getPassword())==null) {
			return false;
		}return true;
	}

	//获取所有用户
	public List<User> getUsers() {
		return userDao.getAllUser();
	}

	//获取用户总数
	public int getUserTotal(){
		return getUsers().size();
	}

	//获取用户列表
	public List<User> getUserList(String sortname, String sortorder,int begin, int rows) {
		return userDao.getPartUser(sortname, sortorder, begin, rows);
	}

	//添加用户
	public boolean addUser(User user) {
		return userDao.save(user);
	}

	//删除用户
	public boolean deleteUser(User user) {
		List<Grade> gradeList = gradeService.getAllGradeByUser(user.getId());
		for(Grade grade : gradeList){ // 删除用户前先删除用户成绩记录
			gradeService.delete(grade);
		}
		return userDao.delete(userDao.getUser(user.getId()));
	}

	//更新用户
	public boolean updateUser(User user) {
		User userOld = userDao.findUser(user.getUsername()); //先去处该用户其他数据, 以免hibernate将其他字段设为空
		userOld.setPassword(user.getPassword());	//设置新密码
		userOld.setEmail(user.getEmail());
		userOld.setIntro(user.getIntro());
		return userDao.update(userOld);
	}
	
	//更新积分
	public boolean updateTotal(String username, int total){
		User userOld = userDao.findUser(username); //先去处该用户其他数据, 以免hibernate将其他字段设为空
		userOld.setTotal(userOld.getTotal()+total);
		return userDao.update(userOld);
	}

	//通过用户名查找用户
	public User getUser(String username) {
		return userDao.getUserByName(username);
	}

	//通过id查找用户
	public User getUser(int id) {
		return userDao.getUser(id);
	}
}
