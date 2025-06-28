package com.tradingcards.elements.menus;

import com.tradingcards.elements.card.CardController;

import java.util.Scanner;

/**
 * Menu class responsible for handling user interactions related to card
 * operations.
 */
public class CardMenu {

    /** Controller that manages logic for card-related functionality. */
    private CardController cardController;

    /** Static Scanner instance used for user input. */
    public static final Scanner GETACTION = new Scanner(System.in);

    /**
     * Constructs a CardMenu with the specified CardController.
     *
     * @param controller the CardController used to perform card operations
     */
    public CardMenu(CardController controller) {
        this.cardController = controller;
    }

    /**
     * Runs the card management menu loop, prompting the user for actions and
     * executing the corresponding controller methods until the user exits.
     */
    public void runMenu() {
        int action;

        do {
            manageCards();
            System.out.print("Action: ");
            action = GETACTION.nextInt();
            GETACTION.nextLine(); // Clears input buffer
            switch (action) {
                case 1:
                    cardController.displayCard();
                    break;
                case 2:
                    cardController.displayCollection();
                    break;
                case 3:
                    cardController.modifyCardQuantity();
                    break;
            }

        } while (action != 0);
    }

    /**
     * Displays the card management submenu options to the user.
     */
    private void manageCards() {
        System.out.println("-------------------------------");
        System.out.println("Manage Cards:");
        System.out.println("-------------------------------");
        System.out.println("[1] Display Card");
        System.out.println("[2] Display Collection");
        System.out.println("[3] Increase/decrease card counts");
        System.out.println("[0] Exit Menu");
    }
}
