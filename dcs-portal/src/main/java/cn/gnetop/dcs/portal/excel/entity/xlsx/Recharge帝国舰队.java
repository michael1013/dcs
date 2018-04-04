package cn.gnetop.dcs.portal.excel.entity.xlsx;

import java.math.BigDecimal;
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

public class Recharge帝国舰队 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge帝国舰队(String gameid, Map<String, Object> map, String file, Integer sheet) {
		if (MapUtils.isNotEmpty(map)) {
			if (map.size() == 9) {
				this.gameid = gameid;
				this.file = file;
				if (StringUtils.isEmpty(String.valueOf(map.get(2)))) {
					return;
				}
				if (map.get(2) instanceof Double) {
					this.userid = BigDecimal.valueOf((Double) map.get(2)).toPlainString();
				} else {
					this.userid = String.valueOf(map.get(2));
				}
				this.username = String.valueOf(map.get(3));
				if (map.get(4) instanceof Double) {
					this.orderid = BigDecimal.valueOf((Double) map.get(4)).toPlainString();
				} else {
					this.orderid = String.valueOf(map.get(4));
				}
				try {
					this.logTime = DateUtils.formate(DateUtils.parseDate(String.valueOf(map.get(8)),
					        "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
				} catch (ParseException e) {
					System.err.println(file + ":" + map.get(8));
					e.printStackTrace();
					return;
				}
				this.ip = String.valueOf(map.get(7));
				this.amount = String.valueOf(map.get(0));
				this.currency = "USD";
				this.channel = String.valueOf(map.get(6));
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
		this.isValid = false;
	}

	public static void main(String[] args) {
		long t = 0;
		String[] filePaths = { "C:/Users/luke/Documents/logs/帝国舰队东南亚 - log" };
		for (String filePath : filePaths) {
			for (int i = 0; i < 4; i++) {
				List<? extends CommonRecharge> list = ParseXlsxFile.read(filePath, "4002001004", Recharge帝国舰队.class,
				        "UTF-8", i);
				// InsertRechargeManager.save(list);
				// InsertRechargeManager.print(list);
				InsertRechargeManager.saveFile(list, filePath);
				t += list.size();
			}
		}
		System.out.println(t);
		filePaths = new String[] { "C:/Users/luke/Documents/logs/帝国舰队欧美 - log" };
		for (String filePath : filePaths) {
			for (int i = 0; i < 7; i++) {
				List<? extends CommonRecharge> list = ParseXlsxFile.read(filePath, "4002001005", Recharge帝国舰队.class,
				        "UTF-8", i);
				// InsertRechargeManager.save(list);
				// InsertRechargeManager.print(list);
				InsertRechargeManager.saveFile(list, filePath);
				t += list.size();
			}
		}

		System.out.println(t);
	}
}
