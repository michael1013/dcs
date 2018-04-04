package cn.gnetop.dcs.system.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import cn.gnetop.dcs.system.engine.Engine;
import cn.gnetop.dcs.system.entity.MessageContext;
import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.entity.Response;
import cn.gnetop.dcs.system.entity.Result;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.helper.ResultCodeManager;
import cn.gnetop.pde.foundation.CommonUtils;
import cn.gnetop.pde.foundation.log.InterfaceLog;

@Aspect
public class DcsAspectManager {

	private static final Logger log = Logger.getLogger(DcsAspectManager.class);

	public void logBefore(String uuid, String auth, String ip, String method, String service, String action,
	        Request req) {
		InterfaceLog.log(uuid, auth, ip, method, service, action, "req", String.valueOf(req));
	}

	public void logAfter(String uuid, String auth, String ip, String method, String service, String action,
	        Response rsp) {
		InterfaceLog.log(uuid, auth, ip, method, service, action, "rsp", String.valueOf(rsp));
	}

	@Around("execution(* cn.gnetop.dcs.server.service.*service.*ServiceImpl.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		MessageContext mc = Engine.getThreadMessageContext();
		String service = mc.getServiceName();
		String action = mc.getAction();
		Request req = (Request) pjp.getArgs()[0];

		String uuid = CommonUtils.getUUID();

		String ip = mc.getIp();
		// 记录入口日志
		this.logBefore(uuid, mc.getHeader().getAuthenticateCode(), ip, mc.getMethod(), service, action, req);
		Response rsp = null;
		try {
			// 参数校验
			ParamVerifyManager.verify(req);

			rsp = (Response) pjp.proceed();// 执行方法
		} catch (Throwable e) {
			log.error("", e);
			e.printStackTrace();
			if (e instanceof DcsException) {
				DcsException pe = (DcsException) e;
				String resultCode = pe.getResultCode();
				Result result = ResultCodeManager.getResult(resultCode);
				rsp = mc.getRspClz().newInstance();
				rsp.setResult(result);
			} else {
				rsp = mc.getRspClz().newInstance();
				rsp.setResult(ResultCodeManager.getResult(ResultCode.SYS_ERR));
			}
			// 记录异常出口日志
			this.logAfter(uuid, mc.getHeader().getAuthenticateCode(), ip, mc.getMethod(), service, action, rsp);
			return rsp;
		}
		// 若正常到这，则请求成功
		if (null != rsp && null == rsp.getResult()) {
			rsp.setResult(ResultCodeManager.getResult(ResultCode.SUCCESS));
		} else if (null == rsp) {
			// 若rsp为空, 则说明handler返回的结果为空, 代码有异常
			throw new DcsException(ResultCode.SYS_ERR);
		}
		// 记录出口日志
		this.logAfter(uuid, mc.getHeader().getAuthenticateCode(), ip, mc.getMethod(), service, action, rsp);
		return rsp;
	}
}
