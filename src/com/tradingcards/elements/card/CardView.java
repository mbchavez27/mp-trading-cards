package com.tradingcards.elements.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class CardView {

    private Scanner scanner = new Scanner(System.in);

    public String setCardName() {
        System.out.print("Give Card Name: ");
        return scanner.nextLine();
    }

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
            case 1 -> {
                rarity = "Common";
            }
            case 2 -> {
                rarity = "Uncommon";
            }
            case 3 -> {
                rarity = "Rare";
            }
            case 4 -> {
                rarity = "Legendary";
            }
        }

        return rarity;
    }

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
            case 1 -> {
                variant = "Normal";
            }
            case 2 -> {
                variant = "Extended-art";
            }
            case 3 -> {
                variant = "Full-art";
            }
            case 4 -> {
                variant = "Alt-art";
            }
        }

        return variant;
    }

    public double setCardValue() {
        System.out.print("Give Card Value: ");
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public int setCardQuantity() {
        System.out.print("Give Card Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        return quantity;
    }

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
