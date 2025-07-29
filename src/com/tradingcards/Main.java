package com.tradingcards;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.tradingcards.elements.menus.mainMenu.MainMenuController;

public class Main {
    public static void main(String[] args) {
        UIManager.put("Button.font", new Font("Inter", Font.PLAIN, 14));
        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(10, 20, 10, 20));

        SwingUtilities.invokeLater(() -> {
            new MainMenuController().start();
        });
    }
}
