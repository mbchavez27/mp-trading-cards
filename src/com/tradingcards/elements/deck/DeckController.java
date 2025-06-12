package com.tradingcards.elements.deck;

import com.tradingcards.elements.collection.CollectionModel;

/**
 * Controller class responsible for managing operations related to decks. This
 * includes adding new decks and removing existing ones by interacting with the
 * shared collection and view.
 */
public class DeckController {

    private CollectionModel sharedCollection;
    private DeckModel model;
    private DeckView view;

    /**
     * Constructs a DeckController with the given shared collection, model, and
     * view.
     *
     * @param sharedCollection the shared CollectionModel containing the deck
     * data
     * @param model the DeckModel representing a deck
     * @param view the DeckView responsible for user interaction
     */
    public DeckController(CollectionModel sharedCollection, DeckModel model, DeckView view) {
        this.sharedCollection = sharedCollection;
        this.model = model;
        this.view = view;
    }

    /**
     * Adds a new deck to the shared collection. Prompts the user to enter a
     * unique deck name. If the name already exists, the user is asked to input
     * a new name until a unique one is provided. A new DeckModel is then
     * created and added to the collection.
     */
    public void addDeck() {
        DeckModel deck = new DeckModel();
        String name;

        do {
            name = view.setDeckName();
            if (sharedCollection.getDeckCollection().containsKey(name)) {
                System.err.println("Deck already exists choose a new name...");
            }

        } while (sharedCollection.getDeckCollection().containsKey(name));

        sharedCollection.setDeckCollection(deck, name);
    }

    /**
     * Removes a deck from the shared collection. Prompts the user to input the
     * name of the deck to be removed. The specified deck is then removed from
     * the collection.
     */
    public void removeDeck() {
        String name = view.setDeckName();

        sharedCollection.removeDeckCollection(name);
    }
}
