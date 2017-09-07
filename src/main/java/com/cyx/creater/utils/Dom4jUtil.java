package com.cyx.creater.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

public class Dom4jUtil {

    public static Document read(String file) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File(file));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }


}