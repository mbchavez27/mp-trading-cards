package com.tradingcards.elements.card;

public class CardModel {
    private String cardName;
    private String cardRarity;
    private String cardVariant;
    private double cardValue;
    private int cardQuantity;

    public void setName(String cardName){
        this.cardName = cardName;
    }

    public String getName(){
        return this.cardName;
    }

    public void setRarity(String rarity) {
        this.cardRarity = cardRarity;
    }

    public String getRarity() {
        return cardRarity;
    }

    public void setVariant(String variant) {
        this.cardVariant = cardVariant;
    }

    public String getVariant() {
        return cardVariant;
    }

    public void setValue(double value) {
        this.cardValue = value;
    }

    public double getValue() {
        return cardValue;
    }

    public void setQuantity(int quantity) {
        this.cardQuantity = quantity;
    }

    public int getQuantity() {
        return cardQuantity;
    }






}
