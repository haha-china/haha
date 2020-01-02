package com.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.Category;
import com.service.CategoryService;

/**
 * 类目Action
 * 负责管理员对类目的操作
 */
@Namespace("/admin")
@ParentPackage("json-default")	//指定Action所在包的父包, 必须这样设置才能处理json
@Results({
		//配置返回json字符串 includeProperties属性为包含入json的属性
	@Result(type="json",name="categorylist",params={"includeProperties","page,total,rows.*"})
})
@SuppressWarnings("serial")
public class CategoryAction extends BaseAction{

	@Resource
	private CategoryService categoryService;
	private Category category;

	//struts将下列对象转换成json
	public List<Category> rows;
	public int page;
	public int total;

	
	//获取类目列表
	@Action("categoryList")
	public String categoryList(){
		String sortname = getServletRequest().getParameter("sortname");	//排序字段
		String sortorder = getServletRequest().getParameter("sortorder");	//排序方式
		int rp = Integer.valueOf(getServletRequest().getParameter("rp"));	//每页显示数量
		page = Integer.valueOf(getServletRequest().getParameter("page"));	//当前页码
		total = categoryService.getCategoryTotal();		//获取类目总数
		rows = categoryService.getCategoryList(sortname, sortorder, (page-1)*rp, rp);//分页查询类目信息
		return "categorylist";
	}

	//添加类目
	@Action("categoryAdd")
	public void add() throws IOException{
		if (categoryService.add(category)) {
			getServletResponse().getWriter().write("添加成功!!");
		}else {
			getServletResponse().getWriter().write("添加失败!!");
		}
	}

	//修改类目
	@Action("categoryUpdate")
	public void update() throws IOException{
		if (categoryService.update(category)) {
			getServletResponse().getWriter().write("修改成功");
		}else {
			getServletResponse().getWriter().write("修改失败");
		}
	}	
	
	//删除类目
	@Action("categoryDelete")
	public void delete() throws IOException{
		String idstr = getServletRequest().getParameter("idstr");
		String[] ids = idstr.split(",");
		for (int i = 0; i < ids.length; i++) {
			Category category = new Category();
			category.setId(Integer.parseInt(ids[i]));
			if (!categoryService.delete(category)){
				getServletResponse().getWriter().write("删除失败");
			}
		}
		getServletResponse().getWriter().write("删除成功");
	}

	

	public List<Category> getRows() {
		return rows;
	}
	public void setRows(List<Category> rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
