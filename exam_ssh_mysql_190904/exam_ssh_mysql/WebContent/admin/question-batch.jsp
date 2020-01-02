<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<table cellspacing=0 cellpadding=0 width="100%" align=center border=0>
		<tr height=28><td background="images/title_bg1.jpg">当前位置 &raquo; 考题导入导出</td></tr>
		<tr><td bgcolor=#b1ceef height=1></td></tr>
		<tr height=20><td background="images/shadow_bg.jpg"></td></tr>
	</table>
	
	<font color="red">* 导入文件只支持模版格式文件, 请点击	
	<a href="questionModel.action">下载模版</a></font>
	
	<form action="questionImport.action" method="post" enctype="multipart/form-data"><br>
		选择文件：<input type="file" name="file"/>
		<input type="submit" value="导入考题"/>
		<font color="red">${msg}</font><br><br>
	</form>
	

	导出考题：<a href="questionExport.action"><button>导出考题</button></a>
	
</body>
</html>