package com.tradingcards.elements.card;

import java.util.ArrayList;
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
        return scanner.nextDouble();
    }

    public int setCardQuantity() {
        System.out.print("Give Card Value: ");
        return scanner.nextInt();
    }

    public void displayCard(ArrayList<CardModel> cards) {
        System.out.println("Card Details");
        System.out.println(cards.get(0).getName());
    }

}
