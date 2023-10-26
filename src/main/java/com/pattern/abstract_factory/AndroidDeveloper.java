package com.pattern.abstract_factory;

public class AndroidDeveloper implements Employee {
    @Override
    public int getSalary() {
        System.out.println("Getting salary of Android Developer");
        return 15000;
    }

    @Override
    public String getName() {
        System.out.println("Getting name of Android Developer");
        return "Akash Kesarwani - Android Developer";
    }
}
