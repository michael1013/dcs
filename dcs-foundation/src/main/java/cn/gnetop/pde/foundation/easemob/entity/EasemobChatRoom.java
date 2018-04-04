package cn.gnetop.pde.foundation.easemob.entity;

import java.io.Serializable;
import java.util.List;

public class EasemobChatRoom implements Serializable {

	private static final long serialVersionUID = -796061512963087541L;

	private String id;// String 聊天室 ID，聊天室唯一标识符，由环信服务器生成。
	private String name;// String 聊天室名称，任意字符串。
	private String description;// String 聊天室描述，任意字符串。
	private Integer maxusers;// Integer 聊天室成员上限，创建聊天室的时候设置，可修改。
	private Integer affiliations_count;// Integer 现有成员总数。
	private List<Object> affiliations;// Array 现有成员列表，包含了 owner 和
										// member。例如：
	// “affiliations”:[{“owner”:
	// “13800138001”},{“member”:”v3y0kf9arx”},{“member”:”xc6xrnbzci”}]。
	private String owner;// String 聊天室创建者的 username。例如：{“owner”: “13800138001”}。
	private String member;// String 聊天室成员的 username。例如： {“member”:”xc6xrnbzci”}。
	private String type;
	private String created;
	private String modified;
	private Boolean membersonly;
	private Boolean allowinvites;
	private Boolean publics;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaxusers() {
		return maxusers;
	}

	public void setMaxusers(Integer maxusers) {
		this.maxusers = maxusers;
	}

	public Integer getAffiliations_count() {
		return affiliations_count;
	}

	public void setAffiliations_count(Integer affiliations_count) {
		this.affiliations_count = affiliations_count;
	}

	public List<Object> getAffiliations() {
		return affiliations;
	}

	public void setAffiliations(List<Object> affiliations) {
		this.affiliations = affiliations;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public Boolean getMembersonly() {
		return membersonly;
	}

	public void setMembersonly(Boolean membersonly) {
		this.membersonly = membersonly;
	}

	public Boolean getAllowinvites() {
		return allowinvites;
	}

	public void setAllowinvites(Boolean allowinvites) {
		this.allowinvites = allowinvites;
	}

	public Boolean getPublic() {
		return publics;
	}

	public void setPublic(Boolean publics) {
		this.publics = publics;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EasemobChatRoom [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", maxusers=");
		builder.append(maxusers);
		builder.append(", affiliations_count=");
		builder.append(affiliations_count);
		builder.append(", affiliations=");
		builder.append(affiliations);
		builder.append(", owner=");
		builder.append(owner);
		builder.append(", member=");
		builder.append(member);
		builder.append(", type=");
		builder.append(type);
		builder.append(", created=");
		builder.append(created);
		builder.append(", modified=");
		builder.append(modified);
		builder.append(", membersonly=");
		builder.append(membersonly);
		builder.append(", allowinvites=");
		builder.append(allowinvites);
		builder.append(", publics=");
		builder.append(publics);
		builder.append("]");
		return builder.toString();
	}

}
