package com.tradingcards.elements.menus.cardMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * GUI View for managing cards, similar in style to MainMenuView.
 */
public class CardMenuView extends JPanel {
    private final JButton displayCardBtn = new JButton("Display Card");
    private final JButton displayCollectionBtn = new JButton("Display Collection");
    private final JButton modifyQuantityBtn = new JButton("Modify Card Quantity");
    private final JButton sellCardBtn = new JButton("Sell Card");
    private final JButton backBtn = new JButton("Back");
    private final JPanel optionPanel = new JPanel();
    private final JPanel dataPanel = new JPanel(new BorderLayout());
    private final JScrollPane scrollingPanel = new JScrollPane();

    public CardMenuView() {
        setLayout(new BorderLayout());

        // JPanel optionPanel = new JPanel();
        // JPanel dataPanel = new JPanel();
        scrollingPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollingPanel.setBackground(Color.white);

        dataPanel.setLayout(new BorderLayout());
        dataPanel.add(scrollingPanel);
        //adds DataPanel to center of window
        add(dataPanel, BorderLayout.CENTER);

        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));

        //adds OptionPanel to the LHS of window
        add(optionPanel, BorderLayout.WEST);
        optionPanel.setBackground(Color.darkGray);

        //Setting button dimensions
        displayCardBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, displayCardBtn.getPreferredSize().height));
        displayCollectionBtn
                .setMaximumSize(new Dimension(Integer.MAX_VALUE, displayCollectionBtn.getPreferredSize().height));
        modifyQuantityBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, modifyQuantityBtn.getPreferredSize().height));
        sellCardBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, sellCardBtn.getPreferredSize().height));
        backBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, backBtn.getPreferredSize().height));

        optionPanel.add(Box.createVerticalStrut(100));
        styleButton(displayCardBtn);
        optionPanel.add(displayCardBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        styleButton(displayCollectionBtn);
        optionPanel.add(displayCollectionBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        styleButton(modifyQuantityBtn);
        optionPanel.add(modifyQuantityBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        styleButton(sellCardBtn);
        optionPanel.add(sellCardBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        styleButton(backBtn);
        optionPanel.add(backBtn);
    }

    public void setDataInPanel(JPanel newPanel) {
        scrollingPanel.setViewportView(newPanel);
        scrollingPanel.revalidate();
        scrollingPanel.repaint();
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Inter", Font.PLAIN, 14));
        button.setContentAreaFilled(true);
        button.setOpaque(true);
    }

    // public class CardMenuView extends JFrame {
    //
    // private final JButton displayCardBtn = new JButton("Display Card");
    // private final JButton displayCollectionBtn = new JButton("Display
    // Collection");
    // private final JButton modifyQuantityBtn = new JButton("Modify Card
    // Quantity");
    // private final JButton backBtn = new JButton("Back");
    //
    // public CardMenuView() {
    // setTitle("Card Management");
    // setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Only close this window
    // setSize(300, 300);
    // setLocationRelativeTo(null); // center the window
    // setLayout(new BorderLayout());
    //
    // JLabel titleLabel = new JLabel("Manage Cards", SwingConstants.CENTER);
    // titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
    // titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
    // add(titleLabel, BorderLayout.NORTH);
    //
    // JPanel buttonPanel = new JPanel();
    // buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    // buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
    //
    // displayCardBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    // displayCollectionBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    // modifyQuantityBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    // backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    //
    // buttonPanel.add(displayCardBtn);
    // displayCardBtn.setFocusable(false);
    // buttonPanel.add(Box.createVerticalStrut(10));
    // buttonPanel.add(displayCollectionBtn);
    // displayCollectionBtn.setFocusable(false);
    // buttonPanel.add(Box.createVerticalStrut(10));
    // buttonPanel.add(modifyQuantityBtn);
    // modifyQuantityBtn.setFocusable(false);
    // buttonPanel.add(Box.createVerticalStrut(10));
    // buttonPanel.add(backBtn);
    //
    // add(buttonPanel, BorderLayout.CENTER);
    // }

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

    public void setSellCardAction(ActionListener listener) {
        sellCardBtn.addActionListener(listener);
    }
}
