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

    public void removeCard(){
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        HashMap<String, CardModel> binder;
        String cardToRemove;
        boolean taskDone = false;

        CardView cardView = new CardView();
        displayBinders();

        String binderName = view.setBinderName();
        if (binderCollection.containsKey(binderName)){
            binder = binderCollection.get(binderName).getBinder();

            if (binder.isEmpty()){
                System.out.println("Binder is currently empty");
                System.out.println("Add cards to the Binder first");
            } else {
                displayBinderContent(binder);
                do {
                    System.out.println("Indicate card to be deleted");
                    cardToRemove = cardView.setCardName();
                    if (binder.containsKey(cardToRemove)){
                        //TODO: Current code does not handle identical cards being added
                        collection.put(cardToRemove, binder.get(cardToRemove));
                        binder.remove(cardToRemove);
                        System.out.println("Sucessfully transferred Card into Collection");
                        taskDone = true;
                    } else {
                        System.err.println("No Card with given name exists in Binder");
                        System.err.println("Please re-input Card name");
                    }

                } while (!binder.containsKey(cardToRemove) && !taskDone);
            }
        } else {
            System.err.println("No Binder with given name exists");
        }
    }

    public void addCard(){
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        CardView cardView = new CardView();
        BinderModel binder;

        String cardToRemove;
        boolean taskDone = false;

        displayBinders();

        String binderName = view.setBinderName();

        if (binderCollection.isEmpty()){
            System.out.println("No cards in collection yet");
            System.out.println("Input cards in collection first");
        } else {
            if (binderCollection.containsKey(binderName)){
                binder = binderCollection.get(binderName);
                cardView.displayCollection(collection);

                do {
                    cardToRemove = cardView.setCardName();
                    if (collection.containsKey(cardToRemove)){
                        if(binder.setBinderCollection(collection.get(cardToRemove), cardToRemove)){
                            collection.remove(cardToRemove);
                            System.out.println("Successfully transferred card into binder");
                            taskDone = true;
                        } else {
                            System.err.println("Binder is already full");
                        }

                    } else {
                        System.err.println("No Card with given name exists in Collection");
                        System.err.println("Please re-input Card name");
                    }
                } while (!collection.containsKey(cardToRemove) && !taskDone);
            } else {
                System.err.println("No Binder with given name exists");
            }
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

    public void displaySingleBinder(){
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        displayBinders();
        System.out.println("Indicate binder to view");
        String binderName = view.setBinderName();

        if (binderCollection.containsKey(binderName)){
            displayBinderContent(binderCollection.get(binderName).getBinder());
        } else {
            System.err.println("No Binder with given name exists");
        }
    }

    public void displayBinderContent(HashMap<String, CardModel> binder){

        if (!binder.isEmpty()){
            view.displayBinderContent(binder);
        } else {
            System.err.println("No Cards in Binder");
        }
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
