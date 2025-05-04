package org.example.finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;


import java.io.*;
import java.util.ArrayList;

public class MainApp extends Application {

    private ArrayList<Properties> properties;
    private Buyers currentBuyer;
    private Seller currentSeller;
    private Tenant lastTenant;
    private Tenant currentTenant;
    private Stage primaryStage;
    private Seller lastSeller;
    private Buyers lastBuyer;
    private PropertyManagement propertyManagement;
    // Setting the current seller
    public void setCurrentSeller(Seller seller) {
        this.currentSeller = seller;
        this.lastSeller = seller;// Update the lastSeller variable
        }
    public void setCurrentTenant(Tenant tenant){
        this.currentTenant=tenant;
        this.lastTenant=tenant;
    }

    // Setting the current buyer
    public void setCurrentBuyer(Buyers buyer) {
        this.currentBuyer = buyer;
        this.lastBuyer = buyer; // Update the lastBuyer variable
    }


    public PropertyManagement getPropertyManagement() {
        return propertyManagement;
    }

    public Buyers getCurrentBuyer() {
        return currentBuyer;
    }

    public Seller getCurrentSeller() {
        return currentSeller;
    }

    public Tenant getCurrentTenant() {
        return currentTenant;
    }

    public MainApp() {
        propertyManagement = new PropertyManagement();
        //propertyManagement.initializeProperties(); // Ensure properties are initialized
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.properties = new PropertyManagement().getProperties();

        propertyManagement.initializeProperties();
        showLoginScreen();
        primaryStage.setTitle("Your Application Title");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();

    }

    public void showBuyerScreen() {
        BuyerM buyerM = new BuyerM(this);
        Scene buyerScene = new Scene(buyerM.getRoot());
        primaryStage.setScene(buyerScene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }
    public void showEditPropertyScreen(Properties properties){
        EditProp editProp = new EditProp(this,properties);
        editProp.show();

    }

    public void showTenantScreen() {
        TenantM tenantM = new TenantM(this);
        Scene tenantScene = new Scene(tenantM.getRoot());

        primaryStage.setScene(tenantScene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }

    public void showSellerScreen() {
        SellerM sellerM = new SellerM(this);
        Scene sellerScene = new Scene(sellerM.getRoot());
        primaryStage.setScene(sellerScene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }

    public void showLoginScreen() {
        HelloApplication helloApplication = new HelloApplication(this);
        Scene loginScene = new Scene(helloApplication.getRoot());
        primaryStage.setScene(loginScene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }
    public ArrayList<Properties> retrievePropertiesByPrice(double min, double max) {
        ArrayList<Properties> fProperties = new ArrayList<>();
        for (Properties p : propertyManagement.getProperties()) {
            if (min <= p.getPrice() && max >= p.getPrice() &&p.getSaleOrRent().equalsIgnoreCase("rent")) {
                fProperties.add(p);
            }
        }
        return fProperties; // Return the list (which might be empty if no properties match the criteria)
    }

    public void showSignupScreen() {
        Signup signup = new Signup(this);
        Scene signupScene = new Scene(signup.getRoot());
        primaryStage.setScene(signupScene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }
    public void showTransactionScreen(int id){
        BTransaction buyerTransaction = new BTransaction(this, id);
        Scene buyerTransactionScene = new Scene(buyerTransaction.getRoot());
        primaryStage.setScene(buyerTransactionScene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }
    public void showRentTransactionScreen(int id){
        RTransaction rentTransaction = new RTransaction(this,id);
        Scene tenantTranactionScene = new Scene(rentTransaction.getRoot());
        primaryStage.setScene(tenantTranactionScene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }


    public void showPropertiesBuyScreen() {
        PropertiesBuy propertiesBuy = new PropertiesBuy(this);
        Scene propertiesBuyScene = new Scene(propertiesBuy.getRoot());
        primaryStage.setScene(propertiesBuyScene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }

    public void searchPropertiesByPrice() {

            SearchForm searchForm = new SearchForm(this, propertyManagement);
            primaryStage.setScene(new Scene(searchForm.getRoot()));
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        }
        public void searchRentPropertiesByPrice(){
        TenantSearchForm tenantSearchForm = new TenantSearchForm(this,propertyManagement);
        primaryStage.setScene(new Scene(tenantSearchForm.getRoot()));
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");
        }
    public void showBoughtPropertiesScreen() {
        if (currentBuyer != null) {
            BoughtPropertyScreen displayScreen = new BoughtPropertyScreen(this);
            Scene scene = new Scene(displayScreen.getRoot());
            displayScreen.displayProperties();
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");
            primaryStage.show();
        } else {
            System.out.println("No current buyer set.");
        }
    }


    public void showSoldPropertiesScreen() {
        if (currentSeller != null) { //

            SoldPropertyScreen displayScreen = new SoldPropertyScreen(this, currentSeller);
            Scene scene = new Scene(displayScreen.getRoot());
            displayScreen.displaySoldProperties(currentSeller);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");
            primaryStage.show();
        } else {
            System.out.println("No current Sellerer set.");
        }
    }
    public void showTenantPropertiesScreen(ArrayList<Properties> foundProperties) {
        TenantPropertiesDisplayScreen tenantPropertiesDisplayScreen = new TenantPropertiesDisplayScreen(this, foundProperties);
        tenantPropertiesDisplayScreen.displayProperties(foundProperties);
        Scene scene = new Scene(tenantPropertiesDisplayScreen.getRoot());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }

    public void showRentedPropertiesScreen() {
        if (currentTenant != null) { //

            RentedPropertyScreen displayScreen = new RentedPropertyScreen(this, currentTenant);
            Scene scene = new Scene(displayScreen.getRoot());
            displayScreen.displayProperties();
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");
            primaryStage.show();
        } else {
            System.out.println("No current buyer set.");
        }
    }



    public void showPropertiesDisplayScreen(ArrayList<Properties> properties) {
        PropertiesDisplayScreen displayScreen = new PropertiesDisplayScreen(this, properties);
        Scene scene = new Scene(displayScreen.getRoot());
        displayScreen.displayProperties(propertyManagement.getProperties()); // Call displayProperties to populate the screen
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show(); // Show the stage
    }

    public ArrayList<Properties> retrievePropertiesByRent(double minPrice, double maxPrice) {
        ArrayList<Properties> rentProperties = new ArrayList<>();
        for (Properties property : propertyManagement.getProperties()) {
            if (property.getSaleOrRent().equalsIgnoreCase("Rent") && property.getPrice() >= minPrice && property.getPrice() <= maxPrice && property.isAvailable()) {
                rentProperties.add(property);
            }
        }
        return rentProperties;
    }


    public void showAddPropertyScreen() {
        AddProp addPropertyScreen = new AddProp(this);
        Scene scene = new Scene(addPropertyScreen.getRoot());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }

public void showDeletePropertyScreen(){
        DeletePropertyScreen deletePropertyScreen = new DeletePropertyScreen(this);
        Scene deleteScreenScene = new Scene(deletePropertyScreen.getRoot());
        primaryStage.setScene(deleteScreenScene);
    primaryStage.setFullScreen(true);
    primaryStage.setFullScreenExitHint("");
}
public void showRentPropertiesAvalable(){
        PropertiesRent rentedPropertyScreen = new PropertiesRent(this,currentTenant);
        Scene rentPropertyScreen = new Scene(rentedPropertyScreen.getRoot());
        primaryStage.setScene(rentPropertyScreen);
    primaryStage.setFullScreen(true);
    primaryStage.setFullScreenExitHint("");

}


    public static void main(String[] args) {
        launch(args);
    }

    public ArrayList<Properties> getProperties() {
        return properties;
    }
}
