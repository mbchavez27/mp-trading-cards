// package com.tradingcards;

// import com.tradingcards.elements.menus.MainMenuController;

// public class Main {

//     public static void main(String[] args) {
//         MainMenuController mainMenu = new MainMenuController();
//         mainMenu.runMenu();
//     }
// }

package com.tradingcards;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("JavaFX works!");
        Scene scene = new Scene(label, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Trading Cards App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
