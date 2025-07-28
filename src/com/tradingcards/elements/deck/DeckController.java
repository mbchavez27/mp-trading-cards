package com.tradingcards.elements.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.card.CardView;
import com.tradingcards.elements.collection.CollectionModel;
import com.tradingcards.elements.menus.menuUtils.DialogUtil;

import javax.swing.*;

/**
 * Controller class responsible for managing operations related to decks. This
 * includes adding new decks, removing existing ones, and manipulating cards
 * within decks by interacting with the shared collection and view.
 */
public class DeckController {

    /** Reference to the shared collection model containing decks and cards. */
    private CollectionModel sharedCollection;

    /** The view component responsible for user interaction regarding decks. */
    private DeckView view;

    /** Constant used to signal user cancellation of an operation. */
    private static final String EXIT_CODE = "-999";

    /**
     * Constructs a DeckController with the given shared collection and view.
     *
     * @param sharedCollection the shared CollectionModel containing the deck data
     * @param view             the DeckView responsible for user interaction
     */
    public DeckController(CollectionModel sharedCollection, DeckView view) {
        this.sharedCollection = sharedCollection;
        this.view = view;
    }

    /**
     * Adds a new deck to the shared collection.
     * Prompts the user for a unique deck name and creates the deck if valid.
     * If the user inputs "-999", the operation is cancelled.
     */
    public void addDeck() {
        DeckModel deck = view.showDeckForm();

        if (deck == null) {
            DialogUtil.showWarning(null, "Deck creation cancelled.", "Cancelled");
        } else {
            if (sharedCollection.getDeckCollection().containsKey(deck.getName())) {
                DialogUtil.showWarning(null, "Deck of the same name already exists", "Duplicate Deck");
            } else {
                sharedCollection.setDeckCollection(deck, deck.getName());
                DialogUtil.showInfo(
                        null,
                        "New " + deck.getType() + " deck successfully added to collection!",
                        "New deck");
            }
        }
    }

    /**
     * Removes a specified deck from the shared collection.
     * All cards in the deck are returned to the card collection.
     * Cancels operation if user inputs "-999".
     */
    public void removeDeck(JPanel panel) {
        // Display the list of decks to the user
        refreshPanel(panel, displayDecks());
        boolean cancelled = false;

        String name = view.setDeckName();
        if (name.equals(EXIT_CODE) || name == null)
            cancelled = true;

        // Proceed only if not cancelled
        if (!cancelled) {
            // Get all existing decks
            HashMap<String, DeckModel> decks = sharedCollection.getDeckCollection();

            // Check if the specified deck exists
            if (decks.containsKey(name)) {
                DeckModel deck = decks.get(name);
                HashMap<String, CardModel> cardsInDeck = deck.getDeck();

                // Return each card from the deck to the main collection
                for (HashMap.Entry<String, CardModel> entry : cardsInDeck.entrySet()) {
                    String cardName = entry.getKey();
                    // Increase the quantity of the card in the collection by 1
                    sharedCollection.getCardCollection().get(cardName)
                            .setQuantity(sharedCollection.getCardCollection().get(cardName).getQuantity() + 1);
                }

                // Remove the deck from the shared collection
                sharedCollection.removeDeckCollection(name);
                DialogUtil.showInfo(panel, "Deck \"" + name + "\" removed and cards returned", "Deck Removed");
                refreshPanel(panel, displayDecks());
            } else {
                DialogUtil.showInfo(panel, "Deck \"" + name + "\" not found", "Deck Not Found");
            }
        }
    }

    /**
     * Removes a specific card from a selected deck and returns it to the card
     * collection.
     * Cancels operation if user inputs "-999".
     */
    public void removeCard(JPanel panel) {
        // Get references to main collections
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, DeckModel> deckCollection = sharedCollection.getDeckCollection();
        HashMap<String, CardModel> deck;
        String cardToRemove;
        boolean taskDone = false;
        boolean cancelled = false;

        // Proceed only if at least one deck exists
        if (!deckCollection.isEmpty()) {
            CardView cardView = new CardView();
            displayDecks();

            view.displayMessageNewLine("Enter \"-999\" to cancel");
            String deckName = view.setDeckName();
            if (deckName.equals(EXIT_CODE))
                cancelled = true;

            // Proceed only if user didn't cancel
            if (!cancelled) {
                if (deckCollection.containsKey(deckName)) {
                    deck = deckCollection.get(deckName).getDeck();

                    if (deck.isEmpty()) {
                        view.displayMessageNewLine("Deck is currently empty");
                        view.displayMessageNewLine("Add cards to the Deck first");
                    } else {
                        displayDeckContent(deck);

                        // Repeat until a valid card is removed
                        do {
                            view.displayMessageNewLine("Indicate card to be deleted");
                            cardToRemove = cardView.setCardName();

                            // Check if the card exists in the deck
                            if (deck.containsKey(cardToRemove)) {
                                // Increase quantity back to main collection
                                collection.get(cardToRemove)
                                        .setQuantity(collection.get(cardToRemove).getQuantity() + 1);
                                // Remove card from deck
                                deck.remove(cardToRemove);
                                view.displayMessageNewLine("Sucessfully transferred Card into Collection");
                                taskDone = true;
                            } else {
                                // Invalid input; prompt again
                                view.displayMessageNewLine("No Card with given name exists in Deck");
                                view.displayMessageNewLine("Please re-input Card name");
                            }

                        } while (!deck.containsKey(cardToRemove) && !taskDone);
                    }
                } else {
                    // If deck name is invalid
                    view.displayMessageNewLine("No Deck with given name exists");
                }
            } else {
                // If there are no decks created
                view.displayMessageNewLine("No decks made yet");
            }
        }
    }

    /**
     * Adds a card from the collection to a specified deck.
     * Validates input and checks for quantity and deck size limits.
     * Cancels operation if user inputs "-999".
     */
    public void addCard(JPanel panel) {
        // Get references to the card collection and deck collection
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, DeckModel> deckCollection = sharedCollection.getDeckCollection();
        CardView cardView = new CardView();
        DeckModel deck;
        CardModel cardInCollection;

        String cardToRemove;
        boolean taskDone = false;
        boolean cancelled = false;

        // Proceed only if the collection is not empty
        if (!collection.isEmpty()) {

            displayDecks();

            // Prompt user for deck name or cancel
            view.displayMessageNewLine("Enter \"-999\" to cancel");
            String deckName = view.setDeckName();
            if (deckName.equals(EXIT_CODE))
                cancelled = true;

            // Check if user canceled
            if (!cancelled) {
                if (deckCollection.isEmpty()) {
                    view.displayMessageNewLine("No cards in collection yet");
                    view.displayMessageNewLine("Input cards in collection first");
                } else {
                    // Proceed if the specified deck exists
                    if (deckCollection.containsKey(deckName)) {
                        deck = deckCollection.get(deckName);
                        cardView.displayCollection(collection);

                        do {
                            cardToRemove = cardView.setCardName();

                            // Check if card exists in collection
                            if (collection.containsKey(cardToRemove)) {
                                cardInCollection = collection.get(cardToRemove);

                                // Check if card has copies left in collection
                                if (cardInCollection.getQuantity() > 0) {

                                    // Check if deck has space for more cards (max 10)
                                    if (deck.getDeck().size() < 10) {
                                        if (deck.addCardtoDeck(collection.get(cardToRemove), cardToRemove)) {
                                            // Decrease quantity in main collection
                                            cardInCollection.setQuantity(cardInCollection.getQuantity() - 1);

                                            // Confirm success
                                            view.displayMessageNewLine("Successfully transferred card into Deck");
                                            taskDone = true;
                                        } else {
                                            // Card is already in the deck
                                            view.displayMessageNewLine("Deck already contains specified card");
                                        }
                                    } else {
                                        // Deck has reached max size
                                        view.displayMessageNewLine("Deck is already full");
                                    }
                                } else {
                                    // No more copies of this card left in collection
                                    view.displayMessageNewLine(
                                            "Collection currently has zero copies of specified card");
                                }
                            } else {
                                // Invalid card name entered
                                view.displayMessageNewLine("No Card with given name exists in Collection");
                                view.displayMessageNewLine("Please re-input Card name");
                            }
                        } while (!collection.containsKey(cardToRemove) && !taskDone);
                    } else {
                        // Deck name not found
                        view.displayMessageNewLine("No Deck with given name exists");
                    }
                }
            }
        } else {
            // No cards available to add to deck
            view.displayMessageNewLine("\nCard collection is empty\n");
        }
    }

    /**
     * Displays the contents of a specified deck.
     * Allows the user to select a card to view in detail by name or number.
     * Cancels operation if user inputs "-999".
     */
    public void displaySingleDeck(JPanel panel) {
        // Get the map of all decks
        HashMap<String, DeckModel> deckCollection = sharedCollection.getDeckCollection();
        HashMap<String, CardModel> selectedDeck;

        // Display all available decks to the user
        refreshPanel(panel, displayDecks());

        boolean cancelled = false;

        // Proceed only if there is at least one deck
        if (!deckCollection.isEmpty()) {

            // Get the deck name input from the user
            String deckName = view.setDeckName();
            if (deckName.equals(EXIT_CODE) || deckName == null)
                cancelled = true;

            // If not cancelled, proceed
            if (!cancelled) {
                // Check if the deck name exists
                if (deckCollection.containsKey(deckName)) {
                    selectedDeck = deckCollection.get(deckName).getDeck();
                    if (!selectedDeck.isEmpty()) {

                        refreshPanel(panel, displayDeckContent(selectedDeck));
                        ;
                        chooseCardFromDeck(selectedDeck);
                    } else {
                        view.displayMessageNewLine("No Cards in Deck");
                    }
                } else {
                    view.displayMessageNewLine("No Deck with given name exists");
                }
            }
        }
    }

    /**
     * Displays the contents of the given deck.
     * If the deck is empty, a message is shown.
     *
     * @param deck the deck to be displayed
     */
    public JPanel displayDeckContent(HashMap<String, CardModel> deck) {
        if (!deck.isEmpty()) {
            return (view.displayDeckContent(deck));
        } else {
            return (new JPanel());
        }
    }

    public void sellDeck(String name) {
        if (name == null || name.equals("-999")) {
            DialogUtil.showError(null, "Sell Deck Cancelled", "Cancelled");
        } else {
            DeckModel deck = sharedCollection.getDeckCollection().get(name);

            if (deck.getSellingPrice() == -1) {
                DialogUtil.showError(null, "Deck is not sellable", "Cancelled");
            } else {
                sharedCollection.setMoney(sharedCollection.getMoney() + deck.getSellingPrice());
                DialogUtil.showInfo(null, "Binder sold, you now have cash total of " + sharedCollection.getMoney(),
                        "Success");
                sharedCollection.removeDeckCollection(name);
                ;
            }
        }
    }

    /**
     * Allows the user to view a card in the deck by name or number.
     *
     * @param deck the deck containing the cards to choose from
     */
    public void chooseCardFromDeck(HashMap<String, CardModel> deck) {
        CardView cardView = new CardView(); // Used to display card details
        String toView = view.viewCardChoice(); // Ask user if they want to view a card
        String selectionOption;
        String cardName;
        int cardNumber;

        // Proceed only if user inputs "Y" or "y"
        if (toView.equalsIgnoreCase("Y")) {
            // Ask if user wants to select by name or number
            selectionOption = view.cardSelectionOption();

            // If user chooses to select by card name
            if (selectionOption.equals("name")) {
                cardName = view.setCardName();

                // Check if the specified card exists in the deck
                if (deck.containsKey(cardName)) {
                    cardView.displayCard(deck, cardName, 0);
                } else {
                    view.displayMessageNewLine("Card does not exist in Deck");
                }

                // If user chooses to select by card number
            } else if (selectionOption.equals("number")) {
                cardNumber = view.setCardNumber();

                if (cardNumber <= deck.size()) {
                    ArrayList<String> cardByKey = new ArrayList<>(deck.keySet());
                    Collections.sort(cardByKey);

                    CardModel cardModel = deck.get(cardByKey.get((cardNumber - 1)));

                    cardView.displayCard(deck, cardModel.getName(), 0);
                } else {
                    view.displayMessageNewLine("Invalid input, card with number does not exist");
                }
            } else {
                view.displayMessageNewLine("Invalid input, exiting deck view");
            }
        } else {
            view.displayMessageNewLine("Exiting Deck View");
        }
    }

    /**
     * Displays all available decks in the shared collection.
     * If there are no decks, an appropriate message is shown.
     */
    public JPanel displayDecks() {
        HashMap<String, DeckModel> deckCollection = sharedCollection.getDeckCollection();

        if (!deckCollection.isEmpty()) {
            return (view.displayDecks(deckCollection));
        } else {
            DialogUtil.showWarning(null, "No Decks yet...", "Decks Warning");
        }
        return view.basicPanel("Currently no decks");
    }

    private void refreshPanel(JPanel uiPanel, JPanel element) {
        uiPanel.removeAll();
        uiPanel.add(element);
        uiPanel.revalidate();
        uiPanel.repaint();
    }
}
