package com.tradingcards.elements.card;

public class CardController {
    private CardModel model;
    private CardView view;

    public CardController(CardModel model, CardView view){
        this.model = model;
        this.view = view;
    }

    public void setCardName(String name){
        model.setName(name);
    }

    public String getCardName(){
        return model.getName();
    }

    public void setCardRarity(String rarity){
        model.setRarity(rarity);
    }

    public String getCardRarity(){
        return model.getRarity();
    }

    public void setCardVariant(String variant){
        model.setVariant(variant);
    }

    public String getCardVariant(){
        return model.getVariant();
    }

    public void setCardValue(double value){
        model.setValue(value);
    }

    public double getCardValue(){
        return model.getValue();
    }

    public void setCardQuantity(int quantity){
        model.setQuantity(quantity);
    }

    public int getCardQuantity(){
        return model.getQuantity();
    }

    public void updateView(){
        //TBD
    }
}
