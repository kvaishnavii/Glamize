
package com.glamize;

import com.glamize.model.FirebaseService;
import com.glamize.view.StylingMenPage;
import com.glamize.view.WelcomePage;
import com.glamize.view.homePage;
import com.glamize.view.profilePage;

//import com.glamize.view.select;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp {
    public void start(Stage primaryStage) {
        new FirebaseService(); // 👈 This will initialize Firebase
        //new LoginPage11().start(primaryStage);
    }
    public static void main(String[] args) {
    
        Application.launch( WelcomePage.class, args);

    }
}
