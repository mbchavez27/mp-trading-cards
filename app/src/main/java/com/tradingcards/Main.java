// package com.tradingcards;

//import com.tradingcards.elements.menus.MainMenuController;

// public class Main {

//     public static void main(String[] args) {
//         MainMenuController mainMenu = new MainMenuController();
//         mainMenu.runMenu();
//     }
// }

package com.tradingcards;

import com.tradingcards.elements.menus.MainMenuView;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.main.java.com.tradingcards.elements.menus.SceneManager;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

        MainMenuView menuView = new MainMenuView(stage);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        Button button1 = new Button("Start");
        button1.setOnAction(e -> {
            stage.setScene(menuView.getScene());
        });

        Button button2 = new Button("Exit");


        Label title = new Label("Trading Card Inventory System");
        vbox.getChildren().addAll(title, button1, button2);

        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Trading Cards App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
