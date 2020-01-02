<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<script type="text/javascript" src="js/admin-list.js"></script> 

</head>
<body>
	<table cellspacing=0 cellpadding=0 width="100%" align=center border=0>
		<tr height=28><td background="images/title_bg1.jpg">当前位置 &raquo; 管理员</td></tr>
		<tr><td bgcolor=#b1ceef height=1></td></tr>
		<tr height=20><td background="images/shadow_bg.jpg"></td></tr>
	</table>


	<!-- table由user-list.js控制 -->
	<table id="flex" ></table>

	<div id="usermsg" class="jqmWindow" style="width: 300px;">
		<div class="drag">用户信息<div class="close"></div></div><br>
		<form id="form" method="post">
			<table align="center">
				<tr><td>用户:</td><td><input type="text" name="admin.username" ></td></tr>
				<tr><td>密码:</td><td><input type="text" name="admin.password"></td></tr>
			</table>
			<div align="center">
				<input type="button" id="submit" class="input-button" value="提交" />
				<input type="reset" class="input-button" value="重置" />
			</div>
		</form>
	</div>

</body>
</html>