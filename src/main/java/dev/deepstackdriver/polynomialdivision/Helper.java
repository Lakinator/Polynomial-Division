package dev.deepstackdriver.polynomialdivision;

/**
 * 11.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

class Helper {
    static String to_str(String[] arr) {
        String out = "";

        for (String s : arr) out += s;

        return out;
    }

    static String trimAll(String s) {
        String[] temp = s.split(" ");
        String out = "";

        for (String t : temp) out += t.trim();

        return out;
    }

    static String compileOutput(String format, Polynom dividend, Polynom divisor, String ergebnis, Polynom remainder, String calculation_steps) {

        format = format.replace("&dividend", dividend.cleaned());
        format = format.replace("&divisor", divisor.cleaned());
        format = format.replace("&result", Helper.clean(ergebnis));
        format = format.replace("&steps", calculation_steps);

        if (remainder.getValue().equals("+0") || remainder.getValue().equals("+") || remainder.getValue().equals("0") || remainder.getValue().isEmpty()) format = format.replace("+ &remainder", "");
        else format = format.replace("&remainder", "(" + remainder.cleaned() + "/" + divisor.cleaned() + ")");

        return format;
    }

    static String clean(String s) {
        s = to_str(s.split("\\.0"));
        String[] t = Polynom.splitPolynom(s);

        for (int i = 0; i < t.length; i++) {

            if (t[i].matches("[+-]?[0]+[a-zA-Z].*")) t[i] = "";
            if (t[i].matches("[+-]?[a-zA-Z]\\^0[.]?[0]*")) t[i] = "+1";
            if (t[i].matches(".+[a-zA-Z]\\^0[.]?[0]*")) t[i] = t[i].split("[a-zA-Z]\\^")[0];

            if (t[i].matches(".*[a-zA-Z]\\^1")) t[i] = t[i].split("\\^")[0];
            if (t[i].matches("[+-][1][a-zA-Z].*")) t[i] = t[i].replace("1", "");

            if (t[i].equals("+")) t[i] = "+0";

        }

        return to_str(t);
    }

    static String biggestExponentialElementFromPolynom(String in) {
        double highest = 0;
        int highestIndex = 0;

        for (int i = 0; i < Polynom.splitPolynom(in).length; i++) {
            if (Polynom.deg(Polynom.splitPolynom(in)[i]) > highest) {
                highest = Polynom.deg(Polynom.splitPolynom(in)[i]);
                highestIndex = i;
            }
        }

        return Polynom.splitPolynom(in)[highestIndex];
    }

    static String elementFromPolynomByExpo(String in, double expo) {

        for (int i = 0; i < Polynom.splitPolynom(in).length; i++) {
            if (Polynom.getExponent(Polynom.splitPolynom(in)[i]) == expo) return Polynom.splitPolynom(in)[i];
        }

        return "";

    }
}
