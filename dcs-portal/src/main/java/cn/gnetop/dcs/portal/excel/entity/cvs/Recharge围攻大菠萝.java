package cn.gnetop.dcs.portal.excel.entity.cvs;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseCsvFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge围攻大菠萝 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge围攻大菠萝(String gameid, String value, String file) {
		if (StringUtils.isNoneBlank(value)) {
			value = value.replaceAll("\\\"", "");
		}
		String[] column = value.split(",");
		if (column.length == 18) {
			this.userid = column[1];
			this.username = column[7];
			this.orderid = column[15];
			try {
				this.logTime = DateUtils.formate(DateUtils.parseDate(column[9], "yyyy-MM-dd HH:mm:ss",
				        "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.ip = column[8];
			this.amount = column[11];
			this.currency = "CNY";
			this.channel = column[3];
			this.isValid = true;
			this.gameid = gameid;
			this.serverid = this.gameid + '_' + column[6];
			this.createTime = DateUtils.getDateString();
			this.md5 = MD5Utils.encrypt(this.toString());
		} else {
			this.isValid = false;
		}
	}

	public static void main(String[] args) {
		String[] filePaths = { "C:/Users/luke/Documents/logs/围攻大菠萝" };
		for (String filePath : filePaths) {
			List<? extends CommonRecharge> list = ParseCsvFile.read(filePath, "4002001031", Recharge围攻大菠萝.class);
			InsertRechargeManager.save(list);
			// InsertRechargeManager.print(list);
		}

	}
}
