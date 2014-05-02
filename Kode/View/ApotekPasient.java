package View;

import java.awt.*;
import java.awt.event.*;
import java.util.TreeSet;
import javax.swing.*;

public class ApotekPasient extends JTabbedPane {

    private ApotekVindu parentFrame;
    private JTextField reseptNummerFelt;
    private JButton registrerKnapp, tillbakaKnappNye, tillbakaKnappHis;
    private JTextArea reseptArea, historikArea;

    private KnappeLytter knappeLytter;

    public ApotekPasient(ApotekVindu a) {

        super();
        parentFrame = a;

        knappeLytter = new KnappeLytter();

        JPanel nyeResepter = new JPanel(new FlowLayout());
        nyeResepter.add(nyeResepterGUI());

        JPanel pasientHistorik = new JPanel(new FlowLayout());
        pasientHistorik.add(pasientHistorikGUI());

        String kundNavn = parentFrame.getKund().getNavn();

        addTab("Rediger", nyeResepter);
        addTab(kundNavn + "Registrer", pasientHistorik);
    }

    public JPanel nyeResepterGUI() {
        JPanel nyeResepter = new JPanel(new GridLayout(0, 1, 5, 5));

        reseptNummerFelt = new JTextField(5);
        JComponent kompRegistrerResepter = Komponent.labelFieldRow("Reseptnummer", reseptNummerFelt);
        nyeResepter.add(kompRegistrerResepter);

        registrerKnapp = new JButton("Registrer utlevert");
        registrerKnapp.setPreferredSize(new Dimension(113, 20));
        registrerKnapp.addActionListener(knappeLytter);
        JPanel registrerKnappPanel = new JPanel(new BorderLayout());
        registrerKnappPanel.add(registrerKnapp, BorderLayout.LINE_END);
        nyeResepter.add(registrerKnappPanel);

        reseptArea = new JTextArea(20, 20);
        JScrollPane scrollPane = new JScrollPane(reseptArea);
        reseptArea.setEditable(false);
        String nyeResepterText = parentFrame.getKund().getReseptliste().finnNyeResepterString();
        reseptArea.setText(nyeResepterText);
        JPanel reseptAreaPanel = new JPanel(new BorderLayout());
        reseptAreaPanel.add(reseptArea, BorderLayout.CENTER);
        nyeResepter.add(reseptAreaPanel);

        tillbakaKnappNye = new JButton("Tilbake");
        tillbakaKnappNye.setPreferredSize(new Dimension(113, 20));
        tillbakaKnappNye.addActionListener(knappeLytter);
        JPanel tillbakaKnappNyePanel = new JPanel(new BorderLayout());
        tillbakaKnappNyePanel.add(tillbakaKnappNye, BorderLayout.LINE_END);
        nyeResepter.add(tillbakaKnappNyePanel);

        return nyeResepter;
    }

    public JPanel pasientHistorikGUI() {

        JPanel historik = new JPanel(new GridLayout(0, 1, 5, 5));

        historikArea = new JTextArea(20, 20);
        JScrollPane scrollPane = new JScrollPane(historikArea);
        historikArea.setEditable(false);
        String gamlaResepter = parentFrame.getKund().getReseptliste().finnGamleResepterString();
        historikArea.setText(gamlaResepter);
        JPanel historikAreaPanel = new JPanel(new BorderLayout());
        historikAreaPanel.add(historikArea, BorderLayout.CENTER);
        historik.add(historikAreaPanel);

        tillbakaKnappHis = new JButton("Tilbake");
        tillbakaKnappHis.setPreferredSize(new Dimension(113, 20));
        tillbakaKnappHis.addActionListener(knappeLytter);
        JPanel tillbakaKnappHisPanel = new JPanel(new BorderLayout());
        tillbakaKnappHisPanel.add(tillbakaKnappHis, BorderLayout.LINE_END);
        historik.add(tillbakaKnappHisPanel);

        return historik;
    }

    public void reseptUtlevert() {
        parentFrame.getKund().getReseptliste().finnResept(Integer.parseInt(reseptNummerFelt.getText())).setUtlevert();
    }

    private class KnappeLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registrerKnapp || e.getSource() == reseptNummerFelt) {
                reseptUtlevert();
            }
        }
    }
}
