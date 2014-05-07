package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StatistikkMedHistorikk extends JPanel {

    private StatistikkVindu parentFrame;
    private JTextField fornavnFelt, etternavnFelt;
    private JComboBox<String> gruppFelt, kategoriFelt, medisinFelt;
    private ButtonGroup typeGrupp, antallGrupp;
    private JRadioButton legeRadio, pasientRadio, alleRadio, sokRadio;
    private JButton sokKnapp;
    private JList personList, reseptList;
    private JTextArea historikkArea;
    private String[] navnString = {"Lege", "Pasient", "Alle", "Søk", "Gruppe", "Kategori", "Medisin",
        "Personliste", "Reseptliste", "Resept info"};
    private String[] grupper = {"Alle", "A", "B", "C"};
    private String[] kategorier, medisiner, personAlt, reseptAlt;
    private String[] eksempel = {"Gunnar", "Joppe", "Anders", "Gøran", "Adrian"};

    private final int TEKSTFELTLENGDE = 10;

    private KnappeLytter knappeLytter;

    public StatistikkMedHistorikk(StatistikkVindu a) {

        super();
        parentFrame = a;
        knappeLytter = new KnappeLytter();

        setLayout(new FlowLayout());

        add(medisinHistorikkGUI());
    }

    private JPanel medisinHistorikkGUI() {

        typeGrupp = new ButtonGroup();
        legeRadio = new JRadioButton(navnString[0], false);
        pasientRadio = new JRadioButton(navnString[1], false);
        typeGrupp.add(legeRadio);
        typeGrupp.add(pasientRadio);

        antallGrupp = new ButtonGroup();
        alleRadio = new JRadioButton(navnString[2], false);
        sokRadio = new JRadioButton(navnString[3], false);
        antallGrupp.add(alleRadio);
        antallGrupp.add(sokRadio);

        JPanel legePasientAlleSokPanel = new JPanel(new GridLayout(2, 0, 5, 5));
        legePasientAlleSokPanel.add(legeRadio);
        legePasientAlleSokPanel.add(pasientRadio);
        legePasientAlleSokPanel.add(alleRadio);
        legePasientAlleSokPanel.add(sokRadio);

        fornavnFelt = new JTextField(TEKSTFELTLENGDE);
        JComponent kompFornavnFelt = Komponent.labelFieldColumb("Fornavn", fornavnFelt);

        etternavnFelt = new JTextField(TEKSTFELTLENGDE);
        JComponent kompEtternavnFelt = Komponent.labelFieldColumb("Etternavn", etternavnFelt);

        JPanel radioNavnPanel = new JPanel(new GridLayout(0, 6, 5, 5));
        radioNavnPanel.add(legePasientAlleSokPanel);
        radioNavnPanel.add(kompFornavnFelt, 1);
        radioNavnPanel.add(kompEtternavnFelt, 2);

        JPanel topPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        topPanel.add(radioNavnPanel);

        gruppFelt = new JComboBox<>(grupper);
        gruppFelt.setSelectedIndex(-1);
        gruppFelt.setEditable(false);
        gruppFelt.addActionListener(knappeLytter);
        JPanel gruppSkriv = Komponent.labelComboBoxColumb(navnString[4], gruppFelt);

        String[] kat = parentFrame.getAlleKategori();
        kategorier = new String[kat.length + 1];
        kategorier[0] = "Alle";
        for (int i = 1; i < kategorier.length; i++) {
            kategorier[i] = kat[i - 1];
        }

        kategoriFelt = new JComboBox<>(kategorier);
        kategoriFelt.setSelectedIndex(-1);
        kategoriFelt.setEditable(false);
        kategoriFelt.addActionListener(knappeLytter);
        JPanel kategoriSkriv = Komponent.labelComboBoxColumb(navnString[5], kategoriFelt);

        String[] med = parentFrame.getAlleMedisinnavn();
        medisiner = new String[med.length + 1];
        medisiner[0] = "Alle";
        for (int i = 1; i < medisiner.length; i++) {
            medisiner[i] = med[i - 1];
        }

        medisinFelt = new JComboBox<>(medisiner);
        medisinFelt.setSelectedIndex(-1);
        medisinFelt.setEditable(false);
        medisinFelt.addActionListener(knappeLytter);
        JPanel medisinSkriv = Komponent.labelComboBoxColumb(navnString[6], medisinFelt);

        JPanel buttomPanel = new JPanel(new GridLayout(0, 6, 5, 5));
        buttomPanel.add(gruppSkriv, 0);
        buttomPanel.add(kategoriSkriv, 1);
        buttomPanel.add(medisinSkriv, 2);

        sokKnapp = new JButton("Finn");
        sokKnapp.addActionListener(knappeLytter);
        JLabel tomt = new JLabel("");

        JPanel sokKnappPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        sokKnappPanel.add(sokKnapp);
        sokKnappPanel.add(tomt);

        JPanel sokPanel = new JPanel(new BorderLayout());
        sokPanel.add(sokKnappPanel, BorderLayout.LINE_START);

        JPanel topButtomPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        topButtomPanel.add(topPanel);
        topButtomPanel.add(buttomPanel);
        topButtomPanel.add(sokPanel);

        JLabel personListNavn = new JLabel(navnString[7]);
        JLabel reseptListNavn = new JLabel(navnString[8]);
        JLabel historikkAreaNavn = new JLabel(navnString[9]);

        JPanel navnListAreaPanel = new JPanel(new GridLayout(0, 3, 5, 5));
        navnListAreaPanel.add(personListNavn);
        navnListAreaPanel.add(reseptListNavn);
        navnListAreaPanel.add(historikkAreaNavn);

        personList = new JList<>(eksempel);

        reseptList = new JList<>(reseptAlt);

        historikkArea = new JTextArea(20, 10);
        historikkArea.setEditable(false);

        JPanel listAreaPanel = new JPanel(new GridLayout(0, 3, 5, 5));
        listAreaPanel.add(new JScrollPane(personList));
        listAreaPanel.add(new JScrollPane(historikkArea));
        listAreaPanel.add(new JScrollPane(historikkArea));

        JPanel medisinHistorikk = new JPanel(new BorderLayout());
        medisinHistorikk.add(topButtomPanel, BorderLayout.PAGE_START);
        medisinHistorikk.add(navnListAreaPanel, BorderLayout.CENTER);
        medisinHistorikk.add(listAreaPanel, BorderLayout.PAGE_END);

        return medisinHistorikk;
    }

    private void checkAllt() {
        if (gruppFelt.getSelectedIndex() == 0) {
            String[] kat = parentFrame.getAlleKategori();
            kategorier = new String[kat.length + 1];
            kategorier[0] = "Alle";
            for (int i = 1; i < kategorier.length; i++) {
                kategorier[i] = kat[i - 1];
            }
        } else {
            String gruppe = (String) gruppFelt.getSelectedItem();
            String[] kat = parentFrame.getAlleKategoriGruppe(gruppe);
            kategorier = new String[kat.length + 1];
            kategorier[0] = "Alle";
            for (int i = 1; i < kategorier.length; i++) {
                kategorier[i] = kat[i - 1];
            }
        }
        if (gruppFelt.getSelectedIndex() == 0 && kategoriFelt.getSelectedIndex() == 0) {
            String[] med = parentFrame.getAlleMedisinnavn();
            medisiner = new String[med.length + 1];
            medisiner[0] = "Alle";
            for (int i = 1; i < medisiner.length; i++) {
                medisiner[i] = med[i - 1];
            }
        } else if (gruppFelt.getSelectedIndex() == 0) {
            String gruppe = (String) gruppFelt.getSelectedItem();
            String[] med = parentFrame.getAlleMedisinnavnGruppe(gruppe);
            medisiner = new String[med.length + 1];
            medisiner[0] = "Alle";
            for (int i = 1; i < medisiner.length; i++) {
                medisiner[i] = med[i - 1];
            }
        } else if (kategoriFelt.getSelectedIndex() == 0) {
            String kategori = (String) kategoriFelt.getSelectedItem();
            String[] med = parentFrame.getAlleMedisinnavnKategori(kategori);
            medisiner = new String[med.length + 1];
            medisiner[0] = "Alle";
            for (int i = 1; i < medisiner.length; i++) {
                medisiner[i] = med[i - 1];
            }
        } else if (gruppFelt.getSelectedIndex() != 0 && kategoriFelt.getSelectedIndex() != 0) {
            String gruppe = (String) gruppFelt.getSelectedItem();
            String kategori = (String) kategoriFelt.getSelectedItem();
            String[] med = parentFrame.getAlleMedisinnavnGruppeKategori(gruppe, kategori);
            medisiner = new String[med.length + 1];
            medisiner[0] = "Alle";
            for (int i = 1; i < medisiner.length; i++) {
                medisiner[i] = med[i - 1];
            }
        }
    }

    private void leggtilPersonliste() {
        if (legeRadio.isSelected()) {
            if (alleRadio.isSelected()) {
                if (gruppFelt.getSelectedIndex() == 0 && kategoriFelt.getSelectedIndex() == 0
                        && medisinFelt.getSelectedIndex() == 0) {
                    //String[] personAlt = parentFrame.

                }
            }
        }
    }

    private class KnappeLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == gruppFelt || e.getSource() == kategoriFelt) {
                checkAllt();
            } else if (e.getSource() == sokKnapp) {
                leggtilPersonliste();
            }
        }
    }
}
