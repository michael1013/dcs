package cn.gnetop.dcs.system.entity;

import java.io.Serializable;

import cn.gnetop.dcs.system.exception.DcsException;
import net.sf.json.JSONObject;

public class Response implements Serializable {

	private static final long serialVersionUID = 6828308134337936991L;

	private Result result;

	private Object data;

	public void putData(String key, Object data) {
		JSONObject jo = new JSONObject();
		jo.put(key, data);
		this.data = jo;
	}

	public Object getData() {
		if (null == data) {
			data = "";
		}
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Response() {
		super();
	}

	public Response(Result result) {
		super();
		this.result = result;
	}

	public Response(DcsException e) {
		super();
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Response [result=");
		builder.append(result);
		builder.append(", data=");
		builder.append(data);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
