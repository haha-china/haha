package com.niit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.biz.IWenjuanBiz;
import com.niit.entity.Wenjuan;

@Controller
@RequestMapping(value="/wenjuan")
public class WenjuanController {
	@Resource(name="wenjuanBiz")
	IWenjuanBiz wenjuanBiz;
	
	
	@RequestMapping(value="/get")
	public String getwenjuan(HttpSession session) {
		List<Wenjuan> list = new ArrayList<Wenjuan>();
		list=wenjuanBiz.findAll();
//		for(int i = 0 ; i<list.size(); i++) {
//			
//			String s = String.valueOf(list.get(i).getW_id());
//			
//			
//		}
//		
		System.out.println("-------"+list.size());
		session.setAttribute("wenjuan", list);
		return "view_WJ";
	} 
	
	
	
	@RequestMapping(value="/get_g")
	public String getwenjuan_g(HttpSession session) {
		List<Wenjuan> list = new ArrayList<Wenjuan>();
		list=wenjuanBiz.findAll();
		System.out.println("-------"+list.size());
		session.setAttribute("wenjuan", list);
		return "gview_WJ";
		
	} 
	
	
	
	@RequestMapping(value="aaa")
	public String getMywen(@RequestParam(value="w_id") Integer userId,HttpServletRequest request,
			HttpSession session) {
	     String s= userId.toString();
	     List<Wenjuan> listw = new ArrayList<>();
	   listw =  wenjuanBiz.findByuid(s);
	   System.out.println(listw.size());
	   session.setAttribute("mywenjuan", listw);
	   request.setAttribute("mywlist", s);
		
		return "viewMy_WJ";
		
		
		
	}
	
	
//	@RequestMapping(value="/{u_id}/sss")
//	public String getMywenjaun(@PathVariable(value="u_id") String userId) {
//		System.out.println(userId);
//		return null;
//		
//		
//		
//	}

}
