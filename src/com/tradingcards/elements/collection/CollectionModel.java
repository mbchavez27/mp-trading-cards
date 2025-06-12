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

    /**
     * Removes a binder from the binder collection by its name.
     *
     * @param name the name of the binder to be removed
     *
     * If the binder is found and successfully removed, a confirmation message
     * is printed. Otherwise, a message indicating the binder was not found is
     * displayed.
     */
    public void removeBinderCollection(String name) {
        BinderModel removed = binderCollection.remove(name);
        if (removed != null) {
            System.out.println(name + " removed successfully...");
        } else {
            System.out.println(name + " not found. Removal unsuccessful...");
        }
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

    /**
     * Removes a deck from the deck collection by its name.
     *
     * @param name the name (key) of the deck to be removed
     *
     * Prints a message indicating whether the deck was successfully removed or
     * if no deck with the given name exists in the collection.
     */
    public void removeDeckCollection(String name) {
        DeckModel removed = deckCollection.remove(name);
        if (removed != null) {
            System.out.println(name + " removed successfully...");
        } else {
            System.out.println(name + " not found. Removal unsuccessful...");
        }
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
