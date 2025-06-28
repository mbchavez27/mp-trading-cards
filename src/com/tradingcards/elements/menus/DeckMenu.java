package com.tradingcards.elements.menus;

import com.tradingcards.elements.deck.DeckController;
import java.util.Scanner;

/**
 * Menu class responsible for handling user interactions related to deck
 * operations.
 */
public class DeckMenu {

    /** Controller used to manage deck logic and operations. */
    private DeckController deckController;

    /** Static Scanner instance used for capturing user input. */
    public static final Scanner GETACTION = new Scanner(System.in);

    /**
     * Constructs a DeckMenu with the specified DeckController.
     *
     * @param controller the DeckController used to perform deck-related operations
     */
    public DeckMenu(DeckController controller) {
        this.deckController = controller;
    }

    /**
     * Runs the deck management menu loop.
     * Prompts the user for input and performs the corresponding deck actions.
     * Loops until the user chooses to exit by entering 0.
     */
    public void runMenu() {
        int action;

        do {
            manageDecks();
            System.out.print("Action: ");
            action = GETACTION.nextInt();
            GETACTION.nextLine(); // Clears input buffer
            switch (action) {
                case 1:
                    deckController.removeDeck();
                    break;
                case 2:
                    deckController.addCard();
                    break;
                case 3:
                    deckController.removeCard();
                    break;
                case 4:
                    deckController.displaySingleDeck();
                    break;
            }
        } while (action != 0);
    }

    /**
     * Displays the deck management submenu options to the user.
     */
    private void manageDecks() {
        System.out.println("-------------------------------");
        System.out.println("Manage Decks:");
        System.out.println("-------------------------------");
        System.out.println("[1] Delete Deck");
        System.out.println("[2] Add Card to Deck");
        System.out.println("[3] Remove Card from Deck");
        System.out.println("[4] View Deck");
        System.out.println("[0] Exit Menu");
    }

}
