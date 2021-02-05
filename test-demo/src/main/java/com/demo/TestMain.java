package com.demo;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(getPid());
        TestObject testObject = new TestObject();

        while (true) {
            testObject.test();
            Thread.sleep(3000);
        }
    }

    /**
     * 获取当前运行 JVM PID
     *
     * @return
     */
    private static String getPid() {
        // get name representing the running Java virtual machine.
        String name = ManagementFactory.getRuntimeMXBean().getName();
        // get pid
        return name.split("@")[0];
    }
}
