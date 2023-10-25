package com.pattern.constructors;
class Parent{
    static String name = "ParentName";
}
public class Child extends Parent{
    static String name = "ChildName";

    public static void main(String[] args) {
        Child c1 = new Child();
    }
}
