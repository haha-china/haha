<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<title>软考在线</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="js/jquery.js" type="text/javascript"></script>
	<script type="text/javascript">	
		function mytab(n) {
			for(var i=1; i<=4; i++) {
				$('#panel' + i).slideUp('fast');//以滑动方式隐藏元素
				$('#li' + i).removeClass('cur');
			}
			$('#panel' + n).slideDown('slow');//以滑动方式显示元素
			$('#li' + n).addClass('cur');
		}
		
		function showmsg(){
			if($('#hidden_msg').val()!=""){
				alert($('#hidden_msg').val());
			}
		}
		
		$(function(){	//鼠标划过超链时改变字体
			$(".paper_list a").mouseover(function(){
				$(this).css("color","red");
				$(this).css("font-weight","bold");
			})
			$(".paper_list a").mouseout(function(){
				$(this).css("color","blue");
				$(this).css("font-weight","normal");
			})
		});
	</script>
</head>
<body onload="showmsg()">
	
	<input type="hidden" id="hidden_msg" value="${msg}">
	
	<!-- 引入页面头部 -->
	<jsp:include page="head.jsp"/>
	
	
	<!-- 网页中间部分 -->
	<div class="main">
		<div class="main_wrap">
			<div class="dy_nrc">
				<h3 class="nav">
					<a href="index.action" >软考在线</a> » 
					<a href="intro.action?id=${category.id}">${category.name}</a>
				</h3>
				<div class="cse_nrwrap clearfix">
					<div class="dyym_c">
						<div class="lb_one testdetail_title">
							<div class="lf_ico">
								<img src="images/right_button.png" />
							</div>
							<div class="rt_jsc">
								<h3>${category.name}</h3>
								<p><font size="4" color="blue">技能介绍：</font>${category.intro}</p>
								<div class="anniuc">
									<a class="cs_ksbut" href="answer.action?category_id=${category.id}">开始挑战</a>
								<!--  	<a class="uc_xgtx" href="rank.action?category_id=${category.id}" target="_blank">查看排行榜</a> -->
								</div>
							</div>
						</div>
						<br><br>
						
					</div>
				</div>
			</div>
		</div>
    </div>

	
	<!-- 页面尾部 -->
	<div class="bottom"></div>
	
	
</body>
</html>