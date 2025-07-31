package com.tradingcards.elements.binder.types;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.binder.BinderView;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.menus.menuUtils.DialogUtil;

/**
 * The {@code LuxuryBinder} class is a specialized binder type that only accepts
 * cards with non-normal variants. It also provides a custom implementation of
 * the selling price logic with user input and validation.
 */
public class LuxuryBinder extends BinderModel {
    BinderView view = new BinderView();

    /**
     * Constructs a {@code LuxuryBinder} with the specified binder type.
     *
     * @param binderType the type of the binder (e.g., "Luxury")
     */
    public LuxuryBinder(String binderType) {
        super(binderType);
    }

    /**
     * Inserts a card into the luxury binder only if it has a non-"Normal" variant.
     *
     * @param card the {@code CardModel} to insert
     * @param name the name or key under which the card is stored
     * @return {@code true} if the card was inserted; {@code false} otherwise
     */
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

    /**
     * Calculates and returns the selling price of the binder.
     * <p>
     * The method first checks if the binder is empty. If it is, an error dialog is
     * shown.
     * Then it computes the total price of all cards based on their value and
     * quantity.
     * The user is prompted to input a new selling price.
     * <ul>
     * <li>If the new price is {@code null} or less than the current total, an error
     * is shown,
     * and the method returns {@code total * 1.10}.</li>
     * <li>If the new price is valid and greater than or equal to the total, a
     * success dialog is shown,
     * and the method returns {@code newPrice * 1.10}.</li>
     * </ul>
     *
     * @return the final selling price after validation and markup
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

        Double newPrice = view.setBinderPrice();

        if (newPrice == null || newPrice < total) {
            DialogUtil.showError(null, "New price is lower than current price", "Error");
            return total * 1.10;
        } else {
            DialogUtil.showInfo(null, "New price is now " + newPrice, "Success");
            return newPrice * 1.10;
        }
    }
}
