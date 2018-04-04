package cn.gnetop.dcs.console.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gnetop.dcs.dao.CoinConsumeDao;
import cn.gnetop.dcs.dao.CoinIncreaseDao;
import cn.gnetop.dcs.dao.GameInfoDao;
import cn.gnetop.dcs.dao.PropertyConsumeDao;
import cn.gnetop.dcs.dao.PropertyIncreaseDao;
import cn.gnetop.dcs.dao.RechargeDao;
import cn.gnetop.dcs.dao.ServerInfoDao;
import cn.gnetop.dcs.dao.UserLoginDao;
import cn.gnetop.dcs.dao.base.Page;
import cn.gnetop.dcs.dao.schema.CoinConsume;
import cn.gnetop.dcs.dao.schema.CoinIncrease;
import cn.gnetop.dcs.dao.schema.GameInfo;
import cn.gnetop.dcs.dao.schema.PropertyConsume;
import cn.gnetop.dcs.dao.schema.PropertyIncrease;
import cn.gnetop.dcs.dao.schema.Recharge;
import cn.gnetop.dcs.dao.schema.RechargeRank;
import cn.gnetop.dcs.dao.schema.ServerInfo;
import cn.gnetop.dcs.dao.schema.UserLogin;

@Service
public class LogService {

	@Autowired
	private ServerInfoDao serverInfoDao;

	@Autowired
	private GameInfoDao gameInfoDao;

	@Autowired
	private UserLoginDao userLoginDao;

	@Autowired
	private CoinIncreaseDao coinIncreaseDao;

	@Autowired
	private CoinConsumeDao coinConsumeDao;

	@Autowired
	private PropertyIncreaseDao propertyIncreaseDao;

	@Autowired
	private PropertyConsumeDao propertyConsumeDao;

	@Autowired
	private RechargeDao rechargeDao;

	public List<ServerInfo> getServerList() {
		return serverInfoDao.findList(new ServerInfo());
	}

	public List<GameInfo> getGameList() {
		return gameInfoDao.findList(new GameInfo());
	}

	public Page<UserLogin> findLoginPage(UserLogin login) {
		Integer total = userLoginDao.countFindList(login);
		Integer pageNo = login.getPageNo();
		Integer pageSize = login.getPageSize();
		Integer start = (pageNo - 1) * pageSize;
		login.setStart(start);
		Page<UserLogin> loginPage = new Page<>();
		List<UserLogin> list = userLoginDao.findList(login);
		loginPage.setList(list);
		loginPage.setTotal(total);
		loginPage.setStart(start);
		loginPage.setPageNo(pageNo);
		loginPage.setPageSize(pageSize);
		return loginPage;
	}

	public Page<CoinIncrease> findCoinIncreasePage(CoinIncrease coinIncrease) {
		Integer total = coinIncreaseDao.countFindList(coinIncrease);
		Integer pageNo = coinIncrease.getPageNo();
		Integer pageSize = coinIncrease.getPageSize();
		Integer start = (pageNo - 1) * pageSize;
		coinIncrease.setStart(start);
		Page<CoinIncrease> page = new Page<>();
		List<CoinIncrease> list = coinIncreaseDao.findList(coinIncrease);
		page.setList(list);
		page.setTotal(total);
		page.setStart(start);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return page;
	}

	public Page<CoinConsume> findCoinConsumePage(CoinConsume coinConsume) {
		Integer total = coinConsumeDao.countFindList(coinConsume);
		Integer pageNo = coinConsume.getPageNo();
		Integer pageSize = coinConsume.getPageSize();
		Integer start = (pageNo - 1) * pageSize;
		coinConsume.setStart(start);
		Page<CoinConsume> page = new Page<>();
		List<CoinConsume> list = coinConsumeDao.findList(coinConsume);
		page.setList(list);
		page.setTotal(total);
		page.setStart(start);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return page;
	}

	public Page<PropertyIncrease> findPropIncreasePage(PropertyIncrease propIncrease) {
		Integer total = propertyIncreaseDao.countFindList(propIncrease);
		Integer pageNo = propIncrease.getPageNo();
		Integer pageSize = propIncrease.getPageSize();
		Integer start = (pageNo - 1) * pageSize;
		propIncrease.setStart(start);
		Page<PropertyIncrease> page = new Page<>();
		List<PropertyIncrease> list = propertyIncreaseDao.findList(propIncrease);
		page.setList(list);
		page.setTotal(total);
		page.setStart(start);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return page;
	}

	public Page<PropertyConsume> findPropConsumePage(PropertyConsume propConsume) {
		Integer total = propertyConsumeDao.countFindList(propConsume);
		Integer pageNo = propConsume.getPageNo();
		Integer pageSize = propConsume.getPageSize();
		Integer start = (pageNo - 1) * pageSize;
		propConsume.setStart(start);
		Page<PropertyConsume> page = new Page<>();
		List<PropertyConsume> list = propertyConsumeDao.findList(propConsume);
		page.setList(list);
		page.setTotal(total);
		page.setStart(start);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return page;
	}

	public Page<Recharge> findRechargePage(Recharge recharge) {
		Integer total = rechargeDao.countFindList(recharge);
		Integer pageNo = recharge.getPageNo();
		Integer pageSize = recharge.getPageSize();
		Integer start = (pageNo - 1) * pageSize;
		recharge.setStart(start);
		Page<Recharge> page = new Page<>();
		List<Recharge> list = rechargeDao.findList(recharge);
		page.setList(list);
		page.setTotal(total);
		page.setStart(start);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return page;
	}

	public Page<Recharge> findRechargeTotal(Recharge recharge) {
		Integer total = rechargeDao.countFindList(recharge);
		Integer pageNo = recharge.getPageNo();
		Integer pageSize = recharge.getPageSize();
		Integer start = (pageNo - 1) * pageSize;
		recharge.setStart(start);
		Page<Recharge> page = new Page<>();
		List<Recharge> list = rechargeDao.findTotalList(recharge);
		page.setList(list);
		page.setTotal(total);
		page.setStart(start);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return page;
	}

	public Page<RechargeRank> findRank(Recharge recharge) {
		Page<RechargeRank> page = new Page<>();
		if (null != recharge) {
			if (recharge.getGameid().indexOf(',') > -1) {
				List<RechargeRank> rankList = new ArrayList<>();
				for (String gameid : recharge.getGameid().split(",")) {
					recharge.setGameid(gameid);
					List<RechargeRank> list = rechargeDao.findRank(recharge);
					if (CollectionUtils.isNotEmpty(list)) {
						for (int i = 0; i < list.size(); i++) {
							list.get(i).setRank(i);
						}
					}
					rankList.addAll(list);
				}
				Collections.sort(rankList, new Comparator<RechargeRank>() {
					@Override
					public int compare(RechargeRank r0, RechargeRank r1) {
						String a0 = r0.getAmount();
						String a1 = r1.getAmount();
						if (null == a0) {
							a0 = "-1";
						}
						if (null == a1) {
							a1 = "-1";
						}
						return new BigDecimal(a1).compareTo(new BigDecimal(a0));
					}
				});
				page.setList(rankList);
			} else {
				List<RechargeRank> list = rechargeDao.findRank(recharge);
				if (CollectionUtils.isNotEmpty(list)) {
					for (int i = 0; i < list.size(); i++) {
						list.get(i).setRank(i);
					}
				}
				page.setList(list);
			}
		}
		return page;
	}

	public ServerInfo findServerInfo(String serverid) {
		ServerInfo info = new ServerInfo();
		info.setServerid(serverid);
		info = serverInfoDao.findOne(info);
		return info;
	}

	public int addServer(ServerInfo info) {
		int result = serverInfoDao.insert(info);
		return result;
	}

	public Page<ServerInfo> getServerPage(ServerInfo info) {
		Integer total = serverInfoDao.countFindList(info);
		Integer pageNo = info.getPageNo();
		Integer pageSize = info.getPageSize();
		Integer start = (pageNo - 1) * pageSize;
		info.setStart(start);
		Page<ServerInfo> page = new Page<>();
		List<ServerInfo> list = serverInfoDao.findList(info);
		page.setList(list);
		page.setTotal(total);
		page.setStart(start);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return page;
	}

}
