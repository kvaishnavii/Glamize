package com.glamize;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AboutUsPage extends Application {

    public void start(Stage stage) {
        VBox content = new VBox(30);
        content.setPadding(new Insets(50));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: transparent;");
        content.setFillWidth(true);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent;");
        scrollPane.setPannable(true);
        scrollPane.setVvalue(0);

        Stop[] stops = new Stop[] {
            new Stop(0, Color.web("#fdf6e3")),
            new Stop(0.5, Color.web("#fffaf0")),
            new Stop(1, Color.web("#f5e4c3"))
        };
        BackgroundFill bgFill = new BackgroundFill(
            new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops),
            CornerRadii.EMPTY,
            Insets.EMPTY
        );
        StackPane root = new StackPane(scrollPane);
        root.setBackground(new Background(bgFill));

        Label appName = new Label("Glamize");
        appName.setFont(Font.font("Georgia", FontWeight.BOLD, 48));
        appName.setStyle("-fx-text-fill: #3e3e3e;");

        Label tagline = new Label("Styled by Glam, Inspired by You.");
        tagline.setFont(Font.font("Arial", FontPosture.ITALIC, 24));
        tagline.setStyle("-fx-text-fill: #3e3e3e;");

        content.getChildren().addAll(
            appName, tagline,
            sectionHeading(" About the Glamize App"),
            sectionText("Glamize is an intelligent outfit recommendation application designed to help users choose the perfect attire based on weather, occasion, personal preferences, and style trends. It empowers users with confidence and convenience by making fashion accessible and personalized. Our motto is to blend technology with style, offering smart suggestions that solve real-world styling dilemmas."),
            sectionHeading("🤝 Team Contribution - The Codespires"),
            sectionText("The Glamize project is a proud creation by The Codespires, a team of passionate developers who envisioned a smart fashion companion. From ideation to implementation, the team worked diligently to design, develop, and refine the application. Each member played a crucial role in UI/UX design, coding, logic development, and testing."),
            sectionHeading("👩‍💻 Project Created By"),
            sectionText("\u2022 Ms. Mansi Devharkar\n\u2022 Ms. Vaishnavi Karle\n\u2022 Ms. Sanika Ahire"),
            sectionHeading("🧑‍🗰️ Guided By"),
            sectionText("Ms. Mansi Jadhav, whose guidance and expertise have been invaluable throughout the development of this project. Her support and mentorship helped the team stay focused, motivated, and aligned with industry standards."),
            sectionHeading("🎓 Faculty Head - Mr. Shashikant Bagal"),
            sectionText("Mr. Shashikant Bagal, fondly known as Shashi Sir, is an accomplished technologist and educator. He founded Core2Web in 2017 and has since mentored thousands of students in programming, system design, and modern frameworks. He is also the co-founder of Incubators System Pvt. Ltd., making a significant impact on student innovation and startup culture."),
            createImageCard("/assets/Images/shashikant_bagal.png", "Mr. Shashikant Bagal"),
            sectionHeading("🏫 About Core2Web"),
            sectionText("Core2Web is a renowned coding institute and startup incubator based in Pune, India. Since its inception in 2017, it has trained over 15,000 students in technologies such as Java, Python, C++, Web Development, React, Flutter, and more. With the tagline “Know the code, till the core,” it continues to bridge the gap between academic learning and real-world development."),
            sectionHeading("📬 Contact Us"),
            sectionText("Email: glamize.team@gmail.com\nInstagram: instagram.com/core2web\nLinkedIn: linkedin.com/company/core2web\nWebsite: www.core2web.in")
        );

        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        backButton.setStyle("-fx-background-color: #f5deb3; -fx-text-fill: #333;");
        backButton.setOnAction(e -> stage.close());
        content.getChildren().add(backButton);

        Scene scene = new Scene(root);
        stage.setTitle("About Us - Glamize");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        scrollPane.setVvalue(0);
    }

    private Label sectionHeading(String text) {
        Label heading = new Label(text);
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        heading.setStyle("-fx-text-fill: #3e3e3e;");
        heading.setWrapText(true);
        heading.setAlignment(Pos.TOP_LEFT);
        return heading;
    }

    private Label sectionText(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", 16));
        label.setStyle("-fx-text-fill: #3e3e3e;");
        label.setWrapText(true);
        label.setAlignment(Pos.TOP_LEFT);
        return label;
    }

    private VBox createImageCard(String imagePath, String caption) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);

        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);

            // Circular clip
            Circle clip = new Circle(100, 100, 100);
            imageView.setClip(clip);

            // White border using second circle
            Circle border = new Circle(100, 100, 102);
            border.setFill(Color.WHITE);

            // Stack image and border
            StackPane imageStack = new StackPane(border, imageView);
            imageStack.setPadding(new Insets(10));
            imageStack.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 5);");

            // Fade in image
            FadeTransition fade = new FadeTransition(Duration.seconds(1.5), imageStack);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();

            // Caption with fade-in
            Label label = new Label(caption);
            label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            label.setStyle("-fx-text-fill: #3e3e3e;");
            label.setOpacity(0);

            FadeTransition fadeText = new FadeTransition(Duration.seconds(1.2), label);
            fadeText.setFromValue(0);
            fadeText.setToValue(1);
            fadeText.setDelay(Duration.seconds(1.0));
            fadeText.play();

            card.getChildren().addAll(imageStack, label);
        } catch (Exception e) {
            Label error = new Label("Image could not be loaded.");
            error.setStyle("-fx-text-fill: red;");
            card.getChildren().add(error);
        }

        return card;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
