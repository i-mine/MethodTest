package com.mobvista.leidu;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

    static class User{
        private String name;
        private int age;
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
    static AtomicReference<User> atomicReference  = new AtomicReference<User>();

    public static void main(String[] args) {
        User user = new User("dulei",20);
        atomicReference.set(user);
        User updateUser = new User("landmine",23);
        atomicReference.compareAndSet(user, updateUser);
        System.out.println(atomicReference.get().getName()+","+atomicReference.get().getAge());
    }
}
