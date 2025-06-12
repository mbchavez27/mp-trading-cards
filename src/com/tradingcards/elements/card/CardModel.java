package com.tradingcards.elements.card;

public class CardModel {

    private String cardName;
    private String cardRarity;
    private String cardVariant;
    private double cardValue;
    private int cardQuantity;

    /**
     * Sets the name of the card.
     *
     * @param cardName the name to set for the card
     */
    public void setName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * Returns the name of the card.
     *
     * @return the card's name
     */
    public String getName() {
        return this.cardName;
    }

    /**
     * Sets the rarity of the card.
     *
     * @param rarity the rarity level to set (e.g., Common, Rare, Legendary)
     */
    public void setRarity(String rarity) {
        this.cardRarity = rarity;
    }

    /**
     * Returns the rarity of the card.
     *
     * @return the card's rarity
     */
    public String getRarity() {
        return cardRarity;
    }

    /**
     * Sets the variant of the card.
     *
     * @param variant the variant type to set (e.g., Normal, Full-art)
     */
    public void setVariant(String variant) {
        this.cardVariant = variant;
    }

    /**
     * Returns the variant of the card.
     *
     * @return the card's variant
     */
    public String getVariant() {
        return cardVariant;
    }

    /**
     * Sets the value of the card.
     *
     * @param value the monetary or numerical value to set
     */
    public void setValue(double value) {
        this.cardValue = value;
    }

    /**
     * Returns the value of the card.
     *
     * @return the card's value
     */
    public double getValue() {
        return cardValue;
    }

    /**
     * Sets the quantity of this card in the collection.
     *
     * @param quantity the quantity to set (must be a non-negative integer)
     */
    public void setQuantity(int quantity) {
        this.cardQuantity = quantity;
    }

    /**
     * Returns the quantity of this card in the collection.
     *
     * @return the number of this card available
     */
    public int getQuantity() {
        return cardQuantity;
    }

    /**
     * Calculates the final value of the card based on its base value and
     * variant.
     * <p>
     * The variant affects the final value as follows:
     * <ul>
     * <li>Normal: ×1</li>
     * <li>Extended-art: ×1.5</li>
     * <li>Full-art: ×2</li>
     * <li>Alt-art: ×3</li>
     * </ul>
     *
     * @param value the base value of the card
     * @param variant the variant type of the card
     * @return the calculated value after applying the variant multiplier
     */
    protected double calculateValue(double value, String variant) {
        double calculatedValue = value;

        switch (variant) {
            case "Normal" ->
                calculatedValue *= 1;

            case "Extended-art" ->
                calculatedValue *= 1.5;

            case "Full-art" ->
                calculatedValue *= 2;

            case "Alt-art" ->
                calculatedValue *= 3;

        }
        return calculatedValue;
    }
}
