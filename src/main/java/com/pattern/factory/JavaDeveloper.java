package com.pattern.factory;

public class JavaDeveloper implements Employee{
    @Override
    public int getSalary() {
        System.out.println("Getting salary of Java Developer");
        return 55000;
    }
}
