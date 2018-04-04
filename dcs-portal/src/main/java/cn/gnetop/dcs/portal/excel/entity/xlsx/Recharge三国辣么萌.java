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

public class Recharge三国辣么萌 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge三国辣么萌(String gameid, String value, String file) {
		if (StringUtils.isNoneBlank(value)) {
			value = value.replaceAll("\\\"", "");
		}
		String[] column = value.split(",");
		if (column.length == 13) {
			this.userid = String.valueOf(((Double) Double.parseDouble(column[3])).longValue());
			this.username = column[7];
			this.orderid = column[12];
			try {
				this.logTime = DateUtils.formate(DateUtils.parseDate(column[10], "yyyy-MM-dd HH:mm:ss",
				        "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// this.ip = column[8];
			this.amount = column[9];
			// this.currency = "CNY";
			this.channel = column[0];
			this.isValid = true;
			this.gameid = gameid;
			this.serverid = this.gameid + '_' + 1;
			this.createTime = DateUtils.getDateString();
			this.md5 = MD5Utils.encrypt(this.toString());
		} else {
			this.isValid = false;
		}
	}

	public Recharge三国辣么萌(String gameid, Map<String, Object> map, String file, Integer sheet) {
		if (MapUtils.isNotEmpty(map)) {
			if (map.size() == 13) {
				this.gameid = gameid;
				this.file = file;
				if (StringUtils.isBlank(String.valueOf(map.get(1)))) {
					return;
				}
				this.userid = String.valueOf(((Double) Double.parseDouble(String.valueOf(map.get(3)))).longValue());
				this.username = String.valueOf(map.get(6));
				try {
					this.orderid = new BigDecimal(String.valueOf(map.get(12))).toPlainString();
				} catch (Exception e) {
					System.err.println(map.get(12));
					this.orderid = String.valueOf(map.get(12));
					e.printStackTrace();
				}
				try {
					this.logTime = DateUtils
					        .formate(DateUtils.parseDate(String.valueOf(map.get(10)), "MM/dd/yyyy HH:mm:ss",
					                "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
				} catch (ParseException e) {
					e.printStackTrace();
					return;
				}
				// this.ip = String.valueOf(map.get(7));
				this.amount = String.valueOf(map.get(8));
				// this.currency = "USD";// String.valueOf(map.get(11));
				this.channel = String.valueOf(map.get(0));
				if (StringUtils.isNoneBlank(this.file)) {
					try {
						this.serverid = this.gameid + '_' + 1;
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
		List<CommonRecharge> list = new ArrayList<>();
		File file = new File(filePath);
		for (File f : file.listFiles()) {
			if (f.getName().lastIndexOf("xlsx") > -1 || f.getName().lastIndexOf("xls") > -1) {
				list.addAll(ParseXlsxFile.read(filePath, gameid, Recharge三国辣么萌.class, charset, sheet));
			} else {
				list.addAll(ParseCsvFile.read(filePath, gameid, Recharge三国辣么萌.class, charset));
			}
		}
		return list;
	}

	public static void main(String[] args) {
		String[] filePaths = { "C:/Users/luke/Documents/logs/三国辣么萌" };
		for (String filePath : filePaths) {
			for (int i = 0; i < 1; i++) {
				List<? extends CommonRecharge> list = read(filePath, "4002001051", "UTF-8", i);
				InsertRechargeManager.save(list);
				// InsertRechargeManager.print(list);
			}
		}
	}
}
