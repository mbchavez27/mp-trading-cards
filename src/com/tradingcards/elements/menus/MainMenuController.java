package com.tradingcards.elements.menus;

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
        CardView cardView = new CardView();
        CardController cardController = new CardController(sharedCollection, cardView);

        BinderView binderView = new BinderView();
        BinderController binderController = new BinderController(sharedCollection, binderView);

        DeckView deckView = new DeckView();
        DeckController deckController = new DeckController(sharedCollection, deckView);

        CardMenu cardMenu;
        BinderMenu binderMenu;
        DeckMenu deckMenu;

        MainMenuView view = new MainMenuView();

        view.setVisible(true);
    }
}
