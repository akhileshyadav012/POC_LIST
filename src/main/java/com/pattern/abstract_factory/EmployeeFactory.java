package com.pattern.abstract_factory;

public class EmployeeFactory {

    public static Employee getEmployee(EmployeeAbstractFactory factory){
        return factory.createEmployee();
    }
}
