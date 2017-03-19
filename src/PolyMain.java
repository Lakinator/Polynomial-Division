package net.bplaced.lakinator.PolynomLib;

/**
 * 08.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class PolyMain {
    /**
     * This String contains the Version of the Library
     */
    private static final String VERSION = "0.93 [BETA]";
    /**
     * This String contains the Variable that is used in the output
     * It will be set in the validateInput Method
     * Default: x
     */
    public static String usedVariable = "x";

    /**
     * The main loop is currently only for testing and is not in the final library compile
     * @param args
     *        Program arguments
     */
    //public static void main(String[] args) {
        //runGui();
        //System.out.println("Valid: " + validateInput("2Z", "Z"));
        //System.out.println(runMainLoop("2Z", "Z", true));
        //System.out.println(Helper.cleaned(PolynomMath.differenz("3x^3+x", "x^2+3x")));
        //System.out.println(Helper.cleaned(PolynomMath.division("+2x", "+x")));
        //System.out.println(PolynomMath.differenz("+4x^2+3", "+4x"));
    //}

    /**
     * A Method to get the Version of the Library
     * @return
     *        The Version String
     */
    public static String getVersion() {
        return VERSION;
    }

    /**
     * This Method validates all given Strings
     * It also sets the usedVariable that will be used in the output later on
     * It returns an Integer indicating the valid or invalid state of the given Strings
     * @param input
     *        The Strings that should be validated
     * @return
     *        0: Valid Polynom -> Valid
     *        1: Wrong Polynom Syntax -> Invalid
     *        2: Not in the right order (exponent1 > exponent2 ...) -> Invalid
     *        3: More than one variable used (only x's or y's etc. but not both) -> Invalid
     */
    public static int validateInput(String... input) {
        usedVariable = "";

        for (String currInput : input) {

            if (Helper.trimAll(currInput).matches("([+-]?[0-9]*(\\.[0-9]+)?[a-zA-Z]?(\\^[0-9]+(\\.[0-9]+)?)?)?([+-][0-9]*(\\.[0-9]+)?[a-zA-Z]?(\\^[0-9]+(\\.[0-9]+)?)?)*")) {

                double highest = Polynom.deg(currInput)+1;

                for (String s : Polynom.splitPolynom(currInput)) {
                    if (Polynom.getExponent(s) < highest) highest = Polynom.getExponent(s);
                    else return 2; //order of each exponent is wrong (expo1 > expo2 > expo3)


                    for (int j = 0; j < currInput.toCharArray().length; j++) {
                        if ((currInput.toCharArray()[j]+"").matches("[a-zA-Z]")) {
                            if (usedVariable.isEmpty()) {
                                usedVariable = currInput.toCharArray()[j]+"";
                                continue;
                            }

                            if (!usedVariable.equals(currInput.toCharArray()[j]+"")) return 3; // More than one variable used (only x's or y's etc. but not both)
                        }
                    }
                }

                //Letzte Überprüfung fertig
                if (input[input.length-1].equals(currInput)) return 0; //valid

            } else {
                return 1; //wrong syntax of the elements
            }

        }

        return 1; //Default invalid

    }

    /**
     * This just runs the GUI
     */
    public static void runGui() {
        new GUI();
    }

    /**
     * This Method calculates the result of a division between two Polynom Strings and returns a formatted output String
     * @param poly1
     *        The first Polynom String (Divident)
     * @param poly2
     *        The second Polynom String (Divisor)
     * @param fullOutput
     *        This boolean indicates whether the full formatted output or only the result should be returned
     * @return
     *        The formatted output String with the result and all calculation steps
     */
    public static String runMainLoop(String poly1, String poly2, boolean fullOutput) {
        /*
         * This String is going to contain the result of the calculation
         */
        String ergebnis = "";
        /*
         * This Polynom is the Divident
         */
        Polynom polynom1;
        /*
         * This Polynom is the Divisor
         */
        Polynom polynom2;
        /*
         * This String is going to contain the result of the calculation plus the formatted calculation that is used in the GUI output
         * It's also the return variable of the mainLoop
         */
        String formatted_output = "&poly1 : &poly2 = &erg &rest\n";

        /*
         * Initializing each Polynom
         */
        polynom1 = new Polynom(poly1);
        polynom2 = new Polynom(poly2);

        /*
         * Splitting each Polynom in a new Polynom, that will be used and changed in the main loop
         */
        Polynom usedPolynom1 = new Polynom(polynom1.getWert());
        Polynom usedPolynom2 = new Polynom(polynom2.getWert());


        do {
            /*                         Step 1                             */
            /* Dividing the highest Exponential Element from each Polynom */
            String t1 = PolynomMath.division(
                                            Helper.biggestExponentialElementFromPolynom(usedPolynom1.getWert()), //Divident
                                            Helper.biggestExponentialElementFromPolynom(usedPolynom2.getWert())  //Divisor
                                            );
            /*                                                            */


            ergebnis += t1; //Adding the result from step 1 to the end result


            /*                                 Step 2                                            */
            /* Multiplicating the result from step 1 with the whole second polynom (The divisor) */
            String t2 = PolynomMath.multiplizieren(
                                                  Helper.clean(t1),
                                                  usedPolynom2.cleaned()
                                                  );
            /*                                                                                   */


            formatted_output += "-(" + Helper.clean(t2) + ")\n"; // Adding the result from step 2 to the formatted output
            formatted_output += "------\n"; // Adding a seperator


            /*                              Step 3                          */
            /* Setting the used polynom to the new subtracted polynom value */
            usedPolynom1.setWert(
                                // Cleaning the result
                                Helper.clean(
                                            // Subtracting the old value with the result from step 2
                                            PolynomMath.differenz(
                                                                 usedPolynom1.getWert(),
                                                                 t2
                                                                 )
                                            )
                                );
            /*                                                               */


            formatted_output += usedPolynom1.cleaned() + "\n"; // Adding the calculated value from step 3 to the formatted output

        } while (Polynom.deg(usedPolynom1.getWert()) >= Polynom.deg(usedPolynom2.getWert()) && !usedPolynom1.cleaned().equals("+0"));


        // This compiles all other components into the output
        formatted_output = Helper.compileFormattedOutput(formatted_output, polynom1, polynom2, ergebnis, usedPolynom1.getWert());

        return fullOutput ? formatted_output : Helper.compileOutput("&erg &rest", polynom2, ergebnis, usedPolynom1.getWert());
    }

}