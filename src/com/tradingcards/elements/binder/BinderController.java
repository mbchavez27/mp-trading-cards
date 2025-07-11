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

        // Proceed only if not cancelled initially
        if (!cancelled)
            do {
                view.displayMessageNewLine("Enter \"-999\" to cancel");
                name = view.setBinderName();

                // Check if the name already exists in the collection
                if (sharedCollection.getBinderCollection().containsKey(name)) {
                    view.displayMessageNewLine("Binder already exists choose a new name...");
                }

                // Check if the input is the exit code (e.g., "-999") to cancel operation
                if (name.equals(EXIT_CODE))
                    cancelled = true;

                // Repeat loop if binder name already exists
            } while (sharedCollection.getBinderCollection().containsKey(name));

        // If not cancelled, add the new binder to the collection
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
        // Display the list of binders to the user
        displayBinders();
        boolean cancelled = false;

        view.displayMessageNewLine("Enter \"-999\" to cancel");
        String name = view.setBinderName();

        // If user inputs the exit code, mark as cancelled
        if (name.equals(EXIT_CODE))
            cancelled = true;

        // Proceed only if the operation wasn't cancelled
        if (!cancelled) {
            HashMap<String, BinderModel> binders = sharedCollection.getBinderCollection();

            if (binders.containsKey(name)) {

                // Get the binder to be removed
                BinderModel binder = binders.get(name);
                HashMap<String, CardModel> cardsInBinder = binder.getBinder();

                // Loop through each card in the binder
                for (HashMap.Entry<String, CardModel> entry : cardsInBinder.entrySet()) {
                    String cardName = entry.getKey();
                    // Increase the quantity of each card in the main collection
                    sharedCollection.getCardCollection().get(cardName)
                            .increaseQuantity(cardsInBinder.get(cardName).getQuantity());
                }

                // Remove the binder from the shared collection
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
        // Get the collection of binders
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        boolean cancelled = false;
        displayBinders();

        // Proceed only if there are binders to display
        if (!binderCollection.isEmpty()) {

            view.displayMessageNewLine("Enter \"-999\" to cancel");
            view.displayMessageNewLine("Indicate binder to view");

            // Get the name of the binder to display
            String binderName = view.setBinderName();
            if (binderName.equals(EXIT_CODE))
                cancelled = true;

            // Proceed only if not cancelled
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
        // Get references to the main card and binder collections
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

            // Continue only if user didn't cancel
            if (!cancelled) {
                if (binderCollection.containsKey(binderName)) {
                    // Get the binder's card collection
                    binder = binderCollection.get(binderName).getBinder();

                    // Check if the binder is empty
                    if (binder.isEmpty()) {
                        view.displayMessageNewLine("Binder is currently empty");
                        view.displayMessageNewLine("Add cards to the Binder first");
                    } else {
                        // Show binder contents before removing a card
                        displayBinderContent(binder);
                        do {
                            view.displayMessageNewLine("Indicate card to be deleted");
                            cardName = cardView.setCardName();

                            // Check if card exists in the binder
                            if (binder.containsKey(cardName)) {
                                // Increase the quantity of the card in the main collection
                                collection.get(cardName).increaseQuantity(1);
                                if (binder.get(cardName).getQuantity() > 1) {
                                    binder.get(cardName).setQuantity(binder.get(cardName).getQuantity() - 1);
                                } else {
                                    // Remove card entirely if only one was present
                                    binder.remove(cardName);
                                }
                                view.displayMessageNewLine("Successfully transferred Card into Collection");
                                System.out.println();
                                taskDone = true;
                            } else {
                                // Card not found in binder, ask for valid input
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
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        CardView cardView = new CardView();
        BinderModel binder;

        String cardName;
        boolean taskDone = false;
        CardModel cardInCollection;
        CardModel cardInBinder;
        CardModel cardCopy;

        boolean cancelled = false;

        if (!collection.isEmpty()) {
            displayBinders();

            view.displayMessageNewLine("Enter \"-999\" to cancel");
            String binderName = view.setBinderName();

            if (binderName.equals(EXIT_CODE))
                cancelled = true;

            if (!cancelled) {
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
                                    view.displayMessageNewLine(
                                            "Collection currently has zero copies of specified card");
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
            }
        } else {
            view.displayMessageNewLine("\nCard collection is empty\n");
        }

    }

    /**
     * Trades a card from a binder with a new card from the collection.
     * The trade is allowed based on the card value difference.
     * User has the option to cancel a trade if conditions are not favorable.
     */
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
        CardModel cardCopy;

        boolean cancelled = false;
        displayBinders();

        view.displayMessageNewLine("Indicate binder to view");

        view.displayMessageNewLine("Enter \"-999\" to cancel");
        String binderName = view.setBinderName();
        if (binderName.equals(EXIT_CODE))
            cancelled = true;

        if (!cancelled) {
            if (binderCollection.containsKey(binderName)) {
                binderModel = binderCollection.get(binderName);
                binder = binderCollection.get(binderName).getBinder();
                if (!binder.isEmpty()) {
                    displayBinderContent(binder);
                    do {
                        view.displayMessageNewLine("Enter \"-999\" to cancel");
                        view.displayMessageNewLine("Indicate card to be traded");
                        outGoingCardName = cardView.setCardName();
                        if (outGoingCardName.equals(EXIT_CODE))
                            cancelled = true;

                        if (!cancelled) {
                            if (binder.containsKey(outGoingCardName)) {
                                view.displayMessageNewLine("Add new card for the collection");
                                String incomingCardName = cardController.addCard();
                                double difference = sharedCollection.getCardCollection().get(incomingCardName)
                                        .getValue()
                                        - binder.get(outGoingCardName).getValue();
                                if (difference >= 1) {
                                    String choice;
                                    view.displayMessageNewLine(
                                            "The difference of the ingoing vs outgoing card is " + difference + "\n");
                                    choice = view.getBinderChoice("Do you want to cancel the trade? (Y/N)");

                                    // Cancels trade and removes the added card from the collection
                                    if (choice.equalsIgnoreCase("Y")) {
                                        collection.remove(incomingCardName);
                                    } else {
                                        // Check if incoming card already exists in the binder
                                        if (binder.containsKey(incomingCardName)) {
                                            view.displayMessageNewLine(
                                                    "Trade failed: Incoming card already exists in the binder.");
                                            taskDone = true;
                                        } else {
                                            // Remove outgoing card from binder
                                            if (binder.get(outGoingCardName).getQuantity() > 1) {
                                                binder.get(outGoingCardName)
                                                        .setQuantity(binder.get(outGoingCardName).getQuantity() - 1);
                                            } else {
                                                binder.remove(outGoingCardName);
                                            }

                                            // Add incoming card to binder
                                            if (binder.size() < 20) {
                                                cardInCollection = collection.get(incomingCardName);
                                                cardCopy = createCardCopy(cardInCollection);
                                                binderCollection.get(binderName).insertInBinder(cardCopy,
                                                        incomingCardName);

                                                // Remove from collection
                                                cardInCollection.setQuantity(cardInCollection.getQuantity() - 1);

                                                view.displayMessageNewLine(
                                                        "Trade successful! " + outGoingCardName + " removed, "
                                                                + incomingCardName + " added.");
                                                taskDone = true;
                                            } else {
                                                view.displayMessageNewLine("Trade failed: Binder is full.");
                                                taskDone = true; // mark task as done to exit loop
                                            }
                                        }
                                    }

                                } else if (difference < 1) {
                                    // Check if incoming card already exists in the binder
                                    if (binder.containsKey(incomingCardName)) {
                                        view.displayMessageNewLine(
                                                "Trade failed: Incoming card already exists in the binder.");
                                        taskDone = true;
                                    } else {
                                        // Remove outgoing card from binder
                                        if (binder.get(outGoingCardName).getQuantity() > 1) {
                                            binder.get(outGoingCardName)
                                                    .setQuantity(binder.get(outGoingCardName).getQuantity() - 1);
                                        } else {
                                            binder.remove(outGoingCardName);
                                        }

                                        // Add incoming card to binder
                                        if (binder.size() < 20) {
                                            cardInCollection = collection.get(incomingCardName);
                                            cardCopy = createCardCopy(cardInCollection);
                                            binderCollection.get(binderName).insertInBinder(cardCopy, incomingCardName);

                                            // Remove from collection
                                            cardInCollection.setQuantity(cardInCollection.getQuantity() - 1);

                                            view.displayMessageNewLine(
                                                    "Trade successful! " + outGoingCardName + " removed, "
                                                            + incomingCardName + " added.");
                                            taskDone = true;
                                        } else {
                                            view.displayMessageNewLine("Trade failed: Binder is full.");
                                            taskDone = true; // mark task as done to exit loop
                                        }
                                    }
                                }
                            } else {
                                view.displayMessageNewLine("No Card with given name exists in Binder");
                                view.displayMessageNewLine("Please re-input Card name");
                            }
                        }
                    } while (!binder.containsKey(outGoingCardName) && !taskDone);
                } else {
                    view.displayMessageNewLine("No Cards in Binder");
                }
            } else {
                view.displayMessageNewLine("No Binder with given name exists");
            }
        }
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
