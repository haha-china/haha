package com.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.Category;
import com.entity.Grade;
import com.entity.Question;
import com.entity.User;
import com.service.CategoryService;
import com.service.GradeService;
import com.service.QuestionService;
import com.service.UserService;

/**
 * 前台Action
 * 负责响应对前台页面的请求
 * 及放置页面显示需要的数据
 */
@Namespace("/index")
@ParentPackage("json-default")	//指定Action所在包的父包, 必须这样设置才能处理json
@Results({
	@Result(name="index",location="/index/index.jsp"),
	@Result(name="intro",location="/index/intro.jsp"),
	@Result(name="home",location="/index/home.jsp"),
	@Result(name="answer",location="/index/answer.jsp"),
	@Result(name="rank",location="/index/rank.jsp"),
	@Result(name="error",location="/index/error.jsp"),
	@Result(type="redirect",name="reindex",location="index.action"),
	//配置返回json字符串 includeProperties属性为包含入json的属性
	@Result(type="json",name="checkresult",params={"includeProperties","question.*,grade.*"}),
	@Result(type="json",name="checkresults",params={"includeProperties","question.*,grades.*"})
})
@SuppressWarnings("serial")
public class IndexAction extends BaseAction{

	@Resource
	private CategoryService categoryService;
	@Resource
	private QuestionService questionService;
	@Resource
	private GradeService gradeService;
	@Resource
	private UserService userService;
	
	private User user;
	
	//struts将下列对象转换成json
	private Question question;
	private Grade grade;
	
	
	//响应主页请求
	@Action("index")
	public String index(){
		//考试类目信息并放入request范围
		List<Category> categories1 = categoryService.getCategoryByLevel(1);
		List<Category> categories2 = categoryService.getCategoryByLevel(2);
		List<Category> categories3 = categoryService.getCategoryByLevel(3);
		getRequest().put("categories1", categories1);
		getRequest().put("categories2", categories2);
		getRequest().put("categories3", categories3);	
		return "index";
	}
	
	//响应简介页面请求
	@Action("intro")
	public String intro(){
		int id = Integer.valueOf(getServletRequest().getParameter("id"));	//获取客户端传来参数
		Category category = categoryService.getCategory(id);		//根据id查询类目信息
		getRequest().put("category", category);
		return "intro";
	}
	
	//响应个人中心页面请求
	@Action("home")
	public String home(){
		//获取用户信息
		String username = getSession().get("username").toString();
		User user = userService.getUser(username);
		getRequest().put("user", user);
		//获取该用户所有类目成绩
		List<Grade> gradeCategories = gradeService.getGradeCategories(user.getId());
		List<Grade> beginner = new ArrayList<Grade>();
		List<Grade> familiar = new ArrayList<Grade>();
		List<Grade> proficient = new ArrayList<Grade>();
		List<Grade> expert = new ArrayList<Grade>();
		List<Grade> master = new ArrayList<Grade>();
		for (int i = 0; i < gradeCategories.size(); i++) {
			if (gradeCategories.get(i).getGrade()<300) {
				beginner.add(gradeCategories.get(i));
			}else if (gradeCategories.get(i).getGrade()>=300 && gradeCategories.get(i).getGrade()<450) {
				familiar.add(gradeCategories.get(i));
			}else if (gradeCategories.get(i).getGrade()>=450 && gradeCategories.get(i).getGrade()<700) {
				proficient.add(gradeCategories.get(i));
			}else if (gradeCategories.get(i).getGrade()>=700 && gradeCategories.get(i).getGrade()<780) {
				expert.add(gradeCategories.get(i));
			}else {
				master.add(gradeCategories.get(i));
			}
		}
		getRequest().put("beginner", beginner);
		getRequest().put("familiar", familiar);
		getRequest().put("proficient", proficient);
		getRequest().put("expert", expert);
		getRequest().put("master", master);
		return "home";
	}
	
	//响应答题页面请求
	@Action("answer")
	public String answer(){
		if (getSession().get("username")==null) {
			int category_id = Integer.valueOf(getServletRequest().getParameter("category_id"));//获取客户端传来参数
			getRequest().put("msg", "登录后才可以答题!!");
			Category category = categoryService.getCategory(category_id);		//根据id查询类目信息
			getRequest().put("category", category);
			return "intro";
		}else {
			int category_id = Integer.valueOf(getServletRequest().getParameter("category_id"));
			getSession().put("category_id", category_id);	//将类目信息存入session 方便其他页面读取
			//获取考题题目等信息
			Question question = questionService.getRandomQuestionIntro(category_id); 
			if (question==null) {//如果该类目下没有考题
				getRequest().put("msg", "该科目暂时还没有考题，请尝试其他科目!!");
				Category category = categoryService.getCategory(category_id);		//根据id查询类目信息
				getRequest().put("category", category);
				return "intro";
			}else {
				Category category = categoryService.getCategory(category_id);		//根据id查询类目信息
				getRequest().put("category", category);
				String username = getSession().get("username").toString();
				//获取该用户在该目录下的成绩信息
				Grade grade = gradeService.getGrade(category_id, username);	//获取成绩信息
				//获取当前类目下该用户成绩排名
				double per = (double)gradeService.getLowGradeNumber(grade.getGrade(),category_id)/gradeService.getAllGradeNumber(category_id);
				String percent = (int)(per*100)+"%";
				int seconds = question.getScore()*10+10;//根据题目分值计算答题时间
				getRequest().put("question", question);
				getRequest().put("grade", grade);
				getRequest().put("seconds", seconds);
				getRequest().put("percent", percent);
				return "answer";
			}
			
		}
	}
	
	//接收答案
	@Action("receive")
	public String receive(){
		int question_id = Integer.valueOf(getServletRequest().getParameter("question_id"));
		int answer = Integer.valueOf(getServletRequest().getParameter("answer"));
		question = questionService.getQuestionResultById(question_id);
		int category_id = (Integer)getSession().get("category_id");
		String username = getSession().get("username").toString();
		grade = gradeService.getGrade(category_id, username);	//获取成绩信息
		if(answer==question.getResult()){	//如果答案正确
			grade.setGrade(grade.getGrade()+question.getScore());	//积分=原积分+本题分值
			grade.setTotal(grade.getTotal()+1);	//答题数+1
			gradeService.update(grade);	//写入数据库
		}else {
			grade.setGrade(grade.getGrade()-question.getScore());	//积分=原积分-本题分值
			if (grade.getGrade()<0) {	//如果成绩小于0 则至为0
				grade.setGrade(0);
			}
			grade.setTotal(grade.getTotal()+1);	//答题数+1
			gradeService.update(grade);	//写入数据库		
		}
		userService.updateTotal(username, 1); // 用户总积分+1
		return "checkresult";
	}
	
	//响应榜单页面请求
	@Action("rank")
	public String rank(){
		int category_id = Integer.valueOf(getServletRequest().getParameter("category_id"));//获取客户端传来参数
		//分页参数
		int page = 1; //当前页码, 默认为第一页
		int rows = 8;	//每页显示数量, 默认为8
		int total = gradeService.getAllGradeNumber(category_id);//该类目下成绩总数
		int pages =  total/rows+1;	//总页数
		String pagestr = getServletRequest().getParameter("page");//获取客户端传来页码
		if (pagestr!=null) {	//如果客户端为传page参数则默认显示第一页 否则按客户的要求显示
			page = Integer.parseInt(pagestr);
			if(page<1){	//如果客户端传来页码小于1 这显示第一页
				page = 1;}
			if (page>pages) {	//如果客户端传来页码大于总页数 这显示最后一夜
				page = pages;}
		}//获取某类目下所有用户成绩(默认按成绩降序排列)
		List<Grade> grades = gradeService.getPartGradeByCategory(category_id,(page-1)*rows,rows);
		if (grades.size()==0) {
			getRequest().put("msg", "该榜单暂时没有成绩记录!!");
		}
		getRequest().put("category_id", category_id);//返回成绩信息
		getRequest().put("grades", grades);//返回成绩信息
		getRequest().put("pages", pages);//返回总页数
		getRequest().put("page", page);//返回当前页数
		return "rank";
	}
	
	//用户登录
	@Action("login")
	public void login() throws IOException {
		if(userService.isValidUser(user)){	//判断用户名和密码是否正确
			getSession().put("username", user.getUsername());	//将用户名写入session 作为登录标识
			getServletResponse().getWriter().write(user.getUsername());
		}else{
			getServletResponse().getWriter().write("fail");
		}
	}
	
	//用户注册
	@Action("register")
	public void register() throws IOException{
		if (userService.isUser(user)) {		//判断用户名是否已经存在
			getServletResponse().getWriter().write("repeat");
		}else {
			if (userService.addUser(user)) {	//注册用户
				getServletResponse().getWriter().write("success");
			}else {
				getServletResponse().getWriter().write("fail");
			}
		}
	}
	
	//退出登录
	@Action("logout")
	public String logout(){
		getSession().remove("username");
		return "reindex";
	}
	
	

	//get方法用于struts2封装json对象
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}