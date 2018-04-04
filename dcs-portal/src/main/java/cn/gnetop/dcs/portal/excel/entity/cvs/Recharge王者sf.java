package cn.gnetop.dcs.portal.excel.entity.cvs;

import java.text.ParseException;
import java.util.List;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseCsvFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge王者sf extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge王者sf(String gameid, String value, String file) {
		String values = value.replaceAll("\"", "");
		String[] column = values.split("\\s+|,");
		if (column.length == 9) {
			this.userid = column[7];
			this.username = column[0];
			this.orderid = column[1];
			try {
				this.logTime = DateUtils.formate(DateUtils.parseDate(column[3] + ' ' + column[4], "yyyy-MM-dd HH:mm:ss",
				        "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.amount = column[5];
			this.currency = "CNY";
			// this.channel = column[16];
			this.ip = column[6];
			this.isValid = true;
			this.gameid = gameid;
			this.serverid = this.gameid + '_' + 1;
			this.createTime = DateUtils.getDateString();
			this.md5 = MD5Utils.encrypt(this.toString());
		} else {
			this.isValid = false;
		}
	}

	public static void main(String[] args) {
		String[] filePaths = { "C:/Users/luke/Documents/logs/王者sf" };
		for (String filePath : filePaths) {
			List<? extends CommonRecharge> list = ParseCsvFile.read(filePath, "4002001044", Recharge王者sf.class);
			InsertRechargeManager.save(list);
			// InsertRechargeManager.print(list);
		}

	}
}
