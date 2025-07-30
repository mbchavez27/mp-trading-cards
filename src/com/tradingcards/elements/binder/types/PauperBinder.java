package com.tradingcards.elements.binder.types;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.menus.menuUtils.DialogUtil;

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
        double total = 0.0;

        if (cardsInBinder.isEmpty()) {
            DialogUtil.showError(null, "Cannot sell an empty binder", "Error");
            return 0.0;
        }

        for (CardModel card : cardsInBinder.values()) {
            if (card.getQuantity() > 0) {
                double cardTotal = card.getValue() * card.getQuantity();
                total += cardTotal;
            }
        }

        return total;
    }
}