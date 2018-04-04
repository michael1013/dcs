package cn.gnetop.dcs.server.service.userservice.handler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import cn.gnetop.dcs.dao.ApplePayDao;
import cn.gnetop.dcs.dao.UserDao;
import cn.gnetop.dcs.dao.schema.ApplePay;
import cn.gnetop.dcs.dao.schema.User;
import cn.gnetop.dcs.server.service.userservice.req.VerifyReceiptReq;
import cn.gnetop.dcs.server.service.userservice.rsp.VerifyReceiptRsp;
import cn.gnetop.dcs.system.entity.ResultCode;
import cn.gnetop.dcs.system.exception.DcsException;
import cn.gnetop.dcs.system.exception.ParamErrException;
import cn.gnetop.dcs.system.helper.ConfigManager;
import cn.gnetop.dcs.system.listener.BeanUtils;
import cn.gnetop.pde.foundation.CommonUtils;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.HttpUtils;
import cn.gnetop.pde.foundation.JsonUtils;
import cn.gnetop.pde.foundation.NumberUtils;
import cn.gnetop.pde.foundation.StringUtils;
import cn.gnetop.pde.foundation.encryte.MD5Utils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class VerifyReceiptHandler {

	private static final String APPSTORE_VERIFY_DEF_URL = "https://buy.itunes.apple.com/verifyReceipt";

	private static final String APPSTORE_VERIFY_SANDBOX_URL = "https://sandbox.itunes.apple.com/verifyReceipt";

	private static final String APPSTORE_VERIFY_URL = ConfigManager.getConfig("applepay_verify_url",
	        APPSTORE_VERIFY_DEF_URL);

	private static final String DELIVERY_PAYKEY_DEF = "gNetop0X7E2";

	private static final String DELIVERY_PAYKEY = ConfigManager.getConfig("delivery_paykey", DELIVERY_PAYKEY_DEF);

	private ApplePayDao applePayDao = BeanUtils.getBean(ApplePayDao.class);

	public VerifyReceiptRsp verifyReceipt(VerifyReceiptReq req) throws DcsException {

		String userid = req.getUserid();
		String username = req.getUsername();
		String receiptData = req.getReceiptData();
		String transid = req.getTransid();
		if (StringUtils.isEmpty(receiptData) || StringUtils.isEmpty(transid)
		        || StringUtils.isAllBlank(userid, username)) {
			throw new ParamErrException(ResultCode.PARAM_ERR);
		}

		UserDao userDao = BeanUtils.getBean(UserDao.class);
		User user = new User();
		user.setUserid(userid);
		user.setUsername(username);
		user = userDao.findOne(user);
		if (null == user) {
			throw new ParamErrException(ResultCode.USER_NOT_EXISTS);
		}
		userid = user.getUserid();
		username = user.getUsername();

		int sandbox = null != req.getSandbox() ? req.getSandbox() : 0;

		JSONObject jo = new JSONObject();
		jo.put("receipt-data", receiptData);

		String result = HttpUtils.doPost(getVerifyUrl(sandbox), null, jo.toString());
		JSONObject jr = JSONObject.fromObject(result);

		VerifyReceiptRsp rsp = new VerifyReceiptRsp();
		try {
			int status = jr.getInt("status");
			if (0 == status) {

				ApplePay p = new ApplePay();
				p.setReceipt(result);
				p.setStatus(String.valueOf(status));
				JSONObject receipt = jr.getJSONObject("receipt");
				if (receipt.containsKey("in_app")) {
					JSONArray inapps = receipt.getJSONArray("in_app");
					if (inapps.size() > 0) {
						for (int i = 0; i < inapps.size(); i++) {
							JSONObject inapp = inapps.getJSONObject(i);
							if (inapp.containsKey("transaction_id")) {
								String transactionId = inapp.getString("transaction_id");
								if (!transactionId.equals(req.getTransid())) {
									continue;
								}
								p.setTransid(transid);
							}
							if (inapp.containsKey("product_id")) {
								p.setProductid(inapp.getString("product_id"));
							}
							if (inapp.containsKey("purchase_date_ms")) {
								Long dateMs = NumberUtils.toLong(inapp.getString("purchase_date_ms"));
								p.setCreateat(String.valueOf(dateMs));
							}
						}
						if (StringUtils.isEmpty(p.getTransid())) {
							throw new DcsException(ResultCode.TRANS_VERIFY_FAILED);
						}
					}
				} else {
					if (receipt.containsKey("transaction_id")) {
						p.setTransid(receipt.getString("transaction_id"));
					}
					if (receipt.containsKey("product_id")) {
						p.setProductid(receipt.getString("product_id"));
					}
					if (receipt.containsKey("bid")) {
						p.setProductid(receipt.getString("vid"));
					}
				}
				if (receipt.containsKey("bundle_id")) {
					p.setBundleid(receipt.getString("bundle_id"));
				}
				if (receipt.containsKey("environment")) {
					p.setSandbox("Sandbox".equals(receipt.getString("environment")) ? 1 : 0);
				}

				p.setOrderid(UUID.randomUUID().toString());
				p.setConsumable(req.getConsumable());
				p.setSandbox(sandbox);
				p.setUserid(userid);
				p.setUsername(username);
				p.setChannel(req.getChannel());
				p.setAppid(req.getAppid());
				p.setPrice(req.getPrice());
				p.setCurrency(req.getCurrency());
				p.setProductname(req.getProductname());
				p.setCreateTime(DateUtils.getDateString());
				p.setShippingUrl(req.getShippingUrl());
				p.setGameServerId(req.getGameServerId());
				p.setExtra(req.getExtraInfo());
				p.setDeliveryFlag("0");
				int insertResult = applePayDao.insert(p);

				// 如果插入数据数量为1, 则说明该数据为新数据, 需要发起充值发货
				if (1 == insertResult) {
					delivery(p, req.getExtraInfo());
				}
				rsp.putData("receiptDate", p);
			} else {
				throw new DcsException(ResultCode.TRANS_VERIFY_FAILED);
			}
		} catch (Exception e) {
			throw new DcsException(e);
		}

		return rsp;
	}

	private static String getVerifyUrl(int sandbox) {
		return sandbox == 1 ? APPSTORE_VERIFY_SANDBOX_URL : APPSTORE_VERIFY_URL;
	}

	private void delivery(ApplePay p, String extraInfo) {
		if (!StringUtils.hasBlank(p.getShippingUrl(), p.getGameServerId())) {
			Map<String, String> map = new LinkedHashMap<>();
			map.put("transid", p.getTransid());
			map.put("orderid", p.getOrderid());
			map.put("userid", p.getUserid());
			map.put("appid", p.getAppid());
			map.put("bundleid", p.getBundleid());
			map.put("productid", p.getProductid());
			map.put("gameserverid", p.getGameServerId());
			map.put("price", p.getPrice());
			map.put("currency", p.getCurrency());
			map.put("sandbox", String.valueOf(p.getSandbox()));
			map.put("createdatems", p.getCreateat());
			String attach = "";
			if (StringUtils.isNotBlank(extraInfo)) {
				attach = CommonUtils.urlEncode(JsonUtils.toString(extraInfo));
			}
			map.put("attach", attach);

			Map<String, String> paramMap = new LinkedHashMap<>();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (StringUtils.isNotBlank(entry.getValue())) {
					paramMap.put(entry.getKey(), entry.getValue());
				}
			}

			StringBuffer valueBuffer = new StringBuffer();
			for (String value : paramMap.values()) {
				valueBuffer.append(value);
			}
			valueBuffer.append(DELIVERY_PAYKEY);
			paramMap.put("sign", MD5Utils.encrypt(valueBuffer.toString().toLowerCase()));
			String response = HttpUtils.doPost(p.getShippingUrl(), "thirdpart", null,
			        JSONObject.fromObject(paramMap).toString());
			if (StringUtils.isNotBlank(response) && response.startsWith("{") && response.endsWith("}")) {
				JSONObject jrsp = JSONObject.fromObject(response);
				if (jrsp.has("status") && "0".equals(jrsp.getString("status"))) {
					p.setDeliveryFlag("1");
					ApplePay pay = new ApplePay();
					pay.setId(p.getId());
					pay.setDeliveryFlag("1");
					applePayDao.update(pay);
				}
			}
		}
	}

}
