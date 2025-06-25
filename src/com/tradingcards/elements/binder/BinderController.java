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
     * View responsible for user interaction related to binders.
     */
    private BinderView view;

    /**
     * Constructs a {@code BinderController} with the shared collection, model,
     * and view.
     *
     * @param sharedCollection the central collection of trading card elements
     * @param view             the view handling user interaction for binders
     */
    public BinderController(CollectionModel sharedCollection, BinderView view) {
        this.sharedCollection = sharedCollection;
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
                view.displayErrorNewLine("Binder already exists choose a new name...");
            }

        } while (sharedCollection.getBinderCollection().containsKey(name));

        sharedCollection.setBinderCollection(binder, name);
    }

    public void removeCard() {
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        HashMap<String, CardModel> binder;
        String cardName;
        boolean taskDone = false;

        CardView cardView = new CardView();
        displayBinders();

        String binderName = view.setBinderName();
        if (binderCollection.containsKey(binderName)) {
            binder = binderCollection.get(binderName).getBinder();

            if (binder.isEmpty()) {
                view.displayMessageNewLine("Binder is currently empty");
                view.displayMessageNewLine("Add cards to the Binder first");
            } else {
                displayBinderContent(binder);
                do {
                    view.displayMessageNewLine("Indicate card to be deleted");
                    cardName = cardView.setCardName();
                    if (binder.containsKey(cardName)) {
                        collection.get(cardName).setQuantity(collection.get(cardName).getQuantity() + 1);
                        if (binder.get(cardName).getQuantity() > 1) {
                            binder.get(cardName).setQuantity(binder.get(cardName).getQuantity() - 1);
                        } else {
                            binder.remove(cardName);
                        }
                        view.displayMessageNewLine("Successfully transferred Card into Collection");
                        System.out.println();
                        taskDone = true;
                    } else {
                        view.displayErrorNewLine("No Card with given name exists in Binder");
                        view.displayErrorNewLine("Please re-input Card name");
                    }
                } while (!binder.containsKey(cardName) && !taskDone);
            }
        } else {
            view.displayErrorNewLine("No Binder with given name exists");
        }
    }

    public void addCard() {
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        CardView cardView = new CardView();
        BinderModel binder;

        String cardName;
        boolean taskDone = false;
        CardModel cardInCollection;
        CardModel cardInBinder;
        CardModel cardCopy;

        displayBinders();

        String binderName = view.setBinderName();

        if (binderCollection.isEmpty()) {
            view.displayMessageNewLine("No cards in collection yet");
            view.displayMessageNewLine("Input cards in collection first");
        } else {
            if (binderCollection.containsKey(binderName)) {
                binder = binderCollection.get(binderName);
                cardView.displayCollection(collection);

                do {
                    cardName = cardView.setCardName();
                    // checks if the collection has the card
                    if (collection.containsKey(cardName)) {
                        // checks if the collection has a positive number of card copies
                        cardInCollection = collection.get(cardName);
                        if (cardInCollection.getQuantity() > 0) {

                            // checks if binder can accommodate new card;
                            if (binder.getBinder().size() < 20) {

                                // checks if binder already contains the specified card
                                if (binder.getBinder().containsKey(cardName)) {
                                    cardInBinder = binder.getBinder().get(cardName);
                                    cardInBinder.setQuantity(cardInBinder.getQuantity() + 1);
                                } else {
                                    // create a new card object to store details
                                    cardCopy = createCardCopy(cardInCollection);
                                    binder.insertInBinder(cardCopy, cardName);
                                }
                                cardInCollection.setQuantity(cardInCollection.getQuantity() - 1);
                                view.displayMessageNewLine("Successfully transferred card into binder");
                                taskDone = true;

                            } else {
                                view.displayErrorNewLine("Binder is already full");
                            }
                        } else {
                            view.displayErrorNewLine("Collection currently has zero copies of specified card");
                        }
                    } else {
                        view.displayErrorNewLine("No Card with given name exists in Collection");
                        view.displayErrorNewLine("Please re-input Card name");
                    }

                } while (!collection.containsKey(cardName) && !taskDone);
            } else {
                view.displayErrorNewLine("No Binder with given name exists");
            }
        }

    }

    public void displayBinders() {
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();

        if (!binderCollection.isEmpty()) {
            view.displayBinders(binderCollection);
        } else {
            view.displayErrorNewLine("No Binders made yet");
        }
    }

    public void displaySingleBinder() {
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        displayBinders();
        view.displayMessageNewLine("Indicate binder to view");
        String binderName = view.setBinderName();

        if (binderCollection.containsKey(binderName)) {
            displayBinderContent(binderCollection.get(binderName).getBinder());
        } else {
            view.displayErrorNewLine("No Binder with given name exists");
        }
    }

    public void displayBinderContent(HashMap<String, CardModel> binder) {

        if (!binder.isEmpty()) {
            view.displayBinderContent(binder);
        } else {
            view.displayErrorNewLine("No Cards in Binder");
        }
    }

    public void tradeCard() {

    }

    /**
     * Removes a binder from the shared collection based on user input.
     */
    public void removeBinder() {
        String name = view.setBinderName();
        HashMap<String, BinderModel> binders = sharedCollection.getBinderCollection();
        if (binders.containsKey(name)) {
            // sharedCollection.removeBinderCollection(name);
        }
    }

    public static CardModel createCardCopy(CardModel originalCard) {
        CardModel copy = new CardModel();
        copy.setName(originalCard.getName());
        copy.setRarity(originalCard.getRarity());
        copy.setVariant(originalCard.getVariant());
        copy.setQuantity(1);
        copy.setValue(originalCard.getValue());

        return copy;
    }
}
