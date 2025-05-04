package org.example.finalproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class BuyerM {

    private final StackPane root;
    private final MainApp mainApp;

    public BuyerM(MainApp mainApp) {
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

        // VBox for buyer menu
        VBox buyerMenu = new VBox(30);

        // Create label
        Label menuLabel = new Label("Buyer Menu");
        menuLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Create buttons
        Button buyPropertyButton = new Button("Buy Property");
        Button searchPropertiesButton = new Button("Search By Price");
        Button listPropertyButton = new Button("Logout");
        Button boughtPropertiesButton = new Button("  Bought\nProperties");

        // Set button styles to make them square and set font style
        String buttonStyle = "-fx-pref-width: 150px; -fx-pref-height: 80px; -fx-font-size: 16px; -fx-font-family: 'Trebuchet MS'; -fx-background-color: rgba(128,128,128,0.59); -fx-text-fill: black";

        buyPropertyButton.setStyle(buttonStyle);
        searchPropertiesButton.setStyle(buttonStyle);
        listPropertyButton.setStyle(buttonStyle);
        boughtPropertiesButton.setStyle(buttonStyle);

        // Arrange buttons in a square
        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(40); // Increased horizontal gap
        buttonGrid.setVgap(40); // Increased vertical gap

        buttonGrid.add(buyPropertyButton, 0, 0);
        buttonGrid.add(searchPropertiesButton, 1, 0);
        buttonGrid.add(listPropertyButton, 0, 1);
        buttonGrid.add(boughtPropertiesButton, 1, 1);
        buttonGrid.setAlignment(Pos.CENTER);

        // Add label and buttons to VBox
        buyerMenu.getChildren().addAll(menuLabel, buttonGrid);
        buyerMenu.setAlignment(Pos.CENTER);

        root.getChildren().addAll(backgroundImageView, r, buyerMenu);

        StackPane.setAlignment(r, Pos.CENTER); // Ensure the rectangle is centered
        StackPane.setAlignment(buyerMenu, Pos.CENTER); // Ensure the VBox is centered

        // Set actions for buttons
        buyPropertyButton.setOnAction(event -> {
            mainApp.showPropertiesBuyScreen();
        });

        searchPropertiesButton.setOnAction(event -> {
            // Handle search properties action here
            mainApp.searchPropertiesByPrice();
        });

        listPropertyButton.setOnAction(event -> {
            // Handle list properties action here
            System.out.println("List Properties clicked");
            mainApp.showLoginScreen();
        });

        boughtPropertiesButton.setOnAction(event -> {

            //ArrayList<Buyers> buyers = new ArrayList<>();
              //     buyers.addAll( mainApp.getPropertyManagement().getBuyers());

            //if (!buyers.isEmpty()) {
              //  Buyers currentBuyer = buyers.get(buyers.size() - 1); // Assuming the last buyer is the current one
                mainApp.showBoughtPropertiesScreen(); // Call the method to display bought properties
            //} else {
                System.out.println("No buyers found.");
            //}
        });


    }

    public StackPane getRoot() {
        return root;
    }
}
