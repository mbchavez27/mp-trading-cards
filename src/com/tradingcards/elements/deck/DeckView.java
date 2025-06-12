package com.tradingcards.elements.deck;

import java.util.Scanner;

public class DeckView {

    private Scanner scanner = new Scanner(System.in);

    public String setDeckName() {
        System.out.print("Give Deck Name: ");
        return scanner.nextLine();
    }
}
