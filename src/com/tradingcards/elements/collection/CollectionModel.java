package com.tradingcards.elements.collection;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;
import java.util.HashMap;

public class CollectionModel {

    private HashMap<String, CardModel> cardCollection = new HashMap<>();

    private HashMap<String, BinderModel> binderCollection = new HashMap<>();

    public void setCardCollection(CardModel card, String name) {
        cardCollection.put(name, card);
    }

    public HashMap<String, CardModel> getCardCollection() {
        return cardCollection;
    }

    public void setBinderCollection(CardModel card, String name) {
        cardCollection.put(name, card);
    }

    public HashMap<String, BinderModel> getBinderCollection() {
        return binderCollection;
    }

}
