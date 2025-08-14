package com.glamize.view;

import com.glamize.model.FirebaseService;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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

public class LoginPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Glamize - Login");

        // Background image
        Image bgImage = new Image(getClass().getResourceAsStream("/assets/Images/loginbg.png"));
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setPreserveRatio(false);
        bgImageView.fitWidthProperty().bind(primaryStage.widthProperty());
        bgImageView.fitHeightProperty().bind(primaryStage.heightProperty());
        bgImageView.setOpacity(0.7);

        // === HEADER WITH BACK BUTTON ===
        Button backButton = new Button("← Back");
        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #5F4B3C;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;"
        );
        backButton.setOnAction(e -> {
            WelcomePage getStartedPage = new WelcomePage();
            try {
                getStartedPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox header = new HBox(backButton);
        header.setPadding(new Insets(20, 0, 0, 20));
        header.setAlignment(Pos.TOP_LEFT);

        // === FORM CONTAINER ===
        VBox formContainer = new VBox(15);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setPadding(new Insets(30));
        formContainer.setMaxHeight(500);
        formContainer.setMaxWidth(350);
        formContainer.setStyle(
                "-fx-background-color: rgba(255,255,255,0.5);" +
                "-fx-background-radius: 20;"
        );

        Label logoLabel = new Label("glamize");
        logoLabel.setFont(Font.font("Serif", FontWeight.BOLD, 40));
        logoLabel.setTextFill(Color.web("#5F4B3C"));

        Label welcomeLabel = new Label("Welcome");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        welcomeLabel.setTextFill(Color.web("#5F4B3C"));

        Label loginAccountLabel = new Label("Login to your account");
        loginAccountLabel.setFont(Font.font("Arial", 18));
        loginAccountLabel.setTextFill(Color.web("#5F4B3C"));

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(300);
        emailField.setStyle("-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #EAD7C6;" +
                "-fx-border-width: 1px;" +
                "-fx-background-radius: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-padding: 10;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);
        passwordField.setStyle("-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #EAD7C6;" +
                "-fx-border-width: 1px;" +
                "-fx-background-radius: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-padding: 10;");

        Button signInButton = new Button("Log in");
        signInButton.setMaxWidth(300);
        signInButton.setStyle("-fx-background-color: #A08D7D;" +
                "-fx-text-fill: #FFFFFF;" +
                "-fx-font-size: 18px;" +
                "-fx-padding: 10 0 10 0;" +
                "-fx-background-radius: 25;");

        // Hover effect for log in button
        signInButton.setOnMouseEntered(e -> signInButton.setStyle(
                "-fx-background-color: #8C776A;" +
                "-fx-text-fill: #FFFFFF;" +
                "-fx-font-size: 18px;" +
                "-fx-padding: 10 0 10 0;" +
                "-fx-background-radius: 25;"
        ));
        signInButton.setOnMouseExited(e -> signInButton.setStyle(
                "-fx-background-color: #A08D7D;" +
                "-fx-text-fill: #FFFFFF;" +
                "-fx-font-size: 18px;" +
                "-fx-padding: 10 0 10 0;" +
                "-fx-background-radius: 25;"
        ));

        Label resultLabel = new Label();
        resultLabel.setTextFill(Color.web("#D9534F"));

        FirebaseService firebaseService = new FirebaseService();

        signInButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            String result = firebaseService.signInWithEmailAndPassword(email, password);
            resultLabel.setText(result);
            resultLabel.setTextFill(result.toLowerCase().contains("successful") ? Color.web("#5F4B3C") : Color.web("#D9534F"));
            if (result.toLowerCase().contains("successful")) {
                homePage homePage = new homePage();
                try {
                    homePage.start(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        Label orLabel = new Label("or sign in with-");
        orLabel.setFont(Font.font("Arial", 14));
        orLabel.setTextFill(Color.web("#5F4B3C"));

        // Removed Google button as requested

        Button signUpLink = new Button("Sign up");
        signUpLink.setStyle("-fx-background-color: transparent;" +
                "-fx-text-fill: #5F4B3C;" +
                "-fx-underline: true;" +
                "-fx-font-size: 14px;");

        // Hover effect for sign-up link
        signUpLink.setOnMouseEntered(e -> signUpLink.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #3D2D23;" +
                "-fx-underline: true;" +
                "-fx-font-size: 14px;"
        ));
        signUpLink.setOnMouseExited(e -> signUpLink.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #5F4B3C;" +
                "-fx-underline: true;" +
                "-fx-font-size: 14px;"
        ));

        signUpLink.setOnAction(e -> {
            SignupPage signupPage = new SignupPage();
            try {
                signupPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox authOptionsBox = new VBox(10, orLabel, signUpLink);
        authOptionsBox.setAlignment(Pos.CENTER);

        formContainer.getChildren().addAll(
                logoLabel, welcomeLabel, loginAccountLabel,
                emailField, passwordField, signInButton,
                resultLabel, authOptionsBox
        );

        VBox content = new VBox(20, header, formContainer);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(40, 0, 0, 0));

        StackPane root = new StackPane(bgImageView, content);

        Scene scene = new Scene(root, 1200, 760);
        primaryStage.setScene(scene);

        // Ensure window is maximized and covers entire visible screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(screenBounds.getMinX());
        primaryStage.setY(screenBounds.getMinY());
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());

        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
