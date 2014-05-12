/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import View.util.Komponent;
import Model.Pasient;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;

/**Dette er GUI for å finne og registering en pasient. Klassen arver
 * JTabbedPane og innholder fanene "finn pasient" og "registrer pasient". 
 * Den er en del av Legekontorvinduet(Brukes av LegekontorVindu).
 * Laget av Hallbjørn Storruste s165519
 * Siste versjon 29-04-2014
 *
 * @author Hallbjørn
 */
public class LegekontorFinnPasient extends JTabbedPane{
    
    LegekontorVindu parentFrame;
    
    private JTextField fodselsnrFinnFelt, fornavnFelt, etternavnFelt, fodselsnrFelt;
    private JButton finnKnapp, registrerKnapp, logutFinnKnapp, logutRegistrerKnapp;
    private JLabel errorFinn, error;
    private String[] labeltekst = {"Fødslesnummer","Fornavn","Etternavn", "Fødselsnummer"};
    
    
    private KnappeLytter knappeLytter;
    private FeltLytter feltLytter;
    
    private final int TEKSTFELTLENGDE = 20;
    
    /*Konstruktøren tar imot LegekontorVindu, dvs vindusklassen som oppretter
    objektet av denne klassen. Dette gjøres for å kunne benytte seg av metodene
    som ligger i dette objektet og kunne få tilgang til bl.a registerne, samt
    kunne gå videre til å skrive resept. Det vil si: å tegne vinduet på nytt med 
    brukergrensesnittet for å skrive resept.*/
    public LegekontorFinnPasient(LegekontorVindu p)
    {
        super();
        parentFrame = p;
        
        knappeLytter = new KnappeLytter();
        feltLytter = new FeltLytter();
        
        JPanel finn = new JPanel(new FlowLayout());
        finn.add(finnGUI());
        
        JPanel registrer = new JPanel(new FlowLayout());
        registrer.add(registrerGUI());
       
        addTab("Finn pasient", finn);
        addTab("Registrer pasient", registrer);
    }
    //Oppretter alt i fane for finn pasient.
    private JPanel finnGUI()
    {
        fodselsnrFinnFelt = new JTextField(TEKSTFELTLENGDE);
        fodselsnrFinnFelt.addActionListener(knappeLytter);
        fodselsnrFinnFelt.addFocusListener(feltLytter);
        JPanel fodselsnrFinn = (JPanel) Komponent.labelFieldRow(labeltekst[0], fodselsnrFinnFelt);
        
        finnKnapp = new JButton("Finn");
        finnKnapp.addActionListener(knappeLytter);
        logutFinnKnapp = new JButton("Log ut");
        logutFinnKnapp.addActionListener(knappeLytter);
        
        errorFinn = new JLabel("");
        errorFinn.setForeground(Komponent.feilTekst);
        
        JPanel knappePanel = new JPanel( new GridLayout(1,0,5,5));
        knappePanel.add(finnKnapp);
        knappePanel.add(logutFinnKnapp);
        JPanel finnKnappPanel = new JPanel(new BorderLayout());
        finnKnappPanel.add(errorFinn, BorderLayout.LINE_START);
        finnKnappPanel.add(knappePanel, BorderLayout.LINE_END);
        
        JPanel finn = new JPanel(new GridLayout(0, 1, 5, 5));
        finn.add(fodselsnrFinn);
        finn.add(finnKnappPanel);
        
        return finn;
    }
    //Opretter alt i fane for registrer pasient.
    private JPanel registrerGUI()
    {
        fornavnFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel fornavnRegistrer = (JPanel) Komponent.labelFieldRow(labeltekst[1], fornavnFelt);
        
        etternavnFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel etternavnRegistrer = (JPanel) Komponent.labelFieldRow(labeltekst[2], etternavnFelt);
        
        fodselsnrFelt = new JTextField(TEKSTFELTLENGDE);
        fodselsnrFelt.addFocusListener(feltLytter);
        fodselsnrFelt.addActionListener(knappeLytter);
        JPanel fodselsnrRegistrer = (JPanel) Komponent.labelFieldRow(labeltekst[3], fodselsnrFelt);
        
        error = new JLabel("");
        error.setForeground(Komponent.feilTekst);
        
        registrerKnapp = new JButton("Registrer");
        registrerKnapp.addActionListener(knappeLytter);
        
        logutRegistrerKnapp = new JButton("Log ut");
        logutRegistrerKnapp.addActionListener(knappeLytter);
        
        JPanel knappePanel = new JPanel( new GridLayout(1,0,5,5));
        knappePanel.add(registrerKnapp);
        knappePanel.add(logutRegistrerKnapp);
        
        JPanel registrerKnappPanel = new JPanel(new BorderLayout());
        registrerKnappPanel.add(error, BorderLayout.LINE_START);
        registrerKnappPanel.add(knappePanel, BorderLayout.LINE_END);
        
        JPanel registrer = new JPanel(new GridLayout(0, 1, 5, 5));
        registrer.add(fornavnRegistrer);
        registrer.add(etternavnRegistrer);
        registrer.add(fodselsnrRegistrer);
        registrer.add(registrerKnappPanel);
        
        return registrer;
    }
    //Metoden skal finner en pasient på fødselsnummer
    private void finn()
    {
        String fodselsnr = fodselsnrFinnFelt.getText();
        Pasient funnet = parentFrame.finnPasient(fodselsnr);
        
        if(funnet == null){
            String melding = "Finner ikke pasienten";
            Komponent.popup(parentFrame, melding);
        }
        else
            parentFrame.tegnSkrivReseptGUI(funnet);
            
    }
    
    /*Registrerer en pasient. Hvis fødselsnummer finnes fra før eller ikke er
    gyldig, gis det beskjed om dette. Blir pasienten registrert tegnes vinduet
    på nytt med GUI for å skrive resept.*/
    private void registrer()
    {
        String fornavn = fornavnFelt.getText();
        String etternavn = etternavnFelt.getText();
        String fodselsnr = fodselsnrFelt.getText();
        
        if(!Komponent.riktigFNr(fodselsnr))
        {
            String melding = "Pasienten ble ikke registrert!\n"
                    + "Fødselsnummeret er ugyldig.";
            Komponent.popup(parentFrame, melding);
            return;
        }
       
        boolean registrert = parentFrame.registrerPasient(fornavn, etternavn, fodselsnr);
        if(registrert)
        {
            parentFrame.skrivTilFil();
            Pasient funnet = parentFrame.finnPasient(fodselsnr);
            parentFrame.tegnSkrivReseptGUI(funnet);
        }
        else 
        {
            String melding = "Pasienten ble ikke registrert!\n"
                    + "Personen finnes fra før.";
            Komponent.popup(parentFrame, melding);
        }
    }
    //Logger ut av lege.
    private void logut()
    {
        parentFrame.tegnLoginGUI();
    }
    
     private class KnappeLytter implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent e)
        {
            if(e.getSource() == finnKnapp || e.getSource() == fodselsnrFinnFelt)
                finn();
            else if(e.getSource() == registrerKnapp || e.getSource() == fodselsnrFelt)
                registrer();
            else if(e.getSource() == logutRegistrerKnapp || e.getSource() == logutFinnKnapp)
                logut();
        }
    }
     
     private class FeltLytter extends FocusAdapter {

        @Override
        public void focusLost(FocusEvent e) {
            if(e.getSource() == fodselsnrFinnFelt )
            {
                if(Komponent.riktigFNr(fodselsnrFinnFelt.getText())){
                    fodselsnrFinnFelt.setForeground(Komponent.rettTekst);
                    errorFinn.setText("");
                }
                else{
                     fodselsnrFinnFelt.setForeground(Komponent.feilTekst);
                     errorFinn.setText("Vennligst skriv fødselsnummer på formen: ddmmåå + 5 siffer");
                }
            }
            else if(e.getSource() == fodselsnrFelt){
                if(Komponent.riktigFNr(fodselsnrFelt.getText())){
                    fodselsnrFelt.setForeground(Komponent.rettTekst);
                    error.setText("");
                }
                else{
                     fodselsnrFelt.setForeground(Komponent.feilTekst);
                     error.setText("Vennligst skriv fødselsnummer på formen: ddmmåå + 5 siffer");
                }
            }
        }
    }
}
