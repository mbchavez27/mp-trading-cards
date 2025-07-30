package com.tradingcards.elements.binder.types;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;

/**
 * The {@code NonCuratedBinder} class represents a binder type that accepts all
 * cards without any filtering based on rarity or variant.
 * <p>
 * This binder is a simple container for cards and does not compute a selling
 * price, returning -1 to indicate selling is not applicable.
 */
public class NonCuratedBinder extends BinderModel {

    /**
     * Constructs a {@code NonCuratedBinder} with the specified binder type.
     *
     * @param binderType the type of the binder
     */
    public NonCuratedBinder(String binderType) {
        super(binderType);
    }

    /**
     * Inserts a card into the binder without any condition.
     *
     * @param card the {@code CardModel} to insert
     * @param name the name or identifier used as a key in the binder
     * @return {@code true} always, since this binder accepts any card
     */
    @Override
    public boolean insertInBinder(CardModel card, String name) {
        cardsInBinder.put(name, card);
        return true;
    }

    /**
     * Returns the selling price of the binder.
     * <p>
     * This implementation always returns -1, indicating that the binder
     * is not sellable or does not support pricing.
     *
     * @return {@code -1}, indicating the binder cannot be sold
     */
    @Override
    public double getSellingPrice() {
        return -1;
    }
}
