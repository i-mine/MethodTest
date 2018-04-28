package com.dulei.fortest;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Demo1 {
    public Demo1(){}
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HashMap<String,String> method = new HashMap();
        Object demo = new Demo1();
        method.put("B","printB");
        method.put("A","printA");
        for (Map.Entry<String,String> entry:method.entrySet()
             ) {
            demo.getClass().getDeclaredMethod(entry.getValue()).invoke(demo);
        }
        System.out.println("Print may be runing or finished");
    }

    public static  void printA(){
        try {
            Thread.sleep(10000);
            System.out.println("A___________________A");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void printB(){
        try {
            Thread.sleep(1000);
            System.out.println("B___________________B");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
