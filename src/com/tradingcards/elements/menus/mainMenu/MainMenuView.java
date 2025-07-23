package com.tradingcards.elements.menus.mainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JPanel {

    private final JButton addCardBtn = new JButton("Add Card");
    private final JButton newBinderBtn = new JButton("Create Binder");
    private final JButton newDeckBtn = new JButton("Create Deck");
    private final JButton manageCardsBtn = new JButton("Manage Cards");
    private final JButton manageBindersBtn = new JButton("Manage Binders");
    private final JButton manageDecksBtn = new JButton("Manage Decks");
    private final JButton placeHolderBtn = new JButton("");
    private final JButton closeApplicationBtn = new JButton("Exit");

    public MainMenuView() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Trading Card Inventory System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        manageCardsBtn.setEnabled(false);
        manageBindersBtn.setEnabled(false);
        manageDecksBtn.setEnabled(false);
        placeHolderBtn.setVisible(false);

        addCardBtn.setFocusable(false);
        newBinderBtn.setFocusable(false);
        newDeckBtn.setFocusable(false);
        manageCardsBtn.setFocusable(false);
        manageBindersBtn.setFocusable(false);
        manageDecksBtn.setFocusable(false);
        closeApplicationBtn.setFocusable(false);

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

    // Action setters
    public void setAddCardAction(ActionListener listener) {
        addCardBtn.addActionListener(listener);
    }

    public void setNewBinderAction(ActionListener listener) {
        newBinderBtn.addActionListener(listener);
    }

    public void setNewDeckAction(ActionListener listener) {
        newDeckBtn.addActionListener(listener);
    }

    public void setManageCardsAction(ActionListener listener) {
        manageCardsBtn.addActionListener(listener);
    }

    public void setManageBindersAction(ActionListener listener) {
        manageBindersBtn.addActionListener(listener);
    }

    public void setManageDecksAction(ActionListener listener) {
        manageDecksBtn.addActionListener(listener);
    }

    public void setCloseApplicationButton(ActionListener listener) {
        closeApplicationBtn.addActionListener(listener);
    }

    // Visibility control
    public void showManageCardBtn() {
        manageCardsBtn.setEnabled(true);
    }

    public void showManageBinderBtn() {
        manageBindersBtn.setEnabled(true);
    }

    public void showManageDeckBtn() {
        manageDecksBtn.setEnabled(true);
    }
}
