package com.cyx.creater.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtil {
    public static ExecutorService executorService = Executors.newFixedThreadPool(5);
}
