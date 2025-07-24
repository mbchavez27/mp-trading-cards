package com.tradingcards.elements.card.cardUtils;

import javax.swing.*;
import java.awt.*;

public class ImageUtils {

    public static ImageIcon scaleIcon(ImageIcon icon, double scaleFactor){
        if (icon == null || icon.getIconWidth() <= 0 || icon.getIconHeight() <= 0){
            System.out.println("Icon is null or empty");
        }

        int newWidth = (int) (icon.getIconWidth() * scaleFactor);
        int newHeight = (int) (icon.getIconHeight() * scaleFactor);

        Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);

    }
}
