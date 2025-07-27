package com.tradingcards.elements.binder.types;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;

public class PauperBinder extends BinderModel {

    public PauperBinder(String binderType) {
        super(binderType);
    }

    @Override
    public boolean insertInBinder(CardModel card, String name) {
        if (card.getRarity().equals("Common") || card.getRarity().equals("Uncommon")) {
            cardsInBinder.put(name, card);
            return true;
        }
        return false; // Invalid card for Pauper
    }

    @Override
    public double getSellingPrice() {
        return cardsInBinder.values().stream().mapToDouble(CardModel::getValue).sum();
    }
}
