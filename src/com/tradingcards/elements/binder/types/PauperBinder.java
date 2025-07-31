package com.tradingcards.elements.binder.types;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.menus.menuUtils.DialogUtil;

/**
 * The {@code PauperBinder} class represents a specialized binder that only
 * accepts cards of rarity "Common" or "Uncommon".
 * <p>
 * It allows calculation of the total selling price based on the value and
 * quantity of eligible cards.
 */
public class PauperBinder extends BinderModel {

    /**
     * Constructs a {@code PauperBinder} with the specified binder type.
     *
     * @param binderType the type of the binder
     */
    public PauperBinder(String binderType) {
        super(binderType);
    }

    /**
     * Inserts a card into the binder if it is of rarity "Common" or "Uncommon".
     *
     * @param card the {@code CardModel} to insert
     * @param name the name or identifier used as a key in the binder
     * @return {@code true} if the card was inserted, {@code false} if the rarity is
     *         not allowed
     */
    @Override
    public boolean insertInBinder(CardModel card, String name) {
        if (card.getRarity().equals("Common") || card.getRarity().equals("Uncommon")) {
            cardsInBinder.put(name, card);
            return true;
        }
        return false; // Invalid card for Pauper
    }

    /**
     * Calculates the total selling price of all cards in the binder.
     * <p>
     * If the binder is empty, an error dialog is shown and {@code 0.0} is returned.
     *
     * @return the total selling price of the binder contents
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

        return total;
    }
}
