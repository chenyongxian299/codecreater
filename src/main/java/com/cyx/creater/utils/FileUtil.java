package com.cyx.creater.utils;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static void createFile(String path, String fileName) throws IOException {
        if (!isExists(path)) {
            File file = new File(path);
            file.mkdirs();
        }
        if (!isExists(path + fileName)) {
            File file = new File(path + fileName);
            file.createNewFile();
        }
    }

    public static boolean isExists(String path) {
        File file = new File(path);
        return isExists(file);
    }

    public static boolean isExists(File file) {
        return file.exists();
    }
}
