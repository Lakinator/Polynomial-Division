import javax.swing.*;

/**
 * 12.03.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class GUI {
    private JFrame jf;
    private JButton startBtn;
    private JLabel teiler_lbl;
    private JLabel inputPoly1_lbl, inputPoly2_lbl;
    private JTextField inputPoly1, inputPoly2;
    private JLabel output_lbl;
    private JTextArea outputArea;

    public GUI()  {
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
        startBtn.addActionListener( e -> {

            if (!inputPoly1.getText().isEmpty() && !inputPoly2.getText().isEmpty()) {
                try {
                    Main.runMainLoop(inputPoly1.getText(), inputPoly2.getText());
                } catch (Exception ex) {
                    Main.OUTPUT_TO_GUI = "No output to display (Error: " + ex.getMessage() + ")";
                }
            } else Main.OUTPUT_TO_GUI  = "No output to display (Empty Input)";

            outputArea.setText(Main.OUTPUT_TO_GUI);
        });

        output_lbl = new JLabel("Output: ");
        output_lbl.setBounds(160, 140, 100, 20);

        outputArea = new JTextArea();
        outputArea.setBounds(10, 160, 360, 290);


        jf.add(inputPoly1_lbl);
        jf.add(inputPoly1);
        jf.add(teiler_lbl);
        jf.add(inputPoly2_lbl);
        jf.add(inputPoly2);
        jf.add(startBtn);
        jf.add(output_lbl);
        jf.add(outputArea);

        jf.setVisible(true);
    }
}
