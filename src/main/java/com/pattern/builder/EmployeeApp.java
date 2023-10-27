package com.pattern.builder;

public class EmployeeApp {
    public static void main(String[] args) {

        Employee e1 = Employee.builder().id(1).name("Akhilesh Yadav").salary(55000.34).build();
        System.out.println("e1 = " + e1);

        Employee sameerSheikh = Employee.builder().name("sameer sheikh").build();
        System.out.println("sameer = " + sameerSheikh);

        Employee build = Employee.builder().build();
        System.out.println(build);

    }
}
