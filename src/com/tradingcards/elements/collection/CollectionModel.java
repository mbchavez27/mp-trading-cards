package com.tradingcards.elements.collection;

import com.tradingcards.elements.card.CardModel;
import java.util.HashMap;

public class CollectionModel {

    private HashMap<String, CardModel> cardCollection = new HashMap<>();

    public void setCardCollection(CardModel card, String name) {
        cardCollection.put(name, card);
    }

    public HashMap<String, CardModel> getCardCollection() {
        return cardCollection;
    }
}
