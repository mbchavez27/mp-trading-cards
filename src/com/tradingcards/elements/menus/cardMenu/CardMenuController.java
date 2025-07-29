package com.tradingcards.elements.menus.cardMenu;

import com.tradingcards.MainFrame;
import com.tradingcards.elements.card.CardController;
import com.tradingcards.elements.card.CardView;
import com.tradingcards.elements.menus.mainMenu.MainMenuView;

public class CardMenuController {

    private final CardController cardController;
    private final CardView cardView;
    private final CardMenuView view;
    private final MainFrame mainFrame;
    private final MainMenuView mainMenuView;

    public CardMenuController(CardMenuView view, CardController cardController, CardView cardView,
            MainMenuView mainMenuView,
            MainFrame mainFrame) {
        this.cardController = cardController;
        this.cardView = cardView;
        this.view = view;
        this.mainMenuView = mainMenuView;
        this.mainFrame = mainFrame;
    }

    public void start() {
        // Initial
        view.setDataInPanel(cardController.displayCollection());

        view.setBackAction(e -> {
            mainMenuView.updateMoneyLabel();
            mainMenuView.updateButtonStatus();
            mainFrame.showPanel("mainMenu");
        });

        view.setDisplayCardAction(e -> {
            // first displays JPanel Collection
            view.setDataInPanel(cardController.displayCollection());
            // gets a JPanel with the information of only 1 card
            view.setDataInPanel(cardController.displayCard());
        });

        view.setDisplayCollectionAction(e -> {
            // gets a JPanel
            view.setDataInPanel(cardController.displayCollection());
        });

        view.setModifyQuantityAction(e -> {
            view.setDataInPanel(cardController.displayCollection(0));
            int newQuantity = cardController.modifyCardQuantity();
            if (newQuantity != -1) {
                view.setDataInPanel(cardController.displayCollection());
            }
        });

        view.setSellCardAction(e -> {
            view.setDataInPanel(cardController.displayCollection());
            boolean cardSold = cardController.sellCard(cardView.setCardName());

            if (cardSold) {
                view.setDataInPanel(cardController.displayCollection());
            }
        });
    }

    public CardMenuView getView() {
        return view;
    }
}
