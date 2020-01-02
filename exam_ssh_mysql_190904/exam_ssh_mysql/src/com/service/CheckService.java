package com.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

/**
 * 验证码服务类
 * 负责验证码图文组合的生成
 */
@Service
public class CheckService {

    //随机的字符库
    private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //字符串的字体
    private String fontType = "Fixedsys";
    
    private int imageWidth = 100;	//图片宽
    private int imageHeight = 25;	//图片高
    private int fontNum = 4;	//字符数量
	private int fontSize = 20;	//字符大小
    private int lineNum = 40;	//干扰线数量
    
    private Random random = new Random();		//持有随机类的引用
    
    //生成随机图片
    public Map<String, Object> getCheckCode() {
        //BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage randomImage = new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_BGR);
        //产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics graphics = randomImage.getGraphics();	
        graphics.fillRect(0, 0, imageWidth, imageHeight);	//填充整个图片(背景)
        graphics.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,18));
        graphics.setColor(getRandColor(110, 133));
        
        //绘制干扰线
        for(int i=0;i<=lineNum;i++){
            drowLine(graphics);
        }
        //绘制随机字符
        String randomString = "";
        for(int i=1;i<=fontNum;i++){
            randomString=drowString(graphics,randomString,i);
        }
        //将随机图片和字符串放入map
        Map<String, Object> checkCode = new HashMap<String, Object>();
        checkCode.put("checkstring", randomString);
        checkCode.put("checkimage",randomImage);
        graphics.dispose();	 //释放资源
		return checkCode;
    }
    
    //获得颜色
    private Color getRandColor(int fc,int bc){
        if(fc > 255)	fc = 255;
        if(bc > 255)	bc = 255;
        int r = fc + random.nextInt(bc-fc-16);
        int g = fc + random.nextInt(bc-fc-14);
        int b = fc + random.nextInt(bc-fc-18);
        return new Color(r,g,b);
    }
    
    //绘制干扰线
    private void drowLine(Graphics g){
        int x = random.nextInt(imageWidth);
        int y = random.nextInt(imageHeight);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x+xl, y+yl);
    }

    //绘制字符串
    private String drowString(Graphics g,String randomString,int i){
        g.setFont(new Font(fontType,Font.CENTER_BASELINE,fontSize));
        g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString +=rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, fontSize*i-random.nextInt(fontSize/2), imageHeight/2+fontSize/4);//随机位置放置验证码       
        return randomString;
    }

    //获取随机的字符
    public String getRandomString(int num){
        return String.valueOf(randString.charAt(num));
    }
    
    
    
	public int getImageWidth() {
		return imageWidth;
	}
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}
	public int getImageHeight() {
		return imageHeight;
	}
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	public int getFontNum() {
		return fontNum;
	}
	public void setFontNum(int fontNum) {
		this.fontNum = fontNum;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public int getLineNum() {
		return lineNum;
	}
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

}
