package de.lakinator.polynomialdivision;

/**
 * 11.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

class PolynomMath {

    /**
     * This calculates the division between two values
     * There are 4 cases:
     * [1]:
     *     Dividend: - Only a normal value (1, 2, 3)
     *               - No exponent
     *               --> Number
     *     Divisor:  - Only a normal value (1, 2, 3)
     *               - No exponent
     *               --> Number
     *
     *     Calculation:
     *                 - Number / number
     *
     * [2]:
     *     Dividend: - The value contains a variable (1x, 2x,3x)
     *               - The value has got an exponent (x^1, x^2, x^3)
     *               --> Monom
     *     Divisor:  - The value contains a variable (1x, 2x,3x)
     *               - The value has got an exponent (x^1, x^2, x^3)
     *               --> Monom
     *
     *     Calculation:
     *                 - Coefficient / coefficient
     *                         +
     *                 - usedVariable
     *                         +
     *                 - Exponent - exponent
     *
     * [3]:
     *     Dividend: - The value contains a variable (1x, 2x,3x)
     *               - The value has got an exponent (x^1, x^2, x^3)
     *               --> Monom
     *     Divisor:  - Only a normal value (1, 2, 3)
     *               - No exponent
     *               --> Number
     *
     *     Calculation:
     *                 - Coefficient / number
     *                         +
     *                 - usedVariable
     *                         +
     *                 - exponent
     *
     *
     *
     * @param dividend
     *        The dividend (only a polynom element)
     * @param divisor
     *        The divisor (only a polynom element)
     * @return
     *        The result
     */
    static String division(String dividend, String divisor) {
        String output = "";

        /*[1]*/
        if (Polynom.getCoefficient(dividend) == 0 && Polynom.getCoefficient(divisor) == 0 && Polynom.getExponent(dividend) == 0 && Polynom.getExponent(divisor) == 0) {
            double temp = Double.parseDouble(dividend) / Double.parseDouble(divisor);
            output += temp >= 0 ? "+" + temp : temp;
        }
        /*[2]*/
        else if (Polynom.getCoefficient(dividend) != 0 && Polynom.getCoefficient(divisor) != 0 && Polynom.getExponent(dividend) != 0 && Polynom.getExponent(divisor) != 0) {
            double temp = Polynom.getCoefficient(dividend) / Polynom.getCoefficient(divisor);
            output += temp >= 0 ? "+" + temp : temp;
            output += PolynomMain.usedVariable + "^";
            output += Polynom.getExponent(dividend) - Polynom.getExponent(divisor);
        }
        /*[3]*/
        else if (Polynom.getCoefficient(dividend) != 0 && Polynom.getCoefficient(divisor) == 0 && Polynom.getExponent(dividend) != 0 && Polynom.getExponent(divisor) == 0) {
            double temp = Polynom.getCoefficient(dividend) / Double.parseDouble(divisor);
            output += temp >= 0 ? "+" + temp : temp;
            output += PolynomMain.usedVariable + "^";
            output += Polynom.getExponent(dividend);
        }

        //System.out.println(dividend + " : " + divisor + " = " + output);

        return Helper.clean(output);

    }



    /**
     * This calculates the multiplication between two values
     * The method iterates over all elements of the multiplicand
     * There are 4 cases:
     * [1]:
     *     Multiplier:    - Only a normal value (1, 2, 3)
     *                    - No exponent
     *                    --> Number
     *     Multiplicand:  - Only a normal value (1, 2, 3)
     *                    - No exponent
     *                    --> Number
     *
     *     Calculation:
     *                 - Number * number
     *
     * [2]:
     *     Multiplier:    - The value contains a variable (1x, 2x,3x)
     *                    - The value has got an exponent (x^1, x^2, x^3)
     *                    --> Monom
     *     Multiplicand:  - The value contains a variable (1x, 2x,3x)
     *                    - The value has got an exponent (x^1, x^2, x^3)
     *                    --> Monom
     *
     *     Calculation:
     *                 - Coefficient * coefficient
     *                         +
     *                 - usedVariable
     *                         +
     *                 - Exponent + exponent
     *
     * [3]:
     *     Multiplier:    - Only a normal value (1, 2, 3)
     *                    - No exponent
     *                    --> Number
     *     Multiplicand:  - The value contains a variable (1x, 2x,3x)
     *                    - The value has got an exponent (x^1, x^2, x^3)
     *                    --> Monom
     *
     *     Calculation:
     *                 - Number * coefficient
     *                         +
     *                 - usedVariable
     *                         +
     *                 - exponent
     *
     * [4]:
     *     Multiplier:    - The value contains a variable (1x, 2x,3x)
     *                    - The value has got an exponent (x^1, x^2, x^3)
     *                    --> Monom
     *     Multiplicand:  - Only a normal value (1, 2, 3)
     *                    - No exponent
     *                    --> Number
     *
     *     Calculation:
     *                 - Coefficient * number
     *                         +
     *                 - usedVariable
     *                         +
     *                 - exponent
     *
     *
     *
     * @param multiplier
     *        The multiplier (only a polynom element)
     * @param multiplicand
     *        The multiplicand (can be a full polynom)
     * @return
     *        The result
     */
    static String multiply(String multiplier, String multiplicand) {
        String output = "";

        for (String s : Polynom.splitPolynom(multiplicand)) {

            /*[1]*/
            if (Polynom.getCoefficient(multiplier) == 0 && Polynom.getCoefficient(s) == 0 && Polynom.getExponent(multiplier) == 0 && Polynom.getExponent(s) == 0) {
                double temp = Double.parseDouble(s) * Double.parseDouble(multiplier);
                output += temp >= 0 ? "+" + temp : temp;
            }
            /*[2]*/
            else if (Polynom.getCoefficient(multiplier) != 0 && Polynom.getCoefficient(s) != 0 && Polynom.getExponent(multiplier) != 0 && Polynom.getExponent(s) != 0) {
                double temp = Polynom.getCoefficient(multiplier) * Polynom.getCoefficient(s);
                output += temp >= 0 ? "+" + temp : temp;
                output += PolynomMain.usedVariable + "^";
                output += Polynom.getExponent(multiplier) + Polynom.getExponent(s);
            }
            /*[3]*/
            else if (Polynom.getCoefficient(multiplier) == 0 && Polynom.getCoefficient(s) != 0 && Polynom.getExponent(multiplier) == 0 && Polynom.getExponent(s) != 0) {
                double temp = Double.parseDouble(multiplier) * Polynom.getCoefficient(s);
                output += temp >= 0 ? "+" + temp : temp;
                output += PolynomMain.usedVariable + "^";
                output += Polynom.getExponent(s);
            }
            /*[4]*/
            else if (Polynom.getCoefficient(multiplier) != 0 && Polynom.getCoefficient(s) == 0 && Polynom.getExponent(multiplier) != 0 && Polynom.getExponent(s) == 0) {
                double temp = Polynom.getCoefficient(multiplier) * Double.parseDouble(s);
                output += temp >= 0 ? "+" + temp : temp;
                output += PolynomMain.usedVariable + "^";
                output += Polynom.getExponent(multiplier);
            }
        }

        //System.out.println(multiplier + " * " + multiplicand + " = " + output);

        return Helper.clean(output);
    }



    /**
     * This calculates the difference between two polynoms
     * The method iterates over all elements of the two polynoms
     * There are 3 cases:
     * [1]:
     *     Minuend:     - A monom with the same degree as the iterating value
     *     Subtrahend:  - A monom with the same degree as the iterating value
     *
     *     Calculation:
     *                 - _difference()
     *
     * [2]:
     *     Minuend:     - A monom with the same degree as the iterating value
     *     Subtrahend:  - No monom with the same degree as the iterating value
     *
     *     Calculation:
     *                 - Add the minuend
     *
     * [2]:
     *     Minuend:     - No monom with the same degree as the iterating value
     *     Subtrahend:  - A monom with the same degree as the iterating value
     *
     *     Calculation:
     *                 - _difference() -> subtracting the subtrahend from 0
     *
     *
     *
     * @param minuend
     *        The minuend (can be a full polynom)
     * @param subtrahend
     *        The subtrahend (can be a full polynom)
     * @return
     *        The result
     */
    static String difference(String minuend, String subtrahend) {
        String output = "";

        for (double i = Polynom.deg(minuend); i >= 0; i--) {
            /*[1]*/
            if (!Helper.elementFromPolynomByExpo(minuend, i).equals("") && !Helper.elementFromPolynomByExpo(subtrahend, i).equals("")) {
                output += _difference(Helper.elementFromPolynomByExpo(minuend, i), Helper.elementFromPolynomByExpo(subtrahend, i));
            }
            /*[2]*/
            else if (!Helper.elementFromPolynomByExpo(minuend, i).equals("") && Helper.elementFromPolynomByExpo(subtrahend, i).equals("")) {
                output += Helper.elementFromPolynomByExpo(minuend, i);
            }
            /*[3]*/
            else if (Helper.elementFromPolynomByExpo(minuend, i).equals("") && !Helper.elementFromPolynomByExpo(subtrahend, i).equals("")) {

                if (Polynom.getExponent(Helper.elementFromPolynomByExpo(subtrahend, i)) == 0) {
                    output += _difference("+0", Helper.elementFromPolynomByExpo(subtrahend, i));
                } else if (Polynom.getExponent(Helper.elementFromPolynomByExpo(subtrahend, i)) != 0) {
                    output += _difference("+0x^" + Polynom.getExponent(Helper.elementFromPolynomByExpo(subtrahend, i)), Helper.elementFromPolynomByExpo(subtrahend, i));
                }

            }
        }

        //System.out.println(minuend + " : " + subtrahend + " = " + output);

        return Helper.clean(output);
    }

    /**
     * TODO: Method description
     *
     * @param aMinuend
     *        The minuend (only a polynom element)
     * @param aSubtrahend
     *        The subtrahend (only a polynom element)
     * @return
     *        The difference between the two inputs
     */
    static String _difference(String aMinuend, String aSubtrahend) {
        String _output = "";

        //Selber Exponent!
        if ( Polynom.getExponent(aMinuend) != Polynom.getExponent(aSubtrahend) ) return "";

        if (Polynom.getExponent(aMinuend) != 0 && Polynom.getExponent(aSubtrahend) != 0 && !aMinuend.equals(aSubtrahend)) {
            double temp = Polynom.getCoefficient(aMinuend) - Polynom.getCoefficient(aSubtrahend);
            if (temp != 1.0 && temp != -1.0) _output += temp >= 0 ? "+" + temp : temp;
            else _output += temp == 1.0 ? "+" : "-";
            _output += PolynomMain.usedVariable;
            if (Polynom.getExponent(aMinuend) != 1.0 && Polynom.getExponent(aMinuend) != -1.0) _output += "^" + Polynom.getExponent(aMinuend);
            else if (Polynom.getExponent(aMinuend) == 1.0 || Polynom.getExponent(aMinuend) == -1.0) _output += "";
        } else if (Polynom.getExponent(aMinuend) == 0 && Polynom.getExponent(aSubtrahend) == 0 && !aMinuend.equals(aSubtrahend)) {
            double temp = Double.parseDouble(aMinuend) - Double.parseDouble(aSubtrahend);
            _output += temp >= 0 ? "+" + temp : temp;
        } else if (aMinuend.equals(aSubtrahend)) {
            _output += "";
        }

        return _output;
    }
}
