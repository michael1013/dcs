package cn.gnetop.dcs.portal.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ExcelCommonDemo {
	public static void main(String[] args) throws Exception {
		File file = new File("D:/My Document/sdk/logs/文件夹/帝国舰队东南亚-20160901/服务器订单.xlsx");
		InputStream is = new FileInputStream(file);
		int sheets = 0;
		List<Map<Integer, Object>> list = ExcelReaderUtil.readExcelContent(is, file.getName(), sheets);
		String t = String.valueOf(list.get(1).get(8));
		System.out.println(t);
	}
}
