package com.tradingcards.elements.menus.deckMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DeckMenuView extends JPanel{

    private final JPanel optionPanel = new JPanel();
    private final JPanel dataPanel = new JPanel(new BorderLayout());
    private final JScrollPane scrollingPanel = new JScrollPane();

    private final JButton deleteDeckBtn = new JButton("Delete Deck");
    private final JButton addCardToDeckBtn = new JButton("Add Card to Deck");
    private final JButton removeCardFromDeckBtn = new JButton("Remove Card from Deck");
    private final JButton viewDeckBtn = new JButton("View Deck");
    private final JButton sellDeckBtn = new JButton("Sell Deck");
    private final JButton backBtn = new JButton("Back");

    public DeckMenuView(){
        setLayout(new BorderLayout());

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

        deleteDeckBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, deleteDeckBtn.getPreferredSize().height));
        addCardToDeckBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, addCardToDeckBtn.getPreferredSize().height));
        removeCardFromDeckBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, removeCardFromDeckBtn.getPreferredSize().height));
        viewDeckBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, viewDeckBtn.getPreferredSize().height));
        sellDeckBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, sellDeckBtn.getPreferredSize().height));
        backBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, backBtn.getPreferredSize().height));

        optionPanel.add(Box.createVerticalStrut(100));
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

    public void setDataInPanel(JPanel newPanel) {
        scrollingPanel.setViewportView(newPanel);
        scrollingPanel.revalidate();
        scrollingPanel.repaint();
    }

    public void setDeleteDeckBtn(ActionListener listener) {
        deleteDeckBtn.addActionListener(listener);
    }

    public void setAddCardToDeckBtn(ActionListener listener){
        addCardToDeckBtn.addActionListener(listener);
    }

    public void setRemoveCardFromDeckBtn(ActionListener listener){
        removeCardFromDeckBtn.addActionListener(listener);
    }

    public void setViewDeckBtn(ActionListener listener){
        viewDeckBtn.addActionListener(listener);
    }
    public void setSellDeckBtn(ActionListener listener){
        sellDeckBtn.addActionListener(listener);
    }
    public void setBackBtn(ActionListener listener){
        backBtn.addActionListener(listener);
    }


}
