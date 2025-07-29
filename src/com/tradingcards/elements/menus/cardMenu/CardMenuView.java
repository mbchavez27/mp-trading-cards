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
        // adds DataPanel to center of window
        add(dataPanel, BorderLayout.CENTER);

        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));

        // adds OptionPanel to the LHS of window
        add(optionPanel, BorderLayout.WEST);
        optionPanel.setBackground(Color.darkGray);

        // Setting button dimensions
        displayCardBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, displayCardBtn.getPreferredSize().height));
        displayCollectionBtn
                .setMaximumSize(new Dimension(Integer.MAX_VALUE, displayCollectionBtn.getPreferredSize().height));
        modifyQuantityBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, modifyQuantityBtn.getPreferredSize().height));
        sellCardBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, sellCardBtn.getPreferredSize().height));
        backBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, backBtn.getPreferredSize().height));

        optionPanel.add(Box.createVerticalStrut(10));
        optionPanel.add(
                new JLabel(ImageUtils.scaleIcon(new ImageIcon(getClass().getResource("/images/cards.png")), 160, 105)));
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

    public void setDataInPanel(JPanel newPanel) {
        scrollingPanel.setViewportView(newPanel);
        scrollingPanel.revalidate();
        scrollingPanel.repaint();
    }

    // Action Listeners
    public void setDisplayCardAction(ActionListener listener) {
        for (ActionListener al : displayCardBtn.getActionListeners()) {
            displayCardBtn.removeActionListener(al);
        }
        displayCardBtn.addActionListener(listener);
    }

    public void setDisplayCollectionAction(ActionListener listener) {
        for (ActionListener al : displayCollectionBtn.getActionListeners()) {
            displayCollectionBtn.removeActionListener(al);
        }
        displayCollectionBtn.addActionListener(listener);
    }

    public void setModifyQuantityAction(ActionListener listener) {
        for (ActionListener al : modifyQuantityBtn.getActionListeners()) {
            modifyQuantityBtn.removeActionListener(al);
        }
        modifyQuantityBtn.addActionListener(listener);
    }

    public void setBackAction(ActionListener listener) {
        for (ActionListener al : backBtn.getActionListeners()) {
            backBtn.removeActionListener(al);
        }
        backBtn.addActionListener(listener);
    }

    public void setSellCardAction(ActionListener listener) {
        for (ActionListener al : sellCardBtn.getActionListeners()) {
            sellCardBtn.removeActionListener(al);
        }
        sellCardBtn.addActionListener(listener);
    }
}
