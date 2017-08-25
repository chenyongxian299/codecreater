package com.cyx.dbtools.dao;

import com.cyx.dbtools.bean.TableInfo;
import jdk.internal.org.objectweb.asm.tree.TableSwitchInsnNode;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("info")
public interface TablesDao extends BaseMapper<TableInfo>{
    List<TableInfo> queryAllTablesBySchemata(@Param("schemaName")String schemaName);

}
