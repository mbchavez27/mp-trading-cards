package com.tradingcards.elements.card;

import java.util.HashMap;

import javax.swing.JPanel;

import com.tradingcards.elements.collection.CollectionModel;
import com.tradingcards.elements.menus.menuUtils.DialogUtil;

/**
 * The {@code CardController} class handles logic for managing cards in the
 * user's collection. It communicates with the {@code CardModel} to manipulate
 * data and interacts with the user through the {@code CardView}.
 *
 * <p>
 * Responsibilities include:
 * <ul>
 * <li>Adding cards to the collection based on user input</li>
 * <li>Modifying the quantity of existing cards</li>
 * <li>Displaying individual card details</li>
 * <li>Displaying the full collection</li>
 * </ul>
 */
public class CardController {

    /**
     * Shared collection of cards, binders, and decks.
     */
    private CollectionModel sharedCollection;

    /**
     * View class used for interacting with the user (e.g., input/output).
     */
    private CardView view;

    /**
     * Exit code constant for user cancellation.
     */
    private static final String EXIT_CODE = "-999";

    /**
     * Constructs a {@code CardController} with the specified collection and view.
     *
     * @param sharedCollection the central collection shared across the app
     * @param view             the view class that handles user input/output for
     *                         cards
     */
    public CardController(CollectionModel sharedCollection, CardView view) {
        this.sharedCollection = sharedCollection;
        this.view = view;
    }

    /**
     * Creates and adds a new card to the collection based on user input.
     *
     * <p>
     * This method sets card properties (name, rarity, value, variant) from
     * user input and stores the card in the shared collection. For "Rare" or
     * "Legendary" rarities, it also asks for a variant and calculates the value
     * accordingly.
     *
     * @return the name of the card added (or attempted to add)
     */
    public String addCard() {
        CardModel card = view.showAddCardForm();

        if (card == null)
            return null; // cancel

        String name = card.getName();

        if (sharedCollection.getCardCollection().containsKey(name)) {
            if (card.hasCopy(sharedCollection, name, card)) {
                if (view.allowIncreaseCardCount(name)) {
                    sharedCollection.getCardCollection().get(name).increaseQuantity(1);
                }
            } else {
                DialogUtil.showError(null, "Card of the same name already exists", "Duplicate Card");
            }
        } else {
            sharedCollection.setCardCollection(card, name);
        }
        return name;
    }

    /**
     * Attempts to sell a card from the shared collection based on its name.
     * <p>
     * If the card exists and has a quantity greater than zero, the method:
     * <ul>
     * <li>Adds the card's value to the total money</li>
     * <li>Decrements the card's quantity by one</li>
     * </ul>
     * If the card does not exist or its quantity is zero, a warning dialog is
     * displayed.
     *
     * @param name the name of the card to sell
     */
    public boolean sellCard(String name) {

        if (sharedCollection.getCardCollection().containsKey(name)
                && sharedCollection.getCardCollection().get(name).getQuantity() > 0) {
            sharedCollection
                    .setMoney(sharedCollection.getMoney() + sharedCollection.getCardCollection().get(name).getValue());

            sharedCollection.getCardCollection().get(name)
                    .setQuantity(sharedCollection.getCardCollection().get(name).getQuantity() - 1);

            DialogUtil.showInfo(null, "Your updated cash is now " + sharedCollection.getMoney(), "Sold card");

        } else {
            DialogUtil.showError(null, "Card does not exist", "Undefined Card");

            return false;
        }
        return true;
    }

    /**
     * Modifies the quantity of a specific card in the collection.
     * Prompts the user to choose a card and input a new valid quantity.
     */
    public int modifyCardQuantity() {
        // Get the card collection from the shared collection
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        boolean cancelled = false;

        String cardKey = view.setCardName();

        // Cancel operation if user enters exit code
        if (cardKey == null || cardKey.equals(EXIT_CODE)) {
            cancelled = true;
        }

        if (!cancelled) {
            // Check if the card exists in the collection
            if (collection.containsKey(cardKey)) {
                int newQuantity;

                // Ask for new quantity until it's valid (not negative and not same as current)
                do {
                    newQuantity = view.setCardQuantity();
                    if (newQuantity == -999) {
                        return -1;
                    }

                } while (collection.get(cardKey).getQuantity() == newQuantity || newQuantity < 0);
                // Update the quantity of the card
                collection.get(cardKey).setQuantity(newQuantity);
                return newQuantity;

            } else {
                DialogUtil.showError(null, "Card does not exist", "Undefined Card");

            }
        }
        return -1;
    }

    /**
     * Prompts the user to select a card and displays its details.
     * If the collection is empty, displays an appropriate message.
     */
    public JPanel displayCard() {
        // Get the main card collection
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        boolean cancelled = false;

        // Proceed only if there are cards in the collection
        if (!collection.isEmpty()) {
            // Ask user to select a card name
            String cardName = view.setCardName();

            if (cardName.equals(EXIT_CODE))
                cancelled = true;

            if (!cancelled)
                return (view.displayCard(collection, cardName));
        } else {
            // TODO, must return a Jpanel
            view.displayMessageNewLine("No Cards yet...");
        }
        return null;
    }

    /**
     * Displays the full card collection including cards with 0 quantity.
     */
    public JPanel displayCollection() {
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();

        if (!collection.isEmpty()) {
            return (view.displayCollection(collection));
        } else {
            DialogUtil.showWarning(null, "No Cards yet...", "Collection Warning");
        }
        return null;
    }

    /**
     * Displays the card collection with filtering options.
     *
     * @param mode display mode (0 = include zero-quantity cards, 1 = exclude
     *             zeroes)
     */
    public void displayCollection(int mode) {
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();

        if (!collection.isEmpty()) {
            view.displayCollection(collection, mode);
        } else {
            view.displayMessageNewLine("No Cards yet...");
        }
    }
}
