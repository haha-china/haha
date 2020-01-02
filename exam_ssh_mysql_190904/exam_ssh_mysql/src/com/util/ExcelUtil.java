package com.util;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * excel文件操作类
 */
public class ExcelUtil {

	// 写入excel文件
	@SuppressWarnings("unchecked")
	public static boolean writeExcel(OutputStream os, List<Object> bookList){
		try {// 打开文件 (可写方式打开文件) 没有则创建
			WritableWorkbook book = Workbook.createWorkbook(os);
			for (int i = 0; i < bookList.size(); i++) {
				List<Object> sheetList = (List<Object>) bookList.get(i);	//解析参数(获取文件名之外工作表)
				String sheetName = sheetList.get(0).toString();	//解析参数(获取工作表名)
				WritableSheet sheet = book.createSheet(sheetName, i);	//创建的工作表
				for(int j=1; j < sheetList.size(); j++){
					List<String> rowList = (List<String>) sheetList.get(j);//解析参数(获取单元格)
					for (int k = 0; k < rowList.size(); k++) {
						Label label = new Label(k, j-1, rowList.get(k)); 
						sheet.addCell(label);
					}
				}
			}
			// 写入数据并关闭文件
			book.write();book.close();
			return true;
		} catch (Exception e) {
			return false;
		} 
	}
	
	
	// 核对excel文件
	public static boolean checkExcel(File file, List<String> list){
		try {
			Workbook workbook = Workbook.getWorkbook(file);// 获取excel文件 (只读方式打开)
			Sheet[] sheets = workbook.getSheets();// 获得所有工作表对象
			for (int i = 0; i < sheets.length; i++) {
				Cell[] cells = sheets[i].getRow(0);	//获取工作表第一行所有单元格
				for (int j = 0; j < list.size(); j++) {
					if (!cells[j].getContents().equals(list.get(j))) {	//判断单元格内容是否与参数相符
						return false;
					}
				}//若每张工作表格式都正确则返回true
			}return true;
		} catch (Exception e) {
			return false;
		} 
	}
	
	// 读取excel文件
	public static List<List<Object>> readExcel(File file){
		List<List<Object>> bookList = new ArrayList<List<Object>>();	//工作簿list 放置整个文件的内容
		try {
			Workbook workbook = Workbook.getWorkbook(file);// 获取excel文件 (只读方式打开)
			Sheet[] sheets = workbook.getSheets();// 获得所有工作表对象
			for (int i = 0; i < sheets.length; i++) {
				List<Object> sheetList = new ArrayList<Object>();
				sheetList.add(sheets[i].getName());
				for (int j = 1; j < sheets[i].getRows(); j++) {
					Cell[] cells = sheets[i].getRow(j);	//获取工作表某行所有单元格
					List<String> rowlList = new ArrayList<String>();
					for (int k = 0; k < cells.length; k++) {
						rowlList.add(cells[k].getContents());
					}sheetList.add(rowlList);
				}bookList.add(sheetList);
			}
		} catch (Exception e) {
			return null;
		}return bookList;
	}

	
}
