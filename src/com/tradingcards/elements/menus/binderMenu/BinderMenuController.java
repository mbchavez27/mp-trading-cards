package com.tradingcards.elements.menus.binderMenu;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.tradingcards.MainFrame;
import com.tradingcards.elements.binder.BinderController;
import com.tradingcards.elements.binder.BinderView;
import com.tradingcards.elements.menus.mainMenu.MainMenuView;

/**
 * Controller class responsible for managing the interactions and actions
 * related to the binder menu. It acts as a bridge between the view
 * and the binder logic, coordinating binder-related operations such as
 * viewing, adding, removing, trading, and selling binders/cards.
 */
public class BinderMenuController {

    /** Controller responsible for binder-related logic. */
    private final BinderController binderController;

    /** View component for displaying individual binders. */
    private final BinderView binderView;

    /** View component for the binder menu interface. */
    private final BinderMenuView view;

    /** Main application frame that handles panel switching. */
    private final MainFrame mainFrame;

    /** View component for the main menu. */
    private final MainMenuView mainMenuView;

    /**
     * Constructs a new {@code BinderMenuController} with the provided
     * view, controller, and main application components.
     *
     * @param view             the binder menu view
     * @param binderController the binder logic controller
     * @param binderView       the binder view
     * @param mainMenuView     the main menu view
     * @param mainFrame        the main application frame
     */
    public BinderMenuController(BinderMenuView view, BinderController binderController, BinderView binderView,
            MainMenuView mainMenuView, MainFrame mainFrame) {
        this.binderController = binderController;
        this.binderView = binderView;
        this.view = view;
        this.mainMenuView = mainMenuView;
        this.mainFrame = mainFrame;
    }

    /**
     * Starts the binder menu controller by setting up the UI interactions
     * and action listeners for all available binder operations.
     */
    public void start() {
        view.setDataInPanel(binderController.displayBinders());
        JPanel displayPanel = new JPanel();

        // Back to main menu
        view.setBackAction(e -> {
            mainMenuView.updateMoneyLabel();
            mainMenuView.updateButtonStatus();
            mainFrame.showPanel("mainMenu");
        });

        // Delete a binder
        view.setDeleteBinderAction(e -> {
            displayPanel.setLayout(new BorderLayout());
            view.setDataInPanel(displayPanel);
            binderController.removeBinder(displayPanel);
        });

        // Add a card to a binder
        view.setAddCardToBinderAction(e -> {
            displayPanel.setLayout(new BorderLayout());
            view.setDataInPanel(displayPanel);
            binderController.addCard(displayPanel);
        });

        // Remove a card from a binder
        view.setRemoveCardFromBinderAction(e -> {
            displayPanel.setLayout(new BorderLayout());
            view.setDataInPanel(displayPanel);
            binderController.removeCard(displayPanel);
        });

        // Trade a card
        view.setTradeCardAction(e -> {
            displayPanel.setLayout(new BorderLayout());
            view.setDataInPanel(displayPanel);
            binderController.tradeCard(displayPanel);
        });

        // Sell a binder
        view.setSellBinderBtn(e -> {
            String name = binderView.setBinderName();
            binderController.sellBinder(name);
        });

        // View a specific binder
        view.setViewBinderAction(e -> {
            displayPanel.setLayout(new BorderLayout());
            view.setDataInPanel(displayPanel);
            binderController.displaySingleBinder(displayPanel);
        });
    }

    /**
     * Returns the view associated with this controller.
     *
     * @return the binder menu view
     */
    public BinderMenuView getView() {
        return view;
    }
}
