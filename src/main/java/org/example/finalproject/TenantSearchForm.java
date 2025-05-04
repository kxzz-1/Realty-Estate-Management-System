package org.example.finalproject;



import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashSet;

public class TenantSearchForm {
    ArrayList<Properties> foundProperties = new ArrayList<>();

    private final MainApp mainApp;
    private final StackPane root;
    private PropertyManagement propertyManagement = null;

    public TenantSearchForm(MainApp mainApp, PropertyManagement propertyManagement) {
        this.mainApp = mainApp;
        this.propertyManagement = propertyManagement; // Initialize property management here
        this.root = new StackPane();

        // Background Image
        Image backgroundImage = new Image("file:C:/Users/hp/Downloads/blurbg.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(false);  // Disable aspect ratio preserving

        // Semi-transparent Rectangle with rounded corners
        Rectangle r = new Rectangle(600, 400);
        r.setFill(Color.WHITE);
        r.setOpacity(0.5);
        r.setArcWidth(20);
        r.setArcHeight(20);

        // VBox for search options
        VBox searchOptions = new VBox(10);

        // "Search by Price" label
        Label searchByPriceLabel = new Label("Search by Price and Rent");
        searchByPriceLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Select Price Range label
        Label selectPriceRangeLabel = new Label("Select Price Range");

        // TextFields for minimum and maximum price
        TextField minPriceField = new TextField();
        minPriceField.setPromptText("Minimum Price");

        TextField maxPriceField = new TextField();
        maxPriceField.setPromptText("Maximum Price");

        HBox priceFields = new HBox(10);
        priceFields.getChildren().addAll(minPriceField, maxPriceField);
        priceFields.setAlignment(Pos.CENTER); // Center the text fields

        // Search button
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            // Handle search action here
            try {
                double minPrice = Double.parseDouble(minPriceField.getText());
                double maxPrice = Double.parseDouble(maxPriceField.getText());
                foundProperties = new ArrayList<>(mainApp.retrievePropertiesByRent(minPrice, maxPrice));
               // ArrayList<Properties> fProperties = new ArrayList<>(new HashSet<>(foundProperties));

                mainApp.showTenantPropertiesScreen(foundProperties);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter valid price values.");

            }
        });

        // Back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            mainApp.showTenantScreen();
        });

        searchOptions.getChildren().addAll(searchByPriceLabel, selectPriceRangeLabel, priceFields, searchButton, backButton);
        searchOptions.setAlignment(Pos.CENTER);

        root.getChildren().addAll(backgroundImageView, r, searchOptions);

        StackPane.setAlignment(r, Pos.CENTER); // Ensure the rectangle is centered
        StackPane.setAlignment(searchOptions, Pos.CENTER); // Ensure the VBox is centered
    }

    public StackPane getRoot() {
        return root;
    }
}
