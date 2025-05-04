package org.example.finalproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class RentedPropertyScreen {
    private final MainApp mainApp;
    private final Tenant tenant;
    private ArrayList<Properties> foundProperties = new ArrayList<>();
    private final StackPane root;
    private final VBox propertiesContainer;

    public RentedPropertyScreen(MainApp mainApp, Tenant tenant) {
        this.mainApp = mainApp;
        this.tenant = tenant;
        this.root = new StackPane();
        this.foundProperties.addAll(tenant.getPropertiesArrayList()); // Assuming the Tenant class has a method to get rented properties

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
        backButton.setOnAction(e -> mainApp.showTenantScreen());  // Go back to the tenant screen

        // Label for Properties
        Label propertiesLabel = new Label("RENTED PROPERTIES");
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
        scrollPane.setMaxHeight(300);  // Adjust the height of the ScrollPane

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

    public void displayProperties() {
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
        List<Properties> propertiesList = propertyManagement.getProperties();
        if (propertiesList == null) {
            throw new NullPointerException("Properties list is null");
        }

        // Add properties rented by the current tenant to the foundProperties list
        foundProperties = (mainApp.getCurrentTenant().getPropertiesArrayList());

        if (!foundProperties.isEmpty()) {
            int row = 0;
            int col = 0;
            int halfSize = foundProperties.size() / 2;
            for (int i = 0; i < foundProperties.size(); i++) {
                System.out.println("count: "+foundProperties.size());
                addPropertyBox(propertyGrid, row, col, foundProperties.get(i));
                col++;
                if (col > 1) { // Assuming 2 columns
                    col = 0;
                    row++;
                }
            }

        } else {
            Label noPropertiesLabel = new Label("No properties found.");
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

        Label propertyNameLabel = new Label(property.getPropertyType());
        propertyNameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: black;");
        Label propertyPriceLabel = new Label("Rent: $" + property.getPrice());
        propertyPriceLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label propertyLocationLabel = new Label("Location: " + property.getAddress());
        propertyLocationLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label propertyTypeLabel = new Label("Type: " + property.getPropertyType());
        propertyTypeLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label propertyOwnerLabel = new Label("Owner: " + property.getSeller().getName());
        propertyOwnerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label ownerPhoneNumber = new Label("Owner Phone: " + property.getSeller().getPhoneNumber());
        ownerPhoneNumber.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

        propertyBox.getChildren().addAll(propertyNameLabel, propertyPriceLabel, propertyLocationLabel, propertyTypeLabel, propertyOwnerLabel, ownerPhoneNumber);
        propertyGrid.add(propertyBox, col, row);
    }
}
