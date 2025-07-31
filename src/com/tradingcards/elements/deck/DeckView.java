package com.tradingcards.elements.deck;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.card.cardUtils.ImageUtils;
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

    public JPanel basicPanel(String message) {
        JPanel messagePanel = new JPanel();
        JLabel text = new JLabel(message);
        messagePanel.add(text);
        return messagePanel;
    };

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

    /**
     * Displays the deck creation form to the user and returns a {@link DeckModel}
     * containing the deck's name and type as entered by the user.
     *
     * <p>
     * If the user cancels the form by entering a sentinel value (e.g., "-999")
     * or null at any step, the method returns {@code null} to indicate that deck
     * creation was aborted.
     * </p>
     *
     * @return a {@code DeckModel} with user-inputted name and type, or {@code null}
     *         if the operation was cancelled
     */
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

    public int showYesNoOptions(String message, String title) {
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
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
     * Asks the user how they want to select a card from the deck.
     *
     * @return the user's input, expected to be "name" or "number"
     */
    public int cardSelectionOption() {
        String[] choices = { "Name", "Number" };

        return JOptionPane.showOptionDialog(
                null,
                "Indicate card selection mode",
                "Selection Mode Query",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                choices,
                0);

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

            JButton tempButton = new JButton("<html>" + "Name: " + deckNames + "<br>Type: "
                    + deckCollection.get(deckNames).getType() + "</html>");
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
    public JPanel displayDeckContent(HashMap<String, CardModel> deck) {
        ArrayList<String> cardByKey = new ArrayList<>(deck.keySet());
        Collections.sort(cardByKey);

        JPanel displayPanel = new JPanel(new GridLayout(0, 3, 5, 5));

        int counter = 0;

        for (String name : cardByKey) {
            counter += 1;
            JPanel tempPanel = new JPanel(new BorderLayout());
            tempPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

            JLabel image;

            String imagePath = deck.get(name).getImagePath();
            ImageIcon cardPhoto;

            if (imagePath != null) {
                cardPhoto = new ImageIcon(imagePath);
            } else {
                cardPhoto = new ImageIcon(getClass().getResource("/images/default.png"));
            }

            image = new JLabel(ImageUtils.scaleIcon(cardPhoto, 120, 120));
            image.setHorizontalAlignment(SwingConstants.CENTER);
            tempPanel.add(image, BorderLayout.CENTER);

            JLabel tempLabel = new JLabel("<html>Card Name: " + deck.get(name).getName() + "<br>Value: "
                    + deck.get(name).getValue() + "<br>Card Number: " + counter + "</html>");

            tempPanel.add(tempLabel, BorderLayout.SOUTH);
            tempPanel.setPreferredSize(new Dimension(190, 190));

            displayPanel.setBackground(Color.white);
            displayPanel.add(tempPanel);
        }
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(displayPanel, BorderLayout.NORTH);
        wrapperPanel.setBackground(Color.WHITE);

        return wrapperPanel;
    }
}
