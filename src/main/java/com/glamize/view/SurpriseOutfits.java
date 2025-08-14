package com.glamize.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SurpriseOutfits extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Glamize - Surprise Outfits");

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.setMaximized(true);

        // Background
        Image bgImage = new Image(getClass().getResourceAsStream("/assets/Images/womenbg.png"));
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setPreserveRatio(false);
        bgImageView.setOpacity(0.6); // Slight opacity for overlay
        bgImageView.fitWidthProperty().bind(stage.widthProperty());
        bgImageView.fitHeightProperty().bind(stage.heightProperty());

        // Title & Subtitle
        Text title = new Text("glamize");
        title.setFont(Font.font("Georgia", FontWeight.EXTRA_BOLD, 42));
        title.setFill(Color.web("#4a2c23"));

        Text subtitle = new Text("A little surprise, just for you");
        subtitle.setFont(Font.font("Georgia", 20));
        subtitle.setFill(Color.web("#4c3223ff"));

        VBox titleBox = new VBox(5, title, subtitle);
        titleBox.setAlignment(Pos.CENTER);

        // Back Button
        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: #f7f0eb; -fx-text-fill: #4a2c23; -fx-font-weight: bold;");
        backBtn.setOnAction(e -> {
            try {
                new homePage().start(stage); // Go back to homePage
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox header = new HBox(backBtn);
        header.setAlignment(Pos.TOP_LEFT);
        header.setPadding(new Insets(10, 0, 0, 10));

        // Outfit Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setHgap(30);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);

        for (int i = 1; i <= 12; i++) {
            String baseName = "sup" + i;
            String imagePath = "/assets/Images/surpriseme/" + baseName + ".png";

            Image img = new Image(getClass().getResourceAsStream(imagePath));
            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(250);
            imageView.setFitWidth(170);

            Button viewBtn = new Button("View");
            viewBtn.setStyle("-fx-background-color: #4a2c23; -fx-text-fill: white; -fx-background-radius: 10;");
            String finalBaseName = baseName;
            viewBtn.setOnAction(e -> {
                SurpriseViewOutfitPage viewPage = new SurpriseViewOutfitPage();
                Scene newScene = viewPage.getScene(stage, finalBaseName);
                stage.setScene(newScene);
            });

            VBox imageCard = new VBox(10, imageView, viewBtn);
            imageCard.setAlignment(Pos.CENTER);

            int col = (i - 1) % 3;
            int row = (i - 1) / 3;
            grid.add(imageCard, col, row);
        }

        // ScrollPane
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(580);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Hides scrollbar
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        VBox scrollContainer = new VBox(scrollPane);
        scrollContainer.setAlignment(Pos.CENTER);
        scrollContainer.setMaxWidth(900);
        scrollContainer.setPadding(new Insets(15));
        scrollContainer.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.7); " +
                "-fx-background-radius: 20; " +
                "-fx-border-radius: 20;"
        );

        VBox layoutContent = new VBox(20, header, titleBox, scrollContainer);
        layoutContent.setAlignment(Pos.TOP_CENTER);
        layoutContent.setPadding(new Insets(20));

        StackPane root = new StackPane(bgImageView, layoutContent);

        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
