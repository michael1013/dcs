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

public class Recharge口袋妖怪3DS extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge口袋妖怪3DS(String gameid, String value, String file) {
		if (StringUtils.isNoneBlank(value)) {
			value = value.replaceAll("\\\"", "");
		}
		String[] column = value.split("\\s+|,");
		if (column.length == 11) {
			this.gameid = gameid;
			this.file = file;
			this.userid = column[3];
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
//		for (String filePath : filePaths) {
//			List<CommonRecharge> list = new ArrayList<>();
//			list.addAll(ParseCsvFile.read(filePath, "4002001039", Recharge口袋妖怪3DS.class, "UTF-8"));
//			// InsertRechargeManager.save(list);
//			InsertRechargeManager.print(list);
//			// System.out.println(list.size());
//		}
	}
}
