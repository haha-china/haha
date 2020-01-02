package com.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.User;
import com.service.UserService;

/**
 * 用户Action
 * 负责注册登录的响应
 * 及响应后台用户管理
 */
@Namespace("/admin")
@ParentPackage("json-default")	//指定Action所在包的父包, 必须这样设置才能处理json
@Results({
		//配置返回json字符串 includeProperties属性为包含入json的属性
	@Result(type="json",name="userlist",params={"includeProperties","page,total,rows.*"})
})
@SuppressWarnings("serial")
public class UserAction extends BaseAction {

	@Resource
	private UserService userService;

	private User user;
	
	//struts将下列对象转换成json
	public List<User> rows;
	public int page;
	public int total;
	
	
	//获取用户列表
	@Action("userList")
	public String userList(){
		String sortname = getServletRequest().getParameter("sortname");	//排序字段
		String sortorder = getServletRequest().getParameter("sortorder");	//排序方式
		int rp = Integer.valueOf(getServletRequest().getParameter("rp"));	//每页显示数量
		page = Integer.valueOf(getServletRequest().getParameter("page"));	//当前页码
		total = userService.getUserTotal();		//获取用户总数
		rows = userService.getUserList(sortname, sortorder, (page-1)*rp, rp);
		return "userlist";
	}
	
	//添加用户
	@Action("userAdd")
	public void addUser() throws IOException{
		if (userService.isUser(user)) {
			getServletResponse().getWriter().write("用户名已存在!!");
		}else {
			if (userService.addUser(user)) {
				getServletResponse().getWriter().write("用户添加成功!!");
			}else {
				getServletResponse().getWriter().write("用户添加失败!!");
			}
		}
	}
	
	//删除用户
	@Action("userDelete")
	public void deleteUser() throws IOException{
		String idstr = getServletRequest().getParameter("idstr");
		String[] ids = idstr.split(",");
		for (int i = 0; i < ids.length; i++) {
			User user = new User();
			user.setId(Integer.parseInt(ids[i]));
			if (!userService.deleteUser(user)){
				getServletResponse().getWriter().write("删除失败!!");
			}
		}getServletResponse().getWriter().write("删除成功!!");
	}
	
	//修改用户信息
	@Action("userUpdate")
	public void updateUser() throws IOException{
		if (userService.updateUser(user)) {
			getServletResponse().getWriter().write("修改成功!!");
		}else {
			getServletResponse().getWriter().write("修改失败!!");
		}
	}
	
	//用户修改密码
	@Action("userChange")
	public void change() throws IOException{
		String password = getServletRequest().getParameter("password");
		String newword = getServletRequest().getParameter("newword");
		String username = getSession().get("username").toString();
		user.setUsername(username);
		user.setPassword(password);
		if (userService.isValidUser(user)) {
			user.setPassword(newword);
			if (userService.updateUser(user)) {
				getServletResponse().getWriter().write("修改成功!!");
			}else {
				getServletResponse().getWriter().write("修改失败!!");
			}
		}else {
			getServletResponse().getWriter().write("原密码错误!!");
		}
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getRows() {
		return rows;
	}
	public void setRows(List<User> rows) {
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
	

	
}
