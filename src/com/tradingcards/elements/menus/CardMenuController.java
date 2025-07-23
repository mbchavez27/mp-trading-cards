package com.tradingcards.elements.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import com.tradingcards.elements.card.CardController;

public class CardMenuController {

    private final CardController cardController;
    private final CardMenuView view;

    public CardMenuController(CardMenuView view, CardController cardController) {
        this.cardController = cardController;
        this.view = view;
    }

    public void start() {
        view.setVisible(true);
        view.setBackAction(e -> view.dispose());

        view.setDisplayCardAction(e->{

        });

        view.setDisplayCollectionAction(e->{
            view.setVisible(false);
            cardController.displayCollection();
            view.setVisible(true);
        });

        view.setModifyQuantityAction(e->{

        });
    }


    public CardMenuView getView() {
        return view;
    }
}
