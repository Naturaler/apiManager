package com.yrx.agent.transformer;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
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
    /** 遍历类名和方法名 end */

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
            try {
                CtClass cls = pool.makeClass(new ByteArrayInputStream(classfileBuffer));

                CtMethod[] methods = cls.getDeclaredMethods();
                for (CtMethod method : methods) {
                    //插入本地变量
                    method.addLocalVariable("startTime", CtClass.longType);
                    String codeStrBefore = "startTime=System.currentTimeMillis();";
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("System.out.println(\"thread:[\"+Thread.currentThread().getName()+\"]")
                            .append("method:[" + className + "." + method.getName() + "] time cost [\"").append(" + (System.currentTimeMillis() - startTime) + \"]millisecond\");");

                    String codeStrAfter = stringBuilder.toString();
                    System.out.println(codeStrBefore);
                    System.out.println(codeStrAfter);
                    method.insertBefore(codeStrBefore);
                    method.insertAfter(codeStrAfter);
                }

                File file = new File("E:\\software\\intelliJ IDEA\\project\\manager\\agent\\src\\com\\yrx\\agent\\demo\\", cls.getSimpleName() + ".class");
                try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                    fileOutputStream.write(cls.toBytecode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return cls.toBytecode();
            } catch (Exception e) {
                System.out.println(" ========= add aop error ========= className:" + className);
                e.printStackTrace();
            }
        }
        return null;
    }
}
