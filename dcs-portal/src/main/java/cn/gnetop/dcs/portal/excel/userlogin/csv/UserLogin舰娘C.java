package cn.gnetop.dcs.portal.excel.userlogin.csv;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.InsertUserLoginManager;
import cn.gnetop.dcs.portal.excel.ParseCsvFile;
import cn.gnetop.dcs.portal.excel.userlogin.CommonUserLogin;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class UserLogin舰娘C extends CommonUserLogin {

	private static final long serialVersionUID = -3355539330629548062L;

	public UserLogin舰娘C(String gameid, String value, String file) throws ParseException {
		if (StringUtils.isNoneBlank(value)) {
			value = value.replaceAll("\\\"", "");
		}
		String[] column = value.split(",");
		if (column.length == 10) {
			this.gameid = gameid;
			this.file = file;
			this.userid = column[3];
			// this.username = column[6];
			// this.nickname = column[9];
			this.logTime = DateUtils.formate(DateUtils.parseDate(column[5], "yyyy-MM-dd HH:mm:ss",
			        "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
			this.ip = column[6];
			if (StringUtils.isNoneBlank(this.file)) {
				this.serverid = this.gameid + '_' + column[1];
			}
			this.createTime = DateUtils.getDateString();
			this.md5 = MD5Utils.encrypt(this.toString());
			this.isValid = true;
		} else {
			this.isValid = false;
		}
	}

	public static void main(String[] args) {
		String[] filePaths = { "D:/My Document/sdk/logs/20160930/文件夹/舰娘C-20160930/188_login1.txt" };
		long t = 0l;
		for (String filePath : filePaths) {
			for (int i = 0; i < 1; i++) {
				List<? extends CommonUserLogin> list = ParseCsvFile.read(filePath, "4002001014", UserLogin舰娘C.class,
				        "UTF-8");
				t += list.size();
				// InsertUserLoginManager.save(list);
				// InsertUserLoginManager.print(list); 
				InsertUserLoginManager.saveFile(list, filePath);
			}
		}
		System.out.println("total:" + t);
	}
}
