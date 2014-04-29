/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Model.Pasient;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton finnKnapp, registrerKnapp;
    private String[] labeltekst = {"Fødslesnummer","Fornavn","Etternavn", "Fødselsnummer"};
    
    private KnappeLytter knappeLytter;
    
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
        JPanel fodselsnrFinn = (JPanel) Komponent.labelFieldRow(labeltekst[0], fodselsnrFinnFelt);
        
        finnKnapp = new JButton("Finn");
        finnKnapp.addActionListener(knappeLytter);
        
        JPanel finnKnappPanel = new JPanel(new BorderLayout());
        finnKnappPanel.add(finnKnapp, BorderLayout.LINE_END);
        
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
        JPanel fodselsnrRegistrer = (JPanel) Komponent.labelFieldRow(labeltekst[3], fodselsnrFelt);
        
        registrerKnapp = new JButton("Registrer");
        registrerKnapp.addActionListener(knappeLytter);
        
        JPanel registrerKnappPanel = new JPanel(new BorderLayout());
        registrerKnappPanel.add(registrerKnapp, BorderLayout.LINE_END);
        
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
        
        boolean registrert = parentFrame.registrerPasient(fornavn, etternavn, fodselsnr);
        if(registrert)
        {
            Pasient funnet = parentFrame.finnPasient(fodselsnr);
            parentFrame.tegnSkrivReseptGUI(funnet);
        }
        else
        {
            String melding = "Pasienten ble ikke registrert!\n"
                    + "Fødselsnummeret er ugyldig, eller personen finnes fra før.";
            Komponent.popup(parentFrame, melding);
        }
    }
    
     private class KnappeLytter implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent e)
        {
            if(e.getSource() == finnKnapp)
                finn();
            else
              registrer();
        }
    }
}
