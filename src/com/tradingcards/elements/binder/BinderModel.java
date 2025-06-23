package com.tradingcards.elements.binder;

import com.tradingcards.elements.card.CardModel;
import java.util.HashMap;

/**
 * The {@code BinderModel} class represents a binder that holds a collection of
 * trading cards. It maintains the name of the binder and a list of cards stored
 * in it.
 */
public class BinderModel {

    /**
     * The name of the binder.
     */
    private String binderName;

    /**
     * A map of card names to {@link CardModel} instances stored in this binder.
     */
    private HashMap<String, CardModel> cardsInBinder = new HashMap<>();

    /**
     * Sets the name of the binder.
     *
     * @param binderName the name to assign to the binder
     */
    public void setName(String binderName) {
        this.binderName = binderName;
    }

    /**
     * Retrieves the name of the binder.
     *
     * @return the name of the binder
     */
    public String getName() {
        return this.binderName;
    }

    public HashMap<String, CardModel> getBinder(){
        return this.cardsInBinder;
    }

    public void insertInBinder(CardModel card, String name){
        cardsInBinder.put(name, card);
    }
}
