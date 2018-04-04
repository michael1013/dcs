package cn.gnetop.dcs.portal.excel;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.gnetop.pde.foundation.DateUtils;

/**
 * @author Junhua.Zhang
 * @version 创建时间：2016-4-23 上午10:25:57
 */
public class ExcelReaderUtil {
	private static Logger log = LoggerFactory.getLogger(ExcelReaderUtil.class);

	/**
	 * 读取excel内容
	 * 
	 * @param inputStream
	 *            excel输入流
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static List<Map<Integer, Object>> readExcelContent(InputStream inputStream, String fileName,
	        Integer sheets) {
		if (StringUtils.isBlank(fileName)) {
			return null;
		}
		String ext = FilenameUtils.getExtension(fileName);
		boolean isXlsx = ext.equalsIgnoreCase("xlsx");
		return readExcelContent(inputStream, isXlsx, sheets);
	}

	/**
	 * 读取excel内容
	 * 
	 * @param inputStream
	 *            excel输入流
	 * @param isXlsx
	 *            是否为xlsx格式2007版本
	 * @param sheetIndex
	 *            工作表下标
	 * @return
	 */
	public static List<Map<Integer, Object>> readExcelContent(InputStream inputStream, boolean isXlsx, int sheetIndex) {
		if (inputStream == null) {
			return null;
		}
		Workbook workbook = null;
		try {
			if (isXlsx) {
				workbook = new XSSFWorkbook(inputStream);
			} else {
				workbook = new HSSFWorkbook(inputStream);
			}
		} catch (IOException e) {
			log.warn("read excel file failed!", e);
			return null;
		} finally {
			try {
				if (null != workbook) {
					workbook.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			IOUtils.closeQuietly(inputStream);
		}

		Sheet sheet = workbook.getSheetAt(sheetIndex);
		if (sheet == null) {
			return null;
		}

		List<Map<Integer, Object>> list = new ArrayList<Map<Integer, Object>>();
		int colNum = sheet.getRow(2).getLastCellNum();
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			Map<Integer, Object> c = new HashMap<Integer, Object>();
			for (int j = 0; j < colNum; j++) {
				try {
					c.put(j, getCellValue(row.getCell(j)));
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
			list.add(c);
		}
		return list;
	}

	/**
	 * 根据excel类型取值
	 * 
	 * @param cell
	 * @return
	 */
	private static Object getCellValue(Cell cell) {// 获取单元格数据内容为字符串类型的数据
		if (cell == null) {
			return "";
		}

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue().trim();
		case Cell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date d = cell.getDateCellValue();
				return DateUtils.getDateString(d);
			}
			return cell.getNumericCellValue();
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		default:
			return "";
		}
	}

	/**
	 * 转换excel中的日期类型数据
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseDate(Object date) {
		if (date == null) {
			return null;
		}
		if (date instanceof Double) {
			return HSSFDateUtil.getJavaDate((Double) date, false);
		} else if (date instanceof String) {
			try {
				return DateUtils.parseDate((String) date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String toString(Object obj) {
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	public static boolean isCellType() {
		return false;
	}
}