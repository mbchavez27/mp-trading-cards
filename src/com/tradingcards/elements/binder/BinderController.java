package com.tradingcards.elements.binder;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JPanel;

import com.tradingcards.elements.card.CardController;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.card.CardView;
import com.tradingcards.elements.collection.CollectionModel;
import com.tradingcards.elements.menus.menuUtils.DialogUtil;

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
        BinderModel binder = view.showBinderForm();

        if (binder == null) {
            DialogUtil.showWarning(null, "Binder creation cancelled.", "Cancelled");
        } else {
            if (sharedCollection.getBinderCollection().containsKey(binder.getName())) {
                DialogUtil.showWarning(null, "Binder of the same name already exists", "Duplicate Binder");
            } else {
                sharedCollection.setBinderCollection(binder, binder.getName());
                DialogUtil.showInfo(
                        null,
                        "New " + binder.getType() + " binder successfully added to collection!",
                        "New Binder");
            }
        }
    }

    /**
     * Removes a binder from the shared collection based on user input.
     * All cards in the binder are returned to the main card collection.
     */
    public void removeBinder(JPanel panel) {
        // Display the list of binders to the user
        refreshPanel(panel, displayBinders());
        boolean cancelled = false;

        String name = view.setBinderName();

        // If user inputs the exit code, mark as cancelled
        if (name.equals(EXIT_CODE))
            cancelled = true;

        // Proceed only if the operation wasn't cancelled
        if (!cancelled) {
            HashMap<String, BinderModel> binders = sharedCollection.getBinderCollection();

            // checks binder collection
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
                DialogUtil.showInfo(panel, "Binder \"" + name + "\" removed and cards returned", "Binder Removed");
                refreshPanel(panel, displayBinders());
            } else {
                DialogUtil.showInfo(panel, "Binder \"" + name + "\" not found", "Binder Not Found");
            }
        } else {
            DialogUtil.showWarning(null,"Operation cancelled", "Warning");
        }
    }

    /**
     * Displays all binders currently stored in the collection.
     */
    public JPanel displayBinders() {
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();

        if (!binderCollection.isEmpty()) {
            return (view.displayBinders(binderCollection));
        } else {
            DialogUtil.showWarning(null, "No Binders in collection", "Binders Warning");
        }

        return view.basicPanel("Currently no binders");
    }

    /**
     * Displays the contents of a single binder selected by the user.
     */
    public void displaySingleBinder(JPanel panel) {
        // Get the collection of binders
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        boolean cancelled = false;
        refreshPanel(panel, displayBinders());

        // Proceed only if there are binders to display
        if (!binderCollection.isEmpty()) {

            // Get the name of the binder to display
            String binderName = view.setBinderName();
            if (binderName.equals(EXIT_CODE) || binderName == null)
                cancelled = true;

            // Proceed only if not cancelled
            if (!cancelled) {
                if (binderCollection.containsKey(binderName)) {
                    refreshPanel(panel, displayBinderContent(binderCollection.get(binderName).getBinder()));
                } else {
                    DialogUtil.showWarning(null,"No Binder with given name exists", "Warning");
                }
            } else {
                DialogUtil.showWarning(null, "Operation cancelled", "Warning");
            }

        } else {
            DialogUtil.showWarning(null,"Currently no binders in collection", "Warning");
        }

    }

    /**
     * Displays the contents of the specified binder.
     *
     * @param binder the map of cards inside the binder
     */
    public JPanel displayBinderContent(HashMap<String, CardModel> binder) {
        if (!binder.isEmpty()) {
            return (view.displayBinderContent(binder));
        } else {
            return (new JPanel());
        }
    }

    /**
     * Removes a card from a specified binder and returns it to the main card
     * collection.
     * Prompts the user to select a binder and card to remove.
     */
    public void removeCard(JPanel panel) {
        // Get references to the main card and binder collections
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        HashMap<String, CardModel> binder;
        String cardName;
        boolean taskDone = false;
        boolean cancelled = false;

        if (!binderCollection.isEmpty()) {
            CardView cardView = new CardView();
            refreshPanel(panel, displayBinders());

            String binderName = view.setBinderName();
            if (binderName == null || binderName.equals(EXIT_CODE))
                cancelled = true;

            // Continue only if user didn't cancel
            if (!cancelled) {
                if (binderCollection.containsKey(binderName)) {
                    // Get the binder's card collection
                    binder = binderCollection.get(binderName).getBinder();

                    // Check if the binder is empty
                    if (binder.isEmpty()) {
                        DialogUtil.showWarning(null, "Binder is currently empty, add cards to the Binder first", "Warning");
                    } else {
                        // Show binder contents before removing a card
                        refreshPanel(panel, displayBinderContent(binder));
                        do {
                            DialogUtil.showMessage(null,"Indicate card to be deleted", "Information", 1);
                            cardName = cardView.setCardName();

                            if (cardName == null || cardName.equals(EXIT_CODE)) {
                                taskDone = true;
                            }
                                // Check if card exists in the binder
                            if (binder.containsKey(cardName) && !taskDone) {
                                // Increase the quantity of the card in the main collection
                                collection.get(cardName).increaseQuantity(1);
                                if (binder.get(cardName).getQuantity() > 1) {
                                    binder.get(cardName).setQuantity(binder.get(cardName).getQuantity() - 1);
                                } else {
                                    // Remove card entirely if only one was present
                                    binder.remove(cardName);
                                }
                                refreshPanel(panel, displayBinderContent(binder));
                                DialogUtil.showMessage(null,"Successfully transferred Card into Collection", "Information", 1);
                                taskDone = true;
                            } else {
                                // Card not found in binder, ask for valid input
                                DialogUtil.showWarning(null,
                                        "No Card with given name exists in Binder, please re-input Card name", "Warning");
                            }


                        } while (!binder.containsKey(cardName) && !taskDone);
                    }
                } else {
                    DialogUtil.showWarning(null,"No Binder with given name exists", "Warning");
                }
            } else {
                DialogUtil.showWarning(null,"Operation Cancelled", "Warning");
            }
        } else {
            DialogUtil.showWarning(null, "No binders in collection", "Warning");
        }
    }

    /**
     * Transfers a card from the shared collection into a specific binder.
     * Constraints: A binder can only hold up to 20 unique cards.
     */
    public void addCard(JPanel panel) {
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
            //displays binders
            refreshPanel(panel, displayBinders());

            // Prompt user for binder name or cancel
            String binderName = view.setBinderName();

            if (binderName == null || binderName.equals(EXIT_CODE))
                cancelled = true;

            if (!cancelled) {
                if (binderCollection.isEmpty()) {
                    DialogUtil.showWarning(null,"No Binders in collection yet, make binders first", "Warning");
                } else {
                    if (binderCollection.containsKey(binderName)) {
                        binder = binderCollection.get(binderName);
                        refreshPanel(panel, cardView.displayCollection(collection));
                        do {
                            cardName = cardView.setCardName();
                            if (cardName == null || cardName.equals(EXIT_CODE))
                                taskDone = true;
                            // checks if the collection has the card
                            if (collection.containsKey(cardName) && !taskDone) {
                                // checks if the collection has a positive number of card copies
                                cardInCollection = collection.get(cardName);

                                if (cardInCollection.getQuantity() > 0) {

                                    // checks if binder can accommodate new card;
                                    if (binder.getBinder().size() < 20) {

                                        // checks if binder already contains the specified card
                                        if (binder.getBinder().containsKey(cardName)) {

                                            cardInBinder = binder.getBinder().get(cardName);
                                            cardInBinder.setQuantity(cardInBinder.getQuantity() + 1);
                                            cardInCollection.setQuantity(cardInCollection.getQuantity() - 1);
                                            DialogUtil.showMessage(null, "Successfully transferred card into binder", "Information", 1);
                                        } else {
                                            // create a new card object to store details
                                            cardCopy = createCardCopy(cardInCollection);

                                            // checks if the card is compatible with the binder
                                            if (binder.insertInBinder(cardCopy, cardName)) {
                                                cardInCollection.setQuantity(cardInCollection.getQuantity() - 1);
                                                DialogUtil.showMessage(null,"Successfully transferred card into binder", "Information", 1);
                                            } else {
                                                DialogUtil.showWarning(null,"Incompatible binder and card types", "Warning");
                                            }
                                        }
                                        taskDone = true;
                                    } else {
                                        DialogUtil.showWarning(null,"Binder is already full", "Warning");
                                        taskDone = true;
                                    }
                                } else {
                                    DialogUtil.showWarning(null,"Collection currently has zero copies of specified card", "Warning");
                                }
                            } else {
                                DialogUtil.showWarning(null,
                                        "No Card with given name exists in Collection, please re-input Card name", "Warning");
                            }

                        } while (!collection.containsKey(cardName) && !taskDone);
                    } else {
                        DialogUtil.showWarning(null,"No Binder with given name exists", "Warning");
                    }
                }
            }
        } else {
            DialogUtil.showWarning(null,"Card collection is empty", "Warning");
        }

    }

    private void refreshPanel(JPanel uiPanel, JPanel element) {
        uiPanel.removeAll();
        uiPanel.add(element);
        uiPanel.revalidate();
        uiPanel.repaint();
    }

    private void executeTradeLesserThanOne(String incomingCard, String outgoingCard, String binderName,
                                           boolean[] taskDone) {
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        HashMap<String, CardModel> binder = binderCollection.get(binderName).getBinder();

        CardModel cardInCollectionIncoming, cardCopyIncoming;
        CardModel cardCopyOutgoing;

        // Check if incoming card already exists in the binder
        if (binder.containsKey(incomingCard)) {
            DialogUtil.showWarning(null,
                    "Trade failed: Incoming card already exists in the binder.", "Warning");

            collection.get(incomingCard).setQuantity(collection.get(incomingCard).getQuantity() - 1);
            taskDone[0] = true;
        } else {
            cardCopyOutgoing = createCardCopy(binder.get(outgoingCard));

            // Remove outgoing card from binder
            if (binder.get(outgoingCard).getQuantity() > 1) {
                binder.get(outgoingCard)
                        .setQuantity(binder.get(outgoingCard).getQuantity() - 1);
            } else {
                binder.remove(outgoingCard);
            }

            // Add incoming card to binder
            if (binder.size() < 20) {
                cardInCollectionIncoming = collection.get(incomingCard);
                cardCopyIncoming = createCardCopy(cardInCollectionIncoming);

                if (binderCollection.get(binderName).insertInBinder(cardCopyIncoming, incomingCard)) {
                    // Remove from collection
                    cardInCollectionIncoming.setQuantity(cardInCollectionIncoming.getQuantity() - 1);

                    DialogUtil.showMessage(null,
                            "Trade successful! " + outgoingCard + " removed, "
                                    + incomingCard + " added.", "Information", 1);
                    taskDone[0] = true;
                } else {

                    // adds the initial outgoing car back to the binder
                    if (binder.get(outgoingCard) == null) {
                        binderCollection.get(binderName).insertInBinder(cardCopyOutgoing, cardCopyOutgoing.getName());
                    } else {
                        binder.get(outgoingCard).setQuantity(binder.get(outgoingCard).getQuantity() + 1);
                    }
                    // remove the incoming card to undo operation
                    collection.remove(incomingCard);

                    DialogUtil.showWarning(null,"Type/Variant mismatch", "Warning");
                    taskDone[0] = true;
                }

            } else {
                // remove the incoming card to undo operation
                collection.remove(incomingCard);
                DialogUtil.showWarning(null,"Trade failed: Binder is full.", "Warning");
                taskDone[0] = true; // mark task as done to exit loop
            }
        }
    }

    private void executeTradeGreaterThanOne(String incomingCard, String outgoingCard, String binderName,
            boolean[] taskDone) {
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        HashMap<String, CardModel> binder = binderCollection.get(binderName).getBinder();

        // Check if incoming card already exists in the binder
        CardModel cardInCollectionIncoming, cardCopyIncoming;
        CardModel cardCopyOutgoing;

        if (binder.containsKey(incomingCard)) {
            DialogUtil.showWarning(null,
                    "Trade failed: Incoming card already exists in the binder.", "Warning");

            // subtract one copy of the card since a duplicate copy has already been added
            collection.get(incomingCard).setQuantity(collection.get(incomingCard).getQuantity() - 1);
            taskDone[0] = true;
        } else {
            // Remove outgoing card from binder
            //ERROR HERE
            cardCopyOutgoing = createCardCopy(binder.get(outgoingCard));

            // this prevents binder capacity overflow
            if (binder.get(outgoingCard).getQuantity() > 1) {
                binder.get(outgoingCard)
                        .setQuantity(binder.get(outgoingCard).getQuantity() - 1);
            } else {
                binder.remove(outgoingCard);
            }

            // Add incoming card to binder
            if (binder.size() < 20) {
                cardInCollectionIncoming = collection.get(incomingCard);
                cardCopyIncoming = createCardCopy(cardInCollectionIncoming);

                // checks if Incoming card can be inserted into binder
                if (binderCollection.get(binderName).insertInBinder(cardCopyIncoming, incomingCard)) {
                    // Remove from collection
                    cardInCollectionIncoming.setQuantity(cardInCollectionIncoming.getQuantity() - 1);

                    DialogUtil.showMessage(null, "Trade successful! " + outgoingCard + " removed, " + incomingCard + " added.", "Information", 1);
                    taskDone[0] = true;
                } else {

                    // adds the initial outgoing car back to the binder
                    if (binder.get(outgoingCard) == null) {
                        binderCollection.get(binderName).insertInBinder(cardCopyOutgoing, cardCopyOutgoing.getName());
                    } else {
                        binder.get(outgoingCard).setQuantity(binder.get(outgoingCard).getQuantity() + 1);
                    }

                    // remove the incoming card to undo operation
                    collection.remove(incomingCard);

                    DialogUtil.showWarning(null,"Type/Variant mismatch", "Warning");
                    taskDone[0] = true;
                }

            } else {
                // remove the incoming card to undo operation
                collection.remove(incomingCard);
                DialogUtil.showWarning(null,"Trade failed: Binder is full.", "Warning");
                taskDone[0] = true; // mark task as done to exit loop
            }
        }
    }

    public void tradeCard(JPanel tradingPanel) {
        CardView cardView = new CardView();

        CardController cardController = new CardController(sharedCollection, cardView);

        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();

        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();

        HashMap<String, CardModel> binder;

        BinderModel binderModel;

        final boolean[] taskDone = new boolean[1]; // set to false by default
        final boolean[] validAddCard = new boolean[1]; // set to false by default
        final String[] outGoingCardName = new String[1];
        final String[] incomingCardName = new String[1];
        final String[] binderName = new String[1];

        // final CardModel[] cardInCollection = new CardModel[1];
        // final CardModel[] cardCopy = new CardModel[1];

        boolean cancelled = false;

        // GUI component to display current binders
        refreshPanel(tradingPanel, displayBinders());

        // GUI component
        binderName[0] = view.setBinderName("Indicate which binder to trade from");

        if (binderName[0] == null || binderName[0].equals(EXIT_CODE))
            cancelled = true;

        if (!cancelled) {
            // checks if bindercollection has specified binder
            if (binderCollection.containsKey(binderName[0])) {

                binder = binderCollection.get(binderName[0]).getBinder();
                // checks if binder has cards to choose from
                if (!binder.isEmpty()) {
                    // displays binder content
                    refreshPanel(tradingPanel, displayBinderContent(binder));
                    do {
                        // selects card from selected binder
                        outGoingCardName[0] = view.setCardName();

                        if (outGoingCardName[0].equals(EXIT_CODE))
                            cancelled = true;

                        if (!cancelled) {
                            // checks if binder contains specified card
                            if (binder.containsKey(outGoingCardName[0])) {
                                // adds a new card to collection
                                incomingCardName[0] = cardController.addCard(validAddCard);

                                if (validAddCard[0]) {
                                    // gets absolute value difference
                                    double difference = Math.abs(
                                            sharedCollection.getCardCollection().get(incomingCardName[0]).getValue()
                                                    - binder.get(outGoingCardName[0]).getValue());

                                    refreshPanel(tradingPanel, view.showMainCardDisplay(collection, outGoingCardName[0],
                                            incomingCardName[0], difference));

                                    // executeTrade(incomingCardName, outGoingCardName, binder)
                                    if (difference >= 1) {
                                        // confirmation panel gui

                                        //removes action listeners
                                        for (ActionListener al : view.getButtonConfirm().getActionListeners()) {
                                            view.getButtonConfirm().removeActionListener(al);
                                        }

                                        tradingPanel.add(view.showTradeConfirmation(), BorderLayout.SOUTH);

                                        // user presses accept
                                        view.getButtonConfirm().addActionListener(e -> {
                                            refreshPanel(tradingPanel, new JPanel());
                                            executeTradeGreaterThanOne(incomingCardName[0], outGoingCardName[0],
                                                    binderName[0], taskDone);
                                        });

                                        // user presses decline
                                        view.getButtonDecline().addActionListener(e -> {
                                            refreshPanel(tradingPanel, new JPanel());
                                            collection.remove(incomingCardName[0]);
                                            DialogUtil.showMessage(null,"Trade declined", "Information", 1);
                                        });
                                    } else {
                                        // difference of cards is less than 1
                                        executeTradeLesserThanOne(incomingCardName[0], outGoingCardName[0],
                                                binderName[0], taskDone);
                                    }
                                } else {
                                    DialogUtil.showWarning(null,"Please re-input card with novel details", "Warning");
                                }

                            } else {
                                DialogUtil.showWarning(null,"No Card with given name exists in Binder, please re-input Card name", "Warning");
                            }

                        } else {
                            tradingPanel.removeAll();
                            DialogUtil.showWarning(null, "Operation Cancelled", "Warning");
                        }

                    } while (!binder.containsKey(outGoingCardName[0]) && !taskDone[0] && !cancelled);

                } else {
                    DialogUtil.showWarning(null,"No Cards in Binder", "Warning");
                    tradingPanel.removeAll();
                }

            } else {
                tradingPanel.removeAll();
                DialogUtil.showWarning(null,"Binder does not exist", "Warning");
            }
        } else {
            DialogUtil.showWarning(null,"Cancelled trade", "Warning");
            tradingPanel.removeAll();
        }
        tradingPanel.revalidate();
        tradingPanel.repaint();
    }


    public void sellBinder(String name) {
        if (name == null || name.equals("-999")) {
            DialogUtil.showError(null, "Sell Binder Cancelled", "Cancelled");
        } else {
            BinderModel binder = sharedCollection.getBinderCollection().get(name);
            if (binder.getSellingPrice() == -1) {
                DialogUtil.showError(null, "Binder is not sellable", "Cancelled");
            } else {
                sharedCollection.setMoney(sharedCollection.getMoney() + binder.getSellingPrice());
                DialogUtil.showInfo(null, "Binder sold, you now have cash total of " + sharedCollection.getMoney(),
                        "Success");
                sharedCollection.removeBinderCollection(name);
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
