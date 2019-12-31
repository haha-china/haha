package com.niit.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.dao.IQuestionDao;
import com.niit.dao.QuestionDao;
import com.niit.entity.Questions;

@Service
public class QuestionBiz implements IQuestionBiz {
@Resource(name="questionDao")
IQuestionDao questionDao;
	
	@Override
	public void save(Questions w) {
	questionDao.save(w);
	}

	@Override
	public void update(Questions w) {
		questionDao.update(w);
	}

	@Override
	public void delete(Integer q_id) {
		questionDao.delete(q_id);
	}

	@Override
	public List<Questions> findAll() {
		return questionDao.findAll();
	}

	@Override
	public Questions findById(Integer id) {
		return questionDao.findById(id);
	}

	@Override
	public List<Questions> findBywid(Integer w_id) {
		return questionDao.findBywid(w_id);
	}
	

}
