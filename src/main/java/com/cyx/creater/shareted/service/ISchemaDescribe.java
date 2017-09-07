package com.cyx.creater.shareted.service;

import com.cyx.creater.shareted.bean.SchemataInfo;

import java.util.List;

public interface ISchemaDescribe {

    List<SchemataInfo> getSchemataDescribe();

    SchemataInfo getSchemaDescribe();

    List<String> getSchemataName();

    String getSchemaName();

}
