package net.bplaced.lakinator.PolynomLib;

/**
 * 08.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class PolyMain {
    public static final String VERSION = "0.42 [ALPHA]";
    public static String usedVariable = "x";
    private static String ergebnis;
    private static Polynom polynom1;
    private static Polynom polynom2;
    public static String OUTPUT_TO_GUI;

    public static void main(String[] args) {
        runGui();
        //System.out.println(runMainLoop("3", "2", true));
        //System.out.println(validateInput("3x^2.4-4x^2.2+1+x^2"));
    }


    public static String getVersion() {
        return VERSION;
    }

    public static boolean validateInput(String input) {

        if (Helper.trimAll(input).matches("([+-]?[0-9]*[.]?[0-9]*[a-z]?[\\^]?[0-9]*[.]?[0-9]*)?([+-][0-9]*[.]?[0-9]*[a-z]?[\\^]?[0-9]*[.]?[0-9]*)*")) {

            //System.out.println("Input matches");

            double highest = Polynom.deg(input)+1;

            for (String s : Polynom.splitPolynom(input)) {
                if (Polynom.getExponent(s) < highest) highest = Polynom.getExponent(s);
                else return false;
            }

            //System.out.println("Reihenfolge matches");

            return true;

        } else {
            return false;
        }

    }

    public static void runGui() {
        new GUI();
    }

    public static String runMainLoop(String poly1, String poly2, boolean log) {
        ergebnis = "";

        OUTPUT_TO_GUI = "&poly1 : &poly2 = &erg &rest\n";

        polynom1 = new Polynom(poly1);
        polynom2 = new Polynom(poly2);

        //MAIN LOOP
        int index = 0;
        Polynom usedPolynom1 = new Polynom(polynom1.getWert());
        Polynom usedPolynom2 = new Polynom(polynom2.getWert());



        do {
            String t1 = PolynomMath.division(Helper.biggestFromPolynom(usedPolynom1.getWert()), Helper.biggestFromPolynom(usedPolynom2.getWert()));
            ergebnis += t1;
            String t2 = PolynomMath.multiplizieren(Helper.clean(t1), usedPolynom2.clean());
            OUTPUT_TO_GUI += "-(" + Helper.clean(t2) + ")\n";
            OUTPUT_TO_GUI += "------\n";
            usedPolynom1.setWert( Helper.clean(PolynomMath.differenz(usedPolynom1.getWert(), t2)) );

            //Runterziehen falls möglich
            if (Polynom.splitPolynom(polynom1.getWert()).length > Polynom.splitPolynom(polynom2.getWert()).length+index) {
                usedPolynom1.setWert( usedPolynom1.getWert() + (Polynom.splitPolynom(polynom1.getWert())[Polynom.splitPolynom(polynom2.getWert()).length+index]) );
                index++;
            }

            OUTPUT_TO_GUI += usedPolynom1.clean() + "\n";

        } while (Polynom.deg(usedPolynom1.getWert()) >= Polynom.deg(usedPolynom2.getWert()) &&
                !(Polynom.splitPolynom(usedPolynom1.getWert()).length == 1 && Polynom.splitPolynom(usedPolynom2.getWert()).length == 1));


        if (log) {
            System.out.println(ergebnis);
            System.out.println(usedPolynom1.getWert());


            System.out.println("Endergebnis: " + polynom1.getWert() + " : " + polynom2.getWert() + " = " + Helper.clean(ergebnis));
            System.out.println("Rest: " + usedPolynom1.getWert());

            System.out.println("\n\nCompiled Output: \n" + Helper.compileOutput(OUTPUT_TO_GUI, polynom1, polynom2, ergebnis, usedPolynom1.getWert()));
        }

        OUTPUT_TO_GUI = Helper.compileOutput(OUTPUT_TO_GUI, polynom1, polynom2, ergebnis, usedPolynom1.getWert());

        return OUTPUT_TO_GUI;
    }

}
