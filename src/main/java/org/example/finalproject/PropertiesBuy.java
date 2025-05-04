package org.example.finalproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

public class PropertiesBuy {
    private final MainApp mainApp;
    private final StackPane root;

    public PropertiesBuy(MainApp mainApp) {
        this.mainApp = mainApp;
        root = new StackPane();

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
        backButton.setOnAction(e -> mainApp.showBuyerScreen());  // Go back to the buyer menu screen

        // Label for Properties
        Label propertiesLabel = new Label("Properties Available for Purchase");
        propertiesLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: black");
        propertiesLabel.setPadding(new Insets(10));
        propertiesLabel.setAlignment(Pos.CENTER);

        // HBox to contain the back button and properties label
        HBox topContainer = new HBox(35, backButton, propertiesLabel);
        topContainer.setAlignment(Pos.CENTER_LEFT);
        topContainer.setPadding(new Insets(10));

        // GridPane for property boxes
        GridPane propertyGrid = new GridPane();
        propertyGrid.setAlignment(Pos.CENTER);
        propertyGrid.setHgap(10);
        propertyGrid.setVgap(20);
        propertyGrid.setPadding(new Insets(20));
        propertyGrid.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        // ScrollPane to allow scrolling if properties exceed the available space
        ScrollPane scrollPane = new ScrollPane(propertyGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setMaxHeight(300);  // Adjust the height of the ScrollPane

        // Add some properties to the propertyManagement for demonstration
        String sale = "Sale";
        ArrayList<Properties> propertiesList = new ArrayList<>();

        for (Properties prop : mainApp.getPropertyManagement().getProperties()) {
            if (prop.getSaleOrRent().equalsIgnoreCase(sale) && prop.isAvailable()) {
                propertiesList.add(prop);
            }
        }

        for (int i = 0; i < propertiesList.size(); i++) {
            addPropertyBox(propertyGrid, i / 2, i % 2, propertiesList.get(i));
        }

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

    private void addPropertyBox(GridPane gridPane, int rowIndex, int columnIndex, Properties property) {
        // Check if the property is already present in the gridPane
        if (!isPropertyPresent(gridPane, property)) {
            VBox propertyBox = new VBox(5);
            propertyBox.setAlignment(Pos.CENTER);
            propertyBox.setPadding(new Insets(10));
            propertyBox.setPrefWidth(150);
            propertyBox.setMinHeight(190);
            propertyBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            String style = "-fx-text-fill: black; -fx-font-family: Trebuchet MS;";

            Label propertyIdLabel = new Label("Property ID: " + property.getPropertyId());
            Label typeLabel = new Label("Type: " + property.getPropertyType());
            Label sizeLabel = new Label("Size: " + property.getSize() + " sqft");
            Label priceLabel = new Label("Price: $" + property.getPrice());
            Label addressLabel = new Label("Address: " + property.getAddress());
            Label owner = new Label("Owner Cnic: " + (property.getSeller() != null ? property.getSeller().getCnic() : "N/A"));
            Label ownerContact = new Label("Contact Number: " + (property.getSeller() != null ? property.getSeller().getPhoneNumber() : "N/A"));

            propertyIdLabel.setStyle(style);
            typeLabel.setStyle(style);
            sizeLabel.setStyle(style);
            priceLabel.setStyle(style);
            addressLabel.setStyle(style);
            owner.setStyle(style);
            ownerContact.setStyle(style);

            Button buy = new Button("Buy");
            buy.setOnAction(q -> {
                String cnic = owner.getText();
                // Extract the property ID from the label text
                int id = Integer.parseInt(propertyIdLabel.getText().split(":")[1].trim());
                for (Properties prop : mainApp.getPropertyManagement().getProperties()) {
                    if (prop.getPropertyId() == id) {
                        prop.setAvailable(false);
                        prop.setBuyers(mainApp.getCurrentBuyer());
                        mainApp.getCurrentBuyer().addProperty(prop);
                    }


                }
                mainApp.showTransactionScreen(id);

            });

            propertyBox.getChildren().addAll(propertyIdLabel, typeLabel, sizeLabel, priceLabel, addressLabel, owner, ownerContact, buy);

            gridPane.add(propertyBox, columnIndex, rowIndex);
        }
    }

    // Method to check if a property is already present in the gridPane
    private boolean isPropertyPresent(GridPane gridPane, Properties property) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof VBox) {
                VBox vbox = (VBox) node;
                for (Node child : vbox.getChildren()) {
                    if (child instanceof Label) {
                        Label label = (Label) child;
                        if (label.getText().contains("Property ID: " + property.getPropertyId())) {
                            return true; // Property already present
                        }
                    }
                }
            }
        }
        return false; // Property not present
    }

    public StackPane getRoot() {
        return root;
    }
}
