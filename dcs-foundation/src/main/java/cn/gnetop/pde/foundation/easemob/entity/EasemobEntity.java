package cn.gnetop.pde.foundation.easemob.entity;

import java.io.Serializable;
import java.util.Arrays;

public class EasemobEntity<T> implements Serializable {

	private static final long serialVersionUID = 8494764364942494436L;
	private String action;
	private String application;
	private String path;
	private String uri;
	private T[] entities;
	private T[] data;
	private Long timestamp;
	private Long duration;
	private String organization;
	private String applicationName;
	private Integer count;
	private String error;
	private String exception;
	private String error_description;

	public T[] getData() {
		return data;
	}

	public void setData(T[] data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getError_description() {
		return error_description;
	}

	public void setError_description(String error_description) {
		this.error_description = error_description;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public T[] getEntities() {
		return entities;
	}

	public void setEntities(T[] entities) {
		this.entities = entities;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EasemobEntity [action=");
		builder.append(action);
		builder.append(", application=");
		builder.append(application);
		builder.append(", path=");
		builder.append(path);
		builder.append(", uri=");
		builder.append(uri);
		builder.append(", entities=");
		builder.append(Arrays.toString(entities));
		builder.append(", data=");
		builder.append(Arrays.toString(data));
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", organization=");
		builder.append(organization);
		builder.append(", applicationName=");
		builder.append(applicationName);
		builder.append(", count=");
		builder.append(count);
		builder.append(", error=");
		builder.append(error);
		builder.append(", exception=");
		builder.append(exception);
		builder.append(", error_description=");
		builder.append(error_description);
		builder.append("]");
		return builder.toString();
	}

}
