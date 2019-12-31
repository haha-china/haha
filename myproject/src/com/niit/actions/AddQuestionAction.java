package com.niit.actions;

import com.niit.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class AddQuestionAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;setchar=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userInfo");

        response.getWriter().println("<script>window.alert('添加成功！！');window.location.href='view_QO.jsp';</script>");
        return "success";
    }
}
