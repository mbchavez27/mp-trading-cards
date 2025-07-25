package com.tradingcards.elements.deck;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;

import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.menus.menuUtils.DialogUtil;

/**
 * View class responsible for handling user input and displaying output related
 * to deck operations.
 */
public class DeckView {

    /**
     * Scanner object for reading user input from the console.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to input the name of the deck.
     *
     * @return the deck name entered by the user
     */
    public String setDeckName() {
        return JOptionPane.showInputDialog(null, "Give Deck Name (Enter -999 to cancel): ");
    }

    /**
     * Displays a dialog for the user to select a deck type from a predefined list.
     * <p>
     * The method uses a {@link JOptionPane} input dialog with the following
     * options:
     * <ul>
     * <li>Normal Deck</li>
     * <li>Sellable Deck</li>
     * </ul>
     * The first option ("Normal Deck") is selected by default.
     * </p>
     *
     * @return the selected deck type as a {@code String}, or {@code null} if the
     *         user
     *         cancels the dialog or closes it without making a selection.
     */
    public String setDeckType() {
        String[] deckTypes = { "Normal Deck", "Sellable Deck" };

        String selectedType = (String) JOptionPane.showInputDialog(
                null,
                "Select Deck Type:",
                "Deck Type Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                deckTypes,
                deckTypes[0]);

        if (selectedType == null) {
            return null;
        }

        return selectedType;
    }

    public DeckModel showDeckForm() {
        DeckModel newDeck = new DeckModel();
        String name = setDeckName();
        if (name == null || name.equals("-999"))
            return null;

        String type = setDeckType();
        if (type == null)
            return null;

        newDeck.setName(name);
        newDeck.setType(type);

        return newDeck;
    }

    /**
     * Prompts the user to input the name of a card in the deck.
     *
     * @return the card name entered by the user
     */
    public String setCardName() {
        return JOptionPane.showInputDialog(null, "Give Card Name (Enter -999 to cancel): ");
    }

    /**
     * Prompts the user to input the number of a card in the deck.
     *
     * @return the card number entered by the user
     */
    public int setCardNumber() {
        String input;
        int number;
        do {
            input = JOptionPane.showInputDialog(null, "Give Card Number in Deck:");

            if (input == null || input.equals("-999")) {
                return -999;
            }

            try {
                number = Integer.parseInt(input);
                return number;
            } catch (NumberFormatException e) {
                DialogUtil.showError(null, "Please Enter a valid Number", "Error");
            }
        } while (!input.equals("-999"));

        return -999;
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
    public JPanel displayDecks(HashMap<String, DeckModel> deckCollection) {
        ArrayList<String> deckKeys = new ArrayList<>(deckCollection.keySet());
        Collections.sort(deckKeys);

        JPanel displayPanel = new JPanel(new GridLayout(0, 3, 0, 5));


        for (String deckNames : deckKeys) {

            JPanel wrapper = new JPanel();

            JButton tempButton = new JButton(deckNames);
            tempButton.setPreferredSize(new Dimension(190, 190));
            wrapper.setPreferredSize(new Dimension(200, 200));
            wrapper.add(tempButton);

            displayPanel.add(wrapper);
        }
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(displayPanel, BorderLayout.NORTH);
        wrapperPanel.setBackground(Color.WHITE);

        return wrapperPanel;
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
