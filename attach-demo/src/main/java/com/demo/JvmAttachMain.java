package com.demo;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;
import java.net.URL;

public class JvmAttachMain {
    public static void main(String[] args) {
        // 输入参数，第一个参数为需要 Attach jvm pid 第二参数为 class 路径
        if(args==null||args.length<2){
            System.out.println("请输入必要参数，第一个参数为 pid，第二参数为 class 绝对路径");
            return;
        }
        String pid = args[0];
        String classPath = args[1];
        System.out.println("当前需要热更新 jvm pid 为 "+pid);
        System.out.println("更换 class 绝对路径为 "+classPath);
        // 获取当前 jar 路径
        URL jarUrl = JvmAttachMain.class.getProtectionDomain().getCodeSource().getLocation();
        String jarPath=jarUrl.getPath();
        System.out.println("当前热更新工具 jar 路径为 "+jarPath);
        VirtualMachine vm = null;//7997是待绑定的jvm进程的pid号
        try {
            vm = VirtualMachine.attach(pid);
        } catch (AttachNotSupportedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 运行最终 AgentMain 中方法
        try {
            vm.loadAgent(jarPath, classPath);
        } catch (AgentLoadException e) {
            e.printStackTrace();
        } catch (AgentInitializationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
