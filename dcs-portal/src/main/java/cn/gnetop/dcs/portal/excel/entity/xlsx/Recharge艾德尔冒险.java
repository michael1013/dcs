package cn.gnetop.dcs.portal.excel.entity.xlsx;

import java.io.File;
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

public class Recharge艾德尔冒险 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge艾德尔冒险(String gameid, Map<String, Object> map, String file, Integer sheet) {
		if (MapUtils.isNotEmpty(map)) {
			if (map.size() == 15) {
				this.gameid = gameid;
				this.file = file;
				this.userid = String.valueOf(map.get(0));
				this.username = String.valueOf(map.get(1));
				this.orderid = String.valueOf(map.get(6));
				try {
					this.logTime = DateUtils.formate(DateUtils.parseDate(String.valueOf(map.get(9)),
					        "MM/dd/yyyy HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
				} catch (ParseException e) {
					System.err.println(this.file + ':' + map.get(9));
					e.printStackTrace();
				}
				this.ip = String.valueOf(map.get(8));
				this.amount = String.valueOf(map.get(7));
				this.currency = "USF";
				// this.channel = String.valueOf(map.get(5));
				if (StringUtils.isNoneBlank(this.file)) {
					try {
						this.serverid = this.gameid + '_' + 1;
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
		String[] filePaths = { "C:/Users/luke/Documents/logs/艾德尔冒险" };
		for (String filePath : filePaths) {
			for (File f : new File(filePath).listFiles()) {
				List<? extends CommonRecharge> list = ParseXlsxFile.read(f.getPath(), "4002001001", Recharge艾德尔冒险.class,
				        "UTF-8", 0);
				// InsertRechargeManager.save(list);
				InsertRechargeManager.print(list);
			}
		}

	}
}
