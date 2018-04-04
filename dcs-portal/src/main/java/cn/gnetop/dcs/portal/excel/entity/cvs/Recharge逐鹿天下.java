package cn.gnetop.dcs.portal.excel.entity.cvs;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseCsvFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge逐鹿天下 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge逐鹿天下(String gameid, String value, String file) {
		if (StringUtils.isNoneBlank(value)) {
			value = value.replaceAll("\\\"", "");
		}
		String[] column = value.split(",");
		if (column.length == 13) {
			this.gameid = gameid;
			this.file = file;
			this.userid = column[2];
			this.username = column[4];
			this.orderid = column[0];
			try {
				this.logTime = DateUtils.formate(DateUtils.parseDate(column[11], "yyyy-MM-dd HH:mm:ss",
				        "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// this.ip = column[8];
			this.amount = column[7];
			this.currency = "CNY";
			// this.channel = column[6];
			if (StringUtils.isNoneBlank(this.file)) {
				this.serverid = this.gameid + '_' + this.file.subSequence(0, this.file.indexOf('服'));
			}
			this.createTime = DateUtils.getDateString();
			this.md5 = MD5Utils.encrypt(this.toString());
			this.isValid = true;
		} else {
			this.isValid = false;
		}
	}

	public static void main(String[] args) {
		String[] filePaths = { "D:/My Document/sdk/logs/文件夹/逐鹿天下-20160911/充值日志" };
		for (String filePath : filePaths) {
			List<? extends CommonRecharge> list = ParseCsvFile.read(filePath, "4002001034", Recharge逐鹿天下.class, "GBK");
			InsertRechargeManager.save(list);
		}

	}
}
