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

    public void displayErrorNewLine(String message){
        System.err.println(message);
    }

    public void displayBinders(HashMap<String, BinderModel> binderCollection){
        ArrayList<String> binderKeys = new ArrayList<>(binderCollection.keySet());
        Collections.sort(binderKeys);

        System.out.println("------------------------------------");
        System.out.println("Current Binders:");
        System.out.println("");

        for (String binderNames : binderKeys) {
            System.out.println(binderNames);
        }
    }

    public void displayBinderContent(HashMap<String, CardModel> binder){
        ArrayList<String> cardByKey = new ArrayList<>(binder.keySet());
        Collections.sort(cardByKey);

        System.out.println("------------------------------------");
        System.out.println("Binder Contents:");
        System.out.println("");

        for (String name : cardByKey){
            if (binder.get(name).getQuantity() > 1){
                for (int i = 1; i <= binder.get(name).getQuantity(); i++){
                    System.out.println("Card Name: " + binder.get(name).getName());
                }
            } else {
                System.out.println("Card Name: " + binder.get(name).getName());
            }

        }
    }
}
