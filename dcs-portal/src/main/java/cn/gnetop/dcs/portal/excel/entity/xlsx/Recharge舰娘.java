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

public class Recharge舰娘 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge舰娘(String gameid, Map<String, Object> map, String file, Integer sheet) {
		if (MapUtils.isNotEmpty(map)) {
			if (map.size() == 12) {
				this.gameid = gameid;
				this.file = file;
				this.userid = String.valueOf(((Double) map.get(11)).longValue());
				this.username = String.valueOf(map.get(0));
				this.orderid = String.valueOf(map.get(5));
				try {
					this.logTime = DateUtils.formate(DateUtils.parseDate(String.valueOf(map.get(1)),
					        "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// this.ip = String.valueOf(map.get(7));
				this.amount = String.valueOf(map.get(10));
				this.currency = "CNJ";
				this.channel = String.valueOf(map.get(3));
				if (StringUtils.isNoneBlank(this.file)) {
					try {
						this.serverid = this.gameid + '_' + map.get(7);
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
		String[] filePaths = { "D:/My Document/sdk/logs/文件夹/舰娘C-港澳台-20160801-0911/舰娘即拓_港台_0801-0911.xlsx" };
		for (String filePath : filePaths) {
			for (int i = 0; i < 2; i++) {
				List<? extends CommonRecharge> list = ParseXlsxFile.read(filePath, "4002001014", Recharge舰娘.class,
				        "UTF-8", i);
				InsertRechargeManager.save(list);
				// InsertRechargeManager.print(list);
			}
		}
	}
}
