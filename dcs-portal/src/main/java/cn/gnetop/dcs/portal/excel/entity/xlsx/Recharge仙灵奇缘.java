package cn.gnetop.dcs.portal.excel.entity.xlsx;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseCsvFile;
import cn.gnetop.dcs.portal.excel.ParseXlsxFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.MapUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge仙灵奇缘 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge仙灵奇缘(String gameid, String value, String file) {
		if (StringUtils.isNoneBlank(value)) {
			value = value.replaceAll("\\\"", "");
		}
		String[] column = value.split(",");
		if (column.length == 10) {
			this.gameid = gameid;
			this.file = file;
			this.userid = column[1];
			this.username = column[2];
			// this.orderid = column[0];
			try {
				this.logTime = DateUtils.formate(DateUtils.parseDate(column[8], "yyyy-MM-dd HH:mm:ss",
				        "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// this.ip = column[8];
			this.amount = column[6];
			this.currency = "CNY";
			// this.channel = column[6];
			if (StringUtils.isNoneBlank(column[0])) {
				try {
					this.serverid = this.gameid + '_' + column[0].substring(column[0].indexOf('_') + 1);
				} catch (Exception e) {
					System.err.println(column[0]);
					e.printStackTrace();
				}
			}
			this.createTime = DateUtils.getDateString();
			this.md5 = MD5Utils.encrypt(this.toString());
			this.isValid = true;
		} else {
			this.isValid = false;
		}
	}

	public Recharge仙灵奇缘(String gameid, Map<String, Object> map, String file, Integer sheet) {
		if (MapUtils.isNotEmpty(map)) {
			if (map.size() == 9) {
				this.gameid = gameid;
				this.file = file;
				if (StringUtils.isBlank(String.valueOf(map.get(0)))) {
					return;
				}
				this.userid = String.valueOf(map.get(2));
				this.username = String.valueOf(map.get(3));
				try {
					this.orderid = new BigDecimal(String.valueOf(map.get(5))).toPlainString();
				} catch (Exception e) {
					System.err.println(map.get(4));
					e.printStackTrace();
				}
				try {
					this.logTime = DateUtils.formate(
					        DateUtils.parseDate(String.valueOf(map.get(9)), "MM/dd/yyyy HH:mm:ss", "yyyy/MM/dd HH:mm",
					                "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
				} catch (ParseException e) {
					e.printStackTrace();
					return;
				}
				// this.ip = String.valueOf(map.get(4));
				this.amount = String.valueOf(map.get(7));
				// this.currency = "USD";// String.valueOf(map.get(11));
				// this.channel = String.valueOf(map.get(6));
				if (StringUtils.isNoneBlank(this.file)) {
					try {
						this.serverid = this.gameid + '_'
						        + String.valueOf(map.get(1)).substring(String.valueOf(map.get(1)).lastIndexOf('_') + 1);
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

	private static List<CommonRecharge> read(String filePath, String gameid, String charset, Integer sheet) {
		File file = new File(filePath);
		List<CommonRecharge> list = new ArrayList<>();
		for (File f : file.listFiles()) {
			if (f.getName().lastIndexOf("xlsx") > -1 || f.getName().lastIndexOf("xls") > -1) {
				list.addAll(ParseXlsxFile.read(filePath, gameid, Recharge仙灵奇缘.class, charset, sheet));
			} else {
				list.addAll(ParseCsvFile.read(filePath, gameid, Recharge仙灵奇缘.class, charset));
			}
		}
		return list;
	}

	public static void main(String[] args) {
		String[] filePaths = { "C:/Users/luke/Documents/logs/仙灵奇缘" };
		for (String filePath : filePaths) {
			for (int i = 0; i < 1; i++) {
				List<? extends CommonRecharge> list = read(filePath, "4002001050", "GBK", i);
				InsertRechargeManager.save(list);
				// InsertRechargeManager.print(list);
			}
		}
	}
}
