package com.pattern.abstract_factory;

public class WebDevFactory extends EmployeeAbstractFactory{
    @Override
    public Employee createEmployee() {
        return new WebDeveloper();
    }
}
