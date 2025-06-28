package com.tradingcards.elements.deck;

import com.tradingcards.elements.card.CardModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * View class responsible for handling user input and displaying output related
 * to deck operations.
 */
public class DeckView {

    /** Scanner instance used for reading user input from the console. */
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

    /**
     * Prompts the user to input the name of a card in the deck.
     *
     * @return the card name entered by the user
     */
    public String setCardName() {
        System.out.print("Give Card Name in Deck: ");
        return scanner.nextLine();
    }

    /**
     * Prompts the user to input the number of a card in the deck.
     *
     * @return the card number entered by the user
     */
    public int setCardNumber() {
        System.out.print("Give Card Number in Deck: ");
        return scanner.nextInt();
    }

    /**
     * Asks the user whether they want to view a card in the deck.
     *
     * @return the user's response, expected to be "Y" or "N"
     */
    public String viewCardChoice() {
        System.out.print("Would you like to view a Card in the deck? [Y/N]");
        return scanner.nextLine();
    }

    /**
     * Asks the user how they want to select a card from the deck.
     *
     * @return the user's input, expected to be "name" or "number"
     */
    public String cardSelectionOption() {
        System.out.println("Indicate card selection mode");
        System.out.print("Input [name/number]: ");
        return scanner.nextLine();
    }

    /**
     * Displays the names of all available decks in the collection.
     *
     * @param deckCollection the collection of all existing decks
     */
    public void displayDecks(HashMap<String, DeckModel> deckCollection) {
        ArrayList<String> deckKeys = new ArrayList<>(deckCollection.keySet());
        Collections.sort(deckKeys);

        System.out.println("------------------------------------");
        System.out.println("Current Decks:");
        System.out.println("");

        for (String deckNames : deckKeys) {
            System.out.println(deckNames);
        }
        System.out.println("");
    }

    /**
     * Displays the contents of a single deck including card names and their order
     * number.
     *
     * @param deck the map of card names to card objects in the deck
     */
    public void displayDeckContent(HashMap<String, CardModel> deck) {
        ArrayList<String> cardByKey = new ArrayList<>(deck.keySet());
        Collections.sort(cardByKey);
        int counter = 0;
        System.out.println("------------------------------------");
        System.out.println("Deck Contents:");
        System.out.println("");

        for (String name : cardByKey) {
            counter += 1;
            System.out.println("Card Name: " + deck.get(name).getName() + "\n");
            System.out.println("Card Number: " + counter);
        }
        System.out.println("");
    }

    /**
     * Displays a message to the user followed by a newline.
     *
     * @param message the message to display
     */
    public void displayMessageNewLine(String message) {
        System.out.println(message);
    }
}
