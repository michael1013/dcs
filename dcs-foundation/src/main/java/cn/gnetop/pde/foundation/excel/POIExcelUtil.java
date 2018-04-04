package cn.gnetop.pde.foundation.excel;

public class POIExcelUtil {
	// private static Log log = LogFactory.getLog(POIExcelUtil.class);
	//
	// private final static String SEPARATOR = "-";
	//
	// public static String getCellValue(int[] position, Row row) {
	// String value = "";
	// for (int pIndex = 0; pIndex < position.length; pIndex++) {
	// if (position[pIndex] == -1)
	// continue;
	// Cell cell = row.getCell(position[pIndex]);
	// if (cell == null) {
	// continue;
	// }
	//
	// String cellValue = getCellValue(cell);
	//
	// value += cellValue.concat(SEPARATOR);
	// }
	// if (value != null && value.length() > 0)
	// value = value.substring(0, value.length() - 1);
	// return value;
	// }
	//
	// public static String getCellValue(Cell cell) {
	// String cellValue = "";
	// int cType = cell.getCellType();
	// try {
	// switch (cType) {
	// // 这里会不会有问题？会不会返回null?
	// case Cell.CELL_TYPE_STRING:
	// cellValue = cell.getStringCellValue();
	// break;
	// case Cell.CELL_TYPE_NUMERIC:
	// if (HSSFDateUtil.isCellDateFormatted(cell)) {
	// // 获取日期格式
	// cellValue = DateFormat.getDateInstance().format(cell.getDateCellValue());
	// } else {
	// // 数字格式,去掉浮点数,如1.02,读取出来变成1
	// cellValue = Integer.toString((int) cell.getNumericCellValue());
	// }
	// break;
	// case Cell.CELL_TYPE_BLANK:
	// cellValue = cell.getStringCellValue();
	// break;
	// case Cell.CELL_TYPE_FORMULA:
	// // 是公式，获取公式值
	// cellValue = getFormulaValue(cell);
	// break;
	// case Cell.CELL_TYPE_BOOLEAN:
	// cellValue = Boolean.toString(cell.getBooleanCellValue());
	// break;
	// case Cell.CELL_TYPE_ERROR:
	// cellValue = Byte.toString(cell.getErrorCellValue());
	// break;
	// default:
	// cellValue = cell.getStringCellValue();
	// break;
	// }
	// } catch (Exception e) {
	// log.error(new
	// StringBuilder("读取单元格的值出现异常,SHEET:").append(cell.getSheet().getSheetName()).append(",Row:")
	// .append(cell.getRowIndex() +
	// 1).append(",Column:").append(cell.getColumnIndex() + 1).toString(), e);
	// }
	// if (cellValue != null) // 对单元格值取TRIM
	// cellValue = cellValue.trim();
	// return cellValue;
	// }
	//
	// private static String getFormulaValue(Cell cell) {
	// String cellValue;
	// // 获取公式值
	// switch (cell.getCachedFormulaResultType()) {
	// case Cell.CELL_TYPE_STRING:
	// cellValue = cell.getRichStringCellValue().toString();
	// break;
	// case Cell.CELL_TYPE_BOOLEAN:
	// cellValue = Boolean.toString(cell.getBooleanCellValue());
	// break;
	// case Cell.CELL_TYPE_NUMERIC:
	// if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
	// // 获取日期格式
	// cellValue = DateFormat.getDateInstance().format(cell.getDateCellValue());
	// } else {
	// // 数字格式,去掉浮点数,如1.02,读取出来变成1
	// cellValue = Integer.toString((int) cell.getNumericCellValue());
	// }
	// break;
	// case Cell.CELL_TYPE_ERROR:
	// cellValue = Byte.toString(cell.getErrorCellValue());
	// break;
	// default:
	// // 获取公式
	// cellValue = cell.getCellFormula();
	// break;
	// }
	// return cellValue;
	// }
	//
	// /**
	// * 给Excel单元格设置值
	// *
	// * @param cell
	// * @param value
	// * @param cs
	// */
	// public static void setCell(Cell cell, Object value, CellStyle cs) {
	// if (cs != null) {
	// cell.setCellStyle(cs);
	// }
	// // 针对于值为空值的特殊情况进行处理
	// if (value == null) {
	// cell.setCellValue("");
	// } else {
	// if (value instanceof String) {
	// cell.setCellValue((String) value);
	// } else if (value instanceof Integer) {
	// cell.setCellValue((Integer) value);
	// } else if (value instanceof RichTextString) {
	// cell.setCellValue((RichTextString) value);
	// } else if (value instanceof Double) {
	// cell.setCellValue((Double) value);
	// } else if (value instanceof Date) {
	// // 日期写入Excel值存在显示格式问题.转换为String进行处理
	// cell.setCellValue(DateUtils.formate((Date) value));
	// // cell.setCellValue((Date) value);
	// } else if (value instanceof Calendar) {
	// cell.setCellValue((Calendar) value);
	// } else if (value instanceof Boolean) {
	// cell.setCellValue((Boolean) value);
	// } else {
	// cell.setCellValue(value.toString());
	// }
	// }
	// }
	//
	// /**
	// * 创建sheet样式 @Title: createStyle @Description: TODO @param @param
	// * workbook @param @return @return HSSFCellStyle @throws
	// */
	// public static CellStyle createStyle(Workbook workbook) {
	// CellStyle cs = workbook.createCellStyle(); // 创建一个样式
	// Font font = workbook.createFont();
	// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	// cs.setFont(font);
	// cs.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格底线显示样式：细线
	// cs.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格底线显示颜色：黑色
	// cs.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 设置单元格左线显示样式：细线
	// cs.setLeftBorderColor(HSSFColor.BLACK.index); // 设置单元格左线显示颜色：黑色
	// cs.setBorderTop(HSSFCellStyle.BORDER_THIN); // 设置单元格顶线显示样式：细线
	// cs.setTopBorderColor(HSSFColor.BLACK.index); // 设置单元格顶线显示颜色：黑色
	// cs.setBorderRight(HSSFCellStyle.BORDER_THIN); // 设置单元格右线显示样式：细线
	// cs.setRightBorderColor(HSSFColor.BLACK.index); // 设置单元格右线显示颜色：黑色
	// cs.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 文本对齐方式，水平方向：居中
	// cs.setVerticalAlignment(HSSFCellStyle.ALIGN_FILL); // 文本对齐方式，垂直方向：填充
	// cs.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND); // 填充样式：实心
	// cs.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index); // 设置前景色（单元格显示颜色）
	// return cs;
	// }
	//
}
