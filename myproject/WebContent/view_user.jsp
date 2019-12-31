<%@page import="com.niit.entity.User"%>
<%@page import="com.niit.dao.UserDao"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.sun.org.apache.bcel.internal.generic.NEW"%>
<%@page import="com.niit.biz.IUserBiz"%>
<%@page import="com.niit.dao.IUserDao"%>
<%@page import="javax.annotation.Resource"%>
<%@page import="org.springframework.stereotype.Repository"%>
<%@page import="com.niit.biz.UserBiz"%>
<%@page import="com.niit.controller.UserController"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" 
           uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 <%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!-- 拼装当前网页的相对路径 -->
<base href="<%=basePath%>"> 
<!-- 表明当前页面的相对路径所使用的根路径 -->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>首页</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">


</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");

    //response.setHeader("refresh", "2");
%>
<jsp:useBean id="users" class="com.niit.entity.User"></jsp:useBean>
<jsp:setProperty property="*" name="users"/>

<form action="${pageContext.request.contextPath}/user/getu" method="post">
<table align="center" width="100%" class="table table-striped table-hover">
    <tr>
        <td align="center" colspan="4"><h2>用户所有信息</h2><input type="submit" value="一键获取所有用户信息" /></td>
    </tr>
    <tr align="center">
        <td><b>ID</b></td>
        <td><b>用户名称</b></td>
        <td><b>用户密码</b></td>
        <td><b>删除信息</b></td>
    </tr>

<c:forEach var="u" items="${sessionScope.user}">
    <tr align="center">
        <td>${u.u_id}
        </td>
        <td>${u.username}
        </td>
        <td>${u.password}
        </td>
        <td><a href="delete.jsp?u_id=${u.u_id}">删除</a></td>
        <%--<td><a href="javascript:del();">删除</a></td>--%>
    </tr>

</c:forEach>
    </table>
    </form>


<script language=javascript>

    function del() {

        if (confirm("确定删除？")) {
        <%--window.location.href="DeleteServlet?id=<%=user.getU_id()%>";--%>
        }
    }
</script>

</body>
</html>