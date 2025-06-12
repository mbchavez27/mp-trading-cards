package com.tradingcards.elements.collection;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.deck.DeckModel;
import java.util.HashMap;

public class CollectionModel {

    private HashMap<String, CardModel> cardCollection = new HashMap<>();

    private HashMap<String, BinderModel> binderCollection = new HashMap<>();

    private HashMap<String, DeckModel> deckCollection = new HashMap<>();

    public void setCardCollection(CardModel card, String name) {
        cardCollection.put(name, card);
    }

    public HashMap<String, CardModel> getCardCollection() {
        return cardCollection;
    }

    public void setBinderCollection(BinderModel binder, String name) {
        binderCollection.put(name, binder);
    }

    public HashMap<String, BinderModel> getBinderCollection() {
        return binderCollection;
    }

    public void setDeckCollection(DeckModel deck, String name) {
        deckCollection.put(name, deck);
    }

    public HashMap<String, DeckModel> getDeckCollection() {
        return deckCollection;
    }

}
