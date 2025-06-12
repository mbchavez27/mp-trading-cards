package com.tradingcards.elements.binder;

import com.tradingcards.elements.card.CardModel;
import java.util.HashMap;

public class BinderModel {

    private String binderName;
    private int cardsInBinderQuantity = 0; //have a get set for this variable
    private HashMap<String, CardModel> cardsInBinder = new HashMap<>();

    public void setName(String binderName) {
        this.binderName = binderName;
    }

    public String getName() {
        return this.binderName;
    }

    //first of all these should be in the controller
    // public void deleteBinder(HashMap<String, CardModel> collection) {
    //     for (int i = 0; i < cardsInBinder; i++) {
    //         collection.put(cards[i].getName(), cards[i]);
    //         cards[i] = null;
    //     }
    // }
    // public void addCard(CardModel card) {
    //     //Not sure if addCard should handle removal from collection as well
    //     if (cardsInBinder != 20) {
    //         cards[cardsInBinder] = card;
    //         this.cardsInBinder += 1;
    //     }
    // }
    // public void removeCard(CardModel card) {
    //     if (cardsInBinder != 0) {
    //         //Incomplete, does not remove anything from cards array yet
    //         //Should this insert the card to the collection through parameter?
    //         this.cardsInBinder -= 1;
    //     }
    // }
    // public void tradeCard(CardModel cardInDeck, CardModel cardOutsideDeck) {
    // }
}
