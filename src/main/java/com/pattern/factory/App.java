package com.pattern.factory;

public class App {
    public static void main(String[] args) {

        Employee employeeType = EmployeeFactory.getEmployeeType("Android Developer");
        System.out.println(employeeType.getSalary());

        Employee employeeType1 = EmployeeFactory.getEmployeeType("Java DEveloper");
        System.out.println(employeeType1.getSalary());

    }
}
