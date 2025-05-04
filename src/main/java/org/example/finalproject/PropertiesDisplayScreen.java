package org.example.finalproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashSet;
public class PropertiesDisplayScreen {
    private final MainApp mainApp;
    private final ArrayList<Properties> foundProperties;
    private final StackPane root;
    private final VBox propertiesContainer;
    private final GridPane propertyGrid;

    public PropertiesDisplayScreen(MainApp mainApp, ArrayList<Properties> foundProperties) {
        this.mainApp = mainApp;
        this.foundProperties = foundProperties;
        this.root = new StackPane();
        this.propertyGrid = new GridPane();

        // Background Image
        Image backgroundImage = new Image("file:C:/Users/hp/Downloads/blurbg.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(false);

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
        backButton.setOnAction(e -> mainApp.showBuyerScreen());

        // Label for Properties
        Label propertiesLabel = new Label("Properties Available for Purchase");
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

        VBox scrollPaneContainer = new VBox(topContainer, scrollPane);
        scrollPaneContainer.setAlignment(Pos.TOP_CENTER);
        scrollPaneContainer.setMaxWidth(580);
        scrollPaneContainer.setMaxHeight(380);
        scrollPaneContainer.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPaneContainer.setPadding(new Insets(10, 10, 10, 10));

        StackPane contentPane = new StackPane();
        contentPane.getChildren().addAll(r, scrollPaneContainer);

        StackPane.setAlignment(r, Pos.CENTER);
        StackPane.setAlignment(scrollPaneContainer, Pos.CENTER);

        root.getChildren().addAll(backgroundImageView, contentPane);


    }

    public StackPane getRoot() {
        return root;
    }

    public void displayProperties(ArrayList<Properties> properties) {
        propertiesContainer.getChildren().clear();
        propertyGrid.getChildren().clear();

        propertyGrid.setAlignment(Pos.CENTER);
        propertyGrid.setHgap(10);
        propertyGrid.setVgap(20);
        propertyGrid.setPadding(new Insets(20));
        propertyGrid.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        ArrayList<Properties> searchedProperties = new ArrayList<>();

        for (Properties p : properties) {
            if (p.isAvailable() && p.getSaleOrRent().equalsIgnoreCase("Sale")) {
                searchedProperties.add(p);
            }
        }

        if (searchedProperties.size()/2 > 0) {
            for (int i = 0; i < searchedProperties.size(); i++) {
                System.out.println(foundProperties.size());
                addPropertyBox(propertyGrid, i / 2, i % 2, searchedProperties.get(i));
            }
        } else {
            Label noPropertiesLabel = new Label("No properties found.");
            noPropertiesLabel.setStyle("-fx-font-size: 18px;");
            propertiesContainer.getChildren().add(noPropertiesLabel);
        }

        propertiesContainer.getChildren().add(propertyGrid);
    }

    private void addPropertyBox(GridPane gridPane, int rowIndex, int columnIndex, Properties property) {
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
        Label ownerName = new Label("Owner: " + (property.getSeller() != null ? property.getSeller().getName() : "N/A"));

        propertyIdLabel.setStyle(style);
        typeLabel.setStyle(style);
        sizeLabel.setStyle(style);
        priceLabel.setStyle(style);
        addressLabel.setStyle(style);
        owner.setStyle(style);
        ownerName.setStyle(style);

        // Create and style the Buy button
        Button buyButton = new Button("Buy");
        buyButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-family: Trebuchet MS;");
        buyButton.setOnAction(e -> {
            // Handle the buy action
            mainApp.showPropertiesBuyScreen();
          //  handleBuyAction(property);
        });

        propertyBox.getChildren().addAll(propertyIdLabel, typeLabel, sizeLabel, priceLabel, addressLabel, owner, ownerName, buyButton);
        gridPane.add(propertyBox, columnIndex, rowIndex);
    }

    private void handleBuyAction(Properties property) {
        System.out.println("Property with ID " + property.getPropertyId() + " purchased.");
        Buyers b = mainApp.getCurrentBuyer();
        if (b != null) {
            b.addProperty(property);
            property.setAvailable(false);
            System.out.println("Property added to buyer's list");
        } else {
            System.out.println("Current buyer is null");
        }
        mainApp.showTransactionScreen(property.getPropertyId());
        mainApp.showBoughtPropertiesScreen();
    }


}
