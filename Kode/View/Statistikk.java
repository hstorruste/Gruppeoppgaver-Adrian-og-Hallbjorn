/*Denne klassen upprättar alla komponenter till statistikk fanan.
  Laget av Adrian Westlund s198571.
  Siste versjon 14-05-2014*/
package View;

import Model.Medisin;
import Model.Pasient;
import Model.Resept;
import View.util.Komponent;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class Statistikk extends JPanel {

    private StatistikkVindu parentFrame;
    private ButtonGroup typeGruppe;
    private JRadioButton gruppeRadio, kategoriRadio, medisinRadio;
    private JList<String> medisinList, nyMedisinList;
    private DefaultListModel<String> listModell;
    private JButton leggTilKnapp, taBortKnapp, statistikkKnapp;
    private Calendar kalender;
    private JSpinner aarSpinner;
    private JSpinner.NumberEditor editor;
    private final int STARTAAR = 2012;
    private final int KOLONNER = 13;
    private final int ANTALLMAANEDER = 12;
    private JTable tabell;
    private DefaultTableModel tabellModell;
    private String[] navnString = {"Gruppe", "Kategori", "Medisin"};
    private String[] grupper = {"A", "B", "C"};
    private String[] medisinAlt, nyMedisinAlt;
    private String[] kolonnenavn = {"Type", "Jan", "Feb", "Mars", "April", "Mai", "Juni", "Juli", "Aug", "Sep", "Okt", "Nov", "Des"};
    private final int RADER = 25;
    private KnappeLytter knappeLytter;
    private RadioLytter radioLytter;

    public Statistikk(StatistikkVindu a) {

        super();
        parentFrame = a;
        knappeLytter = new KnappeLytter();
        radioLytter = new RadioLytter();
        kalender = Calendar.getInstance();
        tabellModell = new DefaultTableModel(kolonnenavn, RADER);
        setLayout(new FlowLayout());

        add(statistikkGUI());
    }

    //Oppretter alt i fanen for Statistikk.
    private JPanel statistikkGUI() {

        typeGruppe = new ButtonGroup();
        gruppeRadio = new JRadioButton(navnString[0], true);
        gruppeRadio.addActionListener(radioLytter);
        kategoriRadio = new JRadioButton(navnString[1], false);
        kategoriRadio.addActionListener(radioLytter);
        medisinRadio = new JRadioButton(navnString[2], false);
        medisinRadio.addActionListener(radioLytter);
        typeGruppe.add(gruppeRadio);
        typeGruppe.add(kategoriRadio);
        typeGruppe.add(medisinRadio);

        JPanel gruppeKatPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        gruppeKatPanel.add(gruppeRadio);
        gruppeKatPanel.add(kategoriRadio);

        JPanel medisinPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        medisinPanel.add(medisinRadio);

        JPanel radioPanel = new JPanel(new GridLayout(0, 5, 5, 5));
        radioPanel.add(gruppeKatPanel);
        radioPanel.add(medisinPanel);

        medisinList = new JList<>(grupper);

        leggTilKnapp = new JButton(">>");
        leggTilKnapp.addActionListener(knappeLytter);

        taBortKnapp = new JButton("<<");
        taBortKnapp.addActionListener(knappeLytter);

        JPanel knappPanel = new JPanel(new GridLayout(2, 0, 5, 120));
        knappPanel.add(leggTilKnapp);
        knappPanel.add(taBortKnapp);

        listModell = new DefaultListModel<>();
        nyMedisinList = new JList<>(listModell);
        nyMedisinList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        JPanel listKnappPanel = new JPanel(new GridLayout(0, 3, 5, 5));
        listKnappPanel.add(new JScrollPane(medisinList));
        listKnappPanel.add(knappPanel);
        listKnappPanel.add(new JScrollPane(nyMedisinList));

        JLabel navnSpinner = new JLabel("År:");

        int aarNu = kalender.get(Calendar.YEAR);
        SpinnerModel aarModel = new SpinnerNumberModel(aarNu, STARTAAR, aarNu, 1);
        aarSpinner = new JSpinner(aarModel);
        editor = new JSpinner.NumberEditor(aarSpinner, "#");
        editor.getTextField().setEditable(false);
        aarSpinner.setEditor(editor);
        JPanel labelSpinnerPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        labelSpinnerPanel.add(navnSpinner);
        labelSpinnerPanel.add(aarSpinner);

        statistikkKnapp = new JButton("Statistikk");
        statistikkKnapp.addActionListener(knappeLytter);

        JPanel comboBoxKnappPanel = new JPanel(new BorderLayout());
        comboBoxKnappPanel.add(labelSpinnerPanel, BorderLayout.LINE_START);
        comboBoxKnappPanel.add(statistikkKnapp, BorderLayout.LINE_END);

        JPanel leggTilPanel = new JPanel(new BorderLayout());
        leggTilPanel.add(radioPanel, BorderLayout.PAGE_START);
        leggTilPanel.add(listKnappPanel, BorderLayout.CENTER);
        leggTilPanel.add(comboBoxKnappPanel, BorderLayout.PAGE_END);

        tabell = new JTable(tabellModell);
        tabell.setEnabled(false);

        JPanel statistikk = new JPanel(new BorderLayout());
        statistikk.add(leggTilPanel, BorderLayout.PAGE_START);
        statistikk.add(new JScrollPane(tabell), BorderLayout.CENTER);

        return statistikk;
    }

    //Setter medisinList med alle navn på grupper.
    private void visGruppe() {
        medisinList.setListData(grupper);
    }

    //Setter medisinList med alle navn på kategorier.
    private void visKategori() {
        medisinAlt = parentFrame.getAlleKategori();
        medisinList.setListData(medisinAlt);
    }

    //Setter medisinList med alle navn på medisiner.
    private void visMedisin() {
        medisinAlt = parentFrame.getAlleMedisinnavn();
        medisinList.setListData(medisinAlt);
    }

    //Sjekker om navnet finns i nyMedisinList.
    private boolean finnes(String e, JList<String> liste) {
        ListModel<String> listemodell = liste.getModel();
        for (int i = 0; i < listemodell.getSize(); i++) {
            if (listemodell.getElementAt(i).equals(e)) {
                return true;
            }
        }
        return false;
    }

    //Setter inn en eller flere navn fra medisinList til nyMedisinList.
    private void settInnElement() {
        int antall = 0;
        java.util.List<String> liste = medisinList.getSelectedValuesList();
        String[] inn = new String[liste.size()];
        inn = liste.toArray(inn);
        for (int i = 0; i < inn.length; i++) {
            if (!finnes(inn[i], nyMedisinList)) {
                if (antall < RADER) {
                    antall++;
                    listModell.addElement(inn[i]);
                } else {
                    String melding = "Du kan bare leggetil " + RADER + " elementer.";
                    Komponent.popup(parentFrame, melding);
                }

            }
        }
    }

    //Fjerner en eller flere navn fra nyMedisinList.
    private void fjernElement() {
        java.util.List<String> liste = nyMedisinList.getSelectedValuesList();
        String[] fjern = new String[liste.size()];
        fjern = liste.toArray(fjern);
        for (int i = 0; i < fjern.length; i++) {
            listModell.removeElement(fjern[i]);
        }
    }

    //Setter inn verdier i tabellen.
    private void lageTabell() {
        String navn;

        for (int i = 0; i < listModell.size(); i++) {
            navn = listModell.elementAt(i);
            tabell.getModel().setValueAt(navn, i, 0);
            int aar = Integer.parseInt(editor.getTextField().getText());
            Integer[] nummer;
            nummer = finnResepter(navn, aar);
            for (int j = 1; j < KOLONNER; j++) {
                tabell.getModel().setValueAt(nummer[j - 1], i, j);

            }
        }
    }

    /*Finner hur många resepter som är skrivet på navnet 
     som sänds med på varje månad på året som sänds med.*/
    private Integer[] finnResepter(String navn, int aar) {

        Medisin[] medisinListe;
        medisinListe = parentFrame.finnMedisinNavn(navn);
        if (medisinListe.length == 0) {
            medisinListe = parentFrame.finnMedisinKategori(navn);
            if (medisinListe.length == 0) {
                medisinListe = parentFrame.finnMedisinGruppe(navn);
            }
        }

        Integer[] antallResept = new Integer[ANTALLMAANEDER];
        Arrays.fill(antallResept, 0);
        Resept[] reseptListe;
        Pasient[] pasientListe = parentFrame.getPasientListe(medisinListe);

        for (int i = 0; i < pasientListe.length; i++) {
            reseptListe = pasientListe[i].getReseptliste().finnReseptMedisin(medisinListe, null);
            for (int j = 0; j < reseptListe.length; j++) {
                Calendar dato = reseptListe[j].getDato();
                if (dato.get(Calendar.YEAR) == aar) {
                    int maaned = (int) dato.get(Calendar.MONTH);
                    antallResept[maaned]++;
                    antallResept[maaned] += reseptListe[j].getOpprinneligReit();
                }
            }
        }
        return antallResept;
    }

    private class KnappeLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == leggTilKnapp) {
                if (medisinList.getSelectedIndex() != -1) {
                    settInnElement();
                }
            } else if (e.getSource() == taBortKnapp) {
                fjernElement();
            } else if (e.getSource() == statistikkKnapp) {
                lageTabell();
            }
        }
    }

    private class RadioLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (gruppeRadio.isSelected()) {
                visGruppe();
            } else if (kategoriRadio.isSelected()) {
                visKategori();
            } else if (medisinRadio.isSelected()) {
                visMedisin();
            }
        }
    }
}
