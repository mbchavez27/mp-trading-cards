package com.tradingcards.elements.binder;

import java.util.Scanner;

public class BinderView {

    private Scanner scanner = new Scanner(System.in);

    public String setBinderName() {
        System.out.print("Give Binder Name: ");
        return scanner.nextLine();
    }
}
