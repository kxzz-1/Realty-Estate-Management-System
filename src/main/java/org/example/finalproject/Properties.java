package org.example.finalproject;

import java.io.Serializable;

public class Properties implements Serializable {
    private int propertyId;
    private String propertyType;
    private double size;
    private String saleOrRent;
    private boolean available;
    private double price;
    private String address;
    private Tenant tenant;
    private Buyers buyers;
    private Seller seller;

    public Properties(String propertyType, boolean available, double price, String address, int propertyId, String saleOrRent, double size) {
        this.propertyType = propertyType;
        this.available = available;
        this.price = price;
        this.address = address;
        this.propertyId = propertyId;
        this.saleOrRent = saleOrRent;
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getSaleOrRent() {
        return saleOrRent;
    }

    public void setSaleOrRent(String saleOrRent) {
        this.saleOrRent = saleOrRent;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Buyers getBuyers() {
        return buyers;
    }

    public void setBuyers(Buyers buyers) {
        this.buyers = buyers;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public void displayInfo() {
        System.out.println("*** Property Details ***");
        System.out.println("Property ID: " + propertyId);
        System.out.println("Property Type: " + propertyType);
        System.out.println("Available: " + available);
        System.out.println("Price: " + price);
        System.out.println("Address: " + address);
        System.out.println("Sale or Rent: " + saleOrRent);
        System.out.println("Size: " + size);
        if (seller != null) {
            System.out.println("Seller: " + seller.getName());
        }
        if (tenant != null) {
            System.out.println("Tenant: " + tenant.getName());
        }
        if (buyers != null) {
            System.out.println("Buyer: " + buyers.getName());
 }
}
}