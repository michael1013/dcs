package cn.gnetop.dcs.portal.excel.entity.xlsx;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseXlsxFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.MapUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge合金三国 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge合金三国(String gameid, Map<String, Object> map, String file, Integer sheet) {
		if (MapUtils.isNotEmpty(map)) {
			if (map.size() == 4) {
				this.gameid = gameid;
				this.file = file;
				if (StringUtils.isBlank(String.valueOf(map.get(0)))) {
					return;
				}
				try {
					this.userid = String.valueOf(((Double) Double.parseDouble(String.valueOf(map.get(0)))).longValue());
				} catch (Exception e) {
					System.err.println(map.get(0));
					e.printStackTrace();
					return;
				}
				// this.username = String.valueOf(map.get(3));
				// this.orderid = String.valueOf(map.get(4));
				try {
					this.logTime = DateUtils.formate(
					        new Date(((Double) Double.parseDouble(String.valueOf(map.get(3)))).longValue() * 1000),
					        "yyyy-MM-dd HH:mm:ss");
				} catch (Exception e) {
					e.printStackTrace();
				}
				// this.ip = String.valueOf(map.get(7));
				this.amount = String.valueOf(map.get(2));
				this.currency = "CNF";// String.valueOf(map.get(11));
				// this.channel = String.valueOf(map.get(6));
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
		String[] filePaths = { "C:/Users/luke/Documents/logs/合金三国" };
		for (String filePath : filePaths) {
			for (int i = 0; i < 1; i++) {
				List<? extends CommonRecharge> list = ParseXlsxFile.read(filePath, "4002001037", Recharge合金三国.class,
				        "UTF-8", i);
				InsertRechargeManager.save(list);
				// InsertRechargeManager.print(list);
			}
		}
	}
}
