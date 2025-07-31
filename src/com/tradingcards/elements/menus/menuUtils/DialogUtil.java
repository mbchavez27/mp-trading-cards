package com.tradingcards.elements.menus.menuUtils;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * Utility class for displaying standardized dialog messages using JOptionPane.
 * Provides methods to show informational, warning, and error dialogs.
 */
public class DialogUtil {

    /**
     * Displays a message dialog with the specified message type.
     *
     * @param parent      the parent component of the dialog; can be {@code null}
     * @param message     the message to display
     * @param title       the title of the dialog
     * @param messageType the type of message to be displayed:
     *                    {@code JOptionPane.INFORMATION_MESSAGE},
     *                    {@code JOptionPane.WARNING_MESSAGE},
     *                    {@code JOptionPane.ERROR_MESSAGE}, etc.
     */
    public static void showMessage(Component parent, String message, String title, int messageType) {
        JOptionPane.showMessageDialog(parent, message, title, messageType);
    }

    /**
     * Displays a warning dialog with the specified message and title.
     *
     * @param parent  the parent component of the dialog; can be {@code null}
     * @param message the warning message to display
     * @param title   the title of the dialog
     */
    public static void showWarning(Component parent, String message, String title) {
        showMessage(parent, message, title, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Displays an informational dialog with the specified message and title.
     *
     * @param parent  the parent component of the dialog; can be {@code null}
     * @param message the information message to display
     * @param title   the title of the dialog
     */
    public static void showInfo(Component parent, String message, String title) {
        showMessage(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays an error dialog with the specified message and title.
     *
     * @param parent  the parent component of the dialog; can be {@code null}
     * @param message the error message to display
     * @param title   the title of the dialog
     */
    public static void showError(Component parent, String message, String title) {
        showMessage(parent, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
