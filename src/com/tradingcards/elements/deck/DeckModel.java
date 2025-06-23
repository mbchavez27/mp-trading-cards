package com.tradingcards.elements.deck;

import com.tradingcards.elements.card.CardModel;
import java.util.HashMap;

public class DeckModel {

    private String deckName;

    private HashMap<String, CardModel> cardsInDeck = new HashMap<>();

    public void setName(String deckName){
        this.deckName = deckName;
    }

    public String getName(){
        return this.deckName;
    }

    public HashMap<String, CardModel> getDeck(){
        return this.cardsInDeck;
    }

    public boolean addCardtoDeck(CardModel card, String name){
        if (!cardsInDeck.containsKey(name)){
            cardsInDeck.put(name, card);
            return true;
        } else {
            return false;
        }

    }
}
