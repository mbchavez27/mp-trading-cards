package com.tradingcards.elements.deck;

import java.util.Scanner;

/**
 * View class responsible for handling user input related to deck operations.
 */
public class DeckView {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to input the name of the deck.
     *
     * @return the deck name entered by the user
     */
    public String setDeckName() {
        System.out.print("Give Deck Name: ");
        return scanner.nextLine();
    }
}
