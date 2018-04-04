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

public class Recharge烈火一刀 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge烈火一刀(String gameid, String value, String file) {
		if (StringUtils.isNoneBlank(value)) {
			value = value.replaceAll("\\\"", "");
		}
		String[] column = value.split("\\s+|,");
		if (column.length == 9) {
			this.gameid = gameid;
			this.file = file;
			this.userid = column[7];
			this.username = column[0];
			this.orderid = column[2];
			try {
				this.logTime = DateUtils.formate(DateUtils.parseDate(column[3] + ' ' + column[4], "yyyy-MM-dd HH:mm:ss",
				        "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.ip = column[6];
			this.amount = column[5];
			// this.currency = "CNY";
			// this.channel = column[1];
			if (StringUtils.isNoneBlank(this.file)) {
				this.serverid = this.gameid + '_' + 1;
			}
			this.createTime = DateUtils.getDateString();
			this.md5 = MD5Utils.encrypt(this.toString());
			this.isValid = true;
		} else {
			this.isValid = false;
		}
	}

	public static void main(String[] args) {
		String[] filePaths = { "C:/Users/luke/Documents/logs/烈火一刀/充值日志" };
		for (String filePath : filePaths) {
			for (File file : new File(filePath).listFiles()) {
				if (file.isDirectory()) {
					for (File f : file.listFiles()) {
						List<CommonRecharge> list = new ArrayList<>();
						list.addAll(ParseCsvFile.read(f.getPath(), "4002001040", Recharge烈火一刀.class, "UTF-8"));
						// InsertRechargeManager.save(list);
						// InsertRechargeManager.print(list);
						// System.out.println(list.size());
					}
				}
			}
		}
	}
}
