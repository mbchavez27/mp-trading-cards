package com.tradingcards.elements.deck;

import com.tradingcards.elements.collection.CollectionModel;

public class DeckController {

    private CollectionModel sharedCollection;
    private DeckModel model;
    private DeckView view;

    public DeckController(CollectionModel sharedCollection, DeckModel model, DeckView view) {
        this.sharedCollection = sharedCollection;
        this.model = model;
        this.view = view;
    }

    public void addDeck() {
        DeckModel deck = new DeckModel();

        String name = view.setDeckName();

        sharedCollection.setDeckCollection(deck, name);
    }

    public void removeDeck() {
        String name = view.setDeckName();

        sharedCollection.removeDeckCollection(name);
    }
}
