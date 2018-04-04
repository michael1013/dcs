package cn.gnetop.dcs.portal.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParseXlsxFile {

	public static <T> List<? extends T> read(String filePath, String gameid, Class<? extends T> clz, String charset,
	        Integer sheet) {
		List<T> list = new ArrayList<>();
		File file = new File(filePath);
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				list.addAll(read(f, gameid, clz, charset, sheet));
			}
		} else {
			return read(file, gameid, clz, charset, sheet);
		}
		return list;
	}

	public static <T> List<? extends T> read(File file, String gameid, Class<? extends T> clz, String charset,
	        Integer sheet) {
		List<T> list = new ArrayList<>();
		try {
			InputStream is = new FileInputStream(file);
			List<Map<Integer, Object>> mapList = ExcelReaderUtil.readExcelContent(is, file.getName(), sheet);
			for (Map<Integer, Object> map : mapList) {
				try {
					T r = clz.getDeclaredConstructor(String.class, Map.class, String.class, Integer.class)
					        .newInstance(gameid, map, file.getName(), sheet);
					list.add(r);
				} catch (Exception e) {
					System.err.println(file.getName());
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.err.println(file.getName());
			e.printStackTrace();
		}
		return list;
	}

}
