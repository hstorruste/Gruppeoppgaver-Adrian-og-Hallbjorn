package View;

import Controller.Legeregister;
import Model.Lege;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

/*Denne klassen upprättar alla komponenter till lege fanan.
 Laget av Adrian Westlund s198571.
 Siste versjon 05-05-2014*/
public class AdminLege extends JTabbedPane {

    private AdminVindu parentFrame;
    private JTextField finnLegeFelt, fornavnFelt, etternavnFelt, ePostFelt, ePostIgenFelt,
            gateadresseFelt, postNrFelt, poststedFelt, arbetsstedFelt;
    private JPasswordField passordFelt, passordIgenFelt;
    private JButton finnLegeKnapp, seLegeListeKnapp, redigerKnapp;
    private JLabel error;
    private JTextArea legeTextArea;
    private String[] labeltekst = {"E-post", "Fornavn", "Etternavn", "E-post", "E-post igjen",
        "Passord", "Passord igjen", "Gateadresse", "Postnummer",
        "Poststed", "Arbeidssted"};
    private JCheckBox a, b, c;

    private final int TEKSTFELTLENGDE = 10;

    private Lege lege;
    private KnappeLytter knappeLytter;
    private FeltLytter feltLytter;

    public AdminLege(AdminVindu a) {

        super();
        parentFrame = a;
        knappeLytter = new KnappeLytter();
        feltLytter = new FeltLytter();

        JPanel rediger = new JPanel(new FlowLayout());
        rediger.add(redigerGUI());

        JPanel registrer = new JPanel(new FlowLayout());
        LegeRegistrer registrerGUI = new LegeRegistrer(parentFrame);
        registrer.add(registrerGUI);

        addTab("Rediger", rediger);
        addTab("Registrer", registrer);
    }

    //Oppretter alt i fanen for rediger.
    private JPanel redigerGUI() {
        JPanel rediger = new JPanel(new GridLayout(0, 1, 5, 5));

        finnLegeFelt = new JTextField(TEKSTFELTLENGDE);
        finnLegeFelt.addActionListener(knappeLytter);
        JPanel kompFinnLege = Komponent.labelFieldRow(labeltekst[0], finnLegeFelt);
        rediger.add(kompFinnLege);

        finnLegeKnapp = new JButton("Finn");
        finnLegeKnapp.setPreferredSize(new Dimension(135, 20));
        finnLegeKnapp.addActionListener(knappeLytter);
        JPanel finnLegeKnappPanel = new JPanel(new BorderLayout());
        finnLegeKnappPanel.add(finnLegeKnapp, BorderLayout.LINE_END);
        rediger.add(finnLegeKnappPanel);

        seLegeListeKnapp = new JButton("Se hele listen");
        seLegeListeKnapp.setPreferredSize(new Dimension(135, 20));
        seLegeListeKnapp.addActionListener(knappeLytter);
        JPanel seLegeListeKnappPanel = new JPanel(new BorderLayout());
        seLegeListeKnappPanel.add(seLegeListeKnapp, BorderLayout.LINE_END);
        rediger.add(seLegeListeKnappPanel);

        fornavnFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel kompFornavn = Komponent.labelFieldRow(labeltekst[1], fornavnFelt);
        rediger.add(kompFornavn);

        etternavnFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel kompEtternavn = Komponent.labelFieldRow(labeltekst[2], etternavnFelt);
        rediger.add(kompEtternavn);

        ePostFelt = new JTextField(TEKSTFELTLENGDE);
        ePostFelt.addFocusListener(feltLytter);
        JPanel kompEPost = Komponent.labelFieldRow(labeltekst[3], ePostFelt);
        rediger.add(kompEPost);

        ePostIgenFelt = new JTextField(TEKSTFELTLENGDE);
        ePostIgenFelt.addFocusListener(feltLytter);
        JPanel kompEPostIgen = Komponent.labelFieldRow(labeltekst[4], ePostIgenFelt);
        rediger.add(kompEPostIgen);

        passordFelt = new JPasswordField(TEKSTFELTLENGDE);
        JPanel kompPassord = Komponent.labelFieldRow(labeltekst[5], passordFelt);
        rediger.add(kompPassord);

        passordIgenFelt = new JPasswordField(TEKSTFELTLENGDE);
        JPanel kompPassordIgen = (JPanel) Komponent.labelFieldRow(labeltekst[6], passordIgenFelt);
        rediger.add(kompPassordIgen);

        gateadresseFelt = new JTextField(TEKSTFELTLENGDE);
        JComponent kompGate = Komponent.labelFieldRow(labeltekst[7], gateadresseFelt);
        rediger.add(kompGate);

        postNrFelt = new JTextField(TEKSTFELTLENGDE);
        postNrFelt.addFocusListener(feltLytter);
        JComponent kompPostNr = Komponent.labelFieldRow(labeltekst[8], postNrFelt);
        rediger.add(kompPostNr);

        poststedFelt = new JTextField(TEKSTFELTLENGDE);
        JComponent kompPoststed = Komponent.labelFieldRow(labeltekst[9], poststedFelt);
        rediger.add(kompPoststed);

        arbetsstedFelt = new JTextField(TEKSTFELTLENGDE);
        JComponent kompArbetssted = Komponent.labelFieldRow(labeltekst[10], arbetsstedFelt);
        rediger.add(kompArbetssted);

        JPanel checkbox = new JPanel(new GridLayout(1, 0));
        a = new JCheckBox("A");
        b = new JCheckBox("B");
        c = new JCheckBox("C");
        checkbox.add(a);
        checkbox.add(b);
        checkbox.add(c);
        rediger.add(checkbox);
        
        redigerKnapp = new JButton("Lagre");
        redigerKnapp.addActionListener(knappeLytter);

        JPanel redigerKnappPanel = new JPanel(new BorderLayout());
       
        redigerKnappPanel.add(redigerKnapp, BorderLayout.LINE_END);
        rediger.add(redigerKnappPanel);
        
        skjulFelt(rediger);

        JPanel legeInnFelt = new JPanel(new FlowLayout());
        legeInnFelt.add(rediger);

        legeTextArea = new JTextArea(26, 28);
        JScrollPane scrollPane = new JScrollPane(legeTextArea);
        legeTextArea.setEditable(false);

        JPanel legeTextfeltPanel = new JPanel(new FlowLayout());
        legeTextfeltPanel.add(scrollPane);

        error = new JLabel("\t");
        error.setForeground(Komponent.feilTekst);   
        
        JPanel toPanel = new JPanel(new BorderLayout());
        toPanel.add(legeInnFelt, BorderLayout.LINE_START);
        toPanel.add(legeTextfeltPanel, BorderLayout.LINE_END);
        toPanel.add(error,BorderLayout.PAGE_END);

        JPanel legeRedigerTilFane = new JPanel(new FlowLayout());
        legeRedigerTilFane.add(toPanel);

        return legeRedigerTilFane;
    }

    //Metoden skal finner en lege på e-post
    private void finnLege() {
        lege = parentFrame.finnLege(finnLegeFelt.getText());
        if (lege == null) {
            Komponent.popup(parentFrame, "Finner ikke legen");
        } else {
            fornavnFelt.setText(lege.getFornavn());
            etternavnFelt.setText(lege.getEtternavn());
            ePostFelt.setText(lege.getEPost());
            ePostIgenFelt.setText(lege.getEPost());
            char[] pass = lege.getPassord();
            String passord = "";
            for (char i : pass) {
                passord += i;
            }
            passordFelt.setText(passord);
            passordIgenFelt.setText(passord);
            gateadresseFelt.setText(lege.getGateadresse());
            postNrFelt.setText(lege.getPostnummer());
            poststedFelt.setText(lege.getPoststed());
            arbetsstedFelt.setText(lege.getArbetssted());
            a.setSelected(lege.getA());
            b.setSelected(lege.getB());
            c.setSelected(lege.getC());
            
            JComponent[] felt = new JComponent[]{fornavnFelt, etternavnFelt, 
            ePostFelt, ePostIgenFelt, passordFelt, passordIgenFelt, gateadresseFelt,
            postNrFelt, poststedFelt, arbetsstedFelt};
        
        for(JComponent child: felt)
        {
            child.setVisible(true);
            JLabel merkelapp = Komponent.finnLabelTilFelt(this, child);
            if(merkelapp != null)
                merkelapp.setVisible(true);
        }
        
        redigerKnapp.setVisible(true);
        a.setVisible(true);
        b.setVisible(true);
        c.setVisible(true);
        }
    }
    /*Skjuler felt for editering av lege, tar imot en container som alle 
    komponentene ligger i.*/
    private void skjulFelt(Container cont)
    {
        JComponent[] felt = new JComponent[]{fornavnFelt, etternavnFelt, 
            ePostFelt, ePostIgenFelt, passordFelt, passordIgenFelt, gateadresseFelt,
            postNrFelt, poststedFelt, arbetsstedFelt};
        
        for(JComponent child: felt)
        {
            child.setVisible(false);
            JLabel merkelapp = Komponent.finnLabelTilFelt(cont, child);
            if(merkelapp != null)
                merkelapp.setVisible(false);
        }
        
        redigerKnapp.setVisible(false);
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
    }
    //Metoden skriver legeregister.toString()
    private void skrivListe() {
        legeTextArea.setText(parentFrame.skrivLegeListe());
    }

    //Metoden uppdaterar en lege.
    private void updateLege() {
        if (lege == null) {
            Komponent.popup(parentFrame, "Ingen lege er funnet");
        } else {
            String fornavn = fornavnFelt.getText();
            String etternavn = etternavnFelt.getText();
            String epost = ePostFelt.getText();
            String epostigjen = ePostIgenFelt.getText();
            char[] passord = passordFelt.getPassword();
            char[] passordigjen = passordIgenFelt.getPassword();
            String gateadresse = gateadresseFelt.getText();
            String postnr = postNrFelt.getText();
            String poststed = poststedFelt.getText();
            String arbeidssted = arbetsstedFelt.getText();
            
            if(!Komponent.riktigEpost(epost) || !Komponent.riktigEpost(epostigjen)){
                String melding = "Epost er ikke på formen noen@eksempel.no!";
                Komponent.popup(parentFrame, melding);
            } else if(!Komponent.riktigPostNr(postnr)){
                String melding = "Postnummer må inneholde fire siffer (0-9)!";
                Komponent.popup(parentFrame, melding);
            } else if (!epost.equals(epostigjen)) {
                String melding = "Epostene er ikke like!";
                Komponent.popup(parentFrame, melding);
            } else if (!Arrays.equals(passord, passordigjen)) {
                    String melding = "Passordene er ikke like!";
                    Komponent.popup(parentFrame, melding);
            } else {

                lege.setFornavn(fornavn);
                lege.setEtternavn(etternavn);
                lege.setEPost(epost);
                lege.setPassord(passord);
                lege.setGateadresse(gateadresse);
                lege.setPostnummer(postnr);
                lege.setPoststed(poststed);
                lege.setArbetssted(arbeidssted);
                if (a.isSelected()) {
                    lege.setA(true);
                } else {
                    lege.setA(false);
                }
                if (b.isSelected()) {
                    lege.setB(true);
                } else {
                    lege.setB(false);
                }
                if (c.isSelected()) {
                    lege.setC(true);
                } else {
                    lege.setC(false);
                }
                parentFrame.skrivTilFil();
                String melding = "Vellykket oppdatering";
                Komponent.popup(parentFrame, melding);
                fornavnFelt.setText("");
                etternavnFelt.setText("");
                ePostFelt.setText("");
                ePostIgenFelt.setText("");
                passordFelt.setText("");
                passordIgenFelt.setText("");
                gateadresseFelt.setText("");
                postNrFelt.setText("");
                poststedFelt.setText("");
                arbetsstedFelt.setText("");
                a.setSelected(false);
                b.setSelected(false);
                c.setSelected(false);
                lege = null;
                skjulFelt(this);

            }
        }
    }

    private class KnappeLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == finnLegeKnapp || e.getSource() == finnLegeFelt) {
                finnLege();
            } else if (e.getSource() == seLegeListeKnapp) {
                skrivListe();
            } else if (e.getSource() == redigerKnapp) {
                updateLege();
            }
        }
    }
    
    private class FeltLytter extends FocusAdapter {

        @Override
        public void focusLost(FocusEvent e) {
            if(e.getSource() == ePostFelt || e.getSource() == ePostIgenFelt)
            {
                JTextField felt = (JTextField)e.getSource();
                if(Komponent.riktigEpost(felt.getText())){
                    felt.setForeground(Komponent.rettTekst);
                    error.setText("");
                }
                else{
                     felt.setForeground(Komponent.feilTekst);
                     error.setText("Vennligst skriv epost på formen noen@eksempel.no.");
                }
            }
            
            else if(e.getSource() == postNrFelt)
            {
                if(Komponent.riktigPostNr(postNrFelt.getText())){
                    postNrFelt.setForeground(Komponent.rettTekst);
                    error.setText("");
                }
                else{
                    postNrFelt.setForeground(Komponent.feilTekst);
                    error.setText("Vennligst skriv postnummer med fire siffer (0-9).");       
                }
            }
            
        }
    }
}
