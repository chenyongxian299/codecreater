package com.cyx.creater.code.resource;

import com.cyx.creater.utils.FileUtil;
import com.sun.org.apache.bcel.internal.generic.RET;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlTemplateVariable implements TemplateVariable {
    private String xmlPath;

    public XmlTemplateVariable(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    @Override
    public Map<String, Object> getStaticVariable() {
        return xmlToMap();
    }

    public Map<String, Object> xmlToMap() {
        String xmlContent = FileUtil.getContent(xmlPath, "utf8");
        Map<String, Object> _map = new HashMap<>();
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

  /*  public Map<String,Object> xml(Element element) {
        Map<String,Object> _map = new HashMap<>();
        List<Element> elements = element.elements();
        for (Element _element : elements) {
            if (_element.nodeCount() > 0) {
                xml(_element);
            } else {
                _map.put(_element.attributeValue("key"), _element.getText());
            }
        }
        return _map;
    }*/
}
