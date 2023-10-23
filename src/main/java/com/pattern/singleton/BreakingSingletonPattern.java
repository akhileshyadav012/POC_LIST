package com.pattern.singleton;

import java.lang.reflect.Constructor;

//        Reflection API:
public class BreakingSingletonPattern {
    public static void main(String[] args) throws Exception {

        Jalebi jalebi1 = Jalebi.getJalebi();
        System.out.println(jalebi1.hashCode());

//        Jalebi jalebi2 = Jalebi.getJalebi();
//        System.out.println(jalebi2.hashCode());

//        Constructor<Jalebi> jalebiConstructor = Jalebi.class.getDeclaredConstructor();
//        jalebiConstructor.setAccessible(true);
//        Jalebi jalebi3 = jalebiConstructor.newInstance();
//        System.out.println(jalebi3.hashCode());

    }
}
