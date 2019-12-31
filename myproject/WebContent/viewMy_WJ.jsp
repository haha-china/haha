<%@page import="com.sun.org.apache.bcel.internal.generic.NEW"%>
<%@page import="com.niit.biz.IWenjuanBiz"%>
<%@page import="com.niit.dao.IWenjuan"%>
<%@page import="javax.annotation.Resource"%>
<%@page import="org.springframework.stereotype.Repository"%>
<%@page import="com.niit.biz.WenjuanBiz"%>
<%@page import="com.niit.entity.Wenjuan"%>
<%@page import="com.niit.dao.WenjuanDao"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<base href="<%=basePath%>"> 
<html>
<head>
    <title>所有问卷</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    

</head>
<body>
<% 
    request.setCharacterEncoding("UTF-8");

    //response.setHeader("refresh", "2");
%>
<jsp:useBean id="wenjuans" class="com.niit.entity.Wenjuan"></jsp:useBean>
<jsp:setProperty property="*" name="wenjuans"/>

<form action="displayAction.action" method="post">
    <table class="table" style="background-color: #333333">
        <tr><td></td></tr>
        <tr class="container-fluid" align="center">
            <td class="col-xs-4"><a href="home.jsp"><h4 style="color: deepskyblue">首页</h4></a></td>
            <td class="col-xs-4"><a href="view_WJ.jsp"><h4 style="color: deepskyblue">所有问卷</h4></a></td>
            <td class="col-xs-4"><a href="addWJ.jsp"><h4 style="color: deepskyblue">创建问卷</h4></a></td>
        </tr>
    </table>
    <table align="center" width="100%"
           class="table table-striped table-hover" style="margin-top: -19px;">
        <tr>
            <td align="center" colspan="4"><h3>我的问卷</h3></td>
        </tr>
        <tr align="center">
            <td><b>问卷名称</b></td>
            <td><b>问卷类型</b></td>
            <td><b>截止时间</b></td>
            <td><b>问卷操作</b></td>
        </tr>

       
     <c:forEach var="w" items="${sessionScope.mywenjuan}">
        <tr align="center">
            <td>${w.w_title}
            </td>
            <td>${w.w_type} 
            </td>
            <td>${w.w_endtime} 
            </td>
            <td><a href="${pageContext.request.contextPath}/question/aaa?u_id=<%=session.getAttribute("id")%>">查看结果</a></td>
    	</tr>
	</c:forEach>

    </table>
</form>
</body>
</html>
