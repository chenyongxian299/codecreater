package com.cyx.creater.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  /*  public static Map<String, String> xmlToMap() {

    }*/



    /*public static Map<String, String> fileToMap(String path) {
        File file = new File(path);
        return fileToMap(file);
    }*/

}