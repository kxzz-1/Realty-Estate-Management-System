package org.example.finalproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Signup {
    private final StackPane root;
    private final MainApp mainApp;

    public Signup(MainApp mainApp) {
        this.mainApp = mainApp;
        root = new StackPane();

        Image backgroundImage = new Image("file:C:/Users/hp/Downloads/bg.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(false);

        Rectangle r = new Rectangle(600, 400);
        r.setFill(Color.BLACK);
        r.setOpacity(0.5);
        r.setArcWidth(20);
        r.setArcHeight(20);

        VBox signupOptions = new VBox(20);
        signupOptions.setAlignment(Pos.CENTER);

        Label registerLabel = new Label("Register");
        registerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        GridPane labelsAndFields = new GridPane();
        labelsAndFields.setHgap(10);
        labelsAndFields.setVgap(10);

        String whiteTextStyle = "-fx-text-fill: white;";

        Label nameLabel = new Label("Name:");
        nameLabel.setStyle(whiteTextStyle);
        TextField nameField = new TextField();

        Label fatherNameLabel = new Label("Father Name:");
        fatherNameLabel.setStyle(whiteTextStyle);
        TextField fatherNameField = new TextField();

        Label phoneNumberLabel = new Label("Phone Number:");
        phoneNumberLabel.setStyle(whiteTextStyle);
        TextField phoneNumberField = new TextField();

        Label addressLabel = new Label("Address:");
        addressLabel.setStyle(whiteTextStyle);
        TextField addressField = new TextField();

        Label cnicLabel = new Label("CNIC:");
        cnicLabel.setStyle(whiteTextStyle);
        TextField cnicField = new TextField();

        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle(whiteTextStyle);
        PasswordField passwordField = new PasswordField();

        labelsAndFields.addRow(0, nameLabel, nameField);
        labelsAndFields.addRow(1, fatherNameLabel, fatherNameField);
        labelsAndFields.addRow(2, phoneNumberLabel, phoneNumberField);
        labelsAndFields.addRow(3, addressLabel, addressField);
        labelsAndFields.addRow(4, cnicLabel, cnicField);
        labelsAndFields.addRow(5, passwordLabel, passwordField);
        labelsAndFields.setAlignment(Pos.CENTER);

        ToggleGroup group = new ToggleGroup();

        RadioButton option1 = new RadioButton("Buyer");
        option1.setStyle(whiteTextStyle);
        option1.setToggleGroup(group);

        RadioButton option2 = new RadioButton("Seller");
        option2.setStyle(whiteTextStyle);
        option2.setToggleGroup(group);

        RadioButton option3 = new RadioButton("Tenant");
        option3.setStyle(whiteTextStyle);
        option3.setToggleGroup(group);

        HBox radioButtons = new HBox(10);
        radioButtons.getChildren().addAll(option1, option2, option3);
        radioButtons.setAlignment(Pos.CENTER);

        Button signupButton = new Button("Sign Up");

        signupOptions.getChildren().addAll(registerLabel, labelsAndFields, radioButtons, signupButton);
        root.getChildren().addAll(backgroundImageView, r, signupOptions);

        StackPane.setAlignment(r, Pos.CENTER);
        StackPane.setAlignment(signupOptions, Pos.CENTER);

        signupButton.setOnAction(e -> {
            // Handle signup logic and transition to the appropriate scene
            if (option1.isSelected()) {
                Buyers b = new Buyers(nameField.getText(), addressField.getText(), phoneNumberField.getText(), cnicField.getText(), fatherNameField.getText(), passwordField.getText());
                mainApp.getPropertyManagement().getBuyers().add(b);
                mainApp.setCurrentBuyer(b);
                mainApp.showBuyerScreen();
                mainApp.getPropertyManagement().serializeBuyerArrayListToFile(mainApp.getPropertyManagement().getBuyers(),"Buyer.csv");
                // or switch to the relevant scene
            } else if (option2.isSelected()) {
                Seller newSeller = new Seller(nameField.getText(), addressField.getText(), phoneNumberField.getText(), cnicField.getText(), fatherNameField.getText(), passwordField.getText());
                mainApp.getPropertyManagement().addSeller(newSeller);
                mainApp.setCurrentSeller(newSeller);
                mainApp.showSellerScreen();
                mainApp.getPropertyManagement().serializeSellerArrayListToFile(mainApp.getPropertyManagement().getSellers(),"Seller.csv");
            } else if (option3.isSelected()) {
                Tenant newTenant = new Tenant(nameField.getText(), addressField.getText(), phoneNumberField.getText(), cnicField.getText(), fatherNameField.getText(), passwordField.getText());
                mainApp.getPropertyManagement().addTenant(newTenant);
                mainApp.setCurrentTenant(newTenant);
                mainApp.showTenantScreen();
                mainApp.getPropertyManagement().serializeTenantArrayListToFile(mainApp.getPropertyManagement().getTenants(),"Tenant.csv");
            }
        });

    }

    public StackPane getRoot() {
        return root;
    }
}
