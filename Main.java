import java.util.Scanner;

/**
 * 08.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class Main {
    public static String usedVariable = "x";
    private static String ergebnis;
    private static Polynom polynom1;
    private static Polynom polynom2;
    public static String OUTPUT_TO_GUI = "No Output";

    public static void main(String[] args) {

        new GUI();

        //Scanner sc = new Scanner(System.in);

        // ES MUSS GEORDNET SEIN UND KEIN - ODER + IM EXPONENTEN!

        /*// 5x^5-9x^4-3x^3+10x^2-2x  ||  x^3+6x^2+9x+4
        System.out.println("Divident: ");
        polynom1 = new Polynom(sc.nextLine());
        polynom1.clean();
        System.out.println("(" + polynom1.getWert() + ") deg = " + Polynom.deg(polynom1.getWert()));

        // 2x^3-4x^2+2x  ||  x+1
        System.out.println("Divisor: ");
        polynom2 = new Polynom(sc.nextLine());
        polynom2.clean();
        System.out.println("(" + polynom2.getWert() + ") deg = " + Polynom.deg(polynom2.getWert()));

        System.out.println(polynom1.getWert() + " : " + polynom2.getWert());
        System.out.println("Wird berechnet...\n");*/

        //runMainLoop();

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
            //System.out.println("t1: " + Helper.clean(t1) + " (" + t1 + ")");
            ergebnis += t1;
            //System.out.println("us2: " + usedPolynom2.clean() + " (" + usedPolynom2.getWert() + ")");
            String t2 = PolynomMath.multiplizieren(Helper.clean(t1), usedPolynom2.clean());
            //System.out.println("us1: " + usedPolynom1.getWert() + " t2: " + t2);
            OUTPUT_TO_GUI += "-(" + Helper.clean(t2) + ")\n";
            OUTPUT_TO_GUI += "------\n";
            usedPolynom1.setWert( Helper.clean(PolynomMath.differenz(usedPolynom1.getWert(), t2)) );
            try {
                usedPolynom1.setWert( usedPolynom1.getWert() + (Polynom.splitPolynom(polynom1.getWert())[Polynom.splitPolynom(polynom2.getWert()).length+index]) );
                index++;
                OUTPUT_TO_GUI += usedPolynom1.clean() + "\n";
                //System.out.println("");
            } catch (ArrayIndexOutOfBoundsException ex) {
                ex.getMessage();
                break;
            }
        }

        OUTPUT_TO_GUI += usedPolynom1.clean();

        System.out.println("Endergebnis: " + polynom1.getWert() + " : " + polynom2.getWert() + " = " + Helper.clean(ergebnis));
        System.out.println("Rest: " + usedPolynom1.getWert());

        System.out.println("\n\nCompiled Output: \n" + Helper.compileOutput(OUTPUT_TO_GUI, polynom1, polynom2, ergebnis, usedPolynom1.getWert()));
        OUTPUT_TO_GUI = Helper.compileOutput(OUTPUT_TO_GUI, polynom1, polynom2, ergebnis, usedPolynom1.getWert());
    }

}
