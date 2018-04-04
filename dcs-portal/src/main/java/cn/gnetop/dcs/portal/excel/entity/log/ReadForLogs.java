package cn.gnetop.dcs.portal.excel.entity.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.gnetop.dcs.portal.excel.InsertRechargeManager;
import cn.gnetop.dcs.portal.excel.InsertUserLoginManager;
import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;
import cn.gnetop.dcs.portal.excel.userlogin.CommonUserLogin;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;
import net.sf.json.JSONObject;

public class ReadForLogs {

	private static final long serialVersionUID = -3355539330629548062L;

	public static void main(String[] args) {
		File file = new File("D:/My Document/sdk/logs/1258052077.log");
		read(file, "1258052077", "UTF-8");

	}

	private static void read(File file, String gameid, String charset) {
		List<CommonUserLogin> userList = new ArrayList<>();
		List<CommonRecharge> rechargeList = new ArrayList<>();
		try {
			if (StringUtils.isBlank(charset)) {
				charset = "UTF-8";
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
			String l = null;
			while ((l = br.readLine()) != null) {
				try {
					if (!l.startsWith("2")) {
						continue;
					}
					String[] ls = l.split("\\|");
					Date createDate = DateUtils.parseDate(ls[0], "yyyy-MM-dd HH:mm:ss,SSS");
					String createTime = DateUtils.formate(createDate);
					String type = ls[4];
					String req = ls[6];
					req = req.substring(req.indexOf('['), req.lastIndexOf(']') + 1);
					String reqx = req.replace('[', '{').replace("]", "\"}").replace("=", "=\"").replace(",", "\",");
					JSONObject jo = JSONObject.fromObject(reqx);
					String serverid = jo.getString("serverid");
					try {
						Integer server = Integer.parseInt(serverid.substring(serverid.indexOf('_') + 1));
						if (server > 10000) {
							continue;
						}
					} catch (Exception e) {
						continue;
					}
					if ("userLogin".equalsIgnoreCase(type)) {
						CommonUserLogin u = new CommonUserLogin();
						u.setUserid(jo.getString("userid"));
						if (jo.has("username")) {
							u.setUsername(jo.getString("username"));
						}
						u.setGameid(gameid);
						u.setNickname(jo.getString("nickname"));
						u.setIp(jo.getString("ip"));
						u.setServerid(serverid);
						long t = jo.getLong("createTime") * 1000;
						u.setLogTime(DateUtils.formate(new Date(t), "yyyy-MM-dd HH:mm:ss"));
						u.setMd5(MD5Utils.encrypt(u.toString()));
						u.setCreateTime(createTime);
						userList.add(u);
					} else if ("recharge".equalsIgnoreCase(type)) {
						CommonRecharge r = new CommonRecharge();
						r.setChannel(jo.getString("channel"));
						r.setOrderid(jo.getString("orderid"));
						r.setAmount(jo.getString("amount"));
						// if (!"null".equals(jo.getString("currency"))) {
						// r.setCurrency(jo.getString("currency"));
						// }
						r.setUserid(jo.getString("userid"));
						if (jo.has("username")) {
							r.setUsername(jo.getString("username"));
						}
						r.setIp(jo.getString("ip"));
						r.setGameid(gameid);
						r.setServerid(serverid);
						long t = jo.getLong("createTime") * 1000;
						r.setLogTime(DateUtils.formate(new Date(t), "yyyy-MM-dd HH:mm:ss"));
						r.setMd5(MD5Utils.encrypt(r.toString()));
						r.setCreateTime(createTime);
						rechargeList.add(r);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(l);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// InsertRechargeManager.print(rechargeList);
		// InsertUserLoginManager.print(userList);

		InsertRechargeManager.save(rechargeList);
		InsertUserLoginManager.save(userList);
	}
}
