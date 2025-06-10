package com.tradingcards.elements.collection;

import com.tradingcards.elements.card.CardModel;
import java.util.HashMap;

public class CollectionModel {

    private static HashMap<String, CardModel> collection = new HashMap<>();

    public static void setCollection(CardModel card, String name) {
        collection.put(name, card);
    }

    public static HashMap<String, CardModel> getCollection() {
        return collection;
    }
}
