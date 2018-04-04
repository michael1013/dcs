package cn.gnetop.dcs.dao.impl;

import org.springframework.stereotype.Repository;

import cn.gnetop.dcs.dao.SnapshotDao;
import cn.gnetop.dcs.dao.base.BaseDaoImpl;
import cn.gnetop.dcs.dao.schema.Snapshot;

@Repository("snapshotDao")
public class SnapshotDaoImpl extends BaseDaoImpl<Snapshot> implements SnapshotDao {

}
