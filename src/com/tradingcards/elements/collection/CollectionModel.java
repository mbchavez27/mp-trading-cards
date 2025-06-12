package com.tradingcards.elements.collection;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.deck.DeckModel;
import java.util.HashMap;

/**
 * The {@code CollectionModel} class manages a user's trading card collection,
 * including individual cards, binders, and decks.
 */
public class CollectionModel {

    /**
     * Stores a collection of cards with their associated names as keys.
     */
    private HashMap<String, CardModel> cardCollection = new HashMap<>();

    /**
     * Stores a collection of binders with their associated names as keys.
     */
    private HashMap<String, BinderModel> binderCollection = new HashMap<>();

    /**
     * Stores a collection of decks with their associated names as keys.
     */
    private HashMap<String, DeckModel> deckCollection = new HashMap<>();

    /**
     * Adds or updates a card in the card collection.
     *
     * @param card the {@link CardModel} to be added or updated
     * @param name the key or name associated with the card
     */
    public void setCardCollection(CardModel card, String name) {
        cardCollection.put(name, card);
    }

    /**
     * Returns the entire card collection.
     *
     * @return a {@code HashMap} containing all cards
     */
    public HashMap<String, CardModel> getCardCollection() {
        return cardCollection;
    }

    /**
     * Adds or updates a binder in the binder collection.
     *
     * @param binder the {@link BinderModel} to be added or updated
     * @param name the key or name associated with the binder
     */
    public void setBinderCollection(BinderModel binder, String name) {
        binderCollection.put(name, binder);
    }

    public void removeBinderCollection(String name) {
        binderCollection.remove(name);
    }

    /**
     * Returns the entire binder collection.
     *
     * @return a {@code HashMap} containing all binders
     */
    public HashMap<String, BinderModel> getBinderCollection() {
        return binderCollection;
    }

    /**
     * Adds or updates a deck in the deck collection.
     *
     * @param deck the {@link DeckModel} to be added or updated
     * @param name the key or name associated with the deck
     */
    public void setDeckCollection(DeckModel deck, String name) {
        deckCollection.put(name, deck);
    }

    public void removeDeckCollection(String name) {
        deckCollection.remove(name);
    }

    /**
     * Returns the entire deck collection.
     *
     * @return a {@code HashMap} containing all decks
     */
    public HashMap<String, DeckModel> getDeckCollection() {
        return deckCollection;
    }

}
