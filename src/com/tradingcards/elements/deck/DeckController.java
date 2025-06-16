package com.tradingcards.elements.deck;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.card.CardView;
import com.tradingcards.elements.collection.CollectionModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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

    public void removeCard(){
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, DeckModel> deckCollection = sharedCollection.getDeckCollection();
        HashMap<String, CardModel> deck;
        String cardToRemove;
        boolean taskDone = false;

        CardView cardView = new CardView();
        displayDecks();

        String binderName = view.setDeckName();
        if (deckCollection.containsKey(binderName)){
            deck = deckCollection.get(binderName).getDeck();

            if (deck.isEmpty()){
                System.out.println("Deck is currently empty");
                System.out.println("Add cards to the Deck first");
            } else {
                displayDeckContent(deck);
                do {
                    System.out.println("Indicate card to be deleted");
                    cardToRemove = cardView.setCardName();
                    if (deck.containsKey(cardToRemove)){
                        //TODO: Current code does not handle identical cards being added
                        collection.put(cardToRemove, deck.get(cardToRemove));
                        deck.remove(cardToRemove);
                        System.out.println("Sucessfully transferred Card into Collection");
                        taskDone = true;
                    } else {
                        System.err.println("No Card with given name exists in Binder");
                        System.err.println("Please re-input Card name");
                    }

                } while (!deck.containsKey(cardToRemove) && !taskDone);
            }
        } else {
            System.err.println("No Binder with given name exists");
        }
    }

    public void addCard(){
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, DeckModel> deckCollection = sharedCollection.getDeckCollection();
        CardView cardView = new CardView();
        DeckModel deck;

        String cardToRemove;
        boolean taskDone = false;

        displayDecks();

        String deckName = view.setDeckName();

        if (deckCollection.isEmpty()){
            System.out.println("No cards in collection yet");
            System.out.println("Input cards in collection first");
        } else {
            if (deckCollection.containsKey(deckName)){
                deck = deckCollection.get(deckName);
                cardView.displayCollection(collection);

                do {
                    cardToRemove = cardView.setCardName();
                    if (collection.containsKey(cardToRemove)){

                        if(deck.setDeckCollection(collection.get(cardToRemove), cardToRemove)){
                            collection.remove(cardToRemove);
                            System.out.println("Successfully transferred card into Deck");
                            taskDone = true;
                        }
                    } else {
                        System.err.println("No Card with given name exists in Collection");
                        System.err.println("Please re-input Card name");
                    }
                } while (!collection.containsKey(cardToRemove) && !taskDone);
            } else {
                System.err.println("No Deck with given name exists");
            }
        }
    }

    public void displaySingleDeck(){
        HashMap<String, DeckModel> deckCollection = sharedCollection.getDeckCollection();
        displayDecks();
        System.out.println("Indicate Deck to view");
        String deckName = view.setDeckName();

        if (deckCollection.containsKey(deckName)){
            displayDeckContent(deckCollection.get(deckName).getDeck());
            chooseCardFromDeck(deckCollection.get(deckName).getDeck());
        } else {
            System.err.println("No Deck with given name exists");
        }
    }

    public void displayDeckContent(HashMap<String, CardModel> deck){

        if (!deck.isEmpty()){
            view.displayDeckContent(deck);
        } else {
            System.err.println("No Cards in Deck");
        }
    }


    public void chooseCardFromDeck(HashMap<String, CardModel> deck){
        CardView cardView = new CardView();
        String toView = view.viewCardChoice();
        String selectionOption;
        String cardName;
        int cardNumber;

        if (toView.equals("Y")){
            selectionOption = view.cardSelectionOption();

            if (selectionOption.equals("name")){
                cardName = view.setCardName();

                if (deck.containsKey(cardName)){
                    cardView.displayCard(deck, cardName);
                } else {
                    System.out.println("Card does not exist in Deck");
                }
            } else if (selectionOption.equals("number")){
                cardNumber = view.setCardNumber();

                if (cardNumber <= deck.size()){
                    ArrayList<String> cardByKey = new ArrayList<>(deck.keySet());
                    Collections.sort(cardByKey);

                    CardModel cardModel = deck.get(cardByKey.get((cardNumber-1)));

                    cardView.displayCard(deck, cardModel.getName());
                } else {
                    System.out.println("Invalid input, card with number does not exist");
                }

            } else {
                System.out.println("Invalid input, exiting deck view");
            };
        } else {
            System.out.println("Exiting Deck View");
        }
    }



    public void displayDecks(){
        HashMap<String, DeckModel> deckCollection = sharedCollection.getDeckCollection();

        if (!deckCollection.isEmpty()){
            view.displayDecks(deckCollection);
        } else {
            System.err.println("No Decks made yet");
        }
    }
}
