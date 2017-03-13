import java.util.regex.Pattern;

/**
 * 11.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class Polynom {
    private String wert;

    public Polynom() {
        wert = "";
    }

    public Polynom(String wert) {
        this.wert = wert;
    }

    public String clean() {
        this.wert = Helper.clean(this.wert);
        return this.wert;
    }

    public static String[] splitPolynom(String in) {
        String[] out_java7;    //Only needed if running on java 7
       

        String[] out = Helper.trimAll(in).split("(?=[+-])");

        if (out[0].isEmpty()) {
            out_java7 = new String[out.length - 1];
            for (int i = 0; i < out_java7.length; i++) {
                out_java7[i] = out[i + 1];
            }

            if (!out_java7[0].startsWith("+") && !out_java7[0].startsWith("-") ) out[0] = "+" + out[0];
            return out_java7;
        } else {
            if (!out[0].startsWith("+") && !out[0].startsWith("-") ) out[0] = "+" + out[0];
        }
       
        return out;
    }

    public static int deg(String s) {

        int highest = 0;
        String[] alleTeile = splitPolynom(s);

        for (String teil : alleTeile) {
            if (teil.contains("^")) {
                highest = Integer.parseInt(teil.split("\\^")[1]) > highest ? Integer.parseInt(teil.split("\\^")[1]) : highest;
            } else if (!teil.contains("^") && Pattern.compile("[a-z]").matcher(teil).find()) {
                highest = 1 > highest ? 1 : highest;
            }
        }

        return highest;
    }

    public static double getFaktor(String in) {
        if(in.matches("[+-][0-9]+[\\.]*[0-9]*[a-z]\\^[0-9]+[\\.]*[0-9]*")) {
            return Double.parseDouble(in.split("[a-z]")[0]);
        } else if (in.matches("[+-][a-z]\\^[0-9]+[\\.]*[0-9]*")) {
            return Double.parseDouble(in.split("[a-z]")[0] + 1.0);
        } else if (in.matches("[+-][0-9]+[\\.]*[0-9]*[a-z]")) {
            return Double.parseDouble(in.split("[a-z]")[0]);
        } else if (in.matches("[+-][a-z]")) {
            return Double.parseDouble(in.split("[a-z]")[0] + 1.0);
        } else if (in.matches("[+-][0-9]+[\\.]*[0-9]*")){
            return 0;
        } else {
            return 0;
        }
    }

    public static double getExponent(String in) {
        if(in.matches("[+-][0-9]+[\\.]*[0-9]*[a-z]\\^[0-9]+[\\.]*[0-9]*")) {
            return Double.parseDouble(in.split("\\^")[1]);
        } else if (in.matches("[+-][a-z]\\^[0-9]+[\\.]*[0-9]*")) {
            return Double.parseDouble(in.split("\\^")[1]);
        } else if (in.matches("[+-][0-9]+[a-z]")) {
            return 1.0;
        } else if (in.matches("[+-][a-z]")) {
            return 1.0;
        } else if (in.matches("[+-][0-9]+[\\.]*[0-9]*[a-z]")){
            return 1.0;
        } else if (in.matches("[+-][0-9]+[\\.]*[0-9]*")){
            return 0;
        } else {
            return 0;
        }
    }

    public String getWert() {
        return wert;
    }

    public void setWert(String wert) {
        this.wert = wert;
    }
}
