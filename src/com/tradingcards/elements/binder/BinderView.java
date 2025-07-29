package com.tradingcards.elements.binder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.tradingcards.elements.binder.types.CollectorBinder;
import com.tradingcards.elements.binder.types.LuxuryBinder;
import com.tradingcards.elements.binder.types.NonCuratedBinder;
import com.tradingcards.elements.binder.types.PauperBinder;
import com.tradingcards.elements.binder.types.RaresBinder;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.card.CardView;

/**
 * The {@code BinderView} class handles user interaction related to binders,
 * such as prompting the user to input a binder name.
 */
public class BinderView {

    /**
     * Scanner object for reading user input from the console.
     */
    private Scanner scanner = new Scanner(System.in);

    private final JButton buttonConfirm = new JButton("Confirm Trade");
    private final JButton buttonDecline = new JButton("Decline Trade");

    /**
     * Prompts the user to input a name for a binder and returns it.
     *
     * @return the name entered by the user
     */
    public String setBinderName() {
        return JOptionPane.showInputDialog(null, "Give Binder Name (Enter -999 to cancel):");
    }

    public String setBinderName(String message) {
        return JOptionPane.showInputDialog(null, message + " (Enter -999 to cancel):");
    }

    public void showWarning(String warning) {
        JOptionPane.showMessageDialog(null, warning, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public JPanel basicPanel(String message) {
        JPanel messagePanel = new JPanel();
        JLabel text = new JLabel(message);
        messagePanel.add(text);

        return messagePanel;
    }

    public JButton getButtonConfirm() {
        return buttonConfirm;
    }

    public JButton getButtonDecline() {
        return buttonDecline;
    }

    public String setCardName() {
        return JOptionPane.showInputDialog(null, "Give Card Name (Enter -999 to cancel):");
    }

    public Double setBinderPrice() {
        String input = JOptionPane.showInputDialog(null,
                "Give New Card Price (Optional, and not must be lower than original )");

        if (input == null || input.trim().isEmpty()) {
            return null; // User cancelled or entered nothing
        }

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            return null;
        }
    }

    public JPanel showTradeConfirmation() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(panel1, gbc);

        gbc.gridx = 1;
        buttonConfirm.setBackground(Color.GREEN);
        panel2.add(buttonConfirm);
        mainPanel.add(panel2, gbc);

        gbc.gridx = 2;
        buttonDecline.setBackground(Color.RED);
        panel3.add(buttonDecline);
        mainPanel.add(panel3, gbc);

        gbc.gridx = 3;
        mainPanel.add(panel4, gbc);

        return mainPanel;
    }

    public JPanel showMainCardDisplay(HashMap<String, CardModel> collection, String outgoingCard, String incomingCard,
            double difference) {
        CardView cardView = new CardView();

        JPanel tradeDisplay = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel card1Panel = cardView.displayCardForBinderAndDeck(collection, outgoingCard);
        JPanel card2Panel = cardView.displayCardForBinderAndDeck(collection, incomingCard);
        JLabel differenceLabel = new JLabel("Difference: " + difference, SwingConstants.CENTER);
        differenceLabel.setFont(new Font("Inter", Font.BOLD, 16));

        // Left card
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(card1Panel, gbc);

        // Difference in middle
        gbc.gridx = 1;
        centerPanel.add(differenceLabel, gbc);

        // Right card
        gbc.gridx = 2;
        centerPanel.add(card2Panel, gbc);

        // Add to main panel
        tradeDisplay.add(centerPanel, BorderLayout.CENTER);

        return tradeDisplay;
    }

    /**
     * Displays a dialog for the user to select a binder type from a predefined
     * list.
     * <p>
     * The method shows a {@link JOptionPane} input dialog with the following
     * options:
     * <ul>
     *
     * <li>Non-curated Binder</li>
     * <li>Sellable: Pauper Binder</li>
     * <li>Sellable: Rares Binder</li>
     * <li>Sellable: Luxury Binder</li>
     * <li>Collector Binder</li>
     * </ul>
     * The first item ("Non-curated Binder") is selected by default.
     * </p>
     *
     * @return the selected binder type as a {@code String}, or {@code null} if the
     *         user
     *         cancels the dialog or closes it without making a selection.
     */
    public String setBinderType() {
        String[] binderTypes = { "Non-curated Binder", "Sellable: Pauper Binder", "Sellable: Rares Binder",
                "Sellable: Luxury Binder", "Collector Binder" };

        String selectedType = (String) JOptionPane.showInputDialog(
                null,
                "Select Binder Type:",
                "Binder Type Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                binderTypes,
                binderTypes[0]);

        if (selectedType == null) {
            return null;
        }

        return selectedType;
    }

    /**
     * Displays a form sequence to create a new {@link BinderModel} instance.
     * <p>
     * This method prompts the user to enter a binder name and select a binder type.
     * If the user cancels any of the dialogs or returns a special exit code (e.g.,
     * "-999"),
     * the method returns {@code null}.
     * </p>
     * <p>
     * Based on the selected type, it creates an instance of one of the following:
     * <ul>
     * <li>{@link NonCuratedBinder}</li>
     * <li>{@link PauperBinder}</li>
     * <li>{@link RaresBinder}</li>
     * <li>{@link LuxuryBinder}</li>
     * <li>{@link CollectorBinder}</li>
     * </ul>
     * The display name used in the constructor is a shortened, internal-friendly
     * version of the binder type.
     * </p>
     *
     * @return a new {@link BinderModel} instance with the selected name and type,
     *         or {@code null} if the user cancels any part of the input process or
     *         if the type is unrecognized.
     */
    public BinderModel showBinderForm() {
        String name = setBinderName();
        if (name == null || name.equals("-999"))
            return null;

        String type = setBinderType();
        if (type == null || type.equals("-999"))
            return null;

        BinderModel newBinder = switch (type) {
            case "Non-curated Binder" -> new NonCuratedBinder("Non-Curated");
            case "Sellable: Pauper Binder" -> new PauperBinder("Pauper");
            case "Sellable: Rares Binder" -> new RaresBinder("Rares");
            case "Sellable: Luxury Binder" -> new LuxuryBinder("Luxury");
            case "Collector Binder" -> new CollectorBinder("Collector");
            default -> null;
        };

        if (newBinder == null)
            return null;

        newBinder.setName(name);
        return newBinder;
    }

    /**
     * Displays a message followed by a newline.
     *
     * @param message the message to display
     */
    public void displayMessageNewLine(String message) {
        System.out.println(message);
    }

    /**
     * Displays a prompt message and retrieves user input.
     *
     * @param message the message to prompt the user
     * @return the user's input as a string
     */
    public String getBinderChoice(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    /**
     * Displays the list of all binder names in the binder collection.
     *
     * @param binderCollection a map containing binder names and their models
     */
    public JPanel displayBinders(HashMap<String, BinderModel> binderCollection) {
        ArrayList<String> binderKeys = new ArrayList<>(binderCollection.keySet());
        Collections.sort(binderKeys);

        JPanel displayPanel = new JPanel(new GridLayout(0, 3, 0, 5));

        //
        // System.out.println("------------------------------------");
        // System.out.println("Current Binders:");
        // System.out.println("");

        for (String binderNames : binderKeys) {

            JPanel wrapper = new JPanel();

            JButton tempButton = new JButton(binderNames);
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
     * Displays the contents of a binder, including each card's name.
     * If a card has more than one copy, it is listed multiple times.
     *
     * @param binder a map containing card names and their models
     */
    public JPanel displayBinderContent(HashMap<String, CardModel> binder) {
        ArrayList<String> cardByKey = new ArrayList<>(binder.keySet());
        Collections.sort(cardByKey);

        JPanel displayPanel = new JPanel(new GridLayout(0, 3, 0, 5));
        boolean hasCards = false;

        for (String name : cardByKey) {
            if (binder.get(name).getQuantity() >= 1) {
                hasCards = true;

                for (int i = 1; i <= binder.get(name).getQuantity(); i++) {
                    JPanel wrapper = new JPanel();
                    JButton tempButton = new JButton(binder.get(name).getName());
                    tempButton.setPreferredSize(new Dimension(190, 190));
                    wrapper.setPreferredSize(new Dimension(200, 200));
                    wrapper.add(tempButton);
                    displayPanel.add(wrapper);
                }
            }
        }

        if (hasCards) {
            JPanel wrapperPanel = new JPanel(new BorderLayout());
            wrapperPanel.add(displayPanel, BorderLayout.NORTH);
            wrapperPanel.setBackground(Color.WHITE);
            return wrapperPanel;
        } else {
            JPanel emptyPanel = new JPanel(new BorderLayout());
            JLabel emptyMessage = new JLabel("Binder is empty");
            emptyMessage.setFont(new Font("inter", Font.PLAIN, 20));
            emptyPanel.add(emptyMessage, BorderLayout.CENTER);
            return emptyPanel;
        }
    }
}
