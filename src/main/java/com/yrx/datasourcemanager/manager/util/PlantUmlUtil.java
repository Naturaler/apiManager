package com.yrx.datasourcemanager.manager.util;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import net.sourceforge.plantuml.SourceStringReader;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by r.x on 2019/11/18.
 */
public class PlantUmlUtil {

    public static void main(String[] args) {
        System.out.println("TraceLog.convertStrToBean(\"\") = " + TraceLog.convertStrToBean(""));
        readAndGenPng("E:\\tmp\\agent\\trace_v2.txt");
    }

    public static boolean readAndGenPng(String log) {
        Stack<TraceLog> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        builder.append("@startuml").append(lineSeparator);
        builder.append("autonumber").append(lineSeparator);
        builder.append(lineSeparator);
        try {
            Path path = new File(log).toPath();
            Stream<String> lines = Files.lines(path);
            List<TraceLog> list = lines
                    .filter(line -> !StringUtils.isEmpty(line))
                    .map(TraceLog::convertStrToBean)
                    .collect(Collectors.toList());
            for (TraceLog traceLog : list) {
                if (traceLog.flag.equals("before")) {
                    if (stack.size() > 0) {
                        paint(stack.peek(), traceLog, builder, " -> ", lineSeparator);
                    }
                    stack.push(traceLog);
                } else {
                    TraceLog peek = stack.peek();
                    if (peek.className.equals(traceLog.className) && peek.methodName.equals(traceLog.methodName) && peek.flag.equals("before")) {
                        paint(stack.peek(), traceLog, builder, " <- ", lineSeparator);
                    }
                    paint(traceLog, stack.pop(), builder, " <- ", lineSeparator);

                }
            }
            builder.append("@enduml").append(lineSeparator);
            System.out.println("builder.toString() = " + builder.toString());
            genPng(builder);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getDirectionByFlag(String flag) {
        switch (flag) {
            case "before":
                return " -> ";
            case "after":
                return " <- ";
            default:
                return " -> ";
        }
    }

    private static void genPng(StringBuilder builder) {
        try {
            OutputStream png = new FileOutputStream(new File("E:\\tmp\\agent\\seq.png"));
            SourceStringReader reader = new SourceStringReader(builder.toString());
            String image = reader.generateImage(png);
            System.out.println(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void paint(TraceLog first, TraceLog second, StringBuilder builder, String direction, String lineSeparator) {
        builder.append(first.className.replace("/", "_")).append("_").append(first.methodName)
                .append(direction)
                .append(second.className.replace("/", "_")).append("_").append(second.methodName)
                .append(": ")
                .append(lineSeparator);
    }


    @Data
    private static class TraceLog {
        private String flag;
        private Date time;
        private String thread;
        private String className;
        private String methodName;

        static TraceLog convertStrToBean(String source) {
            return JSON.parseObject(source, TraceLog.class);
        }
    }
}
