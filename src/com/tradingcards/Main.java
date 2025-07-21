package com.tradingcards;

import javax.swing.SwingUtilities;

import com.tradingcards.elements.menus.MainMenuController;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenuController().start();
        });
    }
}
