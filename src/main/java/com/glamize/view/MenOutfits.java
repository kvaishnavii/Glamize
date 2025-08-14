package com.glamize.view;

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

public class MenOutfits {

    private final String occasion;

    public MenOutfits(String occasion) {
        this.occasion = occasion;
    }

    public void start(Stage stage) {
        stage.setTitle("Glamize - Men's Outfits");

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.setMaximized(true);

        // 🌟 Background Image with Opacity
        Image bgImage = new Image(getClass().getResourceAsStream("/assets/Images/menbg.png"));
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setPreserveRatio(false);
        bgImageView.setOpacity(0.6);
        bgImageView.fitWidthProperty().bind(stage.widthProperty());
        bgImageView.fitHeightProperty().bind(stage.heightProperty());

        // 🌟 Title
        Text title = new Text("glamize");
        title.setFont(Font.font("Georgia", FontWeight.EXTRA_BOLD, 42));
        title.setFill(Color.web("#4a2c23"));

        Text tagline = new Text("Styled by glam, inspired by you");
        tagline.setFont(Font.font("Georgia", 20));
        tagline.setFill(Color.web("#4c3223ff"));

        VBox titleBox = new VBox(5, title, tagline);
        titleBox.setAlignment(Pos.CENTER);

        // 🌟 Back Button
        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: #f7f0eb; -fx-text-fill: #4a2c23; -fx-font-weight: bold;");
        backBtn.setOnAction(e -> {
            StylingMenPage stylingPage = new StylingMenPage();
            try {
                stylingPage.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox header = new HBox(backBtn);
        header.setAlignment(Pos.TOP_LEFT);
        header.setPadding(new Insets(10, 0, 0, 10));

        // 🌟 Outfit Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setHgap(30);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);

        String[] imageNames = getImagesForOccasion();
        int col = 0, row = 0;
        for (String imgPath : imageNames) {
            Image image = new Image(getClass().getResourceAsStream(imgPath));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(170);
            imageView.setFitHeight(250);

            String fileName = imgPath.substring(imgPath.lastIndexOf("/") + 1);
            String outfitName = fileName.substring(0, fileName.lastIndexOf("."));

            Button viewBtn = new Button("View");
            viewBtn.setStyle("-fx-background-color: #ead7c6; -fx-text-fill: #5F4B3C; -fx-font-weight: bold;");
            viewBtn.setOnAction(event -> {
                MenViewOutfitPage viewPage = new MenViewOutfitPage();
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

        // 🌟 Scroll Pane
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        // Hide scroll bar visually (optional)
        scrollPane.lookupAll(".scroll-bar").forEach(node -> node.setVisible(false));

        VBox scrollContainer = new VBox(scrollPane);
        scrollContainer.setPadding(new Insets(20));
        scrollContainer.setMaxWidth(900);
        scrollContainer.setAlignment(Pos.CENTER);
        scrollContainer.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.7); " +
                "-fx-background-radius: 20;"
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
                        "/assets/Images/men/Casual/casual1.png", "/assets/Images/men/Casual/casual2.png",
                        "/assets/Images/men/Casual/casual3.png", "/assets/Images/men/Casual/casual4.png",
                        "/assets/Images/men/Casual/casual5.png", "/assets/Images/men/Casual/casual6.png",
                        "/assets/Images/men/Casual/casual7.png", "/assets/Images/men/Casual/casual8.png",
                        "/assets/Images/men/Casual/casual9.png", "/assets/Images/men/Casual/casual10.png",
                        "/assets/Images/men/Casual/casual11.png", "/assets/Images/men/Casual/casual12.png"
                };
            case "festive":
                return new String[]{
                        "/assets/Images/men/Festive/festive1.jpg", "/assets/Images/men/Festive/festive2.jpg",
                        "/assets/Images/men/Festive/festive3.jpg", "/assets/Images/men/Festive/festive4.jpg",
                        "/assets/Images/men/Festive/festive5.jpg", "/assets/Images/men/Festive/festive6.jpg",
                        "/assets/Images/men/Festive/festive7.jpg", "/assets/Images/men/Festive/festive8.jpg",
                        "/assets/Images/men/Festive/festive9.jpg", "/assets/Images/men/Festive/festive10.jpg",
                        "/assets/Images/men/Festive/festive11.jpg", "/assets/Images/men/Festive/festive12.jpg"
                };
            case "party":
                return new String[]{
                        "/assets/Images/men/Party/party1.jpg", "/assets/Images/men/Party/party2.jpg",
                        "/assets/Images/men/Party/party3.jpg", "/assets/Images/men/Party/party4.jpg",
                        "/assets/Images/men/Party/party5.jpg", "/assets/Images/men/Party/party6.jpg",
                        "/assets/Images/men/Party/party7.jpg", "/assets/Images/men/Party/party8.jpg",
                        "/assets/Images/men/Party/party9.jpg", "/assets/Images/men/Party/party10.jpg",
                        "/assets/Images/men/Party/party11.jpg", "/assets/Images/men/Party/party12.jpg"
                };
            case "work":
                return new String[]{
                        "/assets/Images/men/Work/work1.jpg", "/assets/Images/men/Work/work2.jpg",
                        "/assets/Images/men/Work/work3.jpg", "/assets/Images/men/Work/work4.jpg",
                        "/assets/Images/men/Work/work5.jpg", "/assets/Images/men/Work/work6.jpg",
                        "/assets/Images/men/Work/work7.jpg", "/assets/Images/men/Work/work8.jpg",
                        "/assets/Images/men/Work/work9.jpg", "/assets/Images/men/Work/work10.jpg",
                        "/assets/Images/men/Work/work11.jpg", "/assets/Images/men/Work/work12.jpg"
                };
            default:
                return new String[0];
        }
    }
}
