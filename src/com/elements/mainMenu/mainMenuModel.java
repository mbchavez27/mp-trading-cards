package com.elements.mainMenu;
import com.elements.binder.*;
import com.elements.deck.*;

public class mainMenuModel {
    public String cardMessage(){
        return "1.) Add a card";
    }

    public String binderMessage(){
        return "2.) Create a new binder";
    }

    public String binderMessage(binderController binder){
        return "2.) Manage binders";
    }

    public String deckMessage(){
        return "3.) Create a new deck";
    }

    public String deckMessage(deckController deck){
        return "3.) Manage decks";
    }

}
