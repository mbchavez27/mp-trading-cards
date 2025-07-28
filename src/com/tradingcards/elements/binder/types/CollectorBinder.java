package com.tradingcards.elements.binder.types;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;

public class CollectorBinder extends BinderModel {

    public CollectorBinder(String binderType) {
        super(binderType);
    }

    @Override
    public boolean insertInBinder(CardModel card, String name) {
        boolean isRareOrLegendary = card.getRarity().equals("Rare") || card.getRarity().equals("Legendary");

        if (card.getVariant() == null)
            return false;

        boolean isSpecialVariant = !card.getVariant().equals("Normal");

        if (isRareOrLegendary && isSpecialVariant) {
            cardsInBinder.put(name, card);
            return true;
        }
        return false;
    }

    @Override
    public double getSellingPrice() {
        return cardsInBinder.values().stream().mapToDouble(CardModel::getValue).sum();
    }
}