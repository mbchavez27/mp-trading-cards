package com.tradingcards.elements.card;

import com.tradingcards.elements.collection.CollectionModel;
import java.util.HashMap;

public class CardController {

    private CardModel model;
    private CardView view;

    public CardController(CardModel model, CardView view) {
        this.model = model;
        this.view = view;
    }

    public void addCard() {
        //Instantsiate Card Object
        CardModel card = new CardModel();

        //Get from View
        String name = view.setCardName();
        card.setName(name);

        String rarity = view.setCardRarity();
        card.setRarity(rarity);

        if (card.getRarity().equals("Rare") || card.getRarity().equals("Legendary")) {
            String variant = view.setCardVariant();
            card.setVariant(variant);
            double value = view.setCardValue();
            card.setValue(card.calculateValue(value, variant));
        } else {
            double value = view.setCardValue();
            card.setValue(value);
        }

        card.setQuantity(1);

        CollectionModel.setCollection(card, name);
    }

    public void displayCard() {
        HashMap<String, CardModel> collection = CollectionModel.getCollection();

        if (!collection.isEmpty()) {
            view.displayCard(collection, view.setCardName());
        } else {
            System.err.println("No Cards yet...");
        }
    }

    public void displayCollection() {
        HashMap<String, CardModel> collection = CollectionModel.getCollection();

        if (!collection.isEmpty()) {
            view.displayCollection(collection);
        } else {
            System.err.println("No Cards yet...");
        }
    }

}
