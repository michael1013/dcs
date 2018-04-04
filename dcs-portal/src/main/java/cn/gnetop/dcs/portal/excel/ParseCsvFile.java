package cn.gnetop.dcs.portal.excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.dao.schema.Recharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class ParseCsvFile {

	public static void main(String[] args) {
		ExcelFileConfig config = ExcelFileInstances.csvList.get(0);
		List<Recharge> rechargeList = read(config);
		// InsertRechargeManager.save(rechargeList);
		InsertRechargeManager.print(rechargeList);
	}

	public static <T> List<? extends T> read(String filePath, String gameid, Class<? extends T> clz) {
		return read(filePath, gameid, clz, "UTF-8");
	}

	public static <T> List<? extends T> read(String filePath, String gameid, Class<? extends T> clz, String charset) {
		List<T> list = new ArrayList<>();
		File file = new File(filePath);
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				list.addAll(read(f, gameid, clz, charset));
			}
		} else {
			return read(file, gameid, clz, charset);
		}
		return list;
	}

	public static <T> List<T> read(File file, String gameid, Class<? extends T> clz, String charset) {
		List<T> list = new ArrayList<>();
		BufferedReader br = null;
		try {
			if (StringUtils.isBlank(charset)) {
				charset = "UTF-8";
			}
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
			String l = null;
			while ((l = br.readLine()) != null) {
				try {
					T r = clz.getDeclaredConstructor(String.class, String.class, String.class).newInstance(gameid, l,
					        file.getName());
					list.add(r);
				} catch (Exception e) {
					System.err.println(file.getName());
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.err.println(file.getName());
			e.printStackTrace();
		} finally {
			try {
				if (null != br) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public static List<Recharge> read(ExcelFileConfig config) {
		List<Recharge> rechargeList = new ArrayList<>();
		try {
			File file = new File(config.getFilePath());
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String l = null;
			while ((l = br.readLine()) != null) {
				String[] column = l.split(",");
				Recharge r = new Recharge();
				if (null != config.getListCount().get(0)) {
					r.setUserid(column[config.getListCount().get(0)]);
				}
				if (null != config.getListCount().get(1)) {
					r.setUsername(column[config.getListCount().get(1)]);
				}
				if (null != config.getListCount().get(2)) {
					r.setIp(column[config.getListCount().get(2)]);
				}
				if (null != config.getAppid()) {
					r.setGameid(config.getAppid());
				}
				if (null == config.getServerid()) {
					r.setServerid(config.getAppid() + "_1");
				} else {
					r.setServerid(config.getAppid() + "_" + column[config.getServerid()]);
				}
				if (null != config.getListCount().get(3)) {
					r.setOrderid(column[config.getListCount().get(3)]);
				}
				if (null != config.getListCount().get(4)) {
					r.setAmount(column[config.getListCount().get(4)]);
				}
				if (null != config.getCurrency()) {
					r.setCurrency(config.getCurrency());
				}
				if (null != config.getListCount().get(5)) {
					r.setLogTime(DateUtils
					        .formate(DateUtils.parseDate(column[config.getListCount().get(5)], config.getPattern())));
				}
				r.setCreateTime(DateUtils.getDateString());
				if (null != config.getListCount().get(6)) {
					r.setChannel(column[config.getListCount().get(6)]);
				}
				r.setFile(config.getFilePath());
				r.setMd5(MD5Utils.encrypt(l));
				rechargeList.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rechargeList;
	}
}
