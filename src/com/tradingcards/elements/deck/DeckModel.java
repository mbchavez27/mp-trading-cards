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

    public boolean setDeckCollection(CardModel card, String name){
        if (cardsInDeck.size() < 10){

            if (!cardsInDeck.containsKey(name)){
                cardsInDeck.put(name, card);
                return true;
            } else {
                System.err.println("Deck already contains specified card");
                return false;
            }
        } else {
            System.err.println("Deck is already full");
            return false;
        }
    }
}
