package com.mobvista.leidu;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {
    static  int value [] = new int[]{1,2,4};
    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        int value0 = atomicIntegerArray.addAndGet(0, 1);
        System.out.println(value0);
        value0 = atomicIntegerArray.getAndSet(0, 4);
        System.out.println(value0+",setValue:"+atomicIntegerArray.get(0));
    }
}
