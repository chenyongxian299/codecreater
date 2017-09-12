package com.cyx.creater.code.resource;

import com.cyx.creater.utils.FileUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlTemplateVariable extends TemplateVariable {
    private String xmlPath;


    public XmlTemplateVariable(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    @Override
    public Map<String, Object> getStaticVariable() {
        return xmlToMap();
    }

    @Override
    public void putVariable(Map<String, Object> variable) {
        _map.putAll(variable);
    }

    public void putTempVariable(Map<String, Object> tempVariable) {
        _tempMap.putAll(tempVariable);
    }

    public void putTempVariable(String key, Object variable) {
        _tempMap.put(key, variable);
    }

    public Map<String, Object> xmlToMap() {
        String xmlContent = FileUtil.getContent(xmlPath, "utf8");
        try {
            Document document = DocumentHelper.parseText(xmlContent);
            // 获取文档的根节点.
            Element root = document.getRootElement();
            Element varRoot = root.element("template_var");
            List<Element> varList = varRoot.elements("var");
            for (Element var : varList) {
                _map.put(var.attributeValue("key"), var.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            return _map;
        }
    }
}
