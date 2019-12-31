<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除成功</title>
</head>
<body>
<%
    int id = Integer.valueOf(request.getParameter("u_id"));
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/questionnaire", "root", "zjq1997926");
        String sql = "delete from user where u_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<%
    response.setHeader("refresh", "3;URL=view_user.jsp");
%>
<p style="color: #01AAED; font-size: 30px;margin-top: 50px" align="center">删除成功！</p>
<p style="color: #01AAED; font-size: 25px;" align="center">系统将在3秒后返回界面...</p>

</body>
</html>

