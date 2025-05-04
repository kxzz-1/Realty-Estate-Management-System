package org.example.finalproject;



import java.io.Serializable;
import java.util.ArrayList;

public class Tenant extends Users implements Serializable {


    private Seller seller;
    private double rentPerMonth;
    private double securityDeposit;
    private ArrayList<Properties> propertiesArrayList = new ArrayList<>();


    public Tenant(String name, String address, String phoneNumber, String cnic, String fathersName, String password) {
        super(name, address, phoneNumber, cnic, fathersName, password);
    }

    /*public Tenant(String name, String address, String phoneNumber, String cnic, String fathersName, String password, Seller seller, double rentPerMonth, double securityDeposit, ArrayList<Properties> propertiesArrayList) {
        super(name, address, phoneNumber, cnic, fathersName, password);
        this.seller = seller;
        this.rentPerMonth = rentPerMonth;
        this.securityDeposit = securityDeposit;
        this.propertiesArrayList = propertiesArrayList;
    }*/




    public void addProperties(Properties properties){
        propertiesArrayList.add(properties);
    }
    public ArrayList<Properties> getPropertiesArrayList() {
        return propertiesArrayList;
    }

    public void setPropertiesArrayList(ArrayList<Properties> propertiesArrayList) {
        this.propertiesArrayList = propertiesArrayList;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public double getRentPerMonth() {
        return rentPerMonth;
    }

    public void setRentPerMonth(double rentPerMonth) {
        this.rentPerMonth = rentPerMonth;
    }

    public double getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(double securityDeposit) {
        this.securityDeposit = securityDeposit;
    }


    @Override
    public void addressDetails() {
        super.addressDetails();
    }
    public void displayInfo() {
        System.out.println();
        System.out.println("***Tenant Details***");
        System.out.println("Name : "+getName());
        System.out.println("CNIC : " + getCnic());
        displayPhoneNumber();
        addressDetails();
        for (Properties properties : propertiesArrayList) {
            System.out.println("The type of property is : " + properties.getPropertyType());
            System.out.println("The price of the property is : " + properties.getPrice());
            System.out.println("Address : " +getAddress());

        }

    }
}



