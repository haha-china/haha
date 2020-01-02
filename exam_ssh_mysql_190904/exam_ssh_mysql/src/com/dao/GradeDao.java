package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Grade;

/**
 * 成绩数据操作类
 * 负责考题相关的数据操作
 */
@Repository//注册dao曾bean等同于@Component
public class GradeDao extends BaseDao{

	//获取类目成绩
	public Grade getGrade(int category_id, String username) {
		return getSession().createQuery("from Grade where category_id=:id and user_id=(from User where username=:username)", Grade.class)
				.setParameter("id", category_id).setParameter("username", username).uniqueResult();
	}
	
	//获取当前类目下比该用户成绩少的用户
	public List<Grade> getLowGrades(int grade, int category_id) {
		return getSession().createQuery("from Grade where grade<:grade and category_id=:id", Grade.class)
				.setParameter("grade", grade).setParameter("id", category_id).list();
	}
	
	//获取当前类目下全部用户成绩记录
	public List<Grade> getAllGradeByCategory(int category_id) {
		return getSession().createQuery("from Grade where category_id=:id", Grade.class)
				.setParameter("id", category_id).list();
	}
	
	//获取当前用户成绩记录
	public List<Grade> getAllGradeByUser(int user_id) {
		return getSession().createQuery("from Grade where user_id=:id", Grade.class)
				.setParameter("id", user_id).list();
	}
	
	//分页查询某类目下部分用户的成绩记录(先按成绩排序 成绩相同时按答题数排列)
	public List<Grade> getPartGradeByCategory(int category_id, int begin, int rows) {
		return getSession().createQuery("from Grade where category_id=:id order by grade desc, total asc", Grade.class)
				.setParameter("id", category_id).setFirstResult(begin).setMaxResults(rows).list();
	}

	//获取所有类目成绩
	public List<Grade> getGradeList() {
		return getSession().createQuery("from Grade", Grade.class).list();
	}

	//分页查询类目成绩信息
	public List<Grade> getGradeList(String sortname, String sortorder, int begin, int rows) {
		return getSession().createQuery("from Grade order by "+sortname+" "+sortorder, Grade.class)
				.setFirstResult(begin).setMaxResults(rows).list();
	}

	//获取该用户所有类目成绩
	public List<Grade> getGradeCategories(int user_id) {
		return getSession().createQuery("from Grade where user_id=:id", Grade.class)
				.setParameter("id", user_id).list();
	}

}
