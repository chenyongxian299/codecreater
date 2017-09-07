package com.cyx.creater.code.resource;

import java.util.HashMap;
import java.util.Map;

public class XmlTemplateVariable implements TemplateVariable {
    @Override
    public Map<String, Object> getStaticVariable() {
        Map<String, Object> _map = new HashMap<>();
        _map.put("addTitle", "添加");
        _map.put("choseTitle", "选择");
        _map.put("setTitle", "编辑");
        _map.put("listUrl", "api/**/list");
        _map.put("addUrl", "api/**/add");
        _map.put("isActiveUrl", "api/**/set/is_active");
        _map.put("deleteUrl", "api/**/del");
        _map.put("setUrl", "api/**/set");
        _map.put("getUrl", "api/**/get");
        _map.put("pageFileDir", "admin_template_create");
        return _map;
    }
}
