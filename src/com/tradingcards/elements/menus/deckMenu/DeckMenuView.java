package com.tradingcards.elements.menus.deckMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.tradingcards.elements.card.cardUtils.ImageUtils;

/**
 * View class for the Deck Menu. It provides a graphical interface where users
 * can manage their decks — including adding or removing cards, viewing,
 * selling,
 * or deleting decks.
 */
public class DeckMenuView extends JPanel {

    /** Panel containing all functional buttons (menu options). */
    private final JPanel optionPanel = new JPanel();

    /** Panel for displaying dynamic content based on user interaction. */
    private final JPanel dataPanel = new JPanel(new BorderLayout());

    /** Scrollable wrapper around the data panel content. */
    private final JScrollPane scrollingPanel = new JScrollPane();

    /** Button to delete a selected deck. */
    private final JButton deleteDeckBtn = new JButton("Delete Deck");

    /** Button to add a card to a deck. */
    private final JButton addCardToDeckBtn = new JButton("Add Card to Deck");

    /** Button to remove a card from a deck. */
    private final JButton removeCardFromDeckBtn = new JButton("Remove Card from Deck");

    /** Button to view details of a selected deck. */
    private final JButton viewDeckBtn = new JButton("View Deck");

    /** Button to sell a selected deck. */
    private final JButton sellDeckBtn = new JButton("Sell Deck");

    /** Button to navigate back to the main menu. */
    private final JButton backBtn = new JButton("Back");

    /**
     * Constructs the DeckMenuView and initializes all components and layout.
     * Includes side panel with buttons and a scrollable center panel for content.
     */
    public DeckMenuView() {
        setLayout(new BorderLayout());

        scrollingPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollingPanel.setBackground(Color.white);

        dataPanel.setLayout(new BorderLayout());
        dataPanel.add(scrollingPanel);
        add(dataPanel, BorderLayout.CENTER); // Center panel for displaying content

        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.setBackground(Color.darkGray);
        add(optionPanel, BorderLayout.WEST); // Left-hand side panel for buttons

        // Set button max width to fill horizontally
        deleteDeckBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, deleteDeckBtn.getPreferredSize().height));
        addCardToDeckBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, addCardToDeckBtn.getPreferredSize().height));
        removeCardFromDeckBtn
                .setMaximumSize(new Dimension(Integer.MAX_VALUE, removeCardFromDeckBtn.getPreferredSize().height));
        viewDeckBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, viewDeckBtn.getPreferredSize().height));
        sellDeckBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, sellDeckBtn.getPreferredSize().height));
        backBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, backBtn.getPreferredSize().height));

        // Build option panel with spacing and image
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(
                new JLabel(ImageUtils.scaleIcon(new ImageIcon(getClass().getResource("/images/cards.png")), 190, 125)));
        optionPanel.add(Box.createVerticalStrut(15));
        optionPanel.add(deleteDeckBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(addCardToDeckBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(removeCardFromDeckBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(viewDeckBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(sellDeckBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(backBtn);
    }

    /**
     * Replaces the current content of the scrollable panel with the given panel.
     *
     * @param newPanel the panel to display inside the scroll area
     */
    public void setDataInPanel(JPanel newPanel) {
        scrollingPanel.setViewportView(newPanel);
        scrollingPanel.revalidate();
        scrollingPanel.repaint();
    }

    // === Action Listener Bindings ===

    /**
     * Sets the action listener for the "Delete Deck" button.
     *
     * @param listener the action listener to assign
     */
    public void setDeleteDeckBtn(ActionListener listener) {
        for (ActionListener al : deleteDeckBtn.getActionListeners()) {
            deleteDeckBtn.removeActionListener(al);
        }
        deleteDeckBtn.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Add Card to Deck" button.
     *
     * @param listener the action listener to assign
     */
    public void setAddCardToDeckBtn(ActionListener listener) {
        for (ActionListener al : addCardToDeckBtn.getActionListeners()) {
            addCardToDeckBtn.removeActionListener(al);
        }
        addCardToDeckBtn.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Remove Card from Deck" button.
     *
     * @param listener the action listener to assign
     */
    public void setRemoveCardFromDeckBtn(ActionListener listener) {
        for (ActionListener al : removeCardFromDeckBtn.getActionListeners()) {
            removeCardFromDeckBtn.removeActionListener(al);
        }
        removeCardFromDeckBtn.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "View Deck" button.
     *
     * @param listener the action listener to assign
     */
    public void setViewDeckBtn(ActionListener listener) {
        for (ActionListener al : viewDeckBtn.getActionListeners()) {
            viewDeckBtn.removeActionListener(al);
        }
        viewDeckBtn.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Sell Deck" button.
     *
     * @param listener the action listener to assign
     */
    public void setSellDeckBtn(ActionListener listener) {
        for (ActionListener al : sellDeckBtn.getActionListeners()) {
            sellDeckBtn.removeActionListener(al);
        }
        sellDeckBtn.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Back" button.
     *
     * @param listener the action listener to assign
     */
    public void setBackBtn(ActionListener listener) {
        for (ActionListener al : backBtn.getActionListeners()) {
            backBtn.removeActionListener(al);
        }
        backBtn.addActionListener(listener);
    }
}
