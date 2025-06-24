package com.tradingcards.elements.card;

import com.tradingcards.elements.collection.CollectionModel;
import java.util.HashMap;

/**
 * The {@code CardController} class handles logic for managing cards in the
 * user's collection. It communicates with the {@code CardModel} to manipulate
 * data and interacts with the user through the {@code CardView}.
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
     * Constructs a {@code CardController} with the specified collection, model,
     * and view.
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
     * <p>
     * This method instantiates a new {@code CardModel} object and sets its
     * properties (name, rarity, value, and optionally variant) using input
     * provided by the user through the {@code view}. If the rarity is "Rare" or
     * "Legendary", a variant is also required and the card's value is
     * calculated using the variant. Otherwise, the value is set directly. The
     * card is then given a default quantity of 1 and added to the collection
     * via {@code CollectionModel.setCardCollection()}.
     * <p>
     * <b>Note:</b> Currently, the method does not check for duplicate cards in
     * the collection before setting the quantity.
     */
    public void addCard() {
        // Instantiate Card Object
        CardModel card = new CardModel();

        // Get from View
        String name = view.setCardName();
        card.setName(name);

        String rarity = view.setCardRarity();
        card.setRarity(rarity);

        if (card.getRarity().equals("Rare") || card.getRarity().equals("Legendary")) {
            String variant = view.setCardVariant();
            card.setVariant(variant);
            double value = view.setCardValue();
            card.setValue(card.calculateValue(value, variant));
        } else {
            double value = view.setCardValue();
            card.setValue(value);
        }

        if (sharedCollection.getCardCollection().containsKey(name)) {
            if (card.isUnique(sharedCollection, name, card)) {
                if (view.allowIncreaseCardCount(name))
                    sharedCollection.getCardCollection().get(name).increaseQuantity(1);
            } else {
                view.displayErrorNewLine("\nCard is not unique");
            }
        } else {
            card.setQuantity(1);
            sharedCollection.setCardCollection(card, name);
        }
    }

    /**
     * Modifies the quantity of a specific card in the collection.
     * <p>
     * This method first displays the current collection and prompts the user to
     * enter the name of the card they wish to modify using
     * {@code view.setCardName()}. If the card exists, it repeatedly prompts the
     * user for a new quantity using {@code view.setCardQuantity()} until a
     * valid and different quantity (non-negative and not equal to the current
     * quantity) is provided. It then updates the card's quantity in the
     * collection. If the specified card does not exist, an error message is
     * displayed.
     */
    public void modifyCardQuantity() {
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        displayCollection();
        String cardKey = view.setCardName();
        if (collection.containsKey(cardKey)) {
            int newQuantity;
            do {
                newQuantity = view.setCardQuantity();
            } while (collection.get(cardKey).getQuantity() == newQuantity || newQuantity < 0);
            collection.get(cardKey).setQuantity(newQuantity);

        } else {
            view.displayErrorNewLine("No Card with given name existing in Collection");
        }
    }

    /**
     * Prompts the user to enter a card name and displays the details of the
     * specified card.
     * <p>
     * This method retrieves the current card collection from
     * {@code CollectionModel.getCardCollection()}. If the collection is not
     * empty, it prompts the user to input a card name using
     * {@code view.setCardName()}, then displays the corresponding card details
     * using {@code view.displayCard()}. If the collection is empty, an error
     * message is displayed.
     */
    public void displayCard() {
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();

        if (!collection.isEmpty()) {
            view.displayCard(collection, view.setCardName());
        } else {
            view.displayErrorNewLine("No Cards yet...");
        }
    }

    /**
     * Retrieves and displays the user's entire card collection.
     * <p>
     * This method obtains the current card collection from
     * {@code CollectionModel.getCardCollection()}. If the collection is not
     * empty, it delegates the display task to the
     * {@code view.displayCollection()} method. If the collection is empty, an
     * error message is printed to indicate that there are no cards.
     */
    public void displayCollection() {
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();

        if (!collection.isEmpty()) {
            view.displayCollection(collection);
        } else {
            view.displayErrorNewLine("No Cards yet...");
        }
    }
}
