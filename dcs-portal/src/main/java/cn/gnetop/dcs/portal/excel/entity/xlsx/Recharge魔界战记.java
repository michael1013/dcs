package cn.gnetop.dcs.portal.excel.entity.xlsx;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseXlsxFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.MapUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge魔界战记 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge魔界战记(String gameid, Map<String, Object> map, String file, Integer sheet) {
		if (MapUtils.isNotEmpty(map)) {
			if (map.size() == 16) {
				this.gameid = gameid;
				this.file = file;
				this.userid = String.valueOf(map.get(1));
				this.username = String.valueOf(map.get(2));
				this.orderid = String.valueOf(((Double) map.get(9)).longValue());
				try {
					this.logTime = DateUtils
					        .formate(DateUtils.parseDate(String.valueOf(map.get(6)), "MM/dd/yyyy HH:mm:ss",
					                "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				this.ip = String.valueOf(map.get(14));
				this.amount = String.valueOf(map.get(12));
				this.currency = String.valueOf(map.get(11));
				this.channel = String.valueOf(map.get(4));
				if (StringUtils.isNoneBlank(this.file)) {
					try {
						this.serverid = this.gameid + '_' + (sheet + 1);
					} catch (Exception e) {
						e.printStackTrace();
						return;
					}
				}
				this.createTime = DateUtils.getDateString();
				this.md5 = MD5Utils.encrypt(this.toString());
				this.isValid = true;
				return;
			} else if (map.size() == 1) {
				String[] columns = String.valueOf(map.get(0)).split("\\|");
				if (columns.length == 16) {
					this.gameid = gameid;
					this.file = file;
					this.userid = columns[1].trim();
					this.username = columns[2].trim();
					this.orderid = columns[9].trim();
					try {
						this.logTime = DateUtils.formate(DateUtils.parseDate(columns[7].trim(), "MM/dd/yyyy HH:mm:ss",
						        "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					this.ip = columns[14].trim();
					this.amount = columns[12].trim();
					this.currency = columns[11].trim();
					this.channel = columns[4].trim();
					if (StringUtils.isNoneBlank(this.file)) {
						try {
							this.serverid = this.gameid + '_' + (sheet + 1);
						} catch (Exception e) {
							e.printStackTrace();
							return;
						}
					}
					this.createTime = DateUtils.getDateString();
					this.md5 = MD5Utils.encrypt(this.toString());
					this.isValid = true;
					return;
				}
			}
		}
		this.isValid = false;
	}

	public static void main(String[] args) {
		String[] filePaths = { "C:/Users/luke/Documents/logs/魔界战记" };
		for (String filePath : filePaths) {
			for (int i = 0; i < 5; i++) {
				List<? extends CommonRecharge> list = ParseXlsxFile.read(filePath, "4002001019", Recharge魔界战记.class,
				        "UTF-8", i);
				InsertRechargeManager.save(list);
				// InsertRechargeManager.print(list);
			}
		}
	}
}
