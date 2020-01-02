<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link type="text/css" href="css/admin.css" rel="stylesheet">
	<link type="text/css" href="flexigrid/css/flexigrid_blue.css" rel="stylesheet"/>
	<link type="text/css" href="jqmodal/css/jqModal_blue.css" rel="stylesheet"/>  
	<script type="text/javascript" src="flexigrid/jquery.js"></script>  
	<script type="text/javascript" src="flexigrid/flexigrid.js"></script>
	<script type="text/javascript" src="jqmodal/jqModal.js"></script> 
	<script type="text/javascript" src="jqmodal/jqDnR.js"></script> 
	<script type="text/javascript" src="js/question-list.js"></script> 

</head>
<body>
	<table cellspacing=0 cellpadding=0 width="100%" align=center border=0>
		<tr height=28><td background="images/title_bg1.jpg">当前位置 &raquo; 考题管理</td></tr>
		<tr><td bgcolor=#b1ceef height=1></td></tr>
		<tr height=20><td background="images/shadow_bg.jpg"></td></tr>
	</table>


	<!-- table由category-list.js控制 -->
	<table id="flex" ></table>

	<div id="usermsg" class="jqmWindow" style="width: 400px;">
		<div class="drag">考题信息<div class="close"></div></div><br>
		<form id="form" method="post">
			<input type="hidden" name="question_id">
			<table align="center">
				<tr><td>类目:</td><td>
					<select name="category_id" style="width: 234px;">
						<s:iterator value="#request.categoryList">
							<option value="<s:property value="id"/>"><s:property value="name"/></option>
						</s:iterator>
					</select></td></tr>
				<tr><td>题目:</td><td><input type="text" name="question.title"  style="width: 230px;"></td></tr>
				<tr><td>选项1:</td><td><input type="text" name="question.option1"  style="width: 230px;"></td></tr>
				<tr><td>选项2:</td><td><input type="text" name="question.option2"  style="width: 230px;"></td></tr>
				<tr><td>选项3:</td><td><input type="text" name="question.option3"  style="width: 230px;"></td></tr>
				<tr><td>选项4:</td><td><input type="text" name="question.option4"  style="width: 230px;"></td></tr>
				<tr><td>答案:</td><td><input type="text" name="question.result"  style="width: 230px;" placeholder="输入数字类型"></td></tr>
				<tr><td>分值:</td><td><input type="text" name="question.score"  style="width: 230px;" placeholder="输入数字类型"></td></tr>
				<tr><td>解析:</td><td><input type="text" name="question.analy"  style="width: 230px;"></td></tr>
			</table>
			<div align="center">
				<input type="button" id="submit" class="input-button" value="提交" />
				<input type="reset" class="input-button" value="重置" />
			</div>
		</form>
	</div>

</body>
</html>