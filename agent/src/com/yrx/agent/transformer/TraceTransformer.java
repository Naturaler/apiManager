package com.yrx.agent.transformer;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by r.x on 2019/10/22.
 */
public class TraceTransformer implements ClassFileTransformer {

    /** 遍历类名和方法名 start */
    /*@Override
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
    }*/

    /**
     * 遍历类名和方法名 end
     */

    private byte[] addAop(String className, String methodName) {
        String currentMethodStr = "System.out.println(\"Thread.currentThread().getStackTrace()[1].getMethodName() = \" + Thread.currentThread().getStackTrace()[1].getMethodName());";
        ClassPool cp = ClassPool.getDefault();
        try {
            CtClass ctClass = cp.get(className);
            CtMethod declaredMethod = ctClass.getDeclaredMethod(methodName);
            declaredMethod.insertBefore(currentMethodStr);


            return ctClass.toBytecode();
        } catch (NotFoundException | CannotCompileException | IOException e) {
            System.out.println("==================== add aop error ! ====================");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // todo 需要剔除接口
        if (className.startsWith("com/yrx/datasourcemanager/manager/") && !className.contains("$")) {
            ClassPool pool = new ClassPool(true);
            pool.appendClassPath(new LoaderClassPath(loader));
            // try {
            //     // pool.appendClassPath("E:\\ppmoney\\project\\agentcollector\\target");
            //     pool.appendClassPath("E:\\ppmoney\\project\\agentcollector\\target\\agent-collector-1.0-SNAPSHOT.jar");
            // } catch (NotFoundException e) {
            //     System.out.println("appendClassPath error! msg:{" + e.getMessage() + "}");
            //     e.printStackTrace();
            // }
            try {
                CtClass cls = pool.makeClass(new ByteArrayInputStream(classfileBuffer));
                if (cls.isInterface()) {
                    return null;
                }

                CtMethod[] methods = cls.getDeclaredMethods();
                for (CtMethod method : methods) {
                    if (method.isEmpty()) {
                        System.out.println("{" + method.getName() + "} method is empty!");
                        continue;
                    }
                    String methodBefore = "com.yrx.agent.agentCollector.log.TraceLog.before(Thread.currentThread().getName(), \"" + className + "\", \"" + method.getName() + "\");";
                    String methodAfter = "com.yrx.agent.agentCollector.log.TraceLog.after(Thread.currentThread().getName(), \"" + className + "\", \"" + method.getName() + "\");";
                    System.out.println("agent collector before:" + methodBefore);
                    System.out.println("agent collector after:" + methodAfter);
                    method.insertBefore(methodBefore);
                    method.insertAfter(methodAfter);
                }

                // File file = new File("E:\\software\\intelliJ IDEA\\project\\manager\\agent\\src\\com\\yrx\\agent\\demo\\", cls.getSimpleName() + ".class");
                // try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                //     fileOutputStream.write(cls.toBytecode());
                // } catch (Exception e) {
                //     e.printStackTrace();
                // }
                return cls.toBytecode();
            } catch (Exception e) {
                System.out.println(" ========= add aop error ========= className:" + className);
                e.printStackTrace();
            }
        }
        return null;
    }
}
