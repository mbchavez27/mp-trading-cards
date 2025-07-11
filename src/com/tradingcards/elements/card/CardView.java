package com.tradingcards.elements.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Provides methods for user interaction related to trading cards, such as
 * creating and viewing card details and collections.
 */
public class CardView {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to input the name of the card.
     *
     * @return the card name entered by the user
     */
    public String setCardName() {
        System.out.print("Give Card Name: ");
        return scanner.nextLine();
    }

    /**
     * Prompts the user to select the rarity of the card from a list of options.
     * Loops until the user enters a valid option (1-4).
     *
     * @return the selected rarity as a string (e.g., "Common", "Uncommon",
     *         "Rare", "Legendary")
     */
    public String setCardRarity() {
        String rarity = "";
        int option;
        do {
            System.out.println("Give Card Rarity: ");
            System.out.println("[1]: Common");
            System.out.println("[2]: Uncommon");
            System.out.println("[3]: Rare");
            System.out.println("[4]: Legendary");
            System.out.println("[-999]: Cancel");
            System.out.print("Rarity: ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option != -999 && (option > 4 || option < 1));

        switch (option) {
            case 1 -> rarity = "Common";
            case 2 -> rarity = "Uncommon";
            case 3 -> rarity = "Rare";
            case 4 -> rarity = "Legendary";
            case -999 -> rarity = "-999";
        }

        return rarity;
    }

    /**
     * Prompts the user to select the variant of the card from a list of
     * options. Loops until the user enters a valid option (1-4).
     *
     * @return the selected variant as a string (e.g., "Normal", "Extended-art",
     *         "Full-art", "Alt-art")
     */
    public String setCardVariant() {
        String variant = "";
        int option;
        do {
            System.out.println("Give Card Variant: ");
            System.out.println("[1]: Normal");
            System.out.println("[2]: Extended-art");
            System.out.println("[3]: Full-art");
            System.out.println("[4]: Alt-art");
            System.out.println("[-999]: Cancel");
            System.out.print("Variant: ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option != -999 && (option > 4 || option < 1));

        switch (option) {
            case 1 -> variant = "Normal";
            case 2 -> variant = "Extended-art";
            case 3 -> variant = "Full-art";
            case 4 -> variant = "Alt-art";
            case -999 -> variant = "-999";
        }

        return variant;
    }

    /**
     * Prompts the user to input the base value of the card.
     *
     * @return the base value entered by the user as a {@code double}
     */
    public double setCardValue() {
        System.out.print("Give Card Value: ");
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    /**
     * Prompts the user to input the quantity of the card.
     *
     * @return the quantity entered by the user as an {@code int}
     */
    public int setCardQuantity() {
        System.out.print("Give NEW Card Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        return quantity;
    }

    /**
     * Displays detailed information about a specific card from the collection.
     *
     * @param collection the HashMap of card name to CardModel
     * @param cardName   the name of the card to display
     */
    public void displayCard(HashMap<String, CardModel> collection, String cardName) {
        displayMessageNewLine("");
        if (collection.containsKey(cardName)){
            if (collection.get(cardName).getQuantity() > 0) {
                displayMessageNewLine("------------------------------------");
                displayMessageNewLine("Card Details:");
                displayMessageNewLine("Card Name: " + collection.get(cardName).getName());
                displayMessageNewLine("Card Rarity: " + collection.get(cardName).getRarity());
                if (collection.get(cardName).getVariant() != null) {
                    displayMessageNewLine("Card Variant: " + collection.get(cardName).getVariant());
                }
                displayMessageNewLine("Card Value: " + collection.get(cardName).getValue());
                displayMessageNewLine("Card Quantity: " + collection.get(cardName).getQuantity());
                displayMessageNewLine("------------------------------------");
            } else if (collection.get(cardName).getQuantity() == 0) {
                displayMessageNewLine("-------------------------------");
                displayMessageNewLine(String.format("Card %s is empty", cardName));
                displayMessageNewLine("-------------------------------");
            }
        } else {
            displayMessageNewLine("-------------------------------");
            displayMessageNewLine(String.format("Card %s does not exist", cardName));
            displayMessageNewLine("-------------------------------");
        }
    }

    /**
     * Displays card details (used for decks), omitting quantity.
     *
     * @param collection the collection containing the card
     * @param cardName   the name of the card to display
     * @param mode       an unused parameter (used to overload method signature)
     */
    public void displayCard(HashMap<String, CardModel> collection, String cardName, int mode) {
        displayMessageNewLine("");
        displayMessageNewLine("------------------------------------");
        displayMessageNewLine("Card Details:");
        displayMessageNewLine("Card Name: " + collection.get(cardName).getName());
        displayMessageNewLine("Card Rarity: " + collection.get(cardName).getRarity());
        if (collection.get(cardName).getVariant() != null) {
            displayMessageNewLine("Card Variant: " + collection.get(cardName).getVariant());
        }
        displayMessageNewLine("Card Value: " + collection.get(cardName).getValue());
        displayMessageNewLine("------------------------------------");
    }

    /**
     * Displays all cards in the collection with their quantities, regardless of
     * value.
     *
     * @param collection the card collection to display
     * @param mode       display mode; mode 0 displays all including zeroes
     */
    public void displayCollection(HashMap<String, CardModel> collection, int mode) {
        ArrayList<String> cardByKey = new ArrayList<>(collection.keySet());
        Collections.sort(cardByKey);
        displayMessageNewLine("------------------------------------");
        displayMessageNewLine("Collection:");
        displayMessageNewLine("");

        for (String name : cardByKey) {
            if (mode == 0) {
                displayMessageNewLine("Card Name: " + collection.get(name).getName());
                displayMessageNewLine("Card Quantity: " + collection.get(name).getQuantity());
                displayMessageNewLine("");
            }
        }
    }

    /**
     * Displays all cards in the collection that have a quantity greater than zero.
     *
     * @param collection the card collection to display
     */
    public void displayCollection(HashMap<String, CardModel> collection) {
        ArrayList<String> cardByKey = new ArrayList<>(collection.keySet());
        Collections.sort(cardByKey);
        displayMessageNewLine("------------------------------------");
        displayMessageNewLine("Collection:");
        displayMessageNewLine("");

        for (String name : cardByKey) {
            if (collection.get(name).getQuantity() > 0) {
                displayMessageNewLine("Card Name: " + collection.get(name).getName());
                displayMessageNewLine("Card Quantity: " + collection.get(name).getQuantity());
                displayMessageNewLine("");
            }
        }
    }

    /**
     * Prompts the user to confirm whether to increase the card quantity.
     *
     * @param name the name of the card in question
     * @return true if user selects Y, false if N
     */
    public boolean allowIncreaseCardCount(String name) {
        System.out.print("Do you want to add another copy for Card: " + name + "? (Y/N): ");
        String action = scanner.nextLine().trim();

        while (!action.equalsIgnoreCase("Y") && !action.equalsIgnoreCase("N")) {
            System.out.print("Please enter Y or N only: ");
            action = scanner.nextLine().trim();
        }

        return action.equalsIgnoreCase("Y");
    }

    /**
     * Displays a message on a new line.
     *
     * @param message the message to be printed
     */
    public void displayMessageNewLine(String message) {
        System.out.println(message);
    }
}
