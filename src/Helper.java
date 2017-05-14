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

    static String compileFormattedOutput(String output, Polynom poly1, Polynom poly2, String ergebnis, String rest) {

        output = output.replace("&poly1", poly1.cleaned());
        output = output.replace("&poly2", poly2.cleaned());
        output = output.replace("&erg", Helper.clean(ergebnis));

        if (rest.equals("+0") || rest.equals("+") || rest.equals("0") || rest.isEmpty()) output = output.replace("&rest", "");
        else output = output.replace("&rest", "+ (" + Helper.clean(rest) + "/" + poly2.cleaned() + ")");

        return output;
    }

    static String compileOutput(String output, Polynom poly2, String ergebnis, String rest) {

        output = output.replace("&erg", Helper.clean(ergebnis));

        if (rest.equals("+0") || rest.equals("+") || rest.equals("0") || rest.isEmpty()) output = output.replace("&rest", "");
        else output = output.replace("&rest", "+ (" + Helper.clean(rest) + "/" + poly2.cleaned() + ")");

        return output;
    }

    static String clean(String s) {
        s = to_str(s.split("\\.0"));
        String[] t = Polynom.splitPolynom(s);

        for (int i = 0; i < t.length; i++) {

            if (t[i].matches("[+-]?[0]+[a-zA-Z].*")) t[i] = "";
            if (t[i].matches(".*[a-zA-Z]\\^0")) t[i] = t[i].split("[a-zA-Z]")[0];

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
