package com.tradingcards.elements.card;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.tradingcards.elements.card.cardUtils.ImageUtils;
import com.tradingcards.elements.menus.menuUtils.DialogUtil;

/**
 * Provides methods for user interaction related to trading cards, such as
 * creating and viewing card details and collections.
 */
public class CardView {

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
        panel.setPreferredSize(new Dimension(300, 350));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Components
        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(250, 30));

        JComboBox<String> rarityBox = new JComboBox<>(new String[] { "Common", "Uncommon", "Rare", "Legendary" });
        rarityBox.setMaximumSize(new Dimension(250, 30));

        JComboBox<String> variantBox = new JComboBox<>(
                new String[] { "Normal", "Extended-art", "Full-art", "Alt-art" });
        variantBox.setMaximumSize(new Dimension(250, 30));

        JTextField valueField = new JTextField();
        valueField.setMaximumSize(new Dimension(250, 30));

        JButton uploadImageButton = new JButton("Upload Image");
        JLabel selectedImageLabel = new JLabel("No image selected");

        JLabel nameLabel = new JLabel("Card Name:");
        JLabel rarityLabel = new JLabel("Rarity:");
        JLabel variantLabel = new JLabel("Variant:");
        JLabel valueLabel = new JLabel("Card Value:");

        // Set alignment to center
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        rarityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rarityBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        variantLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        variantBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        valueField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Upload panel
        JPanel uploadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        uploadPanel.add(uploadImageButton);
        uploadPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Image label panel
        JPanel imageLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imageLabelPanel.add(selectedImageLabel);
        imageLabelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add to panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(5));

        panel.add(rarityLabel);
        panel.add(rarityBox);
        panel.add(Box.createVerticalStrut(5));

        panel.add(variantLabel);
        panel.add(variantBox);
        panel.add(Box.createVerticalStrut(5));

        // Initially hide variant
        variantLabel.setVisible(false);
        variantBox.setVisible(false);
        panel.add(Box.createVerticalStrut(5));

        panel.add(valueLabel);
        panel.add(valueField);
        panel.add(Box.createVerticalStrut(10));

        panel.add(uploadPanel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(imageLabelPanel);

        final String[] imagePath = { null };

        uploadImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Card Image");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Image files", "jpg", "jpeg", "png", "gif"));

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imagePath[0] = selectedFile.getAbsolutePath();
                selectedImageLabel.setText("Image: " + selectedFile.getName());
            }
        });

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
                    DialogUtil.showWarning(panel, "Value must be non-negative", "Wrong input");
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

                if (imagePath[0] != null) {
                    card.setImagePath(imagePath[0]);
                }
                card.setQuantity(1);
                return card;

            } catch (NumberFormatException e) {
                DialogUtil.showWarning(panel, "Invalid input entered", "Wrong input");
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
            input = JOptionPane.showInputDialog(null, "Give New Card Quantity (Enter -999 to cancel):");

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
    public JPanel displayCard(HashMap<String, CardModel> collection, String cardName) {
        displayMessageNewLine("");

        JPanel displayPanel = new JPanel(new BorderLayout());
        JPanel imagePanel = new JPanel(new BorderLayout());

        JPanel informationPanel = new JPanel();

        if (collection.containsKey(cardName)) {
            String imagePath = collection.get(cardName).getImagePath();
            ImageIcon cardPhoto;

            if (imagePath != null) {
                cardPhoto = new ImageIcon(imagePath);
            } else {
                cardPhoto = new ImageIcon(getClass().getResource("/images/default.png"));
            }

            JLabel image = new JLabel(ImageUtils.scaleIcon(cardPhoto));

            if (collection.get(cardName).getQuantity() > 0) {

                // Main container
                displayPanel = new JPanel(new BorderLayout());
                displayPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

                // Rarity Color Mapping
                Color rarityColor;
                switch (collection.get(cardName).getRarity()) {
                    case "Common" -> rarityColor = Color.decode("#A0A0A0"); // Gray
                    case "Uncommon" -> rarityColor = Color.decode("#3CB371"); // Green
                    case "Rare" -> rarityColor = Color.decode("#4169E1"); // Blue
                    case "Legendary" -> rarityColor = Color.decode("#FFD700"); // Gold
                    default -> rarityColor = Color.BLACK;
                }

                // Variant Color Mapping
                Color variantColor;
                String variant = collection.get(cardName).getVariant();
                if (variant == null)
                    variant = "Normal";

                switch (variant) {
                    case "Extended-Art" -> variantColor = Color.decode("#D8BFD8"); // Light Purple
                    case "Full-Art" -> variantColor = Color.decode("#ADD8E6"); // Light Blue
                    case "Alt-Art" -> variantColor = Color.decode("#DC143C"); // Crimson
                    default -> variantColor = Color.decode("#FFFFFF"); // White (Normal)
                }

                // Panel to hold image and info side by side
                JPanel contentPanel = new JPanel();
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
                contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Image Panel
                imagePanel = new JPanel(new BorderLayout());
                imagePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20)); // Right padding

                // Card name at top
                JLabel cardNameDisplay = new JLabel(collection.get(cardName).getName(), SwingConstants.CENTER);
                cardNameDisplay.setFont(new Font("Inter", Font.BOLD, 35));
                cardNameDisplay.setHorizontalAlignment(SwingConstants.CENTER);
                imagePanel.add(cardNameDisplay, BorderLayout.NORTH);

                // Image centered
                image = new JLabel(ImageUtils.scaleIcon(cardPhoto));
                image.setHorizontalAlignment(SwingConstants.CENTER);
                imagePanel.add(image, BorderLayout.CENTER);

                // Information Panel
                informationPanel = new JPanel();
                informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
                informationPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

                // Rarity
                JLabel cardRarityDisplay = new JLabel("Rarity: " + collection.get(cardName).getRarity());
                cardRarityDisplay.setFont(new Font("Inter", Font.BOLD, 18));
                cardRarityDisplay.setAlignmentX(Component.LEFT_ALIGNMENT);
                cardRarityDisplay.setBorder(BorderFactory.createLineBorder(rarityColor, 6));
                cardRarityDisplay.setBackground(rarityColor);
                cardRarityDisplay.setOpaque(true);
                cardRarityDisplay.setForeground(Color.WHITE);
                informationPanel.add(Box.createVerticalStrut(15));
                informationPanel.add(cardRarityDisplay);

                // Variant (if present)
                if (collection.get(cardName).getVariant() != null) {
                    JLabel cardVariantDisplay = new JLabel("Variant: " + collection.get(cardName).getVariant());
                    cardVariantDisplay.setFont(new Font("Inter", Font.BOLD, 18));
                    cardVariantDisplay.setAlignmentX(Component.LEFT_ALIGNMENT);
                    cardVariantDisplay.setBorder(BorderFactory.createLineBorder(variantColor, 6));
                    cardVariantDisplay.setBackground(variantColor);
                    cardVariantDisplay.setOpaque(true);
                    if (variantColor.equals(Color.decode("#FFFFFF"))) {
                        cardVariantDisplay.setForeground(Color.BLACK);
                    } else {
                        cardVariantDisplay.setForeground(Color.WHITE);
                    }
                    informationPanel.add(Box.createVerticalStrut(15));
                    informationPanel.add(cardVariantDisplay);
                }

                // Value
                JLabel cardValueDisplay = new JLabel("Value: " + collection.get(cardName).getValue());
                cardValueDisplay.setFont(new Font("Inter", Font.BOLD, 18));
                cardValueDisplay.setAlignmentX(Component.LEFT_ALIGNMENT);
                informationPanel.add(Box.createVerticalStrut(15));
                informationPanel.add(cardValueDisplay);

                // Quantity
                JLabel cardQuantityDisplay = new JLabel("Current Quantity: " + collection.get(cardName).getQuantity());
                cardQuantityDisplay.setFont(new Font("Inter", Font.BOLD, 18));
                cardQuantityDisplay.setAlignmentX(Component.LEFT_ALIGNMENT);
                informationPanel.add(Box.createVerticalStrut(15));
                informationPanel.add(cardQuantityDisplay);

                // Add panels
                contentPanel.add(imagePanel);
                contentPanel.add(informationPanel);

                // Center the whole content
                displayPanel.add(contentPanel, BorderLayout.CENTER);

                return (displayPanel);

            } else if (collection.get(cardName).getQuantity() == 0) {
                JLabel cardNameDisplay = new JLabel(
                        "Card " + collection.get(cardName).getName() + " has no current copies in collection");
                cardNameDisplay.setFont(new Font("Inter", Font.BOLD, 18));
                informationPanel.add(cardNameDisplay);

                displayPanel.add(informationPanel, BorderLayout.CENTER);

                return displayPanel;

            }
        } else {
            JLabel cardNameDisplay = new JLabel("Card " + collection.get(cardName).getName() + " does not exist");
            cardNameDisplay.setFont(new Font("Inter", Font.BOLD, 18));
            informationPanel.add(cardNameDisplay);

            displayPanel.add(informationPanel, BorderLayout.CENTER);

            return displayPanel;
        }
        return null;
    }


    public JPanel displayCardForBinder(HashMap<String, CardModel> collection, String cardName) {
        displayMessageNewLine("");

        JPanel displayPanel = new JPanel(new BorderLayout());
        JPanel imagePanel = new JPanel(new BorderLayout());

        JPanel informationPanel = new JPanel();

        if (collection.containsKey(cardName)) {
            String imagePath = collection.get(cardName).getImagePath();
            ImageIcon cardPhoto;

            if (imagePath != null) {
                cardPhoto = new ImageIcon(imagePath);
            } else {
                cardPhoto = new ImageIcon(getClass().getResource("/images/default.png"));
            }

            JLabel image = new JLabel(ImageUtils.scaleIcon(cardPhoto));

            // Main container
            displayPanel = new JPanel(new BorderLayout());
            displayPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Rarity Color Mapping
            Color rarityColor;
            switch (collection.get(cardName).getRarity()) {
                case "Common" -> rarityColor = Color.decode("#A0A0A0"); // Gray
                case "Uncommon" -> rarityColor = Color.decode("#3CB371"); // Green
                case "Rare" -> rarityColor = Color.decode("#4169E1"); // Blue
                case "Legendary" -> rarityColor = Color.decode("#FFD700"); // Gold
                default -> rarityColor = Color.BLACK;
            }

            // Variant Color Mapping
            Color variantColor;
            String variant = collection.get(cardName).getVariant();
            if (variant == null)
                variant = "Normal";

            switch (variant) {
                case "Extended-Art" -> variantColor = Color.decode("#D8BFD8"); // Light Purple
                case "Full-Art" -> variantColor = Color.decode("#ADD8E6"); // Light Blue
                case "Alt-Art" -> variantColor = Color.decode("#DC143C"); // Crimson
                default -> variantColor = Color.decode("#FFFFFF"); // White (Normal)
            }

            // Panel to hold image and info side by side
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
            contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Image Panel
            imagePanel = new JPanel(new BorderLayout());
            imagePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20)); // Right padding

            // Card name at top
            JLabel cardNameDisplay = new JLabel(collection.get(cardName).getName(), SwingConstants.CENTER);
            cardNameDisplay.setFont(new Font("Inter", Font.BOLD, 35));
            cardNameDisplay.setHorizontalAlignment(SwingConstants.CENTER);
            imagePanel.add(cardNameDisplay, BorderLayout.NORTH);

            // Image centered
            image = new JLabel(ImageUtils.scaleIcon(cardPhoto));
            image.setHorizontalAlignment(SwingConstants.CENTER);
            imagePanel.add(image, BorderLayout.CENTER);

            // Information Panel
            informationPanel = new JPanel();
            informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
            informationPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

            // Rarity
            JLabel cardRarityDisplay = new JLabel("Rarity: " + collection.get(cardName).getRarity());
            cardRarityDisplay.setFont(new Font("Inter", Font.BOLD, 18));
            cardRarityDisplay.setAlignmentX(Component.LEFT_ALIGNMENT);
            cardRarityDisplay.setBorder(BorderFactory.createLineBorder(rarityColor, 6));
            cardRarityDisplay.setBackground(rarityColor);
            cardRarityDisplay.setOpaque(true);
            cardRarityDisplay.setForeground(Color.WHITE);
            informationPanel.add(Box.createVerticalStrut(15));
            informationPanel.add(cardRarityDisplay);

            // Variant (if present)
            if (collection.get(cardName).getVariant() != null) {
                JLabel cardVariantDisplay = new JLabel("Variant: " + collection.get(cardName).getVariant());
                cardVariantDisplay.setFont(new Font("Inter", Font.BOLD, 18));
                cardVariantDisplay.setAlignmentX(Component.LEFT_ALIGNMENT);
                cardVariantDisplay.setBorder(BorderFactory.createLineBorder(variantColor, 6));
                cardVariantDisplay.setBackground(variantColor);
                cardVariantDisplay.setOpaque(true);
                if (variantColor.equals(Color.decode("#FFFFFF"))) {
                    cardVariantDisplay.setForeground(Color.BLACK);
                } else {
                    cardVariantDisplay.setForeground(Color.WHITE);
                }
                informationPanel.add(Box.createVerticalStrut(15));
                informationPanel.add(cardVariantDisplay);
            }

            // Value
            JLabel cardValueDisplay = new JLabel("Value: " + collection.get(cardName).getValue());
            cardValueDisplay.setFont(new Font("Inter", Font.BOLD, 18));
            cardValueDisplay.setAlignmentX(Component.LEFT_ALIGNMENT);
            informationPanel.add(Box.createVerticalStrut(15));
            informationPanel.add(cardValueDisplay);

            // Add panels
            contentPanel.add(imagePanel);
            contentPanel.add(informationPanel);

            // Center the whole content
            displayPanel.add(contentPanel, BorderLayout.CENTER);

            return (displayPanel);

        } else {
            JLabel cardNameDisplay = new JLabel("Card " + collection.get(cardName).getName() + " does not exist");
            cardNameDisplay.setFont(new Font("Inter", Font.BOLD, 18));
            informationPanel.add(cardNameDisplay);

            displayPanel.add(informationPanel, BorderLayout.CENTER);

            return displayPanel;
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
    public JPanel displayCollection(HashMap<String, CardModel> collection, int mode) {
        ArrayList<String> cardByKey = new ArrayList<>(collection.keySet());
        Collections.sort(cardByKey);

        JPanel displayPanel = new JPanel(new GridLayout(0, 3, 0, 5));

        for (String name : cardByKey) {
            if (mode == 0) {
                JPanel wrapper = new JPanel();

                JButton tempButton = new JButton(
                        collection.get(name).getName() + " quantity: " + collection.get(name).getQuantity());
                tempButton.setPreferredSize(new Dimension(190, 190));
                wrapper.setPreferredSize(new Dimension(200, 200));
                wrapper.add(tempButton);

                displayPanel.add(wrapper);
            }
        }
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(displayPanel, BorderLayout.NORTH);
        wrapperPanel.setBackground(Color.WHITE);

        return wrapperPanel;
    }

    /**
     * Displays all cards in the collection that have a quantity greater than zero.
     *
     * @param collection the card collection to display
     */
    public JPanel displayCollection(HashMap<String, CardModel> collection) {
        ArrayList<String> cardByKey = new ArrayList<>(collection.keySet());
        Collections.sort(cardByKey);

        JPanel displayPanel = new JPanel(new GridLayout(0, 3, 0, 5));

        for (String name : cardByKey) {
            if (collection.get(name).getQuantity() > 0) {
                JPanel wrapper = new JPanel();

                JButton tempButton = new JButton(
                        collection.get(name).getName() + " quantity: " + collection.get(name).getQuantity());
                tempButton.setPreferredSize(new Dimension(190, 190));
                wrapper.setPreferredSize(new Dimension(200, 200));
                wrapper.add(tempButton);

                displayPanel.add(wrapper);
            }
        }

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(displayPanel, BorderLayout.NORTH);
        wrapperPanel.setBackground(Color.WHITE);

        return wrapperPanel;
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

}
