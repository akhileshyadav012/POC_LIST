package com.mapstruct.program;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(5);


        int result = 1;
        for (int i : list){
            result = i*result;
        }
        System.out.println(result);

    }
}
