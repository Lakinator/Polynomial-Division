package de.lakinator.polynomialdivision;

/**
 * 23.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

class PolynomSorter {

    static String sortPolynom(String polynom) {
        if (isSorted(polynom)) return polynom;

        String[] split_polynom = Polynom.splitPolynom(polynom);
        String[] sorted_polynom = new String[split_polynom.length];
        double[] expos = new double[split_polynom.length];

        //Filling the array with all exponents from the polynom
        for (int i = 0; i < expos.length; i++) expos[i] = Polynom.getExponent(split_polynom[i]);

        bubblesort(expos); //Sorting it

        for (int i = 0; i < expos.length; i++) {
            //Filling the sorted array with the elements from the polynom with their exponents
            sorted_polynom[i] = Helper.elementFromPolynomByExpo(polynom, expos[i]);
        }

        return Helper.to_str(sorted_polynom);
    }

    private static void bubblesort(double[] arr) {
        double temp;

        for(int i = 1; i < arr.length; i++) {

            for(int j = 0; j < arr.length-i; j++) {

                if(arr[j] < arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1]= temp;
                }

            }
        }

    }

    private static boolean isSorted(String polynom) {

        double highest = Polynom.deg(polynom)+1;

        for (String s : Polynom.splitPolynom(polynom)) {
            if (Polynom.getExponent(s) < highest) highest = Polynom.getExponent(s);
            else return false;
        }

        return true;
    }
}
