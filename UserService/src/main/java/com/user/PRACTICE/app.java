package com.user.PRACTICE;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class app {
    public static void main(String[] args) {

        Employee e1 = new Employee(1, "abh", 10000);
        Employee e2 = new Employee(2, "abkash", 15000);
        Employee e3 = new Employee(3, "sameer", 25000);
        Employee e4 = new Employee(4, "saurabh", 20000);

        List<Employee> list = new ArrayList<>();
        list.add(e2);
        list.add(e2);
        list.add(e3);
        list.add(e4);

        System.out.println(list);


        Employee employee = list.stream().max(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println("hello " +  employee);

        List<Employee> employeeList = list.stream().filter(employee1 -> employee1.getName().startsWith("ab")).collect(Collectors.toList());
        System.out.println(employeeList);

        System.out.println("=================================");

    }
}
