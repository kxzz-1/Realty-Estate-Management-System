/*package org.example.oopproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class EditPropertyScreen {
    private StackPane root;
    private final MainApp mainApp;
    private final Properties property;

    public EditPropertyScreen(MainApp mainApp, Properties property) {
        this.mainApp = mainApp;
        this.property = property;
        root = new StackPane();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        Label idLabel = new Label("Property ID: ");
        TextField idField = new TextField(String.valueOf(property.getPropertyId()));
        idField.setEditable(false);

        Label typeLabel = new Label("Type: ");
        TextField typeField = new TextField(property.getPropertyType());

        Label rentStatusLabel = new Label("Rent Status: ");
        TextField rentStatusField = new TextField(String.valueOf(property.isRentStatus()));

        Label soldStatusLabel = new Label("Sold Status: ");
        TextField soldStatusField = new TextField(String.valueOf(property.isSoldStatus()));

        Label priceLabel = new Label("Price: ");
        TextField priceField = new TextField(String.valueOf(property.getPrice()));

        Label addressLabel = new Label("Address: ");
        TextField addressField = new TextField(property.getAddress());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            property.setPropertyType(typeField.getText());
            property.setRentStatus(Boolean.parseBoolean(rentStatusField.getText()));
            property.setSoldStatus(Boolean.parseBoolean(soldStatusField.getText()));
            property.setPrice(Double.parseDouble(priceField.getText()));
            property.setAddress(addressField.getText());
            mainApp.saveProperty(property);
        });

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(typeLabel, 0, 1);
        gridPane.add(typeField, 1, 1);
        gridPane.add(rentStatusLabel, 0, 2);
        gridPane.add(rentStatusField, 1, 2);
        gridPane.add(soldStatusLabel, 0, 3);
        gridPane.add(soldStatusField, 1, 3);
        gridPane.add(priceLabel, 0, 4);
        gridPane.add(priceField, 1, 4);
        gridPane.add(addressLabel, 0, 5);
        gridPane.add(addressField, 1, 5);
        gridPane.add(saveButton, 1, 6);

        VBox container = new VBox(10, gridPane);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(10));

        root.getChildren().add(container);
    }

    public StackPane getRoot() {
        return root;
    }
}
*/