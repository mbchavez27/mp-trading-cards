package com.tradingcards.elements.menus.cardMenu;

import com.tradingcards.MainFrame;
import com.tradingcards.elements.card.CardController;
import com.tradingcards.elements.card.CardView;
import com.tradingcards.elements.menus.mainMenu.MainMenuView;

/**
 * Controller class for handling logic and user interactions in the Card Menu.
 * It manages actions such as displaying the card collection, showing individual
 * cards, modifying quantities, and selling cards.
 */
public class CardMenuController {

    /** Controller that manages card-related logic and data. */
    private final CardController cardController;

    /** View for interacting with card input/output dialogs. */
    private final CardView cardView;

    /** View component for displaying the Card Menu UI. */
    private final CardMenuView view;

    /** Reference to the main application frame for switching panels. */
    private final MainFrame mainFrame;

    /** View component for the main menu. */
    private final MainMenuView mainMenuView;

    /**
     * Constructs a new {@code CardMenuController} with all required components.
     *
     * @param view           the card menu view
     * @param cardController the controller that handles card logic
     * @param cardView       the view that prompts and displays card dialogs
     * @param mainMenuView   the main menu view for navigation
     * @param mainFrame      the main application frame for panel switching
     */
    public CardMenuController(CardMenuView view, CardController cardController, CardView cardView,
            MainMenuView mainMenuView, MainFrame mainFrame) {
        this.cardController = cardController;
        this.cardView = cardView;
        this.view = view;
        this.mainMenuView = mainMenuView;
        this.mainFrame = mainFrame;
    }

    /**
     * Initializes and sets up all UI actions for the Card Menu view.
     * Connects UI buttons to the corresponding controller logic.
     */
    public void start() {
        // Display the full card collection initially
        view.setDataInPanel(cardController.displayCollection());

        // Action: Return to main menu
        view.setBackAction(e -> {
            mainMenuView.updateMoneyLabel();
            mainMenuView.updateButtonStatus();
            mainFrame.showPanel("mainMenu");
        });

        // Action: Display a specific card
        view.setDisplayCardAction(e -> {
            view.setDataInPanel(cardController.displayCollection()); // Show full collection first
            view.setDataInPanel(cardController.displayCard()); // Then show selected card details
        });

        // Action: Display the entire collection
        view.setDisplayCollectionAction(e -> {
            view.setDataInPanel(cardController.displayCollection());
        });

        // Action: Modify the quantity of a specific card
        view.setModifyQuantityAction(e -> {
            view.setDataInPanel(cardController.displayCollection(0)); // Show collection with input
            int newQuantity = cardController.modifyCardQuantity();
            if (newQuantity != -1) {
                view.setDataInPanel(cardController.displayCollection()); // Refresh if modified
            }
        });

        // Action: Sell a card
        view.setSellCardAction(e -> {
            view.setDataInPanel(cardController.displayCollection());
            boolean cardSold = cardController.sellCard(cardView.setCardName());

            if (cardSold) {
                view.setDataInPanel(cardController.displayCollection()); // Refresh collection if sold
            }
        });
    }

    /**
     * Returns the view managed by this controller.
     *
     * @return the {@code CardMenuView} instance
     */
    public CardMenuView getView() {
        return view;
    }
}
