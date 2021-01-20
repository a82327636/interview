package com.alibaba.mos.util.excel;

import java.io.File;

public class ExcelReaderUtil {
	
	//excel2003扩展名
	public static final String EXCEL03_EXTENSION = ".xls";

	
	/**
	 * 读取Excel文件
	 * @throws Exception 
	 */
	public static void readExcel(IRowReader reader, File file) throws Exception{
		// 处理excel2003文件
		if (file.getName().endsWith(EXCEL03_EXTENSION)){
			Excel2003Reader excel03 = new Excel2003Reader();
			excel03.setRowReader(reader);
			excel03.process(file);
		} else {
			throw new  Exception("文件格式错误，fileName的扩展名只能是xls");
		}
	}
}

