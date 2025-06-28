package com.tradingcards.elements.menus;

import com.tradingcards.elements.binder.BinderController;
import java.util.Scanner;

/**
 * Menu class responsible for displaying options and handling user interactions
 * for binder-related operations.
 */
public class BinderMenu {

    /** Controller for handling binder logic. */
    private BinderController binderController;

    /** Static Scanner instance used for capturing user input. */
    public static final Scanner GETACTION = new Scanner(System.in);

    /**
     * Constructs a BinderMenu with the given BinderController.
     *
     * @param controller the BinderController used to perform binder operations
     */
    public BinderMenu(BinderController controller) {
        this.binderController = controller;
    }

    /**
     * Runs the binder menu loop, prompting the user for actions and executing
     * corresponding binder operations until the user exits.
     */
    public void runMenu() {
        int action;

        do {
            manageBinders();
            System.out.print("Action: ");
            action = GETACTION.nextInt();
            GETACTION.nextLine(); // Clears input buffer

            switch (action) {
                case 1:
                    binderController.removeBinder();
                    break;
                case 2:
                    binderController.addCard();
                    break;
                case 3:
                    binderController.removeCard();
                    break;
                case 4:
                    binderController.tradeCard();
                    break;
                case 5:
                    binderController.displaySingleBinder();
                    break;
            }

        } while (action != 0);
    }

    /**
     * Displays the binder management submenu options to the user.
     */
    private void manageBinders() {
        System.out.println("-------------------------------");
        System.out.println("Manage Binders:");
        System.out.println("-------------------------------");
        System.out.println("[1] Delete Binder");
        System.out.println("[2] Add Card to Binder");
        System.out.println("[3] Remove Card from Binder");
        System.out.println("[4] Trade Card");
        System.out.println("[5] View Binder");
        System.out.println("[0] Exit Menu");
    }
}
