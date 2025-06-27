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
        // Create a new instance of BinderModel
        BinderModel binder = new BinderModel();
        String name;

        // Loop until a unique binder name is entered
        do {
            // Prompt user for binder name
            name = view.setBinderName();

            // If the binder name already exists in the collection, show error
            if (sharedCollection.getBinderCollection().containsKey(name)) {
                view.displayMessageNewLine("Binder already exists choose a new name...");
            }
            // Continue loop if name is already in use
        } while (sharedCollection.getBinderCollection().containsKey(name));

        //Display successful addition
        view.displayMessageNewLine("Binder successfully added to collection");
        //Add new binder to shared collection
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
        // Retrieve card and binder collections from shared storage
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        HashMap<String, CardModel> binder;
        String cardName;
        boolean taskDone = false;

        // Check if there are any binders in the collection
        if (!binderCollection.isEmpty()) {
            CardView cardView = new CardView();
            displayBinders(); // Show list of available binders

            // Ask user to specify which binder to modify
            String binderName = view.setBinderName();
            // Proceed only if the binder name exists
            if (binderCollection.containsKey(binderName)) {
                // Get the selected binder's contents
                binder = binderCollection.get(binderName).getBinder();
                // If the binder is empty, inform the user and exit
                if (binder.isEmpty()) {
                    view.displayMessageNewLine("Binder is currently empty");
                    view.displayMessageNewLine("Add cards to the Binder first");
                } else {
                    // Display binder contents to user
                    displayBinderContent(binder);
                    do {
                        view.displayMessageNewLine("Indicate card to be deleted");
                        cardName = cardView.setCardName();
                        // Check if the card exists in the binder
                        if (binder.containsKey(cardName)) {
                            // Increase quantity in main collection
                            collection.get(cardName).setQuantity(collection.get(cardName).getQuantity() + 1);
                            // If more than one copy, just decrement quantity in binder
                            if (binder.get(cardName).getQuantity() > 1) {
                                binder.get(cardName).setQuantity(binder.get(cardName).getQuantity() - 1);
                            } else {
                                // If only one copy, remove card entirely from binder
                                binder.remove(cardName);
                            }
                            view.displayMessageNewLine("Successfully transferred Card into Collection");
                            System.out.println();
                            taskDone = true; // Exit loop after successful removal
                        } else {
                            // Card not found in binder
                            view.displayMessageNewLine("No Card with given name exists in Binder");
                            view.displayMessageNewLine("Please re-input Card name");
                        }
                    } while (!binder.containsKey(cardName) && !taskDone);
                }
            } else {
                // Binder name input does not exist
                view.displayMessageNewLine("No Binder with given name exists");
            }
        } else {
            // No binders have been created at all
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
        // Get references to the card collection and binder collection
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        CardView cardView = new CardView();
        BinderModel binder;

        String cardName;
        boolean taskDone = false; // Used to indicate successful completion of the card transfer
        CardModel cardInCollection;
        CardModel cardInBinder;
        CardModel cardCopy;

        // Proceed only if the collection is not empty
        if (!collection.isEmpty()) {
            displayBinders(); // Show all existing binders to the user

            String binderName = view.setBinderName(); // Ask user to choose a binder

            // Check if there are any binders at all
            if (binderCollection.isEmpty()) {
                view.displayMessageNewLine("No cards in collection yet");
                view.displayMessageNewLine("Input cards in collection first");
            } else {
                // Check if the user-selected binder exists
                if (binderCollection.containsKey(binderName)) {
                    binder = binderCollection.get(binderName); // Get the selected binder
                    cardView.displayCollection(collection); // Show current collection to user

                    do {
                        cardName = cardView.setCardName();
                        // Checks if the collection has the card
                        if (collection.containsKey(cardName)) {
                            // Checks if the collection has a positive number of card copies
                            cardInCollection = collection.get(cardName);
                            System.out.println(cardInCollection.getQuantity());
                            if (cardInCollection.getQuantity() > 0) {

                                // Checks if binder can accommodate new card;
                                if (binder.getBinder().size() < 20) {

                                    // Checks if binder already contains the specified card
                                    if (binder.getBinder().containsKey(cardName)) {
                                        cardInBinder = binder.getBinder().get(cardName);
                                        cardInBinder.setQuantity(cardInBinder.getQuantity() + 1);
                                    } else {
                                        // Create a new card object to store details
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
        // Retrieve the binder collection from the shared collection
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();

        // Check if there are any binders in the collection
        if (!binderCollection.isEmpty()) {
            // If binders exist, display them using the view
            view.displayBinders(binderCollection);
        } else {
            // If the binder collection is empty, inform the user
            view.displayMessageNewLine("No Binders made yet");
        }
    }

    public void displaySingleBinder() {
        // Retrieve all binders from the shared collection
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        // Display all available binders to the user
        displayBinders();

        // Check if the binder collection is not empty
        if (!binderCollection.isEmpty()){
            // Prompt user to choose which binder to view
            view.displayMessageNewLine("Indicate binder to view");
            String binderName = view.setBinderName();

            // If the specified binder exists, display its contents
            if (binderCollection.containsKey(binderName)) {
                displayBinderContent(binderCollection.get(binderName).getBinder());
            } else {
                // Notify user if the specified binder name doesn't exist
                view.displayMessageNewLine("No Binder with given name exists");
            }
        }
    }

    public void displayBinderContent(HashMap<String, CardModel> binder) {

        // Check if the binder is not empty
        if (!binder.isEmpty()) {
            // If it has cards, display the contents using the view
            view.displayBinderContent(binder);
        } else {
            // If empty, notify the user
            view.displayMessageNewLine("No Cards in Binder");
        }
    }

    public void tradeCard() {

    }

    /**
     * Removes a binder from the shared collection based on user input.
     */
    public void removeBinder() {
        // Display all existing binders to the user
        displayBinders();

        // Ask the user to input the name of the binder to remove
        String name = view.setBinderName();
        HashMap<String, BinderModel> binders = sharedCollection.getBinderCollection();

        // Check if the specified binder exists in the collection
        if (binders.containsKey(name)) {

            // Get the selected binder
            BinderModel binder = binders.get(name);
            // Get the cards stored in the binder
            HashMap<String, CardModel> cardsInBinder = binder.getBinder();

            // Loop through each card in the binder
            for (HashMap.Entry<String, CardModel> entry : cardsInBinder.entrySet()) {
                String cardName = entry.getKey();
                // Return each card's quantity back to the main collection
                sharedCollection.getCardCollection().get(cardName).increaseQuantity(cardsInBinder.get(cardName).getQuantity());
            }
            // Remove the binder from the collection
            sharedCollection.removeBinderCollection(name);

            // Notify the user of successful removal
            view.displayMessageNewLine("Binder \"" + name + "\" removed and cards returned.");
        } else {
            // Notify the user if the binder does not exist
            view.displayMessageNewLine("Binder \"" + name + "\" not found.");
        }
    }

    public static CardModel createCardCopy(CardModel originalCard) {
        CardModel copy = new CardModel();

        // Copy all relevant properties from the original card to the new one
        copy.setName(originalCard.getName());
        copy.setRarity(originalCard.getRarity());
        copy.setVariant(originalCard.getVariant());
        copy.setQuantity(1);
        copy.setValue(originalCard.getValue());

        // Return the new CardModel instance
        return copy;
    }
}
