package cn.gnetop.dcs.dao.base;

import java.util.List;

public class Page<T> extends DataEntity {

	private static final long serialVersionUID = -8472576072355645140L;

	private List<T> list;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

}
