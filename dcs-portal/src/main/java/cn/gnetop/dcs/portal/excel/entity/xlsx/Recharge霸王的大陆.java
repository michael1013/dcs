package cn.gnetop.dcs.portal.excel.entity.xlsx;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.ParseCsvFile;
import cn.gnetop.dcs.portal.excel.ParseXlsxFile;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.dcs.portal.excel.entity.cvs.Recharge霸王的大陆csv;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.MapUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge霸王的大陆 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge霸王的大陆(String gameid, Map<String, Object> map, String file, Integer sheet) {
		if (MapUtils.isNotEmpty(map)) {
			if (map.size() == 23) {
				this.gameid = gameid;
				this.file = file;
				this.userid = String.valueOf(map.get(1));
				this.username = String.valueOf(map.get(2));
				this.orderid = String.valueOf(map.get(16));
				try {
					this.logTime = DateUtils
					        .formate(DateUtils.parseDate(String.valueOf(map.get(13)) + ' ' + map.get(14),
					                "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				this.ip = String.valueOf(map.get(19));
				this.amount = String.valueOf(map.get(6));
				this.currency = "CNY";
				this.channel = String.valueOf(map.get(5));
				if (StringUtils.isNoneBlank(this.file)) {
					try {
						this.serverid = this.gameid + '_' + String.valueOf(map.get(3)).substring(
						        String.valueOf(map.get(3)).indexOf('下') + 1, String.valueOf(map.get(3)).indexOf('区'));
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

	private static List<? extends CommonRecharge> read(String filePath, String gameid,
	        Class<? extends CommonRecharge> clz, String charset, Integer sheet) {
		List<CommonRecharge> list = new ArrayList<>();
		File file = new File(filePath);
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				if (f.getName().endsWith(".xlsx") || f.getName().endsWith(".xls")) {
					list.addAll(ParseXlsxFile.read(filePath, gameid, clz, charset, sheet));
				} else {
					list.addAll(ParseCsvFile.read(filePath, gameid, Recharge霸王的大陆csv.class, charset));
				}
			}
		} else {
			if (file.getName().endsWith(".xlsx") || file.getName().endsWith(".xls")) {
				list.addAll(ParseXlsxFile.read(filePath, gameid, clz, charset, sheet));
			} else {
				list.addAll(ParseCsvFile.read(filePath, gameid, Recharge霸王的大陆csv.class, charset));
			}
		}
		return list;
	}

	public static void main(String[] args) {
		String[] filePaths = { "C:/Users/luke/Documents/logs/霸王的大陆csv" };
		for (String filePath : filePaths) {
			List<? extends CommonRecharge> list = read(filePath, "4002001002", Recharge霸王的大陆.class, "UTF-8", 0);
			InsertRechargeManager.save(list);
			// InsertRechargeManager.print(list);
		}
	}
}