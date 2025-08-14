package com.glamize.view;

import com.glamize.AboutUsPage;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WelcomePage extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Glamize - Welcome");

        // Background image setup
        Image backgroundImage = null;
        try {
            backgroundImage = new Image(getClass().getResource("/assets/Images/background.png").toExternalForm());
        } catch (Exception e) {
            System.out.println("⚠️ Failed to load image: " + e.getMessage());
        }

        ImageView backgroundImageView = new ImageView();
        if (backgroundImage != null && !backgroundImage.isError()) {
            backgroundImageView.setImage(backgroundImage);
            backgroundImageView.setPreserveRatio(false);
            backgroundImageView.setOpacity(0.7);
            backgroundImageView.fitWidthProperty().bind(primaryStage.widthProperty());
            backgroundImageView.fitHeightProperty().bind(primaryStage.heightProperty());
        }

        Label glamizeLabel = new Label("glamize");
        glamizeLabel.setFont(Font.font("Serif", FontWeight.BOLD, 60));
        glamizeLabel.setTextFill(Color.web("#5F4B3C"));

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), glamizeLabel);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        TranslateTransition bounce = new TranslateTransition(Duration.seconds(1), glamizeLabel);
        bounce.setFromY(-20);
        bounce.setToY(0);
        bounce.setCycleCount(1);
        bounce.setInterpolator(Interpolator.EASE_OUT);

        SequentialTransition animation = new SequentialTransition(fadeIn, bounce);
        animation.play();

        Label taglineLabel = new Label("Styled by glam, inspired by you");
        taglineLabel.setFont(Font.font("Arial", 24));
        taglineLabel.setTextFill(Color.web("#5F4B3C"));

        Button getStartedButton = new Button("GET STARTED");
        getStartedButton.setStyle(
            "-fx-background-color: #EAD7C6; " +
            "-fx-text-fill: #5F4B3C; " +
            "-fx-font-size: 18px; " +
            "-fx-padding: 10px 30px; " +
            "-fx-background-radius: 25; " +
            "-fx-border-color: #5F4B3C; " +
            "-fx-border-width: 1px; " +
            "-fx-border-radius: 25;"
        );
        getStartedButton.setOnAction(e -> {
            LoginPage loginPage = new LoginPage();
            try {
                loginPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox contentBox = new VBox(20, glamizeLabel, taglineLabel, getStartedButton);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(50));

        Button aboutUsButton = new Button("About Us");
        aboutUsButton.setStyle(
            "-fx-background-color: transparent; " +
            "-fx-text-fill: #5F4B3C; " +
            "-fx-font-size: 14px; " +
            "-fx-underline: true;"
        );

        // Important: call start(Stage) of AboutUsPage manually
        aboutUsButton.setOnAction(e -> {
            AboutUsPage aboutUs = new AboutUsPage();
            Stage aboutStage = new Stage();
            try {
                aboutUs.start(aboutStage);
                aboutStage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox topRightBox = new HBox(aboutUsButton);
        topRightBox.setAlignment(Pos.TOP_RIGHT);
        topRightBox.setPadding(new Insets(20, 25, 0, 0));

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topRightBox);
        borderPane.setCenter(contentBox);

        StackPane root = new StackPane();
        if (backgroundImageView.getImage() != null) {
            root.getChildren().addAll(backgroundImageView, borderPane);
        } else {
            root.getChildren().add(borderPane);
        }

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
