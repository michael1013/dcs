package cn.gnetop.dcs.dao.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

import cn.gnetop.dcs.dao.base.IBaseMapper;
import cn.gnetop.dcs.dao.schema.LoginLog;

public interface LoginLogMapper extends IBaseMapper<LoginLog> {
	
	@Select({"<script>",
			"SELECT",
			"date_format(create_time, \"%Y-%m-%d\") date, ",
			"IFNULL(COUNT(1), 0) count",
			"FROM",
			"t_dcs_login_log",
			"WHERE 0 = 0",
			"<if test=\"begin != null and begin != ''\">AND create_time &gt; #{begin}</if>",
			"<if test=\"end != null and end != ''\">AND create_time &lt; #{end}</if>",
			"GROUP BY date_format(create_time, \"%Y-%m-%d\")",
			 "</script>"})
	Map<String, String> findActivity(LoginLog l);
	
}
