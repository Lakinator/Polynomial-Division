import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Formatter;
import java.util.Scanner;

/**
 * 14.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

class ErrorLogger {
    static File errFile;
    private static String fileInhalt = "";
    private static Formatter formatter;
    private static String newline = System.getProperty("line.separator");

    static boolean openLog() {

        try {
            errFile = new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory() + "/log.txt");

            if (errFile.exists()) {
                Scanner sc = new Scanner(errFile);
                while (sc.hasNextLine()) fileInhalt += sc.nextLine() + newline;
                formatter = new Formatter(errFile);
                formatter.format("%s" + newline, fileInhalt);

            } else {
                formatter = new Formatter(errFile);
            }

            return true;

        } catch(FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    static void log(Exception ex) {

        String stackTrace = "";
        for (int i = 0; i < ex.getStackTrace().length; i++) {
            if (ex.getStackTrace()[i].getClassName().contains("net.bplaced.lakinator.PolynomLib.PolyMain") ||
                    ex.getStackTrace()[i].getClassName().contains("net.bplaced.lakinator.PolynomLib.GUI") ||
                    ex.getStackTrace()[i].getClassName().contains("net.bplaced.lakinator.PolynomLib.ErrorLogger") ||
                    ex.getStackTrace()[i].getClassName().contains("net.bplaced.lakinator.PolynomLib.Polynom") ||
                    ex.getStackTrace()[i].getClassName().contains("net.bplaced.lakinator.PolynomLib.PolynomMath") ||
                    ex.getStackTrace()[i].getClassName().contains("net.bplaced.lakinator.PolynomLib.Helper"))
                stackTrace += ex.getStackTrace()[i].getClassName() + "/" + ex.getStackTrace()[i].getMethodName() + ": Line:" + ex.getStackTrace()[i].getLineNumber() + newline;
        }

        formatter.format(newline + "[%s]" + newline + "Exception thrown: %s:" + newline + "%s",
                new Timestamp(System.currentTimeMillis()),
                ex.toString(),
                stackTrace);
    }

    static void closeLogger() {
        formatter.close();
    }
}
