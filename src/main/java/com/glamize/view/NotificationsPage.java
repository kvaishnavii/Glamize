package com.glamize.view;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class NotificationsPage {

    public Scene getScene(Stage stage) {
        StackPane root = new StackPane();

        // Responsive Background Image
        ImageView bgView = new ImageView(new Image(getClass().getResource("/assets/Images/notificationbg.png").toExternalForm()));
        bgView.setPreserveRatio(false);
        bgView.setOpacity(0.35);
        root.getChildren().add(bgView);

        VBox mainContainer = new VBox();
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(40));
        mainContainer.setSpacing(30);

        // Top-left Back Button to Profile Page
        Button backBtn = new Button("← Back to Profile");
        backBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #5F4B3C; -fx-font-size: 15px; -fx-font-weight: bold;");
        backBtn.setOnAction(e -> {
            profilePage profile = new profilePage();
            stage.setScene(profile.getScene(stage));
        });

        HBox backBox = new HBox(backBtn);
        backBox.setAlignment(Pos.TOP_LEFT);
        backBox.setPadding(new Insets(0, 0, 0, 10));

        VBox content = new VBox(20);
        content.setAlignment(Pos.CENTER);

        // Dummy notifications list
        List<String> notifications = new ArrayList<>();
        // Uncomment below to test notifications
        // notifications.add("Your wishlist item is now 30% off!");
        // notifications.add("New arrivals are now in stock!");

        if (notifications.isEmpty()) {
            Image bellImg = new Image(getClass().getResource("/assets/Images/bell.png").toExternalForm());
            ImageView bellIcon = new ImageView(bellImg);
            bellIcon.setFitWidth(120);
            bellIcon.setFitHeight(120);

            Label noNotif = new Label("No Notifications Here");
            noNotif.setFont(Font.font("Arial", FontWeight.BOLD, 26));
            noNotif.setTextFill(Color.web("#5F4B3C"));

            Label subText = new Label("There are no notifications to show right now.");
            subText.setFont(Font.font("Arial", 16));
            subText.setTextFill(Color.web("#5F4B3C"));

            Button continueBtn = new Button("Continue Outfit Hunting");
            continueBtn.setStyle("-fx-background-color: #D4AF37; -fx-text-fill: white; -fx-font-size: 16; -fx-background-radius: 10; -fx-padding: 10 20;");
            continueBtn.setOnAction(e -> new homePage().start(stage));

            content.getChildren().addAll(bellIcon, noNotif, subText, continueBtn);
        } else {
            Label title = new Label("Notifications");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
            title.setTextFill(Color.web("#5F4B3C"));

            VBox notifBox = new VBox(15);
            notifBox.setPadding(new Insets(10));
            notifBox.setPrefWidth(600);
            notifBox.setAlignment(Pos.TOP_LEFT);

            for (String notif : notifications) {
                Label notifLabel = new Label("• " + notif);
                notifLabel.setFont(Font.font("Arial", 18));
                notifLabel.setTextFill(Color.web("#5F4B3C"));
                notifLabel.setWrapText(true);
                notifBox.getChildren().add(notifLabel);
            }

            ScrollPane scrollPane = new ScrollPane(notifBox);
            scrollPane.setFitToWidth(true);
            scrollPane.setMaxHeight(400);
            scrollPane.setStyle("-fx-background-color: transparent;");

            Button backHomeBtn = new Button("Back to Home");
            backHomeBtn.setStyle("-fx-background-color: #D4AF37; -fx-text-fill: white; -fx-font-size: 16; -fx-background-radius: 10; -fx-padding: 10 20;");
            backHomeBtn.setOnAction(e -> new homePage().start(stage));

            content.getChildren().addAll(title, scrollPane, backHomeBtn);
        }

        mainContainer.getChildren().addAll(backBox, content);
        root.getChildren().add(mainContainer);

        // Fade animation
        FadeTransition ft = new FadeTransition(Duration.millis(800), mainContainer);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        Scene scene = new Scene(root, 1200, 760);

        // Bind background image to scene size
        bgView.fitWidthProperty().bind(scene.widthProperty());
        bgView.fitHeightProperty().bind(scene.heightProperty());

        // Maximize like homePage
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.setMaximized(true);

        return scene;
    }
}
