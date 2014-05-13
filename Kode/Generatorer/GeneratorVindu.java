/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Generatorer;

import Controller.*;
import Model.*;
import View.util.Komponent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.*;


/**Dette vinduet, og dennne delen av programmet, skal generere fiktive data i
 * systemet. Det gjøres på følgende måte:
 * Medisiner må man legge inn manuelt i eget register på forhånd.
 * Man kan opprette tilfeldige leger og tilfeldige pasienter, så mange man måtte
 * ønske. (Legenes passord settes til standardpassord: '1234'). 
 * Deretter kan man generere et gitt antall resepter for hver pasient,
 * som legges i pasientens reseptregister. Dette gjøres på følgende måte: 
 * 
 * For hver pasient velges det ut 1-4 forskjellige leger som det blir skrevet ut 
 * et tilfeldig anntall resepter for, opp til det gitte maksimale antallet som
 * brukeren skriver inn. Reseptene skrives ut for perioden 1.1.2012 frem til
 * dags dato.Generatoren sørger for at pasienten finnes i legens eget pasientregister.
 
 * Gerneratorprogrammet består av følgene delgeneratorer:
 * Navngenerator
 * PasientGenerator
 * LegeGenerator
 * Reseptgenerator
 * 
 * Siste versjon: 13-05-2014
 * Laget av Hallbjørn Storruste s165519
 *
 * @author Hallbjørn
 */
public class GeneratorVindu extends JFrame{
    
    
    private Medisinregister medisinregister;
    private Legeregister legeregister;
    private Pasientregister pasientregister;
    private int antallLeger;
    private int antallPasienter;
    private int antallResepter;
    
    private LegeGenerator legegenerator;
    private PasientGenerator pasientgenerator;
    private ReseptGenerator reseptgenerator;
    
    private final int FELTLENGDE = 10;
    
    private JTextField legeFelt, pasientFelt, reseptFelt,
            antLegeFelt, antPasientFelt, antReseptFelt;
    private JButton genLegeKnapp, genPasientKnapp, genReseptKnapp;
    private JTextArea info;
    
    private KnappeLytter knappelytter;
    

    public GeneratorVindu(Medisinregister medisinregister, 
            Legeregister legeregister, Pasientregister pasientregister)
    {
        super("E-resept generator");
        this.medisinregister = medisinregister;
        this.legeregister = legeregister;
        this.pasientregister = pasientregister;
        antallLeger = 0;
        antallPasienter = 0;
        antallResepter = 0;
        
        legegenerator = new LegeGenerator();
        pasientgenerator = new PasientGenerator();
        reseptgenerator = new ReseptGenerator(medisinregister);
        
        knappelytter = new KnappeLytter();
     
        setLayout(new FlowLayout());
        
        JPanel feltPanel = lagFeltGUI();
        info = new JTextArea();
        info.setEditable(false);
        
        JPanel genPanel = new JPanel(new GridLayout(0,1));
        genPanel.add( feltPanel );
        genPanel.add( info );
        add(genPanel);
        
        resultater();
        Komponent.bilde(this);
        Komponent.endreFont(this);
        setVisible(true);
        pack();
    }
    //Oppretter alle komponenter til brukergrensesnittet.
    private JPanel lagFeltGUI()
    {
        legeFelt = new JTextField(FELTLENGDE);
        JPanel legePanel = Komponent.labelFieldRow("Antall leger: ", legeFelt);
        
        pasientFelt = new JTextField(FELTLENGDE);
        JPanel pasientPanel = Komponent.labelFieldRow("Antall pasienter: ", pasientFelt);
        
        reseptFelt = new JTextField(FELTLENGDE);
        JPanel reseptPanel = Komponent.labelFieldRow("Maks resepter/lege/pasient : ", reseptFelt);
        
        genLegeKnapp = new JButton("Opprett leger");
        genLegeKnapp.addActionListener(knappelytter);
        
        genPasientKnapp = new JButton("Opprett pasienter");
        genPasientKnapp.addActionListener(knappelytter);
        
        genReseptKnapp = new JButton("Opprett resepter");
        genReseptKnapp.addActionListener(knappelytter);
        
        antLegeFelt = new JTextField(FELTLENGDE);
        antLegeFelt.setEditable(false);
        JPanel antLegePanel = Komponent.labelFieldRow("Leger opprettet: ", antLegeFelt);
        
        antPasientFelt = new JTextField(FELTLENGDE);
        antPasientFelt.setEditable(false);
        JPanel antPasientPanel = Komponent.labelFieldRow("Pasienter opprettet: ", antPasientFelt);
        
        antReseptFelt = new JTextField(FELTLENGDE);
        antReseptFelt.setEditable(false);
        JPanel antReseptPanel = Komponent.labelFieldRow("Resepter opprettet: ", antReseptFelt);
        
        JPanel feltPanel = new JPanel(new GridLayout(3,3,5,5));
        feltPanel.add(legePanel);
        feltPanel.add(pasientPanel);
        feltPanel.add(reseptPanel);
        feltPanel.add(genLegeKnapp);
        feltPanel.add(genPasientKnapp);
        feltPanel.add(genReseptKnapp);
        feltPanel.add(antLegePanel);
        feltPanel.add(antPasientPanel);
        feltPanel.add(antReseptPanel);
        
        return feltPanel;
                
    }
    /*Genererer resepter for hver enkelt pasient. Det velges ut 1-4 leger hvor 
    det lages et antall resepter(>0) fra hver lege. Maks antall resepter per lege
    per pasient gis med som parameter. */
    private void genererResepter(int antall)
    {
        Lege[] legeListe  = legeregister.getAlleLeger();
        Pasient[] pasientListe = pasientregister.getAllePasienter();
        int antLeger = legeListe.length;
        int antPasienter = pasientListe.length;
        
        if(antLeger == 0 || antPasienter == 0){
             String melding = "Personer mangler i registeret";
             Komponent.popup(null, melding);
             return;
        }
        
        Random generator = new Random();
        
        for(Pasient runner:pasientListe)
        {
            int antallLeger = generator.nextInt(4) + 1;
            for(int i = 0; i < antallLeger; i++)
            {
                int indeksLege = generator.nextInt(antLeger);
                Lege valgt = legeListe[indeksLege];
                int antResepter = generator.nextInt(antall) + 1;
                for( int j = 0; j < antResepter; j++)
                {
                    Resept lagd = reseptgenerator.nyResept(runner, valgt);
                    if(lagd != null)
                        antallResepter++;
                }
            }
            
        }
    }
   /*Oppretter så mange leger som det antallet man sender med. Antallet 
    leger som blir opprettet kan være lavere hvis det allerede finnes 
    en lege med den samme eposten. Den vil da ikke bli talt med*/
    private void genererLeger(int antall)
    {
        for(int i = 0; i < antall; i++)
        {
            Lege ny = legegenerator.nyLege();
            boolean sattInn = legeregister.settInn(ny);
            if(sattInn)
                antallLeger++;
        }
        Lege[] legeListe  = legeregister.getAlleLeger();
        Random generator = new Random();
        //Setter bevilling B til false
        int ant = legeListe.length/50;
        for(int i=0; i < ant; i++)
        {
            int index = generator.nextInt(legeListe.length);
            Lege lege = legeListe[index];
            lege.setB(false);
        }
        //Setter bevilling A til false
        ant = legeListe.length/20;
        for(int i=0; i < ant; i++)
        {
            int index = generator.nextInt(legeListe.length);
            Lege lege = legeListe[index];
            lege.setA(false);
        }
        
    }
    /*Oppretter så mange pasienter som det antallet man sender med. Antallet 
    pasienter som blir opprettet kan være lavere hvis det allerede finnes 
    en pasient med det samme fødselsnummeret. Den vil da ikke bli talt med*/
    private void genererPasienter(int antall)
    {
        for(int i = 0; i < antall; i++)
        {
            Pasient ny = pasientgenerator.nyPasient();
            boolean sattInn = pasientregister.settInn(ny);
            if(sattInn)
                antallPasienter++;
        }
    }
    /*Viser data om registrene i et JTextArea(info)*/
    private void resultater()
    {
        TreeSet<Lege> legeSet = (TreeSet)legeregister.getRegister();
        TreeSet<Pasient> pasientSet = (TreeSet)pasientregister.getRegister();
        TreeSet<Medisin> medisinSet = (TreeSet)medisinregister.getMedisinregister();
        int totaltLeger = legeSet.size();
        int totaltPasienter = pasientSet.size();
        
        Iterator iter = pasientSet.iterator();
        int totaltResepter = 0;
        Pasient runnerP;
        while(iter.hasNext())
        {
            runnerP = (Pasient)iter.next();
            totaltResepter += runnerP.getAntallResepter();
        }
        
        iter = legeSet.iterator();
        int antallPasientListe = 0;
        Lege runnerL;
        while(iter.hasNext())
        {
            runnerL = (Lege)iter.next();
            antallPasientListe += runnerL.getPasientliste().getRegister().size();
        }
        
        int pasientPerLege = antallPasientListe/totaltLeger;
        
        String utskrift = "Totalt antall leger: " + totaltLeger;
        utskrift += "\nTotalt antall pasienter: " + totaltPasienter;
        utskrift += "\nTotalt antall resepter: " + totaltResepter;
        utskrift += "\nGjennomsnittlig antall pasienter på legens pasientliste: ";
        utskrift += pasientPerLege;
        utskrift += "\nGjennomsnittlig antall resepter per pasient: ";
        utskrift += (totaltResepter/totaltPasienter);
        utskrift += "\nGjennomsnittlig antall resepter per lege: ";
        utskrift += (totaltResepter/totaltLeger);
        
        
        info.setText(utskrift);
    }
    

    private class KnappeLytter implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         try{
                if(e.getSource() == genLegeKnapp){
                    int antall = Integer.parseInt(legeFelt.getText());
                    genererLeger(antall);
                    antLegeFelt.setText("" + antallLeger);
                    legeFelt.setText("");
                }
                else if(e.getSource() == genPasientKnapp){
                    int antall = Integer.parseInt(pasientFelt.getText());
                    genererPasienter(antall);
                    antPasientFelt.setText("" + antallPasienter);
                    pasientFelt.setText("");
                }
                else if(e.getSource() == genReseptKnapp){
                    int antall = Integer.parseInt(reseptFelt.getText());
                    if(antall > 0)
                        genererResepter(antall);
                    antReseptFelt.setText("" + antallResepter);
                    reseptFelt.setText("");
                }
                resultater();
         }catch(NumberFormatException nfe){
             String melding = "Feil verdi i nummerfelt";
             Komponent.popup(null, melding);
         }
        }
        
    }
}
