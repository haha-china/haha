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
		
			<!-- 排行榜面板 -->
			<div class="czcon">
				<div class="cz01 clearfix" id="cz_1" style="display:block">
					<div style="width:825px; float:left;">
						<div class="lbc"  style="width:800px;">
							
							
							<!-- 左侧排行表格 -->
							<div class="ph_xbc" style="width:582px;float:left;">
								<div class="ph_lt">
								
									<h2 align="center">
										<font color="red">${msg}</font>
										<a href="intro.action?id=<s:property value="#request.category_id"/>">
											<s:property value="#request.grades[0].category.name"/>
										</a>
									</h2>
									
									<s:if test="#request.grades!=null && #request.grades.size()>0">
										
										<div class="ph_tit">
											<table>
												<tr>
													<td width="60" align="center"style="font-weight:bold">排名</td>
													<td width="160" align="left">&nbsp;</td>
													<td width="300" align="left" style="font-weight:bold">得分</td>
													<td width="60" align="left" style="">答题数</td>
												</tr>
											</table>
										</div>
										<div class="ph_nr">
											<table class="ph_lb">
											
												<s:iterator value="#request.grades" status="st">
													<tr>
														<td width="40" align="center">${page-1}<s:property value="#st.total"/></td>
														<td width="40" class="tx"><a href=""><img src="images/tx.jpg" /></a></td>
														<td width="150" class="mc"><a href=""><s:property value="user.username"/></a></td>
														<td width="320" align="left">
															<div class="Time2">
																<div class="seconds2"><s:property value="grade"/></div>
																<div class="Last2"></div>
															</div>
														</td>
														<td class="td_count" width="50" align="center"><s:property value="total"/></td>
													</tr>
												</s:iterator>
				   
											</table>
											
											<script type="text/javascript">//分数条
												var zongfen=800;//总分数
												$(".ph_lb tr td .Time2").each(function(index, element) {
													var dqfs=$(this).find(".seconds2").html();//实际得分
													var xscd=dqfs*234/zongfen;		//计算进度条宽度
													$(this).find(".Last2").animate({width:xscd},1000);//执行css属性集的自定义动画
												});
											</script>
											
											<div class="pages">
												<span>当前页码：<font color=red>${page}</font> / ${pages} 页</span>
												<a href="rank.action?category_id=<s:property value="#request.category_id"/>">首页 </a>
												<a href="rank.action?page=${page-1}&category_id=<s:property value="#request.category_id"/>">上一页 </a>
												<a href="rank.action?page=${page+1}&category_id=<s:property value="#request.category_id"/>">下一页 </a>
												<a href="rank.action?page=${pages}&category_id=<s:property value="#request.category_id"/>">尾页&nbsp;</a>
											</div>
											
										</div>
					
									</s:if>
								
								</div>
							</div>
							
							
							<!-- 右侧排序介绍 -->
							<div class="cse_rtxx paihang">
								<div class="zwms">
									<h3>排名规则</h3>
									<h4>该技能得分较高者排在前面，若分数相同，则题目数少的用户排在前面，因为其正确率较高</h4>
								</div>
								<div style="clear:both"></div>
								<div class="ph_jsdj">
									<h3>技术等级</h3>
									<h4>技能榜单以得分和答题数进行排名，分数对应技能等级如下</h4>
									
									<script type="text/javascript">
									function toggleP(id){	//技能等级打开关闭特效
										$('#d_'+id).toggleClass("div_selected");
										$("#p_"+id).slideToggle ("fast", function(){
											for (var i=1; i<=5; i++){
												if(i!=id) $("#p_"+i).slideUp("slow", function(){
													$(this).parent().removeClass('div_selected');
												});
											}
										});
									};
									</script>
									
									<div class="level_div" id="d_1">
										<div class="level_h3" onclick="javascript:toggleP(1)" >
											<p>BEGINNER</p><span>0-299</span>
										</div>
										<div id="p_1" class="default">你正在开始实践这项技能                            </div>
									</div>
									<div class="level_div" id="d_2">
										<div class="level_h3" onclick="javascript:toggleP(2)" >
											<p>FAMILIAR</p><span>300-449</span>
										</div>
										<div id="p_2" class="default">你有基本的技能知识，但也有足够的进步空间                            </div>
									</div>
									<div class="level_div" id="d_3">
										<div class="level_h3" onclick="javascript:toggleP(3)" >
											<p>PROFICIENT</p><span>450-699</span>
										</div>
										<div id="p_3" class="default">你能用常规方式熟练使用此项技能                            </div>
									</div>
									<div class="level_div" id="d_4">
										<div class="level_h3" onclick="javascript:toggleP(4)" >
										<p>EXPERT</p><span>700-779</span>
										</div>
										<div id="p_4" class="default">你是技术领跑者，熟稔这项技能及其最新发展                            </div>
									</div>
									<div class="level_div" id="d_5">
										<div class="level_h3" onclick="javascript:toggleP(5)" >
											<p>MASTER</p><span>780-800</span>
										</div>
										<div id="p_5" class="default">你是技术高手，对这项技能运用得游刃有余                            </div>
									</div>
								
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