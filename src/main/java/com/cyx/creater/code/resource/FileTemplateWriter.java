package com.cyx.creater.code.resource;

import com.cyx.creater.utils.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTemplateWriter implements TemplateWriter {
    private String filePath;
    private String charset;

    public FileTemplateWriter(String filePath, String charset) {
        this.filePath = filePath;
        this.charset = charset;
    }

    @Override
    public void writer(String templateContent) {
        String[] fp = FileUtil.getDirAndFileName(filePath);
        System.err.println(filePath);
        try {
            File file = FileUtil.createFile(fp[0], fp[1]);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(templateContent.getBytes(charset));
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
