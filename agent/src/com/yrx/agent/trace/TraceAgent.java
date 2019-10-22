package com.yrx.agent.trace;

import com.yrx.agent.transformer.TraceTransformer;

import java.lang.instrument.Instrumentation;

/**
 * Created by r.x on 2019/10/22.
 */
public class TraceAgent {

    public static void premain(String option, Instrumentation ins) {
        ins.addTransformer(new TraceTransformer());
    }

    public static void main(String[] args) {

    }
}
