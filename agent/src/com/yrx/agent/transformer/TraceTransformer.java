package com.yrx.agent.transformer;

import jdk.internal.org.objectweb.asm.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by r.x on 2019/10/22.
 */
public class TraceTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.startsWith("com/yrx/datasourcemanager/manager/api")) {
            System.out.println("class name:{" + className + "}");
            // System.out.println(new String(classfileBuffer));
        } else {
            return null;
        }
        ClassReader reader = null;
        reader = new ClassReader(classfileBuffer);
        System.out.println("reader ----->" + reader);
        System.out.println("reader ----->" + reader.getClassName());
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
        System.out.println("writer ----->" + writer);
        ClassVisitor visitor = new ClassVisitor(Opcodes.ASM5, writer) {
            @Override
            public MethodVisitor visitMethod(int i, String name, String response, String responseSignature, String[] strings) {
                System.out.println("method name s:{" + name + "}");
                System.out.println("method name s1:{" + response + "}");
                System.out.println("method name s2:{" + responseSignature + "}");
                return super.visitMethod(i, name, response, responseSignature, strings);
            }

        };

        //忽略调试信息
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        return null;
    }
}
