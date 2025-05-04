package org.example.finalproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Optional;

public class SellerM {
    private StackPane root;
    private final MainApp mainApp;

    public SellerM(MainApp mainApp) {
        this.mainApp = mainApp;
        root = new StackPane();

        // Background Image
        Image backgroundImage = new Image("file:C:/Users/hp/Downloads/blurbg.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(false);

        // Semi-transparent Rectangle with rounded corners
        Rectangle r = new Rectangle(600, 400);
        r.setFill(Color.WHITE);
        r.setOpacity(0.5);
        r.setArcWidth(20);
        r.setArcHeight(20);

        // VBox for seller menu
        VBox sellerMenu = new VBox(30);

        // Create label
        Label menuLabel = new Label("Seller Menu");
        menuLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Create buttons
        Button addPropertyButton = new Button("Add Property");
        Button logoutPropertyButton = new Button("Logout ");
        Button deletePropertyButton = new Button("Delete a \nProperty");
        Button myPropertiesButton = new Button("My Properties");

        // Set button styles to make them square and set font style
        String buttonStyle = "-fx-pref-width: 150px; -fx-pref-height: 80px; -fx-font-size: 16px; -fx-font-family: 'Trebuchet MS'; -fx-background-color: rgba(128,128,128,0.59); -fx-text-fill: black";

        addPropertyButton.setStyle(buttonStyle);
        logoutPropertyButton.setStyle(buttonStyle);
        deletePropertyButton.setStyle(buttonStyle);
        myPropertiesButton.setStyle(buttonStyle);

        // Arrange buttons in a square
        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(40);
        buttonGrid.setVgap(40);

        buttonGrid.add(addPropertyButton, 0, 0);
        buttonGrid.add(deletePropertyButton, 1, 0);
        buttonGrid.add(logoutPropertyButton, 0, 1);
        buttonGrid.add(myPropertiesButton, 1, 1);
        buttonGrid.setAlignment(Pos.CENTER);

        // Add label and buttons to VBox
        sellerMenu.getChildren().addAll(menuLabel, buttonGrid);
        sellerMenu.setAlignment(Pos.CENTER);

        root.getChildren().addAll(backgroundImageView, r, sellerMenu);

        StackPane.setAlignment(r, Pos.CENTER);
        StackPane.setAlignment(sellerMenu, Pos.CENTER);

        // Set actions for buttons
        addPropertyButton.setOnAction(event -> {
            mainApp.showAddPropertyScreen();
        });

        logoutPropertyButton.setOnAction(event -> {
            mainApp.showLoginScreen();
        });

        deletePropertyButton.setOnAction(e -> {
            mainApp.showDeletePropertyScreen();
        });



        myPropertiesButton.setOnAction(event -> {
            System.out.println("Sold Properties clicked");
            ArrayList<Properties> soldProperties = new ArrayList<>();
            soldProperties.addAll(mainApp.getCurrentSeller().getSellingProperties());

                        mainApp.showSoldPropertiesScreen();




        });

    }

    public StackPane getRoot() {
        return root;
        }
}

