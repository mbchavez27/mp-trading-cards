package com.tradingcards.elements.mainMenu;

import com.tradingcards.elements.binder.BinderController;
import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.binder.BinderView;
import com.tradingcards.elements.card.CardController;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.card.CardView;
import com.tradingcards.elements.collection.CollectionModel;
import java.util.Scanner;

public class MainMenuController {

    private CollectionModel sharedCollection = new CollectionModel();

    public void runMenu() {
        CardModel cardModel = new CardModel();
        CardView cardView = new CardView();
        CardController cardController = new CardController(sharedCollection, cardModel, cardView);

        BinderModel binderModel = new BinderModel();
        BinderView binderView = new BinderView();
        BinderController binderController = new BinderController(sharedCollection, binderModel, binderView);

        int action = 0;
        boolean createdDeck = false;
        Scanner getAction = new Scanner(System.in);

        do {
            System.out.println("-------------------------------");
            System.out.println("Trading Card Inventory System:");
            System.out.println("-------------------------------");
            System.out.println("[1] Add Card");

            if (sharedCollection.getBinderCollection().isEmpty()) {
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

            if (!sharedCollection.getCardCollection().isEmpty()) {
                System.out.println("[6] Increase/decrease card counts");
            }

            System.out.print("Action: ");
            action = getAction.nextInt();

            switch (action) {
                case 1 -> {
                    System.out.println("");
                    cardController.addCard();
                    System.out.println("");
                }

                case 2 -> {
                    binderController.addBinder();
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
