import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 12.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

class GUI {
    private JFrame jf;
    private JButton startBtn;
    private JLabel teiler_lbl;
    private JLabel inputPoly1_lbl, inputPoly2_lbl;
    private JTextField inputPoly1, inputPoly2;
    private JLabel output_lbl;
    private JTextArea outputArea;
    private JLabel version_lbl;

    GUI()  {
        jf = new JFrame();
        jf.setTitle("Polynomdivision");
        jf.setSize(400, 500);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);

        inputPoly1_lbl = new JLabel("Polynom 1: ");
        inputPoly1_lbl.setBounds(60, 10, 100, 20);

        inputPoly1 = new JTextField();
        inputPoly1.setBounds(20, 40, 150, 22);

        teiler_lbl = new JLabel(":");
        teiler_lbl.setBounds(182, 40, 20, 20);

        inputPoly2_lbl = new JLabel("Polynom 2: ");
        inputPoly2_lbl.setBounds(240, 10, 100, 20);

        inputPoly2 = new JTextField();
        inputPoly2.setBounds(200, 40, 150, 22);

        startBtn = new JButton("Berechne");
        startBtn.setBounds(130, 80, 100, 40);
        startBtn.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!inputPoly1.getText().isEmpty() && !inputPoly2.getText().isEmpty()) {
                    try {
                        PolyMain.runMainLoop(inputPoly1.getText(), inputPoly2.getText(), true);
                    } catch (Exception ex) {

                        if (ErrorLogger.openLog()) {
                            ErrorLogger.log(ex);
                            ErrorLogger.closeLogger();
                        } else {
                            System.err.println("Couldn't open ErrorLog File");
                        }

                        PolyMain.OUTPUT_TO_GUI = "No output to display.\nMore in the log.txt File: " + ErrorLogger.errFile.getPath() + "\n(Error: " + ex.getMessage() + ")";
                        ex.printStackTrace();
                    }
                } else PolyMain.OUTPUT_TO_GUI  = "No output to display (Empty Input)";

                outputArea.setText(PolyMain.OUTPUT_TO_GUI);
            }
        });

        output_lbl = new JLabel("Output: ");
        output_lbl.setBounds(160, 140, 100, 20);

        outputArea = new JTextArea();
        outputArea.setBounds(10, 160, 375, 290);
        outputArea.setEditable(false);

        version_lbl = new JLabel("Lukas Schalk | 2017 | Version: " + PolyMain.getVersion());
        version_lbl.setBounds(10, 450, 300, 20);


        jf.add(inputPoly1_lbl);
        jf.add(inputPoly1);
        jf.add(teiler_lbl);
        jf.add(inputPoly2_lbl);
        jf.add(inputPoly2);
        jf.add(startBtn);
        jf.add(output_lbl);
        jf.add(outputArea);
        jf.add(version_lbl);

        outputArea.setText("Wichtige Infos:\n-Alle Polynome müssen geordnet sein\n" +
                "-Es darf kein + oder - im Exponenten sein\n" +
                "-Falls etwas vom Output abgeschnitten ist, steht auch nochmal alles\n" +
                "  in der Konsole\n\n" +
                "Test Polynome:\n" +
                "5x^5-9x^4-3x^3+10x^2-2x : 2x^3-4x^2+2x\n" +
                "x^3+6x^2+9x+4 : x+1\n" +
                "x^3-5x^2-4x+8 : x-1\n" +
                "x^4+6x^3-4x^2-54x-45 : x-3\n" +
                "x^3-3x^2-2.25x+1.75 : x-3.5\n"
        );

        jf.setVisible(true);
    }
}
