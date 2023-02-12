package dev.deepstackdriver.polynomialdivision;

import java.util.Locale;

/**
 * 22.05.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class InvalidPolynomException extends Exception {
    private String message_en, message_de;
    private int indexOfInvalidPolynom;

    public InvalidPolynomException(String message_en, String message_de, int indexOfInvalidPolynom) {
        this.message_en = message_en;
        this.message_de = message_de;
        this.indexOfInvalidPolynom = indexOfInvalidPolynom;
    }

    public int getIndexOfInvalidPolynom() {
        return indexOfInvalidPolynom;
    }

    @Override
    public String getMessage() {
        return message_en;
    }

    @Override
    public String getLocalizedMessage() {
        String country = Locale.getDefault().getCountry();
        switch (country) {
            default:   return message_en;
            case "EN": return message_en;
            case "US": return message_en;
            case "DE": return message_de;
        }
    }

    @Override
    public String toString() {
        return getLocalizedMessage() + " -> " + indexOfInvalidPolynom;
    }
}
