<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<title>软考在线</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="js/jquery.js" type="text/javascript"></script>
</head>
<body>
	
	<!-- 引入页面头部 -->
	<jsp:include page="head.jsp"/>

	<!-- 网页中间部分 -->
	<div class="main">

		<div class="main_wrap">
		
		
			<div class="left_wrap">
				<h2 class="skillcate">高级资格</h2>
				<dl class="skillbox box1" >
					<s:iterator value="#request.categories1">
						<dd><a href="intro.action?id=<s:property value="id"/>">
							<s:property value="name"/></a></dd>
					</s:iterator>
				</dl>

				<h2 class="skillcate">中级资格</h2>
				<dl class="skillbox box2">
					<s:iterator value="#request.categories2">
						<dd><a href="intro.action?id=<s:property value="id"/>">
							<s:property value="name"/></a></dd>
					</s:iterator>
				</dl>

				<h2 class="skillcate">初级资格</h2>
				<dl class="skillbox box3">
					<s:iterator value="#request.categories3">
						<dd><a href="intro.action?id=<s:property value="id"/>">
							<s:property value="name"/></a></dd>
					</s:iterator>
				</dl>
				
			</div>
	   
		</div>
    </div>
	
	<!-- 页面尾部 -->
	<div class="bottom"></div>

</body>
</html>