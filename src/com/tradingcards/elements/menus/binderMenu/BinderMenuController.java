package com.tradingcards.elements.menus.binderMenu;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.tradingcards.MainFrame;
import com.tradingcards.elements.binder.BinderController;
import com.tradingcards.elements.binder.BinderView;
import com.tradingcards.elements.menus.mainMenu.MainMenuView;

/**
 * Menu class responsible for displaying options and handling user interactions
 * for binder-related operations.
 */
public class BinderMenuController {

    /** Controller for handling binder logic. */
    private final BinderController binderController;
    private final BinderView binderView;
    private final BinderMenuView view;
    private final MainFrame mainFrame;
    private final MainMenuView mainMenuView;

    public BinderMenuController(BinderMenuView view, BinderController binderController, BinderView binderView,
            MainMenuView mainMenuView, MainFrame mainFrame) {
        this.binderController = binderController;
        this.binderView = binderView;
        this.view = view;
        this.mainMenuView = mainMenuView;
        this.mainFrame = mainFrame;
    }

    public void start() {
        view.setDataInPanel(binderController.displayBinders());
        JPanel displayPanel = new JPanel();

        view.setBackAction(e -> {
            mainMenuView.updateMoneyLabel();
            mainMenuView.updateButtonStatus();
            mainFrame.showPanel("mainMenu");
        });

        view.setDeleteBinderAction(e -> {
            // JPanel removeBinderPanel = new JPanel();
            displayPanel.setLayout(new BorderLayout());

            view.setDataInPanel(displayPanel);

            binderController.removeBinder(displayPanel);
        });

        view.setAddCardToBinderAction(e -> {
            displayPanel.setLayout(new BorderLayout());

            view.setDataInPanel(displayPanel);
            binderController.addCard(displayPanel);
        });

        view.setRemoveCardFromBinderAction(e -> {
            displayPanel.setLayout(new BorderLayout());

            view.setDataInPanel(displayPanel);

            binderController.removeCard(displayPanel);
        });

        view.setTradeCardAction(e -> {
            // JPanel tradingPanel = new JPanel();
            displayPanel.setLayout(new BorderLayout());

            view.setDataInPanel(displayPanel);

            binderController.tradeCard(displayPanel);
            // binderController.tradeCard();
        });

        view.setSellBinderBtn(e -> {
            String name = binderView.setBinderName();
            binderController.sellBinder(name);
        });

        view.setViewBinderAction(e -> {
            displayPanel.setLayout(new BorderLayout());

            view.setDataInPanel(displayPanel);

            binderController.displaySingleBinder(displayPanel);
        });

    }

    public BinderMenuView getView() {
        return view;
    }

}
