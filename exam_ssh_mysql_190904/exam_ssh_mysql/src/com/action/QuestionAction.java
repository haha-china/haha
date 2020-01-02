package com.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.Category;
import com.entity.Question;
import com.service.CategoryService;
import com.service.QuestionService;
import com.util.ExcelUtil;

/**
 * 考题Action
 * 负责获取考试题目内容
 * 及后台对考题的管理
 */
@Namespace("/admin")
@ParentPackage("json-default")	//指定Action所在包的父包, 必须这样设置才能处理json
@Results({
	@Result(name="questionList",location="/admin/question-list.jsp"),
	@Result(name="questionBatch",location="/admin/question-batch.jsp"),
	//配置返回json字符串 includeProperties属性为包含入json的属性
	@Result(type="json",name="questionlist",params={"includeProperties","page,total,rows.*"}),
})
@SuppressWarnings("serial")
public class QuestionAction extends BaseAction{

	@Resource
	private QuestionService questionService;
	@Resource
	private CategoryService categoryService;
	
	//struts将下列对象转换成json
	public List<Question> rows;
	public int page;
	public int total;
	
	private File file;				//获取上传文件
    private String fileFileName;	//获取上传文件名称
    private Question question;
    
    
    //考题列表页面
    @Action("questionListPage")
    public String questionListPage(){
    	List<Category> categoryList = categoryService.getCategoryList();
    	getRequest().put("categoryList", categoryList);
    	return "questionList";
    }
    
	//获取考题列表
    @Action("questionList")
	public String questionList(){
		String sortname = getServletRequest().getParameter("sortname");	//排序字段
		String sortorder = getServletRequest().getParameter("sortorder");	//排序方式
		int rp = Integer.valueOf(getServletRequest().getParameter("rp"));	//每页显示数量
		page = Integer.valueOf(getServletRequest().getParameter("page"));	//当前页码
		total = questionService.getQuestionTotal();		//获取考题总数
		rows = questionService.getQuestionList(sortname, sortorder, (page-1)*rp, rp);//分页查询考题信息
		return "questionlist";
	}
	
	//添加考题
    @Action("questionAdd")
	public void add() throws IOException{
		String category_id = getServletRequest().getParameter("category_id");
		Category category = categoryService.getCategory(Integer.parseInt(category_id));
		question.setCategory(category);
		if (questionService.add(question)) {
			getServletResponse().getWriter().write("添加成功!!");
		}else {
			getServletResponse().getWriter().write("添加失败!!");
		}
	}

	//修改考题
    @Action("questionUpdate")
	public void update() throws IOException{
		String question_id = getServletRequest().getParameter("question_id");
		String category_id = getServletRequest().getParameter("category_id");
		Category category = categoryService.getCategory(Integer.parseInt(category_id));
		question.setId(Integer.parseInt(question_id));
		question.setCategory(category);
		if (questionService.update(question)) {
			getServletResponse().getWriter().write("修改成功");
		}else {
			getServletResponse().getWriter().write("修改失败");
		}
	}	
	
	//删除考题
    @Action("questionDelete")
	public void delete() throws IOException{
		String idstr = getServletRequest().getParameter("idstr");
		String[] ids = idstr.split(",");
		for (int i = 0; i < ids.length; i++) {
			Question question = new Question();
			question.setId(Integer.valueOf(ids[i]));
			if (!questionService.delete(question)){
				getServletResponse().getWriter().write("删除失败");
			}
		}getServletResponse().getWriter().write("删除成功");
	}
	
	
	// xml文件表头
	private static List<String> TitleList = new ArrayList<String>();
	static { // 初始化表头信息
		TitleList.add("题目");
		TitleList.add("选项1");
		TitleList.add("选项2");
		TitleList.add("选项3");
		TitleList.add("选项4");
		TitleList.add("答案");
		TitleList.add("分值");
		TitleList.add("解析");
	}
	
	
	
	//下载考题模版(动态生成)
	@Action("questionModel")
	public void getModel() throws IOException{
		List<Object> bookList = new ArrayList<Object>();//拼装生成文件用的信息
		List<Category> categoryList = categoryService.getCategoryList();
		for (Category category : categoryList) {
			List<Object> sheetList = new ArrayList<Object>();
			sheetList.add(category.getName());	//添加工作表名称 - 位置0
			sheetList.add(TitleList);	//添加工作表表头(和导入模版一致) - 位置1
			bookList.add(sheetList);
		}
		//设置response属性, 向页面传送文件输出流
		getServletResponse().setContentType("application/vnd.ms-excel"); 
		getServletResponse().addHeader("Content-Disposition","attachment;filename=\"model.xls\"");
		OutputStream os = getServletResponse().getOutputStream(); 
		ExcelUtil.writeExcel(os, bookList);//生成xls文件
	}


	//批量导入考题
	@Action("questionImport")
	@SuppressWarnings("unchecked")
	public String batchImport(){
		//判断是否上传了文件
		if (file == null) {
			getRequest().put("msg", "上传文件为空, 请先选择上传文件!");
			return "questionBatch";
		}
		//检查文档结构
		if(!ExcelUtil.checkExcel(file, TitleList)){
			getRequest().put("msg", "文件结构不正确, 请先下载导入模版!");
			return "questionBatch";
		}
		//解析文档内容
		List<List<Object>> bookList = ExcelUtil.readExcel(file);
		if (bookList==null) {//如果导入文件失败,返回页面并给予提示
			getRequest().put("msg", "导入失败, 请检查导入文件!");
			return "questionBatch";
		}
		// 保存文档内容
		for(List<Object> sheetList : bookList){
			if (sheetList!=null && sheetList.size()>1) {
				String sheetName = (String) sheetList.get(0);
				List<String> rowList = (List<String>) sheetList.get(1);
				Question question = new Question();
				question.setTitle(rowList.get(0));
				question.setOption1(rowList.get(1));
				question.setOption2(rowList.get(2));
				question.setOption3(rowList.get(3));
				question.setOption4(rowList.get(4));
				String result = rowList.get(5);
				question.setResult((result!=null &&!result.trim().isEmpty()) ? Integer.parseInt(result) : 0);
				String score = rowList.get(6);
				question.setScore((score!=null &&!score.trim().isEmpty()) ? Integer.parseInt(score) : 0);
				question.setAnaly(rowList.get(7));
				question.setCategory(categoryService.getCategoryByName(sheetName));
				questionService.add(question);//插入数据
			}
		}
		getRequest().put("msg", "导入成功!");
		return "questionBatch";
	}
	
	//批量导出考题
	@Action("questionExport")
	public void batchExport() throws IOException{
		List<Object> bookList = new ArrayList<Object>();//拼装生成文件用的信息
		List<Category> categoryList = categoryService.getCategoryList();
		for(Category category : categoryList){
			List<Object> sheetList = new ArrayList<Object>();
			sheetList.add(category.getName());
			sheetList.add(TitleList);
			List<Question> questionList = questionService.getAllCategoryQuestion(category.getId());
			for(Question question : questionList){
				List<String> rowList = new ArrayList<String>();
				rowList.add(question.getTitle());
				rowList.add(question.getOption1());
				rowList.add(question.getOption2());
				rowList.add(question.getOption3());
				rowList.add(question.getOption4());
				rowList.add(String.valueOf(question.getResult()));
				rowList.add(String.valueOf(question.getScore()));
				rowList.add(question.getAnaly());
				sheetList.add(rowList);
			}
			bookList.add(sheetList);
		}
		//设置response属性, 向页面传送文件输出流
		getServletResponse().setContentType("application/vnd.ms-excel"); 
		getServletResponse().addHeader("Content-Disposition","attachment;filename=\"export.xls\"");
		OutputStream os = getServletResponse().getOutputStream(); 
		ExcelUtil.writeExcel(os, bookList);//生成xls文件
	}
	
	
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
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
	public List<Question> getRows() {
		return rows;
	}
	public void setRows(List<Question> rows) {
		this.rows = rows;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

}
