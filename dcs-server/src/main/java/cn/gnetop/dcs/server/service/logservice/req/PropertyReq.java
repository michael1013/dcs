package cn.gnetop.dcs.server.service.logservice.req;

import cn.gnetop.dcs.system.entity.Request;
import cn.gnetop.dcs.system.helper.DcsComment;

@DcsComment(comment = "道具信息")
public class PropertyReq extends Request {

	private static final long serialVersionUID = -3375349395096049253L;

	@DcsComment(comment = "道具id", value = "123456")
	private String propertyid;
	@DcsComment(comment = "类型", value = "abc123")
	private String type;
	@DcsComment(comment = "价格", value = "123456")
	private String price;
	@DcsComment(comment = "游戏id", value = "123456")
	private String gameid;

	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public String getPropertyid() {
		return propertyid;
	}

	public void setPropertyid(String propertyid) {
		this.propertyid = propertyid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PropertyReq [propertyid=");
		builder.append(propertyid);
		builder.append(", type=");
		builder.append(type);
		builder.append(", price=");
		builder.append(price);
		builder.append(", gameid=");
		builder.append(gameid);
		builder.append("]");
		return builder.toString();
	}

}
