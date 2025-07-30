package com.tradingcards.elements.menus.binderMenu;

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
 * View class for the binder menu screen. This panel provides options
 * for interacting with binders including viewing, adding, removing,
 * trading cards, and selling or deleting binders.
 *
 * <p>
 * The layout consists of a scrollable data display panel in the center
 * and a vertical options menu on the left side of the screen.
 * </p>
 */
public class BinderMenuView extends JPanel {

    /** Panel containing option buttons. */
    private final JPanel optionPanel = new JPanel();

    /** Panel that holds the scrollable data content. */
    private final JPanel dataPanel = new JPanel(new BorderLayout());

    /** Scroll pane for displaying dynamic content panels. */
    private final JScrollPane scrollingPanel = new JScrollPane();

    /** Button to delete a binder. */
    private final JButton deleteBinderBtn = new JButton("Delete Binder");

    /** Button to add a card to a binder. */
    private final JButton addCardToBinderBtn = new JButton("Add Card to Binder");

    /** Button to remove a card from a binder. */
    private final JButton removeCardFromBinderBtn = new JButton("Remove Card from Binder");

    /** Button to trade a card. */
    private final JButton tradeCardBtn = new JButton("Trade Card");

    /** Button to view a binder's contents. */
    private final JButton viewBinderBtn = new JButton("View Binder");

    /** Button to sell a binder. */
    private final JButton sellBinderBtn = new JButton("Sell Binder");

    /** Button to return to the main menu. */
    private final JButton backBtn = new JButton("Back");

    /**
     * Constructs the binder menu view by initializing components,
     * laying out the options menu and scrollable data display.
     */
    public BinderMenuView() {
        setLayout(new BorderLayout());

        // Configure scroll panel
        scrollingPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollingPanel.setBackground(Color.white);

        // Center data panel
        dataPanel.setLayout(new BorderLayout());
        dataPanel.add(scrollingPanel);
        add(dataPanel, BorderLayout.CENTER);

        // Left-hand side options panel
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.setBackground(Color.darkGray);
        add(optionPanel, BorderLayout.WEST);

        // Set uniform width for buttons
        deleteBinderBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, deleteBinderBtn.getPreferredSize().height));
        addCardToBinderBtn
                .setMaximumSize(new Dimension(Integer.MAX_VALUE, addCardToBinderBtn.getPreferredSize().height));
        removeCardFromBinderBtn
                .setMaximumSize(new Dimension(Integer.MAX_VALUE, removeCardFromBinderBtn.getPreferredSize().height));
        tradeCardBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, tradeCardBtn.getPreferredSize().height));
        viewBinderBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, viewBinderBtn.getPreferredSize().height));
        sellBinderBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, viewBinderBtn.getPreferredSize().height));
        backBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, backBtn.getPreferredSize().height));

        // Populate options panel with components
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(
                new JLabel(ImageUtils.scaleIcon(new ImageIcon(getClass().getResource("/images/cards.png")), 198, 130)));
        optionPanel.add(Box.createVerticalStrut(15));
        optionPanel.add(deleteBinderBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(addCardToBinderBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(removeCardFromBinderBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(tradeCardBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(viewBinderBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(sellBinderBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(backBtn);
    }

    /**
     * Sets the provided panel as the content of the scrollable viewport.
     *
     * @param newPanel the new panel to be displayed in the scroll pane
     */
    public void setDataInPanel(JPanel newPanel) {
        scrollingPanel.setViewportView(newPanel);
        scrollingPanel.revalidate();
        scrollingPanel.repaint();
    }

    /**
     * Registers the action listener for the delete binder button.
     *
     * @param listener the action to perform on click
     */
    public void setDeleteBinderAction(ActionListener listener) {
        for (ActionListener al : deleteBinderBtn.getActionListeners()) {
            deleteBinderBtn.removeActionListener(al);
        }
        deleteBinderBtn.addActionListener(listener);
    }

    /**
     * Registers the action listener for the add card to binder button.
     *
     * @param listener the action to perform on click
     */
    public void setAddCardToBinderAction(ActionListener listener) {
        for (ActionListener al : addCardToBinderBtn.getActionListeners()) {
            addCardToBinderBtn.removeActionListener(al);
        }
        addCardToBinderBtn.addActionListener(listener);
    }

    /**
     * Registers the action listener for the remove card from binder button.
     *
     * @param listener the action to perform on click
     */
    public void setRemoveCardFromBinderAction(ActionListener listener) {
        for (ActionListener al : removeCardFromBinderBtn.getActionListeners()) {
            removeCardFromBinderBtn.removeActionListener(al);
        }
        removeCardFromBinderBtn.addActionListener(listener);
    }

    /**
     * Registers the action listener for the trade card button.
     *
     * @param listener the action to perform on click
     */
    public void setTradeCardAction(ActionListener listener) {
        for (ActionListener al : tradeCardBtn.getActionListeners()) {
            tradeCardBtn.removeActionListener(al);
        }
        tradeCardBtn.addActionListener(listener);
    }

    /**
     * Registers the action listener for the view binder button.
     *
     * @param listener the action to perform on click
     */
    public void setViewBinderAction(ActionListener listener) {
        for (ActionListener al : viewBinderBtn.getActionListeners()) {
            viewBinderBtn.removeActionListener(al);
        }
        viewBinderBtn.addActionListener(listener);
    }

    /**
     * Registers the action listener for the sell binder button.
     *
     * @param listener the action to perform on click
     */
    public void setSellBinderBtn(ActionListener listener) {
        for (ActionListener al : sellBinderBtn.getActionListeners()) {
            sellBinderBtn.removeActionListener(al);
        }
        sellBinderBtn.addActionListener(listener);
    }

    /**
     * Registers the action listener for the back button.
     *
     * @param listener the action to perform on click
     */
    public void setBackAction(ActionListener listener) {
        for (ActionListener al : backBtn.getActionListeners()) {
            backBtn.removeActionListener(al);
        }
        backBtn.addActionListener(listener);
    }
}
