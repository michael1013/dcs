package cn.gnetop.dcs.portal.excel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.gnetop.dcs.portal.excel.entity.CommonRecharge;

public class InsertRechargeManager {

	public static void save(List<? extends CommonRecharge> rechargeList) {
		if (CollectionUtils.isEmpty(rechargeList)) {
			System.err.println("list is empty");
			return;
		}
		String resource = "conf/mybatis.xml";
		try {
			File conf = new File(resource);
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(new FileInputStream(conf));
			SqlSession session = ssf.openSession();
			for (CommonRecharge r : rechargeList) {
				if (r.isValid()) {
					try {
						session.insert("cn.gnetop.dcs.dao.schema.Recharge.insert", r);
					} catch (Exception e) {
						System.err.println(r.getFile());
						e.printStackTrace();
					}
				}
			}
			session.commit();
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void print(List<?> rechargeList) {
		for (Object recharge : rechargeList) {
			System.out.println(recharge);
		}
	}

	public static void saveFile(List<? extends CommonRecharge> rechargeList, String filePath) {
		String filename = filePath.substring(filePath.lastIndexOf('/'));
		File file = new File("D:/My Document/sdk/logs/txt2/" + filename + ".log");
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			for (CommonRecharge commonRecharge : rechargeList) {
				bw.write(commonRecharge.getSql() + '\n');
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bw) {
					bw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
