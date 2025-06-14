package com.tradingcards.elements.binder;

import com.tradingcards.elements.card.*;
import com.tradingcards.elements.collection.CollectionModel;

import java.util.HashMap;

/**
 * Controller class for managing binder-related actions within the trading card
 * collection application.
 */
public class BinderController {

    /**
     * Reference to the shared collection containing binders, cards, and decks.
     */
    private CollectionModel sharedCollection;

    /**
     * Model representing the current binder logic or data.
     */
    private BinderModel model;

    /**
     * View responsible for user interaction related to binders.
     */
    private BinderView view;

    /**
     * Constructs a {@code BinderController} with the shared collection, model,
     * and view.
     *
     * @param sharedCollection the central collection of trading card elements
     * @param model the model representing the binder's data and logic
     * @param view the view handling user interaction for binders
     */
    public BinderController(CollectionModel sharedCollection, BinderModel model, BinderView view) {
        this.sharedCollection = sharedCollection;
        this.model = model;
        this.view = view;
    }

    /**
     * Adds a new binder to the shared collection. Prompts the user for a binder
     * name and ensures the name is unique.
     */
    public void addBinder() {
        BinderModel binder = new BinderModel();
        String name;

        do {
            name = view.setBinderName();

            if (sharedCollection.getBinderCollection().containsKey(name)) {
                System.err.println("Binder already exists choose a new name...");
            }

        } while (sharedCollection.getBinderCollection().containsKey(name));

        sharedCollection.setBinderCollection(binder, name);
    }

    public void addCardToBinder(){
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        CardView cardView = new CardView();
        CardModel cardModel = new CardModel();

        String cardToRemove;
        boolean taskDone = false;

        displayBinders();

        String binderName = view.setBinderName();

        if (binderCollection.containsKey(binderName)){
            cardView.displayCollection(collection);

            do {
                cardToRemove = cardView.setCardName();
                if (collection.containsKey(cardToRemove)){
                    if(binderCollection.get(binderName).setBinderCollection(collection.get(cardToRemove), cardToRemove)){
                        collection.remove(cardToRemove);
                        System.out.println("Successfully transferred card into binder");
                        taskDone = true;
                    };

                }
            } while (!collection.containsKey(cardToRemove) && !taskDone);
        } else {
            System.err.println("No Card with given name existing in Collection");
        }


    }

    public void displayBinders(){
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();

        if (!binderCollection.isEmpty()){
            view.displayBinders(binderCollection);
        } else {
            System.err.println("No Binders made yet");
        }
    }

    public void removeCard(){

    }

    public void tradeCard(){

    }

    /**
     * Removes a binder from the shared collection based on user input.
     */
    public void removeBinder() {
        String name = view.setBinderName();

        sharedCollection.removeBinderCollection(name);
    }
}
