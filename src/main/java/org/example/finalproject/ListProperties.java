package org.example.finalproject;

import javafx.application.Application;
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
import javafx.stage.Stage;


public class ListProperties {
    private StackPane root;
    private final MainApp mainApp;

    public ListProperties(MainApp mainApp) {
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

        // Back Button with a back arrow
        Button backButton = new Button("\uD83E\uDC68");
        backButton.setStyle("-fx-font-size: 30px; -fx-background-color: transparent; -fx-text-fill: black;");
        backButton.setPadding(new Insets(5));
        backButton.setOnAction(e -> {
            // Handle the back button action here (e.g., navigate to a previous scene)
            mainApp.showSellerScreen(); // Example action
        });

        // Label for Properties
        Label propertiesLabel = new Label("Properties");
        propertiesLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: black");
        propertiesLabel.setPadding(new Insets(10));
        propertiesLabel.setAlignment(Pos.CENTER);

        // HBox to contain the back button and properties label
        HBox topContainer = new HBox(160, backButton, propertiesLabel);
        topContainer.setAlignment(Pos.CENTER_LEFT);
        topContainer.setPadding(new Insets(10));

        GridPane propertyGrid = new GridPane();
        propertyGrid.setAlignment(Pos.CENTER);
        propertyGrid.setHgap(10);
        propertyGrid.setVgap(20);
        propertyGrid.setPadding(new Insets(20));
        propertyGrid.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        // Add 10 sample properties (boxes)
        for (int i = 0; i < 10; i++) {
            addPropertyBox(propertyGrid, i / 2, i % 2, "Property " + (i + 1), "Description " + (i + 1), "Location " + (i + 1));
        }

        // ScrollPane to allow scrolling if properties exceed the available space
        ScrollPane scrollPane = new ScrollPane(propertyGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setMaxHeight(300);  // Decrease the height of the ScrollPane

        VBox scrollPaneContainer = new VBox(topContainer, scrollPane);
        scrollPaneContainer.setAlignment(Pos.TOP_CENTER);
        scrollPaneContainer.setMaxWidth(580);
        scrollPaneContainer.setMaxHeight(380);
        scrollPaneContainer.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPaneContainer.setPadding(new Insets(10, 10, 10, 10));

        StackPane contentPane = new StackPane();
        contentPane.getChildren().addAll(r, scrollPaneContainer);

        StackPane.setAlignment(r, Pos.CENTER); // Ensure the rectangle is centered
        StackPane.setAlignment(scrollPaneContainer, Pos.CENTER); // Ensure the ScrollPane container is centered

        StackPane root = (StackPane) this.getRoot();
        root.getChildren().addAll(backgroundImageView, contentPane);
    }

    private void addPropertyBox(GridPane gridPane, int rowIndex, int columnIndex, String propertyName, String propertyDescription, String propertyLocation) {
        VBox propertyBox = new VBox(5);
        propertyBox.setAlignment(Pos.CENTER);
        propertyBox.setPadding(new Insets(10));
        propertyBox.setPrefWidth(150);
        propertyBox.setMinHeight(190);
        propertyBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        String style = "-fx-text-fill: black; -fx-font-family: 'Trebuchet MS';";

        Label propertyIdLabel = new Label("Property ID:");
        Label typeLabel = new Label("Type:");
        Label sizeLabel = new Label("Size:");
        Label statusLabel = new Label("Status:");
        Label saleOrRent = new Label("Sale/Rent:");
        Label priceLabel = new Label("Price:");
        Label addressLabel = new Label("Address:");

        propertyIdLabel.setStyle(style);
        typeLabel.setStyle(style);
        sizeLabel.setStyle(style);
        saleOrRent.setStyle(style);
        statusLabel.setStyle(style);
        priceLabel.setStyle(style);
        addressLabel.setStyle(style);

        Button delete = new Button("Delete");
        Button edit = new Button("Edit");

        HBox deleteBox = new HBox(15);
        deleteBox.getChildren().addAll(delete, edit);

        propertyBox.getChildren().addAll(propertyIdLabel, typeLabel, sizeLabel,saleOrRent,statusLabel, priceLabel, addressLabel, deleteBox);
        deleteBox.setAlignment(Pos.BASELINE_CENTER);

        gridPane.add(propertyBox, columnIndex, rowIndex);
    }

    public StackPane getRoot() {
        return root;
    }
}