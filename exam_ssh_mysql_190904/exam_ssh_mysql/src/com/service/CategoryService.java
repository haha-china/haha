package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CategoryDao;
import com.entity.Category;
import com.entity.Grade;

/**
 * 用户服务类
 * 处理用户相关业务
 */
@Service
@Transactional	//将此类中所有方法加入事务处理
public class CategoryService {

	@Resource
	private CategoryDao categoryDao;
	@Resource
	private GradeService gradeService;

	
	//获取类目的所有名称
	public List<Category> getCategoryByLevel(int level) {
		return categoryDao.getCategoryByLevel(level);
	}

	//根据id获取类目信息
	public Category getCategory(int id) {
		return categoryDao.findCategory(id);
	}
	
	//根据name获取类目信息
	public Category getCategoryByName(String name) {
		return categoryDao.findCategoryByName(name);
	}

	//获取所有类目信息
	public List<Category> getCategoryList(){
		return categoryDao.getCategoryList();
	}
	
	//获取类目总数
	public int getCategoryTotal() {
		return getCategoryList().size();
	}

	//分页获取类目信息
	public List<Category> getCategoryList(String sortname, String sortorder,int begin, int rows) {
		return categoryDao.getCategoryList(sortname, sortorder, begin, rows);
	}

	//添加类目
	public boolean add(Category category) {
		return categoryDao.save(category);
	}

	//修改类目
	public boolean update(Category category) {
		return categoryDao.update(category);
	}

	//删除类目
	public boolean delete(Category category) {
		List<Grade> gradeList = gradeService.getAllGradeByCategory(category.getId());
		for(Grade grade : gradeList){ // 删除用户前先删除用户成绩记录
			gradeService.delete(grade);
		}
		return categoryDao.delete(category);
	}
	
	

}
