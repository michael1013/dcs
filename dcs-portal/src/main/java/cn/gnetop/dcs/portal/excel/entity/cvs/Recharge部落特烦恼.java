package cn.gnetop.dcs.portal.excel.entity.cvs;

import java.text.ParseException;
import java.util.List;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseCsvFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge部落特烦恼 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge部落特烦恼(String gameid, String value, String file) {
		String[] column = value.split(",");
		if (column.length == 5) {
			this.userid = column[0];
			this.channel = column[1];
			this.orderid = column[2];
			try {
				this.logTime = DateUtils.formate(DateUtils.parseDate(column[3], "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.amount = column[4];
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
		String[] filePaths = { "C:/Users/luke/Documents/logs/部落特烦恼csv" };
		for (String filePath : filePaths) {
			List<? extends CommonRecharge> list = ParseCsvFile.read(filePath, "4002001003", Recharge部落特烦恼.class);
			InsertRechargeManager.save(list);
			// InsertRechargeManager.print(list);
		}
	}
}
