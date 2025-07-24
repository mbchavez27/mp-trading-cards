package com.tradingcards.elements.card;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Provides methods for user interaction related to trading cards, such as
 * creating and viewing card details and collections.
 */
public class CardView {

    /**
     * Scanner object for reading user input from the console.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays a modal form dialog that allows the user to input information for a
     * new card.
     * <p>
     * The form includes fields for:
     * <ul>
     * <li>Card Name (Text)</li>
     * <li>Rarity (Dropdown: Common, Uncommon, Rare, Legendary)</li>
     * <li>Variant (Dropdown: Normal, Extended-art, Full-art, Alt-art)</li>
     * <li>Card Value (Numeric Text)</li>
     * </ul>
     * 
     * <p>
     * If the user selects "OK" and provides valid input:
     * <ul>
     * <li>A new {@code CardModel} object is instantiated and populated with the
     * input values.</li>
     * <li>If the rarity is "Rare" or "Legendary", the card's value is calculated
     * using the
     * {@code calculateValue} method based on the variant and input value.</li>
     * <li>The quantity is set to 1 by default.</li>
     * </ul>
     * 
     * <p>
     * If the form is canceled or invalid input is provided (e.g., empty name/value
     * or invalid number),
     * the method returns {@code null}.
     * 
     * @return a populated {@code CardModel} instance if the user submits valid
     *         input;
     *         {@code null} if the form is canceled or the input is invalid
     */
    public CardModel showAddCardForm() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 200));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField nameField = new JTextField();
        JComboBox<String> rarityBox = new JComboBox<>(new String[] { "Common", "Uncommon", "Rare", "Legendary" });
        JComboBox<String> variantBox = new JComboBox<>(
                new String[] { "Normal", "Extended-art", "Full-art", "Alt-art" });
        JTextField valueField = new JTextField();

        JLabel nameLabel = new JLabel("Card Name:");
        JLabel rarityLabel = new JLabel("Rarity:");
        JLabel variantLabel = new JLabel("Variant:");
        JLabel valueLabel = new JLabel("Card Value:");

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(rarityLabel);
        panel.add(rarityBox);

        panel.add(variantLabel);
        panel.add(variantBox);

        // Initially set these elements to invisible
        variantLabel.setVisible(false);
        variantBox.setVisible(false);

        panel.add(valueLabel);
        panel.add(valueField);

        rarityBox.addActionListener(e -> {
            String selected = (String) rarityBox.getSelectedItem();
            boolean showVariant = selected.equals("Rare") || selected.equals("Legendary");
            variantLabel.setVisible(showVariant);
            variantBox.setVisible(showVariant);
            panel.revalidate();
            panel.repaint();
        });

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Card", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String rarity = (String) rarityBox.getSelectedItem();
            String variant = (String) variantBox.getSelectedItem();
            String valueText = valueField.getText().trim();

            // Cancel on empty name or invalid value
            if (name.isEmpty() || valueText.isEmpty())
                return null;

            try {
                double value = Double.parseDouble(valueText);
                if (value < 0) {
                    JOptionPane.showMessageDialog(null, "Value must be non-negative.");
                    return null;
                }

                CardModel card = new CardModel();
                card.setName(name);
                card.setRarity(rarity);

                if (rarity.equals("Rare") || rarity.equals("Legendary")) {
                    card.setVariant(variant);
                    card.setValue(card.calculateValue(value, variant));
                } else {
                    card.setValue(value);
                }

                card.setQuantity(1);
                return card;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid value entered.");
            }
        }

        return null; // cancelled
    }

    /**
     * Prompts the user to input the name of the card.
     *
     * @return the card name entered by the user
     */
    public String setCardName() {
        return JOptionPane.showInputDialog(null, "Give Card Name (Enter -999 to cancel):");
    }

    /**
     * Prompts the user to select the rarity of the card from a list of options.
     * Loops until the user enters a valid option (1-4).
     *
     * @return the selected rarity as a string (e.g., "Common", "Uncommon",
     *         "Rare", "Legendary")
     */
    public String setCardRarity() {
        String[] options = { "Common", "Uncommon", "Rare", "Legendary" };
        Object input = JOptionPane.showInputDialog(
                null,
                "Select Card Rarity:",
                "Card Rarity",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        return input == null ? "-999" : input.toString();
    }

    /**
     * Prompts the user to select the variant of the card from a list of
     * options. Loops until the user enters a valid option (1-4).
     *
     * @return the selected variant as a string (e.g., "Normal", "Extended-art",
     *         "Full-art", "Alt-art")
     */
    public String setCardVariant() {
        String[] options = { "Normal", "Extended-art", "Full-art", "Alt-art" };
        Object input = JOptionPane.showInputDialog(
                null,
                "Select Card Variant:",
                "Card Variant",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        return input == null ? "-999" : input.toString();

    }

    /**
     * Prompts the user to input the base value of the card.
     *
     * @return the base value entered by the user as a {@code double}
     */
    public double setCardValue() {
        String input = "";
        double value = -1;

        // Keep looping until valid input is given or user cancels
        while (!input.equals("-999")) {
            input = JOptionPane.showInputDialog(null, "Give Card Value (Enter -999 to cancel):");
            if (input == null || input.equals("-999"))
                return -999;

            try {
                value = Double.parseDouble(input);
                return value;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        }

        return -999;
    }

    /**
     * Prompts the user to input the quantity of the card.
     *
     * @return the quantity entered by the user as an {@code int}
     */
    public int setCardQuantity() {
        String input;
        int quantity;

        do {
            input = JOptionPane.showInputDialog(null, "Give Card Value (Enter -999 to cancel):");

            if (input == null || input.equals("-999")) {
                return -999;
            }

            try {
                quantity = Integer.parseInt(input);
                return quantity;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }

        } while (!input.equals("-999"));

        return -999;
    }

    /**
     * Displays detailed information about a specific card from the collection.
     *
     * @param collection the HashMap of card name to CardModel
     * @param cardName   the name of the card to display
     */
    public void displayCard(HashMap<String, CardModel> collection, String cardName) {
        displayMessageNewLine("");
        if (collection.containsKey(cardName)) {
            if (collection.get(cardName).getQuantity() > 0) {
                displayMessageNewLine("------------------------------------");
                displayMessageNewLine("Card Details:");
                displayMessageNewLine("Card Name: " + collection.get(cardName).getName());
                displayMessageNewLine("Card Rarity: " + collection.get(cardName).getRarity());
                if (collection.get(cardName).getVariant() != null) {
                    displayMessageNewLine("Card Variant: " + collection.get(cardName).getVariant());
                }
                displayMessageNewLine("Card Value: " + collection.get(cardName).getValue());
                displayMessageNewLine("Card Quantity: " + collection.get(cardName).getQuantity());
                displayMessageNewLine("------------------------------------");
            } else if (collection.get(cardName).getQuantity() == 0) {
                displayMessageNewLine("-------------------------------");
                displayMessageNewLine(String.format("Card %s is empty", cardName));
                displayMessageNewLine("-------------------------------");
            }
        } else {
            displayMessageNewLine("-------------------------------");
            displayMessageNewLine(String.format("Card %s does not exist", cardName));
            displayMessageNewLine("-------------------------------");
        }
    }

    /**
     * Displays card details (used for decks), omitting quantity.
     *
     * @param collection the collection containing the card
     * @param cardName   the name of the card to display
     * @param mode       an unused parameter (used to overload method signature)
     */
    public void displayCard(HashMap<String, CardModel> collection, String cardName, int mode) {
        displayMessageNewLine("");
        displayMessageNewLine("------------------------------------");
        displayMessageNewLine("Card Details:");
        displayMessageNewLine("Card Name: " + collection.get(cardName).getName());
        displayMessageNewLine("Card Rarity: " + collection.get(cardName).getRarity());
        if (collection.get(cardName).getVariant() != null) {
            displayMessageNewLine("Card Variant: " + collection.get(cardName).getVariant());
        }
        displayMessageNewLine("Card Value: " + collection.get(cardName).getValue());
        displayMessageNewLine("------------------------------------");
    }

    /**
     * Displays all cards in the collection with their quantities, regardless of
     * value.
     *
     * @param collection the card collection to display
     * @param mode       display mode; mode 0 displays all including zeroes
     */
    public void displayCollection(HashMap<String, CardModel> collection, int mode) {
        ArrayList<String> cardByKey = new ArrayList<>(collection.keySet());
        Collections.sort(cardByKey);
        displayMessageNewLine("------------------------------------");
        displayMessageNewLine("Collection:");
        displayMessageNewLine("");

        for (String name : cardByKey) {
            if (mode == 0) {
                displayMessageNewLine("Card Name: " + collection.get(name).getName());
                displayMessageNewLine("Card Quantity: " + collection.get(name).getQuantity());
                displayMessageNewLine("");
            }
        }
    }

    /**
     * Displays all cards in the collection that have a quantity greater than zero.
     *
     * @param collection the card collection to display
     */
    public JPanel displayCollection(HashMap<String, CardModel> collection) {
        ArrayList<String> cardByKey = new ArrayList<>(collection.keySet());
        Collections.sort(cardByKey);
        // displayMessageNewLine("------------------------------------");
        // displayMessageNewLine("Collection:");
        // displayMessageNewLine("");

        JPanel displayPanel = new JPanel(new GridLayout(0, 3));

        for (String name : cardByKey) {
            if (collection.get(name).getQuantity() > 0) {
                System.out.println("Hello");
                displayPanel.add(new JButton(
                        collection.get(name).getName() + " quantity: " + collection.get(name).getQuantity()));
                // displayPanel.add(new Label(collection.get(name).getName()));
                // displayMessageNewLine("Card Name: " + collection.get(name).getName());
                // displayMessageNewLine("Card Quantity: " +
                // collection.get(name).getQuantity());
                // displayMessageNewLine("");
            }
        }

        return displayPanel;
    }

    /**
     * Prompts the user to confirm whether to increase the card quantity.
     *
     * @param name the name of the card in question
     * @return true if user selects Y, false if N
     */
    public boolean allowIncreaseCardCount(String name) {
        int result = JOptionPane.showConfirmDialog(
                null,
                "Do you want to add another copy for Card: " + name + "?",
                "Confirm",
                JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    /**
     * Displays a message on a new line.
     *
     * @param message the message to be printed
     */
    public void displayMessageNewLine(String message) {
        System.out.println(message);
    }

    public void showWarning(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.WARNING_MESSAGE);
    }

    public void showInfo(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
