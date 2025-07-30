package com.tradingcards.elements.binder.types;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;

public class NonCuratedBinder extends BinderModel {

    public NonCuratedBinder(String binderType) {
        super(binderType);
    }

    @Override
    public boolean insertInBinder(CardModel card, String name) {
        cardsInBinder.put(name, card);
        return true;
    }

    @Override
    public double getSellingPrice() {
        return -1;
    }
}
