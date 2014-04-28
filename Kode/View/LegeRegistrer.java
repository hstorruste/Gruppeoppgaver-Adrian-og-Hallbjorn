/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Model.Lege;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/***Dette er GUI for registering av lege. Klassen arver JPanel.
 * Den er en del av 
 * Legekontorvinduet(Brukes av LegekontorVindu).
 * Laget av Hallbjørn Storruste s165519
 * Siste versjon 28-04-2014
 *
 * @author Hallbjørn
 */
public class LegeRegistrer extends JPanel{
    
    private LegeRegSuper parentFrame;
    private JTextField fornavnFelt, etternavnFelt, epostFelt, epostigjenFelt,
            gtadrFelt, postnrFelt, poststedFelt, arbstedFelt;
    private JPasswordField passordFelt, passordigjenFelt;
    private JButton registrerKnapp;
    private String[] labeltekst = {"Fornavn", "Etternavn",
            "E-post", "E-post igjen", "Passord", "Passord igjen", "Gateadresse",
            "Postnummer", "Poststed", "Arbeidssted"};
    private final int TEKSTFELTLENGDE = 20;
    
    private KnappeLytter knappeLytter;
    
    public LegeRegistrer(LegeRegSuper p)
    {
        super(new GridLayout(0, 1, 5, 5));
        
        parentFrame = p;
        knappeLytter = new KnappeLytter();
        
        fornavnFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel fornavnPanel = (JPanel) Komponent.labelFieldRow(labeltekst[0], fornavnFelt);
        
        etternavnFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel etternavnPanel = (JPanel) Komponent.labelFieldRow(labeltekst[1], etternavnFelt);
        
        epostFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel epostPanel = (JPanel) Komponent.labelFieldRow(labeltekst[2], epostFelt);
        
        epostigjenFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel epostigjenPanel = (JPanel) Komponent.labelFieldRow(labeltekst[3], epostigjenFelt);
        
        passordFelt = new JPasswordField(TEKSTFELTLENGDE);
        JPanel passordPanel = (JPanel) Komponent.labelFieldRow(labeltekst[4], passordFelt);
        
        passordigjenFelt = new JPasswordField(TEKSTFELTLENGDE);
        JPanel passordigjenPanel = (JPanel) Komponent.labelFieldRow(labeltekst[5], passordigjenFelt);
        
        gtadrFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel gtadrPanel = (JPanel) Komponent.labelFieldRow(labeltekst[6], gtadrFelt);
        
        postnrFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel postnrPanel = (JPanel) Komponent.labelFieldRow(labeltekst[7], postnrFelt);
        
        poststedFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel poststedPanel = (JPanel) Komponent.labelFieldRow(labeltekst[8], poststedFelt);
        
        arbstedFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel arbstedPanel = (JPanel) Komponent.labelFieldRow(labeltekst[9], arbstedFelt);
        
        registrerKnapp = new JButton("Registrer");
        registrerKnapp.addActionListener(knappeLytter);
        
        JPanel registrerKnappPanel = new JPanel(new BorderLayout());
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
    private void registrer()
    {
        String fornavn = fornavnFelt.getText();
        String etternavn = etternavnFelt.getText();
        String epost = epostFelt.getText();
        String epostigjen = epostigjenFelt.getText();
        char[] passord = passordFelt.getPassword(); 
        char[] passordigjen = passordigjenFelt.getPassword(); 
        String gateadresse = gtadrFelt.getText();
        int postnr;
        String poststed = poststedFelt.getText();
        String arbeidssted = arbstedFelt.getText();
        try{
           postnr = Integer.parseInt(postnrFelt.getText());
        }
        catch(NumberFormatException nfe)
        {
            String melding = "Ulovlig postnummer!";
            Komponent.popup(parentFrame, melding);
            return;
        }
        
        if(!epost.equals(epostigjen))
        {
            String melding = "Epost stemmer ikke!";
            Komponent.popup(parentFrame, melding);
        }
        else
        {
            if(Arrays.equals(passord, passordigjen))            
            {
                boolean registrert = parentFrame.registrerLege(fornavn, etternavn, 
                    epost, gateadresse, postnr, poststed, arbeidssted, passord);
                if(registrert)
                {
                    String melding = "Du er registrert!";
                    Komponent.popup(parentFrame, melding);
                    if(parentFrame instanceof LegekontorVindu){
                        LegekontorVindu vindu = (LegekontorVindu)parentFrame;
                        Lege innlogget = vindu.login(epost, passord);

                        if(innlogget == null)
                        {
                            melding = "Feil epost eller passord!";
                            Komponent.popup(parentFrame, melding);
                        }
                        else
                        {
                            
                            vindu.tegnFinnPasientGUI(innlogget);
                            melding = "Det er riktig!";
                            Komponent.popup(parentFrame, melding);
                        }
                    }
                }
                else
                {
                    String melding = "Du ble ikke registrert!";
                    Komponent.popup(parentFrame, melding);
                }
            }
            else
            {
                String melding = "Passordene er ikke identiske";
                Komponent.popup(parentFrame, melding);
            }
        }
                
    }
    
     private class KnappeLytter implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent e)
        {
            if(e.getSource() == registrerKnapp)
                registrer();
        }
    }
            
}
