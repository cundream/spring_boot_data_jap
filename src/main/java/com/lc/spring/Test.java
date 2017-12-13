package com.lc.spring;

public class Test {
    public static void main(String[] args) {
        String a1="hello world";
        String a2="hello world";
        String b1=new String("hello world");
        String b2=new String("hello world");
        System.out.println(a1.equals(a2));
        System.out.println(b1.equals(b2));
        System.out.println(a1.equals(b1));
        System.out.println(a1==a2);
        System.out.println(b1==b2);
        System.out.println(a1==b1);

    }
}
