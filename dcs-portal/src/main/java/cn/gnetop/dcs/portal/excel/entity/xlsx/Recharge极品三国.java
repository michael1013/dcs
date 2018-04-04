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

public class Recharge极品三国 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge极品三国(String gameid, Map<String, Object> map, String file, Integer sheet) {
		if (MapUtils.isNotEmpty(map)) {
			if (map.size() == 11) {
				this.gameid = gameid;
				this.file = file;
				if (StringUtils.isBlank(String.valueOf(map.get(8)))) {
					return;
				}
				this.userid = String.valueOf(map.get(8));
				// this.username = String.valueOf(map.get(3));
				try {
					this.orderid = String.valueOf(map.get(4));
				} catch (Exception e) {
					System.err.println(map.get(11));
					e.printStackTrace();
				}
				try {
					this.logTime = DateUtils
					        .formate(DateUtils.parseDate(String.valueOf(map.get(5)), "MM/dd/yyyy HH:mm:ss",
					                "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// this.ip = String.valueOf(map.get(7));
				this.amount = String.valueOf(map.get(6));
				// this.currency = "USD";// String.valueOf(map.get(11));
				this.channel = String.valueOf(map.get(1));
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
		String[] filePaths = { "C:/Users/luke/Documents/logs/极品三国" };
		for (String filePath : filePaths) {
			for (int i = 0; i < 1; i++) {
				List<? extends CommonRecharge> list = ParseXlsxFile.read(filePath, "4002001013", Recharge极品三国.class,
				        "UTF-8", i);
				InsertRechargeManager.save(list);
				// InsertRechargeManager.print(list);
			}
		}
	}
}
