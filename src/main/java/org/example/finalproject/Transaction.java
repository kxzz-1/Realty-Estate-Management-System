package org.example.finalproject;


public class Transaction {
    private double totalTransaction;
    private Seller seller;
    private Tenant tenant;
    private  Buyers buyers;
    private Properties properties;

    public Transaction(double totalTransaction, Seller seller, Buyers buyers, Properties properties) {
        this.totalTransaction = totalTransaction;
        this.seller = seller;
        this.buyers = buyers;
        this.properties = properties;
    }

    public Transaction(double totalTransaction, Seller seller, Tenant tenant, Properties properties) {
        this.totalTransaction = totalTransaction;
        this.seller = seller;
        this.tenant = tenant;
        this.properties = properties;
    }
    public void displayTransactionRent(){
        System.out.println("Total transaction amount  : "+totalTransaction);
        System.out.println("Seller name :"+seller.getName());
        System.out.println("Seller CNIC"+seller.getCnic());
        System.out.println("Tenant : "+tenant.getName());
        System.out.println("Tenant CNIC : "+tenant.getCnic());
        System.out.println(properties.toString());
    }

    public void displayTransactionSale(){
        System.out.println();
        System.out.println("***Transaction Details***");
        System.out.println("Total transaction amount  : "+totalTransaction);
        System.out.println("Seller name :"+seller.getName());
        System.out.println("Seller CNIC : "+seller.getCnic());
        System.out.println("Buyer name : "+buyers.getName());
        System.out.println("Buyer id : "+buyers.getCnic());
        System.out.println(properties.toString());
    }
}
