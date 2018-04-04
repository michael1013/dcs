package cn.gnetop.dcs.portal.excel.entity.cvs;

import java.text.ParseException;
import java.util.List;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseCsvFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge海盗信条 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge海盗信条(String gameid, String value, String file) {
		if (value.indexOf('"') > -1) {
			value = value.replaceAll("\"", "");
		}
		String[] column = value.split(",");
		if (column.length == 5) {
			this.userid = column[1];
			try {
				this.logTime = DateUtils.formate(DateUtils.parseDate(column[4], "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.amount = column[3];
			this.isValid = true;
			this.gameid = gameid;
			this.serverid = this.gameid + '_' + column[0];
			this.createTime = DateUtils.getDateString();
			this.md5 = MD5Utils.encrypt(this.toString());
		} else {
			this.isValid = false;
		}
	}

	public static void main(String[] args) {
		String[] filePaths = new String[] { "D:/My Document/sdk/logs/文件夹/海盗信条（北美）-20160911/1/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条（北美）-20160911/4/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条（北美）-20160911/7/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条（北美）-20160911/8/充值信息.csv" };
		for (String filePath : filePaths) {
			List<? extends CommonRecharge> list = ParseCsvFile.read(filePath, "4002001009", Recharge海盗信条.class);
			InsertRechargeManager.save(list);
		}
		filePaths = new String[] { "D:/My Document/sdk/logs/文件夹/海盗信条(越南）-20160911/1/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条(越南）-20160911/2/充值信息.csv" };
		for (String filePath : filePaths) {
			List<? extends CommonRecharge> list = ParseCsvFile.read(filePath, "4002001011", Recharge海盗信条.class);
			InsertRechargeManager.save(list);
		}
		filePaths = new String[] { "D:/My Document/sdk/logs/文件夹/海盗信条（国内）-20160911/1/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条（国内）-20160911/1/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条（国内）-20160911/5/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条（国内）-20160911/9/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条（国内）-20160911/17/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条（国内）-20160911/21/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条（国内）-20160911/23/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条（国内）-20160911/24/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条（国内）-20160911/25/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条（国内）-20160911/26/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条（国内）-20160911/27/充值信息.csv",
		        "D:/My Document/sdk/logs/文件夹/海盗信条（国内）-20160911/28/充值信息.csv" };
		for (String filePath : filePaths) {
			List<? extends CommonRecharge> list = ParseCsvFile.read(filePath, "4002001010", Recharge海盗信条.class);
			InsertRechargeManager.save(list);
		}
	}
}
