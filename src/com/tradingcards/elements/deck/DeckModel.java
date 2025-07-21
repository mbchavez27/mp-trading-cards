package com.tradingcards.elements.deck;

import com.tradingcards.elements.card.CardModel;
import java.util.HashMap;

/**
 * Model class representing a Deck of cards.
 * A deck has a name and contains a collection of unique cards.
 */
public class DeckModel {

    /** The name of the deck. */
    private String deckName;

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
}
