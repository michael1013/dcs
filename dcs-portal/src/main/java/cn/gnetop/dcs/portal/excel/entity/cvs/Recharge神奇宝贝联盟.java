package cn.gnetop.dcs.portal.excel.entity.cvs;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseCsvFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge神奇宝贝联盟 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge神奇宝贝联盟(String gameid, String value, String file) {
		if (StringUtils.isNoneBlank(value)) {
			value = value.replaceAll("\\\"", "");
		}
		String[] column = value.split("\\s+");
		if (column.length == 18) {
			this.gameid = gameid;
			this.file = file;
			this.userid = column[5];
			this.username = column[6];
			this.orderid = column[9];
			try {
				this.logTime = DateUtils.formate(DateUtils.parseDate(column[15] + ' ' + column[16],
				        "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.amount = column[12];
			this.currency = column[11];
			this.channel = column[10];
			if (StringUtils.isNoneBlank(this.file)) {
				this.serverid = this.gameid + '_' + 1;
			}
			this.md5 = MD5Utils.encrypt(this.toString());
			this.createTime = DateUtils.getDateString();
			this.isValid = true;
		} else {
			this.isValid = false;
		}
	}

	public static void main(String[] args) {
		String[] filePaths = { "C:/Users/luke/Documents/logs/神奇宝贝联盟" };
		for (String filePath : filePaths) {
			List<? extends CommonRecharge> list = ParseCsvFile.read(filePath, "4002001041", Recharge神奇宝贝联盟.class,
			        "UTF-8");
			InsertRechargeManager.save(list);
			// InsertRechargeManager.print(list);
		}

	}
}
