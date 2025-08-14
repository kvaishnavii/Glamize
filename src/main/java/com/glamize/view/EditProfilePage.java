package com.glamize.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class EditProfilePage {

    public static Scene getScene(Stage stage, String idToken, String localId) {
        // === Background image ===
        Image bgImage = new Image("/assets/Images/background.png");
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setPreserveRatio(false);
        bgImageView.setFitWidth(Screen.getPrimary().getBounds().getWidth());
        bgImageView.setFitHeight(Screen.getPrimary().getBounds().getHeight());

        // === Back button header ===
        Button backButton = new Button("← Back");
        backButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #5F4B3C;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;"
        );
        backButton.setOnAction(e -> {
            homePage home = new homePage(); // Update this to your previous page
            try {
                home.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox header = new HBox(backButton);
        header.setAlignment(Pos.TOP_LEFT);
        header.setPadding(new Insets(20, 0, 0, 20));

        // === Form container ===
        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(30));
        formBox.setMaxWidth(420);
        formBox.setStyle(
                "-fx-background-color: rgba(255,255,255,0.6);" +
                        "-fx-background-radius: 20;"
        );

        Label title = new Label("Glamize");
        title.setFont(Font.font("Serif", FontWeight.BOLD, 36));
        title.setTextFill(Color.web("#5F4B3C"));

        Label subtitle = new Label("Edit your profile");
        subtitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        subtitle.setTextFill(Color.web("#5F4B3C"));

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setStyle(inputStyle());

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setStyle(inputStyle());

        TextField contactField = new TextField();
        contactField.setPromptText("Contact Number");
        contactField.setStyle(inputStyle());

        DatePicker dobPicker = new DatePicker();
        dobPicker.setPromptText("Date of Birth");
        dobPicker.setStyle(inputStyle());

        Button saveButton = new Button("Save Changes");
        saveButton.setStyle(buttonStyle());
        saveButton.setOnMouseEntered(e -> saveButton.setStyle(buttonHoverStyle()));
        saveButton.setOnMouseExited(e -> saveButton.setStyle(buttonStyle()));

        saveButton.setOnAction(e -> {
            if (emailField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Missing Email", "Please enter your email.");
            } else {
                // TODO: Firebase save logic
                showAlert(Alert.AlertType.INFORMATION, "Success", "Profile Updated Successfully!");
            }
        });

        formBox.getChildren().addAll(title, subtitle, nameField, emailField, contactField, dobPicker, saveButton);

        // === Layout all together ===
        VBox mainContent = new VBox(20, header, formBox);
        mainContent.setAlignment(Pos.TOP_CENTER);
        mainContent.setPadding(new Insets(40, 0, 0, 0));

        StackPane root = new StackPane(bgImageView, mainContent);
        StackPane.setAlignment(mainContent, Pos.TOP_CENTER);

        Scene scene = new Scene(root, 1200, 760);

        // === Fit fullscreen ===
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.setMaximized(true);

        return scene;
    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static String inputStyle() {
        return "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #EAD7C6;" +
                "-fx-border-width: 1px;" +
                "-fx-background-radius: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-padding: 10;";
    }

    private static String buttonStyle() {
        return "-fx-background-color: #A08D7D;" +
                "-fx-text-fill: #FFFFFF;" +
                "-fx-font-size: 16px;" +
                "-fx-padding: 10 0 10 0;" +
                "-fx-background-radius: 25;" +
                "-fx-cursor: hand;";
    }

    private static String buttonHoverStyle() {
        return "-fx-background-color: #8C776A;" +
                "-fx-text-fill: #FFFFFF;" +
                "-fx-font-size: 16px;" +
                "-fx-padding: 10 0 10 0;" +
                "-fx-background-radius: 25;" +
                "-fx-cursor: hand;";
    }
}
