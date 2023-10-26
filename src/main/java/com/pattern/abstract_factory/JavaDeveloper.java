package com.pattern.abstract_factory;

public class JavaDeveloper implements Employee{

    @Override
    public int getSalary() {
        System.out.println("Getting salary of Java Developer");
        return 65000;
    }

    @Override
    public String getName() {
        System.out.println("Getting name of Web Developer");
        return "Akhilesh Yadav - Java Developer";
    }
}
