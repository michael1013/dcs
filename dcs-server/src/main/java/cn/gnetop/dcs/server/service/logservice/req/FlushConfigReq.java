package cn.gnetop.dcs.server.service.logservice.req;

import cn.gnetop.dcs.system.entity.Request;

public class FlushConfigReq extends Request {

	private static final long serialVersionUID = -416782711158522525L;

	private String k;

	private String v;

	private String x;

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlushConfigReq [k=");
		builder.append(k);
		builder.append(", v=");
		builder.append(v);
		builder.append(", x=");
		builder.append(x);
		builder.append("]");
		return builder.toString();
	}

}
