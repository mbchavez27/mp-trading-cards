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

        view.setBackAction(e->{
            mainFrame.showPanel("mainMenu");
        });

        view.setDisplayCardAction(e->{
            cardController.displayCard();
        });

        view.setDisplayCollectionAction(e->{
            view.setDataInPanel(cardController.displayCollection());
        });
        view.setModifyQuantityAction(e->{
            cardController.modifyCardQuantity();
        });
    }


    public CardMenuView getView() {
        return view;
    }
}
