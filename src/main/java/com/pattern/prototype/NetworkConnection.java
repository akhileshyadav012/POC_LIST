package com.pattern.prototype;

public class NetworkConnection implements Cloneable{
    private String ip;
    private String data;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void loadVeryImportantData() throws InterruptedException {
        this.data = "Very very important data";
        Thread.sleep(5000);
    }

    @Override
    public String toString() {
        return "NetworkConnection{" + "ip='" + ip + '\'' + ", data='" + data + '\'' +'}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
