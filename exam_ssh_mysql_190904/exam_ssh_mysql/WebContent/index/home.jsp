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
			
			<div class="home_wrap">
				<div class="uc_wrap" style="clear:both;">
					<div class="uc_left"> 
						<div class="ucb_top">
							<h2 class="uc_nm">
								<a href="home.action">${user.username}</a>&nbsp;
								<img id="dj" src="images/dj1.gif" />
							</h2>
                        </div>
						<br/>
						
						<div class="ucb_top"><h2 class="uc_grxx">积分</h2></div>
						<div class="ucb_grxx"><p>积分：${user.total}</p></div>
						
						<div class="ucb_top"><h2 class="uc_grxx">个人信息</h2></div>
						<div class="ucb_grxx"><p>邮箱：${user.email}</p></div>
						<div class="ucb_grxx"><p>介绍：${user.intro}</p></div>

					</div>

					
					<div class="uc_right" style="margin-right: 8px; width: 650px;"> 
						<div class="dyym_c">
							<ul class="uccz" id="cz_li_cur_">
								<li id="li1" class="cur"><a href="javascript:void(0);" onclick="mytab(1)">我的挑战</a></li>
                            </ul>

							<div class="cz01 clearfix" id="panel1" style="display: block;">
								<div class="uc_whddcs">
									<div class="ucwhh_top"><h2> 个人名片 </h2></div>
									<img id="333" width="50" height="50" src="images/tx.gif" />
								</div>
							</div>

						</div>
                    </div>
  
                </div>
            </div>

        </div>
    </div>
	

	<!-- 页面尾部 -->
	<div class="bottom"></div>

</body>
</html>