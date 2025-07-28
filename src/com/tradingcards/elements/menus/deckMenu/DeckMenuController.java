package com.tradingcards.elements.menus.deckMenu;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.tradingcards.MainFrame;
import com.tradingcards.elements.deck.DeckController;
import com.tradingcards.elements.deck.DeckView;
import com.tradingcards.elements.menus.mainMenu.MainMenuView;

/**
 * Menu class responsible for handling user interactions related to deck
 * operations.
 */
public class DeckMenuController {

    private final DeckController deckController;
    private final DeckView deckView;
    private final DeckMenuView view;
    private final MainFrame mainFrame;
    private final MainMenuView mainMenuView;

    public DeckMenuController(DeckMenuView view, DeckController deckController, DeckView deckView,
            MainMenuView mainMenuView, MainFrame mainFrame) {
        this.deckController = deckController;
        this.deckView = deckView;
        this.view = view;
        this.mainMenuView = mainMenuView;
        this.mainFrame = mainFrame;
    }

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

    public DeckMenuView getView() {
        return view;
    }

    // /** Controller used to manage deck logic and operations. */
    // private DeckController deckController;
    //
    // /** Static Scanner instance used for capturing user input. */
    // public static final Scanner GETACTION = new Scanner(System.in);
    //
    // /**
    // * Constructs a DeckMenu with the specified DeckController.
    // *
    // * @param controller the DeckController used to perform deck-related
    // operations
    // */
    // public DeckMenuController(DeckController controller) {
    // this.deckController = controller;
    // }
    //
    // /**
    // * Runs the deck management menu loop.
    // * Prompts the user for input and performs the corresponding deck actions.
    // * Loops until the user chooses to exit by entering 0.
    // */
    // public void runMenu() {
    // int action;
    //
    // do {
    // manageDecks();
    // System.out.print("Action: ");
    // action = GETACTION.nextInt();
    // GETACTION.nextLine(); // Clears input buffer
    // switch (action) {
    // case 1:
    // deckController.removeDeck();
    // break;
    // case 2:
    // deckController.addCard();
    // break;
    // case 3:
    // deckController.removeCard();
    // break;
    // case 4:
    // deckController.displaySingleDeck();
    // break;
    // }
    // } while (action != 0);
    // }
    //
    // /**
    // * Displays the deck management submenu options to the user.
    // */
    // private void manageDecks() {
    // System.out.println("-------------------------------");
    // System.out.println("Manage Decks:");
    // System.out.println("-------------------------------");
    // System.out.println("[1] Delete Deck");
    // System.out.println("[2] Add Card to Deck");
    // System.out.println("[3] Remove Card from Deck");
    // System.out.println("[4] View Deck");
    // System.out.println("[0] Exit Menu");
    // }

}
