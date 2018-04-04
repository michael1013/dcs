package cn.gnetop.dcs.portal.excel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelFileInstances {

	public static List<ExcelFileConfig> xlsxList = new ArrayList<>();
	public static List<ExcelFileConfig> csvList = new ArrayList<>();

	// userid, username, ip, orderid, amount, channel
	static {
		xlsxList.add(new ExcelFileConfig("D:/My Document/sdk/logs/文件夹/艾德尔冒险-20160919/付费数据", "4002001001", null, "USF",
		        "MM/dd/yyyy HH:mm:ss", 0, Arrays.asList(new Integer[] { 0, 1, 8, 6, 7, 14, 9 })));
		// xlsxList.add(new ExcelFileConfig("D:/My
		// Document/sdk/logs/文件夹/霸王的大陆-20160911/充值日志.xls", "4002001002", null,
		// "CNY", "MM/dd/yyyy HH:mm:ss", Arrays.asList(new Integer[] { 0, 1, 8,
		// 6, 7, 14 })));
		xlsxList.add(new ExcelFileConfig("D:/My Document/sdk/logs/文件夹/帝国舰队东南亚-20160901/服务器订单.xlsx", "4002001004", null,
		        "CNY", "yyyy-MM-dd HH:mm:ss", 4, Arrays.asList(new Integer[] { 2, 3, 7, 4, 0, 6, 8 })));
		xlsxList.add(new ExcelFileConfig("D:/My Document/sdk/logs/文件夹/帝国舰队欧美-20160901/充值订单.xlsx", "4002001005", null,
		        "CNY", "yyyy-MM-dd HH:mm:ss", 4, Arrays.asList(new Integer[] { 2, 3, 7, 4, 0, 6, 8 })));

		csvList.add(new ExcelFileConfig("D:/My Document/sdk/logs/文件夹/部落特烦恼手机网络游戏软件-20160911/部落_pay.csv", "4002001003",
		        null, "CNY", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS", 0,
		        Arrays.asList(new Integer[] { 0, null, null, 2, 4, 3, 1 })));
	}

}

class ExcelFileConfig {
	private String filePath;
	private String appid;
	private Integer serverid;
	private String currency;
	private String pattern;
	private Integer sheets;
	private List<Integer> listCount;

	public ExcelFileConfig(String filePath, String appid, Integer serverid, String currency, String pattern,
	        Integer sheets, List<Integer> listCount) {
		super();
		this.filePath = filePath;
		this.appid = appid;
		this.serverid = serverid;
		this.currency = currency;
		this.pattern = pattern;
		this.sheets = sheets;
		this.listCount = listCount;
	}

	public Integer getSheets() {
		return sheets;
	}

	public void setSheets(Integer sheets) {
		this.sheets = sheets;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Integer getServerid() {
		return serverid;
	}

	public void setServerid(Integer serverid) {
		this.serverid = serverid;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<Integer> getListCount() {
		return listCount;
	}

	public void setListCount(List<Integer> listCount) {
		this.listCount = listCount;
	}

}