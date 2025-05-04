package org.example.finalproject;



import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RConfirm extends Scene {

    public RConfirm(StackPane root, double width, double height) {
        super(root, width, height);

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

        // VBox for confirmation content
        VBox confirmationBox = new VBox(20);

        // "Confirmation" label
        Label confirmationTitleLabel = new Label("Confirmation");
        confirmationTitleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        confirmationTitleLabel.setAlignment(Pos.CENTER);

        // Confirmation message label
        Label confirmationMessageLabel = new Label("Are you sure that you want to Rent this Property?");
        confirmationMessageLabel.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-size: 18px;");
        confirmationMessageLabel.setAlignment(Pos.CENTER);

        // Yes and No buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        // Set actions for buttons
        yesButton.setOnAction(event -> {
            // Handle yes action here
            System.out.println("Proceeding...");
        });

        noButton.setOnAction(event -> {
            // Handle no action here
            System.out.println("Action canceled.");
        });

        // HBox to hold the buttons horizontally
        HBox buttonsBox = new HBox(20, yesButton, noButton);
        buttonsBox.setAlignment(Pos.CENTER);

        // Add all components to the VBox
        confirmationBox.getChildren().addAll(confirmationTitleLabel, confirmationMessageLabel, buttonsBox);
        confirmationBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(backgroundImageView, r, confirmationBox);

        StackPane.setAlignment(r, Pos.CENTER); // Ensure the rectangle is centered
        StackPane.setAlignment(confirmationBox, Pos.CENTER); // Ensure the VBox is centered
    }

}
