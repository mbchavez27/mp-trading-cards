package com.tradingcards;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code MainFrame} class serves as the main application window for
 * the Trading Card Inventory System.
 *
 * <p>
 * It uses a {@link CardLayout} to manage multiple panels within the same frame,
 * allowing different screens (e.g., menu, binder, collection) to be displayed
 * dynamically.
 * </p>
 *
 * <p>
 * The frame is fixed in size and centered on the screen upon launch.
 * </p>
 */
public class MainFrame extends JFrame {

    /** Layout manager used to switch between different panels. */
    private final CardLayout cardLayout;

    /**
     * The main container panel that holds all sub-panels managed by the card
     * layout.
     */
    private final JPanel mainPanel;

    /**
     * Constructs a new {@code MainFrame} window.
     *
     * <p>
     * Initializes the frame with a fixed size, title, and layout manager,
     * and sets up the main panel using {@link CardLayout}.
     * </p>
     */
    public MainFrame() {
        setTitle("Trading Card Inventory System");
        setSize(1450, 850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);
    }

    /**
     * Adds a panel to the main panel managed by the card layout.
     *
     * @param name  the unique name used to identify the panel
     * @param panel the {@link JPanel} to be added
     */
    public void addPanel(String name, JPanel panel) {
        mainPanel.add(panel, name);
    }

    /**
     * Displays the panel associated with the given name.
     *
     * @param name the name of the panel to be shown
     */
    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }
}
