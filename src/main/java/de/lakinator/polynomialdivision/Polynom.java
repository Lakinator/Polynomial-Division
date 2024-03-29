package de.lakinator.polynomialdivision;

import java.util.regex.Pattern;

/**
 * 11.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

class Polynom {
    private String value;


    Polynom() {
        value = "";
    }

    Polynom(String value) {
        this.value = value;
    }

    Polynom(Polynom polynom) {
        this.value = polynom.getValue();
    }


    String getValue() {
        return value;
    }

    void setValue(String value) {
        this.value = value;
    }

    double getDeg() {
        return deg(this.value);
    }

    /**
     * @return
     *        A cleaned version of the saved string in the polynom
     */
    String cleaned() {
        return Helper.clean(this.value);
    }

    /**
     * This static method basically splits a polynom into its pieces
     * @param in
     *        The string that should be splitted
     * @return
     *        An array with the splitted parts
     */
    static String[] splitPolynom(String in) {

        String[] out = Helper.trimAll(in).split("(?=[+-])");

        if (out.length == 0) return new String[0];

        //Just in case the first element is empty (small edge case), this just removes the first empty element
        if (out[0].isEmpty() && out.length > 1) {
            String[] temp = out;

            out = new String[out.length - 1];
            System.arraycopy(temp, 1, out, 0, out.length);
        }

        // Adds a "+" if there is no "+" or "-" in front of the first element
        if (!out[0].startsWith("+") && !out[0].startsWith("-") ) out[0] = "+" + out[0];

        return out;
    }

    /**
     *
     * @param s
     *        The polynom string from which you want to know the degree
     * @return
     *        The polynom degree
     */
    static double deg(String s) {

        double highest = 0;
        String[] allParts = splitPolynom(s);

        for (String part : allParts) {
            if (part.contains("^")) {
                highest = Double.parseDouble(part.split("\\^")[1]) > highest ? Double.parseDouble(part.split("\\^")[1]) : highest;
            } else if (!part.contains("^") && Pattern.compile("[a-zA-Z]").matcher(part).find()) {
                highest = 1 > highest ? 1 : highest;
            }
        }

        return highest;
    }

    /**
     *
     * @param in
     *        The polynom string element from which you want to know the coefficient
     * @return
     *        The coefficient from the element
     *        It returns 0 if there is no variable in the string, that means the string contains only a number
     */
    static double getCoefficient(String in) {
        if(in.matches("[+-]?[0-9]*(\\.[0-9]+)?[a-zA-Z](\\^[0-9]+(\\.[0-9]+)?)?")) {
            String split = in.split("[a-zA-Z]")[0];
            if (split.equals("+") || split.equals("-")) split = split + 1;
            return Double.parseDouble(split);
        } else if (in.matches("[+-]?[a-zA-Z]")) {
            String split = in.split("[a-zA-Z]")[0];
            if (!split.equals("+") && !split.equals("-")) split = "+";
            return Double.parseDouble(split + 1.0);
        } else {
            return 0;
        }
    }

    /**
     *
     * @param in
     *        The polynom string element from which you want to know the exponent
     * @return
     *        The exponent from the element
     *        It returns 0 if there is no variable in the string, that means the string contains only a number
     */
    static double getExponent(String in) {
        if(in.matches("[+-]?[0-9]*(\\.[0-9]+)?[a-zA-Z]\\^[0-9]+(\\.[0-9]+)?")) {
            return Double.parseDouble(in.split("\\^")[1]);
        } else if (in.matches("[+-]?[0-9]*(\\.[0-9]+)?[a-zA-Z]")) {
            return 1.0;
        } else {
            return 0;
        }
    }

}
