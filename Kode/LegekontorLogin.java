/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Model.Lege;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;

/**Dette er GUI for innlogging og registering av lege. Klassen arver
 * JTabbedPane og innholder fanene login og registerr. Den er en del av 
 * Legekontorvinduet(Brukes av LegekontorVindu).
 * Laget av Hallbjørn Storruste s165519
 * Siste versjon 27-04-2014
 * 
 * @author Hallbjørn
 */
public class LegekontorLogin extends JTabbedPane{
    
    private LegekontorVindu parentFrame;
    private JTextField fornavnFelt, etternavnFelt, epostFelt, epostigjenFelt,
            gtadrFelt, postnrFelt, poststedFelt, arbstedFelt, epostLoginFelt;
    private JPasswordField passordFelt, passordigjenFelt, passordLoginFelt;
    private JButton registrerKnapp, loginKnapp;
    private String[] labeltekst = {"E-post", "Passord", "Fornavn", "Etternavn",
            "E-post", "E-post igjen", "Passord", "Passord igjen", "Gateadresse",
            "Postnummer", "Poststed", "Arbeidssted"};
    private final int TEKSTFELTLENGDE = 20;
    
    private KnappeLytter knappeLytter;
    
    /*Konstruktøren tar imot LegekontorVindu, dvs vindusklassen som oppretter
    objektet av denne klassen. Dette gjøres for å kunne benytte seg av metodene
    som ligger i dette objektet og kunne få tilgang på bl.a registerne, samt
    kunne gå vidrere/logge inn. Det vil si å tegne vinduet på nytt med 
    brukergrensesnittet for å finne en pasient.*/
    public LegekontorLogin(LegekontorVindu p)
    {
        super();
        parentFrame = p;
        
        knappeLytter = new KnappeLytter();
        
        JPanel login = new JPanel(new FlowLayout());
        login.add(loginGUI());
        
        JPanel registrer = new JPanel(new FlowLayout());
        registrer.add(registrerGUI());
       
        addTab("Login", login);
        addTab("Registrer", registrer);
    }
    
    //Oppretter alt i fanen for login.
    private JPanel loginGUI()
    {
        epostLoginFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel epostLogin = (JPanel) Komponent.labelFieldRow(labeltekst[0], epostLoginFelt);
        
        passordLoginFelt = new JPasswordField(TEKSTFELTLENGDE);
        JPanel passordLogin = (JPanel) Komponent.labelFieldRow(labeltekst[1], passordLoginFelt);
        
        loginKnapp = new JButton("Logg inn");
        loginKnapp.addActionListener(knappeLytter);
        
        JPanel loginKnappPanel = new JPanel(new BorderLayout());
        loginKnappPanel.add(loginKnapp, BorderLayout.LINE_END);
        
        JPanel login = new JPanel(new GridLayout(0, 1, 5, 5));
        login.add(epostLogin);
        login.add(passordLogin);
        login.add(loginKnappPanel);
        
        return login;     
    }
    //Oppretter alt i fane for registrering av lege
    private JPanel registrerGUI()
    {
        fornavnFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel fornavnPanel = (JPanel) Komponent.labelFieldRow(labeltekst[2], fornavnFelt);
        
        etternavnFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel etternavnPanel = (JPanel) Komponent.labelFieldRow(labeltekst[3], etternavnFelt);
        
        epostFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel epostPanel = (JPanel) Komponent.labelFieldRow(labeltekst[4], epostFelt);
        
        epostigjenFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel epostigjenPanel = (JPanel) Komponent.labelFieldRow(labeltekst[5], epostigjenFelt);
        
        passordFelt = new JPasswordField(TEKSTFELTLENGDE);
        JPanel passordPanel = (JPanel) Komponent.labelFieldRow(labeltekst[6], passordFelt);
        
        passordigjenFelt = new JPasswordField(TEKSTFELTLENGDE);
        JPanel passordigjenPanel = (JPanel) Komponent.labelFieldRow(labeltekst[7], passordigjenFelt);
        
        gtadrFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel gtadrPanel = (JPanel) Komponent.labelFieldRow(labeltekst[8], gtadrFelt);
        
        postnrFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel postnrPanel = (JPanel) Komponent.labelFieldRow(labeltekst[9], postnrFelt);
        
        poststedFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel poststedPanel = (JPanel) Komponent.labelFieldRow(labeltekst[10], poststedFelt);
        
        arbstedFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel arbstedPanel = (JPanel) Komponent.labelFieldRow(labeltekst[11], arbstedFelt);
        
        registrerKnapp = new JButton("Registrer");
        registrerKnapp.addActionListener(knappeLytter);
        
        JPanel registrerKnappPanel = new JPanel(new BorderLayout());
        registrerKnappPanel.add(registrerKnapp, BorderLayout.LINE_END);
        
        JPanel registrer = new JPanel(new GridLayout(0, 1, 5, 5));
        registrer.add(fornavnPanel);
        registrer.add(etternavnPanel);
        registrer.add(epostPanel);
        registrer.add(epostigjenPanel);
        registrer.add(passordPanel);
        registrer.add(passordigjenPanel);
        registrer.add(gtadrPanel);
        registrer.add(postnrPanel);
        registrer.add(poststedPanel);
        registrer.add(arbstedPanel);
        registrer.add(registrerKnappPanel);
        
        return registrer;
        
    }
    /*Denne metoden sjekker om epost og passord er skrevet riktig inn.
    Og sender eventuelt videre til login.*/
    public void login()
    {
        String epost = epostLoginFelt.getText();
        char[] passord = passordLoginFelt.getPassword();
        Lege innlogget = parentFrame.login(epost, passord);
        
        if(innlogget == null)
        {
            String melding = "Feil epost eller passord!";
            Komponent.popup(parentFrame, melding);
        }
        else
            parentFrame.tegnFinnPasientGUI(innlogget);
            
    }
    
    /*Registerer en lege i registeret. Gir beskjed via et dialogvindu
    om hvordan registeringen går og eventuelle brukerfeil.*/
    public void registrer()
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
                boolean registrert = parentFrame.registrer(fornavn, etternavn, 
                    epost, gateadresse, postnr, poststed, epost, passord);
                if(registrert)
                {
                    String melding = "Du er registrert!";
                    Komponent.popup(parentFrame, melding);
                    
                    Lege innlogget = parentFrame.login(epost, passord);
        
                    if(innlogget == null)
                    {
                        melding = "Feil epost eller passord!";
                        Komponent.popup(parentFrame, melding);
                    }
                    else
                    {
                        parentFrame.tegnFinnPasientGUI(innlogget);
                        melding = "Det er riktig!";
                        Komponent.popup(parentFrame, melding);
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
            if(e.getSource() == loginKnapp)
                login();
            else
                registrer();
        }
    }
}
