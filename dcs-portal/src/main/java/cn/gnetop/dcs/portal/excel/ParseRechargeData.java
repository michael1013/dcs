package cn.gnetop.dcs.portal.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.gnetop.dcs.dao.schema.Recharge;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class ParseRechargeData {

	public static void main(String[] args) {
		ExcelFileConfig config = ExcelFileInstances.xlsxList.get(1);
		List<Recharge> rechargeList = read(config);
		// InsertRechargeManager.save(rechargeList);
		// InsertRechargeManager.print(rechargeList);
	}

	public static List<? extends CommonRecharge> read(String filePath, String gameid,
	        Class<? extends CommonRecharge> clz, String charset, Integer sheet) {
		List<CommonRecharge> list = new ArrayList<>();
		File file = new File(filePath);
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				list.addAll(read(f, gameid, clz, sheet, charset));
			}
		} else {
			return read(file, gameid, clz, sheet, charset);
		}
		return list;
	}

	public static List<? extends CommonRecharge> read(File file, String gameid, Class<? extends CommonRecharge> clz,
	        Integer sheet, String charset) {
		List<CommonRecharge> list = new ArrayList<>();
		try {
			InputStream is = new FileInputStream(file);
			List<Map<Integer, Object>> mapList = ExcelReaderUtil.readExcelContent(is, file.getName(), sheet);
			for (Map<Integer, Object> map : mapList) {
				try {
					CommonRecharge r = clz.getDeclaredConstructor(String.class, Map.class, String.class, String.class)
					        .newInstance(gameid, map, file.getName());
					if (r.isValid()) {
						list.add(r);
					}
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Recharge> read(ExcelFileConfig config) {
		String filePath = config.getFilePath();
		File file = new File(filePath);
		List<Recharge> rechargeList = new ArrayList<>();
		for (int i = 0; i < config.getSheets(); i++) {
			try {
				if (file.isDirectory()) {
					for (File f : file.listFiles()) {
						rechargeList.addAll(parseExcelFile(f, config.getAppid(), config.getServerid(),
						        config.getListCount(), config.getCurrency(), config.getPattern(), i));
					}
				} else {
					rechargeList.addAll(parseExcelFile(file, config.getAppid(), config.getServerid(),
					        config.getListCount(), config.getCurrency(), config.getPattern(), i));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rechargeList;
	}

	public static List<Recharge> parseExcelFile(File file, String gameid, Integer serverid, List<Integer> countList,
	        String currency, String parsePattern, Integer sheets) throws IOException {
		InputStream is = new FileInputStream(file);
		List<Map<Integer, Object>> list = ExcelReaderUtil.readExcelContent(is, file.getName(), sheets);
		// 检查赋值
		List<Recharge> rechargeList = new ArrayList<>();
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, Object> map = list.get(i);
			Recharge r = new Recharge();
			if (null != countList.get(0)) {
				r.setUserid(String.valueOf(map.get(countList.get(0))));
			}
			if (null != countList.get(1)) {
				r.setUsername(String.valueOf(map.get(countList.get(1))));
			}
			if (null != countList.get(2)) {
				r.setIp(String.valueOf(map.get(countList.get(2))));
			}
			if (null != gameid) {
				r.setGameid(gameid);
			}
			if (null == serverid) {
				r.setServerid(gameid + "_1");
			} else {
				r.setServerid(gameid + "_" + sheets);
			}
			if (null != countList.get(3)) {
				r.setOrderid(String.valueOf(map.get(countList.get(3))));
			}
			if (null != countList.get(4)) {
				r.setAmount(String.valueOf(map.get(countList.get(4))));
			}
			r.setCurrency(currency);
			try {
				r.setLogTime(DateUtils
				        .formate(DateUtils.parseDate(String.valueOf(map.get(countList.get(6))), parsePattern)));
			} catch (ParseException e) {
				System.err.println(r);
				e.printStackTrace();
			}
			r.setCreateTime(DateUtils.getDateString());
			if (null != countList.get(5)) {
				r.setChannel(String.valueOf(countList.get(5)));
			}

			r.setFile(file.getName());
			r.setMd5(MD5Utils.encrypt(r.toString()));
			rechargeList.add(r);
		}

		return rechargeList;
	}
}
