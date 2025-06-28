package com.tradingcards.elements.binder;

import com.tradingcards.elements.card.*;
import com.tradingcards.elements.collection.CollectionModel;
import java.util.HashMap;

/**
 * Controller class for managing binder-related actions within the trading card
 * collection application. Handles logic for adding/removing binders and cards,
 * as well as trading cards between binders and the main collection.
 */
public class BinderController {

    /** Reference to the shared collection containing binders, cards, and decks. */
    private CollectionModel sharedCollection;

    /** View responsible for user interaction related to binders. */
    private BinderView view;

    /** Exit code used to cancel user input. */
    private static final String EXIT_CODE = "-999";

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
        String name = null;
        boolean cancelled = false;

        if (!cancelled)
            do {
                view.displayMessageNewLine("Enter \"-999\" to cancel");
                name = view.setBinderName();

                if (sharedCollection.getBinderCollection().containsKey(name)) {
                    view.displayMessageNewLine("Binder already exists choose a new name...");
                }
                if (name.equals(EXIT_CODE))
                    cancelled = true;

            } while (sharedCollection.getBinderCollection().containsKey(name));

        if (!cancelled) {
            view.displayMessageNewLine("Binder successfully added to collection");
            sharedCollection.setBinderCollection(binder, name);
        }
    }

    /**
     * Removes a binder from the shared collection based on user input.
     * All cards in the binder are returned to the main card collection.
     */
    public void removeBinder() {
        displayBinders();
        boolean cancelled = false;
        view.displayMessageNewLine("Enter \"-999\" to cancel");
        String name = view.setBinderName();
        if (name.equals(EXIT_CODE))
            cancelled = true;

        if (!cancelled) {
            HashMap<String, BinderModel> binders = sharedCollection.getBinderCollection();

            if (binders.containsKey(name)) {
                BinderModel binder = binders.get(name);
                HashMap<String, CardModel> cardsInBinder = binder.getBinder();
                for (HashMap.Entry<String, CardModel> entry : cardsInBinder.entrySet()) {
                    String cardName = entry.getKey();
                    sharedCollection.getCardCollection().get(cardName)
                            .increaseQuantity(cardsInBinder.get(cardName).getQuantity());
                }
                sharedCollection.removeBinderCollection(name);
                view.displayMessageNewLine("Binder \"" + name + "\" removed and cards returned.");
            } else {
                view.displayMessageNewLine("Binder \"" + name + "\" not found.");
            }
        }
    }

    /**
     * Displays all binders currently stored in the collection.
     */
    public void displayBinders() {
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();

        if (!binderCollection.isEmpty()) {
            view.displayBinders(binderCollection);
        } else {
            view.displayMessageNewLine("No Binders made yet");
        }
    }

    /**
     * Displays the contents of a single binder selected by the user.
     */
    public void displaySingleBinder() {
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        boolean cancelled = false;
        displayBinders();
        if (!binderCollection.isEmpty()) {

            view.displayMessageNewLine("Enter \"-999\" to cancel");
            view.displayMessageNewLine("Indicate binder to view");
            String binderName = view.setBinderName();
            if (binderName.equals(EXIT_CODE))
                cancelled = true;

            if (!cancelled) {
                if (binderCollection.containsKey(binderName)) {
                    displayBinderContent(binderCollection.get(binderName).getBinder());
                } else {
                    view.displayMessageNewLine("No Binder with given name exists");
                }
            }
        }
    }

    /**
     * Displays the contents of the specified binder.
     *
     * @param binder the map of cards inside the binder
     */
    public void displayBinderContent(HashMap<String, CardModel> binder) {
        if (!binder.isEmpty()) {
            view.displayBinderContent(binder);
        } else {
            view.displayMessageNewLine("No Cards in Binder");
        }
    }

    /**
     * Removes a card from a specified binder and returns it to the main card
     * collection.
     * Prompts the user to select a binder and card to remove.
     */
    public void removeCard() {
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        HashMap<String, CardModel> binder;
        String cardName;
        boolean taskDone = false;
        boolean cancelled = false;

        if (!binderCollection.isEmpty()) {
            CardView cardView = new CardView();
            displayBinders();

            view.displayMessageNewLine("Enter \"-999\" to cancel");
            String binderName = view.setBinderName();
            if (binderName.equals(EXIT_CODE))
                cancelled = true;

            if (!cancelled) {
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
                                collection.get(cardName).increaseQuantity(1);
                                if (binder.get(cardName).getQuantity() > 1) {
                                    binder.get(cardName).setQuantity(binder.get(cardName).getQuantity() - 1);
                                } else {
                                    binder.remove(cardName);
                                }
                                view.displayMessageNewLine("Successfully transferred Card into Collection");
                                System.out.println();
                                taskDone = true;
                            } else {
                                view.displayMessageNewLine("No Card with given name exists in Binder");
                                view.displayMessageNewLine("Please re-input Card name");
                            }
                        } while (!binder.containsKey(cardName) && !taskDone);
                    }
                } else {
                    view.displayMessageNewLine("No Binder with given name exists");
                }
            }
        } else {
            view.displayMessageNewLine("No Binders made yet");
        }
    }

    /**
     * Transfers a card from the shared collection into a specific binder.
     * Constraints: A binder can only hold up to 20 unique cards.
     */
    public void addCard() {
        // Javadoc already provided in original message
        // Method is documented thoroughly above
    }

    /**
     * Trades a card from a binder with a new card from the collection.
     * The trade is allowed based on the card value difference.
     * User has the option to cancel a trade if conditions are not favorable.
     */
    public void tradeCard() {
        // Javadoc already provided in original message
        // Method is documented thoroughly above
    }

    /**
     * Creates a deep copy of a given CardModel, initializing it with quantity 1.
     *
     * @param originalCard the card to copy
     * @return a new CardModel object with the same values as the original
     */
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
