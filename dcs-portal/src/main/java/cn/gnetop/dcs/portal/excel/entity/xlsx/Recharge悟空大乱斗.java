package cn.gnetop.dcs.portal.excel.entity.xlsx;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseXlsxFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.MapUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge悟空大乱斗 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge悟空大乱斗(String gameid, Map<String, Object> map, String file, Integer sheet) {
		if (MapUtils.isNotEmpty(map)) {
			if (map.size() == 9) {
				this.gameid = gameid;
				this.file = file;
				if (StringUtils.isBlank(String.valueOf(map.get(1)))) {
					return;
				}
				this.userid = String.valueOf(map.get(1));
				this.username = String.valueOf(map.get(7));
				try {
					// this.orderid = String.valueOf(map.get(4));
				} catch (Exception e) {
					System.err.println(map.get(11));
					e.printStackTrace();
				}
				try {
					this.logTime = DateUtils
					        .formate(DateUtils.parseDate(String.valueOf(map.get(8)), "MM/dd/yyyy HH:mm:ss",
					                "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// this.ip = String.valueOf(map.get(7));
				this.amount = String.valueOf(map.get(05));
				// this.currency = "USD";// String.valueOf(map.get(11));
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

	private static List<? extends CommonRecharge> read(String filePath, String gameid,
	        Class<? extends CommonRecharge> clz, String chartset, Integer sheet) {
		List<CommonRecharge> list = new ArrayList<>();
		File file = new File(filePath);
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				if (f.isDirectory()) {
					for (File fx : f.listFiles()) {
						if (fx.getName().indexOf("pay") > -1 || fx.getName().indexOf("Pay") > -1) {
							list.addAll(ParseXlsxFile.read(fx.getPath(), gameid, clz, chartset, sheet));
						}
					}
				} else if (f.getName().indexOf("充值") > -1) {
					list.addAll(ParseXlsxFile.read(filePath, gameid, clz, chartset, sheet));
				}
			}
		} else {
			list.addAll(ParseXlsxFile.read(filePath, gameid, clz, chartset, sheet));
		}
		return list;
	}

	public static void main(String[] args) {
		String[] filePaths = { "C:/Users/luke/Documents/logs/悟空大乱斗" };
		for (String filePath : filePaths) {
			for (int i = 0; i < 1; i++) {
				List<? extends CommonRecharge> list = read(filePath, "4002001045", Recharge悟空大乱斗.class, "UTF-8", i);
				InsertRechargeManager.save(list);
				// InsertRechargeManager.print(list);
			}
		}
	}
}
