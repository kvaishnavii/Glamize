package com.glamize.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StylingMenPage extends Application {

    private ToggleGroup bodyGroup = new ToggleGroup();
    private ToggleGroup skinGroup = new ToggleGroup();
    private ToggleGroup weatherGroup = new ToggleGroup();
    private ToggleGroup occasionGroup = new ToggleGroup();

    private String selectedBodyType = null;
    private String selectedSkinTone = null;
    private String selectedWeather = null;
    private String selectedOccasion = null;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Glamize - Styling Page (Men)");

        Image bgImage = new Image(getClass().getResource("/assets/Images/menbg.png").toExternalForm(), 1300, 800, false, true);
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.fitWidthProperty().bind(primaryStage.widthProperty());
        bgImageView.fitHeightProperty().bind(primaryStage.heightProperty());
        bgImageView.setOpacity(0.4);

        BorderPane mainContent = new BorderPane();
        mainContent.setPadding(new Insets(20));

        // ✅ Top section with Back button, Title and Tagline
        Button backBtn = new Button("← Back");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        backBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #5F4B3C;");
        backBtn.setOnAction(e -> {
            homePage home = new homePage();
            try {
                home.start(primaryStage); // Navigate to home page using same stage
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Label title = new Label("glamize");
        title.setFont(Font.font("Serif", FontWeight.BOLD, 48));
        title.setTextFill(Color.web("#5F4B3C"));
        title.setEffect(new DropShadow(2, Color.GRAY));

        Label tagline = new Label("Styled by glam, inspired by you.");
        tagline.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        tagline.setTextFill(Color.web("#5F4B3C"));

        HBox topBar = new HBox(backBtn);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(5, 0, 5, 10));

        VBox topBox = new VBox(5, topBar, title, tagline);
        topBox.setAlignment(Pos.CENTER);
        mainContent.setTop(topBox);
        BorderPane.setAlignment(topBox, Pos.CENTER);

        // ✅ Center selection options
        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(30));
        centerGrid.setHgap(40);
        centerGrid.setVgap(40);
        centerGrid.setAlignment(Pos.CENTER);

        VBox bodyTypeBox = createSection("Body Type", new String[]{"Fit", "Average", "Athletic", "Heavy"}, bodyGroup, "body");
        VBox weatherBox = createSection("Weather", new String[]{"Sunny", "Rainy", "Winter", "Cloudy"}, weatherGroup, "weather");
        VBox skinToneBox = createSection("Skin Tone", new String[]{"Fair", "Medium", "Olive", "Dark"}, skinGroup, "skin");
        VBox occasionBox = createSection("Occasion", new String[]{"Casual", "Party", "Work", "Festive"}, occasionGroup, "occasion");

        centerGrid.add(weatherBox, 0, 0);
        centerGrid.add(occasionBox, 0, 1);
        centerGrid.add(bodyTypeBox, 1, 0);
        centerGrid.add(skinToneBox, 1, 1);

        mainContent.setCenter(centerGrid);

        // ✅ Bottom Next button
        Button nextBtn = new Button("Next");
        nextBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nextBtn.setStyle("-fx-background-color: #5F4B3C; -fx-text-fill: #EAD7C6;");
        nextBtn.setOnAction(e -> {
            if (selectedBodyType == null || selectedSkinTone == null || selectedWeather == null || selectedOccasion == null) {
                showAlert("Please select all four options before proceeding.");
                return;
            }

            System.out.println("Body: " + selectedBodyType + ", Skin: " + selectedSkinTone +
                    ", Weather: " + selectedWeather + ", Occasion: " + selectedOccasion);

            openMenOutfits(selectedOccasion);
            primaryStage.close();
        });

        VBox bottomBox = new VBox(nextBtn);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(20));
        mainContent.setBottom(bottomBox);

        // ✅ Stack everything with background image
        StackPane layeredPane = new StackPane();
        layeredPane.getChildren().addAll(bgImageView, mainContent);

        Scene scene = new Scene(layeredPane, 1300, 800);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); // 🖥️ Maximize on launch
        primaryStage.show();
    }

    private VBox createSection(String title, String[] options, ToggleGroup group, String type) {
        Label label = new Label(title);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        label.setTextFill(Color.web("#5F4B3C"));

        VBox section = new VBox(15);
        section.setAlignment(Pos.TOP_CENTER);
        section.setPadding(new Insets(15));
        section.setPrefSize(300, 300);
        section.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6); -fx-background-radius: 15;");
        section.getChildren().add(label);

        GridPane optionsGrid = new GridPane();
        optionsGrid.setHgap(10);
        optionsGrid.setVgap(10);
        optionsGrid.setAlignment(Pos.CENTER);

        for (int i = 0; i < options.length; i++) {
            ToggleButton button = new ToggleButton(options[i]);
            button.setFont(Font.font("Arial", 14));
            button.setPrefWidth(120);
            button.setToggleGroup(group);

            String defaultStyle = "-fx-background-color: #EAD7C6; -fx-text-fill: #5F4B3C; -fx-font-weight: bold;";
            String selectedStyle = "-fx-background-color: #5F4B3C; -fx-text-fill: #EAD7C6; -fx-font-weight: bold;";

            button.setStyle(defaultStyle);

            button.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    button.setStyle(selectedStyle);
                    switch (type) {
                        case "body": selectedBodyType = button.getText(); break;
                        case "skin": selectedSkinTone = button.getText(); break;
                        case "weather": selectedWeather = button.getText(); break;
                        case "occasion": selectedOccasion = button.getText(); break;
                    }
                } else {
                    button.setStyle(defaultStyle);
                }
            });

            optionsGrid.add(button, i % 2, i / 2);
        }

        section.getChildren().add(optionsGrid);
        return section;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Incomplete Selection");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void openMenOutfits(String occasion) {
        MenOutfits menOutfits = new MenOutfits(occasion);
        Stage outfitStage = new Stage();
        menOutfits.start(outfitStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
