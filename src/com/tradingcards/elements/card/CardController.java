package com.tradingcards.elements.card;

import java.util.ArrayList;

public class CardController {

    private ArrayList<CardModel> cards = new ArrayList<>();
    private CardModel model = new CardModel();
    private CardView view = new CardView();

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
        cards.add(card);
    }

    public void displayCard() {
        System.out.print("Choose a card:");
        if (!cards.isEmpty()) {
            view.displayCard(cards);
        } else {
            System.err.println("No Cards yet...");
        }
    }
}
