<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<head>
	<meta http-equiv=content-type content="text/html; charset=gb2312">
	<link href="css/admin.css" type="text/css" rel="stylesheet">

	<!-- 开合左侧菜单栏 -->
	<script language=javascript>
		function expand(el){
			childObj = document.getElementById("child" + el);
			if (childObj.style.display == 'none')
			{childObj.style.display = 'block';}
			else
			{childObj.style.display = 'none';}
			return;
		}
	</script>
</head>
<body>
	<table height="100%" cellspacing=0 cellpadding=0 width=170 background="images/menu_bg.jpg" border=0>
		<tr>
		<td valign=top align="center">
			<!--菜单栏上部-->
			<table cellspacing=0 cellpadding=0 width="100%" border=0>
				<tr><td height=20></td></tr>
			</table>
			
			<!--菜单栏1-->
			<table cellspacing=0 cellpadding=0 width=150 border=0>
				<tr height=22>
					<td style="padding-left: 30px" background="images/menu_bt.jpg">
						<a class=menuparent onclick=expand(1) href="javascript:void(0);">用户管理</a>
					</td>
				</tr>
				<tr height=4><td></td></tr>
			</table>
				<!--菜单子项1-->
				<table id=child1 cellspacing=0 cellpadding=0 width=150 border=0>
					<tr height=20>
						<td align="center" width=30><img height=9 src="images/menu_icon.jpg" width=9></td>
						<td><a class=menuchild href="user-list.jsp" target=main>用户列表</a></td>
					</tr>
					<tr height=4><td colspan=2></td></tr>
				</table>
			
			<!--菜单栏2-->
			<table cellspacing=0 cellpadding=0 width=150 border=0>
				<tr height=22>
					<td style="padding-left: 30px" background="images/menu_bt.jpg">
						<a class=menuparent onclick=expand(2) href="javascript:void(0);">类目管理</a>
					</td>
				</tr>
				<tr height=4><td></td></tr>
			</table>
				<!--菜单子项2-->
				<table id=child2 cellspacing=0 cellpadding=0 width=150 border=0>
					<tr height=20>
						<td align="center" width=30><img height=9 src="images/menu_icon.jpg" width=9></td>
						<td><a class=menuchild href="category-list.jsp" target=main>类目列表</a></td>
					</tr>
					<tr height=4><td colspan=2></td></tr>
				</table>
				
			<!--菜单栏4-->
			<table cellspacing=0 cellpadding=0 width=150 border=0>
				<tr height=22>
					<td style="padding-left: 30px" background="images/menu_bt.jpg">
						<a class=menuparent onclick=expand(4) href="javascript:void(0);">考题管理</a>
					</td>
				</tr>
				<tr height=4><td></td></tr>
			</table>
				<!--菜单子项4-->
				<table id=child4 cellspacing=0 cellpadding=0 width=150 border=0>
					<tr height=20>
						<td align="center" width=30><img height=9 src="images/menu_icon.jpg" width=9></td>
						<td><a class=menuchild href="questionListPage.action" target=main>考题列表</a></td>
					</tr>
					<tr height=4><td colspan=2></td></tr>
					<tr height=20>
						<td align="center" width=30><img height=9 src="images/menu_icon.jpg" width=9></td>
						<td><a class=menuchild href="question-batch.jsp" target=main>导入导出</a></td>
					</tr>
					<tr height=4><td colspan=2></td></tr>
				</table>

			<!--菜单栏5-->
			<table cellspacing=0 cellpadding=0 width=150 border=0>
				<tr height=22>
					<td style="padding-left: 30px" background="images/menu_bt.jpg">
						<a class=menuparent onclick=expand(5) href="javascript:void(0);">成绩管理</a>
					</td>
				</tr>
				<tr height=4><td></td></tr>
			</table>
				<!--菜单子项5-->
				<table id=child5 cellspacing=0 cellpadding=0 width=150 border=0>
					<tr height=20>
						<td align="center" width=30><img height=9 src="images/menu_icon.jpg" width=9></td>
						<td><a class=menuchild href="gradeListPage.action" target=main>成绩列表</a></td>
					</tr>
					<tr height=4><td colspan=2></td></tr>
				</table>
			
			<!--菜单栏6-->
			<table cellspacing=0 cellpadding=0 width=150 border=0>
				<tr height=22>
					<td style="padding-left: 30px" background="images/menu_bt.jpg">
						<a class=menuparent onclick=expand(6) href="javascript:void(0);">高级管理</a>
					</td>
				</tr>
				<tr height=4><td></td></tr>
			</table>
				<!--菜单子项6-->
				<table id=child6 cellspacing=0 cellpadding=0 width=150 border=0>
					<tr height=20 id="usertr">
						<td align="center" width=30><img height=9 src="images/menu_icon.jpg" width=9></td>
						<td><a class=menuchild href="admin-list.jsp" target=main>管理员</a></td>
					</tr>
					<tr height=4><td colspan=2></td></tr>
				</table>
				
				
		</td>
		<td width=1 bgcolor=#d1e6f7></td>
		</tr>
	</table>
</body>



