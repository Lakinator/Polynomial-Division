package Polynomdivision;

/**
 * 11.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

class PolynomMath {
    static String division(String dividend, String divisor) {
        String output = "";

        if (Polynom.getCoefficient(dividend) == 0 && Polynom.getCoefficient(divisor) == 0 && Polynom.getExponent(dividend) == 0 && Polynom.getExponent(divisor) == 0) {
            double temp = Double.parseDouble(dividend) / Double.parseDouble(divisor);
            output += temp >= 0 ? "+" + temp : temp;
        } else if (Polynom.getCoefficient(dividend) != 0 && Polynom.getCoefficient(divisor) != 0 && Polynom.getExponent(dividend) != 0 && Polynom.getExponent(divisor) != 0) {
            double temp = Polynom.getCoefficient(dividend) / Polynom.getCoefficient(divisor);
            output += temp >= 0 ? "+" + temp : temp;
            output += PolynomMain.usedVariable + "^";
            output += Polynom.getExponent(dividend) - Polynom.getExponent(divisor);
        } else if (Polynom.getCoefficient(dividend) == 0 && Polynom.getCoefficient(divisor) != 0 && Polynom.getExponent(dividend) == 0 && Polynom.getExponent(divisor) != 0) {
            double temp = Double.parseDouble(dividend) / Polynom.getCoefficient(divisor);
            output += temp >= 0 ? "+" + temp : temp;
            output += PolynomMain.usedVariable + "^";
            output += Polynom.getExponent(divisor);
        } else if (Polynom.getCoefficient(dividend) != 0 && Polynom.getCoefficient(divisor) == 0 && Polynom.getExponent(dividend) != 0 && Polynom.getExponent(divisor) == 0) {
            double temp = Polynom.getCoefficient(dividend) / Double.parseDouble(divisor);
            output += temp >= 0 ? "+" + temp : temp;
            output += PolynomMain.usedVariable + "^";
            output += Polynom.getExponent(dividend);
        }

        //System.out.println(dividend + " : " + divisor + " = " + output);

        return output;

    }

    static String multiplizieren(String multiplikator, String multiplikand) {
        String output = "";

        for (String s : Polynom.splitPolynom(multiplikand)) {

            if (Polynom.getCoefficient(multiplikator) == 0 && Polynom.getCoefficient(s) == 0 && Polynom.getExponent(multiplikator) == 0 && Polynom.getExponent(s) == 0) {
                double temp = Double.parseDouble(s) * Double.parseDouble(multiplikator);
                output += temp >= 0 ? "+" + temp : temp;
            } else if (Polynom.getCoefficient(multiplikator) != 0 && Polynom.getCoefficient(s) != 0 && Polynom.getExponent(multiplikator) != 0 && Polynom.getExponent(s) != 0) {
                double temp = Polynom.getCoefficient(multiplikator) * Polynom.getCoefficient(s);
                output += temp >= 0 ? "+" + temp : temp;
                output += PolynomMain.usedVariable + "^";
                output += Polynom.getExponent(multiplikator) + Polynom.getExponent(s);
            } else if (Polynom.getCoefficient(multiplikator) == 0 && Polynom.getCoefficient(s) != 0 && Polynom.getExponent(multiplikator) == 0 && Polynom.getExponent(s) != 0) {
                double temp = Double.parseDouble(multiplikator) * Polynom.getCoefficient(s);
                output += temp >= 0 ? "+" + temp : temp;
                output += PolynomMain.usedVariable + "^";
                output += Polynom.getExponent(s);
            } else if (Polynom.getCoefficient(multiplikator) != 0 && Polynom.getCoefficient(s) == 0 && Polynom.getExponent(multiplikator) != 0 && Polynom.getExponent(s) == 0) {
                double temp = Polynom.getCoefficient(multiplikator) * Double.parseDouble(s);
                output += temp >= 0 ? "+" + temp : temp;
                output += PolynomMain.usedVariable + "^";
                output += Polynom.getExponent(multiplikator);
            }
        }

        //System.out.println(multiplikator + " * " + multiplikand + " = " + output);

        return output;
    }

    static String differenz(String minuend, String subtrahend) {
        String output = "";

        for (double i = Polynom.deg(minuend); i >= 0; i--) {
            if (!Helper.elementFromPolynomByExpo(minuend, i).equals("") && !Helper.elementFromPolynomByExpo(subtrahend, i).equals("")) {
                output += _differenz(Helper.elementFromPolynomByExpo(minuend, i), Helper.elementFromPolynomByExpo(subtrahend, i));
            } else if (!Helper.elementFromPolynomByExpo(minuend, i).equals("") && Helper.elementFromPolynomByExpo(subtrahend, i).equals("")) {
                output += Helper.elementFromPolynomByExpo(minuend, i);
            } else if (Helper.elementFromPolynomByExpo(minuend, i).equals("") && !Helper.elementFromPolynomByExpo(subtrahend, i).equals("")) {

                if (Polynom.getExponent(Helper.elementFromPolynomByExpo(subtrahend, i)) == 0) {
                    output += _differenz("+0", Helper.elementFromPolynomByExpo(subtrahend, i));
                } else if (Polynom.getExponent(Helper.elementFromPolynomByExpo(subtrahend, i)) != 0) {
                    output += _differenz("+0x^" + Polynom.getExponent(Helper.elementFromPolynomByExpo(subtrahend, i)), Helper.elementFromPolynomByExpo(subtrahend, i));
                }

            }
        }

        //System.out.println(minuend + " : " + subtrahend + " = " + output);

        return output;
    }

    static String _differenz(String einMinuend, String einSubtrahend) {
        String _output = "";

        //Selber Exponent!
        if ( Polynom.getExponent(einMinuend) != Polynom.getExponent(einSubtrahend) ) return "";

        if (Polynom.getExponent(einMinuend) != 0 && Polynom.getExponent(einSubtrahend) != 0 && !einMinuend.equals(einSubtrahend)) {
            double temp = Polynom.getCoefficient(einMinuend) - Polynom.getCoefficient(einSubtrahend);
            if (temp != 1.0 && temp != -1.0) _output += temp >= 0 ? "+" + temp : temp;
            else _output += temp == 1.0 ? "+" : "-";
            _output += PolynomMain.usedVariable;
            if (Polynom.getExponent(einMinuend) != 1.0 && Polynom.getExponent(einMinuend) != -1.0) _output += "^" + Polynom.getExponent(einMinuend);
            else if (Polynom.getExponent(einMinuend) == 1.0 || Polynom.getExponent(einMinuend) == -1.0) _output += "";
        } else if (Polynom.getExponent(einMinuend) == 0 && Polynom.getExponent(einSubtrahend) == 0 && !einMinuend.equals(einSubtrahend)) {
            double temp = Double.parseDouble(einMinuend) - Double.parseDouble(einSubtrahend);
            _output += temp >= 0 ? "+" + temp : temp;
        } else if (einMinuend.equals(einSubtrahend)) {
            _output += "";
        }

        return _output;
    }
}
