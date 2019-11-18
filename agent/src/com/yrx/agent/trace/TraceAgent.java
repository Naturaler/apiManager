package com.yrx.agent.trace;

import com.yrx.agent.transformer.TraceTransformer;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.jar.JarFile;

/**
 * Created by r.x on 2019/10/22.
 */
public class TraceAgent {

    public static void premain(String option, Instrumentation ins) {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile("E:\\ppmoney\\project\\agentcollector\\target\\agent-collector-1.0-SNAPSHOT-jar-with-dependencies.jar");
        } catch (IOException e) {
            System.out.println("jar file construct error ! msg:{" + e.getMessage() + "}");
            e.printStackTrace();
        }
        // ins.appendToBootstrapClassLoaderSearch(jarFile);
        ins.appendToSystemClassLoaderSearch(jarFile);
        ins.addTransformer(new TraceTransformer());
    }

    public static void main(String[] args) {
    }
}
