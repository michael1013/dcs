package cn.gnetop.dcs.console.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gnetop.dcs.dao.RechargeDao;
import cn.gnetop.dcs.dao.UserLoginDao;
import cn.gnetop.dcs.dao.schema.ServerInfo;
import cn.gnetop.dcs.dao.schema.UserData;

@Service
public class DataService {

	@Autowired
	private UserLoginDao userLoginDao;

	@Autowired
	private RechargeDao rechargeDao;

	public Map<String, Integer> getNewUserCount(String date) {
		return null;
	}

	public Map<String, Object> findNew(ServerInfo server) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> dataList = userLoginDao.findNew(server);
		for (Map<String, Object> dataMap : dataList) {
			Object key = dataMap.get("date");
			Object value = dataMap.get("count");
			if (null != key) {
				map.put(String.valueOf(key), String.valueOf(value));
			}
		}
		return map;
	}

	public Map<String, Map<String, Object>> findStay(List<String> dateList, ServerInfo server) {
		Map<String, Map<String, Object>> dataMap = new HashMap<>();
		for (String logTime : dateList) {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("gameid", server.getGameid());
			paramMap.put("serverid", server.getServerid());
			paramMap.put("logTime", logTime);
			paramMap.put("dateList", dateList);
			Map<String, Object> result = userLoginDao.findStay(paramMap);
			dataMap.put(logTime, result);
		}
		return dataMap;
	}

	public Map<String, Object> findActive(ServerInfo server) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> dataList = userLoginDao.findActive(server);
		for (Map<String, Object> dataMap : dataList) {
			Object key = dataMap.get("date");
			Object value = dataMap.get("count");
			if (null != key) {
				map.put(String.valueOf(key), String.valueOf(value));
			}
		}
		return map;
	}

	public Map<String, UserData> findKpi(ServerInfo server, List<String> dateList) {
		Map<String, UserData> map = new HashMap<>();
		if (null != server) {
			if (server.getGameid().indexOf(',') > -1) {
				for (String date : dateList) {
					UserData totalData = new UserData();
					double totalRecharge = 0D;
					double rechargeCount = 0D;
					double newCount = 0D;
					double newStay = 0D;
					double activeCount = 0D;
					double activeStay = 0D;
					for (String gameid : server.getGameid().split(",")) {
						Map<String, Object> paramMap = new HashMap<>();
						paramMap.put("gameid", gameid);
						paramMap.put("date", date);
						UserData ud = userLoginDao.findGameKpi(paramMap);
						totalRecharge += ud.getTotalRecharge();
						rechargeCount += ud.getRechargeCount();
						newCount += ud.getNewCount();
						newStay += ud.getNewStay();
						activeCount += ud.getActiveCount();
						activeStay += ud.getActiveStay();
					}
					totalData.setTotalRecharge(totalRecharge);
					totalData.setRechargeCount(rechargeCount);
					totalData.setPermeability(rechargeCount / activeCount);
					totalData.setArppu(totalRecharge / rechargeCount);
					totalData.setNewCount(newCount);
					totalData.setNewStay(newStay);
					totalData.setNewStayRat(newStay / newCount);
					totalData.setActiveCount(activeCount);
					totalData.setActiveStay(activeStay);
					totalData.setActiveStayRat(activeStay / activeCount);
					map.put(date, totalData);
				}
			} else {
				for (String date : dateList) {
					Map<String, Object> paramMap = new HashMap<>();
					paramMap.put("gameid", server.getGameid());
					paramMap.put("serverid", server.getServerid());
					paramMap.put("date", date);
					UserData data = userLoginDao.findKpi(paramMap);
					map.put(date, data);
				}
			}
		}
		return map;
	}

	public Map<String, UserData> findMonthKpi(ServerInfo server, List<String> dateList) {
		Map<String, UserData> map = new HashMap<>();
		if (null != server) {
			if (server.getGameid().indexOf(',') > -1) {
				for (String date : dateList) {
					UserData totalData = new UserData();
					double totalRecharge = 0D;
					double rechargeCount = 0D;
					double newCount = 0D;
					double newStay = 0D;
					double activeCount = 0D;
					double activeStay = 0D;
					for (String gameid : server.getGameid().split(",")) {
						Map<String, Object> paramMap = new HashMap<>();
						paramMap.put("gameid", gameid);
						paramMap.put("date", date);
						UserData ud = userLoginDao.findGameMonthKpi(paramMap);
						totalRecharge += ud.getTotalRecharge();
						rechargeCount += ud.getRechargeCount();
						newCount += ud.getNewCount();
						newStay += ud.getNewStay();
						activeCount += ud.getActiveCount();
						activeStay += ud.getActiveStay();
					}
					totalData.setTotalRecharge(totalRecharge);
					totalData.setRechargeCount(rechargeCount);
					totalData.setPermeability(rechargeCount / activeCount);
					totalData.setArppu(totalRecharge / rechargeCount);
					totalData.setNewCount(newCount);
					totalData.setNewStay(newStay);
					totalData.setNewStayRat(newStay / newCount);
					totalData.setActiveCount(activeCount);
					totalData.setActiveStay(activeStay);
					totalData.setActiveStayRat(activeStay / activeCount);
					map.put(date, totalData);
				}
			} else {
				for (String date : dateList) {
					Map<String, Object> paramMap = new HashMap<>();
					paramMap.put("gameid", server.getGameid());
					paramMap.put("serverid", server.getServerid());
					paramMap.put("date", date);
					UserData data = userLoginDao.findMonthKpi(paramMap);
					map.put(date, data);
				}
			}
		}
		return map;
	}

	public Map<String, Map<String, Object>> findRechargeData(ServerInfo server, List<String> dateList) {
		Map<String, Map<String, Object>> map = new HashMap<>();
		if (null != server) {
			if (server.getGameid().indexOf(',') > -1) {
				for (String date : dateList) {
					Map<String, Object> resultMap = new HashMap<>();
					for (String gameid : server.getGameid().split(",")) {
						Map<String, Object> paramMap = new HashMap<>();
						paramMap.put("gameid", gameid);
						paramMap.put("date", date);
						Map<String, Object> data = rechargeDao.findGameRechargeData(paramMap);
						for (Map.Entry<String, Object> entry : data.entrySet()) {
							Object value = resultMap.get(entry.getKey());
							if (null == value) {
								resultMap.put(entry.getKey(), entry.getValue());
							} else {
								Double d = NumberUtils.toDouble(String.valueOf(value));
								d += NumberUtils.toDouble(String.valueOf(entry.getValue()));
								resultMap.put(entry.getKey(), d);
							}
						}
					}
					map.put(date, resultMap);
				}

			} else {
				for (String date : dateList) {
					Map<String, Object> paramMap = new HashMap<>();
					paramMap.put("gameid", server.getGameid());
					paramMap.put("serverid", server.getServerid());
					paramMap.put("date", date);
					Map<String, Object> data = rechargeDao.findRechargeData(paramMap);
					map.put(date, data);
				}
			}
		}
		return map;
	}

}
