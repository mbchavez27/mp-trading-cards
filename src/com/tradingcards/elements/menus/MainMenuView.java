package com.tradingcards.elements.menus;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
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

    public MainMenuView() {
        setTitle("Trading Card Inventory System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null); // center the window
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Trading Card Inventory System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Panel for buttons (centered vertically and horizontally)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // padding

        // Center-align buttons
        addCardBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        newBinderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        newDeckBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        manageCardsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        manageBindersBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        manageDecksBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(addCardBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(newBinderBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(newDeckBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(manageCardsBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(manageBindersBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(manageDecksBtn);

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
}
