package com.alibaba.mos.util.excel;

import java.util.List;

/**
 * 因为内存有限的原因，所以一行一行从xls文件中取，而不是整个文件加入内存
 */
public interface IRowReader {
	
	/** 一行行获取
	 * @param sheetIndex
	 * @param curRow
	 * @param rowlist
	 */
	public  void getRows(int sheetIndex, int curRow, List<String> rowlist);

}

