package cn.gnetop.dcs.system.entity;

import java.io.Serializable;

public class MessageHeader implements Serializable {

	private static final long serialVersionUID = -3614622110295423522L;

	private String version;

	private String componentType;

	private String componentCode;

	private String authenticateCode;

	private String timeStamp;

	private String vk;

	public String getVk() {
		return vk;
	}

	public void setVk(String vk) {
		this.vk = vk;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getComponentCode() {
		return componentCode;
	}

	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}

	public String getAuthenticateCode() {
		return authenticateCode;
	}

	public void setAuthenticateCode(String authenticateCode) {
		this.authenticateCode = authenticateCode;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

}
