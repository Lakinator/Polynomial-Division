/**
 * 11.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class PolynomMath {
    public static String division(String divident, String divisor) {
        //System.out.println(divident + " : " + divisor);
        String output = "";

        if (Polynom.getFaktor(divident) == 0 && Polynom.getFaktor(divisor) == 0 && Polynom.getExponent(divident) == 0 && Polynom.getExponent(divisor) == 0) {
            double temp = Double.parseDouble(divisor) / Double.parseDouble(divisor);
            output += temp >= 0 ? "+" + temp : temp;
        } else if (Polynom.getFaktor(divident) != 0 && Polynom.getFaktor(divisor) != 0 && Polynom.getExponent(divident) != 0 && Polynom.getExponent(divisor) != 0) {
            double temp = Polynom.getFaktor(divident) / Polynom.getFaktor(divisor);
            output += temp >= 0 ? "+" + temp : temp;
            output += Main.usedVariable + "^"; //Shiet
            output += Polynom.getExponent(divident) - Polynom.getExponent(divisor);
        } else if (Polynom.getFaktor(divident) == 0 && Polynom.getFaktor(divisor) != 0 && Polynom.getExponent(divident) == 0 && Polynom.getExponent(divisor) != 0) {
            double temp = Double.parseDouble(divident) / Polynom.getFaktor(divisor);
            output += temp >= 0 ? "+" + temp : temp;
            output += Main.usedVariable + "^"; //Shiet
            output += Polynom.getExponent(divisor);
        } else if (Polynom.getFaktor(divident) != 0 && Polynom.getFaktor(divisor) == 0 && Polynom.getExponent(divident) != 0 && Polynom.getExponent(divisor) == 0) {
            double temp = Polynom.getFaktor(divident) / Double.parseDouble(divisor);
            output += temp >= 0 ? "+" + temp : temp;
            output += Main.usedVariable + "^"; //Shiet
            output += Polynom.getExponent(divident);
        }

        //System.out.println(divident + " : " + divisor + " = " + output);

        return output;

    }

    public static String multiplizieren(String multiplikator, String multiplikand) {
        //last ergebnis * divisor
        String output = "";

        for (String s : Polynom.splitPolynom(multiplikand)) {

            if (Polynom.getFaktor(multiplikator) == 0 && Polynom.getFaktor(s) == 0 && Polynom.getExponent(multiplikator) == 0 && Polynom.getExponent(s) == 0) {
                double temp = Double.parseDouble(s) * Double.parseDouble(multiplikator);
                output += temp >= 0 ? "+" + temp : temp;
            } else if (Polynom.getFaktor(multiplikator) != 0 && Polynom.getFaktor(s) != 0 && Polynom.getExponent(multiplikator) != 0 && Polynom.getExponent(s) != 0) {
                double temp = Polynom.getFaktor(multiplikator) * Polynom.getFaktor(s);
                output += temp >= 0 ? "+" + temp : temp;
                output += Main.usedVariable + "^"; //Shiet
                output += Polynom.getExponent(multiplikator) + Polynom.getExponent(s);
            } else if (Polynom.getFaktor(multiplikator) == 0 && Polynom.getFaktor(s) != 0 && Polynom.getExponent(multiplikator) == 0 && Polynom.getExponent(s) != 0) {
                double temp = Double.parseDouble(multiplikator) * Polynom.getFaktor(s);
                output += temp >= 0 ? "+" + temp : temp;
                output += Main.usedVariable + "^"; //Shiet
                output += Polynom.getExponent(s);
            } else if (Polynom.getFaktor(multiplikator) != 0 && Polynom.getFaktor(s) == 0 && Polynom.getExponent(multiplikator) != 0 && Polynom.getExponent(s) == 0) {
                double temp = Polynom.getFaktor(multiplikator) * Double.parseDouble(s);
                output += temp >= 0 ? "+" + temp : temp;
                output += Main.usedVariable + "^"; //Shiet
                output += Polynom.getExponent(multiplikator);
            }
        }

        //System.out.println(multiplikator + " * " + multiplikand + " = " + output);

        return output;
    }

    public static String differenz(String minuend, String subtrahend) {
        String output = "";

        for (String einMinuend : Polynom.splitPolynom(minuend)) {

            for (String einSubtrahend : Polynom.splitPolynom(subtrahend)) {
                if (Polynom.getExponent(einMinuend) != 0 && Polynom.getExponent(einSubtrahend) != 0 && Polynom.getExponent(einMinuend) == Polynom.getExponent(einSubtrahend) && !einMinuend.equals(einSubtrahend)) {
                    double temp = Polynom.getFaktor(einMinuend) - Polynom.getFaktor(einSubtrahend);
                    if (temp != 1.0 && temp != -1.0) output += temp >= 0 ? "+" + temp : temp;
                    else output += temp == 1.0 ? "+" : "-";
                    output += Main.usedVariable; //Shiet
                    if (Polynom.getExponent(einMinuend) != 1.0 && Polynom.getExponent(einMinuend) != -1.0) output += "^" + Polynom.getExponent(einMinuend);
                    else if (Polynom.getExponent(einMinuend) == 1.0 || Polynom.getExponent(einMinuend) == -1.0) output += "";
                } else if (Polynom.getExponent(einMinuend) == 0 && Polynom.getExponent(einSubtrahend) == 0 && !einMinuend.equals(einSubtrahend)) {
                    double temp = Double.parseDouble(einMinuend) - Double.parseDouble(einSubtrahend);
                    output += temp >= 0 ? "+" + temp : temp;
                } else if (einMinuend.equals(einSubtrahend)) {
                    output += "";
                }
            }

        }

        //System.out.println(minuend + " : " + subtrahend + " = " + output);

        return output;
    }
}
