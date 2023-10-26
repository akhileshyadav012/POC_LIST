package com.pattern.abstract_factory;

public class JavaDevFactory extends EmployeeAbstractFactory{
    @Override
    public Employee createEmployee() {
        return new JavaDeveloper();
    }
}
