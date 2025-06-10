package com.tradingcards.elements.card;

import java.util.HashMap;

public class CardController {

    private HashMap<String, CardModel> collection = new HashMap<>();
    private CardModel model;
    private CardView view;

    public CardController(CardModel model, CardView view) {
        this.model = model;
        this.view = view;
    }

    public void addCard() {
        String name = view.setCardName();
        String rarity = view.setCardRarity();
        String variant = view.setCardVariant();
        double value = view.setCardValue();

        CardModel card = new CardModel();
        card.setName(name);
        card.setRarity(rarity);
        card.setVariant(variant);
        card.setValue(value);
        card.setQuantity(1);
        collection.put(name, card);
    }

    public void displayCard() {
        if (!collection.isEmpty()) {
            view.displayCard(collection, view.setCardName());
        } else {
            System.err.println("No Cards yet...");
        }
    }

    public void displayCollection() {
        if (!collection.isEmpty()) {
            view.displayCollection(collection);
        } else {
            System.err.println("No Cards yet...");
        }
    }
}
