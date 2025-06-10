package com.tradingcards;

import com.tradingcards.elements.card.CardController;
import com.tradingcards.elements.card.CardModel;
import com.tradingcards.elements.card.CardView;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CardModel cardModel = new CardModel();
        CardView cardView = new CardView();
        CardController cardController = new CardController(cardModel, cardView);

        int action = 0;
        Scanner getAction = new Scanner(System.in);

        do {
            System.out.println("-------------------------------");
            System.out.println("Trading Card Inventory System:");
            System.out.println("-------------------------------");
            System.out.println("[1] Add Card");
            System.out.println("[2] Display Card");
            System.out.println("[3] Display Collection");

            System.out.print("Action: ");
            action = getAction.nextInt();

            switch (action) {
                case 1 -> {
                    System.out.println("");
                    cardController.addCard();
                    System.out.println("");
                }

                case 2 -> {
                    System.out.println("");
                    cardController.displayCard();
                    System.out.println("");
                }

                case 3 -> {
                    System.out.println("");
                    cardController.displayCollection();
                    System.out.println("");
                }

            }

        } while (action != 10);
        getAction.close();
    }
}
