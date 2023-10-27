package com.pattern.builder;

public class App {
    public static void main(String[] args) {

//        User user = new User.UserBuilder()
//                .setId("1")
//                .setName("AKhilesh Yadav")
//                .setEmail("akhyad54321@gmail.com")
//                .build();
//
//        System.out.println(user);

//        Product product = new Product.ProductBuilder().setBuilderId(1)
//                .setBuilderName("Akhilesh")
//                .setBuilderLocation("Airoli")
//                .build();
//
//        System.out.println(product);

        Address build = Address.builder().colony("j56").location("airoli").build();
        System.out.println(build);
    }
}
