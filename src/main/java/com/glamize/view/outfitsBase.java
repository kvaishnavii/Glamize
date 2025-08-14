package com.glamize.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public abstract class outfitsBase {

    private static String selectedOccasion = "Festive";

    public static void setOccasion(String occasion) {
        selectedOccasion = occasion;
    }

    public void start(Stage stage) {
        stage.setTitle("Glamize - Outfits");

        String[] imagePaths = getImagePathsForOccasion();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.TOP_CENTER);

        for (int i = 0; i < imagePaths.length; i++) {
            VBox box = new VBox(10);
            box.setAlignment(Pos.CENTER);
            box.setStyle("-fx-background-color: #FFFFFFAA; -fx-border-color: #EAD7C6; -fx-border-width: 1px; -fx-border-radius: 10; -fx-background-radius: 10;");
            box.setPadding(new Insets(10));
            box.setPrefSize(180, 400);

            try {
                Image image = new Image(getClass().getResourceAsStream(imagePaths[i]));
                ImageView itemImage = new ImageView(image);
                itemImage.setFitWidth(160);
                itemImage.setFitHeight(300);
                itemImage.setPreserveRatio(false);

                Label label = new Label(selectedOccasion + " " + (i + 1));
                label.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                label.setTextFill(Color.web("#5F4B3C"));

                box.getChildren().addAll(itemImage, label);
            } catch (Exception e) {
                Label errorLabel = new Label("Image not found");
                errorLabel.setTextFill(Color.RED);
                box.getChildren().add(errorLabel);
            }

            int row = i / 4;
            int col = i % 4;
            gridPane.add(box, col, row);
        }

        Label glamizeTitle = new Label("Glamize");
        glamizeTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 40));
        glamizeTitle.setTextFill(Color.web("#5F4B3C"));

        VBox rootLayout = new VBox(20);
        rootLayout.setPadding(new Insets(20));
        rootLayout.setAlignment(Pos.TOP_CENTER);
        rootLayout.setStyle("-fx-background-color: #F5F0EB;");

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: transparent;");

        rootLayout.getChildren().addAll(glamizeTitle, scrollPane);

        Scene scene = new Scene(rootLayout, 1000, 750);
        stage.setScene(scene);
        stage.show();
    }

    protected abstract String getImageFolder();

    private String[] getImagePathsForOccasion() {
        String folder = getImageFolder() + selectedOccasion;
        String prefix;

        switch (selectedOccasion.toLowerCase()) {
            case "party":
                prefix = "party";
                break;
            case "work":
                prefix = "work";
                break;
            case "casual":
                prefix = "casual";
                break;
            default:
                prefix = "trad";
        }

        String[] paths = new String[12];
        for (int i = 0; i < 12; i++) {
            paths[i] = folder + "/" + prefix + (i + 1) + ".png";
        }
        return paths;
    }
}
