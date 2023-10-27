package com.pattern.prototype;

public class NetworkConnectionMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Ganeshaa");

        NetworkConnection networkConnection = new NetworkConnection();
        networkConnection.setIp("120:40:101");
        networkConnection.loadVeryImportantData();
        System.out.println(networkConnection);

        try{
            NetworkConnection networkConnection1 = (NetworkConnection)networkConnection.clone();
            System.out.println(networkConnection1);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
