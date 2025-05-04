package org.example.finalproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditProp {
    private Stage stage;
    private Properties property;
    private final MainApp mainApp;
    private StackPane root;

    public EditProp(MainApp mainApp, Properties property) {
        this.mainApp = mainApp;
        this.property = property;
        this.root = new StackPane();
        this.stage = new Stage();
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit Property");

        // Load background image
        Image backgroundImage = new Image("file:C:/Users/hp/Downloads/blurbg.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);

        // Semi-transparent Rectangle with rounded corners
        Rectangle r = new Rectangle(600, 400);
        r.setFill(Color.WHITE);
        r.setOpacity(0.7);
        r.setArcWidth(20);
        r.setArcHeight(20);

        VBox editBox = new VBox(20);
        editBox.setPadding(new Insets(10));
        editBox.setAlignment(Pos.CENTER);

        // "Edit Property" label
        Label editTitleLabel = new Label("Edit Property");
        editTitleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        editTitleLabel.setAlignment(Pos.CENTER);

        // Attribute fields
        TextField typeField = new TextField(property.getPropertyType());
        TextField sizeField = new TextField(String.valueOf(property.getSize()));
        TextField saleOrRentField = new TextField(property.getSaleOrRent());
        CheckBox availibleCheckBox = new CheckBox("Available");
        availibleCheckBox.setSelected(property.isAvailable());
        TextField priceField = new TextField(String.valueOf(property.getPrice()));
        TextField addressField = new TextField(property.getAddress());

        // Labels
        Label typeLabel = new Label("Property Type:");
        Label sizeLabel = new Label("Size:");
        Label saleOrRentLabel = new Label("Sale or Rent:");
        Label priceLabel = new Label("Price:");
        Label addressLabel = new Label("Address:");

        // Add components to the grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(typeLabel, 0, 0);
        grid.add(typeField, 1, 0);
        grid.add(sizeLabel, 0, 1);
        grid.add(sizeField, 1, 1);
        grid.add(saleOrRentLabel, 0, 2);
        grid.add(saleOrRentField, 1, 2);
        grid.add(availibleCheckBox, 1, 3);
        grid.add(priceLabel, 0, 4);
        grid.add(priceField, 1, 4);
        grid.add(addressLabel, 0, 5);
        grid.add(addressField, 1, 5);

        // Edit options
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        // Set actions for buttons
        saveButton.setOnAction(event -> {
            saveChanges(
                    typeField.getText(),
                    Double.parseDouble(sizeField.getText()),
                    saleOrRentField.getText(),
                    availibleCheckBox.isSelected(),
                    Double.parseDouble(priceField.getText()),
                    addressField.getText()
            );
        });

        cancelButton.setOnAction(event -> {
            stage.close();

        });

        // Add all components to the VBox
        editBox.getChildren().addAll(editTitleLabel, grid, saveButton, cancelButton);

        // Add all components to the root StackPane
        root.getChildren().addAll(backgroundImageView, r, editBox);

        StackPane.setAlignment(backgroundImageView, Pos.CENTER);
        StackPane.setAlignment(r, Pos.CENTER); // Ensure the rectangle is centered
        StackPane.setAlignment(editBox, Pos.CENTER); // Ensure the VBox is centered

        // Create and set the scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    private void saveChanges(String type, double size, String saleOrRent, boolean availible, double price, String address) {
        // Update the property with new details
        property.setPropertyType(type);
        property.setSize(size);
        property.setSaleOrRent(saleOrRent);
        property.setAvailable(availible);
        property.setPrice(price);
        property.setAddress(address);
        System.out.println("Property Updated");
        mainApp.showSoldPropertiesScreen();
        // Example: mainApp.saveProperty(property);
        stage.close();
    }

    public void show() {
        // Show the stage
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.showAndWait();

    }
}
