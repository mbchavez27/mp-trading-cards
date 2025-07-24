package com.tradingcards.elements.menus.cardMenu;

import com.tradingcards.MainFrame;
import com.tradingcards.elements.card.CardController;

public class CardMenuController {

    private final CardController cardController;
    private final CardMenuView view;
    private final MainFrame mainFrame;

    public CardMenuController(CardMenuView view, CardController cardController, MainFrame mainFrame) {
        this.cardController = cardController;
        this.view = view;
        this.mainFrame = mainFrame;
    }

    public void start() {

        view.setBackAction(e -> {
            mainFrame.showPanel("mainMenu");
        });

        view.setDisplayCardAction(e -> {
            //first displays JPanel Collection
            view.setDataInPanel(cardController.displayCollection());
            //gets a JPanel with the information of only 1 card
            view.setDataInPanel(cardController.displayCard());
        });

        view.setDisplayCollectionAction(e -> {
            //gets a JPanel
            view.setDataInPanel(cardController.displayCollection());
        });
        view.setModifyQuantityAction(e -> {
            int newQuantity = cardController.modifyCardQuantity();
            if (newQuantity != -1) {
                view.setDataInPanel(cardController.displayCollection());
            }
        });
    }

    public CardMenuView getView() {
        return view;
    }
}
