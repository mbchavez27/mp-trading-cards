package com.tradingcards.elements.binder.types;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;

/**
 * Represents a special type of binder designed for collectors, allowing only
 * rare or legendary cards with non-normal variants to be inserted.
 * 
 * <p>
 * This class extends {@link BinderModel} and overrides the insertion logic
 * to enforce stricter rules based on card rarity and variant.
 * </p>
 */
public class CollectorBinder extends BinderModel {

    /**
     * Constructs a new {@code CollectorBinder} with the specified binder type.
     *
     * @param binderType the type or label of the binder
     */
    public CollectorBinder(String binderType) {
        super(binderType);
    }

    /**
     * Inserts a card into the collector binder if it meets specific criteria.
     * 
     * <p>
     * Only cards that are either {@code "Rare"} or {@code "Legendary"} in rarity
     * and have a variant other than {@code "Normal"} are allowed to be inserted.
     * </p>
     *
     * @param card the {@link CardModel} to insert
     * @param name the unique identifier or name to store the card under
     * @return {@code true} if the card was inserted; {@code false} otherwise
     */
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

    /**
     * Returns the selling price of the collector binder.
     * 
     * <p>
     * Collector binders do not have a defined selling price, so this method
     * always returns {@code -1}.
     * </p>
     *
     * @return {@code -1} to indicate no selling price
     */
    @Override
    public double getSellingPrice() {
        return -1;
    }
}