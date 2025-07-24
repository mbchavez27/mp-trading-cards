package com.tradingcards.elements.menus.binderMenu;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BinderMenuView extends JPanel {

    private final JButton deleteBinderBtn = new JButton("Delete Binder");
    private final JButton addCardtoBinderBtn = new JButton("Add Card to Binder");
    private final JButton deleteCardFromBinderBtn = new JButton("Remove Card from Binder");
    private final JButton tradeCardBtn = new JButton("Trade Card");
    private final JButton viewBinderBtn = new JButton("View Binder");
    private final JButton backBtn = new JButton("Exit Menu");

    public BinderMenuView() {
        setLayout(new BorderLayout());
    }
}
