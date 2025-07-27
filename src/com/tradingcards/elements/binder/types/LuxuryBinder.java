package com.tradingcards.elements.binder.types;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;

public class LuxuryBinder extends BinderModel {
    private double customPrice = -1;

    public LuxuryBinder(String binderType) {
        super(binderType);
    }

    @Override
    public boolean insertInBinder(CardModel card, String name) {
        if (card.getVariant() == null){
            return false;
        } else if (!card.getVariant().equals("Normal")) {
            cardsInBinder.put(name, card);
            return true;
        }
        return false;
    }

    public boolean setCustomPrice(double price) {
        return true;
    }

    @Override
    public double getSellingPrice() {
        return 1;
    }
}
