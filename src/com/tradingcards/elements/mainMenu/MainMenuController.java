package com.tradingcards.elements.mainMenu;

import com.tradingcards.elements.binder.BinderController;
import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.binder.BinderView;
import com.tradingcards.elements.card.CardController;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.card.CardView;
import com.tradingcards.elements.collection.CollectionModel;
import com.tradingcards.elements.deck.DeckController;
import com.tradingcards.elements.deck.DeckModel;
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
        CardModel cardModel = new CardModel();
        CardView cardView = new CardView();
        CardController cardController = new CardController(sharedCollection, cardModel, cardView);

        BinderModel binderModel = new BinderModel();
        BinderView binderView = new BinderView();
        BinderController binderController = new BinderController(sharedCollection, binderModel, binderView);

        DeckModel deckModel = new DeckModel();
        DeckView deckView = new DeckView();
        DeckController deckController = new DeckController(sharedCollection, deckModel, deckView);

        int action = 0;
        Scanner getAction = new Scanner(System.in);

        do {
            System.out.println("-------------------------------");
            System.out.println("Trading Card Inventory System:");
            System.out.println("-------------------------------");

            baseMenu();

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
                    deckController.addDeck();
                }

                case 4 -> {
                    System.out.println("");
                    ManageCards();
                    System.out.println("");
                }

                case 5 -> {
                    System.out.println("");
                    ManageBinders();
                    System.out.println("");
                }
                case 6 -> {
                    System.out.println("");
                    ManageDecks();
                    System.out.println("");
                }
                case 10 -> {
                    System.out.println("");
                    binderController.removeBinder();
                    System.out.println("");
                }
                case 14 -> {
                    System.out.println("");
                    deckController.removeDeck();
                    System.out.println("");
                }

            }

        } while (action != 17);
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
        System.out.println("[17] Exit");
    }

    /**
     * Displays the card management submenu options.
     */
    private void ManageCards() {
        System.out.println("-------------------------------");
        System.out.println("Manage Cards:");
        System.out.println("-------------------------------");
        System.out.println("[7] Display Card");
        System.out.println("[8] Display Collection");
        System.out.println("[9] Increase/decrease card counts");
    }

    /**
     * Displays the binder management submenu options.
     */
    private void ManageBinders() {
        System.out.println("-------------------------------");
        System.out.println("Manage Binders:");
        System.out.println("-------------------------------");
        System.out.println("[10] Delete Binder");
        System.out.println("[11] Add/Remove Card to/from Binder");
        System.out.println("[12] Trade Card");
        System.out.println("[13] View Binder");
    }

    /**
     * Displays the deck management submenu options.
     */
    private void ManageDecks() {
        System.out.println("-------------------------------");
        System.out.println("Manage Decks:");
        System.out.println("-------------------------------");
        System.out.println("[14] Delete Deck");
        System.out.println("[15] Add/Remove Card to/from Deck");
        System.out.println("[16] View");

    }
}
