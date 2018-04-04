package cn.gnetop.dcs.portal.excel.userlogin.xlsx;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.InsertUserLoginManager;
import cn.gnetop.dcs.portal.excel.ParseXlsxFile;
import cn.gnetop.dcs.portal.excel.userlogin.CommonUserLogin;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.MapUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class UserLogin极品三国 extends CommonUserLogin {

	private static final long serialVersionUID = -3355539330629548062L;

	public UserLogin极品三国(String gameid, Map<String, Object> map, String file, Integer sheet) throws ParseException {
		if (MapUtils.isNotEmpty(map)) {
			if (map.size() == 14) {
				this.gameid = gameid;
				this.file = file;
				if (StringUtils.isBlank(String.valueOf(map.get(8)))) {
					return;
				}
				this.userid = String.valueOf(map.get(8));
				// this.username = String.valueOf(map.get(3));
				this.nickname = String.valueOf(map.get(5));
				this.logTime = DateUtils.formate(DateUtils.parseDate(String.valueOf(map.get(4)), "MM/dd/yyyy HH:mm:ss",
				        "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
				this.ip = String.valueOf(map.get(7));
				if (StringUtils.isNoneBlank(this.file)) {
					try {
						this.serverid = this.gameid + '_' + 1;
					} catch (Exception e) {
						e.printStackTrace();
						return;
					}
				}
				this.md5 = MD5Utils.encrypt(this.toString());
				this.createTime = DateUtils.getDateString();
				this.isValid = true;
				return;
			}
		}
		this.isValid = false;
	}

	public static void main(String[] args) {
		String[] filePaths = { "D:/My Document/sdk/logs/20160930/文件夹/极品三国-20160930/登录.xlsx" };
		for (String filePath : filePaths) {
			for (int i = 0; i < 1; i++) {
				List<? extends CommonUserLogin> list = ParseXlsxFile.read(filePath, "4002001013", UserLogin极品三国.class,
				        "UTF-8", i);
				InsertUserLoginManager.save(list);
				// InsertUserLoginManager.print(list);
				// InsertUserLoginManager.saveFile(list, filePath);
			}
		}
	}
}
