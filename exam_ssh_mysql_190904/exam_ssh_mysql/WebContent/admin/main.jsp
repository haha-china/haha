<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*,java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv=content-type content="text/html; charset=UTF-8">
	<link href="css/admin.css" type="text/css" rel="stylesheet">
</head>
<body>
	<table cellspacing=0 cellpadding=0 width="100%" align=center border=0>
		<tr height=28><td background="images/title_bg1.jpg">当前位置 &raquo; 后台首页</td></tr>
		<tr><td bgcolor=#b1ceef height=1></td></tr>
		<tr height=20><td background="images/shadow_bg.jpg"></td></tr>
	</table><br/>
	<table cellspacing=0 cellpadding=0 width="90%" align=center border=0>
		<tr height=100>
			<td align="center" width=100><img height=100 src="images/admin_b.jpg" width=90></td>
			<td width=60>&nbsp;</td>
			<td>
				<table height=100 cellspacing=0 cellpadding=0 width="100%" border=0>
					
					<tr><td style="font-weight: bold; font-size: 16px">欢迎进入网站管理中心</td></tr>
					<tr><td style="font-weight: bold; font-size: 16px">当前时间：<%=new SimpleDateFormat().format(new Date())%></td></tr>
					
				</table>
			</td>
		</tr>
		<tr><td colspan=3 height=10></td></tr>
	</table>

</body>
</html>