package com.pattern.builder;

public class App {
    public static void main(String[] args) {

        User user = new User.UserBuilder()
                .setId("1")
                .setName("AKhilesh Yadav")
                .setEmail("akhyad54321@gmail.com")
                .build();

        System.out.println(user);
    }
}
