// package com.tradingcards;

//import com.tradingcards.elements.menus.MainMenuController;

// public class Main {

//     public static void main(String[] args) {
//         MainMenuController mainMenu = new MainMenuController();
//         mainMenu.runMenu();
//     }
// }

package com.tradingcards;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        Scene homeScene = new Scene(vbox, 400, 300);
        stage.setScene(homeScene);
        stage.setTitle("Trading Cards App");

        com.tradingcards.elements.menus.MainMenu menuView = new com.tradingcards.elements.menus.MainMenu(stage, homeScene);

        //Functionality for button 1
        Button button1 = new Button("Start");
        button1.setOnAction(e -> {
            stage.setScene(menuView.getScene());
        });

        //Functionality for button 2
        Button button2 = new Button("Close Program");
        button2.setOnAction(e -> stage.close());

        Label title = new Label("    Trading Card\n Inventory System");
        vbox.getChildren().addAll(title, button1, button2);

        homeScene.getStylesheets().add(getClass().getResource("/styles/HomeMenu.css").toExternalForm());

        //Displays stage
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
