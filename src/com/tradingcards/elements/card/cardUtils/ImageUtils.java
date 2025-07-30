package com.tradingcards.elements.card.cardUtils;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Utility class for scaling ImageIcons to specified dimensions.
 */
public class ImageUtils {

    private static final int TARGET_WIDTH = 300;
    private static final int TARGET_HEIGHT = 380;

    /**
     * Scales the given ImageIcon to the default target width and height.
     *
     * @param icon the ImageIcon to scale
     * @return a new ImageIcon scaled to TARGET_WIDTH x TARGET_HEIGHT, or null if
     *         the input is invalid
     */
    public static ImageIcon scaleIcon(ImageIcon icon) {
        if (icon == null || icon.getIconWidth() <= 0 || icon.getIconHeight() <= 0) {
            System.out.println("Icon is null or has invalid dimensions");
            return null;
        }

        Image scaledImage = icon.getImage().getScaledInstance(TARGET_WIDTH, TARGET_HEIGHT, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    /**
     * Scales the given ImageIcon to the specified width and height.
     *
     * @param icon            the ImageIcon to scale
     * @param preferredWidth  the desired width
     * @param preferredHeight the desired height
     * @return a new ImageIcon scaled to preferredWidth x preferredHeight, or null
     *         if the input is invalid
     */
    public static ImageIcon scaleIcon(ImageIcon icon, int preferredWidth, int preferredHeight) {
        if (icon == null || icon.getIconWidth() <= 0 || icon.getIconHeight() <= 0) {
            System.out.println("Icon is null or has invalid dimensions");
            return null;
        }

        Image scaledImage = icon.getImage().getScaledInstance(preferredWidth, preferredHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}