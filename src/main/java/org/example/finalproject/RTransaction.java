package org.example.finalproject;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RTransaction  {

    private final MainApp mainApp;
    private StackPane root;
    private int id;

    public RTransaction(MainApp mainApp,int id) {
        this.mainApp=mainApp;
        root = new StackPane();
        this.id = id;
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

        // VBox for receipt content
        VBox receiptBox = new VBox(20);

        // "Receipt" label
        Label receiptTitleLabel = new Label("Property Rent Receipt");
        receiptTitleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        receiptTitleLabel.setAlignment(Pos.CENTER);

        // GridPane for receipt details
        GridPane detailsGrid = new GridPane();
        detailsGrid.setHgap(10);
        detailsGrid.setVgap(10);
        detailsGrid.setPadding(new Insets(10));

        Label ownerNameLabel = new Label("Owner Name:");
        Label ownerPhoneLabel = new Label("Owner Phone:");
        Label tenantNameLabel = new Label("Tenant Name:");
        Label tenantPhoneLabel = new Label("Tenant Phone:");
        Label propertyIdLabel = new Label("Property ID:");
        Label typeLabel = new Label("Property Type:");
        Label sizeLabel = new Label("Property Size:");
        Label addressLabel = new Label("Property Address:");
        Label priceLabel = new Label("Rent per Month:");

        Label ownerNameValue = new Label();
        Label ownerPhoneValue = new Label();
        Label tenantNameValue = new Label();
        Label tenantPhoneValue = new Label();
        Label propertyIdValue = new Label();
        Label typeValue = new Label();
        Label sizeValue = new Label();
        Label addressValue = new Label();
        Label priceValue = new Label();

        for (Properties p : mainApp.getPropertyManagement().getProperties()) {
            if (p.getPropertyId() == id) {
                if (p.getSeller() != null) {
                    ownerNameValue.setText(p.getSeller().getName());
                    ownerPhoneValue.setText(p.getSeller().getPhoneNumber());
                } else {
                    ownerNameValue.setText("N/A");
                    ownerPhoneValue.setText("N/A");
                }

                if (p.getTenant() != null) {
                    tenantNameValue.setText(p.getTenant().getName());
                    tenantPhoneValue.setText(p.getTenant().getPhoneNumber());
                } else {
                    tenantNameValue.setText("N/A");
                    tenantPhoneValue.setText("N/A");
                }

                propertyIdValue.setText(String.valueOf(id));
                typeValue.setText(p.getPropertyType());
                addressValue.setText(p.getAddress());
                priceValue.setText(String.valueOf(p.getPrice()));
                sizeValue.setText(String.valueOf(p.getSize()));
                break;
            }
        }

       /* Label ownerNameValue = new Label("John Doe");
        Label ownerPhoneValue = new Label("123-456-7890");
        Label buyerNameValue = new Label("Jane Smith");
        Label buyerPhoneValue = new Label("098-765-4321");
        Label propertyIdValue = new Label("12345");
        Label typeValue = new Label("Apartment");
        Label sizeValue = new Label("1500 sq ft");
        Label addressValue = new Label("123 Main St, City, Country");
        Label priceValue = new Label("PKR 10,000,000");
*/
        detailsGrid.addRow(0, ownerNameLabel, ownerNameValue);
        detailsGrid.addRow(1, ownerPhoneLabel, ownerPhoneValue);
        detailsGrid.addRow(2, tenantNameLabel, tenantNameValue);
        detailsGrid.addRow(3, tenantPhoneLabel, tenantPhoneValue);
        detailsGrid.addRow(4, propertyIdLabel, propertyIdValue);
        detailsGrid.addRow(5, typeLabel, typeValue);
        detailsGrid.addRow(6, sizeLabel, sizeValue);
        detailsGrid.addRow(7, addressLabel, addressValue);
        detailsGrid.addRow(8, priceLabel, priceValue);
        detailsGrid.setAlignment(Pos.CENTER);

        // Back Button
        Button backButton = new Button("Back");

        // Set action for back button
        backButton.setOnAction(event -> {
            // Handle back action here
            System.out.println("Back to previous screen");
            mainApp.showTenantScreen();
        });

        HBox buttonBox = new HBox(backButton);
        buttonBox.setAlignment(Pos.CENTER);

        receiptBox.getChildren().addAll(receiptTitleLabel, detailsGrid, buttonBox);
        receiptBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(backgroundImageView, r, receiptBox);

        StackPane.setAlignment(r, Pos.CENTER); // Ensure the rectangle is centered
        StackPane.setAlignment(receiptBox, Pos.CENTER); // Ensure the VBox is centered
    }
    public StackPane getRoot(){
        return root;
    }
}