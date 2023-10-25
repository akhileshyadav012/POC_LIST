package com.pattern.factory;

public class EmployeeFactory {

    public static Employee getEmployeeType(String employee){
        if (employee.trim().equalsIgnoreCase("Android Developer")){
            return new AndroidDeveloper();
        } else if (employee.trim().equalsIgnoreCase("Java Developer")) {
            return new JavaDeveloper();
        }else {
            return null;
        }
    }

}
