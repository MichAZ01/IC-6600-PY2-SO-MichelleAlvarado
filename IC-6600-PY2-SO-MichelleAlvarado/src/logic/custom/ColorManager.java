/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Michelle Alvarado
 */
public class ColorManager {
    public ArrayList<String> usedColors;

    public ColorManager() {
        this.setUsedColors();
    }
    
    public void setUsedColors(){
        this.usedColors = new ArrayList<String>();
    }

    public boolean StringExistsInArray(String string, ArrayList<String> array) {
        Boolean exists = false;
        int size = array.size();
        String currentString = null;
        for (int i = 0; i < size; i++) {
            currentString = array.get(i);
            if (currentString == null) {
                break;
            } else if (string.equals(currentString)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public Color generateColor(int currentIndex) {
        Color newColor = null;
        Random random = new Random();
        while (newColor == null) {
            final float hue = random.nextFloat(); // Saturation between 0.1 and 0.3 final 
            float saturation = (random.nextInt(2000) + 1000) / 10000f;
            final float luminance = 0.9f;
            final Color color = Color.getHSBColor(hue, saturation, luminance);
            if (!this.StringExistsInArray(color.toString(), this.usedColors) && hasNoCodeEqualy(color.toString(), this.usedColors, currentIndex)) {
                newColor = color;
                this.usedColors.add(newColor.toString());
            }
        }
        return newColor;
    }

    public boolean hasNoCodeEqualy(String color, ArrayList<String> usedColors, int currentIndex) {
        boolean hasOnlyOne = true;
        String[] previousColorRGB = null;
        String[] currentColorRGB = null;
        int countCoincidences = 0;
        if (currentIndex > 0) {
            String previousColor = usedColors.get(currentIndex - 1);
            if (previousColor != null) {
                previousColorRGB = this.getRGBCodes(previousColor);
                currentColorRGB = this.getRGBCodes(color);
                if (previousColorRGB[0].equals(currentColorRGB[0])) {
                    countCoincidences++;
                }
                if (previousColorRGB[1].equals(currentColorRGB[1])) {
                    countCoincidences++;
                }
                if (previousColorRGB[2].equals(currentColorRGB[2])) {
                    countCoincidences++;
                }
            }
            if (countCoincidences > 0) {
                return false;
            }
        }

        return hasOnlyOne;
    }

    public String[] getRGBCodes(String color) {
        int initialIndex = color.indexOf("[");
        String rgbCode = color.substring(initialIndex);
        String[] splitRGBCode = rgbCode.split("=");
        String red = splitRGBCode[1].split(",")[0];
        String green = splitRGBCode[2].split(",")[0];
        String blue = splitRGBCode[3].split("]")[0];
        return new String[]{red, green, blue};
    }
}
