package com.tradingcards;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.tradingcards.elements.menus.mainMenu.MainMenuController;

/**
 * The entry point of the Trading Cards application.
 * 
 * <p>
 * This class sets up global UI styles using {@link UIManager}, such as
 * button font, colors, and borders, and then starts the application by
 * launching
 * the main menu through {@link MainMenuController}.
 * </p>
 */
public class Main {

    /**
     * The main method of the application.
     * 
     * <p>
     * Initializes UI styling properties and ensures that the main menu is
     * started on the Event Dispatch Thread using
     * {@link SwingUtilities#invokeLater}.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Set global UI styles
        UIManager.put("Button.font", new Font("Inter", Font.PLAIN, 14));
        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Launch the main menu on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new MainMenuController().start();
        });
    }
}
