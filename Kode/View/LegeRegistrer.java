/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import View.util.*;
import Model.Lege;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * *Dette er GUI for registering av lege. Klassen arver JPanel. Den er en del av
 * Legekontorvinduet(Brukes av LegekontorVindu). Laget av Hallbjørn Storruste
 * s165519 Siste versjon 28-04-2014
 *
 * @author Hallbjørn
 */
public class LegeRegistrer extends JPanel {

    private LegeRegSuper parentFrame;
    private JTextField fornavnFelt, etternavnFelt, epostFelt, epostigjenFelt,
            gtadrFelt, postnrFelt, poststedFelt, arbstedFelt;
    private JPasswordField passordFelt, passordigjenFelt;
    private JLabel error;
    private JButton registrerKnapp;
    private String[] labeltekst = {"Fornavn", "Etternavn",
        "E-post", "E-post igjen", "Passord", "Passord igjen", "Gateadresse",
        "Postnummer", "Poststed", "Arbeidssted"};
    private final int TEKSTFELTLENGDE = 20;

    private KnappeLytter knappeLytter;
    private FeltLytter feltLytter;

    public LegeRegistrer(LegeRegSuper p) {
        super(new GridLayout(0, 1, 5, 5));

        parentFrame = p;
        knappeLytter = new KnappeLytter();
        feltLytter = new FeltLytter();

        fornavnFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel fornavnPanel = (JPanel) Komponent.labelFieldRow(labeltekst[0], fornavnFelt);

        etternavnFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel etternavnPanel = (JPanel) Komponent.labelFieldRow(labeltekst[1], etternavnFelt);

        epostFelt = new JTextField(TEKSTFELTLENGDE);
        epostFelt.addFocusListener(feltLytter);
        JPanel epostPanel = (JPanel) Komponent.labelFieldRow(labeltekst[2], epostFelt);

        epostigjenFelt = new JTextField(TEKSTFELTLENGDE);
        epostigjenFelt.addFocusListener(feltLytter);
        JPanel epostigjenPanel = (JPanel) Komponent.labelFieldRow(labeltekst[3], epostigjenFelt);

        passordFelt = new JPasswordField(TEKSTFELTLENGDE);
        JPanel passordPanel = (JPanel) Komponent.labelFieldRow(labeltekst[4], passordFelt);

        passordigjenFelt = new JPasswordField(TEKSTFELTLENGDE);
        JPanel passordigjenPanel = (JPanel) Komponent.labelFieldRow(labeltekst[5], passordigjenFelt);

        gtadrFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel gtadrPanel = (JPanel) Komponent.labelFieldRow(labeltekst[6], gtadrFelt);

        postnrFelt = new JTextField(TEKSTFELTLENGDE);
        postnrFelt.addFocusListener(feltLytter);
        JPanel postnrPanel = (JPanel) Komponent.labelFieldRow(labeltekst[7], postnrFelt);

        poststedFelt = new JTextField(TEKSTFELTLENGDE);
        poststedFelt.setEditable(false);
        JPanel poststedPanel = (JPanel) Komponent.labelFieldRow(labeltekst[8], poststedFelt);

        arbstedFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel arbstedPanel = (JPanel) Komponent.labelFieldRow(labeltekst[9], arbstedFelt);

        error = new JLabel("");
        error.setForeground(Komponent.feilTekst);
        
        registrerKnapp = new JButton("Registrer");
        registrerKnapp.addActionListener(knappeLytter);

        JPanel registrerKnappPanel = new JPanel(new BorderLayout());
        registrerKnappPanel.add(error, BorderLayout.LINE_START);
        registrerKnappPanel.add(registrerKnapp, BorderLayout.LINE_END);

        add(fornavnPanel);
        add(etternavnPanel);
        add(epostPanel);
        add(epostigjenPanel);
        add(passordPanel);
        add(passordigjenPanel);
        add(gtadrPanel);
        add(postnrPanel);
        add(poststedPanel);
        add(arbstedPanel);
        add(registrerKnappPanel);

    }

    /*Registerer en lege i registeret. Gir beskjed via et dialogvindu
     om hvordan registeringen går og eventuelle brukerfeil.*/
    private void registrer() {
        String fornavn = fornavnFelt.getText();
        String etternavn = etternavnFelt.getText();
        String epost = epostFelt.getText();
        String epostigjen = epostigjenFelt.getText();
        char[] passord = passordFelt.getPassword();
        char[] passordigjen = passordigjenFelt.getPassword();
        String gateadresse = gtadrFelt.getText();
        String postnr = postnrFelt.getText();
        String poststed = poststedFelt.getText();
        String arbeidssted = arbstedFelt.getText();
        
        if(!Komponent.riktigEpost(epost) || !Komponent.riktigEpost(epostigjen)){
            String melding = "Epost er ikke på formen noen@eksempel.no!";
            Komponent.popup(parentFrame, melding);
        } else if(!Postnummer.riktigPostNr(postnr)){
            String melding = "Postnummer må inneholde fire siffer (0-9)!";
            Komponent.popup(parentFrame, melding);
            poststedFelt.setText("");
        } else if(Poststed.finnPoststed(postnr) == null){
            String melding = "Postnummeret eksisterer ikke!";
             Komponent.popup(parentFrame, melding);
             poststedFelt.setText("");
        } else if (!epost.equals(epostigjen)) {
            String melding = "Epostene er ikke like!";
            Komponent.popup(parentFrame, melding);
        } else if (!Arrays.equals(passord, passordigjen)) {
            String melding = "Passordene er ikke identiske";
            Komponent.popup(parentFrame, melding);
        } else {
            boolean registrert = parentFrame.registrerLege(fornavn, etternavn,
                    epost, gateadresse, postnr, poststed, arbeidssted, passord);
            if (registrert) {
                parentFrame.skrivTilFil();
                String melding = "Du er registrert!";
                Komponent.popup(parentFrame, melding);
                fornavnFelt.setText("");
                etternavnFelt.setText("");
                epostFelt.setText("");
                epostigjenFelt.setText("");
                passordFelt.setText("");
                passordigjenFelt.setText("");
                gtadrFelt.setText("");
                postnrFelt.setText("");
                poststedFelt.setText("");
                arbstedFelt.setText("");
                if (parentFrame instanceof LegekontorVindu) {
                    LegekontorVindu vindu = (LegekontorVindu) parentFrame;
                    Lege innlogget = vindu.login(epost, passord);

                    if (innlogget == null) {
                        melding = "Feil epost eller passord!";
                        Komponent.popup(parentFrame, melding);
                    } else {
                        vindu.tegnFinnPasientGUI(innlogget);
                    }
                }
            } else {
                String melding = "Du ble ikke registrert!";
                Komponent.popup(parentFrame, melding);
            }
        }
    }

    private class KnappeLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registrerKnapp) {
                registrer();
            }
        }
    }
    
    private class FeltLytter extends FocusAdapter {

        @Override
        public void focusLost(FocusEvent e) {
            if(e.getSource() == epostFelt || e.getSource() == epostigjenFelt)
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
            
            else if(e.getSource() == postnrFelt)
            {
                String postNr = postnrFelt.getText();
                if(!Postnummer.riktigPostNr(postNr)){
                    postnrFelt.setForeground(Komponent.feilTekst);
                    error.setText("Vennligst skriv postnummer med fire siffer (0-9).");
                    poststedFelt.setText("");
                }
                else if(Poststed.finnPoststed(postNr) == null){
                    postnrFelt.setForeground(Komponent.feilTekst);
                    error.setText("Postnummeret eksisterer ikke!");
                    poststedFelt.setText("");
                }
                else{
                    postnrFelt.setForeground(Komponent.rettTekst);
                    error.setText("");
                    poststedFelt.setText( Poststed.finnPoststed(postNr));
                }
            }
            
        }
    }

}
