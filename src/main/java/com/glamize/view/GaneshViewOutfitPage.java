package com.glamize.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GaneshViewOutfitPage {

    public Scene getScene(Stage stage, String outfitName) {
        // Extract number
        String numberOnly = outfitName.replaceAll("[^0-9]", "");
    ImageView avtaarImageView;
    Image avtaarImage = null;
    String basePath = "/assets/Images/Ganpati/Ganpati" + numberOnly + "avtaar";

    try {
    avtaarImage = new Image(getClass().getResource(basePath + ".jpg").toExternalForm());
    } catch (Exception e1) {
    try {
        avtaarImage = new Image(getClass().getResource(basePath + ".png").toExternalForm());
    } catch (Exception e2) {
        System.out.println("Error loading image: " + basePath + " (.jpg or .png not found)");
        return new Scene(new StackPane(), 800, 600);
    }
    }

avtaarImageView = new ImageView(avtaarImage);


        // Image properties
        avtaarImageView.setFitWidth(300);
        avtaarImageView.setPreserveRatio(true);
        avtaarImageView.setSmooth(true);
        avtaarImageView.setEffect(new DropShadow(12, Color.GRAY));

        // Hover zoom
        avtaarImageView.setOnMouseEntered(e -> {
            avtaarImageView.setScaleX(1.05);
            avtaarImageView.setScaleY(1.05);
        });
        avtaarImageView.setOnMouseExited(e -> {
            avtaarImageView.setScaleX(1.0);
            avtaarImageView.setScaleY(1.0);
        });

        // Glamize header
        Text title = new Text("Glamize");
        title.setFont(Font.font("Serif", FontWeight.BOLD, 48));
        title.setFill(Color.web("#5F4B3C"));

        Text tagline = new Text("Styled by glam, inspired by you");
        tagline.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        tagline.setFill(Color.web("#5F4B3C"));

        VBox headerBox = new VBox(title, tagline);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setSpacing(5);
        headerBox.setPadding(new Insets(10, 0, 30, 0));

        // Image container
        StackPane imageBox = new StackPane(avtaarImageView);
        imageBox.setPadding(new Insets(30));
        imageBox.setStyle("-fx-background-color: white; " +
                          "-fx-border-color: #5F4B3C; " +
                          "-fx-border-width: 2px; " +
                          "-fx-border-radius: 20px; " +
                          "-fx-background-radius: 20px;");
        imageBox.setEffect(new DropShadow(10, Color.LIGHTGRAY));

        VBox contentBox = new VBox(headerBox, imageBox);
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setSpacing(20);
        contentBox.setPadding(new Insets(20));

        // Scroll pane
        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setPadding(new Insets(10));

        // Back button
        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: #ead7c6; -fx-text-fill: #5F4B3C; -fx-font-size: 14px;");
        backBtn.setOnAction(e -> {
            GaneshChaturthiOutfits outfitsPage = new GaneshChaturthiOutfits();
            Scene previousScene = outfitsPage.getScene(stage);
            stage.setScene(previousScene);
        });

        HBox topBar = new HBox(backBtn);
        topBar.setAlignment(Pos.TOP_LEFT);
        topBar.setPadding(new Insets(20, 0, 0, 20));

        VBox topSection = new VBox(topBar, headerBox);
        topSection.setAlignment(Pos.TOP_CENTER);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topSection);
        mainLayout.setCenter(scrollPane);
        mainLayout.setStyle("-fx-background-color: #fff9f2;");

        return new Scene(mainLayout, 1200, 800);
    }
}
