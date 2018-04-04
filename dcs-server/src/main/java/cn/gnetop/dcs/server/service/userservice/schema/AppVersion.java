package cn.gnetop.dcs.server.service.userservice.schema;

import java.io.Serializable;
import java.util.List;

public class AppVersion implements Serializable {

	private static final long serialVersionUID = -3914411049175052145L;

	private String id;
	private String bundleid;
	private String version;
	private List<String> channel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBundleid() {
		return bundleid;
	}

	public void setBundleid(String bundleid) {
		this.bundleid = bundleid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<String> getChannel() {
		return channel;
	}

	public void setChannel(List<String> channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AppVersion [id=");
		builder.append(id);
		builder.append(", bundleid=");
		builder.append(bundleid);
		builder.append(", version=");
		builder.append(version);
		builder.append(", channel=");
		builder.append(channel);
		builder.append("]");
		return builder.toString();
	}

}
