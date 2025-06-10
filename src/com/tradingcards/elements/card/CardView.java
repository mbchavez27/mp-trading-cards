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
        System.out.print("Give Card Rarity: ");
        return scanner.nextLine();
    }

    public String setCardVariant() {
        System.out.print("Give Card Variant: ");
        return scanner.nextLine();
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
            System.out.println("Card Variant: " + collection.get(cardName).getVariant());
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
