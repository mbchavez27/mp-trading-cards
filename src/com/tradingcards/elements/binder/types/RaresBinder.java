package com.tradingcards.elements.binder.types;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;

public class RaresBinder extends BinderModel {

    public RaresBinder(String binderType) {
        super(binderType);
    }

    @Override
    public boolean insertInBinder(CardModel card, String name) {
        if (card.getRarity().equals("Rare") || card.getRarity().equals("Legendary")) {
            cardsInBinder.put(name, card);
            return true;
        }
        return false;
    }

    @Override
    public double getSellingPrice() {
        double total = 0.0;

        for (CardModel card : cardsInBinder.values()) {
            if (card.getQuantity() > 0) {
                double cardTotal = card.getValue() * card.getQuantity();
                total += cardTotal;
            }
        }

        return total;
    }
}
