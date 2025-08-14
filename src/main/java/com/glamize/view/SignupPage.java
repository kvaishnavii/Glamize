package com.glamize.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SignupPage extends Application {

    private final String API_KEY = "AIzaSyC4ysTqPAl72pPo5b8UKHevIhu6LFZsTz8"; // your Firebase Web API Key
    private final String DATABASE_URL = "https://glamize-default-rtdb.firebaseio.com"; // your Firebase Realtime DB URL

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Glamize - Sign Up");

        // Background image
        ImageView backgroundView = new ImageView(new Image("/assets/Images/loginbg.png"));
        backgroundView.setPreserveRatio(false);
        backgroundView.setOpacity(0.7);
        backgroundView.fitWidthProperty().bind(primaryStage.widthProperty());
        backgroundView.fitHeightProperty().bind(primaryStage.heightProperty());

        // Back Button
        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #5F4B3C; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 8 20;");
        backButton.setOnAction(e -> {
            LoginPage login = new LoginPage();
            try {
                login.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox header = new HBox(backButton);
        header.setPadding(new Insets(15, 0, 0, 15));
        header.setAlignment(Pos.TOP_LEFT);

        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(40));
        formBox.setMaxWidth(350);
        formBox.setMaxHeight(600);
        formBox.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-background-radius: 20;");

        Label logoLabel = new Label("glamize");
        logoLabel.setFont(Font.font("Serif", FontWeight.BOLD, 40));
        logoLabel.setTextFill(Color.web("#5F4B3C"));

        Label createAccountLabel = new Label("Create your account");
        createAccountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        createAccountLabel.setTextFill(Color.web("#5F4B3C"));

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(300);
        styleInput(emailField);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);
        styleInput(passwordField);

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");
        confirmPasswordField.setMaxWidth(300);
        styleInput(confirmPasswordField);

        DatePicker dobPicker = new DatePicker();
        dobPicker.setPromptText("Date of Birth");
        dobPicker.setMaxWidth(300);
        styleInput(dobPicker.getEditor());

        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female", "Other");
        genderComboBox.setPromptText("Gender");
        genderComboBox.setMaxWidth(300);
        genderComboBox.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #EAD7C6; -fx-border-width: 1px; -fx-background-radius: 5; -fx-border-radius: 5; -fx-padding: 10px;");

        Label statusLabel = new Label();
        statusLabel.setTextFill(Color.RED);

        Button signUpButton = new Button("Sign up");
        signUpButton.setMaxWidth(300);
        signUpButton.setStyle("-fx-background-color: #A08D7D; -fx-text-fill: white; -fx-font-size: 18px; -fx-padding: 10px 0; -fx-background-radius: 25;");

        signUpButton.setOnAction(e -> {
            String email = emailField.getText().trim();
            String pass = passwordField.getText().trim();
            String confirmPass = confirmPasswordField.getText().trim();
            String dob = (dobPicker.getValue() != null) ? dobPicker.getValue().toString() : "";
            String gender = genderComboBox.getValue();

            if (email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty() || dob.isEmpty() || gender == null) {
                statusLabel.setText("Please fill in all fields.");
                return;
            }

            if (!pass.equals(confirmPass)) {
                statusLabel.setText("Passwords do not match.");
                return;
            }

            signUpButton.setDisable(true);

            new Thread(() -> {
                boolean success = signUpWithEmail(email, pass, dob, gender);
                Platform.runLater(() -> {
                    signUpButton.setDisable(false);
                    if (success) {
                        homePage home = new homePage();
                        home.start((Stage) signUpButton.getScene().getWindow());
                    } else {
                        statusLabel.setText("Sign-up failed. Try again.");
                        statusLabel.setTextFill(Color.RED);
                    }
                });
            }).start();
        });

        formBox.getChildren().addAll(
                logoLabel, createAccountLabel,
                emailField, passwordField, confirmPasswordField,
                dobPicker, genderComboBox,
                signUpButton, statusLabel
        );

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(header);
        borderPane.setCenter(formBox);

        StackPane root = new StackPane(backgroundView, borderPane);
        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setScene(scene);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(screenBounds.getMinX());
        primaryStage.setY(screenBounds.getMinY());
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void styleInput(TextField field) {
        field.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #EAD7C6; -fx-border-width: 1px; -fx-background-radius: 5; -fx-border-radius: 5; -fx-padding: 10px;");
    }

    private boolean signUpWithEmail(String email, String password, String dob, String gender) {
        try {
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String payload = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email, password);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes());
                os.flush();
            }

            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                Scanner scanner = new Scanner(is).useDelimiter("\\A");
                String responseBody = scanner.hasNext() ? scanner.next() : "";
                JSONObject json = new JSONObject(responseBody);
                String localId = json.getString("localId");
                saveUserProfile(localId, email, dob, gender);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void saveUserProfile(String uid, String email, String dob, String gender) {
        try {
            URL url = new URL(DATABASE_URL + "/users/" + uid + ".json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String payload = String.format("{\"email\":\"%s\", \"dob\":\"%s\", \"gender\":\"%s\"}", email, dob, gender);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes());
                os.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
