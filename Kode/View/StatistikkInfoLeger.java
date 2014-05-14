/*Denne klassen upprättar alla komponenter till info leger fanan.
 Laget av Adrian Westlund s198571.
 Siste versjon 14-05-2014*/
package View;

import Model.Lege;
import View.util.Komponent;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class StatistikkInfoLeger extends JPanel {

    private StatistikkVindu parentFrame;
    private ButtonGroup antallGrupp;
    private JRadioButton alleRadio, sokRadio;
    private JTextField fornavnFelt, etternavnFelt;
    private JCheckBox a, b, c;
    private JButton sokKnapp;
    private JTextArea textArea;
    private String[] navnString = {"Alle", "Søk", "Reseptgruppe", "Legeliste", "Info om legen"};
    private JList<String> legeList;
    private String[] legeAlt;
    private Lege[] legeListe;
    private Lege lege;

    private final int TEKSTFELTLENGDE = 10;

    private KnappeLytter knappeLytter;
    private RadioLytter radioLytter;
    private ListLytter listLytter;

    public StatistikkInfoLeger(StatistikkVindu a) {
        super();
        parentFrame = a;
        knappeLytter = new KnappeLytter();
        radioLytter = new RadioLytter();
        listLytter = new ListLytter();

        setLayout(new FlowLayout());

        add(infoLegerGUI());
    }

    //Oppretter alt i fanen for Info om leger.
    private JPanel infoLegerGUI() {

        antallGrupp = new ButtonGroup();
        alleRadio = new JRadioButton(navnString[0], true);
        alleRadio.addActionListener(radioLytter);
        sokRadio = new JRadioButton(navnString[1], false);
        sokRadio.addActionListener(radioLytter);
        antallGrupp.add(alleRadio);
        antallGrupp.add(sokRadio);

        JPanel alleSokPanel = new JPanel(new GridLayout(2, 0, 5, 5));
        alleSokPanel.add(alleRadio);
        alleSokPanel.add(sokRadio);

        fornavnFelt = new JTextField(TEKSTFELTLENGDE);
        JComponent kompFornavnFelt = Komponent.labelFieldColumb("Fornavn", fornavnFelt);

        etternavnFelt = new JTextField(TEKSTFELTLENGDE);
        JComponent kompEtternavnFelt = Komponent.labelFieldColumb("Etternavn", etternavnFelt);

        JPanel radioNavnPanel = new JPanel(new GridLayout(0, 4, 5, 5));
        radioNavnPanel.add(alleSokPanel);
        radioNavnPanel.add(kompFornavnFelt);
        radioNavnPanel.add(kompEtternavnFelt);

        JLabel labelNavn = new JLabel(navnString[2]);

        a = new JCheckBox("A");
        b = new JCheckBox("B");
        c = new JCheckBox("C");

        JPanel checkbox = new JPanel(new GridLayout(0, 3, 5, 5));
        checkbox.add(a);
        checkbox.add(b);
        checkbox.add(c);

        JPanel labelCheckPanel = new JPanel(new GridLayout(2, 0, 5, 5));
        labelCheckPanel.add(labelNavn);
        labelCheckPanel.add(checkbox);

        sokKnapp = new JButton("Finn");
        sokKnapp.addActionListener(knappeLytter);
        JLabel tomt = new JLabel("");

        JPanel sokKnappPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        sokKnappPanel.add(sokKnapp);
        sokKnappPanel.add(tomt);

        JPanel sokPanel = new JPanel(new BorderLayout());
        sokPanel.add(sokKnappPanel, BorderLayout.LINE_START);

        JPanel topPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        topPanel.add(radioNavnPanel);
        topPanel.add(labelCheckPanel);
        topPanel.add(sokPanel);

        JLabel legeListNavn = new JLabel(navnString[3]);
        JLabel legeInfoAreaNavn = new JLabel(navnString[4]);

        JPanel navnListAreaPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        navnListAreaPanel.add(legeListNavn);
        navnListAreaPanel.add(legeInfoAreaNavn);

        legeList = new JList<>();
        legeList.addListSelectionListener(listLytter);

        textArea = new JTextArea(20, 15);
        textArea.setEditable(false);

        JPanel listAreaPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        listAreaPanel.add(new JScrollPane(legeList));
        listAreaPanel.add(new JScrollPane(textArea));

        JPanel infoLeger = new JPanel(new BorderLayout());
        infoLeger.add(topPanel, BorderLayout.PAGE_START);
        infoLeger.add(navnListAreaPanel, BorderLayout.CENTER);
        infoLeger.add(listAreaPanel, BorderLayout.PAGE_END);

        skjulFelt(infoLeger);

        return infoLeger;
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

    /*Sjekker alle verdier til vinduet og finner de leger som passer søket.
     setter sen in legene i legeListe med setLegeList()*/
    private void leggTilLegeList() {
        legeListe = null;
        if (alleRadio.isSelected()) {
            Lege[] leger = parentFrame.getLegeListe(null);
            if (a.isSelected() && b.isSelected() && c.isSelected()) {
                legeListe = sjekkC(leger);
                legeListe = sjekkB(legeListe);
                legeListe = sjekkA(legeListe);
                setLegeList(legeListe);
            } else if (a.isSelected() && b.isSelected()) {
                legeListe = sjekkB(leger);
                legeListe = sjekkA(legeListe);
                setLegeList(legeListe);
            } else if (a.isSelected() && c.isSelected()) {
                legeListe = sjekkC(leger);
                legeListe = sjekkA(legeListe);
                setLegeList(legeListe);
            } else if (a.isSelected()) {
                legeListe = sjekkA(leger);
                setLegeList(legeListe);
            } else if (b.isSelected() && c.isSelected()) {
                legeListe = sjekkC(leger);
                legeListe = sjekkB(legeListe);
                setLegeList(legeListe);
            } else if (b.isSelected()) {
                legeListe = sjekkB(leger);
                setLegeList(legeListe);
            } else if (c.isSelected()) {
                legeListe = sjekkC(leger);
                setLegeList(legeListe);
            } else {
                String melding = "Du må velge minst en reseptgruppe.";
                Komponent.popup(parentFrame, melding);
            }
        }
        if (sokRadio.isSelected()) {
            String etternavn = etternavnFelt.getText();
            String fornavn = fornavnFelt.getText();
            Lege[] leger = parentFrame.finnLegeNavn(etternavn, fornavn);
            if (a.isSelected() && b.isSelected() && c.isSelected()) {
                legeListe = sjekkC(leger);
                legeListe = sjekkB(legeListe);
                legeListe = sjekkA(legeListe);
                setLegeList(legeListe);
            } else if (a.isSelected() && b.isSelected()) {
                legeListe = sjekkB(leger);
                legeListe = sjekkA(legeListe);
                setLegeList(legeListe);
            } else if (a.isSelected() && c.isSelected()) {
                legeListe = sjekkC(leger);
                legeListe = sjekkA(legeListe);
                setLegeList(legeListe);
            } else if (a.isSelected()) {
                legeListe = sjekkA(leger);
                setLegeList(legeListe);
            } else if (b.isSelected() && c.isSelected()) {
                legeListe = sjekkC(leger);
                legeListe = sjekkB(legeListe);
                setLegeList(legeListe);
            } else if (b.isSelected()) {
                legeListe = sjekkB(leger);
                setLegeList(legeListe);
            } else if (c.isSelected()) {
                legeListe = sjekkC(leger);
                setLegeList(legeListe);
            } else {
                String melding = "Du må velge minst en reseptgruppe.";
                Komponent.popup(parentFrame, melding);
            }
        }
    }

//Legger til lege objekter i legeList
    private void setLegeList(Lege[] leger) {
        if (leger.length == 0) {
            String melding = "Finner ikke noen lege.";
            Komponent.popup(parentFrame, melding);
        } else {
            legeAlt = new String[leger.length];
            for (int i = 0; i < legeAlt.length; i++) {
                legeAlt[i] = leger[i].getNavn() + " " + leger[i].getEPost();
            }
            legeList.setListData(legeAlt);
        }
    }

    //Sjekker om legen får skriva resepter på gruppen A.
    private Lege[] sjekkA(Lege[] leger) {
        int k = 0;
        legeListe = new Lege[leger.length];
        for (int i = 0; i < leger.length; i++) {
            if (leger[i].getA() == true) {
                legeListe[k++] = leger[i];
            }
        }
        legeListe = Arrays.copyOfRange(legeListe, 0, k);

        return legeListe;
    }

    //Sjekker om legen får skriva resepter på gruppen B.
    private Lege[] sjekkB(Lege[] leger) {
        int k = 0;
        legeListe = new Lege[leger.length];
        for (int i = 0; i < leger.length; i++) {
            if (leger[i].getB() == true) {
                legeListe[k++] = leger[i];
            }
        }
        legeListe = Arrays.copyOfRange(legeListe, 0, k);

        return legeListe;
    }

    //Sjekker om legen får skriva resepter på gruppen C.
    private Lege[] sjekkC(Lege[] leger) {
        int k = 0;
        legeListe = new Lege[leger.length];
        for (int i = 0; i < leger.length; i++) {
            if (leger[i].getC() == true) {
                legeListe[k++] = leger[i];
            }
        }
        legeListe = Arrays.copyOfRange(legeListe, 0, k);

        return legeListe;
    }

    //Skriver ut resepten sin toString i reseptArea
    private void leggTilArea() {
        int i = legeList.getSelectedIndex();
        String reseptInfo = "";
        if (i != -1) {
            reseptInfo = legeListe[i].toString();
        }
        textArea.setText(reseptInfo);

    }

    private class KnappeLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == sokKnapp) {
                leggTilLegeList();
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
            if (e.getSource() == legeList) {
                leggTilArea();
            }
        }

    }
}
