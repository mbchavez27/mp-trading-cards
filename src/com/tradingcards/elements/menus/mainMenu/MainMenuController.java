package com.tradingcards.elements.menus.mainMenu;

import com.tradingcards.MainFrame;
import com.tradingcards.elements.binder.BinderController;
import com.tradingcards.elements.binder.BinderView;
import com.tradingcards.elements.card.CardController;
import com.tradingcards.elements.card.CardView;
import com.tradingcards.elements.collection.CollectionModel;
import com.tradingcards.elements.deck.DeckController;
import com.tradingcards.elements.deck.DeckView;
import com.tradingcards.elements.menus.binderMenu.BinderMenuController;
import com.tradingcards.elements.menus.binderMenu.BinderMenuView;
import com.tradingcards.elements.menus.cardMenu.CardMenuController;
import com.tradingcards.elements.menus.cardMenu.CardMenuView;
import com.tradingcards.elements.menus.deckMenu.DeckMenuController;
import com.tradingcards.elements.menus.deckMenu.DeckMenuView;

/**
 * Controller responsible for managing the main menu interface and initializing
 * other submenus (Card, Binder, and Deck menus). Acts as the entry point to the
 * application UI.
 */
public class MainMenuController {

    /** Shared data model used across all modules (cards, binders, decks). */
    private final CollectionModel sharedCollection = new CollectionModel();

    /**
     * Initializes the entire GUI application. Sets up views, controllers, event
     * listeners,
     * and displays the main menu.
     */
    public void start() {
        MainFrame mainFrame = new MainFrame();
        MainMenuView mainMenuView = new MainMenuView(sharedCollection);

        CardMenuView cardMenuView = new CardMenuView();
        BinderMenuView binderMenuView = new BinderMenuView();
        DeckMenuView deckMenuView = new DeckMenuView();

        // Set up MVC components for Card menu
        CardView cardView = new CardView();
        CardController cardController = new CardController(sharedCollection, cardView);
        CardMenuController cardMenuController = new CardMenuController(
                cardMenuView, cardController, cardView, mainMenuView, mainFrame);

        // Set up MVC components for Binder menu
        BinderView binderView = new BinderView();
        BinderController binderController = new BinderController(sharedCollection, binderView);
        BinderMenuController binderMenuController = new BinderMenuController(
                binderMenuView, binderController, binderView, mainMenuView, mainFrame);

        // Set up MVC components for Deck menu
        DeckView deckView = new DeckView();
        DeckController deckController = new DeckController(sharedCollection, deckView);
        DeckMenuController deckMenuController = new DeckMenuController(
                deckMenuView, deckController, deckView, mainMenuView, mainFrame);

        // Register all possible menu panels with the main frame
        mainFrame.addPanel("mainMenu", mainMenuView);
        mainFrame.addPanel("manageCardMenu", cardMenuView);
        mainFrame.addPanel("manageBinderMenu", binderMenuView);
        mainFrame.addPanel("manageDeckMenu", deckMenuView);

        // Show main menu by default
        mainFrame.setVisible(true);
        mainFrame.showPanel("mainMenu");

        // Handle "Add Card" button logic
        mainMenuView.setAddCardAction(e -> {
            boolean[] temp = new boolean[1]; // Used by addCard logic
            cardController.addCard(temp);

            if (!sharedCollection.getCardCollection().isEmpty()) {
                mainMenuView.showManageCardBtn(); // Enable manage card button if cards exist
            }
        });

        // Handle "Add Binder" button logic
        mainMenuView.setNewBinderAction(e -> {
            binderController.addBinder();

            if (!sharedCollection.getBinderCollection().isEmpty()) {
                mainMenuView.showManageBinderBtn(); // Enable manage binder button if binders exist
            }
        });

        // Handle "Add Deck" button logic
        mainMenuView.setNewDeckAction(e -> {
            deckController.addDeck();

            if (!sharedCollection.getDeckCollection().isEmpty()) {
                mainMenuView.showManageDeckBtn(); // Enable manage deck button if decks exist
            }
        });

        // Navigate to Manage Cards screen
        mainMenuView.setManageCardsAction(e -> {
            cardMenuController.start();
            mainFrame.showPanel("manageCardMenu");
        });

        // Navigate to Manage Binders screen
        mainMenuView.setManageBindersAction(e -> {
            binderMenuController.start();
            mainFrame.showPanel("manageBinderMenu");
        });

        // Navigate to Manage Decks screen
        mainMenuView.setManageDecksAction(e -> {
            deckMenuController.start();
            mainFrame.showPanel("manageDeckMenu");
        });

        // Handle exit button
        mainMenuView.setCloseApplicationButton(e -> System.exit(0));
    }
}
