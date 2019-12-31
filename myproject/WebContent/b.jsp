<%@page import="com.niit.dao.QuestionDao"%>
<%@page import="org.aspectj.weaver.patterns.TypePatternQuestions.Question"%>
<%@page import="com.niit.dao.IQuestionDao"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.niit.dao.QuestionDao"%>
<%@ page import="com.niit.entity.Questions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<base href="<%=basePath%>"> 
<html>
<head>
    <title>填写问卷</title>

    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">

</head>

<% 
	int tk = 0;
    int dx = 0;
    int imx = 0;
    int mx = 0;
    ArrayList<Questions> list = (ArrayList<Questions>)session.getAttribute("question");
	
%>
<body>

<div class="container">
    <div class="one">
        <table class="table" style="background-color: #333333">
            <tr><td></td></tr>
            <tr class="container-fluid" align="center">
                <td class="col-xs-3"><a href="home.jsp"><h4 style="color: deepskyblue">首页</h4></a></td>
                <td class="col-xs-3"><a href="view_WJ.jsp"><h4 style="color: deepskyblue">所有问卷</h4></a></td>
                <td class="col-xs-3"><a href="viewMy_WJ.jsp?u_id=<%=session.getAttribute("Name")%>"><h4 style="color: deepskyblue">我的问卷</h4></a></td>
                <td class="col-xs-3"><a href="addWJ.jsp"><h4 style="color: deepskyblue">创建问卷</h4></a></td>
            </tr>
        </table>
        <div class="two">
            <form action="Tj.action">
                <input type="text" style="display: none;" value="1" name="q_id">
                <div align="left">
                    <%
                        if (list.size() > 0) {
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getO_content().equals("") || list.get(i).getO_content() == "") {
                    %>
                    <br>
                    <label><%=i + 1%>、<%=list.get(i).getQ_content()%>【填空题】</label>
                    <input type="text" style="display: none;" value="<%=list.get(i).getQ_id()%>" name="tkqid<%=tk%>">
                    <br><textarea class="form-control" rows="3" type="text" name="tk<%=tk%>"
                                  placeholder="请输入你的答案"></textarea><br>
                    <%
                        tk += 1;
                    } else {
                        String[] strings = list.get(i).getO_content().split("#");
                        if (strings[1].substring(1, 3).equals("多选")) {
                    %>
                    <br>
                    <label><%=i + 1%>、<%=list.get(i).getQ_content()%> 【多选】</label>
                    <input type="text" style="display: none;" value="<%=list.get(i).getQ_id()%>" name="dxqid<%=mx%>">
                    <%
                        for (int j = 1; j < strings.length; j++) {

                                    if (j == 1) {
                    %>
                    <p style="margin:0.1em 0;">&nbsp;&nbsp;&nbsp;<input type="checkbox" value="<%=strings[j].substring(4)%>"
                                                                        name="mx<%=mx%><%=imx%>"><%=strings[j].substring(4)%></p>
                    <%
                    } else {
                    %>
                    <p style="margin:0.1em 0;">&nbsp;&nbsp;&nbsp;<input type="checkbox" value="<%=strings[j]%>" name="mx<%=mx%><%=imx%>"><%=strings[j]%></p>
                    <%
                            }
                            imx += 1;
                        }%>

                    <input type="text" style="display: none;" value="<%=imx%>" name="imx<%=mx%>">
                    <%
                            imx = 0;
                            mx += 1;
                        }
                        if (strings[1].substring(1, 3).equals("单选")) {

                    %>

                    <br><label><%=i + 1%>、<%=list.get(i).getQ_content()%>【单选】</label>
                    <input type="text" style="display: none;" value="<%=list.get(i).getQ_id()%>" name="ddxqid<%=dx%>">
                    <%
                        for (int n = 1; n < strings.length; n++) {
                            if (n == 1) {
                    %>
                    <p style="margin:0.1em 0;">&nbsp;&nbsp;&nbsp;<input type="radio" value="<%=strings[n].substring(4)%>"
                               name="dx<%=dx%>"><%=strings[n].substring(4)%>
                    </p>

                    <%
                    } else {
                    %>
                    <p style="margin:0.1em 0;">&nbsp;&nbsp;&nbsp;<input type="radio" value="<%=strings[n]%>" name="dx<%=dx%>"><%=strings[n]%></p>
                    <%
                                            }
                                        }
                                        dx += 1;
                                    }
                                }
                            }
                        }
                    %>

                </div>
                <input type="text" style="display: none;" value="<%=tk%>" name="tk">
                <input type="text" style="display: none;" value="<%=dx%>" name="dx">
                <input type="text" style="display: none;" value="<%=mx%>" name="mx">
                <div align="center">
                    <input type="submit" value="提交" class="btn btn-success" style="margin-top: 15px;">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

