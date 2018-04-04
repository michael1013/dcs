package cn.gnetop.dcs.console.service.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.gnetop.dcs.console.system.linster.BeanUtils;
import cn.gnetop.dcs.console.util.FileLog;
import cn.gnetop.dcs.dao.CoinConsumeDao;
import cn.gnetop.dcs.dao.CoinIncreaseDao;
import cn.gnetop.dcs.dao.FileHistoryDao;
import cn.gnetop.dcs.dao.PropertyConsumeDao;
import cn.gnetop.dcs.dao.PropertyIncreaseDao;
import cn.gnetop.dcs.dao.RechargeDao;
import cn.gnetop.dcs.dao.UserLoginDao;
import cn.gnetop.dcs.dao.base.BaseDao;
import cn.gnetop.dcs.dao.base.DataEntity;
import cn.gnetop.dcs.dao.schema.CoinConsume;
import cn.gnetop.dcs.dao.schema.CoinIncrease;
import cn.gnetop.dcs.dao.schema.FileHistory;
import cn.gnetop.dcs.dao.schema.PropertyConsume;
import cn.gnetop.dcs.dao.schema.PropertyIncrease;
import cn.gnetop.dcs.dao.schema.Recharge;
import cn.gnetop.dcs.dao.schema.UserLogin;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.StringUtils;
import cn.gnetop.pde.foundation.config.GlobalConfig;
import cn.gnetop.pde.foundation.encryte.MD5Utils;

public class FileAnalyzeThread implements Runnable {

	public static final String PATH_ROOT = GlobalConfig.getConfig("log.file.root", "/var/logs/");

	private static final String[] PATH_PATHS = GlobalConfig.getConfig("log.file.path", "yyyyMMdd/|yyyy/MM/dd/")
	        .split("\\|");

	private String year;
	private String month;
	private String day;
	private String account;
	private String dateTime;
	private String filePath;

	private CoinConsumeDao coinConsumeDao = BeanUtils.getBean(CoinConsumeDao.class);
	private CoinIncreaseDao coinIncreaseDao = BeanUtils.getBean(CoinIncreaseDao.class);
	private PropertyConsumeDao propertyConsumeDao = BeanUtils.getBean(PropertyConsumeDao.class);
	private PropertyIncreaseDao propertyIncreaseDao = BeanUtils.getBean(PropertyIncreaseDao.class);
	private RechargeDao rechargeDao = BeanUtils.getBean(RechargeDao.class);
	private UserLoginDao userLoginDao = BeanUtils.getBean(UserLoginDao.class);
	private FileHistoryDao fileHistoryDao = BeanUtils.getBean(FileHistoryDao.class);

	public FileAnalyzeThread(Date date, String account) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		this.year = String.valueOf(cal.get(Calendar.YEAR));
		int m = cal.get(Calendar.MONTH) + 1;
		this.month = m < 10 ? "0" + m : String.valueOf(m);
		int d = cal.get(Calendar.DATE);
		this.day = d < 10 ? "0" + d : String.valueOf(d);
		this.dateTime = this.year + '-' + this.month + '-' + this.day;
		this.account = account;
		this.filePath = this.getFilePath(account);
	}

	public FileAnalyzeThread(String path) {
		this.filePath = path;
		path = path.replaceAll("\\\\", "/");
		String pathTmp = path.substring(path.indexOf(PATH_ROOT) + PATH_ROOT.length());
		this.account = pathTmp.substring(0, pathTmp.indexOf('/'));
		this.dateTime = pathTmp.substring(pathTmp.indexOf('/') + 1, pathTmp.lastIndexOf('/'));
	}

	@Override
	public void run() {
		if (StringUtils.isNotBlank(filePath)) {
			this.analyzeFile(filePath);
		}
	}

	private void analyzeFile(String filePath) {
		File file = new File(filePath);
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				readFile(f);
			}
		} else {
			readFile(file);
		}
	}

	private void readFile(File f) {
		try {
			if (f.getName().startsWith("coinConsume.")) {
				readCoinConsumeFile(f);
			} else if (f.getName().startsWith("coinIncrease.")) {
				readCoinIncreaseFile(f);
			} else if (f.getName().startsWith("propertyIncrease.")) {
				readPropertyIncreaseFile(f);
			} else if (f.getName().startsWith("propertyConsume.")) {
				readPropertyConsumeFile(f);
			} else if (f.getName().startsWith("recharge.")) {
				readRechargeFile(f);
			} else if (f.getName().startsWith("userLogin.")) {
				readUserLoginFile(f);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readCoinConsumeFile(File file) throws IOException {
		if (null != file && file.exists()) {
			BufferedReader br = this.getBurreredReader(file);
			String l = null;
			List<CoinConsume> list = new ArrayList<>();
			int lineCount = 1;
			while ((l = br.readLine()) != null) {
				String md5 = MD5Utils.encrypt(l);
				String[] line = l.split("\\t");
				CoinConsume o = new CoinConsume();
				if (line.length == 7) {
					String serverid = this.parseServerid(line[1], file.getPath(), lineCount);
					o.setUserid(line[0]);
					o.setServerid(serverid);
					o.setType(line[2]);
					o.setName(line[3]);
					o.setConsume(line[4]);
					o.setAmount(line[5]);
					o.setCreateTime(line[6]);
					o.setLogTime(DateUtils.getDateString());
					o.setMd5(md5);
				} else if (line.length == 8) {
					String serverid = this.parseServerid(line[2], file.getPath(), lineCount);
					o.setUserid(line[0]);
					o.setUsername(line[1]);
					o.setServerid(serverid);
					o.setType(line[3]);
					o.setName(line[4]);
					o.setConsume(line[5]);
					o.setAmount(line[6]);
					o.setCreateTime(line[7]);
					o.setLogTime(DateUtils.getDateString());
					o.setMd5(md5);
				} else {
					FileLog.error(
					        "logPath:[" + file.getPath() + "] lineCount:[" + lineCount + "]" + "dataError:[" + l + "]");
					break;
				}
				list.add(o);
				lineCount++;
			}
			try {
				this.batchInsert(list, coinConsumeDao);
			} catch (Throwable t) {
				FileLog.error("batchInsert error file:[" + file.getPath() + "]", t);
			}
		}
	}

	private void readCoinIncreaseFile(File file) throws IOException {
		if (null != file && file.exists()) {
			BufferedReader br = this.getBurreredReader(file);
			String l = null;
			List<CoinIncrease> list = new ArrayList<>();
			int lineCount = 1;
			while ((l = br.readLine()) != null) {
				String md5 = MD5Utils.encrypt(l);
				String[] line = l.split("\\t");
				CoinIncrease o = new CoinIncrease();
				if (line.length == 7) {
					String serverid = this.parseServerid(line[1], file.getPath(), lineCount);
					o.setUserid(line[0]);
					o.setServerid(serverid);
					o.setType(line[2]);
					o.setName(line[3]);
					o.setIncrease(line[4]);
					o.setAmount(line[5]);
					o.setCreateTime(line[6]);
					o.setLogTime(DateUtils.getDateString());
					o.setMd5(md5);
				} else if (line.length == 8) {
					String serverid = this.parseServerid(line[2], file.getPath(), lineCount);
					o.setUserid(line[0]);
					o.setUsername(line[1]);
					o.setServerid(serverid);
					o.setType(line[3]);
					o.setName(line[4]);
					o.setIncrease(line[5]);
					o.setAmount(line[6]);
					o.setCreateTime(line[7]);
					o.setLogTime(DateUtils.getDateString());
					o.setMd5(md5);
				} else {
					FileLog.error(
					        "logPath:[" + file.getPath() + "] lineCount:[" + lineCount + "]" + "dataError:[" + l + "]");
					break;
				}
				list.add(o);
				lineCount++;
			}
			this.batchInsert(list, coinIncreaseDao);
		}
	}

	private void readPropertyConsumeFile(File file) throws IOException {
		if (null != file && file.exists()) {
			BufferedReader br = this.getBurreredReader(file);
			String l = null;
			List<PropertyConsume> list = new ArrayList<>();
			int lineCount = 1;
			while ((l = br.readLine()) != null) {
				String md5 = MD5Utils.encrypt(l);
				String[] line = l.split("\\t");
				PropertyConsume o = new PropertyConsume();
				if (line.length == 7) {
					String serverid = this.parseServerid(line[1], file.getPath(), lineCount);
					o.setUserid(line[0]);
					o.setServerid(serverid);
					o.setType(line[2]);
					o.setName(line[3]);
					o.setConsume(line[4]);
					o.setAmount(line[5]);
					o.setCreateTime(line[6]);
					o.setLogTime(DateUtils.getDateString());
					o.setMd5(md5);
				} else if (line.length == 8) {
					String serverid = this.parseServerid(line[2], file.getPath(), lineCount);
					o.setUserid(line[0]);
					o.setUsername(line[1]);
					o.setServerid(serverid);
					o.setType(line[3]);
					o.setName(line[4]);
					o.setConsume(line[5]);
					o.setAmount(line[6]);
					o.setCreateTime(line[7]);
					o.setLogTime(DateUtils.getDateString());
					o.setMd5(md5);
				} else {
					FileLog.error(
					        "logPath:[" + file.getPath() + "] lineCount:[" + lineCount + "]" + "dataError:[" + l + "]");
					break;
				}
				list.add(o);
				lineCount++;
			}
			this.batchInsert(list, propertyConsumeDao);
		}
	}

	private void readPropertyIncreaseFile(File file) throws IOException {
		if (null != file && file.exists()) {
			BufferedReader br = this.getBurreredReader(file);
			String l = null;
			List<PropertyIncrease> list = new ArrayList<>();
			int lineCount = 1;
			while ((l = br.readLine()) != null) {
				String md5 = MD5Utils.encrypt(l);
				String[] line = l.split("\\t");
				PropertyIncrease o = new PropertyIncrease();
				if (line.length == 7) {
					String serverid = this.parseServerid(line[1], file.getPath(), lineCount);
					o.setUserid(line[0]);
					o.setServerid(serverid);
					o.setType(line[2]);
					o.setName(line[3]);
					o.setIncrease(line[4]);
					o.setAmount(line[5]);
					o.setCreateTime(line[6]);
					o.setLogTime(DateUtils.getDateString());
					o.setMd5(md5);
				} else if (line.length == 8) {
					String serverid = this.parseServerid(line[2], file.getPath(), lineCount);
					o.setUserid(line[0]);
					o.setUsername(line[1]);
					o.setServerid(serverid);
					o.setType(line[3]);
					o.setName(line[4]);
					o.setIncrease(line[5]);
					o.setAmount(line[6]);
					o.setCreateTime(line[7]);
					o.setLogTime(DateUtils.getDateString());
					o.setMd5(md5);
				} else {
					FileLog.error(
					        "logPath:[" + file.getPath() + "] lineCount:[" + lineCount + "]" + "dataError:[" + l + "]");
					break;
				}
				list.add(o);
				lineCount++;
			}
			this.batchInsert(list, propertyIncreaseDao);
		}
	}

	private void readRechargeFile(File file) throws IOException {
		if (null != file && file.exists()) {
			BufferedReader br = this.getBurreredReader(file);
			String l = null;
			List<Recharge> list = new ArrayList<>();
			int lineCount = 1;
			while ((l = br.readLine()) != null) {
				String md5 = MD5Utils.encrypt(l);
				String[] line = l.split("\\t");
				Recharge o = new Recharge();
				if (line.length == 7) {
					String serverid = this.parseServerid(line[3], file.getPath(), lineCount);
					o.setChannel(line[0]);
					o.setUserid(line[1]);
					o.setOrderid(line[2]);
					o.setServerid(serverid);
					o.setIp(line[4]);
					o.setAmount(line[5]);
					o.setCreateTime(line[6]);
					o.setLogTime(DateUtils.getDateString());
					o.setMd5(md5);
				} else if (line.length == 8) {
					String serverid = this.parseServerid(line[4], file.getPath(), lineCount);
					o.setChannel(line[0]);
					o.setUserid(line[1]);
					o.setUsername(line[2]);
					o.setOrderid(line[3]);
					o.setServerid(serverid);
					o.setIp(line[5]);
					o.setAmount(line[6]);
					o.setCreateTime(line[7]);
					o.setLogTime(DateUtils.getDateString());
					o.setMd5(md5);
				} else {
					FileLog.error(
					        "logPath:[" + file.getPath() + "] lineCount:[" + lineCount + "]" + "dataError:[" + l + "]");
					break;
				}
				list.add(o);
				lineCount++;
			}
			this.batchInsert(list, rechargeDao);
		}
	}

	private void readUserLoginFile(File file) throws IOException {
		if (null != file && file.exists()) {
			BufferedReader br = this.getBurreredReader(file);
			String l = null;
			List<UserLogin> list = new ArrayList<>();
			int lineCount = 1;

			while ((l = br.readLine()) != null) {
				String md5 = MD5Utils.encrypt(l);
				String[] line = l.split("\\t");
				UserLogin o = new UserLogin();
				if (line.length == 6) {
					String serverid = this.parseServerid(line[4], file.getPath(), lineCount);
					o.setUserid(line[0]);
					o.setUsername(line[1]);
					o.setNickname(line[2]);
					o.setIp(line[3]);
					o.setServerid(serverid);
					o.setCreateTime(line[5]);
					o.setLogTime(DateUtils.getDateString());
					o.setMd5(md5);
				} else {
					FileLog.error(
					        "logPath:[" + file.getPath() + "] lineCount:[" + lineCount + "]" + "dataError:[" + l + "]");
					break;
				}
				list.add(o);
				lineCount++;
			}
			this.batchInsert(list, userLoginDao);
		}
	}

	private void batchInsert(List<? extends DataEntity> list, BaseDao dao) {
		List<List<? extends DataEntity>> lists = new ArrayList<>();
		if (list.size() > 5000) {
			for (int i = 0; i < list.size() % 5000; i++) {
				int begin = i * 5000;
				if (begin > list.size()) {
					break;
				}
				int end = (i + 1) * 5000;
				end = end > list.size() ? list.size() : end;
				lists.add(list.subList(begin, end));
			}
		} else {
			lists.add(list);
		}
		for (List<? extends DataEntity> li : lists) {
			dao.batchInsert(li);
		}
	}

	private String getFilePath(String account) {
		if (StringUtils.isNoneEmpty(account)) {
			String filePath = PATH_ROOT + account;
			File file = new File(filePath);
			if (file.isDirectory()) {
				File f = null;
				for (String p : PATH_PATHS) {
					String path = p.replace("yyyy", this.year).replace("MM", this.month).replace("dd", this.day);
					f = new File(filePath + '/' + path);
					if (f.isDirectory()) {
						return f.getPath();
					} else {
						continue;
					}
				}
			}
		}
		FileLog.error("account: [" + account + "] dateTime:[" + dateTime + "] file not found");
		return null;
	}

	private String parseServerid(String serverid, String path, int lineCount) {
		if (StringUtils.isBlank(serverid)) {
			serverid = this.account;
		}
		try {
			if (serverid.substring(0, serverid.indexOf('_')).equals("0")) {
				serverid = this.account + '_' + serverid.substring(serverid.indexOf('_') + 1);
			}
		} catch (Exception e) {
			FileLog.error("serverid error:[" + serverid + "] filePath:[" + path + "] lineCount:[" + lineCount + "]");
		}
		return serverid;
	}

	private BufferedReader getBurreredReader(File file) throws UnsupportedEncodingException, FileNotFoundException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		this.insertFileHistory(file);
		return br;
	}

	private void insertFileHistory(File file) throws FileNotFoundException {
		FileHistory his = new FileHistory(file.getPath(), MD5Utils.encrypt(file), DateUtils.getDateString());
		his.setAccount(this.account);
		his.setDateTime(this.dateTime);
		int result = fileHistoryDao.insert(his);
		if (1 != result) {
			throw new FileNotFoundException("file has been read!file:[" + file.getPath() + "]");
		}
	}

}
