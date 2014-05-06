package View;

import Controller.Legeregister;
import Model.Medisin;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*Denne klassen upprättar alla komponenter till medisin fanan.
 Laget av Adrian Westlund s198571.
 Siste versjon 06-05-2014*/
public class AdminMedisin extends JTabbedPane {

    private AdminVindu parentFrame;
    private JTextField finnMedisin, medisinNavn, medisinStyrke, medisinForm, medisinPakning, atc, 
            regMedisinNavn, regMedisinStyrke, regMedisinForm, regMedisinPakning, regATC;
    private JButton finnMedisinKnapp, seMedisinListeKnapp, medisinSpareKnapp, regMedisinSpareKnapp;
    private JTextArea medisinTextArea;
    private JComboBox<String> gruppVelger, kategoriVelger, regGruppVelger, regKategoriVelger;
    private String[] abc = {"A", "B", "C"};
    private String[] kategoriArray;
    private final int TEKSTFELTLENGDE = 10;

    private KnappeLytterMedisin knappeLytter;

    public AdminMedisin(AdminVindu a) {

        super();
        parentFrame = a;
        kategoriArray = parentFrame.skrivKatArray();
        knappeLytter = new KnappeLytterMedisin();
        JPanel rediger = new JPanel(new FlowLayout());
        rediger.add(redigerGUI());

        JPanel registrer = new JPanel(new FlowLayout());
        registrer.add(registrerGUI());

        addTab("Rediger", rediger);
        addTab("Registrer", registrer);
    }

    //Leger alla komponenter till medisin sin rediger fana.
    private JPanel redigerGUI() {
        JPanel medisinRediger = new JPanel(new GridLayout(0, 1, 5, 5));

        finnMedisin = new JTextField(TEKSTFELTLENGDE);
        finnMedisin.addActionListener(knappeLytter);
        JComponent kompFinnMedisin = Komponent.labelFieldRow("Navn", finnMedisin);
        medisinRediger.add(kompFinnMedisin);

        finnMedisinKnapp = new JButton("Finn");
        finnMedisinKnapp.setPreferredSize(new Dimension(152, 20));
        finnMedisinKnapp.addActionListener(knappeLytter);
        JPanel finnMedisinKnappPanel = new JPanel(new BorderLayout());
        finnMedisinKnappPanel.add(finnMedisinKnapp, BorderLayout.LINE_END);
        medisinRediger.add(finnMedisinKnappPanel);

        seMedisinListeKnapp = new JButton("Se hele listen");
        seMedisinListeKnapp.setPreferredSize(new Dimension(152, 20));
        seMedisinListeKnapp.addActionListener(knappeLytter);
        JPanel seMedisinListeKnappPanel = new JPanel(new BorderLayout());
        seMedisinListeKnappPanel.add(seMedisinListeKnapp, BorderLayout.LINE_END);
        medisinRediger.add(seMedisinListeKnappPanel);
        
        medisinNavn = new JTextField(TEKSTFELTLENGDE);
        JComponent kompNavn = Komponent.labelFieldRow("Navn", medisinNavn);
        medisinRediger.add(kompNavn);
                
        kategoriVelger = new JComboBox<>(kategoriArray);
        kategoriVelger.setSelectedIndex(-1);
        kategoriVelger.setEditable(true);
        JComponent kategori = Komponent.labelComboBoxRow("Kategori", kategoriVelger);
        medisinRediger.add(kategori);

        gruppVelger = new JComboBox<>(abc);
        gruppVelger.setSelectedIndex(-1);
        JComponent grupp = Komponent.labelComboBoxRow("Grupp", gruppVelger);
        medisinRediger.add(grupp);
        
        medisinStyrke = new JTextField(TEKSTFELTLENGDE);
        JComponent kompStyrke = Komponent.labelFieldRow("Styrke", medisinStyrke);
        medisinRediger.add(kompStyrke);
        
        medisinForm = new JTextField(TEKSTFELTLENGDE);
        JComponent kompForm = Komponent.labelFieldRow("Form", medisinForm);
        medisinRediger.add(kompForm);
        
        medisinPakning = new JTextField(TEKSTFELTLENGDE);
        JComponent kompPakning = Komponent.labelFieldRow("Pakning", medisinPakning);
        medisinRediger.add(kompPakning);

        atc = new JTextField(TEKSTFELTLENGDE);
        atc.addActionListener(knappeLytter);
        JComponent kompATC = Komponent.labelFieldRow("ATC-nr", atc);
        medisinRediger.add(kompATC);

        medisinSpareKnapp = new JButton("Spare");
        medisinSpareKnapp.addActionListener(knappeLytter);
        JPanel medisinSpareKnappPanel = new JPanel(new BorderLayout());
        medisinSpareKnappPanel.add(medisinSpareKnapp, BorderLayout.LINE_END);
        medisinRediger.add(medisinSpareKnappPanel);

        JPanel medisinInnFelt = new JPanel(new FlowLayout());
        medisinInnFelt.add(medisinRediger);

        medisinTextArea = new JTextArea(26, 28);
        JScrollPane scrollPane = new JScrollPane(medisinTextArea);
        medisinTextArea.setEditable(false);

        JPanel medisinTextfeltPanel = new JPanel(new FlowLayout());
        medisinTextfeltPanel.add(medisinTextArea);

        JPanel toPanel = new JPanel(new BorderLayout());
        toPanel.add(medisinInnFelt, BorderLayout.LINE_START);
        toPanel.add(medisinTextfeltPanel, BorderLayout.LINE_END);

        JPanel medisinRedigerTilFane = new JPanel(new FlowLayout());
        medisinRedigerTilFane.add(toPanel);

        return medisinRedigerTilFane;
    }

    //Leger alla komponenter till medisin sin registrer fana.
    private JPanel registrerGUI() {
        JPanel medisinRegistrer = new JPanel(new GridLayout(0, 1, 5, 5));

        regMedisinNavn = new JTextField(TEKSTFELTLENGDE);
        JPanel kompNavn = Komponent.labelFieldRow("Navn", regMedisinNavn);
        medisinRegistrer.add(kompNavn);
        
        regKategoriVelger = new JComboBox<>(kategoriArray);
        regKategoriVelger.setSelectedIndex(-1);
        regKategoriVelger.setEditable(true);
        JPanel kategori = Komponent.labelComboBoxRow("Kategori", regKategoriVelger);
        medisinRegistrer.add(kategori);

        regGruppVelger = new JComboBox<>(abc);
        regGruppVelger.setSelectedIndex(-1);
        JPanel grupp = Komponent.labelComboBoxRow("Grupp", regGruppVelger);
        medisinRegistrer.add(grupp);
        
        regMedisinStyrke = new JTextField(TEKSTFELTLENGDE);
        JPanel kompStyrke = Komponent.labelFieldRow("Styrke", regMedisinStyrke);
        medisinRegistrer.add(kompStyrke);
        
        regMedisinForm = new JTextField(TEKSTFELTLENGDE);
        JPanel kompForm = Komponent.labelFieldRow("Legemiddelform", regMedisinForm);
        medisinRegistrer.add(kompForm);
        
        regMedisinPakning = new JTextField(TEKSTFELTLENGDE);
        JPanel kompPakning = Komponent.labelFieldRow("Pakning", regMedisinPakning);
        medisinRegistrer.add(kompPakning);
        
        regATC = new JTextField(TEKSTFELTLENGDE);
        regATC.addActionListener(knappeLytter);
        JPanel kompATC = Komponent.labelFieldRow("ATC-nr", regATC);
        medisinRegistrer.add(kompATC);

        regMedisinSpareKnapp = new JButton("Registrer");
        regMedisinSpareKnapp.addActionListener(knappeLytter);
        JPanel medisinSpareKnappPanel = new JPanel(new BorderLayout());
        medisinSpareKnappPanel.add(regMedisinSpareKnapp, BorderLayout.LINE_END);
        medisinRegistrer.add(medisinSpareKnappPanel);

        return medisinRegistrer;
    }

    public void finnMedisin() {
        String navn = medisinNavn.getText();
        String styrke = medisinStyrke.getText();
        String form = medisinForm.getText();
        String pakning = medisinPakning.getText();
        
        Medisin medisin = parentFrame.finnMedisin(navn, styrke, form, pakning);
        if (medisin == null) {
            Komponent.popup(parentFrame, "Finner ikke medisinen");
        } else {
            int j = -1;
            for (int i = 0; i < abc.length; i++) {
                if (abc[i].equals(medisin.getGrupp())) {
                    j = i;
                }
            }
            int k = -1;
            for (int i = 0; i < kategoriArray.length; i++) {
                if (kategoriArray[i].equals(medisin.getKategori())) {
                    k = i;
                }
            }
            gruppVelger.setSelectedIndex(j);
            kategoriVelger.setSelectedIndex(k);
            medisinNavn.setText(medisin.getNavn());
            atc.setText(medisin.getATCNr());
        }
    }

    public void skrivListe() {
        medisinTextArea.setText(parentFrame.skrivMedisinListe());
    }

    public void updateMedisin() {
        String navn = medisinNavn.getText();
        String styrke = medisinStyrke.getText();
        String form = medisinForm.getText();
        String pakning = medisinPakning.getText();
        
        Medisin medisin = parentFrame.finnMedisin(navn, styrke, form, pakning);
        if (medisin == null) {
            Komponent.popup(parentFrame, "Finner ikke medisinen");
        } else {
            String grupp = (String) gruppVelger.getSelectedItem();
            String kategori = (String) kategoriVelger.getSelectedItem();
            String atcNr = atc.getText();

            medisin.setGrupp(grupp);
            medisin.setKategori(kategori);
            medisin.setNavn(navn);
            medisin.setATCNr(atcNr);

            parentFrame.skrivTilFil();
            String melding = "Vellykket oppdatering";
            Komponent.popup(parentFrame, melding);
            gruppVelger.setSelectedIndex(-1);
            kategoriVelger.setSelectedIndex(-1);
            medisinNavn.setText("");
            medisinStyrke.setText("");
            medisinForm.setText("");
            medisinPakning.setText("");
            atc.setText("");
        }
    }
    // Metoden registrerar en pasient om alla felt är ifyllda
    private void registrerMedisin() {
        String navn = regMedisinNavn.getText();
        String kategori = (String) regKategoriVelger.getSelectedItem();
        String grupp = (String) regGruppVelger.getSelectedItem();
        String styrke = regMedisinStyrke.getText();
        String form = regMedisinForm.getText();
        String pakning = regMedisinPakning.getText();
        String atcNr = regATC.getText();
        if (((grupp.equals("") || kategori.equals("")) || navn.equals("")) || atcNr.equals("")) {
            String melding = "Alle felt må vare utfyllt!";
            Komponent.popup(parentFrame, melding);
        } else {
            boolean registrert = parentFrame.registrerMedisin(navn, kategori, grupp, styrke, form, pakning, atcNr);
            
            if (registrert) {
                parentFrame.skrivTilFil();
                String melding = "Medisin er registrert!";
                Komponent.popup(parentFrame, melding);
                regGruppVelger.setSelectedIndex(-1);
                regKategoriVelger.setSelectedIndex(-1);
                regMedisinNavn.setText("");
                medisinStyrke.setText("");
                medisinForm.setText("");
                medisinPakning.setText("");
                regATC.setText("");
            } else {
                String melding = "Du ble ikke registrert!";
                Komponent.popup(parentFrame, melding);
            }
        }
    }

    private class KnappeLytterMedisin implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == finnMedisinKnapp || e.getSource() == medisinNavn) {
                finnMedisin();
            } else if (e.getSource() == seMedisinListeKnapp) {
                skrivListe();
            } else if (e.getSource() == medisinSpareKnapp || e.getSource() == atc) {
                updateMedisin();
            } else if (e.getSource() == regMedisinSpareKnapp || e.getSource() == regATC) {
                registrerMedisin();
            }
        }

    }
}
