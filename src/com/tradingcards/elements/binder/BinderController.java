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
                view.displayMessageNewLine("Binder already exists choose a new name...");
            }

        } while (sharedCollection.getBinderCollection().containsKey(name));
        view.displayMessageNewLine("Binder successfully added to collection");
        sharedCollection.setBinderCollection(binder, name);
    }

    /**
     * Removes a card from a specified binder and returns it to the main card
     * collection.
     * This method allows the user to select a binder and transfer a card from that
     * binder
     * back into the shared card collection.
     * Note: This method uses user input for both binder name and card name.
     * The process continues until a valid card is successfully removed.
     */
    public void removeCard() {
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        HashMap<String, CardModel> binder;
        String cardName;
        boolean taskDone = false;

        if (!binderCollection.isEmpty()) {
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
                            view.displayMessageNewLine("No Card with given name exists in Binder");
                            view.displayMessageNewLine("Please re-input Card name");
                        }
                    } while (!binder.containsKey(cardName) && !taskDone);
                }
            } else {
                view.displayMessageNewLine("No Binder with given name exists");
            }
        } else {
            view.displayMessageNewLine("No Binders made yet");
        }
    }

    /**
     * Transfers a card from the shared collection into a specific binder.
     * This method allows the user to select a binder and move a card from the main
     * collection
     * into that binder.
     * Constraints:
     * A binder can only hold up to 20 different cards.
     * The collection must have at least one copy of the selected card.
     */
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

        if (!collection.isEmpty()) {
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
                            System.out.println(cardInCollection.getQuantity());
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
                                    view.displayMessageNewLine("Binder is already full");
                                }
                            } else {
                                view.displayMessageNewLine("Collection currently has zero copies of specified card");
                            }
                        } else {
                            view.displayMessageNewLine("No Card with given name exists in Collection");
                            view.displayMessageNewLine("Please re-input Card name");
                        }

                    } while (!collection.containsKey(cardName) && !taskDone);
                } else {
                    view.displayMessageNewLine("No Binder with given name exists");
                }
            }
        } else {
            view.displayMessageNewLine("\nCard collection is empty\n");
        }

    }

    public void displayBinders() {
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();

        if (!binderCollection.isEmpty()) {
            view.displayBinders(binderCollection);
        } else {
            view.displayMessageNewLine("No Binders made yet");
        }
    }

    public void displaySingleBinder() {
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        displayBinders();
        if (!binderCollection.isEmpty()){
            view.displayMessageNewLine("Indicate binder to view");
            String binderName = view.setBinderName();

            if (binderCollection.containsKey(binderName)) {
                displayBinderContent(binderCollection.get(binderName).getBinder());
            } else {
                view.displayMessageNewLine("No Binder with given name exists");
            }
        }
    }

    public void displayBinderContent(HashMap<String, CardModel> binder) {

        if (!binder.isEmpty()) {
            view.displayBinderContent(binder);
        } else {
            view.displayMessageNewLine("No Cards in Binder");
        }
    }

    public void tradeCard() {
        CardView cardView = new CardView();

        CardController cardController = new CardController(sharedCollection, cardView);

        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();

        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();

        HashMap<String, CardModel> binder;

        BinderModel binderModel;

        String outGoingCardName;

        boolean taskDone = false;

        CardModel cardInCollection;
        CardModel cardInBinder;
        CardModel cardCopy;
        displayBinders();

        view.displayMessageNewLine("Indicate binder to view");

        String binderName = view.setBinderName();

        if (binderCollection.containsKey(binderName)) {
            binderModel = binderCollection.get(binderName);
            binder = binderCollection.get(binderName).getBinder();
            if (!binder.isEmpty()) {
                displayBinderContent(binder);
                do {
                    view.displayMessageNewLine("Indicate card to be traded");
                    outGoingCardName = cardView.setCardName();

                    if (binder.containsKey(outGoingCardName)) {
                        cardInCollection = collection.get(outGoingCardName);

                        view.displayMessageNewLine("Add new card for the collection");
                        String incomingCardName = cardController.addCard();
                        double difference = sharedCollection.getCardCollection().get(incomingCardName).getValue()
                                - binder.get(outGoingCardName).getValue();

                        if (difference >= 1) {
                            view.displayMessageNewLine(
                                    "The difference of the ingoing vs outgoing card is " + difference);

                        } else if (difference < 1) {
                            // Remove c1 from binder
                            if (binder.get(outGoingCardName).getQuantity() > 1) {
                                binder.get(outGoingCardName)
                                        .setQuantity(binder.get(outGoingCardName).getQuantity() - 1);
                            } else {
                                binder.remove(outGoingCardName);
                            }

                            // Add c2 to binder
                            if (binder.size() < 20) {
                                cardInCollection = collection.get(incomingCardName);

                                if (binder.containsKey(incomingCardName)) {
                                    cardInBinder = binder.get(incomingCardName);
                                    cardInBinder.setQuantity(cardInBinder.getQuantity() + 1);
                                } else {
                                    cardCopy = createCardCopy(cardInCollection);
                                    binderCollection.get(binderName).insertInBinder(cardCopy, incomingCardName);
                                }

                                // Decrease from collection
                                cardInCollection.setQuantity(cardInCollection.getQuantity() - 1);

                                view.displayMessageNewLine("Trade successful! " + outGoingCardName + " removed, "
                                        + incomingCardName + " added.");
                                taskDone = true;
                            } else {
                                view.displayMessageNewLine("Trade failed: Binder is full.");
                            }
                        }
                    } else {
                        view.displayMessageNewLine("No Card with given name exists in Binder");
                        view.displayMessageNewLine("Please re-input Card name");
                    }
                } while (!binder.containsKey(outGoingCardName) && !taskDone);
            } else {
                view.displayMessageNewLine("No Cards in Binder");
            }
        } else {
            view.displayMessageNewLine("No Binder with given name exists");
        }
    }

    /**
     * Removes a binder from the shared collection based on user input.
     */
    public void removeBinder() {
        displayBinders();
        String name = view.setBinderName();
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
