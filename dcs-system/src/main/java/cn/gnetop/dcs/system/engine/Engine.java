package cn.gnetop.dcs.system.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import cn.gnetop.dcs.system.auth.ParamAntiTemper;
import cn.gnetop.dcs.system.entity.MessageContext;
import cn.gnetop.dcs.system.entity.Response;
import cn.gnetop.dcs.system.helper.ServiceMap;

public class Engine {

	private static Logger logger = Logger.getLogger(Engine.class);

	private static Engine instance = new Engine();

	private Engine() {
	}

	public static Engine getInstance() {
		return instance;
	}

	private static ThreadLocal<MessageContext> threadMessageContext = new ThreadLocal<MessageContext>();

	public Response invoke(MessageContext mc) throws Throwable {
		// 入参防篡改
		ParamAntiTemper.auth(mc);
		// 上下文放入线程变量中
		threadMessageContext.set(mc);

		Response rsp = null;
		// get serviceClass getActionMethod
		IService service = ServiceMap.getServices().get(mc.getServiceName());

		// doService and getResult
		Method method = ServiceMap.getMethods().get(mc.getMapKey());
		try {
			rsp = (Response) method.invoke(service, mc.getReq());
		} catch (IllegalAccessException | IllegalArgumentException e) {
			logger.error("", e);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}

		return rsp;
	}

	public static MessageContext getThreadMessageContext() {
		return threadMessageContext.get();
	}

}
