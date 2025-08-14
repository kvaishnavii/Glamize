package com.glamize.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StylingSurprisePage extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Surprise Me - Glamize");

        // Use ImageView as background with dynamic binding
        Image bgImage = new Image(getClass().getResourceAsStream("/assets/Images/surprisebg.png"));
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setOpacity(0.4);
        bgImageView.setPreserveRatio(false);
        bgImageView.fitWidthProperty().bind(stage.widthProperty());
        bgImageView.fitHeightProperty().bind(stage.heightProperty());

        // Title and tagline
        Text title = new Text("glamize");
        title.setFont(Font.font("Serif", FontWeight.EXTRA_BOLD, 40));
        title.setFill(Color.web("#4a2c23"));

        Text tagline = new Text("Styled by glam, inspired by you");
        tagline.setFont(Font.font("Georgia", FontWeight.NORMAL, 20));
        tagline.setFill(Color.web("#4a2c23"));

        VBox titleBox = new VBox(5, title, tagline);
        titleBox.setAlignment(Pos.CENTER);

        // Subtitle and button inside white box
        Text subtitle = new Text("Let us surprise you with style!");
        subtitle.setFont(Font.font("Georgia", 22));
        subtitle.setFill(Color.web("#4c3223"));

        Button showBtn = new Button("Show Me Surprise Outfits");
        showBtn.setStyle("-fx-background-color: #4a2c23; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20; -fx-background-radius: 10;");
        showBtn.setOnAction(e -> {
            SurpriseOutfits outfitsPage = new SurpriseOutfits();
            try {
                outfitsPage.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox contentBox = new VBox(20, subtitle, showBtn);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(30));
        contentBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.75); -fx-background-radius: 20;");

        // Back Button
        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: #f7f0eb; -fx-text-fill: #4a2c23; -fx-font-weight: bold;");
        backBtn.setOnAction(e -> {
            homePage home = new homePage();
            try {
                home.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox topBar = new HBox(backBtn);
        topBar.setAlignment(Pos.TOP_LEFT);
        topBar.setPadding(new Insets(10, 0, 0, 10));

        VBox centerContent = new VBox(30, titleBox, contentBox);
        centerContent.setAlignment(Pos.CENTER);

        VBox rootContent = new VBox(20, topBar, centerContent);
        rootContent.setAlignment(Pos.TOP_CENTER);
        rootContent.setPadding(new Insets(20));

        // Use StackPane with ImageView background
        StackPane root = new StackPane();
        root.getChildren().addAll(bgImageView, rootContent);

        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
