package com.tradingcards.elements.menus.deckMenu;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import com.tradingcards.MainFrame;
import com.tradingcards.elements.deck.DeckController;
import com.tradingcards.elements.deck.DeckView;
import com.tradingcards.elements.menus.mainMenu.MainMenuView;

/**
 * Controller class responsible for managing interactions within the deck menu
 * UI.
 * It handles user actions such as adding/removing cards from decks, deleting or
 * viewing decks, and selling decks. The class coordinates between the DeckView,
 * DeckController, and the main application frame.
 */
public class DeckMenuController {

    /** Handles core logic and operations related to decks. */
    private final DeckController deckController;

    /** The view responsible for displaying deck-related user interface elements. */
    private final DeckView deckView;

    /** The view component of the deck menu interface. */
    private final DeckMenuView view;

    /** Reference to the main application frame for switching views. */
    private final MainFrame mainFrame;

    /** The main menu view to return to after deck operations. */
    private final MainMenuView mainMenuView;

    /**
     * Constructs a DeckMenuController to manage user interactions in the deck menu.
     *
     * @param view           the DeckMenuView UI component
     * @param deckController the controller managing deck logic
     * @param deckView       the view for individual deck input/output
     * @param mainMenuView   the main menu UI view
     * @param mainFrame      the main frame for swapping application views
     */
    public DeckMenuController(DeckMenuView view, DeckController deckController, DeckView deckView,
            MainMenuView mainMenuView, MainFrame mainFrame) {
        this.deckController = deckController;
        this.deckView = deckView;
        this.view = view;
        this.mainMenuView = mainMenuView;
        this.mainFrame = mainFrame;
    }

    /**
     * Initializes the deck menu interface, sets up button listeners, and updates
     * the panel with initial deck data.
     */
    public void start() {
        view.setDataInPanel(deckController.displayDecks());
        JPanel displayPanel = new JPanel();

        view.setBackBtn(e -> {
            mainMenuView.updateMoneyLabel();
            mainMenuView.updateButtonStatus();
            mainFrame.showPanel("mainMenu");
        });

        view.setDeleteDeckBtn(e -> {
            displayPanel.setLayout(new BorderLayout());
            view.setDataInPanel(displayPanel);
            deckController.removeDeck(displayPanel);
        });

        view.setAddCardToDeckBtn(e -> {
            displayPanel.setLayout(new BorderLayout());
            view.setDataInPanel(displayPanel);
            deckController.addCard(displayPanel);
        });

        view.setRemoveCardFromDeckBtn(e -> {
            displayPanel.setLayout(new BorderLayout());
            view.setDataInPanel(displayPanel);
            deckController.removeCard(displayPanel);
        });

        view.setSellDeckBtn(e -> {
            String name = deckView.setDeckName();
            deckController.sellDeck(name);
        });

        view.setViewDeckBtn(e -> {
            displayPanel.setLayout(new BorderLayout());
            view.setDataInPanel(displayPanel);
            deckController.displaySingleDeck(displayPanel);
        });
    }

    /**
     * Returns the current deck menu view instance.
     *
     * @return the {@link DeckMenuView} being managed by this controller
     */
    public DeckMenuView getView() {
        return view;
    }
}
