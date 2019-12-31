package com.niit.dao;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.niit.entity.Questions;
import com.niit.entity.Wenjuan;
@Repository
public class QuestionDao implements IQuestionDao {
	@Resource(name="ht")
	private HibernateTemplate ht;
	

	@Override
	public void save(Questions q) {
		ht.save(q);

	}

	@Override
	public void update(Questions q) {
		ht.update(q);
	}

	@Override
	public void delete(Integer q_id) {
		ht.delete(q_id);
	}

	@Override
	public List<Questions> findAll() {
		
		return ht.loadAll(Questions.class);
	}

	@Override
	public Questions findById(Integer q_id) {
		return ht.get(Questions.class, q_id);
	}

	@Override
	public List<Questions> findBywid(Integer w_id) {
try {
			
			System.out.println("3");
			 String hql = "from Questions q where q.w_id = 1";
			 List<Questions> rsList = (List<Questions>) ht.find(hql);
			 System.out.println("---"+rsList.size());
					return rsList;
		} catch (Exception e) {
			System.out.println("Èô²éÑ¯Îª¿Õ±¨´í");
			return null;
		
	}
	}

}
