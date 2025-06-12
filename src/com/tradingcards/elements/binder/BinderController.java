package com.tradingcards.elements.binder;

import com.tradingcards.elements.collection.CollectionModel;

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

    /**
     * Removes a binder from the shared collection based on user input.
     */
    public void removeBinder() {
        String name = view.setBinderName();

        sharedCollection.removeBinderCollection(name);
    }
}
