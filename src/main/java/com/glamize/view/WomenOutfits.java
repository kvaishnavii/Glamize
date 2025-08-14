package com.glamize.view;

import java.io.InputStream;

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

public class WomenOutfits {

    private final String occasion;

    public WomenOutfits(String occasion) {
        this.occasion = occasion;
    }

    public void start(Stage stage) {
        stage.setTitle("Glamize - Women's Outfits");

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.setMaximized(true);

        // 🌸 Background image with opacity
        Image bgImage = new Image(getClass().getResourceAsStream("/assets/Images/womenbg.png"));
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setPreserveRatio(false);
        bgImageView.setOpacity(0.6);
        bgImageView.fitWidthProperty().bind(stage.widthProperty());
        bgImageView.fitHeightProperty().bind(stage.heightProperty());

        // 🌸 Title and Tagline
        Text title = new Text("glamize");
        title.setFont(Font.font("Georgia", FontWeight.EXTRA_BOLD, 42));
        title.setFill(Color.web("#4a2c23"));

        Text tagline = new Text("Styled by glam, inspired by you");
        tagline.setFont(Font.font("Georgia", 20));
        tagline.setFill(Color.web("#4c3223ff"));

        VBox titleBox = new VBox(5, title, tagline);
        titleBox.setAlignment(Pos.CENTER);

        // 🌸 Back Button
        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: #f7f0eb; -fx-text-fill: #4a2c23; -fx-font-weight: bold;");
        backBtn.setOnAction(e -> {
            StylingWomenPage stylingPage = new StylingWomenPage();
            try {
                stylingPage.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox header = new HBox(backBtn);
        header.setAlignment(Pos.TOP_LEFT);
        header.setPadding(new Insets(10, 0, 0, 10));

        // 🌸 Outfit Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setHgap(30);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);

        String[] imageNames = getImagesForOccasion();
        int col = 0, row = 0;

        for (String imgPath : imageNames) {
            InputStream imageStream = getClass().getResourceAsStream(imgPath);
            if (imageStream == null) {
                System.out.println("❌ Image not found: " + imgPath);
                continue;
            }

            Image image = new Image(imageStream);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(160);
            imageView.setFitHeight(230);
            imageView.setStyle("-fx-border-color: red;"); // Optional: See image boundaries

            String fileName = imgPath.substring(imgPath.lastIndexOf("/") + 1);
            String outfitName = fileName.substring(0, fileName.lastIndexOf("."));

            Button viewBtn = new Button("View");
            viewBtn.setStyle("-fx-background-color: #ead7c6; -fx-text-fill: #5F4B3C; -fx-font-weight: bold;");
            viewBtn.setOnAction(event -> {
                WomenViewOutfitPage viewPage = new WomenViewOutfitPage();
                stage.setScene(viewPage.getScene(stage, outfitName, occasion));
            });

            VBox itemBox = new VBox(10, imageView, viewBtn);
            itemBox.setAlignment(Pos.CENTER);

            grid.add(itemBox, col, row);
            col++;
            if (col >= 3) {
                col = 0;
                row++;
            }
        }

        // 🌸 Scroll Pane
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(580);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
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

    private String[] getImagesForOccasion() {
        switch (occasion.toLowerCase()) {
            case "casual":
                return new String[]{
                        "/assets/Images/women/Casual/casual1.png", "/assets/Images/women/Casual/casual2.png",
                        "/assets/Images/women/Casual/casual3.png", "/assets/Images/women/Casual/casual4.png",
                        "/assets/Images/women/Casual/casual5.png", "/assets/Images/women/Casual/casual6.png",
                        "/assets/Images/women/Casual/casual7.png", "/assets/Images/women/Casual/casual8.png",
                        "/assets/Images/women/Casual/casual9.png", "/assets/Images/women/Casual/casual10.png",
                        "/assets/Images/women/Casual/casual11.png", "/assets/Images/women/Casual/casual12.png"
                };
            case "festive":
                return new String[]{
                        "/assets/Images/women/Festive/festive1.png", "/assets/Images/women/Festive/festive2.png",
                        "/assets/Images/women/Festive/festive3.png", "/assets/Images/women/Festive/festive4.png",
                        "/assets/Images/women/Festive/festive5.png", "/assets/Images/women/Festive/festive6.png",
                        "/assets/Images/women/Festive/festive7.png", "/assets/Images/women/Festive/festive8.png",
                        "/assets/Images/women/Festive/festive9.png", "/assets/Images/women/Festive/festive10.png",
                        "/assets/Images/women/Festive/festive11.png", "/assets/Images/women/Festive/festive12.png"
                };
            case "party":
                return new String[]{
                        "/assets/Images/women/Party/party1.png", "/assets/Images/women/Party/party2.png",
                        "/assets/Images/women/Party/party3.png", "/assets/Images/women/Party/party4.png",
                        "/assets/Images/women/Party/party5.png", "/assets/Images/women/Party/party6.png",
                        "/assets/Images/women/Party/party7.png", "/assets/Images/women/Party/party8.png",
                        "/assets/Images/women/Party/party9.png", "/assets/Images/women/Party/party10.png",
                        "/assets/Images/women/Party/party11.png", "/assets/Images/women/Party/party12.png"
                };
            case "work":
                return new String[]{
                        "/assets/Images/women/Work/work1.png", "/assets/Images/women/Work/work2.png",
                        "/assets/Images/women/Work/work3.png", "/assets/Images/women/Work/work4.png",
                        "/assets/Images/women/Work/work5.png", "/assets/Images/women/Work/work6.png",
                        "/assets/Images/women/Work/work7.png", "/assets/Images/women/Work/work8.png",
                        "/assets/Images/women/Work/work9.png", "/assets/Images/women/Work/work10.png",
                        "/assets/Images/women/Work/work11.png", "/assets/Images/women/Work/work12.png"
                };
            default:
                return new String[0];
        }
    }
}
