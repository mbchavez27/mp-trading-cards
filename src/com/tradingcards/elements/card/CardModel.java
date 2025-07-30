package com.tradingcards.elements.card;

import java.util.HashMap;
import java.util.Objects;

import com.tradingcards.elements.collection.CollectionModel;

/**
 * Represents a model for a trading card, encapsulating properties such as name,
 * rarity, variant, value, quantity, and image path. Provides methods to get and
 * set these properties and to calculate the final value of a card based on its
 * variant.
 */
public class CardModel {

    /**
     * The name of the card.
     */
    private String cardName;

    /**
     * The rarity of the card (e.g., Common, Rare, Legendary).
     */
    private String cardRarity;

    /**
     * The variant of the card (e.g., Normal, Full-art, Alt-art).
     */
    private String cardVariant;

    /**
     * The base value of the card.
     */
    private double cardValue;

    /**
     * The quantity of this card in the collection.
     */
    private int cardQuantity;

    /**
     * The image path of this card in the collection.
     */
    private String cardImagePath;

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
     * Sets the image path of the card.
     *
     * @param cardImagePath the image path to set for the card
     */
    public void setImagePath(String cardImagePath) {
        this.cardImagePath = cardImagePath;
    }

    /**
     * Returns the image path of the card.
     *
     * @return the card's image path
     */
    public String getImagePath() {
        return this.cardImagePath;
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
     * Increases the quantity of this card by the specified amount.
     *
     * @param quantity the number of copies to add to the current quantity
     */
    public void increaseQuantity(int quantity) {
        this.cardQuantity += quantity;
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
     * Calculates the final value of the card based on its base value and variant.
     * <p>
     * The variant affects the final value as follows:
     * <ul>
     * <li>Normal: ×1 (base value)</li>
     * <li>Extended-art: ×1.5 (50% increase)</li>
     * <li>Full-art: ×2.0 (100% increase)</li>
     * <li>Alt-art: ×3.0 (200% increase)</li>
     * </ul>
     *
     * @param value   the base value of the card
     * @param variant the variant type of the card
     * @return the calculated value after applying the variant multiplier
     */
    protected double calculateValue(double value, String variant) {
        double calculatedValue = value;

        switch (variant) {
            case "Normal" -> calculatedValue *= 1;
            case "Extended-art" -> calculatedValue *= 1.5;
            case "Full-art" -> calculatedValue *= 2;
            case "Alt-art" -> calculatedValue *= 3;
        }

        return calculatedValue;
    }

    /**
     * Determines whether a card with the same name already exists in the collection
     * and has the same rarity, variant, and value.
     *
     * @param sharedCollection the main collection to check against
     * @param name             the name of the card to compare
     * @param newCard          the new card being checked
     * @return {@code true} if a card with the same name and properties exists;
     *         {@code false} otherwise
     */
    protected Boolean hasCopy(CollectionModel sharedCollection, String name, CardModel newCard) {
        HashMap<String, CardModel> cards = sharedCollection.getCardCollection();

        if (cards.containsKey(name)) {
            CardModel existingCard = cards.get(name);
            if (!Objects.equals(existingCard.cardRarity, newCard.cardRarity)) {
                return false;
            }
            if (!Objects.equals(existingCard.cardVariant, newCard.cardVariant)) {
                return false;
            }
            if (existingCard.cardValue != newCard.cardValue) {
                return false;
            }
        }
        return true;
    }
}
