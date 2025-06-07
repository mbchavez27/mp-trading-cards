package com.elements.mainMenu;
import java.util.Scanner;

public class mainMenuController {

    private mainMenuModel model;
    private mainMenuView view;

    public mainMenuController(mainMenuModel model, mainMenuView view){
        this.model = model;
        this.view = view;
    }

    public int start(){
        Scanner sc = new Scanner(System.in);
        String message1 = model.cardMessage();
        String message2 = model.binderMessage();
        String message3 = model.deckMessage();
        view.displayMenu(message1, message2, message3);

        //lacks error handling
        int choice = sc.nextInt();

        sc.close();

        return choice;
    }



}
