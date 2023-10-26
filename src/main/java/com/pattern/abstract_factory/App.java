package com.pattern.abstract_factory;

public class App {
    public static void main(String[] args) {

        Employee e1 = EmployeeFactory.getEmployee(new WebDevFactory());
        System.out.println(e1.getName());
        System.out.println(e1.getSalary());

        System.out.println("------------------------------------------------------");

        Employee e2 = EmployeeFactory.getEmployee(new JavaDevFactory());
        System.out.println(e2.getSalary());
        System.out.println(e2.getName());
    }
}
