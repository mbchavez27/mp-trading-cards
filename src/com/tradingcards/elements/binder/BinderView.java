package com.tradingcards.elements.binder;

import java.util.Scanner;

/**
 * The {@code BinderView} class handles user interaction related to binders,
 * such as prompting the user to input a binder name.
 */
public class BinderView {

    /**
     * Scanner object for reading user input from the console.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to input a name for a binder and returns it.
     *
     * @return the name entered by the user
     */
    public String setBinderName() {
        System.out.print("Give Binder Name: ");
        return scanner.nextLine();
    }
}
