package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.GradeDao;
import com.entity.Grade;

/**
 * 成绩服务类
 * 处理成绩相关业务
 */
@Service
@Transactional	//将此类中所有方法加入事务处理
public class GradeService {

	@Resource
	private GradeDao gradeDao;
	
	@Resource
	private UserService userService;
	@Resource
	private CategoryService categoryService;
	
	
	//获取某用户的类目成绩
	public Grade getGrade(int category_id, String username) {
		Grade grade = gradeDao.getGrade(category_id,username);
		if (grade==null || grade.getId()<=0) {
			Grade gradeNew = new Grade();
			gradeNew.setCategory(categoryService.getCategory(category_id));
			gradeNew.setUser(userService.getUser(username));
			gradeNew.setTotal(0); //答题总数为0
			initGrade(gradeNew);	//初始化一个成绩
			grade = gradeDao.getGrade(category_id,username);
		}
		return grade;
	}
	
	//获取该用户所有类目成绩
	public List<Grade> getGradeCategories(int user_id) {
		return gradeDao.getGradeCategories(user_id);
	}
	
	//更新类目成绩
	public boolean update(Grade grade) {
		return gradeDao.update(grade);
	}

	//初始化添加类目成绩记录
	public boolean initGrade(Grade grade) {
		return gradeDao.save(grade);
	}

	//获取当前类目下比该用户成绩少的用户数量
	public int getLowGradeNumber(int grade,int category_id) {
		return gradeDao.getLowGrades(grade, category_id).size();
	}
	
	//获取当前类目下全部用户数量
	public int getAllGradeNumber(int category_id) {
		return gradeDao.getAllGradeByCategory(category_id).size();
	}
	
	//获取某类目下所有用户的成绩记录
	public List<Grade> getAllGradeByCategory(int category_id) {
		return gradeDao.getAllGradeByCategory(category_id);	
	}
	
	//获取某用户的成绩记录
	public List<Grade> getAllGradeByUser(int user_id) {
		return gradeDao.getAllGradeByUser(user_id);	
	}
	
	//分页获取某类目下用户的成绩记录
	public List<Grade> getPartGradeByCategory(int category_id, int begin, int rows) {
		return gradeDao.getPartGradeByCategory(category_id,begin,rows);
	}

	//获取所有类目成绩
	public List<Grade> getGradeList(){
		return gradeDao.getGradeList();
	}
	
	//获取类目成绩总数	
	public int getGradeTotal() {
		return getGradeList().size();
	}

	//分页查询类目成绩信息
	public List<Grade> getGradeList(String sortname, String sortorder, int begin, int rows) {
		List<Grade> gradeList = gradeDao.getGradeList(sortname,sortorder,begin,rows);
		for (Grade grade : gradeList) {
			grade.setCategoryName(grade.getCategory().getName());
			grade.setUserName(grade.getUser().getUsername());
		}
		return gradeList;
	}

	//添加类目成绩
	public boolean add(Grade grade) {
		return gradeDao.save(grade);
	}

	//删除类目成绩
	public boolean delete(Grade grade) {
		return gradeDao.delete(grade);
	}
	
}
