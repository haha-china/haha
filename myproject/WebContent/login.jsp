<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  
     用户登陆:
   </br>
    <form action="${pageContext.request.contextPath }/user/login" method="post">
	    </br>
	          用户名:<input name="name" type="text">
	    </br>
	           性别:<input name="gender"  type="text">
	     </br>
	      <input type="submit" value="登录">
   </form>
</body>
</html>
