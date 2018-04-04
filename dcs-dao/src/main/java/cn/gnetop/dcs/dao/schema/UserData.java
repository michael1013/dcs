package cn.gnetop.dcs.dao.schema;

import cn.gnetop.dcs.dao.base.DataEntity;

public class UserData extends DataEntity {

	private static final long serialVersionUID = 7279539123811163701L;

	private String gameName;
	private String serverName;
	private Double totalRecharge;
	private Double rechargeCount;
	private Double permeability;
	private Double arppu;
	private Double newCount;
	private Double newStay;
	private Double newStayRat;
	private Double activeCount;
	private Double activeStay;
	private Double activeStayRat;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Double getTotalRecharge() {
		return totalRecharge;
	}

	public void setTotalRecharge(Double totalRecharge) {
		this.totalRecharge = totalRecharge;
	}

	public Double getRechargeCount() {
		return rechargeCount;
	}

	public void setRechargeCount(Double rechargeCount) {
		this.rechargeCount = rechargeCount;
	}

	public Double getPermeability() {
		return permeability;
	}

	public void setPermeability(Double permeability) {
		this.permeability = permeability;
	}

	public Double getArppu() {
		return arppu;
	}

	public void setArppu(Double arppu) {
		this.arppu = arppu;
	}

	public Double getNewCount() {
		return newCount;
	}

	public void setNewCount(Double newCount) {
		this.newCount = newCount;
	}

	public Double getNewStay() {
		return newStay;
	}

	public void setNewStay(Double newStay) {
		this.newStay = newStay;
	}

	public Double getNewStayRat() {
		return newStayRat;
	}

	public void setNewStayRat(Double newStayRat) {
		this.newStayRat = newStayRat;
	}

	public Double getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(Double activeCount) {
		this.activeCount = activeCount;
	}

	public Double getActiveStay() {
		return activeStay;
	}

	public void setActiveStay(Double activeStay) {
		this.activeStay = activeStay;
	}

	public Double getActiveStayRat() {
		return activeStayRat;
	}

	public void setActiveStayRat(Double activeStayRat) {
		this.activeStayRat = activeStayRat;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserData [gameName=");
		builder.append(gameName);
		builder.append(", serverName=");
		builder.append(serverName);
		builder.append(", totalRecharge=");
		builder.append(totalRecharge);
		builder.append(", rechargeCount=");
		builder.append(rechargeCount);
		builder.append(", permeability=");
		builder.append(permeability);
		builder.append(", arppu=");
		builder.append(arppu);
		builder.append(", newCount=");
		builder.append(newCount);
		builder.append(", newStay=");
		builder.append(newStay);
		builder.append(", newStayRat=");
		builder.append(newStayRat);
		builder.append(", activeCount=");
		builder.append(activeCount);
		builder.append(", activeStay=");
		builder.append(activeStay);
		builder.append(", activeStayRat=");
		builder.append(activeStayRat);
		builder.append("]");
		return builder.toString();
	}

}
