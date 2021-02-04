package com.demo;

import java.lang.management.ManagementFactory;

public class TestObject {

    public static void main(String[] args) {

        while (true){
            System.out.println("hello world");
            System.out.println(getPid());
        }
    }

    /**
     * 获取当前运行 JVM PID
     * @return
     */
    private static String getPid() {
        // get name representing the running Java virtual machine.
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
        // get pid
        return name.split("@")[0];
    }
}
