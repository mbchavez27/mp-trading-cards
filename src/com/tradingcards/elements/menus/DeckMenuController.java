package com.tradingcards.elements.menus;


import com.tradingcards.elements.deck.DeckController;

import java.util.Scanner;

public class DeckMenuController {

    private DeckController deckController;
    public static final Scanner getAction = new Scanner(System.in);
    public DeckMenuController(DeckController controller){
        this.deckController = controller;
    }

    public void runMenu(){
        int action;

        do{
            ManageDecks();
            System.out.print("Action: ");
            action = getAction.nextInt();
            getAction.nextLine(); // Clears input buffer
            switch(action){
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
     * Displays the deck management submenu options.
     */

    private void ManageDecks() {
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

