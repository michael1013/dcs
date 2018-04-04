package cn.gnetop.dcs.portal.excel.entity.xlsx;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.ExcelReaderUtil;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.MapUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class Recharge壮绝大战争 extends CommonRecharge {

	private static final long serialVersionUID = -3355539330629548062L;

	public Recharge壮绝大战争(String gameid, Map<Integer, Object> map, String file, Integer sheet) {
		if (MapUtils.isNotEmpty(map)) {
			if (map.size() == 9) {
				this.gameid = gameid;
				this.file = file;
				if (StringUtils.isBlank(String.valueOf(map.get(1)))) {
					return;
				}
				this.userid = new BigDecimal(String.valueOf(map.get(2))).toPlainString();
				this.username = String.valueOf(map.get(3));
				try {
					this.orderid = new BigDecimal(String.valueOf(map.get(4))).toPlainString();
				} catch (Exception e) {
					System.err.println(map.get(4));
					e.printStackTrace();
				}
				try {
					this.logTime = DateUtils
					        .formate(DateUtils.parseDate(String.valueOf(map.get(8)), "MM/dd/yyyy HH:mm:ss",
					                "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
				} catch (ParseException e) {
					e.printStackTrace();
					return;
				}
				this.ip = String.valueOf(map.get(7));
				this.amount = String.valueOf(map.get(0));
				this.currency = "USD";// String.valueOf(map.get(11));
				this.channel = String.valueOf(map.get(6));
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
		String[] filePaths = { "C:/Users/luke/Documents/logs/壮绝大战争 - outofmemory" };
		for (String filePath : filePaths) {
			for (int i = 0; i < 13; i++) {
				read(filePath, "4002001035", Recharge壮绝大战争.class, "UTF-8", i);
			}
		}
	}

	private static BufferedWriter bw = null;
	static {
		File outputFile = new File("D:/My Document/sdk/logs/txt-壮绝大战争/recharge.log");
		try {
			bw = new BufferedWriter(new FileWriter(outputFile, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<? extends CommonRecharge> read(String filePath, String gameid,
	        Class<? extends CommonRecharge> clz, String charset, Integer sheet) {
		List<CommonRecharge> list = new ArrayList<>();
		File file = new File(filePath);
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				readXlsx(f, gameid, clz, charset, sheet);
			}
		} else {
			readXlsx(file, gameid, clz, charset, sheet);
		}
		return list;
	}

	public static void readXlsx(File file, String gameid, Class<? extends CommonRecharge> clz, String charset,
	        Integer sheet) {
		try {
			InputStream is = new FileInputStream(file);

			List<Map<Integer, Object>> mapList = ExcelReaderUtil.readExcelContent(is, file.getName(), sheet);
			for (Map<Integer, Object> map : mapList) {
				try {
					CommonRecharge r = new Recharge壮绝大战争(gameid, map, file.getName(), sheet);
					if (r.isValid()) {
						bw.write(r.getSql() + '\n');
					}
				} catch (Exception e) {
					System.err.println(file.getName());
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.err.println(file.getName());
			e.printStackTrace();
		} finally {
			try {
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
