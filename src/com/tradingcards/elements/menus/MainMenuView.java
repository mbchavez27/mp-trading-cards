package com.tradingcards.elements.menus;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainMenuView extends JFrame {

    private final JButton addCardBtn = new JButton("Add Card");
    private final JButton newBinderBtn = new JButton("Create Binder");
    private final JButton newDeckBtn = new JButton("Create Deck");
    private final JButton manageCardsBtn = new JButton("Manage Cards");
    private final JButton manageBindersBtn = new JButton("Manage Binders");
    private final JButton manageDecksBtn = new JButton("Manage Decks");
    private final JButton placeHolderBtn = new JButton("");
    private final JButton closeApplicationBtn = new JButton("Exit");

    public MainMenuView() {
        setTitle("Trading Card Inventory System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null); // center the window
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Trading Card Inventory System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

//        addCardBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
//        newBinderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
//        newDeckBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
//        manageCardsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        manageCardsBtn.setEnabled(false);
//        manageBindersBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        manageBindersBtn.setEnabled(false);
//        manageDecksBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        manageDecksBtn.setEnabled(false);
//        closeApplicationBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(addCardBtn);
        addCardBtn.setFocusable(false);
//        buttonPanel.add(Box.createVerticalStrut(10));

        buttonPanel.add(newBinderBtn);
        newBinderBtn.setFocusable(false);
//        buttonPanel.add(Box.createVerticalStrut(10));

        buttonPanel.add(newDeckBtn);
        newDeckBtn.setFocusable(false);
//        buttonPanel.add(Box.createVerticalStrut(10));

        buttonPanel.add(manageCardsBtn);
        manageCardsBtn.setFocusable(false);
//        buttonPanel.add(Box.createVerticalStrut(10));

        buttonPanel.add(manageBindersBtn);
        manageBindersBtn.setFocusable(false);
//        buttonPanel.add(Box.createVerticalStrut(10));

        buttonPanel.add(manageDecksBtn);
        manageDecksBtn.setFocusable(false);
//        buttonPanel.add(Box.createVerticalStrut(10));

        buttonPanel.add(placeHolderBtn);
        placeHolderBtn.setVisible(false);

        buttonPanel.add(closeApplicationBtn);
        closeApplicationBtn.setFocusable(false);

        add(buttonPanel, BorderLayout.CENTER);
    }

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

    public void ShowManageCard() {
        manageCardsBtn.setEnabled(true);
    }

    public void ShowManageBinder() {
        manageBindersBtn.setEnabled(true);
    }

    public void ShowManageDeck() {
        manageDecksBtn.setEnabled(true);
    }
}
