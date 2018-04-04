package cn.gnetop.dcs.dao.schema;

import java.io.Serializable;

public class Config implements Serializable {

	private static final long serialVersionUID = 3177818145798764828L;

	private String id;
	private String key;
	private String value;
	private String def;
	private boolean isOpen;

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Config [id=");
		builder.append(id);
		builder.append(", key=");
		builder.append(key);
		builder.append(", value=");
		builder.append(value);
		builder.append(", def=");
		builder.append(def);
		builder.append("]");
		return builder.toString();
	}

}
