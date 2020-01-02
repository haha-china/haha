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
	<script type="text/javascript" src="js/grade-list.js"></script> 

</head>
<body>
	<table cellspacing=0 cellpadding=0 width="100%" align=center border=0>
		<tr height=28><td background="images/title_bg1.jpg">当前位置 &raquo; 成绩管理</td></tr>
		<tr><td bgcolor=#b1ceef height=1></td></tr>
		<tr height=20><td background="images/shadow_bg.jpg"></td></tr>
	</table>


	<!-- table由category-list.js控制 -->
	<table id="flex" ></table>

	<div id="usermsg" class="jqmWindow" style="width: 400px;">
		<div class="drag">类目成绩<div class="close"></div></div><br>
		<form id="form" method="post">
			<input type="hidden" name="grade_id">
			<table align="center">				
				<tr><td>类目:</td><td>
					<select name="category_id" style="width: 204px;">
						<s:iterator value="#request.categories">
							<option value="<s:property value="id"/>"><s:property value="name"/></option>
						</s:iterator>
					</select></td></tr>
				<tr><td>用户:</td><td>
					<select name="user_id" style="width: 204px;">
						<s:iterator value="#request.users">
							<option value="<s:property value="id"/>"><s:property value="username"/></option>
						</s:iterator>
					</select></td></tr>
				<tr><td>成绩:</td><td><input type="text" name="grade.grade"  style="width: 200px;"/>
				<tr><td>答题:</td><td><input type="text" name="grade.total"  style="width: 200px;"/>
			</table>
			<div align="center">
				<input type="button" id="submit" class="input-button" value="提交" />
				<input type="reset" class="input-button" value="重置" />
			</div>
		</form>
	</div>

</body>
</html>