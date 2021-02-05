package com.demo;


import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class AgentMain {

    public static void agentmain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException, ClassNotFoundException, IOException {
        System.out.println("开始进行热更新:>>>>");

        String classPath  = agentArgs;
//        try {
            // 读取 class 文件字节码
            RandomAccessFile raf = new RandomAccessFile(classPath, "r");
            final byte[] bytes = new byte[(int) raf.length()];
            raf.readFully(bytes);

            // 使用 asm 框架获取类名
            final  String clazzName = readClassName(bytes);


            // inst.getAllLoadedClasses 方法将会获取所有已加载的 class
            for (Class clazz : inst.getAllLoadedClasses()) {
                // 匹配需要替换 class
                if (clazz.getName().equals(clazzName)) {
                    ClassDefinition definition = new ClassDefinition(clazz, bytes);
                    // 使用指定的 class 替换当前系统正在使用 class
                    inst.redefineClasses(definition);
                    System.out.println("正在替换类:>>>>" + clazzName);
                }
            }

//        } catch (IOException | UnmodifiableClassException | ClassNotFoundException e) {
////            System.out.println("热更新数据失败");
//            e.printStackTrace();
//        }
    }

    private static String readClassName(byte[] bytes) {
        return new ClassReader(bytes).getClassName().replace("/",".");
    }
}
