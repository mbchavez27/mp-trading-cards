package com.tradingcards.elements.menus.mainMenu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.tradingcards.elements.collection.CollectionModel;

/**
 * View component for the Main Menu screen of the Trading Card Inventory System.
 * Displays options to create or manage cards, binders, decks, and exit the
 * application.
 * Also shows the user's current available money.
 */
public class MainMenuView extends JPanel {

    /** Button for adding a new card to the collection. */
    private final JButton addCardBtn = new JButton("Add Card");

    /** Button for creating a new binder. */
    private final JButton newBinderBtn = new JButton("Create Binder");

    /** Button for creating a new deck. */
    private final JButton newDeckBtn = new JButton("Create Deck");

    /** Button for managing existing cards. Disabled by default. */
    private final JButton manageCardsBtn = new JButton("Manage Cards");

    /** Button for managing existing binders. Disabled by default. */
    private final JButton manageBindersBtn = new JButton("Manage Binders");

    /** Button for managing existing decks. Disabled by default. */
    private final JButton manageDecksBtn = new JButton("Manage Decks");

    /** Invisible placeholder button to maintain grid layout symmetry. */
    private final JButton placeHolderBtn = new JButton("");

    /** Button to close or exit the application. */
    private final JButton closeApplicationBtn = new JButton("Exit");

    /** Label that displays the user's current cash balance. */
    private final JLabel moneyLabel;

    /** Shared collection model that holds the cards, binders, decks, and money. */
    private final CollectionModel sharedCollection;

    /**
     * Constructs the MainMenuView and initializes layout and buttons.
     *
     * @param sharedCollection The shared collection model containing cards,
     *                         binders, decks, and money.
     */
    public MainMenuView(CollectionModel sharedCollection) {
        this.sharedCollection = sharedCollection;
        setLayout(new BorderLayout());

        // Top label panel
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Trading Card Inventory System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        moneyLabel = new JLabel("Cash: " + sharedCollection.getMoney(), SwingConstants.CENTER);
        moneyLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        moneyLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 20, 0));
        moneyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        northPanel.add(titleLabel);
        northPanel.add(moneyLabel);
        add(northPanel, BorderLayout.NORTH);

        // Button grid panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Disable manage buttons by default
        manageCardsBtn.setEnabled(false);
        manageBindersBtn.setEnabled(false);
        manageDecksBtn.setEnabled(false);
        placeHolderBtn.setVisible(false); // Invisible but balances grid layout

        // Improve accessibility
        addCardBtn.setFocusable(false);
        newBinderBtn.setFocusable(false);
        newDeckBtn.setFocusable(false);
        manageCardsBtn.setFocusable(false);
        manageBindersBtn.setFocusable(false);
        manageDecksBtn.setFocusable(false);
        closeApplicationBtn.setFocusable(false);

        // Add all buttons to grid
        buttonPanel.add(addCardBtn);
        buttonPanel.add(newBinderBtn);
        buttonPanel.add(newDeckBtn);
        buttonPanel.add(manageCardsBtn);
        buttonPanel.add(manageBindersBtn);
        buttonPanel.add(manageDecksBtn);
        buttonPanel.add(placeHolderBtn);
        buttonPanel.add(closeApplicationBtn);

        add(buttonPanel, BorderLayout.CENTER);
    }

    // -------------------------------
    // Action listener setters
    // -------------------------------

    /**
     * Sets the action listener for the "Add Card" button.
     */
    public void setAddCardAction(ActionListener listener) {
        addCardBtn.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Create Binder" button.
     */
    public void setNewBinderAction(ActionListener listener) {
        newBinderBtn.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Create Deck" button.
     */
    public void setNewDeckAction(ActionListener listener) {
        newDeckBtn.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Manage Cards" button.
     */
    public void setManageCardsAction(ActionListener listener) {
        manageCardsBtn.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Manage Binders" button.
     */
    public void setManageBindersAction(ActionListener listener) {
        manageBindersBtn.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Manage Decks" button.
     */
    public void setManageDecksAction(ActionListener listener) {
        manageDecksBtn.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Exit" button.
     */
    public void setCloseApplicationButton(ActionListener listener) {
        closeApplicationBtn.addActionListener(listener);
    }

    // -------------------------------
    // UI state updates
    // -------------------------------

    /**
     * Enables the "Manage Cards" button.
     */
    public void showManageCardBtn() {
        manageCardsBtn.setEnabled(true);
    }

    /**
     * Enables the "Manage Binders" button.
     */
    public void showManageBinderBtn() {
        manageBindersBtn.setEnabled(true);
    }

    /**
     * Enables the "Manage Decks" button.
     */
    public void showManageDeckBtn() {
        manageDecksBtn.setEnabled(true);
    }

    /**
     * Updates the status (enabled/disabled) of the "Manage" buttons based on the
     * current collection.
     */
    public void updateButtonStatus() {
        if (sharedCollection.getBinderCollection().isEmpty())
            manageBindersBtn.setEnabled(false);

        if (sharedCollection.getCardCollection().isEmpty())
            manageCardsBtn.setEnabled(false);

        if (sharedCollection.getDeckCollection().isEmpty())
            manageDecksBtn.setEnabled(false);
    }

    /**
     * Updates the cash label to reflect the user's current amount of money.
     */
    public void updateMoneyLabel() {
        moneyLabel.setText("Cash: " + sharedCollection.getMoney());
    }
}
