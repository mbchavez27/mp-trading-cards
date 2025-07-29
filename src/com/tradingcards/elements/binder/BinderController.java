package com.tradingcards.elements.binder;

import java.awt.BorderLayout;
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
            view.showWarning("Operation cancelled");
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
                    view.displayMessageNewLine("No Binder with given name exists");
                }
            } else {
                view.showWarning("Operation cancelled");
            }

        } else {
            view.showWarning("Currently no binders in collection");
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
                        view.showWarning("Binder is currently empty, add cards to the Binder first");
                    } else {
                        // Show binder contents before removing a card
                        refreshPanel(panel, displayBinderContent(binder));
                        do {
                            view.showMessage("Indicate card to be deleted");
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
                                view.showMessage("Successfully transferred Card into Collection");
                                taskDone = true;
                            } else {
                                // Card not found in binder, ask for valid input
                                view.showWarning(
                                        "No Card with given name exists in Binder, please re-input Card name");
                            }


                        } while (!binder.containsKey(cardName) && !taskDone);
                    }
                } else {
                    view.showWarning("No Binder with given name exists");
                }
            } else {
                view.showWarning("Operation Cancelled");
            }
        } else {
            view.showWarning("No binders in collection");
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
                    view.showWarning("No Binders in collection yet, make binders first");
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
                                            view.showMessage("Successfully transferred card into binder");
                                        } else {
                                            // create a new card object to store details
                                            cardCopy = createCardCopy(cardInCollection);

                                            // checks if the card is compatible with the binder
                                            if (binder.insertInBinder(cardCopy, cardName)) {
                                                cardInCollection.setQuantity(cardInCollection.getQuantity() - 1);
                                                view.showMessage("Successfully transferred card into binder");
                                            } else {
                                                view.showWarning("Incompatible binder and card types");
                                            }
                                        }
                                        taskDone = true;
                                    } else {
                                        view.showWarning("Binder is already full");
                                        taskDone = true;
                                    }
                                } else {
                                    view.showMessage("Collection currently has zero copies of specified card");
                                }
                            } else {
                                view.showMessage(
                                        "No Card with given name exists in Collection, please re-input Card name");
                            }

                        } while (!collection.containsKey(cardName) && !taskDone);
                    } else {
                        view.showWarning("No Binder with given name exists");
                    }
                }
            }
        } else {
            view.showWarning("Card collection is empty");
        }

    }

    private void refreshPanel(JPanel uiPanel, JPanel element) {
        uiPanel.removeAll();
        uiPanel.add(element);
        uiPanel.revalidate();
        uiPanel.repaint();
    }

    private void executeTradeGreaterLesserThanOne(String incomingCard, String outgoingCard, String binderName,
            boolean[] taskDone) {
        HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
        HashMap<String, BinderModel> binderCollection = sharedCollection.getBinderCollection();
        HashMap<String, CardModel> binder = binderCollection.get(binderName).getBinder();

        CardModel cardInCollectionIncoming, cardCopyIncoming;
        CardModel cardCopyOutgoing;

        // Check if incoming card already exists in the binder
        if (binder.containsKey(incomingCard)) {
            view.showWarning(
                    "Trade failed: Incoming card already exists in the binder.");

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

                    view.showMessage(
                            "Trade successful! " + outgoingCard + " removed, "
                                    + incomingCard + " added.");
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

                    view.showWarning("Type/Variant mismatch");
                    taskDone[0] = true;
                }

            } else {
                // remove the incoming card to undo operation
                collection.remove(incomingCard);
                view.showWarning("Trade failed: Binder is full.");
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
            view.showMessage(
                    "Trade failed: Incoming card already exists in the binder.");

            // subtract one copy of the card since a duplicate copy has already been added
            collection.get(incomingCard).setQuantity(collection.get(incomingCard).getQuantity() - 1);
            taskDone[0] = true;
        } else {
            // Remove outgoing card from binder
            cardCopyOutgoing = createCardCopy(binder.get(outgoingCard));

            // this prevents binder capacity overflow
            if (binder.get(outgoingCard).getQuantity() > 1) {
                System.out.println("TRYING DUPLICATION");
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


                    view.showMessage("Trade successful! " + outgoingCard + " removed, " + incomingCard + " added.");
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

                    view.showWarning("Type/Variant mismatch");
                    taskDone[0] = true;
                }

            } else {
                // remove the incoming card to undo operation
                collection.remove(incomingCard);
                view.showMessage("Trade failed: Binder is full.");
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
                                            view.showMessage("Trade declined");
                                        });
                                    } else {
                                        // difference of cards is less than 1
                                        executeTradeGreaterLesserThanOne(incomingCardName[0], outGoingCardName[0],
                                                binderName[0], taskDone);
                                    }
                                } else {
                                    view.showWarning("Please re-input card with novel details");
                                }

                            } else {
                                view.showWarning("No Card with given name exists in Binder, please re-input Card name");
                            }

                        } else {
                            tradingPanel.removeAll();
                            view.showWarning("Operation Cancelled");
                        }

                    } while (!binder.containsKey(outGoingCardName[0]) && !taskDone[0] && !cancelled);

                } else {
                    view.showWarning("No Cards in Binder");
                    tradingPanel.removeAll();
                }

            } else {
                tradingPanel.removeAll();
                view.showWarning("Binder does not exist");
            }
        } else {
            view.showWarning("Cancelled trade");
            tradingPanel.removeAll();
        }
        tradingPanel.revalidate();
        tradingPanel.repaint();
    }

    /**
     * Trades a card from a binder with a new card from the collection.
     * The trade is allowed based on the card value difference.
     * User has the option to cancel a trade if conditions are not favorable.
     */
    // public void tradeCard() {
    // CardView cardView = new CardView();
    //
    // CardController cardController = new CardController(sharedCollection,
    // cardView);
    //
    // HashMap<String, BinderModel> binderCollection =
    // sharedCollection.getBinderCollection();
    //
    // HashMap<String, CardModel> collection = sharedCollection.getCardCollection();
    //
    // HashMap<String, CardModel> binder;
    //
    // BinderModel binderModel;
    //
    // String outGoingCardName;
    //
    // boolean taskDone = false;
    //
    // CardModel cardInCollection;
    // CardModel cardCopy;
    //
    // boolean cancelled = false;
    // displayBinders();
    //
    // view.displayMessageNewLine("Indicate binder to view");
    //
    // view.displayMessageNewLine("Enter \"-999\" to cancel");
    //
    // // GUI component
    // String binderName = view.setBinderName();
    //
    // if (binderName.equals(EXIT_CODE))
    // cancelled = true;
    //
    // if (!cancelled) {
    // if (binderCollection.containsKey(binderName)) {
    // binderModel = binderCollection.get(binderName);
    // binder = binderCollection.get(binderName).getBinder();
    // if (!binder.isEmpty()) {
    // displayBinderContent(binder);
    // do {
    // view.displayMessageNewLine("Enter \"-999\" to cancel");
    // view.displayMessageNewLine("Indicate card to be traded");
    // outGoingCardName = cardView.setCardName();
    // if (outGoingCardName.equals(EXIT_CODE))
    // cancelled = true;
    //
    // if (!cancelled) {
    // if (binder.containsKey(outGoingCardName)) {
    // view.displayMessageNewLine("Add new card for the collection");
    // String incomingCardName = cardController.addCard();
    // double difference =
    // sharedCollection.getCardCollection().get(incomingCardName)
    // .getValue()
    // - binder.get(outGoingCardName).getValue();
    // if (difference >= 1) {
    // String choice;
    // view.displayMessageNewLine(
    // "The difference of the ingoing vs outgoing card is " + difference + "\n");
    // choice = view.getBinderChoice("Do you want to cancel the trade? (Y/N)");
    //
    // // Cancels trade and removes the added card from the collection
    // if (choice.equalsIgnoreCase("Y")) {
    // collection.remove(incomingCardName);
    // view.displayMessageNewLine(
    // "Trade cancelled...");
    // } else {
    // // Check if incoming card already exists in the binder
    // if (binder.containsKey(incomingCardName)) {
    // view.displayMessageNewLine(
    // "Trade failed: Incoming card already exists in the binder.");
    // taskDone = true;
    // } else {
    // // Remove outgoing card from binder
    // if (binder.get(outGoingCardName).getQuantity() > 1) {
    // binder.get(outGoingCardName)
    // .setQuantity(binder.get(outGoingCardName).getQuantity() - 1);
    // } else {
    // binder.remove(outGoingCardName);
    // }
    //
    // // Add incoming card to binder
    // if (binder.size() < 20) {
    // cardInCollection = collection.get(incomingCardName);
    // cardCopy = createCardCopy(cardInCollection);
    // binderCollection.get(binderName).insertInBinder(cardCopy,
    // incomingCardName);
    //
    // // Remove from collection
    // cardInCollection.setQuantity(cardInCollection.getQuantity() - 1);
    //
    // view.displayMessageNewLine(
    // "Trade successful! " + outGoingCardName + " removed, "
    // + incomingCardName + " added.");
    // taskDone = true;
    // } else {
    // view.displayMessageNewLine("Trade failed: Binder is full.");
    // taskDone = true; // mark task as done to exit loop
    // }
    // }
    // }
    //
    // } else if (difference < 1) {
    // // Check if incoming card already exists in the binder
    // if (binder.containsKey(incomingCardName)) {
    // view.displayMessageNewLine(
    // "Trade failed: Incoming card already exists in the binder.");
    // taskDone = true;
    // } else {
    // // Remove outgoing card from binder
    // if (binder.get(outGoingCardName).getQuantity() > 1) {
    // binder.get(outGoingCardName)
    // .setQuantity(binder.get(outGoingCardName).getQuantity() - 1);
    // } else {
    // binder.remove(outGoingCardName);
    // }
    //
    // // Add incoming card to binder
    // if (binder.size() < 20) {
    // cardInCollection = collection.get(incomingCardName);
    // cardCopy = createCardCopy(cardInCollection);
    // binderCollection.get(binderName).insertInBinder(cardCopy, incomingCardName);
    //
    // // Remove from collection
    // cardInCollection.setQuantity(cardInCollection.getQuantity() - 1);
    //
    // view.displayMessageNewLine(
    // "Trade successful! " + outGoingCardName + " removed, "
    // + incomingCardName + " added.");
    // taskDone = true;
    // } else {
    // view.displayMessageNewLine("Trade failed: Binder is full.");
    // taskDone = true; // mark task as done to exit loop
    // }
    // }
    // }
    // } else {
    // view.displayMessageNewLine("No Card with given name exists in Binder");
    // view.displayMessageNewLine("Please re-input Card name");
    // }
    // }
    // } while (!binder.containsKey(outGoingCardName) && !taskDone);
    // } else {
    // view.displayMessageNewLine("No Cards in Binder");
    // }
    // } else {
    // view.displayMessageNewLine("No Binder with given name exists");
    // }
    // }
    // }

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
