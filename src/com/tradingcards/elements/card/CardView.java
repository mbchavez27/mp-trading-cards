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
     * "Rare", "Legendary")
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
            System.out.print("Rarity: ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option > 4 || option < 1);

        switch (option) {
            case 1 ->
                rarity = "Common";
            case 2 ->
                rarity = "Uncommon";
            case 3 ->
                rarity = "Rare";
            case 4 ->
                rarity = "Legendary";
        }

        return rarity;
    }

    /**
     * Prompts the user to select the variant of the card from a list of
     * options. Loops until the user enters a valid option (1-4).
     *
     * @return the selected variant as a string (e.g., "Normal", "Extended-art",
     * "Full-art", "Alt-art")
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
            System.out.print("Variant: ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option > 4 || option < 1);

        switch (option) {
            case 1 ->
                variant = "Normal";
            case 2 ->
                variant = "Extended-art";
            case 3 ->
                variant = "Full-art";
            case 4 ->
                variant = "Alt-art";
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
        System.out.print("Give Card Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        return quantity;
    }

    /**
     * Displays detailed information about a specific card from the collection.
     * If the card with the specified name exists in the collection, this method
     * prints its name, rarity, variant (if available), value, and quantity to
     * the standard output. If the card is not found, an error message is
     * printed instead.
     *
     * @param collection the HashMap containing card names as keys and their
     * corresponding {@code CardModel} objects as values
     * @param cardName the name of the card to be displayed
     */
    public void displayCard(HashMap<String, CardModel> collection, String cardName) {
        System.out.println("");
        if (collection.containsKey(cardName)) {
            System.out.println("------------------------------------");
            System.out.println("Card Details:");
            System.out.println("Card Name: " + collection.get(cardName).getName());
            System.out.println("Card Rarty: " + collection.get(cardName).getRarity());
            if (collection.get(cardName).getVariant() != null) {
                System.out.println("Card Variant: " + collection.get(cardName).getVariant());
            }
            System.out.println("Card Value: " + collection.get(cardName).getValue());
            System.out.println("Card Quantity: " + collection.get(cardName).getQuantity());
            System.out.println("------------------------------------");
        } else {
            System.out.println("-------------------------------");
            System.err.println(String.format("Card %s does not exist", cardName));
            System.out.println("-------------------------------");
        }
    }

    /**
     * Displays the contents of a card collection in alphabetical order by card
     * name. This method takes a {@code HashMap<String, CardModel>} where the
     * key is the card name and the value is a {@code CardModel} object
     * containing details such as the name and quantity. It sorts the keys (card
     * names) alphabetically and prints each card's name and quantity to the
     * standard output.
     *
     * @param collection the HashMap containing card names as keys and their
     * corresponding {@code CardModel} objects as values
     */
    public void displayCollection(HashMap<String, CardModel> collection) {
        ArrayList<String> cardByKey = new ArrayList<>(collection.keySet());
        Collections.sort(cardByKey);
        System.out.println("------------------------------------");
        System.out.println("Collection:");
        System.out.println("");

        for (String name : cardByKey) {
            System.out.println("Card Name: " + collection.get(name).getName());
            System.out.println("Card Quantity: " + collection.get(name).getQuantity());
            System.out.println("");
        }
    }

}
