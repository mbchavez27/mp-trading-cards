package com.tradingcards.elements.binder.types;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.menus.menuUtils.DialogUtil;

/**
 * {@code RaresBinder} is a type of {@code BinderModel} that only accepts cards
 * with rarity "Rare" or "Legendary". It adds a 10% premium when calculating
 * the selling price of the binder.
 */
public class RaresBinder extends BinderModel {

    /**
     * Constructs a {@code RaresBinder} with the specified binder type.
     *
     * @param binderType the type of the binder
     */
    public RaresBinder(String binderType) {
        super(binderType);
    }

    /**
     * Inserts a card into the binder only if its rarity is "Rare" or "Legendary".
     *
     * @param card the card to insert
     * @param name the name to associate with the card in the binder
     * @return {@code true} if the card was inserted; {@code false} otherwise
     */
    @Override
    public boolean insertInBinder(CardModel card, String name) {
        if (card.getRarity().equals("Rare") || card.getRarity().equals("Legendary")) {
            cardsInBinder.put(name, card);
            return true;
        }
        return false;
    }

    /**
     * Calculates the total selling price of the binder contents with a 10% premium.
     * Shows an error dialog if the binder is empty.
     *
     * @return the selling price, or 0.0 if the binder is empty
     */
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

        return total * 1.10;
    }
}
