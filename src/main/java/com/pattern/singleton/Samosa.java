package com.pattern.singleton;

//Lazy way to create the object:
public class Samosa {

//    3rd step: we have to initialize samosa variable,
//    so that in below method we can check whether object samosa is null or not
    private static Samosa samosa;

//    1st step: Constructor should be private, so that other class cannot call it.
    private Samosa(){

    }

//    2nd Step: Below method should be static,
//    so that it should be able to call without creating any object:
    public static Samosa getSamosa(){
        if (samosa == null){
            samosa = new Samosa();
        }
        return samosa;
    }
}
