package com.cyx.creater.utils;

import org.beetl.sql.core.annotatoin.Param;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static File createFile(String path, String fileName) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File((path.endsWith(File.separator) ? path : path + File.separator) + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * 获取去除root剩下的路径
     *
     * @param root 根路径
     * @param path 路径（全）
     * @return String 去除root剩下的路径
     */
    public static String getEndPath(String root, String path) {
        //可以使用repeat实现
        String endPath = path;
        int startIndex = path.indexOf(root);
        if (startIndex != -1) {
            endPath = path.substring(startIndex + root.length(), path.length());
        }
        return endPath;
    }

    public static String[] getDirAndFileName(String filePath) {
        int startIndex = filePath.lastIndexOf(File.separator);
        String dir = filePath.substring(0, startIndex + 1);
        String fileName = filePath.substring(startIndex + File.separator.length(), filePath.length());
        return new String[]{dir, fileName};
    }

    public String getContent(InputStream fis, String charset) {
        int len = -1;
        byte[] readSize = new byte[1024];
        StringBuffer sb = new StringBuffer("");
        try {
            while ((len = fis.read(readSize)) != -1) {
                sb.append(new String(readSize, 0, len, charset));
            }
            fis.close();
        } catch (UnsupportedEncodingException e) {
            return "";
        } catch (IOException e) {
            return "";
        }
        return sb.toString();
    }

    public static List<InputStream> getFiles(String dir) {
        List<InputStream> fisList = null;
        try {
            fisList = eachFileToStream(dir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fisList;
    }

    /**
     * 查找出dir下的所有文件
     * @param dir 根目录
     * @return List<InputStream> 输出流集合
     * @throws FileNotFoundException
     */
    public static List<InputStream> eachFileToStream(String dir) throws FileNotFoundException {
        List<InputStream> fileList = new ArrayList<>();
        File file = new File(dir);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                fileList.addAll(eachFileToStream(f.getAbsolutePath()));
            } else {
                fileList.add(new FileInputStream(f));
            }
        }
        return fileList;
    }

    public static List<File> eachFile(String dir) throws FileNotFoundException {
        List<File> fileList = new ArrayList<>();
        File file = new File(dir);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                fileList.addAll(eachFile(f.getAbsolutePath()));
            } else {
                fileList.add(f);
            }
        }
        return fileList;
    }
    public static boolean isExists(String path) {
        File file = new File(path);
        return isExists(file);
    }

    public static boolean isExists(File file) {
        return file.exists();
    }
}
