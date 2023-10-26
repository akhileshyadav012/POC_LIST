package com.pattern.abstract_factory;

public class WebDeveloper implements Employee{
    @Override
    public int getSalary() {
        System.out.println("Getting salary of Web Developer");
        return 35000;
    }

    @Override
    public String getName() {
        System.out.println("Getting name of Web Developer");
        return "Akhilesh Yadav - Web Developer";
    }
}
