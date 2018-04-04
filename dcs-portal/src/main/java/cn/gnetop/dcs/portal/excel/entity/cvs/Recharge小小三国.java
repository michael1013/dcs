package cn.gnetop.dcs.portal.excel.entity.cvs;

import java.text.ParseException;
import java.util.List;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseCsvFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge小小三国 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge小小三国(String gameid, String value, String file) {
		String values = value.replaceAll("\"", "");
		String[] column = values.split(",");
		if (column.length == 23) {
			this.userid = column[1];
			// this.username = column[0];
			this.orderid = column[12].substring(column[12].lastIndexOf(':'));
			try {
				this.logTime = DateUtils.formate(DateUtils.parseDate(column[8], "yyyy-MM-dd HH:mm:ss",
				        "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.amount = column[4];
			this.currency = "CNF";
			// this.channel = column[16];
			// this.ip = column[6];
			this.isValid = true;
			this.gameid = gameid;
			this.serverid = this.gameid + '_' + column[2];
			this.createTime = DateUtils.getDateString();
			this.md5 = MD5Utils.encrypt(this.toString());
		} else {
			this.isValid = false;
		}
	}

	public static void main(String[] args) {
		String[] filePaths = { "C:/Users/luke/Documents/logs/小小三国" };
		for (String filePath : filePaths) {
			List<? extends CommonRecharge> list = ParseCsvFile.read(filePath, "4002001046", Recharge小小三国.class);
			InsertRechargeManager.save(list);
			// InsertRechargeManager.print(list);
		}

	}
}
