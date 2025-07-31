package com.tradingcards.elements.binder;

import java.util.HashMap;

import com.tradingcards.elements.card.CardModel;

/**
 * The {@code BinderModel} class represents a binder that holds a collection of
 * trading cards. It maintains the name of the binder and a list of cards stored
 * in it.
 */
public abstract class BinderModel {

    /**
     * The name of the binder.
     */
    protected String binderName;

    /**
     * The type of the binder.
     */
    protected String binderType;

    /**
     * A map of card names to {@link CardModel} instances stored in this binder.
     */
    protected HashMap<String, CardModel> cardsInBinder = new HashMap<>();

    /**
     * Constructs a new BinderModel with the specified binder type.
     *
     * @param binderType the type of the binder (e.g., "Non-curated", "Pauper",
     *                   "Rares", etc.)
     */
    public BinderModel(String binderType) {
        this.binderType = binderType;
    }

    /**
     * Sets the name of the binder.
     *
     * @param binderName the name to assign to the binder
     */
    public void setName(String binderName) {
        this.binderName = binderName;
    }

    /**
     * Retrieves the name of the binder.
     *
     * @return the name of the binder
     */
    public String getName() {
        return this.binderName;
    }

    /**
     * Sets the type of the binder.
     *
     * @param binderType the type of the binder (e.g., "Non-curated", "Pauper",
     *                   etc.)
     */
    public void setType(String binderType) {
        this.binderType = binderType;
    }

    /**
     * Retrieves the type of the binder.
     *
     * @return the type of the binder
     */
    public String getType() {
        return this.binderType;
    }

    /**
     * Retrieves the collection of cards stored in the binder.
     *
     * @return a {@code HashMap} containing card names mapped to {@link CardModel}
     *         instances
     */
    public HashMap<String, CardModel> getBinder() {
        return this.cardsInBinder;
    }

    /**
     * Inserts a card into the binder.
     * 
     * @param card the {@link CardModel} object to add
     * @param name the name of the card to use as the key in the binder
     */
    public abstract boolean insertInBinder(CardModel card, String name);

    /**
     * Determines selling price of binder
     */
    public abstract double getSellingPrice();
}
