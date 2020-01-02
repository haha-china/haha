package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Question;

/**
 * 考题数据操作类
 * 负责考题相关的数据操作
 */
@Repository//注册dao曾bean等同于@Component
public class QuestionDao extends BaseDao{
	
	// 获取类目下所有题目id
	public List<Question> getQuestionListByCategory(int categoryId){
		return getSession().createQuery("select id from Question where category_id=:categoryId", Question.class).setParameter("categoryId", categoryId).list();
	}

	//根据id获取问题信息
	public Question getQuestionById(int question_id) {
		return getSession().get(Question.class, question_id);
	}

	//根据id获取考题的答案等信息
	public Question getQuestionResultById(int question_id) {
		return getSession().createQuery("from Question where id=:id", Question.class)
				.setParameter("id", question_id).uniqueResult();
	}

	//获取某考卷中的全部考题
	public List<Question> getAllCategoryQuestion(int category_id) {
		return getSession().createQuery("from Question where category_id=:id", Question.class)
				.setParameter("id", category_id).list();
	}

	//分页查询某考卷中的题目信息
	public Question getPartQuestionIntro(int paper_id, int begin, int rows) {
		return getSession().createQuery("from Question where exampaper_id=:id", Question.class)
				.setParameter("id", paper_id).setFirstResult(begin).setMaxResults(rows).uniqueResult();
	}

	//获取全部考题
	public List<Question> getAllQuestion() {
		return getSession().createQuery("from Question", Question.class).list();
	}

	//分页获取考题信息
	public List<Question> getQuestionList(String sortname, String sortorder, int begin, int rows) {
		return getSession().createQuery("from Question order by "+sortname+" "+sortorder, Question.class)
				.setFirstResult(begin).setMaxResults(rows).list();
	}

}
