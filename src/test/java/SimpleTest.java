import de.lakinator.polynomialdivision.InvalidPolynomException;
import de.lakinator.polynomialdivision.PolynomMain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest {

    @Test
    void test() {
        String polynom1 = "3x^2-4x+2";
        String polynom2 = "x-1";

        try {
            if (PolynomMain.validateInput(polynom1, polynom2)) {
                String output = PolynomMain.calculate(polynom1, polynom2, false);
                System.out.println(output);

                assertEquals("+3x-1 + (+1/+x-1)", output);
            }
        } catch (InvalidPolynomException e) {
            // The error message
            System.out.println(e.getLocalizedMessage());
            // The index of the invalid polynom
            System.out.println(e.getIndexOfInvalidPolynom());
            // Or print them out together
            System.out.println(e.toString());
        }
    }

}
