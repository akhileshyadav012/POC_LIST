package com.pattern.builder;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;

@Builder
public class Address {
    private int id;
    private String location;
    private String street;
    private String colony;

    public Address(){
        System.out.println("Empty Constructor");
    }
    public Address(int id, String location, String street, String  colony){
        this.id = id;
        this.location = location;
        this.street = street;
        this.colony = colony;
    }

    public int getId(){
        return id;
    }
    public String getLocation(){
        return location;
    }
    public String getStreet(){
        return street;
    }
    public String getColony(){
        return colony;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setStreet(String street){
        this.street = street;
    }
    public void setColony(String colony){
        this.colony = colony;
    }
    public void setLocation(String location){
        this.location = location;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", street='" + street + '\'' +
                ", colony='" + colony + '\'' +
                '}';
    }
}
