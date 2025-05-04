package org.example.finalproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HelloApplication  {
    private final StackPane root;
    private final MainApp mainApp;
    private ArrayList<Buyers> buyersArrayList = new ArrayList<>();
    private ArrayList<Seller> sellersArrayList = new ArrayList<>();
    private ArrayList<Tenant> tenantsArrayList = new ArrayList<>();

    public HelloApplication(MainApp mainApp) {
        this.mainApp = mainApp;
        root = new StackPane();

        Image backgroundImage = new Image("file:C:/Users/hp/Downloads/bg.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);

        Rectangle blackRectangle = new Rectangle(400, 300);
        blackRectangle.setFill(Color.rgb(0, 0, 0, 0.6));

        VBox loginBox = new VBox(10);
        loginBox.setAlignment(Pos.CENTER);

        Label loginLabel = new Label("Login");
        loginLabel.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");

        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-text-fill: white;");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: white;");
        TextField passwordField = new TextField();
        passwordField.setPrefWidth(200);

        Button loginButton = new Button("Login");

        Button signupButton = new Button("Signup");
        signupButton.setStyle("-fx-background-color: transparent; -fx-text-fill: skyblue;");

        Label messageLabel = new Label("Don't have an account?");
        messageLabel.setStyle("-fx-text-fill: white;");

        HBox signupBox = new HBox(5);
        signupBox.setAlignment(Pos.CENTER);
        signupBox.getChildren().addAll(messageLabel, signupButton);

        loginBox.getChildren().addAll(loginLabel, usernameLabel, usernameField, passwordLabel, passwordField, loginButton, signupBox);
        loginBox.setMaxWidth(200);

        root.getChildren().addAll(backgroundImageView, blackRectangle, loginBox);

        root.setAlignment(Pos.CENTER);




        loginButton.setOnAction(event -> {
            String cnic = usernameField.getText();
            String password = passwordField.getText();
            boolean matchFound = false;

            // Check for sellers
            sellersArrayList= ( mainApp.getPropertyManagement().deserializeSellerArrayListFromFile("Seller.csv"));

            for (Seller seller : sellersArrayList) {
                if (cnic.equalsIgnoreCase(seller.getCnic()) && password.equals(seller.getPassword())) {
                    matchFound = true;
                    System.out.println("Seller match found: " + seller.getCnic());
                    mainApp.setCurrentSeller(seller);
                    mainApp.showSellerScreen();
                    break;
                }
            }

            // Check for buyers
            if (!matchFound) {
                buyersArrayList= ( mainApp.getPropertyManagement().deserializeBuyerArrayListFromFile("Buyer.csv"));
                for (Buyers buyer :buyersArrayList ) {
                    System.out.println(buyersArrayList.size());

                        if(cnic.equalsIgnoreCase(buyer.getCnic()) && password.equals(buyer.getPassword()))
                        {
                            matchFound = true;
                            System.out.println("Buyer match found: " + buyer.getCnic());
                            mainApp.setCurrentBuyer(buyer);
                            mainApp.showBuyerScreen();
                            break;
                        }
                        else
                            System.out.println("buyer not found");


                }
            }

            // Check for tenants
            if (!matchFound) {
                tenantsArrayList= ( mainApp.getPropertyManagement().deserializeTenantArrayListFromFile("Tenant.csv"));

                for (Tenant tenant : tenantsArrayList) {
                    if (cnic.equalsIgnoreCase(tenant.getCnic()) && password.equals(tenant.getPassword())) {
                        matchFound = true;
                        System.out.println("Tenant match found: " + tenant.getCnic());
                        mainApp.setCurrentTenant(tenant);
                        mainApp.showTenantScreen();
                        break;
                    }
                }
            }

            // Show signup screen if no match found
            if (!matchFound) {
                System.out.println("No match found for CNIC: " + cnic);
                mainApp.showSignupScreen();
            }
        });

        signupButton.setOnAction(e -> {
            /*String cnic = usernameField.getText();
            String passward = passwordField.getText();
            for(Seller users : mainApp.getPropertyManagement().getSellers()){
                if(cnic.equalsIgnoreCase(users.getCnic()) && passward.equalsIgnoreCase(users.getPassword())){
                    mainApp.setCurrentSeller(users);
                }
            }
            for(Buyers users : mainApp.getPropertyManagement().getBuyers()){
                if(cnic.equalsIgnoreCase(users.getCnic()) && passward.equalsIgnoreCase(users.getPassword())){
                    mainApp.setCurrentBuyer(users);
                }
            }
            for(Tenant users : mainApp.getPropertyManagement().getTenants()){
                if(cnic.equalsIgnoreCase(users.getCnic()) && passward.equalsIgnoreCase(users.getPassword())){
                    mainApp.setCurrentTenant(users);
                }
            }*/
                mainApp.showSignupScreen();
            });
        }


    public StackPane getRoot() {
        return root;
    }


}
