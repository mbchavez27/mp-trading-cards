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

public class BinderMenuView extends JPanel {

    private final JPanel optionPanel = new JPanel();
    private final JPanel dataPanel = new JPanel(new BorderLayout());
    private final JScrollPane scrollingPanel = new JScrollPane();

    private final JButton deleteBinderBtn = new JButton("Delete Binder");
    private final JButton addCardToBinderBtn = new JButton("Add Card to Binder");
    private final JButton removeCardFromBinderBtn = new JButton("Remove Card from Binder");
    private final JButton tradeCardBtn = new JButton("Trade Card");
    private final JButton viewBinderBtn = new JButton("View Binder");
    private final JButton sellBinderBtn = new JButton("Sell Binder");
    private final JButton backBtn = new JButton("Back");

    public BinderMenuView() {
        setLayout(new BorderLayout());

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

        deleteBinderBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, deleteBinderBtn.getPreferredSize().height));
        addCardToBinderBtn
                .setMaximumSize(new Dimension(Integer.MAX_VALUE, addCardToBinderBtn.getPreferredSize().height));
        removeCardFromBinderBtn
                .setMaximumSize(new Dimension(Integer.MAX_VALUE, removeCardFromBinderBtn.getPreferredSize().height));
        tradeCardBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, tradeCardBtn.getPreferredSize().height));
        viewBinderBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, viewBinderBtn.getPreferredSize().height));
        sellBinderBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, viewBinderBtn.getPreferredSize().height));
        backBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, backBtn.getPreferredSize().height));

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

    public void setDataInPanel(JPanel newPanel) {
        scrollingPanel.setViewportView(newPanel);
        scrollingPanel.revalidate();
        scrollingPanel.repaint();
    }

    // Action Listeners
    public void setDeleteBinderAction(ActionListener listener) {
        for (ActionListener al : deleteBinderBtn.getActionListeners()) {
            deleteBinderBtn.removeActionListener(al);
        }
        deleteBinderBtn.addActionListener(listener);
    }

    public void setAddCardToBinderAction(ActionListener listener) {
        for (ActionListener al : addCardToBinderBtn.getActionListeners()) {
            addCardToBinderBtn.removeActionListener(al);
        }
        addCardToBinderBtn.addActionListener(listener);
    }

    public void setRemoveCardFromBinderAction(ActionListener listener) {
        for (ActionListener al : removeCardFromBinderBtn.getActionListeners()) {
            removeCardFromBinderBtn.removeActionListener(al);
        }
        removeCardFromBinderBtn.addActionListener(listener);
    }

    public void setTradeCardAction(ActionListener listener) {
        for (ActionListener al : tradeCardBtn.getActionListeners()) {
            tradeCardBtn.removeActionListener(al);
        }
        tradeCardBtn.addActionListener(listener);
    }

    public void setViewBinderAction(ActionListener listener) {
        for (ActionListener al : viewBinderBtn.getActionListeners()) {
            viewBinderBtn.removeActionListener(al);
        }
        viewBinderBtn.addActionListener(listener);
    }

    public void setSellBinderBtn(ActionListener listener) {
        for (ActionListener al : sellBinderBtn.getActionListeners()) {
            sellBinderBtn.removeActionListener(al);
        }
        sellBinderBtn.addActionListener(listener);
    }

    public void setBackAction(ActionListener listener) {
        for (ActionListener al : backBtn.getActionListeners()) {
            backBtn.removeActionListener(al);
        }
        backBtn.addActionListener(listener);
    }
}
