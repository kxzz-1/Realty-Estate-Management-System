package org.example.finalproject;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class DeletePropertyScreen {
    private final StackPane root;
    private final MainApp mainApp;

    public DeletePropertyScreen(MainApp mainApp) {
        this.mainApp = mainApp;
        root = new StackPane();

        // Background Image
        Image backgroundImage = new Image("file:C:/Users/hp/Downloads/blurbg.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(false);
        backgroundImageView.setFitHeight(600);
        backgroundImageView.setFitWidth(800);

        // Semi-transparent Rectangle with rounded corners
        Rectangle r = new Rectangle(600, 400);
        r.setFill(Color.WHITE);
        r.setOpacity(0.5);
        r.setArcWidth(20);
        r.setArcHeight(20);

        // VBox for delete menu content
        VBox deleteMenuBox = new VBox(20);

        // "Delete Property" label
        Label deleteTitleLabel = new Label("Delete Property");
        deleteTitleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        deleteTitleLabel.setAlignment(Pos.CENTER);

        // Label and TextField for property ID
        Label propertyIdLabel = new Label("Enter Property ID:");
        propertyIdLabel.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-size: 18px;");
        TextField propertyIdField = new TextField();
        propertyIdField.setMaxWidth(150);
        propertyIdField.setPromptText("Property ID");

        // VBox to hold label and text field
        VBox propertyIdBox = new VBox(10, propertyIdLabel, propertyIdField);
        propertyIdBox.setAlignment(Pos.CENTER);

        // Delete and Cancel buttons
        Button deleteButton = new Button("Delete");
        Button cancelButton = new Button("Cancel");

        // Set actions for buttons
        deleteButton.setOnAction(event -> {
            String propertyIdText = propertyIdField.getText();
            if (propertyIdText.isEmpty()) {
                showAlert("Please enter a property ID.");
                return;
            }

            try {
                int propertyId = Integer.parseInt(propertyIdText);
                Properties propertyToDelete = null;
                List<Properties> properties = new ArrayList<>();
                       properties.addAll( mainApp.getCurrentSeller().getSellingProperties());

                for (Properties p : properties) {
                    if (p.getPropertyId() == propertyId) {
                        propertyToDelete = p;
                        break;
                    }
                }

                if (propertyToDelete != null) {
                    properties.remove(propertyToDelete);
                    mainApp.getCurrentSeller().getSellingProperties().remove(propertyToDelete);
                    mainApp.showSellerScreen();
                    showAlert("Property with ID " + propertyId + " and seller name " + propertyToDelete.getSeller().getName() + " has been deleted.");
                } else {
                    showAlert("Property with ID " + propertyId + " not found.");
                }

            } catch (NumberFormatException e) {
                showAlert("Invalid property ID. Please enter a valid number.");
            }
        });

        cancelButton.setOnAction(event -> {
            mainApp.showSellerScreen();
        });

        // HBox to hold the buttons horizontally
        HBox buttonsBox = new HBox(20, deleteButton, cancelButton);
        buttonsBox.setAlignment(Pos.CENTER);

        // Add components to the VBox
        deleteMenuBox.getChildren().addAll(deleteTitleLabel, propertyIdBox, buttonsBox);
        deleteMenuBox.setAlignment(Pos.CENTER);

        // Add components to the root
        root.getChildren().addAll(backgroundImageView, r, deleteMenuBox);

        // Ensure components are centered
        StackPane.setAlignment(r, Pos.CENTER);
        StackPane.setAlignment(deleteMenuBox, Pos.CENTER);
    }

    public StackPane getRoot() {
        return root;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Property Deleted");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
