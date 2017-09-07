package com.cyx.creater.code.resource;

import com.cyx.creater.utils.FileUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XmlTemplateVariable implements TemplateVariable {
    private String xmlPath;

    public XmlTemplateVariable(String xmlPath) {
        this.xmlPath = xmlPath;
    }

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

    public void xmlToStr() {
        String xmlContent = FileUtil.getContent(xmlPath, "utf8");
        try {
            Document document = DocumentHelper.parseText(xmlContent);
            // 获取文档的根节点.
            Element root = document.getRootElement();
            root.elements("var");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
