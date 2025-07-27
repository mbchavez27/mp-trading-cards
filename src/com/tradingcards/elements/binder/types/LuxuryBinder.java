package com.tradingcards.elements.binder.types;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.binder.BinderView;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.menus.menuUtils.DialogUtil;

public class LuxuryBinder extends BinderModel {
    BinderView view = new BinderView();

    public LuxuryBinder(String binderType) {
        super(binderType);
    }

    @Override
    public boolean insertInBinder(CardModel card, String name) {
        if (card.getVariant() == null) {
            return false;
        } else if (!card.getVariant().equals("Normal")) {
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
        Double newPrice = view.setBinderPrice();

        if (newPrice == null || newPrice < total) {
            DialogUtil.showError(null, "New price is lower than current price", "Error");
            return total;
        } else {
            DialogUtil.showInfo(null, "New price is now " + newPrice, "Success");
            return newPrice;
        }
    }
}