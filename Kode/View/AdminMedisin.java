package View;

import Controller.Legeregister;
import java.awt.*;
import javax.swing.*;

public class AdminMedisin extends JTabbedPane {
     
    private AdminVindu parentFrame;
    private JTextField finnMedisin, medisinNavn, atc, regMedisinNavn, regATC;
    private JButton finnMedisinKnapp, seMedisinListeKnapp, medisinSpareKnapp, regMedisinSpareKnapp;
    private JTextArea medisinTextArea;
    private JComboBox<String> gruppVelger, kategoriVelger, regGruppVelger, regKategoriVelger;
    
    private final int TEKSTFELTLENGDE = 10;
    
    public AdminMedisin(AdminVindu a) {
        
        super();
        parentFrame = a;
        
        JPanel rediger = new JPanel(new FlowLayout());
        rediger.add(redigerGUI());
        
        JPanel registrer = new JPanel(new FlowLayout());
        registrer.add(registrerGUI());
       
        addTab("Rediger", rediger);
        addTab("Registrer", registrer);
    }
    
     private JPanel redigerGUI() {
        JPanel medisinRediger = new JPanel(new GridLayout(0, 1, 5, 5));

        finnMedisin = new JTextField(TEKSTFELTLENGDE);
        JComponent kompFinnMedisin = Komponent.labelFieldRow("Navn", finnMedisin);
        medisinRediger.add(kompFinnMedisin);

        finnMedisinKnapp = new JButton("Finn");
        finnMedisinKnapp.setPreferredSize(new Dimension(135, 20));
        //finnMedisinKnapp.addActionListener( actionLytter );
        JPanel finnMedisinKnappPanel = new JPanel(new BorderLayout());
        finnMedisinKnappPanel.add(finnMedisinKnapp, BorderLayout.LINE_END);
        medisinRediger.add(finnMedisinKnappPanel);

        seMedisinListeKnapp = new JButton("Se hele listen");
        seMedisinListeKnapp.setPreferredSize(new Dimension(135, 20));
        //seMedisinListeKnapp.addActionListener( actionLytter );
        JPanel seMedisinListeKnappPanel = new JPanel(new BorderLayout());
        seMedisinListeKnappPanel.add(seMedisinListeKnapp, BorderLayout.LINE_END);
        medisinRediger.add(seMedisinListeKnappPanel);

        String[] abc = {"A", "B", "C"};
        gruppVelger = new JComboBox<>(abc);
        gruppVelger.setSelectedIndex(0);
        //gruppVelger.addActionListener(actionLytter);
        JComponent grupp = Komponent.labelComboBoxRow("Grupp", gruppVelger);
        medisinRediger.add(grupp);

        String[] kategoriArray = {"Sövnmedel", "Antidepresiva", "Narkotikaklassade"};//Har bara lagt in något
        kategoriVelger = new JComboBox<>(kategoriArray);
        //gruppVelger.addActionListener(actionLytter);
        JComponent kategori = Komponent.labelComboBoxRow("Kategori", kategoriVelger);
        medisinRediger.add(kategori);

        medisinNavn = new JTextField(TEKSTFELTLENGDE);
        JComponent kompNavn = Komponent.labelFieldRow("Navn", medisinNavn);
        medisinRediger.add(kompNavn);

        atc = new JTextField(TEKSTFELTLENGDE);
        JComponent kompATC = Komponent.labelFieldRow("ATC-nr", atc);
        medisinRediger.add(kompATC);

        medisinSpareKnapp = new JButton("Spare");
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

    private JPanel registrerGUI() {
        JPanel medisinRegistrer = new JPanel(new GridLayout(0, 1, 5, 5));
        
        String[] abc = {"A", "B", "C"};
        regGruppVelger = new JComboBox<>(abc);
        regGruppVelger.setSelectedIndex(0);
        //gruppVelger.addActionListener(actionLytter);
        JPanel grupp = Komponent.labelComboBoxRow("Grupp", regGruppVelger);
        medisinRegistrer.add(grupp);

        String[] kategoriArray = {"Sövnmedel", "Antidepresiva", "Narkotikaklassade"};//Har bara lagt in något
        regKategoriVelger = new JComboBox<>(kategoriArray);
        //gruppVelger.addActionListener(actionLytter);
        JPanel kategori = Komponent.labelComboBoxRow("Kategori", regKategoriVelger);
        medisinRegistrer.add(kategori);

        regMedisinNavn = new JTextField(TEKSTFELTLENGDE);
        JPanel kompNavn = Komponent.labelFieldRow("Navn", medisinNavn);
        medisinRegistrer.add(kompNavn);

        regATC = new JTextField(TEKSTFELTLENGDE);
        JPanel kompATC = Komponent.labelFieldRow("ATC-nr", atc);
        medisinRegistrer.add(kompATC);

        regMedisinSpareKnapp = new JButton("Spare");
        JPanel medisinSpareKnappPanel = new JPanel(new BorderLayout());
        medisinSpareKnappPanel.add(regMedisinSpareKnapp, BorderLayout.LINE_END);
        medisinRegistrer.add(medisinSpareKnappPanel);
        
        return medisinRegistrer;
    }
}
