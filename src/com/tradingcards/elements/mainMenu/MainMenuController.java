package com.tradingcards.elements.mainMenu;

import com.tradingcards.elements.card.CardController;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.card.CardView;
import java.util.Scanner;

public class MainMenuController {

    public static void runMenu() {
        CardModel cardModel = new CardModel();
        CardView cardView = new CardView();
        CardController cardController = new CardController(cardModel, cardView);

        int action = 0;
        boolean collectionPopulated = false;
        boolean createdBinder = false;
        boolean createdDeck = false;
        Scanner getAction = new Scanner(System.in);

        do {
            System.out.println("-------------------------------");
            System.out.println("Trading Card Inventory System:");
            System.out.println("-------------------------------");
            System.out.println("[1] Add Card");

            if (!createdBinder) {
                System.out.println("[2] Create a new Binder");
            } else {
                System.out.println("[2] Manage Binders");
            }

            if (!createdDeck) {
                System.out.println("[3] Create a new Deck");
            } else {
                System.out.println("[3] Manage Decks");
            }

            System.out.println("[4] Display Card");
            System.out.println("[5] Display Collection");

            if (collectionPopulated) {
                System.out.println("[6] Increase/decrease card counts");
            }

            System.out.print("Action: ");
            action = getAction.nextInt();

            switch (action) {
                case 1 -> {
                    collectionPopulated = true;
                    System.out.println("");
                    cardController.addCard();
                    System.out.println("");
                }

                case 2 -> {
                    createdBinder = true;
                }

                case 3 -> {
                    createdDeck = true;
                }

                case 4 -> {
                    System.out.println("");
                    cardController.displayCard();
                    System.out.println("");
                }

                case 5 -> {
                    System.out.println("");
                    cardController.displayCollection();
                    System.out.println("");
                }
                case 6 -> {
                    System.out.println("");
                    cardController.modifyCardQuantity();
                    System.out.println("");
                }


            }

        } while (action != 10);
        getAction.close();
    }
}
