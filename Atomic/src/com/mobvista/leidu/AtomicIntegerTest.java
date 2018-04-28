package com.mobvista.leidu;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        //在原有的基础上加并返回新值
        System.out.println("the atomic value is:"+atomicInteger.addAndGet(3));
        //在原有的基础上加1并返回新值
        int value = atomicInteger.incrementAndGet();
        System.out.println("the atomic value is:"+value);
        //设置为新值，返回旧值
        int setValue = atomicInteger.getAndSet(3);
        System.out.println("the atomic beforevalue is:"+setValue);
        System.out.println("the atomic value is:"+atomicInteger);
        //
        System.out.println("update value:"+atomicInteger.compareAndSet(3,5));
        System.out.println("the current value is "+atomicInteger);

    }
}
