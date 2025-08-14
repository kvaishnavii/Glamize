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
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class profilePage extends Application {

    @Override
    public void start(Stage stage) {
        stage.setScene(getScene(stage));
        stage.setTitle("Glamize - My Profile");
        stage.show();
    }

    public Scene getScene(Stage stage) {
        StackPane root = new StackPane();

        ImageView bgView = new ImageView(new Image(getClass().getResource("/assets/Images/homebg.png").toExternalForm()));
        bgView.setPreserveRatio(false);
        bgView.setOpacity(0.3);

        VBox container = new VBox(25);
        container.setAlignment(Pos.TOP_CENTER);
        container.setPadding(new Insets(40));
        container.setMaxWidth(500);
        container.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 18px;");

        Text glamTitle = new Text("Glamize");
        glamTitle.setFont(Font.font("Georgia", FontWeight.EXTRA_BOLD, 44));
        glamTitle.setFill(Color.web("#4a2c23"));

        Text tagline = new Text("Styled by Glam, inspired by you");
        tagline.setFont(Font.font("Georgia", 20));
        tagline.setFill(Color.web("#4c3223"));

        ImageView profileImage = new ImageView(new Image(getClass().getResource("/assets/Images/profile.png").toExternalForm()));
        profileImage.setFitWidth(120);
        profileImage.setFitHeight(120);
        profileImage.setPreserveRatio(true);
        profileImage.setClip(new Circle(60, 60, 60));

        Text userName = new Text("mansi@gmail.com");
        userName.setFont(Font.font("Georgia", FontWeight.SEMI_BOLD, 22));
        userName.setFill(Color.web("#4a2c23"));

        // Buttons
        Button editProfileBtn = createStyledButton("Edit Profile");
        Button notificationsBtn = createStyledButton("Notifications");
        Button backBtn = createStyledButton("Back");

        editProfileBtn.setOnAction(e -> {
            EditProfilePage editPage = new EditProfilePage();
            stage.setScene(EditProfilePage.getScene(stage, null, null));

        });

        notificationsBtn.setOnAction(e -> {
            NotificationsPage notifPage = new NotificationsPage();
            stage.setScene(notifPage.getScene(stage));
        });

        backBtn.setOnAction(e -> {
            try {
                new homePage().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        container.getChildren().addAll(glamTitle, tagline, profileImage, userName,
                editProfileBtn, notificationsBtn, backBtn);

        root.getChildren().addAll(bgView, container);

        Scene scene = new Scene(root, 1200, 760);
        bgView.fitWidthProperty().bind(scene.widthProperty());
        bgView.fitHeightProperty().bind(scene.heightProperty());

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.setMaximized(true);

        return scene;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(220);
        button.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        button.setStyle("-fx-background-color: #e0c8b2ff; -fx-background-radius: 20px;");
        addHoverEffect(button, "#e0c8b2ff", "#f5e1d0");
        return button;
    }

    private void addHoverEffect(Region node, String normalColor, String hoverColor) {
        DropShadow shadow = new DropShadow();
        node.setOnMouseEntered(e -> {
            node.setStyle("-fx-background-color: " + hoverColor + "; -fx-background-radius: 20px;");
            node.setEffect(shadow);
        });
        node.setOnMouseExited(e -> {
            node.setStyle("-fx-background-color: " + normalColor + "; -fx-background-radius: 20px;");
            node.setEffect(null);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
