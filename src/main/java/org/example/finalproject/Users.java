package org.example.finalproject;


public class Users {


    private String name;
    private String password;
    private String address;
    private String phoneNumber;
    private String cnic;
    private String fathersName;

    public Users(String name, String address ,String phoneNumber,String cnic, String fathersName, String password) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cnic = cnic;
        this.fathersName = fathersName;
        this.password = password;
    }
    public Users(){}

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }
    public void displayPhoneNumber(){
        System.out.println();
        System.out.println("***Phone Number Details***");
        System.out.println("Phone Number: "+phoneNumber);

    }
    public void addressDetails(){
        System.out.println();
        System.out.println("***Address Details***");
        System.out.println("Address: " +getAddress());
    }



}
