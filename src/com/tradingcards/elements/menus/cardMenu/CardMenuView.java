package com.tradingcards.elements.menus.cardMenu;

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
 * GUI view class responsible for displaying the Card Menu screen.
 * This panel provides buttons for viewing cards, managing card quantities,
 * selling cards, and returning to the main menu.
 *
 * <p>
 * The layout features a scrollable data display panel at the center
 * and a vertical button panel on the left-hand side for user actions.
 * </p>
 */
public class CardMenuView extends JPanel {

    /** Button to display a specific card. */
    private final JButton displayCardBtn = new JButton("Display Card");

    /** Button to display the entire card collection. */
    private final JButton displayCollectionBtn = new JButton("Display Collection");

    /** Button to modify the quantity of a selected card. */
    private final JButton modifyQuantityBtn = new JButton("Modify Card Quantity");

    /** Button to sell a selected card. */
    private final JButton sellCardBtn = new JButton("Sell Card");

    /** Button to go back to the main menu. */
    private final JButton backBtn = new JButton("Back");

    /** Panel that contains all the option buttons. */
    private final JPanel optionPanel = new JPanel();

    /** Panel that holds scrollable content like the card collection. */
    private final JPanel dataPanel = new JPanel(new BorderLayout());

    /** Scroll pane for dynamically displaying card-related panels. */
    private final JScrollPane scrollingPanel = new JScrollPane();

    /**
     * Constructs a new {@code CardMenuView}, initializing its layout,
     * buttons, scroll panel, and action area.
     */
    public CardMenuView() {
        setLayout(new BorderLayout());

        scrollingPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollingPanel.setBackground(Color.white);

        dataPanel.setLayout(new BorderLayout());
        dataPanel.add(scrollingPanel);
        add(dataPanel, BorderLayout.CENTER);

        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.setBackground(Color.darkGray);
        add(optionPanel, BorderLayout.WEST);

        // Set max width for all buttons
        displayCardBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, displayCardBtn.getPreferredSize().height));
        displayCollectionBtn
                .setMaximumSize(new Dimension(Integer.MAX_VALUE, displayCollectionBtn.getPreferredSize().height));
        modifyQuantityBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, modifyQuantityBtn.getPreferredSize().height));
        sellCardBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, sellCardBtn.getPreferredSize().height));
        backBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, backBtn.getPreferredSize().height));

        // Add components to option panel
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(new JLabel(ImageUtils.scaleIcon(
                new ImageIcon(getClass().getResource("/images/cards.png")), 160, 105)));
        optionPanel.add(Box.createVerticalStrut(15));
        optionPanel.add(displayCardBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(displayCollectionBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(modifyQuantityBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(sellCardBtn);
        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(backBtn);
    }

    /**
     * Sets the content panel to be displayed inside the scroll pane.
     *
     * @param newPanel the panel to display
     */
    public void setDataInPanel(JPanel newPanel) {
        scrollingPanel.setViewportView(newPanel);
        scrollingPanel.revalidate();
        scrollingPanel.repaint();
    }

    /**
     * Assigns an action listener to the "Display Card" button.
     *
     * @param listener the action to perform when the button is clicked
     */
    public void setDisplayCardAction(ActionListener listener) {
        for (ActionListener al : displayCardBtn.getActionListeners()) {
            displayCardBtn.removeActionListener(al);
        }
        displayCardBtn.addActionListener(listener);
    }

    /**
     * Assigns an action listener to the "Display Collection" button.
     *
     * @param listener the action to perform when the button is clicked
     */
    public void setDisplayCollectionAction(ActionListener listener) {
        for (ActionListener al : displayCollectionBtn.getActionListeners()) {
            displayCollectionBtn.removeActionListener(al);
        }
        displayCollectionBtn.addActionListener(listener);
    }

    /**
     * Assigns an action listener to the "Modify Card Quantity" button.
     *
     * @param listener the action to perform when the button is clicked
     */
    public void setModifyQuantityAction(ActionListener listener) {
        for (ActionListener al : modifyQuantityBtn.getActionListeners()) {
            modifyQuantityBtn.removeActionListener(al);
        }
        modifyQuantityBtn.addActionListener(listener);
    }

    /**
     * Assigns an action listener to the "Sell Card" button.
     *
     * @param listener the action to perform when the button is clicked
     */
    public void setSellCardAction(ActionListener listener) {
        for (ActionListener al : sellCardBtn.getActionListeners()) {
            sellCardBtn.removeActionListener(al);
        }
        sellCardBtn.addActionListener(listener);
    }

    /**
     * Assigns an action listener to the "Back" button.
     *
     * @param listener the action to perform when the button is clicked
     */
    public void setBackAction(ActionListener listener) {
        for (ActionListener al : backBtn.getActionListeners()) {
            backBtn.removeActionListener(al);
        }
        backBtn.addActionListener(listener);
    }
}
