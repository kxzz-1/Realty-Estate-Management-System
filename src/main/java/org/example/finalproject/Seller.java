package org.example.finalproject;


import java.io.Serializable;
import java.util.ArrayList;

public class Seller extends Users implements Serializable {
    private ArrayList<Properties> sellingProperties = new ArrayList<>();


    public Seller(String name, String address, String phoneNumber, String cnic, String fathersName, String password) {
        super(name, address, phoneNumber, cnic, fathersName, password);

    }

    public Seller(ArrayList<Properties> sellingProperties) {
        this.sellingProperties = sellingProperties;
    }

    public Seller(Buyers buyers){
        super.setName(buyers.getName());
        super.setAddress(buyers.getAddress());
        super.setCnic(buyers.getCnic());
        super.setFathersName(buyers.getFathersName());

    }
    public Seller(Tenant tenant) {
        super.setName(tenant.getName());
        super.setAddress(tenant.getAddress());
        super.setCnic(tenant.getCnic());
        super.setFathersName(tenant.getFathersName());
    }


    public ArrayList<Properties> getSellingProperties() {
        return sellingProperties;
    }

    public void setSellingProperties(ArrayList<Properties> sellingProperties) {
        this.sellingProperties = sellingProperties;
    }
    public String sellerInfo(){
        return super.toString();
    }



    public void addProperty(Properties properties){
        sellingProperties.add(properties);

    }
    public void displayInfo() {
        System.out.println();
        System.out.println("***Seller Deatils***");
        System.out.println("Name : "+getName());
        System.out.println("CNIC : "+getCnic());
        displayPhoneNumber();
        addressDetails();
        for (Properties properties : sellingProperties) {
            System.out.println("The type of property is : " + properties.getPropertyType());
            System.out.println("The price of the property is : " + properties.getPrice());
            System.out.println("Address : " +getAddress());

        }

    }
}
