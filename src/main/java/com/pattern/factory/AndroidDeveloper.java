package com.pattern.factory;

public class AndroidDeveloper implements Employee{
    @Override
    public int getSalary() {
        System.out.println("Getting salary of Android Developer");
        return 25000;
    }
}
