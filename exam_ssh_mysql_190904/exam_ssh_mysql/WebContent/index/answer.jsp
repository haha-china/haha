<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<title>软考在线</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="js/jquery.js" type="text/javascript"></script>
 	<script type="text/javascript">
		
		
			
			$(function(){
				
				
				
				//倒计时模块
				var zsj=$("span[id=show-time]").html();//获取倒计时初始秒数
				var yx=window.setInterval(function() {	//setInterval()方法可按照指定的周期（以毫秒计）来调用函数或计算表达式
					var timeCounter = $("span[id=show-time]").html();	//获取页面数值
					var updateTime = eval(timeCounter)- eval(1);	//对数值进行-1
					$("span[id=show-time]").html(updateTime);	//将新数值写回页面
					if(updateTime <10){	//若时间小于十秒则时间字体改为红色
						$("#seconds").css("color","#F00");
					}
					if(updateTime <=0){	//若时间结束
						sendAnswer(0,$("#question_id").val());	//发送答案
						hrBox();	//超时后 结束倒计时等操作..
					}}, 1000);
				
				
				//点击答题后
				var first_answer = true;		//是否第一次答题标记
				$('.answers').click(function(){	//选项被点击时执行
					var answer = $(this).val();	//获取选中项的值
					var question_id = $("#question_id").val();	//获取当然题目id
					if(first_answer == true){	//若是第一次回答则发送答案
						sendAnswer(answer,question_id);	//判断答案是否正确
					}else{alert("您已经回答过了！");}
					first_answer = false;	
					hrBox2();		//回答问题之后 结束倒计时等操作..
				});


				//回答问题之后 结束倒计时等操作..
				var hrBox2 = function(){
					clearInterval(yx);	//结束倒计时
					$("#djscon").hide();	//隐藏倒计时模块
					$("#jxdt_cn").fadeIn();	//显示分数结果模块
				};
				
				
				

			});

		

		//判定答案(超时或问题已经回答时执行ajax请求)
		function sendAnswer(answer,question_id) {
			$.post('receive.action', {answer:answer,question_id:question_id, rand:Math.random()}, function(data) {
				//根据分值显示题目难度
				switch(data.question.score){
					case 1:$('#t_level').text("E");break;
					case 2:$('#t_level').text("M");break;
					case 3:$('#t_level').text("H");break;
				}
				$('#s_scores').text(data.grade.grade);	//显示总分
				$('#analy').text(data.question.analy);	//设置解析内容
				$('#user_answered').fadeIn();	//显示解析
				//判断答案是否正确
				if (data.question.result == answer) {	
					$('#answer'+data.question.result).attr("class","rightaw");//改变选项class属性
					$('#t_scores').text("+"+data.question.score);	//显示本题得分+
				} else {	//改变该选项的class属性
					$('#answer'+answer).attr("class","wrongaw");
					$('#answer'+data.question.result).attr("class","rightaw");
					$('#t_scores').text("-"+data.question.score);	//显示本题得分-
				}
			},'json');
		}
		
		
		
	</script>
</head>
<body>

	<!-- 引入页面头部 -->
	<jsp:include page="head.jsp"/>
	
	
	<!-- 网页中间部分 -->
	<div class="main">

		<div class="main_wrap">

			<!-- 左边部分 -->
			<div class="cse_left">
				<div class="cse_tmlb" style="position:relative;">
					<div class="dt_top">
					
						<!-- 倒计时组件 -->
						<div class="dt_djs" id="djscon">
							<div id="Time">
								<div id="seconds"><span id="show-time">${seconds}</span>seconds</div>
								<div id="Last" style="width:100%;"></div>
							</div>
						</div>
						
						<!-- 分数结果 -->
						<div id="jxdt_cn" style="display:none;">
							<!-- 等级 -->
							<div class="dj" id="songc">
								<dl>
									<dt>题目难度</dt>
									<dd id="t_level"></dd>
								</dl>
								<ul id="ul">
									<li>E=简单</li>
									<li>M=中等</li>
									<li>H=困难</li>
								</ul>
							</div>
							<!-- 本题分数 -->
							<div class="dj" id="songc2">
								<dl>
									<dt>本题得分</dt>
									<dd id="t_scores"></dd>
								</dl>
								<div id="divc">回答正确加分，否则减分，加减幅度由题目的等级决定</div>
							</div>
							<!-- 当前总分 -->
							<div class="dj" id="songc3">
								<dl>
									<dt>当前总分</dt>
									<dd id="s_scores"></dd>
								</dl>
								<div id="divc2">技能测试分数，它代表你在该类目技能测试水平</div>
							</div>
							
							<!-- 鼠标滑动至按钮出现介绍 -->
							<script type="text/javascript">
								document.getElementById("songc").onmouseover=function () {
									document.getElementById("ul").style.display="block";
								}
								document.getElementById("songc").onmouseout=function () {
									document.getElementById("ul").style.display="none";
								}
								document.getElementById("songc2").onmouseover=function () {
									document.getElementById("divc").style.display="block";
								}
								document.getElementById("songc2").onmouseout=function () {
									document.getElementById("divc").style.display="none";
								}
								document.getElementById("songc3").onmouseover=function () {
									document.getElementById("divc2").style.display="block";
								}
								document.getElementById("songc3").onmouseout=function () {
									document.getElementById("divc2").style.display="none";
								}
							</script>
							
							<div class="jx">
								<a href="answer.action?category_id=${question.category.id}" class="dt_jxdtan">
										继续答题</a>
							</div>
						</div>
					</div>
					
					<!-- 题目及选项 -->
					<div class="tmdb_c">
						<input type="hidden" id="question_id" value="${question.id }"/>
						<div class="dtbt"><pre>${question.title}</pre></div>
						<div class="tmnr" style="clear:both;">
							<ul class="dt_tmlb" id="tm">
								<li class="answers" value="1" id="answer1">
									<pre> A、&nbsp;${question.option1} </pre>
								</li>
								<li class="answers" value="2" id="answer2">
									<pre> B、&nbsp;${question.option2} </pre>
								</li>
								<li class="answers" value="3" id="answer3">
									<pre> C、&nbsp;${question.option3} </pre>
								</li>
								<li class="answers" value="4" id="answer4">
									<pre> D、&nbsp;${question.option4} </pre>
								</li>
							</ul>
						</div>
					</div>
					
					<!-- 时间结束时显示 -->
					<div id="timeup" style="display:none;">
						<div class="wz" id="msg"></div>
						<div class="dbyy">
							<img src="images/times.png"/>
						</div>
					</div>
					
					<!-- 用户回答之后显示 -->
					<div id="user_answered" style="display:none;">
							<p>答案解析：</p>
							<div class="analy" id="analy"></div>
					</div>
					
				</div>
				
			</div>



			<!-- 右边部分 -->
			<div class="cse_rtxx" style="padding-top:0px;">
				<div class="dbt_h1">你已经超越了<span>${percent }</span>的作答用户！</div>
				<div class='cse_tmlb' style='margin-bottom: 18px; padding: 0; width: 338px; clear:all; background-color:transparent;border:none;box-shadow: none;'>
					<h2 id='steps_title'>晋级进度 ( <span id="span_int"></span> )</h2>
					<div id="steps_bg0">
						<input type="hidden" id="grade_input" value="${grade.grade }"/>
						<div id='steps_step' style="width:0px;"></div>
					</div>
					<div class='clearfix' style='height:12px;'></div>
				</div>
				<div class='dt_rtc clearfix' style='height:24px;'></div>
				
				<div class="dt_rtc clearfix" style="margin-top:-10px;">
					<div class="ltjj">
						<h2>
							<a href="intro.action?id=${question.category.id}" target="_blank">${category.name}</a>
						</h2>
					</div>
					
						
				</div>

				<div class="dt_plun clearfix" style="border-top:none;">

				</div>
			</div>
			
		</div>

    </div>

	
	
	<!-- 页面尾部 -->
	<div class="bottom"></div>
	
	
</body>
</html>