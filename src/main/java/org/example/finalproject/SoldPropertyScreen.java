package org.example.finalproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class SoldPropertyScreen {
    private final MainApp mainApp;
    private final ArrayList<Properties> foundProperties = new ArrayList<>();
    private final StackPane root;
    private final VBox propertiesContainer;
    private Seller seller;

    public SoldPropertyScreen(MainApp mainApp, Seller seller) {
        this.mainApp = mainApp;
        this.seller = seller;
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

        // Back Button
        Button backButton = new Button("\uD83E\uDC68");
        backButton.setStyle("-fx-font-size: 30px; -fx-background-color: transparent; -fx-text-fill: black;");
        backButton.setPadding(new Insets(5));
        backButton.setOnAction(e -> mainApp.showSellerScreen());  // Go back to the buyer menu screen

        // Label for Properties
        Label propertiesLabel = new Label("My Properties");
        propertiesLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: black");
        propertiesLabel.setPadding(new Insets(10));
        propertiesLabel.setAlignment(Pos.CENTER);

        // HBox to contain the back button and properties label
        HBox topContainer = new HBox(35, backButton, propertiesLabel);
        topContainer.setAlignment(Pos.CENTER_LEFT);
        topContainer.setPadding(new Insets(10));

        // VBox for property boxes
        propertiesContainer = new VBox(10);
        propertiesContainer.setAlignment(Pos.CENTER);
        propertiesContainer.setPadding(new Insets(20));

        // ScrollPane to allow scrolling if properties exceed the available space
        ScrollPane scrollPane = new ScrollPane(propertiesContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setMaxHeight(300);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        // Adjust the height of the ScrollPane

        VBox scrollPaneContainer = new VBox(topContainer, scrollPane);
        scrollPaneContainer.setAlignment(Pos.TOP_CENTER);
        scrollPaneContainer.setMaxWidth(580);
        scrollPaneContainer.setMaxHeight(380);
        scrollPaneContainer.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPaneContainer.setPadding(new Insets(10, 10, 10, 10));

        StackPane contentPane = new StackPane();
        contentPane.getChildren().addAll(r, scrollPaneContainer);

        StackPane.setAlignment(r, Pos.CENTER);  // Ensure the rectangle is centered
        StackPane.setAlignment(scrollPaneContainer, Pos.CENTER);  // Ensure the ScrollPane container is centered

        root.getChildren().addAll(backgroundImageView, contentPane);
    }

    public StackPane getRoot() {
        return root;
    }

    public void displaySoldProperties(Seller seller) {
        propertiesContainer.getChildren().clear();
        foundProperties.clear(); // Clear the list before adding properties

        GridPane propertyGrid = new GridPane();
        propertyGrid.setAlignment(Pos.CENTER);
        propertyGrid.setHgap(10);
        propertyGrid.setVgap(20);
        propertyGrid.setPadding(new Insets(20));
        propertyGrid.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        // Ensure PropertyManagement is not null
        PropertyManagement propertyManagement = mainApp.getPropertyManagement();
        if (propertyManagement == null) {
            throw new NullPointerException("PropertyManagement is null");
        }

        // Get the properties list
        List<Properties> propertiesList = new ArrayList<>();
        propertiesList.addAll(propertyManagement.getProperties());
        if (propertiesList == null) {
            throw new NullPointerException("Properties list is null");
        }

        // Ensure seller and seller's CNIC are not null
        if (seller == null || seller.getCnic() == null) {
            throw new NullPointerException("Seller or seller's CNIC is null");
        }

        System.out.println("Seller CNIC: " + seller.getCnic());

        foundProperties.addAll(mainApp.getCurrentSeller().getSellingProperties());

        if (foundProperties.size() > 0) {
            System.out.println("Found properties count: " + foundProperties.size());
            for (int i = 0; i < foundProperties.size(); i++) {
                addPropertyBox(propertyGrid, i / 2, i % 2, foundProperties.get(i));
            }
        } else {
            System.out.println("No sold properties found.");
            Label noPropertiesLabel = new Label("No sold properties found.");
            noPropertiesLabel.setStyle("-fx-font-size: 18px;");
            propertiesContainer.getChildren().add(noPropertiesLabel);
        }

        propertiesContainer.getChildren().add(propertyGrid);
    }

    private void addPropertyBox(GridPane propertyGrid, int row, int col, Properties property) {
        VBox propertyBox = new VBox(10);
        propertyBox.setAlignment(Pos.CENTER);
        propertyBox.setPadding(new Insets(10));
        propertyBox.setMaxWidth(250);
        propertyBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #666666; -fx-border-width: 1px; -fx-border-radius: 5px;");

        Label idLabel = new Label("ID:"+String.valueOf(property.getPropertyId()));
        idLabel.setStyle("-fx-font-size: 16px;-fx-text-fill: black;");
        Label propertyNameLabel = new Label(property.getPropertyType());
        propertyNameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: black;");
        Label propertyPriceLabel = new Label("Price: $" + property.getPrice());
        propertyPriceLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label propertyLocationLabel = new Label("Location: " + property.getAddress());
        propertyLocationLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label propertyTypeLabel = new Label("Type: " + property.getPropertyType());
        propertyTypeLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label propertyOwnerLabel = new Label("Seller: " + property.getSeller().getName());
        propertyOwnerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label ownerPhoneNumber = new Label("Seller Phone: " + property.getSeller().getPhoneNumber());
        ownerPhoneNumber.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

        propertyBox.getChildren().addAll(propertyNameLabel,idLabel ,propertyPriceLabel, propertyLocationLabel, propertyTypeLabel, propertyOwnerLabel, ownerPhoneNumber);

        // Check if the property is sold and add buyer's information if it is
      /*  if (!property.isAvailable()) {
            Label buyerLabel = new Label("Buyer: " + property.getBuyers().getName());
            buyerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
            Label buyerPhoneNumberLabel = new Label("Buyer Phone: " + property.getBuyers().getPhoneNumber());
            buyerPhoneNumberLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
            propertyBox.getChildren().addAll(buyerLabel, buyerPhoneNumberLabel);
        }*/

        // Add Edit Button
        Button editButton = new Button("Edit");
        editButton.setStyle("-fx-font-size: 14px; -fx-background-color: #007BFF; -fx-text-fill: white;");
        editButton.setOnAction(e -> editProperty(property));  // Replace with actual edit action
        propertyBox.getChildren().add(editButton);

        propertyGrid.add(propertyBox, col, row);
    }

    private void editProperty(Properties property) {
        // Implement the edit functionality here
        System.out.println("Editing property: " + property.getAddress());
        mainApp.showEditPropertyScreen(property);

        // For example, show an edit dialog or navigate to an edit screen
    }
}
