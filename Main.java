/**
 * 08.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class Main {
    public static final String VERSION = "0.31";
    public static String usedVariable = "x";
    private static String ergebnis;
    private static Polynom polynom1;
    private static Polynom polynom2;
    public static String OUTPUT_TO_GUI;

    public static void main(String[] args) {

        new GUI();

    }

    public static void runMainLoop(String poly1, String poly2) {
        ergebnis = "";

        OUTPUT_TO_GUI = "&poly1 : &poly2 = &erg &rest\n";

        polynom1 = new Polynom(poly1);
        polynom2 = new Polynom(poly2);

        //MAIN LOOP
        int index = 0;
        Polynom usedPolynom1 = new Polynom(polynom1.getWert());
        Polynom usedPolynom2 = new Polynom(polynom2.getWert());

        while (Polynom.deg(usedPolynom1.getWert()) >= Polynom.deg(usedPolynom2.getWert())) {
            String t1 = PolynomMath.division(Helper.biggestFromPolynom(usedPolynom1.getWert()), Helper.biggestFromPolynom(usedPolynom2.getWert()));
            ergebnis += t1;
            String t2 = PolynomMath.multiplizieren(Helper.clean(t1), usedPolynom2.clean());
            OUTPUT_TO_GUI += "-(" + Helper.clean(t2) + ")\n";
            OUTPUT_TO_GUI += "------\n";
            usedPolynom1.setWert( Helper.clean(PolynomMath.differenz(usedPolynom1.getWert(), t2)) );
            try {
                usedPolynom1.setWert( usedPolynom1.getWert() + (Polynom.splitPolynom(polynom1.getWert())[Polynom.splitPolynom(polynom2.getWert()).length+index]) );
                index++;
                OUTPUT_TO_GUI += usedPolynom1.clean() + "\n";
                System.out.println("");
            } catch (ArrayIndexOutOfBoundsException ex) {
                ex.getMessage();
                break;
            }
        }
        
        System.out.println(usedPolynom1.getWert());
        
        OUTPUT_TO_GUI += usedPolynom1.clean();

        System.out.println("Endergebnis: " + polynom1.getWert() + " : " + polynom2.getWert() + " = " + Helper.clean(ergebnis));
        System.out.println("Rest: " + usedPolynom1.getWert());

        System.out.println("\n\nCompiled Output: \n" + Helper.compileOutput(OUTPUT_TO_GUI, polynom1, polynom2, ergebnis, usedPolynom1.getWert()));
        OUTPUT_TO_GUI = Helper.compileOutput(OUTPUT_TO_GUI, polynom1, polynom2, ergebnis, usedPolynom1.getWert());
    }

}
