package com.mobvista.leidu;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {
    static class User{
        private String name;
        public volatile int age;
        public User(String name, int age){
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }
    private  static AtomicIntegerFieldUpdater<User>
            atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater
            .newUpdater(User.class,"age");

    public static void main(String[] args) {
        User user = new User("dulei",20);
        System.out.println(atomicIntegerFieldUpdater.getAndIncrement(user));
        System.out.println(atomicIntegerFieldUpdater.get(user));
    }
}
