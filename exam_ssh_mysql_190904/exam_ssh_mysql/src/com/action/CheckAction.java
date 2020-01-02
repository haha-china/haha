package com.action;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.struts2.convention.annotation.Action;

import com.service.CheckService;

/**
 * 验证码Action
 * 负责传送验证码图片
 * 及对输入内容进行验证
 */
@SuppressWarnings("serial")
public class CheckAction extends BaseAction{

	@Resource
	private CheckService checkService;
	private String inputString;


	//获取验证码图片
	@Action("codeImage")
	public void image(){
		checkService.setImageWidth(100);	//设置验证码图片宽度
		checkService.setImageHeight(30);	//设置验证码图片高度
//		checkService.setFontSize(20);	//设置验证码字体大小
//		checkService.setFontNum(4);		//设置验证码字符长度
//		checkService.setLineNum(20);	//设置验证码干扰线数量
		
		//获取验证码图文组合
		Map<String, Object> checkCode = checkService.getCheckCode();
		//将验证码内容写入session
		getSession().put("checkstring", checkCode.get("checkstring"));
		//将验证码图片传到页面
		try {
			ImageIO.write((RenderedImage) checkCode.get("checkimage"), "JPEG", getServletResponse().getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}  
        //禁止页面缓存   
		getServletResponse().addHeader("Cache-Control", "No-Cache"); 
		getServletResponse().addHeader("Pragma", "No-Cache");   
		getServletResponse().addHeader("Expires", "0"); 
	}
	
	
	//核对验证码
	@Action("codeCheck")
	public void check() throws IOException{
		String checkString = (String) getSession().get("checkstring");
		if (checkString.equals(inputString.toUpperCase())) {
//			System.out.println("验证成功!");
			getServletResponse().getWriter().write("success");
		}
		else {
//			System.out.println("验证失败!");
			getServletResponse().getWriter().write("fail");
		}
	}


	public String getInputString() {
		return inputString;
	}
	public void setInputString(String inputString) {
		this.inputString = inputString;
	}

}
