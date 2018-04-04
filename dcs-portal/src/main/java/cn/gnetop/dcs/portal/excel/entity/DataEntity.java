package cn.gnetop.dcs.portal.excel.entity;

import java.io.Serializable;

public class DataEntity implements Serializable {

	private static final long serialVersionUID = 1336781737078879052L;

	private String id;

	private Integer total;

	private Integer start;

	private Integer pageNo;

	private Integer pageSize;

	private String begin;

	private String end;

	private String sort;

	private String orderBy;

	private Integer delFlag = 0;

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getStart() {
		if (null == start) {
			start = (getPageNo() - 1) * getPageSize();
		}
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageNo() {
		if (null == pageNo || 0 == pageNo) {
			pageNo = Integer.valueOf(1);
		}
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		if (null == pageSize) {
			pageSize = Integer.valueOf(0);
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
