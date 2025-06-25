package com.tradingcards.elements.menus;

import com.tradingcards.elements.binder.BinderController;
import com.tradingcards.elements.binder.BinderView;
import com.tradingcards.elements.card.CardController;
import com.tradingcards.elements.card.CardView;
import com.tradingcards.elements.collection.CollectionModel;
import com.tradingcards.elements.deck.DeckController;
import com.tradingcards.elements.deck.DeckView;
import java.util.Scanner;

/**
 * Controller class for handling the main menu of the Trading Card Inventory
 * System. Initializes and coordinates all component controllers and handles
 * user input to navigate the system.
 */
public class MainMenuController {

    private CollectionModel sharedCollection = new CollectionModel();

    /**
     * Starts and runs the main menu loop. Handles user input to navigate
     * through different functionalities such as managing cards, binders, and
     * decks.
     */
    public void runMenu() {
        CardView cardView = new CardView();
        CardController cardController = new CardController(sharedCollection, cardView);

        BinderView binderView = new BinderView();
        BinderController binderController = new BinderController(sharedCollection, binderView);

        DeckView deckView = new DeckView();
        DeckController deckController = new DeckController(sharedCollection, deckView);

        int action;
        Scanner getAction = new Scanner(System.in);

        CardMenu cardMenu;
        BinderMenu binderMenu;
        DeckMenu deckMenu;

        do {
            System.out.println("-------------------------------");
            System.out.println("Trading Card Inventory System:");
            System.out.println("-------------------------------");

            baseMenu();

            System.out.print("Action: ");
            action = getAction.nextInt();
            getAction.nextLine(); //Clears input buffer
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
                    deckController.addDeck();
                }

                case 4 -> {
                    System.out.println("");
                    cardMenu = new CardMenu(cardController);
                    cardMenu.runMenu();
                    System.out.println("");
                }

                case 5 -> {
                    System.out.println("");
                    binderMenu = new BinderMenu(binderController);
                    binderMenu.runMenu();
                    System.out.println("");
                }
                case 6 -> {
                    System.out.println("");
                    deckMenu = new DeckMenu(deckController);
                    deckMenu.runMenu();
                    System.out.println("");
                }
            }

        } while (action != 0);
        getAction.close();
    }

    /**
     * Displays the base menu options to the user based on current collection
     * state.
     */
    private void baseMenu() {
        System.out.println("[1] Add Card");
        System.out.println("[2] Create new Binder");
        System.out.println("[3] Create a new Deck");
        if (!sharedCollection.getCardCollection().isEmpty()) {
            System.out.println("[4] Manage Cards");
        }
        if (!sharedCollection.getBinderCollection().isEmpty()) {
            System.out.println("[5] Manage Binders");
        }
        if (!sharedCollection.getDeckCollection().isEmpty()) {
            System.out.println("[6] Manage Decks");
        }
        System.out.println("[0] Exit Program");
    }

}
