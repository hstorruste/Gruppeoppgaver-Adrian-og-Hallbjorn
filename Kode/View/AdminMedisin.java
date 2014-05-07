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
    private JTextField medisinNavn, medisinStyrke, medisinForm, medisinPakning, atc, 
            regMedisinNavn, regMedisinStyrke, regMedisinForm, regMedisinPakning, regATC;
    private JButton finnMedisinKnapp, seMedisinListeKnapp, medisinSpareKnapp, regMedisinSpareKnapp;
    private JTextArea medisinTextArea;
    private JComboBox<String> finnNavn, finnStyrke, finnForm, finnPakning, 
            gruppVelger, kategoriVelger, regGruppVelger, regKategoriVelger;
    private String[] abc = {"A", "B", "C"};
    private String[] kategoriArray;
    private Medisin funnet;
    private final int TEKSTFELTLENGDE = 10;

    private KnappeLytterMedisin lytter;

    public AdminMedisin(AdminVindu a) {

        super();
        parentFrame = a;
        kategoriArray = parentFrame.skrivKatArray();
        funnet = null;
        lytter = new KnappeLytterMedisin();
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
        
        String[] medisiner = parentFrame.getAlleMedisinnavn();
        finnNavn = new JComboBox<>(medisiner);
        finnNavn.setSelectedIndex(-1);
        finnNavn.setEditable(false);
        finnNavn.addActionListener(lytter);
        JPanel navnRediger = (JPanel) Komponent.labelComboBoxRow("Medisin", finnNavn);
        medisinRediger.add(navnRediger);
        
        finnStyrke = new JComboBox<>();
        finnStyrke.setSelectedIndex(-1);
        finnStyrke.setEditable(false);
        finnStyrke.addActionListener(lytter);
        JPanel styrkeRediger = Komponent.labelComboBoxRow("Styrke", finnStyrke);
        medisinRediger.add(styrkeRediger);
        
        finnForm = new JComboBox<>();
        finnForm.setSelectedIndex(-1);
        finnForm.setEditable(false);
        finnForm.addActionListener(lytter);
        JPanel formRediger = Komponent.labelComboBoxRow("Form", finnForm);
        medisinRediger.add(formRediger);
        
        finnPakning = new JComboBox<>();
        finnPakning.setSelectedIndex(-1);
        finnPakning.setEditable(false);
        finnPakning.addActionListener(lytter);
        JPanel pakningRediger = Komponent.labelComboBoxRow("Pakning", finnPakning);
        medisinRediger.add(pakningRediger);
        
        finnMedisinKnapp = new JButton("Finn");
        finnMedisinKnapp.setPreferredSize(new Dimension(152, 20));
        finnMedisinKnapp.addActionListener(lytter);
        JPanel finnMedisinKnappPanel = new JPanel(new BorderLayout());
        finnMedisinKnappPanel.add(finnMedisinKnapp, BorderLayout.LINE_END);
        medisinRediger.add(finnMedisinKnappPanel);

        seMedisinListeKnapp = new JButton("Se hele listen");
        seMedisinListeKnapp.setPreferredSize(new Dimension(152, 20));
        seMedisinListeKnapp.addActionListener(lytter);
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
        JComponent grupp = Komponent.labelComboBoxRow("Gruppe", gruppVelger);
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
        atc.addActionListener(lytter);
        JComponent kompATC = Komponent.labelFieldRow("ATC-nr", atc);
        medisinRediger.add(kompATC);

        medisinSpareKnapp = new JButton("Lagre");
        medisinSpareKnapp.addActionListener(lytter);
        JPanel medisinSpareKnappPanel = new JPanel(new BorderLayout());
        medisinSpareKnappPanel.add(medisinSpareKnapp, BorderLayout.LINE_END);
        medisinRediger.add(medisinSpareKnappPanel);

        JPanel medisinInnFelt = new JPanel(new FlowLayout());
        medisinInnFelt.add(medisinRediger);

        medisinTextArea = new JTextArea(26, 28);
        JScrollPane scrollPane = new JScrollPane(medisinTextArea);
        medisinTextArea.setEditable(false);

        JPanel medisinTextfeltPanel = new JPanel(new FlowLayout());
        medisinTextfeltPanel.add(scrollPane);

        JPanel toPanel = new JPanel(new BorderLayout());
        toPanel.add(medisinInnFelt, BorderLayout.LINE_START);
        toPanel.add(medisinTextfeltPanel, BorderLayout.LINE_END);

        JPanel medisinRedigerTilFane = new JPanel(new FlowLayout());
        medisinRedigerTilFane.add(toPanel);
        
        JComponent[] felt = new JComponent[]{gruppVelger, kategoriVelger, 
            medisinNavn, medisinStyrke, medisinForm, medisinPakning, atc};
        
        for(JComponent child: felt)
        {
            child.setVisible(false);
            JLabel merkelapp = Komponent.finnLabelTilFelt(medisinRedigerTilFane, child);
            if(merkelapp != null)
                merkelapp.setVisible(false);
        }
        
        medisinSpareKnapp.setVisible(false);
            
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
        JPanel grupp = Komponent.labelComboBoxRow("Gruppe", regGruppVelger);
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
        regATC.addActionListener(lytter);
        JPanel kompATC = Komponent.labelFieldRow("ATC-nr", regATC);
        medisinRegistrer.add(kompATC);

        regMedisinSpareKnapp = new JButton("Registrer");
        regMedisinSpareKnapp.addActionListener(lytter);
        JPanel medisinSpareKnappPanel = new JPanel(new BorderLayout());
        medisinSpareKnappPanel.add(regMedisinSpareKnapp, BorderLayout.LINE_END);
        medisinRegistrer.add(medisinSpareKnappPanel);

        return medisinRegistrer;
    }

    public void finnMedisin() {
        String navn = (String)finnNavn.getSelectedItem();
        String styrke = (String)finnStyrke.getSelectedItem();
        String form = (String)finnForm.getSelectedItem();
        String pakning = (String)finnPakning.getSelectedItem();
        
        funnet = parentFrame.finnMedisin(navn, styrke, form, pakning);
        if (funnet == null) {
            Komponent.popup(parentFrame, "Finner ikke medisinen");
        } else {
            JComponent[] felt = new JComponent[]{gruppVelger, kategoriVelger, 
            medisinNavn, medisinStyrke, medisinForm, medisinPakning, atc};
        
            for(JComponent child: felt)
            {
                child.setVisible(true);
                JLabel merkelapp = Komponent.finnLabelTilFelt(this, child);
                if(merkelapp != null)
                    merkelapp.setVisible(true);
            }

            medisinSpareKnapp.setVisible(true);
            int j = -1;
            for (int i = 0; i < abc.length; i++) {
                if (abc[i].equals(funnet.getGrupp())) {
                    j = i;
                }
            }
            int k = -1;
            for (int i = 0; i < kategoriArray.length; i++) {
                if (kategoriArray[i].equals(funnet.getKategori())) {
                    k = i;
                }
            }
            gruppVelger.setSelectedIndex(j);
            kategoriVelger.setSelectedIndex(k);
            medisinNavn.setText(funnet.getNavn());
            medisinStyrke.setText(styrke);
            medisinForm.setText(form);
            medisinPakning.setText(pakning);
            atc.setText(funnet.getATCNr());
        }
    }

    public void skrivListe() {
        medisinTextArea.setText(parentFrame.skrivMedisinListe());
    }

    public void updateMedisin() {
        
        if (funnet == null) {
            Komponent.popup(parentFrame, "Ingen medisin funnet");
        } else {
            String grupp = (String) gruppVelger.getSelectedItem();
            String kategori = (String) kategoriVelger.getSelectedItem();
            String atcNr = atc.getText();
            String navn = medisinNavn.getText();
            String styrke = medisinStyrke.getText();
            String form = medisinForm.getText();
            String pakning = medisinPakning.getText();
            
            funnet.setGrupp(grupp);
            funnet.setKategori(kategori);
            funnet.setNavn(navn);
            funnet.setStyrke(styrke);
            funnet.setForm(form);
            funnet.setPakning(pakning);
            funnet.setATCNr(atcNr);

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
            
            JComponent[] felt = new JComponent[]{gruppVelger, kategoriVelger, 
            medisinNavn, medisinStyrke, medisinForm, medisinPakning, atc};
        
            for(JComponent child: felt)
            {
                child.setVisible(false);
                JLabel merkelapp = Komponent.finnLabelTilFelt(this, child);
                if(merkelapp != null)
                    merkelapp.setVisible(false);
            }

            medisinSpareKnapp.setVisible(false);
            oppdaterMedisiner();
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
                oppdaterMedisiner();
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
    /*Oppdaterer innhold i ComboBox for pakning og gjør dem synlig/usynlig.*/
    private void oppdaterPakningComboBox()
    {
        if(finnForm.getSelectedIndex() > -1)
        {
            finnPakning.setVisible(true);
            JLabel merkelapp = Komponent.finnLabelTilFelt(this, finnPakning);
            merkelapp.setVisible(true);
            finnPakning.removeAllItems();
            String navn = (String)finnNavn.getSelectedItem();
            String styrke = (String)finnStyrke.getSelectedItem();
            String form = (String)finnForm.getSelectedItem();
            String[] pakning = parentFrame.getAlleMedisinPakninger(navn, styrke, form);
            for(int i = 0; i < pakning.length; i++)
                finnPakning.insertItemAt(pakning[i], i);
        }
        else
        {
            finnPakning.setVisible(false);
            JLabel merkelapp = Komponent.finnLabelTilFelt(this, finnPakning);
            merkelapp.setVisible(false);
            /*gruppeFelt.setText("");
            kategoriFelt.setText("");
            atcFelt.setText("");*/
        }
    }
    
    /*Oppdaterer innhold i ComboBox for form og gjør dem synlig/usynlig.*/
    private void oppdaterFormComboBox()
    {
        if(finnStyrke.getSelectedIndex() > -1)
        {
            finnForm.setVisible(true);
            JLabel merkelapp = Komponent.finnLabelTilFelt(this, finnForm);
            merkelapp.setVisible(true);
            finnForm.removeAllItems();
            String navn = (String)finnNavn.getSelectedItem();
            String styrke = (String)finnStyrke.getSelectedItem();
            String[] form = parentFrame.getAlleMedisinFormer(navn, styrke);
            for(int i = 0; i < form.length; i++)
                finnForm.insertItemAt(form[i], i);
        }
        else
        {
            JComponent[] felt = new JComponent[]{finnForm, finnPakning};
        
            for(JComponent child: felt)
            {
                child.setVisible(false);
                JLabel merkelapp = Komponent.finnLabelTilFelt(this, child);
                if(merkelapp != null)
                    merkelapp.setVisible(false);
            }
            /*gruppeFelt.setText("");
            kategoriFelt.setText("");
            atcFelt.setText("");*/

        }
    }
    /*Oppdaterer innhold i ComboBox for styrke og gjør dem synlig/usynlig.*/
    private void oppdaterStyrkeComboBox()
    {
        if(finnNavn.getSelectedIndex() > -1)
        {
            finnStyrke.setVisible(true);
            JLabel merkelapp = Komponent.finnLabelTilFelt(this, finnStyrke);
            merkelapp.setVisible(true);
            finnStyrke.removeAllItems();
            String navn = (String)finnNavn.getSelectedItem();
            String[] styrke = parentFrame.getAlleMedisinStyrker(navn);
            for(int i = 0; i < styrke.length; i++)
                finnStyrke.insertItemAt(styrke[i], i);
            
        }
        else
        {
            JComponent[] felt = new JComponent[]{finnStyrke, finnForm, finnPakning};
        
            for(JComponent child: felt)
            {
                child.setVisible(false);
                JLabel merkelapp = Komponent.finnLabelTilFelt(this, child);
                if(merkelapp != null)
                    merkelapp.setVisible(false);
            }
            /*gruppeFelt.setText("");
            kategoriFelt.setText("");
            atcFelt.setText("");*/
        }
    }
    //Oppdaterer medisinlisten
    private void oppdaterMedisiner()
    {
        String[] medisiner = parentFrame.getAlleMedisinnavn();
        finnNavn.removeAllItems();
        for(int i = 0; i < medisiner.length; i++)
            finnNavn.insertItemAt(medisiner[i], i);
        
        kategoriArray = parentFrame.skrivKatArray();
        kategoriVelger.removeAllItems();
        regKategoriVelger.removeAllItems();
        for(int i = 0; i < kategoriArray.length; i++)
        {
            kategoriVelger.insertItemAt(kategoriArray[i], i);
            regKategoriVelger.insertItemAt(kategoriArray[i], i);
        }
            }
    private class KnappeLytterMedisin implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == finnMedisinKnapp) {
                finnMedisin();               
            } else if(e.getSource() == finnNavn)
                oppdaterStyrkeComboBox();
            else if(e.getSource() == finnStyrke)
                oppdaterFormComboBox();
            else if(e.getSource() == finnForm)
                oppdaterPakningComboBox();
            else if (e.getSource() == seMedisinListeKnapp) {
                skrivListe();
            } else if (e.getSource() == medisinSpareKnapp || e.getSource() == atc) {
                updateMedisin();
            } else if (e.getSource() == regMedisinSpareKnapp || e.getSource() == regATC) {
                registrerMedisin();
            }
        }

    }
}
