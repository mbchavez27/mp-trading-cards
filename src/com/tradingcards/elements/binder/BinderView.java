package com.tradingcards.elements.binder;

import com.tradingcards.elements.card.CardModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

    public void displayMessageNewLine(String message){
        System.out.println(message);
    }

    public void displayBinders(HashMap<String, BinderModel> binderCollection){
        // Extract the keys (binder names) from the binder collection
        ArrayList<String> binderKeys = new ArrayList<>(binderCollection.keySet());
        // Sort the binder names alphabetically
        Collections.sort(binderKeys);

        System.out.println("------------------------------------");
        System.out.println("Current Binders:");
        System.out.println("");

        // Print each binder name in sorted order
        for (String binderNames : binderKeys) {
            System.out.println(binderNames);
        }
    }

    public void displayBinderContent(HashMap<String, CardModel> binder){
        // Get all card names (keys) from the binder and sort them alphabetically
        ArrayList<String> cardByKey = new ArrayList<>(binder.keySet());
        Collections.sort(cardByKey);

        System.out.println("------------------------------------");
        System.out.println("Binder Contents:");
        System.out.println("");

        // Loop through each card name in sorted order
        for (String name : cardByKey){
            // If the card quantity is more than 1, print the name multiple times
            if (binder.get(name).getQuantity() > 1){
                for (int i = 1; i <= binder.get(name).getQuantity(); i++){
                    System.out.println("Card Name: " + binder.get(name).getName());
                }
            } else {
                // If only 1 copy exists, print it once
                System.out.println("Card Name: " + binder.get(name).getName());
            }

        }
    }
}
