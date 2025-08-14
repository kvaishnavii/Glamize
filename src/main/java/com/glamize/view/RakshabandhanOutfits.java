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

public class RakshabandhanOutfits extends Application {

    private final int TOTAL_IMAGES = 12;

    @Override
    public void start(Stage stage) {
        // Get full screen dimensions
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.setTitle("Rakshabandhan Outfits");
        stage.setScene(getScene(stage));
        stage.show();
    }

    public Scene getScene(Stage stage) {
        // Load background image
        Image bg;
        try {
            bg = new Image(getClass().getResource("/assets/Images/background.png").toExternalForm());
        } catch (Exception e) {
            System.out.println("Background image not found!");
            return new Scene(new StackPane(), 800, 600);
        }

        ImageView bgView = new ImageView(bg);
        bgView.setPreserveRatio(false);
        bgView.setOpacity(0.3);
        bgView.fitWidthProperty().bind(stage.widthProperty());
        bgView.fitHeightProperty().bind(stage.heightProperty());

        StackPane root = new StackPane(bgView);

        // Titles
        Text glamTitle = new Text("glamize");
        glamTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 60));
        glamTitle.setFill(Color.web("#5F4B3C"));

        Text tagline = new Text("Styled by Glam, inspired by you");
        tagline.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 18));
        tagline.setFill(Color.web("#5F4B3C"));

        Text subtitle = new Text("Rakshabandhan Outfits");
        subtitle.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        subtitle.setFill(Color.web("#5F4B3C"));

        VBox titleBox = new VBox(5, glamTitle, tagline, subtitle);
        titleBox.setAlignment(Pos.CENTER);

        // Grid
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(30));
        grid.setAlignment(Pos.TOP_CENTER);

        int col = 0, row = 0;
        for (int i = 1; i <= TOTAL_IMAGES; i++) {
            VBox imageBox = new VBox(10);
            imageBox.setAlignment(Pos.CENTER);

            String imgName = "raksha" + i;
            Image image = null;

            try {
                image = new Image(getClass().getResource("/assets/Images/rakhi/" + imgName + ".jpg").toExternalForm());
            } catch (Exception ex) {
                try {
                    image = new Image(getClass().getResource("/assets/Images/rakhi/" + imgName + ".png").toExternalForm());
                } catch (Exception ignored) {
                    System.out.println("Image not found: " + imgName);
                    continue;
                }
            }

            ImageView outfitImage = new ImageView(image);
            outfitImage.setFitWidth(250);
            outfitImage.setFitHeight(330);
            outfitImage.setPreserveRatio(false);

            Button viewButton = new Button("View");
            viewButton.setStyle("-fx-background-color: #5F4B3C; -fx-text-fill: white; -fx-background-radius: 10;");
            String finalImgName = imgName;
            viewButton.setOnAction(e -> {
                ViewOutfitPage viewPage = new ViewOutfitPage();
                stage.setScene(viewPage.getScene(stage, finalImgName));
            });

            imageBox.getChildren().addAll(outfitImage, viewButton);
            grid.add(imageBox, col, row);
            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent;");

        VBox transparentBox = new VBox(scrollPane);
        transparentBox.setAlignment(Pos.CENTER);
        transparentBox.setPadding(new Insets(20));
        transparentBox.setMaxWidth(1100);
        transparentBox.setStyle(
            "-fx-background-color: rgba(255, 255, 255, 0.6);" +
            "-fx-background-radius: 30;" +
            "-fx-border-radius: 30;"
        );

        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
        backButton.setStyle("-fx-background-color: #EAD7C6; -fx-text-fill: #5F4B3C;");
        backButton.setOnAction(e -> {
            com.glamize.view.homePage home = new com.glamize.view.homePage();
            try {
                home.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox backBox = new HBox(backButton);
        backBox.setAlignment(Pos.CENTER_LEFT);

        VBox mainLayout = new VBox(20, backBox, titleBox, transparentBox);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(30));

        root.getChildren().add(mainLayout);
        return new Scene(root);
    }
}
