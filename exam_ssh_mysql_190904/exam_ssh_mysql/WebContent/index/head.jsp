<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/style.css" rel="stylesheet" type="text/css"/>
	<link href="css/front.css" rel="stylesheet" type="text/css"/>
</head>
<body>

	<!-- 页面头部 -->
	<div class="top">
		<div class="wap_top">
			<h1 class="logo">
				<a href="index.action" target="_parent"><img src="images/logo.png" width="142" height="50"/></a>
			</h1>
			<div class="logo_text">|&nbsp; 在线考试答题系统</div>
			<div class="fright">
			
				<div id="button_login" class="sign_in">
					<div id="topnav" class="topnav">
						<a id="login" href="" class="signin"><span>登录</span></a> 
						<a id="register" href="" class="signin"><span>注册</span></a> 
						<a id="admin" href="../admin/login.jsp" target="_blank"><span>后台管理</span></a> 
					</div>
				</div>
				
				<div id="button_user" class="sign_in">
					<a id="sign_user" href="home.action">${username }</a>
					<a href="home.action">个人中心</a>
					<a href="logout.action">退出登录</a>
				</div>
				
			</div>
			
			<fieldset id="signin_menu">
				<label id="label">&lt;用户登录&gt;</label><br/><br/>
				<form action="login.action" id="form" method="post">
					<label for="username">用户：</label>
					<input id="username" name="user.username" type="text"/>
					<label for="password">密码：</label>
					<input id="password" name="user.password" type="password"/>
				 	<img id="check_img" src="codeImage.action" width="100" height="30" onclick="refresh()" />
				 	<img id="check_ico" src="images/check_3.png" width="25" height="25" onclick="refresh()"/>
				 	<input id="check_code" name="check_code" type="text"/>	
					<input id="signin_submit"  type="submit" value="登录" />
				</form>
			</fieldset>
			
			<script type="text/javascript">
		        $(document).ready(function() {
					
		        	if($("#sign_user").text()==""){//若当前没有用户登录
		        		$("#button_user").hide();	//用户中心模块隐藏
		        	}else{
		        		$("#button_login").hide();	//登录模块隐藏
		        	}
		        	
		        	var want_type = "";	//区分注册/登录
		        	
	        		//点击登录按钮事件
					$("#login").click(function(e) {
						e.preventDefault(); //不执行与事件关联的默认动作
		                $("fieldset#signin_menu").toggle();	//切换元素的显示与隐藏状态
						$("#login").toggleClass("menu-open");	//改变class属性
						$("#username").val("");		//清空填写框
						$("#password").val("");
						$("#check_code").val("");
						$("#signin_submit").attr("value","登录");		//改变按钮文字
						$("#label").text("用户登录");	
						want_type = "login";
		            });
		        	
	        		//点击注册按钮事件
					$("#register").click(function(e) {
						e.preventDefault(); //不执行与事件关联的默认动作
		                $("fieldset#signin_menu").toggle();	//切换元素的显示与隐藏状态
						$("#register").toggleClass("menu-open");	//改变class属性
						$("#username").val("");		//清空填写框
						$("#password").val("");
						$("#check_code").val("");
						$("#signin_submit").attr("value","注册");		//改变按钮文字
						$("#label").text("用户注册");	
						want_type = "register";
		            });
	        		
	        		//
					
					//表单提交事件
					$("#signin_submit").click(function(e){
						e.preventDefault(); //不执行与事件关联的默认动作
						var username = $("#username").val();
						var password = $("#password").val();
						var checkcode = $("#check_code").val();
						
						if(username==""){	//用户名不呢为空
							alert("请正确输入用户名!!");
							$("#username").focus();
							return false;
						}
						if(password==""){		//密码不呢为空
							alert("请正确输入密码!!");
							$("#username").focus();
							return false;
						}
						if(checkcode==""){	//验证码不呢为空
							alert("请输入验证码!!");
							$("#check_code").focus();
							return false;
						}
						
						//设置全局 AJAX 默认选项 - 默认同步方式
						$.ajaxSetup({async:false});	
						
						//ajax方式检测验证码是否正确
						$.get("codeCheck.action?inputString="+checkcode,function(data){
							if(data=="success"){
								if(want_type=="login"){//如果是登录
									//ajax同步方式验证用户名和密码
									$.get("login.action?user.username="+username+"&user.password="+password,function(data){
										if(data=="fail"){
											alert("用户名或密码错误!!");
											$("#password").val("");
											$("#check_code").val("");
											refresh();	//刷新登录验证码
										}else{
											//	alert("登录成功!!");
											$("#login").trigger("click");	//模拟点击登录按钮
											$("#button_login").toggle();	//隐藏登录按钮
											$("#sign_user").text(data);	//显示用户名
											$("#button_user").toggle();	//显示用户按钮
											refresh();	//刷新登录验证码
										}
										return false;
									},"text");
								}
								if(want_type=="register"){//如果是注册
									//ajax同步方式注册用户
									$.get("register.action?user.username="+username+"&user.password="+password,function(data){
										if(data=="repeat"){
											alert("用户名已存在!!");
											$("#username").val("");
											$("#check_code").val("");
											refresh();	//刷新登录验证码
											return false;
										}
										if(data=="fail"){
											alert("注册失败!!");
											$("#password").val("");
											$("#check_code").val("");
											refresh();	//刷新登录验证码
											return false;
										}else{
											alert("注册成功!!");
											$("#register").trigger("click");	//模拟点击登录按钮
											refresh();	//刷新登录验证码
										}
										return false;
									},"text");
								}
								
							}else{
								alert("验证码输入错误!!");
								refresh();	//刷新登录验证码
								$("#check_code").val("");
								return false;
							}
						},"text");
						
					});
					
		        });
		        
		      	//刷新验证码
		        function refresh(){	
		        	$("#check_img").attr("src","codeImage.action?x="+Math.random());
		        }
        
		      	
	</script>
			

		</div>
	</div>
	
</body>
</html>