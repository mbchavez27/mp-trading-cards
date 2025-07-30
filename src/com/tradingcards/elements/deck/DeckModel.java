package com.tradingcards.elements.deck;

import java.util.HashMap;

import com.tradingcards.elements.card.CardModel;

/**
 * Model class representing a Deck of cards.
 * A deck has a name, a type, and contains a collection of unique cards.
 */
public class DeckModel {

    /** The name of the deck. */
    private String deckName;

    /** The type of the deck. */
    private String deckType;

    /** A map of card names to their corresponding CardModel objects in the deck. */
    private HashMap<String, CardModel> cardsInDeck = new HashMap<>();

    /**
     * Sets the name of the deck.
     *
     * @param deckName the name to assign to the deck
     */
    public void setName(String deckName) {
        this.deckName = deckName;
    }

    /**
     * Returns the name of the deck.
     *
     * @return the name of the deck
     */
    public String getName() {
        return this.deckName;
    }

    /**
     * Sets the type of the deck.
     *
     * @param deckType the type to assign to the deck
     */
    public void setType(String deckType) {
        this.deckType = deckType;
    }

    /**
     * Returns the type of the deck.
     *
     * @return the type of the deck
     */
    public String getType() {
        return this.deckType;
    }

    /**
     * Returns the collection of cards in the deck.
     *
     * @return a HashMap of card names to CardModel objects
     */
    public HashMap<String, CardModel> getDeck() {
        return this.cardsInDeck;
    }

    /**
     * Adds a card to the deck if it is not already present.
     *
     * @param card the CardModel object to add
     * @param name the name of the card
     * @return true if the card was successfully added; false if it already exists
     *         in the deck
     */
    public boolean addCardtoDeck(CardModel card, String name) {
        if (!cardsInDeck.containsKey(name)) {
            cardsInDeck.put(name, card);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculates and returns the selling price of the deck.
     * For "Normal" decks, returns -1. For other types, sums the value of all cards.
     *
     * @return the total selling price of the deck, or -1 if deck type is "Normal"
     */
    public double getSellingPrice() {
        if (deckType.equals("Normal")) {
            return -1;
        } else {
            double total = 0.0;

            for (CardModel card : cardsInDeck.values()) {
                double cardTotal = card.getValue() * 1;
                total += cardTotal;
            }

            return total;
        }
    }

}
