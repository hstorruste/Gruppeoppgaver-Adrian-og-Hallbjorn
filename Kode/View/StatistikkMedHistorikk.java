/*Denne klassen upprättar alla komponenter till medisin historikk fanan.
 Laget av Adrian Westlund s198571.
 Siste versjon 14-05-2014*/
package View;

import Controller.ReseptComparator;
import Model.*;
import View.util.Komponent;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.TreeSet;
import javax.swing.*;
import javax.swing.event.*;

public class StatistikkMedHistorikk extends JPanel {

    private StatistikkVindu parentFrame;
    private JTextField fornavnFelt, etternavnFelt;
    private JComboBox<String> gruppFelt, kategoriFelt, medisinFelt;
    private ButtonGroup typeGrupp, antallGrupp;
    private JRadioButton legeRadio, pasientRadio, alleRadio, sokRadio;
    private JButton sokKnapp;
    private JList personList, reseptList;
    private JTextArea reseptArea;
    private String[] navnString = {"Lege", "Pasient", "Alle", "Søk", "Gruppe", "Kategori", "Medisin",
        "Personliste", "Reseptliste", "Resept info"};
    private String[] grupper = {"Alle", "A", "B", "C"};
    private String[] kategorier, medisiner, personAlt, reseptAlt;
    private Lege[] legeListe;
    private Pasient[] pasientListe;
    private Medisin[] medisinListe;
    private Resept[] reseptListe;
    private Lege lege;
    private Pasient pasient;

    private final int TEKSTFELTLENGDE = 10;

    private KnappeLytter knappeLytter;
    private RadioLytter radioLytter;
    private ListLytter listLytter;

    public StatistikkMedHistorikk(StatistikkVindu a) {

        super();
        parentFrame = a;
        knappeLytter = new KnappeLytter();
        radioLytter = new RadioLytter();
        listLytter = new ListLytter();

        setLayout(new FlowLayout());

        add(medisinHistorikkGUI());
    }

    //Oppretter alt i fanen for Medisin historikk.
    private JPanel medisinHistorikkGUI() {

        typeGrupp = new ButtonGroup();
        legeRadio = new JRadioButton(navnString[0], true);
        pasientRadio = new JRadioButton(navnString[1], false);
        typeGrupp.add(legeRadio);
        typeGrupp.add(pasientRadio);

        antallGrupp = new ButtonGroup();
        alleRadio = new JRadioButton(navnString[2], true);
        alleRadio.addActionListener(radioLytter);
        sokRadio = new JRadioButton(navnString[3], false);
        sokRadio.addActionListener(radioLytter);
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
        gruppFelt.setSelectedIndex(0);
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
        kategoriFelt.setSelectedIndex(0);
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
        medisinFelt.setSelectedIndex(0);
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

        personList = new JList<>();
        personList.addListSelectionListener(listLytter);

        reseptList = new JList<>();
        reseptList.addListSelectionListener(listLytter);

        reseptArea = new JTextArea(20, 10);
        reseptArea.setEditable(false);

        JPanel listAreaPanel = new JPanel(new GridLayout(0, 3, 5, 5));
        listAreaPanel.add(new JScrollPane(personList));
        listAreaPanel.add(new JScrollPane(reseptList));
        listAreaPanel.add(new JScrollPane(reseptArea));

        JPanel medisinHistorikk = new JPanel(new BorderLayout());
        medisinHistorikk.add(topButtomPanel, BorderLayout.PAGE_START);
        medisinHistorikk.add(navnListAreaPanel, BorderLayout.CENTER);
        medisinHistorikk.add(listAreaPanel, BorderLayout.PAGE_END);

        skjulFelt(medisinHistorikk);

        return medisinHistorikk;
    }

    /*Viser felt for søking av lege/pasient, tar imot en container som alle 
     komponentene ligger i.*/
    private void visFelt(Container cont) {
        JComponent[] felt = new JComponent[]{fornavnFelt, etternavnFelt};

        for (JComponent child : felt) {
            child.setVisible(true);
            JLabel merkelapp = Komponent.finnLabelTilFelt(cont, child);
            if (merkelapp != null) {
                merkelapp.setVisible(true);
            }
        }
    }

    /*Skjuler felt for søking av lege/pasient, tar imot en container som alle 
     komponentene ligger i.*/
    private void skjulFelt(Container cont) {
        JComponent[] felt = new JComponent[]{fornavnFelt, etternavnFelt};

        for (JComponent child : felt) {
            child.setVisible(false);
            JLabel merkelapp = Komponent.finnLabelTilFelt(cont, child);
            if (merkelapp != null) {
                merkelapp.setVisible(false);
            }
        }
    }

    //Sjekker forandringer i gruppfelt
    private void checkGruppFelt() {
        if (gruppFelt.getSelectedIndex() == 0) {
            String[] kat = parentFrame.getAlleKategori();
            kategoriFelt.removeAllItems();
            kategorier = new String[kat.length + 1];
            kategorier[0] = "Alle";
            for (int i = 1; i < kategorier.length; i++) {
                kategorier[i] = kat[i - 1];
            }
            for (int i = 0; i < kategorier.length; i++) {
                kategoriFelt.insertItemAt(kategorier[i], i);
            }
        } else {
            String gruppe = (String) gruppFelt.getSelectedItem();
            String[] kat = parentFrame.getAlleKategoriGruppe(gruppe);
            kategoriFelt.removeAllItems();
            kategorier = new String[kat.length + 1];
            kategorier[0] = "Alle";
            for (int i = 1; i < kategorier.length; i++) {
                kategorier[i] = kat[i - 1];
            }
            for (int i = 0; i < kategorier.length; i++) {
                kategoriFelt.insertItemAt(kategorier[i], i);
            }
        }
        kategoriFelt.setSelectedIndex(0);
        medisinFelt.setSelectedIndex(0);
    }

    //Sjekker forandringer i kategorifelt
    private void checkKategoriFelt() {
        if (gruppFelt.getSelectedIndex() == 0 && kategoriFelt.getSelectedIndex() == 0) {
            String[] med = parentFrame.getAlleMedisinnavn();
            medisinFelt.removeAllItems();
            medisiner = new String[med.length + 1];
            medisiner[0] = "Alle";
            for (int i = 1; i < medisiner.length; i++) {
                medisiner[i] = med[i - 1];
            }
            for (int i = 0; i < medisiner.length; i++) {
                medisinFelt.insertItemAt(medisiner[i], i);
            }
        } else if (gruppFelt.getSelectedIndex() != 0 && kategoriFelt.getSelectedIndex() != 0) {
            String gruppe = (String) gruppFelt.getSelectedItem();
            String kategori = (String) kategoriFelt.getSelectedItem();
            String[] med = parentFrame.getAlleMedisinnavnGruppeKategori(gruppe, kategori);
            medisinFelt.removeAllItems();
            medisiner = new String[med.length + 1];
            medisiner[0] = "Alle";
            for (int i = 1; i < medisiner.length; i++) {
                medisiner[i] = med[i - 1];
            }
            for (int i = 0; i < medisiner.length; i++) {
                medisinFelt.insertItemAt(medisiner[i], i);
            }
        } else if (gruppFelt.getSelectedIndex() != 0) {
            String gruppe = (String) gruppFelt.getSelectedItem();
            String[] med = parentFrame.getAlleMedisinnavnGruppe(gruppe);
            medisinFelt.removeAllItems();
            medisiner = new String[med.length + 1];
            medisiner[0] = "Alle";
            for (int i = 1; i < medisiner.length; i++) {
                medisiner[i] = med[i - 1];
            }
            for (int i = 0; i < medisiner.length; i++) {
                medisinFelt.insertItemAt(medisiner[i], i);
            }
        } else if (kategoriFelt.getSelectedIndex() != 0) {
            String kategori = (String) kategoriFelt.getSelectedItem();
            String[] med = parentFrame.getAlleMedisinnavnKategori(kategori);
            medisinFelt.removeAllItems();
            medisiner = new String[med.length + 1];
            medisiner[0] = "Alle";
            for (int i = 1; i < medisiner.length; i++) {
                medisiner[i] = med[i - 1];
            }
            for (int i = 0; i < medisiner.length; i++) {
                medisinFelt.insertItemAt(medisiner[i], i);
            }
        }
        medisinFelt.setSelectedIndex(0);
    }

    //Legger til person objekter i personList
    private void leggtilPersonlist() {

        legeListe = null;
        pasientListe = null;
        reseptListe = null;
        reseptAlt = new String[]{};
        reseptList.setListData(reseptAlt);

        if (legeRadio.isSelected()) {
            if (alleRadio.isSelected()) {
                if (gruppFelt.getSelectedIndex() == 0 && kategoriFelt.getSelectedIndex() == 0
                        && medisinFelt.getSelectedIndex() == 0) {

                    medisinListe = null;
                    legeListe = parentFrame.getLegeListe(medisinListe);
                    personAlt = new String[legeListe.length];
                    for (int i = 0; i < personAlt.length; i++) {
                        personAlt[i] = legeListe[i].getNavn() + " " + legeListe[i].getEPost();
                    }
                    personList.setListData(personAlt);
                }
                if (medisinFelt.getSelectedIndex() != 0) {
                    String medisinNavn = (String) medisinFelt.getSelectedItem();
                    medisinListe = parentFrame.finnMedisinNavn(medisinNavn);

                    legeListe = parentFrame.getLegeListe(medisinListe);
                    if (legeListe.length == 0) {
                        String melding = "Finner ikke noen lege med den medisinen.";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        personAlt = new String[legeListe.length];
                        for (int i = 0; i < personAlt.length; i++) {
                            personAlt[i] = legeListe[i].getNavn() + " " + legeListe[i].getEPost();
                        }
                        personList.setListData(personAlt);
                    }
                } else if (kategoriFelt.getSelectedIndex() != 0) {
                    String kategoriNavn = (String) kategoriFelt.getSelectedItem();
                    medisinListe = parentFrame.finnMedisinKategori(kategoriNavn);

                    legeListe = parentFrame.getLegeListe(medisinListe);
                    if (legeListe.length == 0) {
                        String melding = "Finner ikke noen lege med dem medisinnerna.";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        personAlt = new String[legeListe.length];
                        for (int i = 0; i < personAlt.length; i++) {
                            personAlt[i] = legeListe[i].getNavn() + " " + legeListe[i].getEPost();
                        }
                        personList.setListData(personAlt);
                    }
                } else if (gruppFelt.getSelectedIndex() != 0) {
                    String gruppeNavn = (String) gruppFelt.getSelectedItem();
                    medisinListe = parentFrame.finnMedisinGruppe(gruppeNavn);

                    legeListe = parentFrame.getLegeListe(medisinListe);
                    if (legeListe.length == 0) {
                        String melding = "Finner ikke noen lege med dem medisinerna.";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        personAlt = new String[legeListe.length];
                        for (int i = 0; i < personAlt.length; i++) {
                            personAlt[i] = legeListe[i].getNavn() + " " + legeListe[i].getEPost();
                        }
                        personList.setListData(personAlt);
                    }
                }
            }
            if (sokRadio.isSelected()) {
                String fornavn = fornavnFelt.getText();
                String etternavn = etternavnFelt.getText();
                if (gruppFelt.getSelectedIndex() == 0 && kategoriFelt.getSelectedIndex() == 0
                        && medisinFelt.getSelectedIndex() == 0) {

                    legeListe = parentFrame.finnLegeNavn(etternavn, fornavn);
                    if (legeListe.length == 0) {
                        String melding = "Finner ikke noen lege med det navnet";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        personAlt = new String[legeListe.length];
                        for (int i = 0; i < personAlt.length; i++) {
                            personAlt[i] = legeListe[i].getNavn() + " " + legeListe[i].getEPost();
                        }
                        personList.setListData(personAlt);
                    }
                } else if (medisinFelt.getSelectedIndex() != 0) {
                    String medisinNavn = (String) medisinFelt.getSelectedItem();
                    medisinListe = parentFrame.finnMedisinNavn(medisinNavn);

                    legeListe = parentFrame.finnLegeNavn(etternavn, fornavn, medisinListe);
                    if (legeListe.length == 0) {
                        String melding = "Finner ikke noen lege med det navnet eller medisinen";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        personAlt = new String[legeListe.length];
                        for (int i = 0; i < personAlt.length; i++) {
                            personAlt[i] = legeListe[i].getNavn() + " " + legeListe[i].getEPost();
                        }
                        personList.setListData(personAlt);
                    }
                } else if (kategoriFelt.getSelectedIndex() != 0) {
                    String kategoriNavn = (String) kategoriFelt.getSelectedItem();
                    medisinListe = parentFrame.finnMedisinKategori(kategoriNavn);

                    legeListe = parentFrame.finnLegeNavn(etternavn, fornavn, medisinListe);
                    if (legeListe.length == 0) {
                        String melding = "Finner ikke noen lege med det navnet eller medisinerna";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        personAlt = new String[legeListe.length];
                        for (int i = 0; i < personAlt.length; i++) {
                            personAlt[i] = legeListe[i].getNavn() + " " + legeListe[i].getEPost();
                        }
                        personList.setListData(personAlt);
                    }
                } else if (gruppFelt.getSelectedIndex() != 0) {
                    String gruppeNavn = (String) gruppFelt.getSelectedItem();
                    medisinListe = parentFrame.finnMedisinGruppe(gruppeNavn);

                    legeListe = parentFrame.finnLegeNavn(etternavn, fornavn, medisinListe);
                    if (legeListe.length == 0) {
                        String melding = "Finner ikke noen lege med det navnet eller medisinerna";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        personAlt = new String[legeListe.length];
                        for (int i = 0; i < personAlt.length; i++) {
                            personAlt[i] = legeListe[i].getNavn() + " " + legeListe[i].getEPost();
                        }
                        personList.setListData(personAlt);
                    }
                }
            }//End if søk
        }//End if lege
        if (pasientRadio.isSelected()) {
            if (alleRadio.isSelected()) {
                if (gruppFelt.getSelectedIndex() == 0 && kategoriFelt.getSelectedIndex() == 0
                        && medisinFelt.getSelectedIndex() == 0) {

                    medisinListe = null;
                    pasientListe = parentFrame.getPasientListe(medisinListe);
                    personAlt = new String[pasientListe.length];
                    for (int i = 0; i < personAlt.length; i++) {
                        personAlt[i] = pasientListe[i].getNavn() + " " + pasientListe[i].getFnr();
                    }
                    personList.setListData(personAlt);
                } else if (medisinFelt.getSelectedIndex() != 0) {
                    String medisinNavn = (String) medisinFelt.getSelectedItem();
                    medisinListe = parentFrame.finnMedisinNavn(medisinNavn);

                    pasientListe = parentFrame.getPasientListe(medisinListe);
                    if (pasientListe.length == 0) {
                        String melding = "Finner ikke noe pasienter med den medisinen.";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        personAlt = new String[pasientListe.length];
                        for (int i = 0; i < personAlt.length; i++) {
                            personAlt[i] = pasientListe[i].getNavn() + " " + pasientListe[i].getFnr();
                        }
                        personList.setListData(personAlt);
                    }
                } else if (kategoriFelt.getSelectedIndex() != 0) {
                    String kategoriNavn = (String) kategoriFelt.getSelectedItem();
                    medisinListe = parentFrame.finnMedisinKategori(kategoriNavn);

                    pasientListe = parentFrame.getPasientListe(medisinListe);
                    if (pasientListe.length == 0) {
                        String melding = "Finner ikke noe pasienter med dem medisinnerna.";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        personAlt = new String[pasientListe.length];
                        for (int i = 0; i < personAlt.length; i++) {
                            personAlt[i] = pasientListe[i].getNavn() + " " + pasientListe[i].getFnr();
                        }
                        personList.setListData(personAlt);
                    }
                } else if (gruppFelt.getSelectedIndex() != 0) {
                    String gruppeNavn = (String) gruppFelt.getSelectedItem();
                    medisinListe = parentFrame.finnMedisinGruppe(gruppeNavn);

                    pasientListe = parentFrame.getPasientListe(medisinListe);
                    if (pasientListe.length == 0) {
                        String melding = "Finner ikke noen pasient med dem medisinerna.";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        personAlt = new String[pasientListe.length];
                        for (int i = 0; i < personAlt.length; i++) {
                            personAlt[i] = pasientListe[i].getNavn() + " " + pasientListe[i].getFnr();
                        }
                        personList.setListData(personAlt);
                    }
                }
            }
            if (sokRadio.isSelected()) {
                String etternavn = etternavnFelt.getText();
                String fornavn = fornavnFelt.getText();
                if (gruppFelt.getSelectedIndex() == 0 && kategoriFelt.getSelectedIndex() == 0
                        && medisinFelt.getSelectedIndex() == 0) {

                    pasientListe = parentFrame.finnPasientNavn(etternavn, fornavn);
                    if (pasientListe.length == 0) {
                        String melding = "Finner ikke noen pasient med det navnet.";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        personAlt = new String[pasientListe.length];
                        for (int i = 0; i < personAlt.length; i++) {
                            personAlt[i] = pasientListe[i].getNavn() + " " + pasientListe[i].getFnr();
                        }
                        personList.setListData(personAlt);
                    }
                } else if (medisinFelt.getSelectedIndex() != 0) {
                    String medisinNavn = (String) medisinFelt.getSelectedItem();
                    medisinListe = parentFrame.finnMedisinNavn(medisinNavn);

                    pasientListe = parentFrame.finnPasientNavn(etternavn, fornavn, medisinListe);
                    if (pasientListe.length == 0) {
                        String melding = "Finner ikke noen pasient med det navnet eller medisinen.";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        personAlt = new String[pasientListe.length];
                        for (int i = 0; i < personAlt.length; i++) {
                            personAlt[i] = pasientListe[i].getNavn() + " " + pasientListe[i].getFnr();
                        }
                        personList.setListData(personAlt);
                    }
                } else if (kategoriFelt.getSelectedIndex() != 0) {
                    String kategoriNavn = (String) kategoriFelt.getSelectedItem();
                    medisinListe = parentFrame.finnMedisinKategori(kategoriNavn);

                    pasientListe = parentFrame.finnPasientNavn(etternavn, fornavn, medisinListe);
                    if (pasientListe.length == 0) {
                        String melding = "Finner ikke noen pasient med det navnet eller medisinerna.";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        personAlt = new String[pasientListe.length];
                        for (int i = 0; i < personAlt.length; i++) {
                            personAlt[i] = pasientListe[i].getNavn() + " " + pasientListe[i].getFnr();
                        }
                        personList.setListData(personAlt);
                    }
                } else if (gruppFelt.getSelectedIndex() != 0) {
                    String gruppeNavn = (String) gruppFelt.getSelectedItem();
                    medisinListe = parentFrame.finnMedisinGruppe(gruppeNavn);

                    pasientListe = parentFrame.finnPasientNavn(etternavn, fornavn, medisinListe);
                    if (pasientListe.length == 0) {
                        String melding = "Finner ikke noen pasient med det navnet eller medisinerna.";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        personAlt = new String[pasientListe.length];
                        for (int i = 0; i < personAlt.length; i++) {
                            personAlt[i] = pasientListe[i].getNavn() + " " + pasientListe[i].getFnr();
                        }
                        personList.setListData(personAlt);
                    }
                }
            }// End if søk
        }// End if pasient
    }//End metod

    //Legger til resepter i reseptList
    private void leggTilReseptList() {
        if (personList.getSelectedIndex() == -1) {
            return;
        }
        if (legeListe != null) {
            lege = legeListe[personList.getSelectedIndex()];
            Pasient[] pasienter = lege.getPasientliste().finnPasientMedisin(medisinListe);
            Comparator komp = new ReseptComparator();
            TreeSet<Resept> reseptSet = new TreeSet<>(komp);
            for (Pasient p : pasienter) {
                Resept[] r = p.getReseptliste().finnReseptMedisin(medisinListe, lege);
                for (Resept res : r) {
                    reseptSet.add(res);
                }
            }
            reseptListe = new Resept[reseptSet.size()];
            reseptListe = reseptSet.toArray(reseptListe);

            reseptAlt = new String[reseptListe.length];
            for (int i = 0; i < reseptAlt.length; i++) {
                reseptAlt[i] = reseptListe[i].getMedisin().getNavn() + ", " + reseptListe[i].getMedisin().getStyrke();
            }
            reseptList.setListData(reseptAlt);
        }
        if (pasientListe != null) {
            pasient = pasientListe[personList.getSelectedIndex()];
            reseptListe = pasient.getReseptliste().finnReseptMedisin(medisinListe, null);

            reseptAlt = new String[reseptListe.length];
            for (int i = 0; i < reseptAlt.length; i++) {
                reseptAlt[i] = reseptListe[i].getMedisin().getNavn() + ", " + reseptListe[i].getMedisin().getStyrke();
            }
            reseptList.setListData(reseptAlt);
        }
    }

    //Skriver ut resepten sin toString i reseptArea
    private void leggTilArea() {
        int i = reseptList.getSelectedIndex();
        String reseptInfo = "";
        if (i != -1) {
            reseptInfo = reseptListe[i].toString();
        }
        reseptArea.setText(reseptInfo);
    }

    private class KnappeLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == gruppFelt) {
                checkGruppFelt();
            } else if (e.getSource() == kategoriFelt) {
                checkKategoriFelt();
            } else if (e.getSource() == sokKnapp) {
                leggtilPersonlist();
            }
        }
    }

    private class RadioLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (sokRadio.isSelected()) {
                visFelt(parentFrame);
            } else if (alleRadio.isSelected()) {
                skjulFelt(parentFrame);
            }
        }
    }

    private class ListLytter implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            if (e.getSource() == personList) {
                reseptList.setSelectedIndex(-1);
                leggTilReseptList();
            } else if (e.getSource() == reseptList) {
                leggTilArea();
            }
        }

    }
}
