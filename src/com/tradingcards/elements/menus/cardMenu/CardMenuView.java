package com.tradingcards.elements.menus.cardMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * GUI View for managing cards, similar in style to MainMenuView.
 */
public class CardMenuView extends JFrame {

    private final JButton displayCardBtn = new JButton("Display Card");
    private final JButton displayCollectionBtn = new JButton("Display Collection");
    private final JButton modifyQuantityBtn = new JButton("Modify Card Quantity");
    private final JButton backBtn = new JButton("Back");

    public CardMenuView() {
        setTitle("Card Management");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Only close this window
        setSize(300, 300);
        setLocationRelativeTo(null); // center the window
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Manage Cards", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        displayCardBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        displayCollectionBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        modifyQuantityBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(displayCardBtn);
        displayCardBtn.setFocusable(false);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(displayCollectionBtn);
        displayCollectionBtn.setFocusable(false);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(modifyQuantityBtn);
        modifyQuantityBtn.setFocusable(false);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(backBtn);

        add(buttonPanel, BorderLayout.CENTER);
    }

    // Action Listeners
    public void setDisplayCardAction(ActionListener listener) {
        displayCardBtn.addActionListener(listener);
    }

    public void setDisplayCollectionAction(ActionListener listener) {
        displayCollectionBtn.addActionListener(listener);
    }

    public void setModifyQuantityAction(ActionListener listener) {
        modifyQuantityBtn.addActionListener(listener);
    }

    public void setBackAction(ActionListener listener) {
        backBtn.addActionListener(listener);
    }
}
