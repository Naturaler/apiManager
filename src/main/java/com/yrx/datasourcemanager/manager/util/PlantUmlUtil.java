package com.yrx.datasourcemanager.manager.util;

import com.alibaba.fastjson.JSON;
import com.yrx.datasourcemanager.manager.pojo.trace.TraceTree;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by r.x on 2019/11/18.
 */
public class PlantUmlUtil {

    public static void main(String[] args) {
        readAndGenPng("E:\\tmp\\agent\\trace_v2.txt");
    }

    public static boolean readAndGenPng(String log) {
        try {
            Path path = new File(log).toPath();
            Stream<String> lines = Files.lines(path);
            List<TraceLog> list = lines
                    .filter(line -> !StringUtils.isEmpty(line))
                    .map(TraceLog::convertStrToBean)
                    .collect(Collectors.toList());
            // 先设置根节点
            TraceLog root = list.get(0);
            TraceTree<TraceLog> traceTree = new TraceTree<>(root);
            TraceTree<TraceLog> pointer = traceTree;
            for (int i = 1; i < list.size(); i++) {
                TraceLog node = list.get(i);
                if (node.flag.equals("before")) {
                    pointer = pointer.appendSon(node);
                } else {
                    if (pointer.getParent() == null) {
                        break;
                    }
                    pointer = pointer.getParent();
                }
            }
            System.out.println("JSON.toJSONString(traceTree) = " + JSON.toJSONString(traceTree));
            paint(traceTree);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void paint(TraceTree<TraceLog> traceTree) {
        String lineSeparator = System.lineSeparator();
        StringBuilder builder = new StringBuilder();
        builder.append("@startuml").append(lineSeparator);
        builder.append("start").append(lineSeparator);
        builder.append(lineSeparator);

        TraceLog root = traceTree.getValue();
        lineFormat(builder, root);
        paintSon(traceTree.getSon(), builder);

        builder.append(lineSeparator);
        builder.append("stop").append(lineSeparator);
        builder.append("@enduml").append(lineSeparator);

        System.out.println("builder.toString() = ");
        System.out.println(builder.toString());
        genPng(builder);
    }

    private static void paintSon(List<TraceTree<TraceLog>> traceTree, StringBuilder builder) {
        String lineSeparator = System.lineSeparator();
        if (traceTree.size() == 1) {
            TraceLog son = traceTree.get(0).getValue();
            lineFormat(builder, son);
            if (traceTree.get(0).getSon() != null) {
                paintSon(traceTree.get(0).getSon(), builder);
            }
        } else {
            builder.append("fork").append(lineSeparator);
            TraceTree<TraceLog> first = traceTree.get(0);
            lineFormat(builder, first.getValue());
            if (first.getSon() != null) {
                paintSon(first.getSon(), builder);
            }
            for (int i = 1; i < traceTree.size(); i++) {
                TraceTree<TraceLog> son = traceTree.get(i);
                builder.append("fork again").append(lineSeparator);
                lineFormat(builder, son.getValue());
                if (son.getSon() != null) {
                    paintSon(son.getSon(), builder);
                }
            }
            builder.append("end fork").append(lineSeparator);
        }
    }

    private static void lineFormat(StringBuilder builder, TraceLog item) {
        String lineSeparator = System.lineSeparator();
        builder.append(":")
                .append(item.className.replace("/", "_")).append("_").append(item.methodName)
                .append(";")
                .append(lineSeparator);
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
