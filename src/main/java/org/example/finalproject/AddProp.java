package org.example.finalproject;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AddProp{
    TextField typeField;

    TextField sellerField;
    TextField propertyIdField;
    TextField sizeField;
    TextField statusField;
    TextField priceField;
    TextField addressField;
    TextField saleOrRentField;

    private StackPane root;
    private final MainApp mainApp;

    public AddProp(MainApp mainApp) {
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

        // VBox for add property form
        VBox addPropertyForm = new VBox(10);

        // "Add Property" label
        Label addPropertyLabel = new Label("Add Property");
        addPropertyLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        GridPane labelsAndFields = new GridPane();
        labelsAndFields.setHgap(10);
        labelsAndFields.setVgap(10);

        Label propertyIdLabel = new Label("Property ID:");
         propertyIdField = new TextField();

        Label typeLabel = new Label("Type:");
         typeField = new TextField();
        typeField.setPromptText("e.g. Plot/Apartment");

        Label sizeLabel = new Label("Size:");
         sizeField = new TextField();
        sizeField.setPromptText("Enter size in square feet");

        Label statusLabel = new Label("Status:");
         statusField = new TextField();
        statusField.setPromptText("Available/Not Available");

        Label saleOrRent = new Label("For Sale Or Rent");
         saleOrRentField = new TextField();
        saleOrRentField.setPromptText("Sale or rent");

        Label priceLabel = new Label("Price:");
         priceField = new TextField();
        priceField.setPromptText("Enter price in PKR");

        Label addressLabel = new Label("Address:");
         addressField = new TextField();
        addressField.setPromptText("Enter address");

        Label sellerLabel = new Label("Seller CNIC:");
        sellerField = new TextField();
        sellerField.setPromptText("CNIC without dashes");

        labelsAndFields.addRow(0, propertyIdLabel, propertyIdField);
        labelsAndFields.addRow(1, sellerLabel, sellerField);
        labelsAndFields.addRow(2, typeLabel, typeField);
        labelsAndFields.addRow(3, sizeLabel, sizeField);
        labelsAndFields.addRow(4, saleOrRent, saleOrRentField);
        labelsAndFields.addRow(5, statusLabel, statusField);
        labelsAndFields.addRow(6, priceLabel, priceField);
        labelsAndFields.addRow(7, addressLabel, addressField);
        labelsAndFields.setAlignment(Pos.CENTER);

        Button saveButton = new Button("Save");
        Button backButton = new Button("Back");

        HBox buttonsBox = new HBox(30, backButton, saveButton);
        buttonsBox.setAlignment(Pos.CENTER);

        addPropertyForm.getChildren().addAll(addPropertyLabel, labelsAndFields, buttonsBox);
        addPropertyForm.setAlignment(Pos.CENTER);

        StackPane s = (StackPane) this.getRoot();
        s.getChildren().addAll(backgroundImageView, r, addPropertyForm);

        StackPane.setAlignment(r, Pos.CENTER); // Ensure the rectangle is centered
        StackPane.setAlignment(addPropertyForm, Pos.CENTER); // Ensure the VBox is centered

        // Set actions for buttons

        saveButton.setOnAction(event -> {
            try {
                double size = Double.parseDouble(sizeField.getText());
                String type = typeField.getText();
                String address = addressField.getText();
                String saleOrRentFieldTextr = saleOrRentField.getText();
                boolean availible = Boolean.parseBoolean(statusField.getText());
                int id = Integer.parseInt(propertyIdField.getText());
                double price = Double.parseDouble(priceField.getText());
                String cnic = sellerField.getText();

                Properties prop = new Properties(type, availible, price, address, id, saleOrRentFieldTextr, size);


                boolean sellerFound = false;
                for (Seller seller : mainApp.getPropertyManagement().getSellers()) {
                    if (cnic.equalsIgnoreCase(seller.getCnic())) {
                        seller.getSellingProperties().add(prop);
                        prop.setSeller(seller);
                       // mainApp.getPropertyManagement().addProperty(prop);
                        sellerFound = true;
                        break;
                    }
                }

                // Show confirmation alert
                if (sellerFound) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Property Saved");
                    alert.setHeaderText(null);
                    alert.setContentText("The property has been successfully saved.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Seller Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("The property has been saved, but no matching seller was found.");
                    alert.showAndWait();
                }

            } catch (NumberFormatException e) {
                // Show error alert if input is invalid
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid input for all fields.");
                alert.showAndWait();
            }
        });


        backButton.setOnAction(event -> {
            // Handle back action here
            mainApp.showSellerScreen();
        });
    }

    public StackPane getRoot() {
        return root;
    }
}