package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Category;

/**
 * 考试类别数据操作类
 * 负责类别相关的数据操作
 */
@Repository//注册dao曾bean等同于@Component
public class CategoryDao extends BaseDao{

	//根据等级获取类目信息
	public List<Category> getCategoryByLevel(int level) {
		return getSession().createQuery("from Category where level=:level", Category.class)
				.setParameter("level", level).list();
	}

	//根据id获取类目信息
	public Category findCategory(int id) {
		return getSession().get(Category.class, id);
	}
	
	//根据id获取类目信息
	public Category findCategoryByName(String name) {
		return getSession().createQuery("from Category where name=:name", Category.class)
				.setParameter("name", name).uniqueResult();
	}

	//获取所有类目信息
	public List<Category> getCategoryList() {
		return getSession().createQuery("from Category", Category.class).list();
	}

	//分页获取类目信息
	public List<Category> getCategoryList(String sortname, String sortorder, int begin, int rows) {
		return getSession().createQuery("from Category order by "+sortname+" "+sortorder, Category.class)
				.setFirstResult(begin).setMaxResults(rows).list();
	}

}
