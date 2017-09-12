package com.cyx.creater.code.resource;

import java.util.HashMap;
import java.util.Map;

public abstract class TemplateVariable implements ITemplateVariable {
    /**
     * 存放临时变量
     */
    protected Map<String, Object> _tempMap = new HashMap<>();

    /**
     * 存放用户变量
     */
    protected Map<String, Object> _map = new HashMap<>();

    /**
     * 存放系统变量
     */
    private Map<String, Object> _sysMap = new HashMap<>();

    @Override
    public Map<String, Object> getStaticVariable() {
        return null;
    }

    @Override
    public void putVariable(Map<String, Object> variable) {
        _map.putAll(variable);
    }

    @Override
    public void putVariable(String key, Object value) {
        _map.put(key, value);
    }
}
