package com.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.Admin;
import com.service.AdminService;


/**
 * 后台管理Action
 * 负责注册登录的响应
 * 及响应后台用户管理
 */
@Namespace("/admin")
@ParentPackage("json-default")	//指定Action所在包的父包, 必须这样设置才能处理json
@Results({
	@Result(name="index",location="/admin/index.jsp"),
	@Result(name="input",location="/admin/login.jsp"),
		//配置返回json字符串 includeProperties属性为包含入json的属性
	@Result(type="json",name="list",params={"includeProperties","page,total,rows.*"})
})
@SuppressWarnings("serial")
public class AdminAction extends BaseAction {
	
	@Resource
	private AdminService adminService;
	private Admin admin;

	//struts将下列对象转换成json
	public List<Admin> rows;
	public int page;
	public int total;
	
	
	//管理员登录
	@Action("adminLogin")
	public String login() throws IOException {
		if(adminService.isValidAdmin(admin)){	//判断用户名和密码是否正确
			getSession().put("adminname", admin.getUsername());	//将用户名写入session 作为登录标识
			return "index";
		}else{
			getSession().put("msg", "用户名或密码错误!!");
			return "input";
		}
	}

	//获取用户列表
	@Action("adminList")
	public String list(){
		String sortname = getServletRequest().getParameter("sortname");	//排序字段
		String sortorder = getServletRequest().getParameter("sortorder");	//排序方式
		int rp = Integer.valueOf(getServletRequest().getParameter("rp"));	//每页显示数量
		page = Integer.valueOf(getServletRequest().getParameter("page"));	//当前页码
		total = adminService.getAdminTotal();		//获取用户总数
		rows = adminService.getAdminList(sortname, sortorder, (page-1)*rp, rp);
		return "list";
	}
	
	//添加用户
	@Action("adminAdd")
	public void add() throws IOException{
		if (adminService.isAdmin(admin)) {
			getServletResponse().getWriter().write("用户名已存在!!");
		}else {
			if (adminService.add(admin)) {
				getServletResponse().getWriter().write("用户添加成功!!");
			}else {
				getServletResponse().getWriter().write("用户添加失败!!");
			}
		}
	}
	
	//删除用户
	@Action("adminDelete")
	public void delete() throws IOException{
		String idstr = getServletRequest().getParameter("idstr");
		String[] ids = idstr.split(",");
		for (int i = 0; i < ids.length; i++) {
			Admin admin = new Admin();
			admin.setId(Integer.parseInt(ids[i]));
			if (!adminService.delete(admin)){
				getServletResponse().getWriter().write("删除失败!!");
			}
		}getServletResponse().getWriter().write("删除成功!!");
	}
	
	//修改用户信息
	@Action("adminUpdate")
	public void update() throws IOException{
		if (adminService.update(admin)) {
			getServletResponse().getWriter().write("修改成功!!");
		}else {
			getServletResponse().getWriter().write("修改失败!!");
		}
	}
	

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Admin> getRows() {
		return rows;
	}

	public void setRows(List<Admin> rows) {
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
