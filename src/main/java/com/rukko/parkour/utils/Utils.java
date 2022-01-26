package com.rukko.parkour.utils;

/**
 * @author ViiictorXD
 */
public class Utils {

    public static Integer parseInt(String subject) {
        try {
            return Integer.parseInt(subject);
        } catch (NumberFormatException exception) {
            return null;
        }
    }
}
