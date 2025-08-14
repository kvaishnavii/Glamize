package com.glamize.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class homePage extends Application {

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();

        ImageView bgView = new ImageView(new Image(getClass().getResource("/assets/Images/homebg.png").toExternalForm()));
        bgView.setPreserveRatio(false);
        bgView.setOpacity(0.5);

        VBox mainContainer = new VBox(30);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(40));

        // Back Button
        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #4a2c23; -fx-font-size: 14px; -fx-font-weight: bold;");
        backBtn.setOnAction(e -> {
            try {
                new WelcomePage().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox topLeftBox = new HBox(backBtn);
        topLeftBox.setAlignment(Pos.TOP_LEFT);
        topLeftBox.setPadding(new Insets(0, 0, 0, 10));

        // Profile Button (static for now)
        HBox topRightButtons = new HBox(15);
        topRightButtons.setAlignment(Pos.TOP_RIGHT);
        ImageView profileIcon = new ImageView(new Image(getClass().getResource("/assets/Images/profile.png").toExternalForm()));
        profileIcon.setFitWidth(32);
        profileIcon.setFitHeight(32);
        Button profileBtn = new Button("", profileIcon);
        profileBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");

        addHoverEffect(profileBtn, "#f3e6db", "#ffffff");

        profileBtn.setOnAction(e -> {
            try {
                // TODO: Replace with your local profilePage or settings page
                new profilePage().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        topRightButtons.getChildren().add(profileBtn);

        // Title and Subtitle
        Text title = new Text("glamize");
        title.setFont(Font.font("Georgia", FontWeight.EXTRA_BOLD, 52));
        title.setFill(Color.web("#4a2c23"));

        Text subtitle = new Text("Styled by glam, inspired by you");
        subtitle.setFont(Font.font("Georgia", 22));
        subtitle.setFill(Color.web("#4c3223"));

        VBox titleBox = new VBox(12, title, subtitle);
        titleBox.setAlignment(Pos.CENTER);

        // Cards
        HBox cardBox = new HBox(50);
        cardBox.setAlignment(Pos.CENTER);
        VBox menCard = createCard("/assets/Images/men1.jpg", "Men", stage, "men");
        VBox womenCard = createCard("/assets/Images/women.png", "Women", stage, "women");
        VBox surpriseCard = createCard("/assets/Images/surprise.png", "Surprise Me", stage, "surprise");

        cardBox.getChildren().addAll(menCard, womenCard, surpriseCard);

        // Upcoming Events
        VBox upcomingBox = new VBox(10);
        upcomingBox.setPadding(new Insets(20));
        upcomingBox.setStyle("-fx-background-color: #EAD7C6; -fx-background-radius: 16px; -fx-border-color: #d8c1b2; -fx-border-radius: 16px;");
        upcomingBox.setMaxWidth(720);
        addHoverEffect(upcomingBox, "#ead7c6", "#f0e4d9");

        Text upcomingTitle = new Text("Upcoming Events");
        upcomingTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        upcomingTitle.setFill(Color.web("#3f2c23"));

        Button seeEvents = new Button("See Events");
        seeEvents.setStyle("-fx-background-color: #EAD7C6; -fx-background-radius: 20px; -fx-font-size: 15px; -fx-padding: 8 20;");
        addHoverEffect(seeEvents, "#EAD7C6", "#f0e4d9");

        VBox festiveList = new VBox(8);
        festiveList.setVisible(false);
        festiveList.setPadding(new Insets(10, 0, 0, 20));

        String[] festivals = {
                "Rakshabandhan",
                "Ganesh Chaturthi",
                
        };

        for (String fest : festivals) {
    Text festText = new Text("• " + fest);
    festText.setFont(Font.font("Georgia", 16));
    festText.setFill(Color.web("#4a2c23"));
    addHoverEffect(festText, "#f3e6db", "#ffffff");

    festText.setOnMouseClicked(e -> {
        try {
            switch (fest) {
                case "Rakshabandhan" -> new RakshabandhanOutfits().start(new Stage());
                case "Ganesh Chaturthi" -> new GaneshChaturthiOutfits().start(new Stage());
                
            }
            stage.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    });

    festiveList.getChildren().add(festText);
}


        seeEvents.setOnAction(e -> festiveList.setVisible(!festiveList.isVisible()));
        upcomingBox.getChildren().addAll(upcomingTitle, seeEvents, festiveList);

        VBox topRow = new VBox(topLeftBox, topRightButtons);
        topRow.setSpacing(10);
        topRow.setAlignment(Pos.TOP_CENTER);

        mainContainer.getChildren().addAll(topRow, titleBox, cardBox, upcomingBox);
        root.getChildren().addAll(bgView, mainContainer);

        Scene scene = new Scene(root, 1200, 760);
        bgView.fitWidthProperty().bind(scene.widthProperty());
        bgView.fitHeightProperty().bind(scene.heightProperty());

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.setMaximized(true);

        stage.setTitle("Glamize - Welcome");
        stage.setScene(scene);
        stage.show();
    }

    private VBox createCard(String imagePath, String labelText, Stage stage, String type) {
        VBox card = new VBox(18);
        card.setPadding(new Insets(24));
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: #f3e6dbff; -fx-background-radius: 18px; -fx-border-color: #d8c1b2; -fx-border-radius: 18px;");
        card.setPrefSize(220, 300);

        DropShadow shadow = new DropShadow();
        card.setOnMouseEntered(e -> {
            card.setStyle("-fx-background-color: #fff9f5; -fx-background-radius: 18px; -fx-border-color: #d8c1b2; -fx-border-radius: 18px;");
            card.setEffect(shadow);
        });
        card.setOnMouseExited(e -> {
            card.setStyle("-fx-background-color: #f3e6dbff; -fx-background-radius: 18px; -fx-border-color: #d8c1b2; -fx-border-radius: 18px;");
            card.setEffect(null);
        });

        ImageView icon = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        icon.setFitWidth(130);
        icon.setFitHeight(160);

        Text label = new Text(labelText);
        label.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
        label.setFill(Color.web("#3f2c23"));

        Button openBtn = new Button("Open");
        openBtn.setStyle("-fx-background-color: #e0c8b2ff; -fx-background-radius: 20px; -fx-font-size: 13px; -fx-padding: 6 16;");
        addHoverEffect(openBtn, "#e0c8b2ff", "#f5e1d0");

        openBtn.setOnAction(e -> {
            try {
                switch (type) {
                    case "men" -> new StylingMenPage().start(stage);
                    case "women" -> new StylingWomenPage().start(stage);
                    case "surprise" -> new SurpriseOutfits().start(stage);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        card.getChildren().addAll(icon, label, openBtn);
        return card;
    }

    private void addHoverEffect(Region node, String normalColor, String hoverColor) {
        DropShadow shadow = new DropShadow();
        node.setOnMouseEntered(e -> {
            node.setStyle(node.getStyle() + String.format("-fx-background-color: %s;", hoverColor));
            node.setEffect(shadow);
        });
        node.setOnMouseExited(e -> {
            node.setStyle(node.getStyle().replace(hoverColor, normalColor));
            node.setEffect(null);
        });
    }

    private void addHoverEffect(Text node, String bgColor, String hoverBgColor) {
        DropShadow shadow = new DropShadow();
        node.setOnMouseEntered(e -> {
            node.setStyle("-fx-background-color: " + hoverBgColor + "; -fx-background-radius: 8px;");
            node.setEffect(shadow);
        });
        node.setOnMouseExited(e -> {
            node.setStyle("-fx-background-color: transparent;");
            node.setEffect(null);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
