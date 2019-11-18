package com.yrx.datasourcemanager.manager.util;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.stream.Stream;

/**
 * Created by r.x on 2019/11/18.
 */
public class PlantUmlUtil {

    public static void main(String[] args) {
        System.out.println("TraceLog.convertStrToBean(\"\") = " + TraceLog.convertStrToBean(""));
    }

    public static boolean readAndGenPng(String log) {
        try {
            Path path = new File(log).toPath();
            Stream<String> lines = Files.lines(path);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Data
    private static class TraceLog {
        private String flag;
        private Date time;
        private String thread;
        private String className;
        private String methodName;

        static TraceLog convertStrToBean(String source) {
            String s = "{\"className\":\"com/yrx/datasourcemanager/manager/pojo/BlogExample\",\"flag\":\"after\",\"methodName\":\"createCriteria\",\"thread\":\"http-nio-9090-exec-10\",\"time\":1574094544236}";
            return JSON.parseObject(s, TraceLog.class);
        }
    }
}
