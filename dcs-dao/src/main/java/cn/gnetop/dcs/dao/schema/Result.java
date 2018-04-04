package cn.gnetop.dcs.dao.schema;

import java.io.Serializable;

public class Result implements Serializable {

	private static final long serialVersionUID = 1842945823145126139L;
	private String id;
	private String msgEn;
	private String msgCn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsgEn() {
		return msgEn;
	}

	public void setMsgEn(String msgEn) {
		this.msgEn = msgEn;
	}

	public String getMsgCn() {
		return msgCn;
	}

	public void setMsgCn(String msgCn) {
		this.msgCn = msgCn;
	}

	@Override
	public String toString() {
		return "Result [id=" + id + ", msgEn=" + msgEn + ", msgCn=" + msgCn + "]";
	}

}
