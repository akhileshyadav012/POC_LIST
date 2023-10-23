package com.pattern.singleton;

//Eager way to create the object:
public class Jalebi {
    private static Jalebi jalebi = new Jalebi();

    private Jalebi(){
        if (jalebi != null){
            throw new RuntimeException("It is singleton class");
        }
    }
    public static Jalebi getJalebi(){
        return jalebi;
    }

}
