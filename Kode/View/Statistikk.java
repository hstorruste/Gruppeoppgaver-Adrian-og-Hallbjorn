package View;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;

public class Statistikk extends JPanel {

    private StatistikkVindu parentFrame;
    private ButtonGroup typeGruppe;
    private JRadioButton gruppeRadio, kategoriRadio, medisinNavnRadio, enMedisinRadio;
    private JList medisinList, leggTilMedisinList;
    private JButton leggTilKnapp, taBortKnapp, statistikkKnapp;
    private Calendar kalender;
    private JSpinner aarsspinner;
    private JTable tabell;
    private String[] navnString = {"Gruppe", "Kategori", "Medisin navn", "En medisin"};
    private String[] kolonnenavn = {"Type", "Jan", "Feb", "Mars", "April"};
    private Object[][] celler
            = {
                {
                    "Merkur", new Double(2440), new Integer(0),
                    Boolean.FALSE, Color.yellow
                },
                {
                    "Venus", new Double(6052), new Integer(0),
                    Boolean.FALSE, Color.yellow
                },
                {
                    "Jorda", new Double(6378), new Integer(1),
                    Boolean.FALSE, Color.blue
                },
                {
                    "Mars", new Double(3397), new Integer(2),
                    Boolean.FALSE, Color.red
                },
                {
                    "Jupiter", new Double(71492), new Integer(16),
                    Boolean.TRUE, Color.orange
                },
                {
                    "Saturn", new Double(60268), new Integer(18),
                    Boolean.TRUE, Color.orange
                },
                {
                    "Uranus", new Double(25559), new Integer(17),
                    Boolean.TRUE, Color.blue
                },
                {
                    "Neptun", new Double(24766), new Integer(8),
                    Boolean.TRUE, Color.blue
                },
                {
                    "Pluto", new Double(1137), new Integer(1),
                    Boolean.FALSE, Color.black
                }
            };
    private String[] eksempel = {"Gunnar", "Joppe", "Anders", "Gøran", "Adrian"};
    private String[] tall = {"2014", "2013", "2012", "2011"};
    private final int TEKSTFELTLENGDE = 5;

    private KnappeLytter knappeLytter;

    public Statistikk(StatistikkVindu a) {

        super();
        parentFrame = a;
        knappeLytter = new KnappeLytter();
        
        kalender = Calendar.getInstance();

        setLayout(new FlowLayout());

        add(statistikkGUI());
    }

    private JPanel statistikkGUI() {

        typeGruppe = new ButtonGroup();
        gruppeRadio = new JRadioButton(navnString[0], true);
        kategoriRadio = new JRadioButton(navnString[1], false);
        medisinNavnRadio = new JRadioButton(navnString[2], false);
        enMedisinRadio = new JRadioButton(navnString[3], false);
        typeGruppe.add(gruppeRadio);
        typeGruppe.add(kategoriRadio);
        typeGruppe.add(medisinNavnRadio);
        typeGruppe.add(enMedisinRadio);

        JPanel gruppeKatPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        gruppeKatPanel.add(gruppeRadio);
        gruppeKatPanel.add(kategoriRadio);

        JPanel medEnMedPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        medEnMedPanel.add(medisinNavnRadio);
        medEnMedPanel.add(enMedisinRadio);

        JPanel radioPanel = new JPanel(new GridLayout(0, 3, 5, 5));
        radioPanel.add(gruppeKatPanel);
        radioPanel.add(medEnMedPanel);

        medisinList = new JList<>(eksempel);

        leggTilKnapp = new JButton(">>");
        leggTilKnapp.addActionListener(knappeLytter);

        taBortKnapp = new JButton("<<");
        taBortKnapp.addActionListener(knappeLytter);

        JPanel knappPanel = new JPanel(new GridLayout(2, 0, 5, 120));
        knappPanel.add(leggTilKnapp);
        knappPanel.add(taBortKnapp);

        leggTilMedisinList = new JList<>(eksempel);

        JPanel listKnappPanel = new JPanel(new GridLayout(0, 3, 5, 5));
        listKnappPanel.add(new JScrollPane(medisinList));
        listKnappPanel.add(knappPanel);
        listKnappPanel.add(new JScrollPane(leggTilMedisinList));

        JLabel navnSpinner = new JLabel("År:");
        
        int aarNu = kalender.get(Calendar.YEAR);
        SpinnerModel aarModel = new SpinnerNumberModel(aarNu, aarNu - 100, aarNu, 1);
        aarsspinner = new JSpinner(aarModel);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(aarsspinner, "#");
        editor.getTextField().setEditable(false);
        aarsspinner.setEditor(editor);
        JPanel labelSpinnerPanel = new JPanel(new GridLayout(0,2, 5, 5));
        labelSpinnerPanel.add(navnSpinner);
        labelSpinnerPanel.add(aarsspinner);

        statistikkKnapp = new JButton("Statistikk");

        JPanel comboBoxKnappPanel = new JPanel(new BorderLayout());
        comboBoxKnappPanel.add(labelSpinnerPanel, BorderLayout.LINE_START);
        comboBoxKnappPanel.add(statistikkKnapp, BorderLayout.LINE_END);

        JPanel leggTilPanel = new JPanel(new BorderLayout());
        leggTilPanel.add(radioPanel, BorderLayout.PAGE_START);
        leggTilPanel.add(listKnappPanel, BorderLayout.CENTER);
        leggTilPanel.add(comboBoxKnappPanel, BorderLayout.PAGE_END);

        tabell = new JTable(celler, kolonnenavn);

        JPanel statistikk = new JPanel(new BorderLayout());
        statistikk.add(leggTilPanel, BorderLayout.PAGE_START);
        statistikk.add(new JScrollPane(tabell), BorderLayout.CENTER);

        return statistikk;
    }

    private class KnappeLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //if (e.getSource() ==)
        }
    }
}
