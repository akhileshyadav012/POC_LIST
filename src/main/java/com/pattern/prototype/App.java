package com.pattern.prototype;

public class App {
    public static void main(String[] args) throws InterruptedException {

        Employee e1 = new Employee();
        e1.setName("AKhilesh");
        e1.loadUser();
        System.out.println(e1);

        try{
            Employee e2 = (Employee)e1.clone();
            System.out.println(e2);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
