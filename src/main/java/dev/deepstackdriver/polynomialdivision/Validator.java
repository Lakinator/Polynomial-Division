package dev.deepstackdriver.polynomialdivision;

/**
 * 23.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

class Validator {

    static boolean validateInput(String... input) throws InvalidPolynomException {
        PolynomMain.usedVariable = "";

        for (int i = 0; i < input.length; i++) {

            if (!isPolynom(input[i])) throw new InvalidPolynomException("Wrong polynom syntax", "Falsche Polynomsysntax", i); // Wrong polynom syntax -> Invalid

            if (!usesVariable(input[i])) throw new InvalidPolynomException("Different variables used", "Verschiedene Variablen genutzt", i); // Different variables used (only x's or y's etc. but not both) -> Invalid

        }

        return true; // Valid polynom -> Valid

    }

    private static boolean isPolynom(String polynom) {
        String[] splitted = Polynom.splitPolynom(polynom);
        double[] expos = new double[splitted.length];
        for (int i = 0; i < expos.length; i++) {
            expos[i] = Polynom.getExponent(splitted[i]);
        }

        for (int j = 0; j < expos.length; j++) {
            for (int k = j + 1; k < expos.length; k++) {
                if (expos[k] == expos[j]) {
                    return false;
                }
            }
        }

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
