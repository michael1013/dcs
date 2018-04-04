package cn.gnetop.dcs.server.init;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.gnetop.dcs.dao.ConfigDao;
import cn.gnetop.dcs.dao.ResultDao;
import cn.gnetop.dcs.dao.ServerInfoDao;
import cn.gnetop.dcs.dao.schema.Config;
import cn.gnetop.dcs.dao.schema.Result;
import cn.gnetop.dcs.dao.schema.ServerInfo;
import cn.gnetop.dcs.server.convertor.ResultConvertor;
import cn.gnetop.dcs.server.service.logservice.LogService;
import cn.gnetop.dcs.server.service.userservice.UserService;
import cn.gnetop.dcs.system.aspect.ParamVerifyManager;
import cn.gnetop.dcs.system.engine.IService;
import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.entity.Response;
import cn.gnetop.dcs.system.helper.ConfigManager;
import cn.gnetop.dcs.system.helper.ResultCodeManager;
import cn.gnetop.dcs.system.helper.ServiceMap;
import cn.gnetop.dcs.system.helper.ServiceMap.Bean;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.MapUtils;

public class ServerInitManager {

	private static final Logger logger = Logger.getLogger(ServerInitManager.class);

	public static void init() {
		resultCodeInit();
		serviceInit();
		configInit();
		paramVerifyInit();
	}

	public static void paramVerifyInit() {
		ServerInfoDao dao = BeanUtils.getBean(ServerInfoDao.class);
		List<ServerInfo> serverInfoList = dao.findList(new ServerInfo());
		List<String> serveridList = new ArrayList<>();
		for (ServerInfo serverInfo : serverInfoList) {
			serveridList.add(serverInfo.getServerid());
		}
		ParamVerifyManager.init(serveridList);
	}

	private static void resultCodeInit() {
		ResultDao resultDao = BeanUtils.getBean(ResultDao.class);
		List<Result> resultList = resultDao.findList(new Result());
		Map<String, cn.gnetop.dcs.system.entity.Result> resultMap = MapUtils.newHashMap();
		for (cn.gnetop.dcs.system.entity.Result result : ResultConvertor.convertor(resultList)) {
			resultMap.put(result.getResultCode(), result);
		}
		ResultCodeManager.setResultMap(resultMap);
	}

	private static void serviceInit() {
		try {
			@SuppressWarnings("unchecked")
			Map<String, String> actionMap = MapUtils.newHashMap();
			actionMap.putAll(getServiceActionMap(LogService.class));
			actionMap.putAll(getServiceActionMap(UserService.class));

			@SuppressWarnings("unchecked")
			Map<String, Bean<? extends Request, ? extends Response>> beanMap = MapUtils.newHashMap();

			@SuppressWarnings("unchecked")
			Map<String, IService> serviceMap = MapUtils.newHashMap();
			serviceMap.put("logService", BeanUtils.getBean(LogService.class));
			serviceMap.put("userService", BeanUtils.getBean(UserService.class));

			@SuppressWarnings("unchecked")
			Map<String, Method> methods = MapUtils.newHashMap();

			for (Map.Entry<String, String> entry : actionMap.entrySet()) {
				String key = entry.getKey();
				String methodName = key.substring(key.indexOf('_') + 1);
				String serviceName = entry.getValue();
				String packageName = "cn.gnetop.dcs.server.service." + serviceName.toLowerCase();
				String req = StringUtils.capitalize(methodName) + "Req";
				String rsp = StringUtils.capitalize(methodName) + "Rsp";
				ServiceMap.BeanContext beanContext = new ServiceMap.BeanContext(serviceName, methodName,
				        packageName + ".req." + req, packageName + ".rsp." + rsp);
				beanMap.put(beanContext.getMapKey(), new ServiceMap.Bean(beanContext.getReq(), beanContext.getRsp()));

				methods.put(beanContext.getMapKey(), BeanUtils.getBean(IService.class, beanContext.getService())
				        .getClass().getMethod(beanContext.getAction(), Class.forName(beanContext.getReqClzName())));
			}
			ServiceMap.setBeanContext(beanMap);
			ServiceMap.setServices(serviceMap);
			ServiceMap.setMethods(methods);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public static void configInit() {
		ConfigDao configDao = BeanUtils.getBean(ConfigDao.class);
		List<Config> configList = configDao.findAll();
		Map<String, String> configMap = MapUtils.newHashMap();
		Map<String, String> configOpenMap = MapUtils.newHashMap();
		if (CollectionUtils.isNotEmpty(configList)) {
			for (Config config : configList) {
				String value = config.getValue();
				if (StringUtils.isBlank(value)) {
					value = config.getDef();
				}
				if (config.isOpen()) {
					configOpenMap.put(config.getKey(), value);
				}
				configMap.put(config.getKey(), value);
			}
		}
		ConfigManager.initMap(configMap, configOpenMap);
	}

	private static Map<String, String> getServiceActionMap(Class<? extends IService> c) {
		Map<String, String> map = MapUtils.newHashMap();
		for (Method m : c.getMethods()) {
			String key = c.getSimpleName() + "_" + m.getName();
			String value = StringUtils.uncapitalize(c.getSimpleName());
			map.put(key, value);
		}
		return map;
	}

}
