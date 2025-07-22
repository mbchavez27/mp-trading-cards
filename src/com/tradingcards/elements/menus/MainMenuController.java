package com.tradingcards.elements.menus;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.tradingcards.elements.binder.BinderController;
import com.tradingcards.elements.binder.BinderView;
import com.tradingcards.elements.card.CardController;
import com.tradingcards.elements.card.CardView;
import com.tradingcards.elements.collection.CollectionModel;
import com.tradingcards.elements.deck.DeckController;
import com.tradingcards.elements.deck.DeckView;

/**
 * Controller class for handling the main menu of the Trading Card Inventory
 * System. Initializes and coordinates all component controllers and handles
 * user input to navigate the system.
 */
public class MainMenuController {

    /** Shared collection that holds cards, binders, and decks. */
    private CollectionModel sharedCollection = new CollectionModel();

    /**
     * Starts and runs the main menu loop. Handles user input to navigate
     * through different functionalities such as managing cards, binders, and
     * decks.
     */
    public void start() {
        // Initialize Needed MVC
        CardView cardView = new CardView();
        CardController cardController = new CardController(sharedCollection, cardView);

        BinderView binderView = new BinderView();
        BinderController binderController = new BinderController(sharedCollection, binderView);

        DeckView deckView = new DeckView();
        DeckController deckController = new DeckController(sharedCollection, deckView);

        CardMenuController cardMenu = new CardMenuController(new CardMenuView(), cardController);
        BinderMenu binderMenu;
        DeckMenu deckMenu;

        MainMenuView view = new MainMenuView();

        // Show Menu
        view.setVisible(true);

        view.setAddCardAction(e -> {
            view.setVisible(false);
            cardController.addCard();
            view.setVisible(true);
            if (!sharedCollection.getCardCollection().isEmpty()) {
                view.ShowManageCard();
            }

        });

        view.setNewBinderAction(e -> {
            view.setVisible(false);
            binderController.addBinder();
            view.setVisible(true);
            if (!sharedCollection.getBinderCollection().isEmpty()) {
                view.ShowManageBinder();
            }
        });

        view.setManageCardsAction(e -> {
            view.setVisible(false);

            CardMenuView cardMenuView = cardMenu.getView();
            cardMenuView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            cardMenuView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    view.setVisible(true);
                }
            });

            cardMenu.start();
        });

        view.setCloseApplicationButton(e -> System.exit(0));

    }
}
