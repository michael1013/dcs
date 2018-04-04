package cn.gnetop.dcs.console.entity;

public class Data {

	private String date;
	private Integer newUser;
	private Integer activeUser;
	private Integer registUser;
	private Integer dayActive;
	private Integer secondDayActive;
	private Integer thirdDayActive;
	private Integer weekActive;
	private Integer monthActive;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getNewUser() {
		return newUser;
	}

	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
	}

	public Integer getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(Integer activeUser) {
		this.activeUser = activeUser;
	}

	public Integer getRegistUser() {
		return registUser;
	}

	public void setRegistUser(Integer registUser) {
		this.registUser = registUser;
	}

	public Integer getDayActive() {
		return dayActive;
	}

	public void setDayActive(Integer dayActive) {
		this.dayActive = dayActive;
	}

	public Integer getSecondDayActive() {
		return secondDayActive;
	}

	public void setSecondDayActive(Integer secondDayActive) {
		this.secondDayActive = secondDayActive;
	}

	public Integer getThirdDayActive() {
		return thirdDayActive;
	}

	public void setThirdDayActive(Integer thirdDayActive) {
		this.thirdDayActive = thirdDayActive;
	}

	public Integer getWeekActive() {
		return weekActive;
	}

	public void setWeekActive(Integer weekActive) {
		this.weekActive = weekActive;
	}

	public Integer getMonthActive() {
		return monthActive;
	}

	public void setMonthActive(Integer monthActive) {
		this.monthActive = monthActive;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Data [date=");
		builder.append(date);
		builder.append(", newUser=");
		builder.append(newUser);
		builder.append(", activeUser=");
		builder.append(activeUser);
		builder.append(", registUser=");
		builder.append(registUser);
		builder.append(", dayActive=");
		builder.append(dayActive);
		builder.append(", secondDayActive=");
		builder.append(secondDayActive);
		builder.append(", thirdDayActive=");
		builder.append(thirdDayActive);
		builder.append(", weekActive=");
		builder.append(weekActive);
		builder.append(", monthActive=");
		builder.append(monthActive);
		builder.append("]");
		return builder.toString();
	}

}
