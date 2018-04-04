package cn.gnetop.dcs.portal.excel.entity.cvs;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseCsvFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge开炮吧坦克 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge开炮吧坦克(String gameid, String value, String file) {
		if (StringUtils.isNoneBlank(value)) {
			value = value.replaceAll("\\\"", "");
		}
		String[] column = value.split("\\s+|,");
		if (column.length == 7) {
			this.gameid = gameid;
			this.file = file;
			this.userid = column[0];
			// this.username = column[4];
			this.orderid = column[2];
			try {
				this.logTime = DateUtils.formate(DateUtils.parseDate(column[4] + ' ' + column[5], "yyyy-MM-dd HH:mm:ss",
				        "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.ip = column[6];
			this.amount = column[3];
			if (this.gameid.equals("7002000")) {
				this.currency = "JPY";
			}
			this.channel = column[1];
			if (StringUtils.isNoneBlank(this.file)) {
				this.serverid = this.gameid + '_' + 0;
			}
			this.createTime = DateUtils.getDateString();
			this.md5 = MD5Utils.encrypt(this.toString());
			this.isValid = true;
		} else {
			this.isValid = false;
		}
	}

	public static void main(String[] args) {
		String[] filePaths = { "C:/Users/luke/Documents/logs/开炮吧坦克" };
		for (String filePath : filePaths) {
			List<CommonRecharge> list = new ArrayList<>();
			for (File f : new File(filePath).listFiles()) {
				if (f.isDirectory()) {
					for (File fx : f.listFiles()) {
						for (File file : fx.listFiles()) {
							if (file.getName().indexOf("paylog") > -1) {
								list.addAll(ParseCsvFile.read(file.getPath(), "7008000", Recharge开炮吧坦克.class, "UTF-8"));
							}
						}
					}
				}
			}
			InsertRechargeManager.save(list);
			// InsertRechargeManager.print(list);
			// System.out.println(list.size());
		}

		filePaths = new String[] { "C:/Users/luke/Documents/logs/开炮吧坦克日本" };
		for (String filePath : filePaths) {
			List<CommonRecharge> list = new ArrayList<>();
			for (File f : new File(filePath).listFiles()) {
				if (f.isDirectory()) {
					for (File fx : f.listFiles()) {
						for (File file : fx.listFiles()) {
							if (file.getName().indexOf("paylog") > -1) {
								list.addAll(ParseCsvFile.read(file.getPath(), "7002000", Recharge开炮吧坦克.class, "UTF-8"));
							}
						}
					}
				}
			}
			InsertRechargeManager.save(list);
			// InsertRechargeManager.print(list);
			// System.out.println(list.size());
		}
	}
}
