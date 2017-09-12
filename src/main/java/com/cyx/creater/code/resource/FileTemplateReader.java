package com.cyx.creater.code.resource;

import java.io.*;

public class FileTemplateReader implements ITemplateReader {

    private String dir;
    private String charset;

    public FileTemplateReader(String dir, String charset) {
        this.dir = dir;
        this.charset = charset;
    }

    @Override
    public String read() {
        return getTemplateContent(dir, charset);
    }

    public String getTemplateContent(String dir, String charset) {
        File file = new File(dir);
        if (!file.exists() || file.isDirectory()) {
            return "";
        }
        byte[] readSize = new byte[1024];
        int len = -1;
        try {
            FileInputStream fis = new FileInputStream(file);
            StringBuffer sb = new StringBuffer("");
            while ((len = fis.read(readSize)) != -1) {
                sb.append(new String(readSize, 0, len, charset));
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            return "";
        } catch (IOException e) {
            return "";
        }
    }
}
