package cn.gnetop.dcs.system.servlet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.gnetop.dcs.system.auth.AccessAuthenticate;
import cn.gnetop.dcs.system.engine.Engine;
import cn.gnetop.dcs.system.entity.MessageContext;
import cn.gnetop.dcs.system.entity.Response;
import cn.gnetop.dcs.system.entity.Result;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.helper.ResultCodeManager;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.JsonUtils;
import cn.gnetop.pde.foundation.log.LogManager;

public class DcsServlet extends HttpServlet {

	private static final long serialVersionUID = 7432202930885867345L;

	private static Logger logger = LogManager.getLogger();

	private static final String ENCODE_UTF8 = "UTF-8";

	private static final int BUFFER_SIZE = 1024 * 8;

	// public static final String

	private Engine engine = Engine.getInstance();

	@Override
	public void init() throws ServletException {
		BeanUtils.getBean("serverInit");
	}

	@Override
	public void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		response.setBufferSize(BUFFER_SIZE);
		response.setContentType("application/json; charset=utf-8");
		String responseBody = this.doService(req);
		this.postResponse(response, responseBody);
		if (!response.isCommitted()) {
			response.flushBuffer();
		}
	}

	public String doService(HttpServletRequest req) {
		Response rsp = null;
		try {
			MessageContext mc = new MessageContext();
			// 消息头鉴权
			AccessAuthenticate.auth(req, mc);
			// 鉴权成功才初始化请求上下文
			mc.instance(req);
			// 交给引擎处理业务
			rsp = engine.invoke(mc);
		} catch (Throwable e) {
			logger.error("uri:" + req.getRequestURI(), e);
			if (e instanceof DcsException) {
				DcsException pe = (DcsException) e;
				String resultCode = pe.getResultCode();
				Result result = ResultCodeManager.getResult(resultCode);
				rsp = new Response(result);
			} else {
				rsp = new Response(ResultCodeManager.getResult(ResultCode.SYS_ERR));
			}
		}
		// 把响应转换为json格式
		return JsonUtils.toString(rsp);
	}

	public MessageContext getMessageContext(HttpServletRequest request) throws DcsException {
		return new MessageContext(request);
	}

	private void postResponse(HttpServletResponse rsp, String responseBody)
			throws UnsupportedEncodingException, IOException {
		Writer writer = new OutputStreamWriter(rsp.getOutputStream(), ENCODE_UTF8);
		writer = new BufferedWriter(new PrintWriter(writer));
		writer.write(responseBody);
		writer.flush();
	}
}
