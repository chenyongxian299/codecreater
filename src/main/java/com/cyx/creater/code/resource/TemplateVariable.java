package com.cyx.creater.code.resource;

import java.util.Map;

public interface TemplateVariable {

    /**
     * 获得静态（固定值）的变量集合的方法
     * @return
     */
    Map<String, Object> getStaticVariable();
}
