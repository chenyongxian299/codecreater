package com.cyx.creater.code.resource;

import java.util.Map;

public interface ITemplateVariable {

    /**
     * 获得静态（固定值）的变量集合的方法
     * @return
     */
    Map<String, Object> getStaticVariable();

    /**
     * 添加变量
     * @param variable
     */
    void putVariable(Map<String,Object> variable);
    /**
     * 添加变量
     */
    void putVariable(String key,Object value);
}
