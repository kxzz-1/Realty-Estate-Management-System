package org.example.finalproject;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class TenantM {
    private StackPane root;
    private final MainApp mainApp;

    public TenantM(MainApp mainApp) {
        this.mainApp = mainApp;
        root = new StackPane();

        // Background Image
        Image backgroundImage = new Image("file:C:/Users/hp/Downloads/blurbg.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        // Fit the background image to the scene height
        backgroundImageView.setPreserveRatio(false);  // Disable aspect ratio preserving

        // Semi-transparent Rectangle with rounded corners
        Rectangle r = new Rectangle(600, 400);
        r.setFill(Color.WHITE);
        r.setOpacity(0.5);
        r.setArcWidth(20);
        r.setArcHeight(20);

        // VBox for tenant menu
        VBox tenantMenu = new VBox(30);

        // Create label
        Label menuLabel = new Label("Tenant Menu");
        menuLabel.setStyle("-fx-font-size:24px; -fx-font-weight-: bold");


        // Create buttons
        Button rentPropertyButton = new Button("Rent Property");
        Button searchPropertiesButton = new Button("Search Properties");
        Button LogoutPropertyButton = new Button("Log-Out");
        Button rentedPropertiesButton = new Button("Rented\nProperty");

        // Set button styles to make them square and set font style
        String buttonStyle = "-fx-pref-width: 150px; -fx-pref-height: 80px; -fx-font-size: 16px; -fx-font-family: 'Trebuchet MS'; -fx-background-color: rgba(128,128,128,0.59); -fx-text-fill: black";

        rentPropertyButton.setStyle(buttonStyle);
        searchPropertiesButton.setStyle(buttonStyle);
        LogoutPropertyButton.setStyle(buttonStyle);
        rentedPropertiesButton.setStyle(buttonStyle);

        // Arrange buttons in a square
        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(40); // Increased horizontal gap
        buttonGrid.setVgap(40); // Increased vertical gap

        buttonGrid.add(rentPropertyButton, 0, 0);
        buttonGrid.add(searchPropertiesButton, 1, 0);
        buttonGrid.add(LogoutPropertyButton, 0, 1);
        buttonGrid.add(rentedPropertiesButton, 1, 1);
        buttonGrid.setAlignment(Pos.CENTER);

        // Add label and buttons to VBox
        tenantMenu.getChildren().addAll(menuLabel, buttonGrid);
        tenantMenu.setAlignment(Pos.CENTER);

        // Add all components to root
        root.getChildren().addAll(backgroundImageView, r, tenantMenu);

        StackPane.setAlignment(r, Pos.CENTER); // Ensure the rectangle is centered
        StackPane.setAlignment(tenantMenu, Pos.CENTER); // Ensure the VBox is centered

        // Set actions for buttons
        rentPropertyButton.setOnAction(event -> {
            // Handle add property action here
            System.out.println("Rent Property clicked");
            mainApp.showRentPropertiesAvalable();
        });

        searchPropertiesButton.setOnAction(event -> {
            // Handle list properties action here
            System.out.println("Search Properties clicked");
            mainApp.searchRentPropertiesByPrice();

            // search by price
        });

        LogoutPropertyButton.setOnAction(event -> {
            // Handle delete property action here
            mainApp.showLoginScreen();
        });

        rentedPropertiesButton.setOnAction(event -> {
            // Handle rented properties action here
            System.out.println("Rented Properties clicked");

//            ArrayList<Properties> rentedProperties = new ArrayList<>();
  //          rentedProperties.addAll(mainApp.getCurrentTenant().getPropertiesArrayList());

                    mainApp.showRentedPropertiesScreen();



        });
    }

    public StackPane getRoot() {
        return root;
    }
}
