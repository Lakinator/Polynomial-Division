/**
 * 11.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class Helper {
    public static String to_str(String[] arr) {
        String out = "";

        for (String s : arr) out += s;

        return out;
    }

    public static String trimAll(String s) {
        String[] temp = s.split(" ");
        String out = "";

        for (String t : temp) out += t.trim();

        return out;
    }

    public static String compileOutput(String output, Polynom poly1, Polynom poly2, String ergebnis, String rest) {

        output = output.replace("&poly1", poly1.clean());
        output = output.replace("&poly2", poly2.clean());
        output = output.replace("&erg", Helper.clean(ergebnis));

        if (rest.equals("+0")) output = output.replace("&rest", "");
        else output = output.replace("&rest", "+ " + Helper.clean(rest) + "/" + poly2.clean());

        return output;
    }

    public static String clean(String s) {
        s = to_str(s.split("\\.0"));
        String[] t = Polynom.splitPolynom(s);

        for (int i = 0; i < t.length; i++) {

            if (t[i].matches("[+-]0[a-z].*")) t[i] = "";
            if (t[i].matches(".*[a-z]\\^0")) t[i] = t[i].split("[a-z]")[0];

            if (t[i].matches("[+-]1[a-z].*")) t[i] = t[i].replace("1", "");
            if (t[i].matches(".*[a-z]\\^1")) t[i] = t[i].split("\\^")[0];


            /*if (Pattern.compile("[a-z]\\^0\\.0").matcher(t[i]).find()) t[i] = "1";
            if (Pattern.compile("0\\.0[a-z]").matcher(t[i]).find()) t[i] = "";
            if (Pattern.compile("[a-z]\\^1\\.0").matcher(t[i]).find()) t[i] = t[i].replace("^1.0", "");
            if (Pattern.compile("1\\.0[a-z]").matcher(t[i]).find()) t[i] = t[i].replace("1.0", "");

            if (Pattern.compile("[a-z]\\^0").matcher(t[i]).find()) t[i] = "1";
            if (Pattern.compile("[^\\.]0[a-z]").matcher(t[i]).find()) t[i] = "";
            if (Pattern.compile("[a-z]\\^1").matcher(t[i]).find()) t[i] = t[i].replace("^1", "");
            if (Pattern.compile("1[a-z]").matcher(t[i]).find()) t[i] = t[i].replace("1", "");*/

        }

        return to_str(t);
    }

    public static String biggestFromPolynom(String in) {
        int highest = 0;
        int highestIndex = 0;

        for (int i = 0; i < Polynom.splitPolynom(in).length; i++) {
            if (Polynom.deg(Polynom.splitPolynom(in)[i]) > highest) {
                highest = Polynom.deg(Polynom.splitPolynom(in)[i]);
                highestIndex = i;
            }
        }

        return Polynom.splitPolynom(in)[highestIndex];
    }
}
