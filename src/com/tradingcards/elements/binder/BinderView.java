package com.tradingcards.elements.binder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.tradingcards.elements.binder.types.CollectorBinder;
import com.tradingcards.elements.binder.types.LuxuryBinder;
import com.tradingcards.elements.binder.types.NonCuratedBinder;
import com.tradingcards.elements.binder.types.PauperBinder;
import com.tradingcards.elements.binder.types.RaresBinder;
import com.tradingcards.elements.card.CardModel;

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
        return JOptionPane.showInputDialog(null, "Give Binder Name (Enter -999 to cancel):");
    }

    public String setBinderType() {
        String[] binderTypes = { "Non-curated Binder", "Sellable: Pauper Binder", "Sellable: Rares Binder",
                "Sellable: Luxury Binder", "Collector Binder" };

        String selectedType = (String) JOptionPane.showInputDialog(
                null,
                "Select Binder Type:",
                "Binder Type Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                binderTypes,
                binderTypes[0]);

        if (selectedType == null) {
            return null;
        }

        return selectedType;
    }

    public BinderModel showBinderForm() {
        String name = setBinderName();
        if (name == null || name.equals("-999"))
            return null;

        String type = setBinderType();
        if (type == null || type.equals("-999"))
            return null;

        BinderModel newBinder = switch (type) {
            case "Non-curated Binder" -> new NonCuratedBinder("Non-Curated");
            case "Sellable: Pauper Binder" -> new PauperBinder("Pauper");
            case "Sellable: Rares Binder" -> new RaresBinder("Rares");
            case "Sellable: Luxury Binder" -> new LuxuryBinder("Luxury");
            case "Collector Binder" -> new CollectorBinder("Collector");
            default -> null;
        };

        if (newBinder == null)
            return null;

        newBinder.setName(name);
        return newBinder;
    }

    /**
     * Displays a message followed by a newline.
     *
     * @param message the message to display
     */
    public void displayMessageNewLine(String message) {
        System.out.println(message);
    }

    /**
     * Displays a prompt message and retrieves user input.
     *
     * @param message the message to prompt the user
     * @return the user's input as a string
     */
    public String getBinderChoice(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    /**
     * Displays the list of all binder names in the binder collection.
     *
     * @param binderCollection a map containing binder names and their models
     */
    public void displayBinders(HashMap<String, BinderModel> binderCollection) {
        ArrayList<String> binderKeys = new ArrayList<>(binderCollection.keySet());
        Collections.sort(binderKeys);

        System.out.println("------------------------------------");
        System.out.println("Current Binders:");
        System.out.println("");

        for (String binderNames : binderKeys) {
            System.out.println(binderNames);
        }
        System.out.println("");
    }

    /**
     * Displays the contents of a binder, including each card's name.
     * If a card has more than one copy, it is listed multiple times.
     *
     * @param binder a map containing card names and their models
     */
    public void displayBinderContent(HashMap<String, CardModel> binder) {
        ArrayList<String> cardByKey = new ArrayList<>(binder.keySet());
        Collections.sort(cardByKey);

        System.out.println("------------------------------------");
        System.out.println("Binder Contents:");
        System.out.println("");

        for (String name : cardByKey) {
            // If there are multiple copies of the card
            if (binder.get(name).getQuantity() > 1) {
                // Print the card name once per copy
                for (int i = 1; i <= binder.get(name).getQuantity(); i++) {
                    System.out.println("Card Name: " + binder.get(name).getName());
                }
            } else {
                // If only one copy, print once
                System.out.println("Card Name: " + binder.get(name).getName());
            }
        }
        System.out.println("");
    }
}
