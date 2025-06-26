package com.tradingcards.elements.deck;

import com.tradingcards.elements.binder.BinderModel;
import com.tradingcards.elements.card.CardModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * View class responsible for handling user input related to deck operations.
 */
public class DeckView {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to input the name of the deck.
     *
     * @return the deck name entered by the user
     */
    public String setDeckName() {
        System.out.print("Give Deck Name: ");
        return scanner.nextLine();
    }

    public String setCardName(){
        System.out.print("Give Card Name in Deck: ");
        return scanner.nextLine();
    }

    public int setCardNumber(){
        System.out.print("Give Card Number in Deck: ");
        return scanner.nextInt();
    }

    public String viewCardChoice(){
        System.out.print("Would you like to view a Card in the deck? [Y/N]");
        return scanner.nextLine();
    }

    public String cardSelectionOption(){
        System.out.println("Indicate card selection mode");
        System.out.print("Input [name/number]: ");
        return scanner.nextLine();
    }

    public void displayDecks(HashMap<String, DeckModel> deckCollection){
        ArrayList<String> deckKeys = new ArrayList<>(deckCollection.keySet());
        Collections.sort(deckKeys);

        System.out.println("------------------------------------");
        System.out.println("Current Decks:");
        System.out.println("");

        for (String deckNames : deckKeys) {
            System.out.println(deckNames);
        }
    }

    public void displayDeckContent(HashMap<String, CardModel> deck){
        ArrayList<String> cardByKey = new ArrayList<>(deck.keySet());
        Collections.sort(cardByKey);
        int counter = 0;
        System.out.println("------------------------------------");
        System.out.println("Deck Contents:");
        System.out.println("");

        for (String name : cardByKey){
            counter += 1;
            System.out.println("Card Name: " + deck.get(name).getName() +"\n");
            System.out.println("Card Number: " + counter);
        }
    }

    public void displayMessageNewLine(String message){
        System.out.println(message);
    }




}
