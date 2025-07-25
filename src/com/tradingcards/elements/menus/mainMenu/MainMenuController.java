package com.tradingcards.elements.menus.mainMenu;//package com.tradingcards.elements.menus.mainMenu;

//
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import javax.swing.JFrame;
//
//import com.tradingcards.elements.binder.BinderController;
//import com.tradingcards.elements.binder.BinderView;
//import com.tradingcards.elements.card.CardController;
//import com.tradingcards.elements.card.CardView;
//import com.tradingcards.elements.collection.CollectionModel;
//import com.tradingcards.elements.deck.DeckController;
//import com.tradingcards.elements.deck.DeckView;
//import com.tradingcards.elements.menus.binderMenu.BinderMenu;
//import com.tradingcards.elements.menus.cardMenu.CardMenuController;
//import com.tradingcards.elements.menus.cardMenu.CardMenuView;
//import com.tradingcards.elements.menus.deckMenu.DeckMenu;
//

import com.tradingcards.MainFrame;
import com.tradingcards.elements.binder.BinderController;
import com.tradingcards.elements.binder.BinderView;
import com.tradingcards.elements.card.CardController;
import com.tradingcards.elements.card.CardView;
import com.tradingcards.elements.collection.CollectionModel;
import com.tradingcards.elements.deck.DeckController;
import com.tradingcards.elements.deck.DeckView;
import com.tradingcards.elements.menus.binderMenu.BinderMenuController;
import com.tradingcards.elements.menus.binderMenu.BinderMenuView;
import com.tradingcards.elements.menus.cardMenu.CardMenuController;
import com.tradingcards.elements.menus.cardMenu.CardMenuView;

//
// * Controller class for handling the main menu of the Trading Card Inventory
// * System. Initializes and coordinates all component controllers and handles
// * user input to navigate the system.
// */
//public class MainMenuController {
//
//    /** Shared collection that holds cards, binders, and decks. */
//    private CollectionModel sharedCollection = new CollectionModel();
//
//    /**
//     * Starts and runs the main menu loop. Handles user input to navigate
//     * through different functionalities such as managing cards, binders, and
//     * decks.
//     */
//    public void start() {
//        // Initialize Needed MVC
//        CardView cardView = new CardView();
//        CardController cardController = new CardController(sharedCollection, cardView);
//
//        BinderView binderView = new BinderView();
//        BinderController binderController = new BinderController(sharedCollection, binderView);
//
//        DeckView deckView = new DeckView();
//        DeckController deckController = new DeckController(sharedCollection, deckView);
//
//        CardMenuController cardMenu = new CardMenuController(new CardMenuView(), cardController);
//        BinderMenu binderMenu;
//        DeckMenu deckMenu;
//
//        MainMenuView view = new MainMenuView();
//
//        // Show Menu
//        view.setVisible(true);
//
//        view.setAddCardAction(e -> {
//            view.setVisible(false);
//            cardController.addCard();
//            view.setVisible(true);
//            if (!sharedCollection.getCardCollection().isEmpty()) {
//                view.ShowManageCardBtn();
//            }
//
//        });
//
//
//        view.setNewBinderAction(e -> {
//            view.setVisible(false);
//            binderController.addBinder();
//            view.setVisible(true);
//            if (!sharedCollection.getBinderCollection().isEmpty()) {
//                view.ShowManageBinderBtn();
//            }
//        });
//
//        view.setManageCardsAction(e -> {
//            view.setVisible(false);
//
//            CardMenuView cardMenuView = cardMenu.getView();
//            cardMenuView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            cardMenuView.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosed(WindowEvent e) {
//                    view.setVisible(true);
//                }
//            });
//
//            cardMenu.start();
//        });
//D
//        view.setCloseApplicationButton(e -> System.exit(0));
//
//    }
//}

public class MainMenuController {
    private final CollectionModel sharedCollection = new CollectionModel();

    public void start() {
        MainFrame mainFrame = new MainFrame();
        MainMenuView mainMenuView = new MainMenuView(sharedCollection);

        CardMenuView cardMenuView = new CardMenuView();
        BinderMenuView binderMenuView = new BinderMenuView();

        // Setup MVC subcomponents
        CardView cardView = new CardView();
        CardController cardController = new CardController(sharedCollection, cardView);
        CardMenuController cardMenuController = new CardMenuController(cardMenuView, cardController, cardView,
                mainMenuView,
                mainFrame);

        BinderView binderView = new BinderView();
        BinderController binderController = new BinderController(sharedCollection, binderView);
        BinderMenuController binderMenuController = new BinderMenuController(binderMenuView, binderController, binderView, mainMenuView, mainFrame);

        DeckView deckView = new DeckView();
        DeckController deckController = new DeckController(sharedCollection, deckView);
        // DeckMenuController deckMenu = new DeckMenuController(...);

        // must list down all possible full menu displays
        mainFrame.addPanel("mainMenu", mainMenuView);
        mainFrame.addPanel("manageCardMenu", cardMenuView);
        mainFrame.addPanel("manageBinderMenu", binderMenuView);

        mainFrame.setVisible(true);
        // Selects mainMenu panel as initial menu
        mainFrame.showPanel("mainMenu");

        // Action for Adding Cards
        mainMenuView.setAddCardAction(e -> {
            // opens form for adding card
            cardController.addCard();

            if (!sharedCollection.getCardCollection().isEmpty()) {
                // enables Manage Card button in mainMenuPanel
                mainMenuView.showManageCardBtn();
            }
        });

        // Action for Adding Binders
        mainMenuView.setNewBinderAction(e -> {
            // opens form for adding binder
            binderController.addBinder();

            if (!sharedCollection.getBinderCollection().isEmpty()) {
                // enables Manage Binders button in mainMenuPanel
                mainMenuView.showManageBinderBtn();
            }
        });

        // Action for adding Decks
        mainMenuView.setNewDeckAction(e -> {
            // opens form for adding
            deckController.addDeck();

            if (!sharedCollection.getDeckCollection().isEmpty()) {
                // enables Manage Decks button in MainMenuPanel
                mainMenuView.showManageDeckBtn();
            }
        });

        // Switch panel to Manage Card Menu
        mainMenuView.setManageCardsAction(e -> {
            cardMenuController.start();
            mainFrame.showPanel("manageCardMenu");
        });

        // Switch panel to Mange Binder Menu
        mainMenuView.setManageBindersAction(e -> {
            binderMenuController.start();
            mainFrame.showPanel("manageBinderMenu");
        });

        mainMenuView.setManageDecksAction(e -> {

        });

        mainMenuView.setCloseApplicationButton(e -> System.exit(0));
    }
}
