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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.tradingcards.elements.binder.types.CollectorBinder;
import com.tradingcards.elements.binder.types.LuxuryBinder;
import com.tradingcards.elements.binder.types.NonCuratedBinder;
import com.tradingcards.elements.binder.types.PauperBinder;
import com.tradingcards.elements.binder.types.RaresBinder;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.card.CardView;
import com.tradingcards.elements.card.cardUtils.ImageUtils;

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
     * Prompts the user to enter a binder name with a custom message.
     * @return the entered binder name, or {@code null} if cancelled
     */
    public String setBinderName() {
        return JOptionPane.showInputDialog(null, "Give Binder Name (Enter -999 to cancel):");
    }

    /**
     * Prompts the user to enter a card name.
     *
     * @return the entered card name, or {@code null} if cancelled
     */
    public String setBinderName(String message) {
        return JOptionPane.showInputDialog(null, message + " (Enter -999 to cancel):");
    }

    /**
     * Returns a simple panel containing a text message.
     *
     * @param message the message to display
     * @return a {@code JPanel} with the message
     */
    public JPanel basicPanel(String message) {
        JPanel messagePanel = new JPanel();
        JLabel text = new JLabel(message);
        messagePanel.add(text);

        return messagePanel;
    }

    /**
     * Returns the Confirm Trade button for external use.
     *
     * @return the confirm button
     */
    public JButton getButtonConfirm() {
        return buttonConfirm;
    }

    /**
     * Returns the Decline Trade button for external use.
     *
     * @return the decline button
     */
    public JButton getButtonDecline() {
        return buttonDecline;
    }

    /**
     * Prompts the user to enter a card name.
     *
     * @return the entered card name, or {@code null} if cancelled
     */
    public String setCardName() {
        return JOptionPane.showInputDialog(null, "Give Card Name (Enter -999 to cancel):");
    }

    /**
     * Prompts the user to set a custom binder price.
     *
     * @return the price as a {@code Double}, or {@code null} if input is invalid or
     *         cancelled
     */
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

    /**
     * Creates and returns a panel that shows Confirm and Decline buttons for trade
     * approval.
     *
     * @return a panel containing trade confirmation buttons
     */
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

    /**
     * Displays a side-by-side panel of two cards involved in a trade, along with
     * the value difference.
     *
     * @param collection   the card collection map
     * @param outgoingCard the name of the card to be traded out
     * @param incomingCard the name of the card to be added
     * @param difference   the value difference between the cards
     * @return a panel showing the two cards and the difference
     */
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

            JButton tempButton = new JButton("<html>" + "Name: " + binderNames + "<br>Type: "
                    + binderCollection.get(binderNames).getType() + "</html>");
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

        JPanel displayPanel = new JPanel(new GridLayout(0, 3, 5, 5));
        boolean hasCards = false;

        for (String name : cardByKey) {
            if (binder.get(name).getQuantity() >= 1) {

                hasCards = true;

                for (int i = 1; i <= binder.get(name).getQuantity(); i++) {
                    JPanel tempPanel = new JPanel(new BorderLayout());
                    tempPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

                    JLabel image;

                    String imagePath = binder.get(name).getImagePath();
                    ImageIcon cardPhoto;

                    if (imagePath != null) {
                        cardPhoto = new ImageIcon(imagePath);
                    } else {
                        cardPhoto = new ImageIcon(getClass().getResource("/images/default.png"));
                    }

                    image = new JLabel(ImageUtils.scaleIcon(cardPhoto, 120, 120));
                    image.setHorizontalAlignment(SwingConstants.CENTER);

                    tempPanel.add(image, BorderLayout.CENTER);

                    JLabel tempLabel = new JLabel("<html>Card Name: " + binder.get(name).getName() + "<br>Value: "
                            + binder.get(name).getValue() + "</html>");
                    tempPanel.add(tempLabel, BorderLayout.SOUTH);
                    tempPanel.setPreferredSize(new Dimension(190, 190));

                    displayPanel.setBackground(Color.white);
                    displayPanel.add(tempPanel);

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
