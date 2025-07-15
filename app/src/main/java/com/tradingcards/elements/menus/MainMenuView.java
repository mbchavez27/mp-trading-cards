package com.tradingcards.elements.menus;

import com.tradingcards.elements.binder.BinderController;
import com.tradingcards.elements.binder.BinderView;
import com.tradingcards.elements.card.CardController;
import com.tradingcards.elements.card.CardView;
import com.tradingcards.elements.collection.CollectionModel;
import com.tradingcards.elements.deck.DeckController;
import com.tradingcards.elements.deck.DeckView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * Controller class for handling the main menu of the Trading Card Inventory
 * System. Initializes and coordinates all component controllers and handles
 * user input to navigate the system.
 */
public class MainMenuView {

    /** Shared collection that holds cards, binders, and decks. */
    private CollectionModel sharedCollection = new CollectionModel();

    /**
     * Starts and runs the main menu loop. Handles user input to navigate
     * through different functionalities such as managing cards, binders, and
     * decks.
     */

    private Scene scene;

    public MainMenuView(Stage stage){
        CardView cardView = new CardView();
        CardController cardController = new CardController(sharedCollection, cardView);

        BinderView binderView = new BinderView();
        BinderController binderController = new BinderController(sharedCollection, binderView);

        DeckView deckView = new DeckView();
        DeckController deckController = new DeckController(sharedCollection, deckView);

        Button addCardBtn = new Button("Add Card");
        addCardBtn.setOnAction(e -> System.out.println("ADD CARD"));

        Button addBinderBtn = new Button("Add Binder");
        addBinderBtn.setOnAction(e -> System.out.println("ADD BINDER"));

        Button addDeckBtn = new Button("Add Deck");
        addDeckBtn.setOnAction(e -> System.out.println("ADD Deck"));

        Button exitBtn = new Button("Exit Menu");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(addCardBtn, addBinderBtn, addDeckBtn);
        scene = new Scene(hbox, 500,500);
    }

    public Scene getScene(){
        return this.scene;
    }
    /*
    public void runMenu() {


        int action;
        Scanner getAction = new Scanner(System.in);

        CardMenu cardMenu;
        BinderMenu binderMenu;
        DeckMenu deckMenu;

        do {
            System.out.println("-------------------------------");
            System.out.println("Trading Card Inventory System:");
            System.out.println("-------------------------------");

            baseMenu();

            System.out.print("Action: ");
            action = getAction.nextInt();
            getAction.nextLine(); // Clears input buffer

            switch (action) {
                case 1 -> {
                    System.out.println("");
                    cardController.addCard();
                    System.out.println("");
                }
                case 2 -> {
                    binderController.addBinder();
                }
                case 3 -> {
                    deckController.addDeck();
                }
                case 4 -> {
                    System.out.println("");
                    cardMenu = new CardMenu(cardController);
                    cardMenu.runMenu();
                    System.out.println("");
                }
                case 5 -> {
                    System.out.println("");
                    binderMenu = new BinderMenu(binderController);
                    binderMenu.runMenu();
                    System.out.println("");
                }
                case 6 -> {
                    System.out.println("");
                    deckMenu = new DeckMenu(deckController);
                    deckMenu.runMenu();
                    System.out.println("");
                }
            }

        } while (action != 0);
        getAction.close();
    }

     */


    /**
     * Displays the base menu options to the user.
     * The options dynamically reflect the current state of the collection.
     */
    private void baseMenu() {
        System.out.println("[1] Add Card");
        System.out.println("[2] Create new Binder");
        System.out.println("[3] Create a new Deck");
        if (!sharedCollection.getCardCollection().isEmpty()) {
            System.out.println("[4] Manage Cards");
        }
        if (!sharedCollection.getBinderCollection().isEmpty()) {
            System.out.println("[5] Manage Binders");
        }
        if (!sharedCollection.getDeckCollection().isEmpty()) {
            System.out.println("[6] Manage Decks");
        }
        System.out.println("[0] Exit Program");
    }
}
