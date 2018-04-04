package cn.gnetop.dcs.system.entity;

import javax.servlet.http.HttpServletRequest;

import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.exception.ParamErrException;
import cn.gnetop.dcs.system.helper.MessageContextHelper;
import cn.gnetop.dcs.system.helper.ServiceMap;
import cn.gnetop.pde.foundation.StringUtils;
import cn.gnetop.pde.foundation.log.LogManager;

public class MessageContext {

	private MessageHeader header;

	private String reqBody;

	private String rspBody;

	private Request req;

	private Response rsp;

	private Class<? extends Response> rspClz;

	private String serviceName;

	private String action;

	private String ip;

	private String mapKey;

	private String method;

	public MessageContext() {
		super();
	}

	public MessageContext(HttpServletRequest request) throws DcsException {
		super();
		this.instance(request);
	}

	public void instance(HttpServletRequest request) throws DcsException {
		if (null != request) {
			String uri = request.getRequestURI();
			this.serviceName = MessageContextHelper.convertorServiceName(uri);
			this.action = uri.substring(uri.lastIndexOf('/') + 1);
			this.mapKey = this.serviceName + "_" + this.action;
			this.ip = request.getRemoteAddr();
			this.method = request.getMethod();
			if (StringUtils.isBlank(this.reqBody)) {
				this.reqBody = MessageContextHelper.convertorReqBody(request);
			}
			try {
				MessageContextHelper.convertorRequest(this);
			} catch (Exception e) {
				LogManager.getLogger().error(this.reqBody);
				throw new ParamErrException(ResultCode.PARAM_FORMAT_ERR, e);
			}
			this.rspClz = (Class<? extends Response>) ServiceMap.getBeanContext().get(this.mapKey).getRsp();
		}
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public MessageHeader getHeader() {
		return header;
	}

	public void setHeader(MessageHeader header) {
		this.header = header;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getReqBody() {
		return reqBody;
	}

	public void setReqBody(String reqBody) {
		this.reqBody = reqBody;
	}

	public String getRspBody() {
		return rspBody;
	}

	public void setRspBody(String rspBody) {
		this.rspBody = rspBody;
	}

	public Request getReq() {
		return req;
	}

	public void setReq(Request req) {
		this.req = req;
	}

	public Response getRsp() {
		return rsp;
	}

	public void setRsp(Response rsp) {
		this.rsp = rsp;
	}

	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}

	public Class<? extends Response> getRspClz() {
		return rspClz;
	}

	public void setRspClz(Class<? extends Response> rspClz) {
		this.rspClz = rspClz;
	}

}
