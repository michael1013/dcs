package cn.gnetop.dcs.portal.excel.entity.cvs;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseCsvFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge绝世唐门 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge绝世唐门(String gameid, String value, String file) {
		if (StringUtils.isNoneBlank(value)) {
			value = value.replaceAll("\\\"", "");
		}
		String[] column = value.split("\\s+|,");
		if (column.length == 9) {
			this.gameid = gameid;
			this.file = file;
			this.userid = column[3];
			this.username = column[2];
			this.orderid = column[6];
			try {
				this.logTime = DateUtils.formate(DateUtils.parseDate(column[7] + ' ' + column[8], "yyyy-MM-dd HH:mm:ss",
				        "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// this.ip = column[8];
			this.amount = column[5];
			this.currency = "CNY";
			this.channel = column[4];
			if (StringUtils.isNoneBlank(this.file)) {
				this.serverid = this.gameid + '_' + column[1].substring(column[1].lastIndexOf('_') + 1);
			}
			this.createTime = DateUtils.getDateString();
			this.md5 = MD5Utils.encrypt(this.toString());
			this.isValid = true;
		} else {
			this.isValid = false;
		}
	}

	public static void main(String[] args) {
		String[] filePaths = { "C:/Users/luke/Documents/logs/绝世唐门" };
		for (String filePath : filePaths) {
			List<? extends CommonRecharge> list = ParseCsvFile.read(filePath, "4002001038", Recharge绝世唐门.class,
			        "UTF-8");
			InsertRechargeManager.save(list);
			// InsertRechargeManager.print(list);
		}

	}
}
