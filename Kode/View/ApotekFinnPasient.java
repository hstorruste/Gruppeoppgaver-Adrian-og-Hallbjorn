package View;

/**
 * Detta är GUI för finn pasient. Den är en del av AdminVindu. Laget av Adrian
 * Westlund s198571 Siste versjon 05-05-2014
 */
import View.util.Komponent;
import Model.Pasient;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ApotekFinnPasient extends JPanel {

    private ApotekVindu parentFrame;
    private JTextField finnPasient;
    private JButton finnPasientKnapp;

    private KnappeLytter knappeLytter;

    public ApotekFinnPasient(ApotekVindu a) {
        super();

        parentFrame = a;
        knappeLytter = new KnappeLytter();

        setLayout(new FlowLayout());

        add(finnPasientGUI());
    }

    //Lagar allt till login
    public JPanel finnPasientGUI() {
        JPanel apotekFinnPasient = new JPanel(new GridLayout(0, 1, 5, 5));

        finnPasient = new JTextField(5);
        finnPasient.addActionListener(knappeLytter);
        JComponent kompFinnPasient = Komponent.labelFieldRow("Fødselsnummer", finnPasient);
        apotekFinnPasient.add(kompFinnPasient);

        finnPasientKnapp = new JButton("Finn");
        finnPasientKnapp.setPreferredSize(new Dimension(113, 20));
        finnPasientKnapp.addActionListener(knappeLytter);
        JPanel finnPasientKnappPanel = new JPanel(new BorderLayout());
        finnPasientKnappPanel.add(finnPasientKnapp, BorderLayout.LINE_END);
        apotekFinnPasient.add(finnPasientKnappPanel);

        return apotekFinnPasient;
    }

    //Metoden skal finner en pasient på fødselsnummer
    public void hittadPasient() {
        String fnr = finnPasient.getText();
        Pasient hittad = parentFrame.hittad(fnr);

        if (hittad == null) {
            String melding = "Feil fødselsnummer!";
            Komponent.popup(parentFrame, melding);
        } else {
            parentFrame.tegnApotekPasientGUI(hittad);
        }

    }

    private class KnappeLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == finnPasientKnapp || e.getSource() == finnPasient) {
                hittadPasient();
            }
        }

    }
}
