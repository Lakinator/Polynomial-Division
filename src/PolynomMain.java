package Polynomdivision;

/**
 * 08.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class PolynomMain {
    /**
     * This string contains the version of the library
     */
    private static final String VERSION = "1.0";
    /**
     * This string contains the variable that is used in the output
     * It will be set in the validateInput method
     * Default: x
     */
    static String usedVariable = "x";

    /**
     * A method to get the version of the library
     * @return
     *        The version string
     */
    public static String getVersion() {
        return VERSION;
    }

    /**
     * This method validates all given strings
     * It also sets the usedVariable that will be used in the output later on
     * It returns an integer indicating the valid or invalid state of the given strings
     * @param input
     *        The strings that should be validated
     * @return
     *        0: Valid polynom -> Valid
     *        1: Wrong polynom syntax -> Invalid
     *        2: More than one variable used (only x's or y's etc. but not more than one) -> Invalid
     */
    public static int validateInput(String... input) {
        return Validator.validateInput(input);
    }

    /**
     * This method calculates the result of a division between two polynom strings and returns a formatted output string
     * @param poly1
     *        The first polynom string (Dividend)
     * @param poly2
     *        The second polynom string (Divisor)
     * @param fullOutput
     *        This boolean indicates whether the full formatted output or only the result should be returned
     * @return
     *        The formatted output string with the result and all calculation steps
     */
    public static String calculate(String poly1, String poly2, boolean fullOutput) {
        /*
         * This string is going to contain the result of the calculation
         */
        String result = "";
        /*
         * This polynom is the dividend
         */
        Polynom polynom1;
        /*
         * This polynom is the divisor
         */
        Polynom polynom2;
        /*
         * This string contains all calculation steps
         */
        String calculation_steps = "";
        /*
         * This string contains the final return value
         */
        String output;


        /*
         * Initializing each polynom and sorting it
         */
        polynom1 = new Polynom(PolynomSorter.sortPolynom(poly1));
        polynom2 = new Polynom(PolynomSorter.sortPolynom(poly2));

        /*
         * Setting up the polynoms, which will be used and changed in the main loop
         */
        Polynom usedPolynom1 = new Polynom(polynom1);
        Polynom usedPolynom2 = new Polynom(polynom2);


        while (usedPolynom1.getDeg() >= usedPolynom2.getDeg() && !usedPolynom1.cleaned().equals("+0")) {

            /*                         Step 1                             */
            /* Dividing the highest exponential element from each polynom */
            String t1 = PolynomMath.division(
                                            Helper.biggestExponentialElementFromPolynom(usedPolynom1.getValue()), //Dividend
                                            Helper.biggestExponentialElementFromPolynom(usedPolynom2.getValue())  //Divisor
                                            );
            /*                                                            */


            result += t1; //Adding the result from step 1 to the end result


            /*                                 Step 2                                            */
            /* Multiplicating the result from step 1 with the whole second polynom (The divisor) */
            String t2 = PolynomMath.multiply(
                                                  Helper.clean(t1),
                                                  usedPolynom2.cleaned()
                                                  );
            /*                                                                                   */


            calculation_steps += "-(" + Helper.clean(t2) + ")\n"; // Adding the result from step 2 to the calculation steps
            calculation_steps += "------\n"; // Adding a seperator


            /*                              Step 3                          */
            /* Setting the used polynom to the new subtracted polynom value */
            usedPolynom1.setValue(
                                // Cleaning the result
                                Helper.clean(
                                            // Subtracting the result from step 2 from the old value
                                            PolynomMath.difference(
                                                                 usedPolynom1.getValue(),
                                                                 t2
                                                                 )
                                            )
                                );
            /*                                                               */


            calculation_steps += usedPolynom1.cleaned() + "\n"; // Adding the calculated value from step 3 to the calculation steps

        }


        // This compiles all other components into the output

        if (fullOutput)
            output = Helper.compileOutput("&dividend : &divisor = &result + &remainder\n&steps", polynom1, polynom2, result, usedPolynom1, calculation_steps);
        else
            output = Helper.compileOutput("&result + &remainder", polynom1, polynom2, result, usedPolynom1, calculation_steps);

        return output;
    }

}
