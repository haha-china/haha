<%@page import="com.niit.entity.Wenjuan"%>
<%@page import="com.niit.dao.WenjuanDao"%>
<%@page import="com.niit.biz.IWenjuanBiz"%>
<%@page import="com.niit.dao.IWenjuan"%>
<%@page import="com.niit.biz.WenjuanBiz"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.NEW"%>
<%@page import="java.util.ArrayList" %>
<%@page import="javax.annotation.Resource"%>
<%@page import="org.springframework.stereotype.Repository"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" 
           uri="http://java.sun.com/jsp/jstl/fmt" %>
           
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<base href="<%=basePath%>"> 
<html>
<head>
    <title>所有问卷</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">

</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");

    //response.setHeader("refresh", "2");
%>
<jsp:useBean id="wenjuans" class="com.niit.entity.Wenjuan"></jsp:useBean>
<jsp:setProperty property="*" name="wenjuans"/>

<form action="${pageContext.request.contextPath}/wenjuan/get_g" method="post">
    <table align="center" width="100%"
           class="table table-striped table-hover" style="margin-top: -19px;">
        <tr>
            <td align="center" colspan="7"><h3>所有问卷</h3><input type="submit" value="一键获取所有问卷信息" /></td>
        </tr>
        <tr align="center">
            <td><b>问卷ID</b></td>
            <td><b>问卷名称</b></td>
            <td><b>问卷类型</b></td>
            <td><b>截止时间</b></td>
            <td><b>问卷操作</b></td>
            <td><b>问卷操作</b></td>
            <td><b>问卷操作</b></td>
        </tr>
        
        <c:forEach var="w" items="${sessionScope.wenjuan}">
        <tr align="center">
            <td>${w.w_id}</td>
            <td>${w.w_title}
            </td>
            <td>${w.w_type}
            </td>
            <td>${w.w_endtime}
            </td>
            <td><a href="fx.jsp?id=${w.w_id}">查看结果</a></td>
            <td><a href="b.jsp?id=${w.w_id}">参与调查</a></td>
            <td><a href="w_delete.jsp?w_id=${w.w_id}">删除问卷</a></td>
	</c:forEach>	
    </table>
    </form>
</body>
</html>
