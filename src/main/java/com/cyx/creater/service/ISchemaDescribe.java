package com.cyx.creater.service;

import com.cyx.creater.bean.SchemataInfo;

import java.util.List;

public interface ISchemaDescribe {

    List<SchemataInfo> getSchemataDescribe();

    SchemataInfo getSchemaDescribe();

    List<String> getSchemataName();

    String getSchemaName();

}
