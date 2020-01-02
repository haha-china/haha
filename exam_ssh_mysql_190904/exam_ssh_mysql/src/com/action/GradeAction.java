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
import com.entity.Grade;
import com.entity.User;
import com.service.CategoryService;
import com.service.GradeService;
import com.service.UserService;

/**
 * 成绩Action
 * 负责管理员对成绩的操作
 */
@Namespace("/admin")
@ParentPackage("json-default")	//指定Action所在包的父包, 必须这样设置才能处理json
@Results({
	@Result(name="grade_list",location="/admin/grade-list.jsp"),
	//配置返回json字符串 includeProperties属性为包含入json的属性
	@Result(type="json",name="grade-categorylist",params={"includeProperties","page,total,rows.*"}),
	@Result(type="json",name="grade-paperlist",params={"includeProperties","page,total,rows.*"}),
})
@SuppressWarnings("serial")
public class GradeAction extends BaseAction{

	@Resource
	private GradeService gradeService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private UserService userService;
	
	private Grade grade;

	//struts将下列对象转换成json
	public List<Grade> rows;
	public int page;
	public int total;

	
	//跳转到成绩页面
	@Action("gradeListPage")
	public String listPage(){
		List<Category> categories = categoryService.getCategoryList();
		List<User> users = userService.getUsers();//获取所有普通用户
		getRequest().put("categories", categories);
		getRequest().put("users", users);
		return "grade_list";
	}
	
	//获取成绩列表
	@Action("gradeList")
	public String list(){
		String sortname = getServletRequest().getParameter("sortname");	//排序字段
		String sortorder = getServletRequest().getParameter("sortorder");	//排序方式
		int rp = Integer.valueOf(getServletRequest().getParameter("rp"));	//每页显示数量
		page = Integer.valueOf(getServletRequest().getParameter("page"));	//当前页码
		total = gradeService.getGradeTotal();		//获取类目成绩总数
		rows = gradeService.getGradeList(sortname, sortorder, (page-1)*rp, rp);//分页查询类目成绩信息
		return "grade-categorylist";
	}
	
	//添加成绩
	@Action("gradeAdd")
	public void add() throws IOException{
		String category_id = getServletRequest().getParameter("category_id");
		String user_id = getServletRequest().getParameter("user_id");
		Category category = categoryService.getCategory(Integer.parseInt(category_id));
		User user = userService.getUser((Integer.parseInt(user_id)));
		grade.setCategory(category);
		grade.setUser(user);
		if (gradeService.add(grade)) {
			getServletResponse().getWriter().write("添加成功!!");
		}else {
			getServletResponse().getWriter().write("添加失败!!");
		}
	}
	
	//修改成绩
	@Action("gradeUpdate")
	public void update() throws IOException{
		String grade_id = getServletRequest().getParameter("grade_id");
		String category_id = getServletRequest().getParameter("category_id");
		String user_id = getServletRequest().getParameter("user_id");
		Category category = categoryService.getCategory(Integer.parseInt(category_id));
		User user = userService.getUser(Integer.parseInt(user_id));
		grade.setCategory(category);
		grade.setUser(user);
		grade.setId(Integer.parseInt(grade_id));
		if (gradeService.update(grade)) {
			getServletResponse().getWriter().write("修改成功");
		}else {
			getServletResponse().getWriter().write("修改失败");
		}
	}	
	
	//删除成绩
	@Action("gradeDelete")
	public void delete() throws IOException{
		String idstr = getServletRequest().getParameter("idstr");
		String[] ids = idstr.split(",");
		for (int i = 0; i < ids.length; i++) {
			Grade grade = new Grade();
			grade.setId(Integer.valueOf(ids[i]));
			if (!gradeService.delete(grade)){
				getServletResponse().getWriter().write("删除失败");
			}
		}getServletResponse().getWriter().write("删除成功");
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
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public List<Grade> getRows() {
		return rows;
	}
	public void setRows(List<Grade> rows) {
		this.rows = rows;
	}
	
}
