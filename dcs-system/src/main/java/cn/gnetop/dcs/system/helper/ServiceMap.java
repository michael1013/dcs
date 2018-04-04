package cn.gnetop.dcs.system.helper;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.gnetop.dcs.system.engine.IService;
import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.entity.Response;
import cn.gnetop.pde.foundation.MapUtils;

public class ServiceMap {

	private static final Logger logger = Logger.getLogger(ServiceMap.class);

	@SuppressWarnings("unchecked")
	private static Map<String, Bean<? extends Request, ? extends Response>> beanMap = MapUtils.newHashMap();

	@SuppressWarnings("unchecked")
	private static Map<String, IService> serviceMap = MapUtils.newHashMap();

	@SuppressWarnings("unchecked")
	private static Map<String, Method> methods = MapUtils.newHashMap();

	public static Map<String, Bean<? extends Request, ? extends Response>> getBeanContext() {
		return beanMap;
	}

	public static void setBeanContext(Map<String, Bean<? extends Request, ? extends Response>> map) {
		beanMap = map;
	}

	public static Map<String, IService> getServices() {
		return serviceMap;
	}

	public static void setServices(Map<String, IService> map) {
		serviceMap = map;
	}

	public static Map<String, Method> getMethods() {
		return methods;
	}

	public static void setMethods(Map<String, Method> map) {
		methods = map;
	}

	public static class Bean<R extends Request, P extends Response> {
		private Class<R> req;
		private Class<P> rsp;

		public Bean(Class<R> req, Class<P> rsp) {
			super();
			this.req = req;
			this.rsp = rsp;
		}

		public Class<R> getReq() {
			return req;
		}

		public void setReq(Class<R> req) {
			this.req = req;
		}

		public Class<P> getRsp() {
			return rsp;
		}

		public void setRsp(Class<P> rsp) {
			this.rsp = rsp;
		}

	}

	public static class BeanContext {
		private String mapKey;
		private String service;
		private String action;
		private String reqClzName;
		private String rspClzName;
		private Class<? extends Request> req;
		private Class<? extends Response> rsp;

		@SuppressWarnings("unchecked")
		public BeanContext(String service, String action, String reqClzName, String rspClzName)
				throws ClassNotFoundException {
			super();
			this.mapKey = service + "_" + action;
			this.service = service;
			this.action = action;
			this.reqClzName = reqClzName;
			this.rspClzName = rspClzName;
			this.req = (Class<? extends Request>) Class.forName(reqClzName);
			this.rsp = (Class<? extends Response>) Class.forName(rspClzName);
		}

		public String getMapKey() {
			return mapKey;
		}

		public void setMapKey(String mapKey) {
			this.mapKey = mapKey;
		}

		public String getService() {
			return service;
		}

		public void setService(String service) {
			this.service = service;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public String getReqClzName() {
			return reqClzName;
		}

		public void setReqClzName(String reqClzName) {
			this.reqClzName = reqClzName;
		}

		public String getRspClzName() {
			return rspClzName;
		}

		public void setRspClzName(String rspClzName) {
			this.rspClzName = rspClzName;
		}

		public Class<? extends Request> getReq() {
			return req;
		}

		public void setReq(Class<? extends Request> req) {
			this.req = req;
		}

		public Class<? extends Response> getRsp() {
			return rsp;
		}

		public void setRsp(Class<? extends Response> rsp) {
			this.rsp = rsp;
		}

	}
}
