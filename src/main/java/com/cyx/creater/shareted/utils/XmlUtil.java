package com.cyx.creater.shareted.utils;

import com.cyx.creater.shareted.bean.DbConfigInfo;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXWriter;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtil {

    public static void writeDBConfig(DbConfigInfo dbConfigInfo) {
        String configPath = System.getProperty("user.dir") + File.separator + "appData" + File.separator + "db_conf.xml";
        File file = new File(configPath);
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
            Element root = document.getRootElement();
            Element dbConfig = root.element("dbConfig");
            List<Element> connList = dbConfig.elements("connName");
            boolean isNew = true;
            for (Element conn : connList) {
                if (dbConfigInfo.getConnectionName().equals(conn.attribute("name").getValue())) {
                    isNew = false;
                    conn.element("host").setText(dbConfigInfo.getHost());
                    conn.element("port").setText(String.valueOf(dbConfigInfo.getPort()));
                    conn.element("username").setText(dbConfigInfo.getUsername());
                    conn.element("password").setText(dbConfigInfo.getPassword());
                    conn.element("schema").setText(dbConfigInfo.getSchema());
                    return;
                }
            }
            if (isNew) {
                Element connElement = DocumentHelper.createElement("connName");
                connElement.addAttribute("name", dbConfigInfo.getConnectionName());
                dbConfig.add(connElement);
                Element host = DocumentHelper.createElement("host");
                host.setText(dbConfigInfo.getHost());
                connElement.add(host);
                Element port = DocumentHelper.createElement("port");
                port.setText(String.valueOf(dbConfigInfo.getPort()));
                connElement.add(port);
                Element username = DocumentHelper.createElement("username");
                username.setText(dbConfigInfo.getUsername());
                connElement.add(username);
                Element password = DocumentHelper.createElement("password");
                password.setText(dbConfigInfo.getPassword());
                connElement.add(password);
                Element schema = DocumentHelper.createElement("schema");
                schema.setText(String.valueOf(dbConfigInfo.getSchema()));
                connElement.add(schema);
            }
            FileOutputStream output = new FileOutputStream(file);
            XMLWriter writer = new XMLWriter(output);
            writer.write(document);
            writer.flush();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<DbConfigInfo> readDBConfig() {
        String configPath = System.getProperty("user.dir") + File.separator + "appData" + File.separator + "db_conf.xml";
        File file = new File(configPath);
        SAXReader reader = new SAXReader();
        Document document = null;
        List<DbConfigInfo> list = new ArrayList<>();
        try {
            document = reader.read(file);
            Element root = document.getRootElement();
            Element dbConfig = root.element("dbConfig");
            List<Element> connList = dbConfig.elements();
            for (Element conn : connList) {
                DbConfigInfo dbConfigInfo = new DbConfigInfo();
                dbConfigInfo.setConnectionName(conn.attribute("name").getValue());
                dbConfigInfo.setHost(conn.element("host").getText());
                dbConfigInfo.setPort(Integer.valueOf(conn.element("port").getText()));
                dbConfigInfo.setUsername(conn.element("username").getText());
                dbConfigInfo.setPassword(conn.element("password").getText());
                dbConfigInfo.setSchema(conn.element("schema").getText());
                list.add(dbConfigInfo);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return list;
    }
}
