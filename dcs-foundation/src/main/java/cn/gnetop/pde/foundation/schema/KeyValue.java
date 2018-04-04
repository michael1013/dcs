package cn.gnetop.pde.foundation.schema;

public class KeyValue implements org.apache.commons.collections.KeyValue {

	private String key;
	private String value;

	public KeyValue(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public KeyValue() {
		super();
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeyValue [key=");
		builder.append(key);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}