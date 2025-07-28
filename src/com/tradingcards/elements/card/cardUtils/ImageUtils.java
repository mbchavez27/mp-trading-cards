package com.tradingcards.elements.card.cardUtils;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageUtils {

    private static final int TARGET_WIDTH = 300;
    private static final int TARGET_HEIGHT = 380;

    public static ImageIcon scaleIcon(ImageIcon icon) {
        if (icon == null || icon.getIconWidth() <= 0 || icon.getIconHeight() <= 0) {
            System.out.println("Icon is null or has invalid dimensions");
            return null;
        }

        Image scaledImage = icon.getImage().getScaledInstance(TARGET_WIDTH, TARGET_HEIGHT, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public static ImageIcon scaleIcon(ImageIcon icon, int preferredWidth, int preferredHeight) {
        if (icon == null || icon.getIconWidth() <= 0 || icon.getIconHeight() <= 0) {
            System.out.println("Icon is null or has invalid dimensions");
            return null;
        }

        Image scaledImage = icon.getImage().getScaledInstance(preferredWidth, preferredHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}