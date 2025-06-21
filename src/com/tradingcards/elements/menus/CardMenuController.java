package com.tradingcards.elements.menus;

import com.tradingcards.elements.card.CardController;

import java.util.Scanner;

public class CardMenuController {

    private CardController cardController;
    public static final Scanner getAction = new Scanner(System.in);
    public CardMenuController(CardController controller){
        this.cardController = controller;
    }

    public void runMenu(){
        int action;

        do{
            ManageCards();
            System.out.print("Action: ");
            action = getAction.nextInt();
            getAction.nextLine(); // Clears input buffer
            switch(action){
                case 1:
                    cardController.displayCard();
                    break;
                case 2:
                    cardController.displayCollection();
                    break;
                case 3:
                    cardController.modifyCardQuantity();
                    break;
            }

        } while (action != 0);
    }
    /**
     * Displays the card management submenu options.
     */
    private void ManageCards() {
        System.out.println("-------------------------------");
        System.out.println("Manage Cards:");
        System.out.println("-------------------------------");
        System.out.println("[1] Display Card");
        System.out.println("[2] Display Collection");
        System.out.println("[3] Increase/decrease card counts");
        System.out.println("[0] Exit Menu");
    }
}
