package cn.gnetop.dcs.portal.excel.entity.xlsx;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseCsvFile;
import cn.gnetop.dcs.portal.excel.ParseXlsxFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.dcs.portal.excel.entity.cvs.Recharge超级舰队csv;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.MapUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge超级舰队 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge超级舰队(String gameid, Map<String, Object> map, String file, Integer sheet) {
		if (MapUtils.isNotEmpty(map)) {
			if (map.size() == 10) {
				this.gameid = gameid;
				this.file = file;
				if (StringUtils.isBlank(String.valueOf(map.get(1)))) {
					return;
				}
				try {
					this.userid = String
					        .valueOf(Double.valueOf(Double.parseDouble(String.valueOf(map.get(3)))).longValue());
				} catch (Exception e) {
					System.err.println(map.get(3));
					e.printStackTrace();
					return;
				}
				// this.username = String.valueOf(map.get(3));
				this.orderid = String.valueOf(map.get(2));
				try {
					this.logTime = DateUtils
					        .formate(DateUtils.parseDate(String.valueOf(map.get(8)), "MM/dd/yyyy HH:mm:ss",
					                "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// this.ip = String.valueOf(map.get(7));
				this.amount = String.valueOf(map.get(4));
				this.currency = String.valueOf(map.get(5));
				this.channel = String.valueOf(map.get(7));
				if (StringUtils.isNoneBlank(this.file)) {
					try {
						this.serverid = this.gameid + '_' + String
						        .valueOf(Double.valueOf(Double.parseDouble(String.valueOf(map.get(1)))).longValue());
					} catch (Exception e) {
						e.printStackTrace();
						return;
					}
				}
				this.md5 = MD5Utils.encrypt(this.toString());
				this.createTime = DateUtils.getDateString();
				this.isValid = true;
				return;
			}
		}
		this.isValid = false;
	}

	private static List<? extends CommonRecharge> read(String filePath, String gameid,
	        Class<? extends CommonRecharge> clz, String charset, Integer sheet) {
		List<CommonRecharge> list = new ArrayList<>();
		File file = new File(filePath);
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				if (f.getName().endsWith(".xlsx") || f.getName().endsWith(".xls")) {
					list.addAll(ParseXlsxFile.read(filePath, gameid, clz, charset, sheet));
				} else {
					list.addAll(ParseCsvFile.read(filePath, gameid, Recharge超级舰队csv.class, charset));
				}
			}
		} else {
			if (file.getName().endsWith(".xlsx") || file.getName().endsWith(".xls")) {
				list.addAll(ParseXlsxFile.read(filePath, gameid, clz, charset, sheet));
			} else {
				list.addAll(ParseCsvFile.read(filePath, gameid, Recharge超级舰队csv.class, charset));
			}
		}
		return list;
	}

	public static void main(String[] args) {
		String[] filePaths = { "C:/Users/luke/Documents/logs/超级舰队txt" };
		for (String filePath : filePaths) {
			List<CommonRecharge> list = new ArrayList<>();
			for (File f : new File(filePath).listFiles()) {
				if (f.isDirectory()) {
					for (File fx : f.listFiles()) {
						for (File file : fx.listFiles()) {
							if (file.getName().indexOf("paylog") > -1) {
								list.addAll(read(file.getPath(), "4002001036", Recharge超级舰队.class, "UTF-8", null));
							}
						}
					}
				} else {
					list.addAll(read(f.getPath(), "4002001036", Recharge超级舰队.class, "UTF-8", 0));
					list.addAll(read(f.getPath(), "4002001036", Recharge超级舰队.class, "UTF-8", 1));
				}
			}
			InsertRechargeManager.save(list);
			// InsertRechargeManager.print(list);
		}
	}
}
