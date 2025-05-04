package org.example.finalproject;
import java.io.Serializable;
import java.util.ArrayList;
public class Buyers extends Users implements Serializable {
    private ArrayList<Properties> ownedProperties = new ArrayList<>();

    public Buyers(String name, String address, String phoneNumber, String cnic, String fathersName, String password) {
        super(name, address, phoneNumber, cnic, fathersName, password);

    }




    public ArrayList<Properties> getOwnedProperties() {
        return ownedProperties;
    }

    public void setOwnedProperties(ArrayList<Properties> ownedProperties) {
        this.ownedProperties = ownedProperties;
    }

    @Override
    public void addressDetails() {
        super.addressDetails();
    }
    public void displayInfo() {
        System.out.println();
        System.out.println("***Buyer Details***");
        System.out.println("Name : " + getName());
        System.out.println("CNIC : " + getCnic());
        displayPhoneNumber();
        addressDetails();
        for (Properties properties : ownedProperties) {
            System.out.println("The type of property is : " + properties.getPropertyType());
            System.out.println("The price of the property is : " + properties.getPrice());
            System.out.println("Address : " +getAddress());
        }

    }

    public void addProperty(Properties properties){
        ownedProperties.add(properties);
    }
}

