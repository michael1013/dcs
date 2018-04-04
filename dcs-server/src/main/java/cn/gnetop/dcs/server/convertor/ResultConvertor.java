package cn.gnetop.dcs.server.convertor;

import java.util.ArrayList;
import java.util.List;

import cn.gnetop.dcs.dao.schema.Result;

public class ResultConvertor {

	public static cn.gnetop.dcs.system.entity.Result convertor(Result result) {
		if (null != result) {
			cn.gnetop.dcs.system.entity.Result r = new cn.gnetop.dcs.system.entity.Result();
			r.setResultCode(result.getId());
			r.setResultMsg(result.getMsgCn());
			return r;
		}
		return null;
	}

	public static List<cn.gnetop.dcs.system.entity.Result> convertor(List<Result> resultList) {
		if (null != resultList) {
			List<cn.gnetop.dcs.system.entity.Result> list = new ArrayList<>();
			for (Result result : resultList) {
				list.add(convertor(result));
			}
			return list;
		}
		return null;
	}

}
