package com.niit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.biz.IQuestionBiz;
import com.niit.biz.QuestionBiz;
import com.niit.entity.Questions;
@Controller
@RequestMapping(value="/question")
public class QuestionController {
@Resource(name="questionBiz")
IQuestionBiz questionBiz;

@RequestMapping(value="aaa")
public String getQuestion(@RequestParam(value="w_id") Integer w_id,HttpServletRequest request,
			HttpSession session) {
	List<Questions> lisq= questionBiz.findBywid(w_id);
	System.out.println("-----"+lisq.size());
		session.setAttribute("question", lisq);
			return "b";

}
}