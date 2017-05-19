package Polynomdivision;

/**
 * 23.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

class Validator {

    static int validateInput(String... input) {
        PolynomMain.usedVariable = "";

        for (String einInput : input) {

            if (!isPolynom(einInput)) return 1; // Wrong polynom syntax -> Invalid

            if (!usesVariable(einInput)) return 2; // More than one variable used (only x's or y's etc. but not both) -> Invalid

        }

        return 0; // Valid polynom -> Valid

    }

    private static boolean isPolynom(String polynom) {
        return Helper.trimAll(polynom).matches("([+-]?[0-9]*(\\.[0-9]+)?[a-zA-Z]?(\\^[0-9]+(\\.[0-9]+)?)?)?([+-][0-9]*(\\.[0-9]+)?[a-zA-Z]?(\\^[0-9]+(\\.[0-9]+)?)?)*");
    }

    private static boolean usesVariable(String polynom) {
        for (int i = 0; i < polynom.toCharArray().length; i++) {
            if ((polynom.toCharArray()[i]+"").matches("[a-zA-Z]")) {
                if (PolynomMain.usedVariable.isEmpty()) {
                    PolynomMain.usedVariable = polynom.toCharArray()[i]+"";
                    continue;
                }

                if (!PolynomMain.usedVariable.equals(polynom.toCharArray()[i]+"")) return false; // More than one variable used (only x's or y's etc. but not both)
            }
        }

        return true;
    }

}
