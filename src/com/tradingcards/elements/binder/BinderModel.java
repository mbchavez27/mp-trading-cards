package com.tradingcards.elements.binder;

import com.tradingcards.elements.card.CardModel;
import java.util.HashMap;

/**
 * The {@code BinderModel} class represents a binder that holds a collection of
 * trading cards. It maintains the name of the binder and a list of cards stored
 * in it.
 */
public class BinderModel {

    /**
     * The name of the binder.
     */
    private String binderName;

    /**
     * A map of card names to {@link CardModel} instances stored in this binder.
     */
    private HashMap<String, CardModel> cardsInBinder = new HashMap<>();

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

    // first of all these should be in the controller
    // public void deleteBinder(HashMap<String, CardModel> collection) {
    //     for (int i = 0; i < cardsInBinder; i++) {
    //         collection.put(cards[i].getName(), cards[i]);
    //         cards[i] = null;
    //     }
    // }
    // public void addCard(CardModel card) {
    //     // Not sure if addCard should handle removal from collection as well
    //     if (cardsInBinder != 20) {
    //         cards[cardsInBinder] = card;
    //         this.cardsInBinder += 1;
    //     }
    // }
    // public void removeCard(CardModel card) {
    //     if (cardsInBinder != 0) {
    //         // Incomplete, does not remove anything from cards array yet
    //         // Should this insert the card to the collection through parameter?
    //         this.cardsInBinder -= 1;
    //     }
    // }
    // public void tradeCard(CardModel cardInDeck, CardModel cardOutsideDeck) {
    // }
}
