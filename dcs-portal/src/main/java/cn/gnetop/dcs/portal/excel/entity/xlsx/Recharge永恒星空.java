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

public class Recharge永恒星空 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge永恒星空(String gameid, Map<String, Object> map, String file, Integer sheet) {
		if (MapUtils.isNotEmpty(map)) {
			if (map.size() == 13) {
				this.gameid = gameid;
				this.file = file;
				if (StringUtils.isBlank(String.valueOf(map.get(1)))) {
					return;
				}
				this.userid = String.valueOf(map.get(2));
				this.username = String.valueOf(map.get(1));
				try {
					this.orderid = new BigDecimal(Double.parseDouble(String.valueOf(map.get(0)))).toPlainString();
				} catch (Exception e) {
					this.orderid = String.valueOf(map.get(0));
				}
				try {
					this.logTime = DateUtils
					        .formate(DateUtils.parseDate(String.valueOf(map.get(11)), "MM/dd/yyyy HH:mm:ss",
					                "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
				} catch (ParseException e) {
					e.printStackTrace();
					return;
				}
				this.ip = String.valueOf(map.get(6));
				this.amount = String.valueOf(map.get(4));
				this.currency = "CNY";// String.valueOf(map.get(11));
				// this.channel = String.valueOf(map.get(8));
				if (StringUtils.isNoneBlank(this.file)) {
					try {
						this.serverid = this.gameid + '_' + String.valueOf(map.get(3)).replaceAll("\\D+", "");
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
		String[] filePaths = { "C:/Users/luke/Documents/logs/永恒星空" };
		for (String filePath : filePaths) {
			for (int i = 1; i < 4; i++) {
				List<? extends CommonRecharge> list = ParseXlsxFile.read(filePath, "4002001047", Recharge永恒星空.class,
				        "UTF-8", i);
				InsertRechargeManager.save(list);
				// InsertRechargeManager.print(list);
			}
		}
	}
}
