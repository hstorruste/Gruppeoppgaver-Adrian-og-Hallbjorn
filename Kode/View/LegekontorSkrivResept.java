/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Model.Lege;
import Model.Medisin;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TreeSet;
import javax.swing.*;

/**Dette er GUI for å finne og skrive ut en resept til en pasient. Klassen arver
 * JTabbedPane og innholder fanene "Skriv resept" og "Historikk". 
 * Den er en del av Legekontorvinduet(Brukes av LegekontorVindu).
 * Laget av Hallbjørn Storruste s165519
 * Siste versjon 29-04-2014
 *
 * @author Hallbjørn
 */
public class LegekontorSkrivResept extends JTabbedPane{
    
    private LegekontorVindu parentFrame;
    
    private JComboBox<String> medisinFelt;
    private JTextField gruppeFelt, kategoriFelt, atcFelt, reitFelt;
    private JTextArea bruksanvOmrade, historikkOmrade;
    private JButton sendKnapp, tilbakeKnapp, tilbakeHistKnapp;
    private String[] labeltekst = {"Medisin", "Gruppe", "Kategori", "ATC-nr", 
        "Reit", "Dosering//Bruksanvisning", "Tidligere resepter"};
    private String[] medisiner;
    
    private KnappeLytter lytter;
    
    private final int TEKSTFELTLENGDE = 20;
    
    /*Konstruktøren tar imot LegekontorVindu, dvs vindusklassen som oppretter
    objektet av denne klassen. Dette gjøres for å kunne benytte seg av metodene
    som ligger i dette objektet og kunne få tilgang til bl.a registerne, 
    registrere en resept på en pasient og gå tilbake til finn pasient GUI.
    Det vil si: å tegne vinduet på nytt med brukergrensesnittet for å finne en
    pasient.*/
    public LegekontorSkrivResept(LegekontorVindu p)
    {
        super();
        
        parentFrame = p;
        
        lytter = new KnappeLytter();
        
        String pasientnavn = parentFrame.getPasient().getNavn();
         
        addTab("Skriv resept til " + pasientnavn, skrivReseptGUI());
        addTab("Historikk for " + pasientnavn, historikkGUI());
        
    }
    //Oppretter alt i fane for "Skriv resept".
    private JPanel skrivReseptGUI()
    {
        
        medisiner = parentFrame.getAlleMedisinnavn();
        medisinFelt = new JComboBox<>(medisiner);
        medisinFelt.setSelectedIndex(-1);
        medisinFelt.setEditable(false);
        medisinFelt.addActionListener(lytter);
        JPanel medisinSkriv = (JPanel) Komponent.labelComboBoxColumb(labeltekst[0], medisinFelt);
        
        gruppeFelt = new JTextField(TEKSTFELTLENGDE);
        gruppeFelt.setEditable(false);
        JPanel gruppeSkriv = (JPanel) Komponent.labelFieldColumb(labeltekst[1], gruppeFelt);
        
        kategoriFelt = new JTextField(TEKSTFELTLENGDE);
        kategoriFelt.setEditable(false);
        JPanel kategoriSkriv = (JPanel) Komponent.labelFieldColumb(labeltekst[2], kategoriFelt);
        
        atcFelt = new JTextField(TEKSTFELTLENGDE);
        atcFelt.setEditable(false);
        JPanel atcSkriv = (JPanel) Komponent.labelFieldColumb(labeltekst[3], atcFelt);
        
        reitFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel reitSkriv = (JPanel) Komponent.labelFieldColumb(labeltekst[4], reitFelt);
        
        JPanel medisinPanel = new JPanel( new GridLayout(1,0));
        medisinPanel.add(medisinSkriv);
        medisinPanel.add(gruppeSkriv);
        medisinPanel.add(kategoriSkriv);
        medisinPanel.add(atcSkriv);
        medisinPanel.add(reitSkriv);
        
        JPanel labelPanel = new JPanel( new BorderLayout());
        labelPanel.add(new JLabel(labeltekst[5]), BorderLayout.LINE_START);
        
        bruksanvOmrade = new JTextArea(15,20);
        
        JPanel bruksanvPanel = new JPanel( new GridLayout(1,1));
        bruksanvPanel.add(new JScrollPane(bruksanvOmrade));
        
        sendKnapp = new JButton("Send");
        sendKnapp.addActionListener(lytter);
        
        tilbakeKnapp = new JButton("Tilbake");
        tilbakeKnapp.addActionListener(lytter);
        
        JPanel knappePanel = new JPanel( new BorderLayout());
        knappePanel.add(sendKnapp, BorderLayout.LINE_START);
        knappePanel.add(tilbakeKnapp, BorderLayout.LINE_END);
        
        JPanel medisinReseptPanel =  new JPanel( new GridLayout(0,1,5,5));
        medisinReseptPanel.add(medisinPanel);
        medisinReseptPanel.add(labelPanel);
      
        JPanel skrivReseptPanel = new JPanel( new BorderLayout());
        skrivReseptPanel.add(medisinReseptPanel, BorderLayout.PAGE_START);
        skrivReseptPanel.add(bruksanvPanel, BorderLayout.CENTER);
        skrivReseptPanel.add(knappePanel, BorderLayout.PAGE_END);
        
        return skrivReseptPanel;    
    }
    //Oppretter alt i fane "historikk".
    private JPanel historikkGUI(){
        
        historikkOmrade = new JTextArea(15,20);
        historikkOmrade.setEditable(false);
        String historikk = parentFrame.getPasientHistorikk();
        historikkOmrade.setText(historikk);
        
        JPanel historikkPanel = new JPanel( new GridLayout(1,1));
        historikkPanel.add(new JScrollPane(historikkOmrade));
        
        tilbakeHistKnapp = new JButton("Tilbake");
        tilbakeHistKnapp.addActionListener(lytter);
        
        JPanel knappePanel = new JPanel( new BorderLayout());
        knappePanel.add(tilbakeHistKnapp, BorderLayout.LINE_END);
        
        JPanel labelPanel = new JPanel( new BorderLayout());
        labelPanel.add(new JLabel(labeltekst[6]), BorderLayout.LINE_START);
        
        JPanel historikkGUI = new JPanel( new BorderLayout());
        historikkGUI.add(labelPanel,BorderLayout.PAGE_START);
        historikkGUI.add(historikkPanel, BorderLayout.CENTER);
        historikkGUI.add(knappePanel, BorderLayout.PAGE_END);
        
        return historikkGUI;
    }
    /*Setter gruppeFelt kategoriFelt og atcFelt til de respektive verdiene som
    medisinen som er valgt har.*/
    private void setMedisinFelt()
    {
        String medisinNavn = (String)medisinFelt.getSelectedItem();
        Medisin medisin = parentFrame.finnMedisin(medisinNavn);
        if(medisin == null)
            return;
        
        String gruppe = medisin.getGrupp();
        String kategori = medisin.getKategori();
        String atc = medisin.getATCNr();
        gruppeFelt.setText(gruppe);
        kategoriFelt.setText(kategori);
        atcFelt.setText(atc);
    }
    /*Setter inn  en resept i pasientens reseptregister.*/
    private void sendResept()
    {
        //registerer resept
        Lege lege = parentFrame.getLege();
        String medisinNavn = (String)medisinFelt.getSelectedItem();
        Medisin medisin = parentFrame.finnMedisin(medisinNavn);
        String gruppe = medisin.getGrupp();
        int reit = 0;
        String reitTekst = reitFelt.getText();
        if(!reitTekst.equals("")){
            try{
                reit = Integer.parseInt(reitTekst);
            }
            catch(NumberFormatException nfe){
                String melding = "Ugyldig antall reit! \nBruk kun tegnene 0-9.";
                Komponent.popup(parentFrame, melding);
                return;
            }
        }
        
        boolean bevilling = false;
        if(gruppe.equals("A"))
            bevilling = lege.getA();
        else if(gruppe.equals("B"))
            bevilling = lege.getB();
        else if(gruppe.equals("C"))
            bevilling = lege.getC(); 
        if(!bevilling){
            String melding = "Du har ikke bevilling til å skrive ut medisiner"
                    +" i reseptgruppe: " + gruppe +".";
            Komponent.popup(parentFrame, melding);
            return;
        }
        
        Calendar d = Calendar.getInstance();
        
        String beskrivelse = bruksanvOmrade.getText();
        
        boolean sendt = parentFrame.registrerResept(medisin, d, lege, reit, beskrivelse);   
        if(sendt){
            String melding = "Resepten er sendt";
            Komponent.popup(parentFrame, melding);
            parentFrame.skrivTilFil();
            parentFrame.tegnSkrivReseptGUI(parentFrame.getPasient());
        }
    }
    /*Tegner GUI for å finne en pasient.*/
    private void tilbake()
    {
        parentFrame.tegnFinnPasientGUI(parentFrame.getLege());
    }
    
    private class KnappeLytter implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent e)
        {
            if(e.getSource() == sendKnapp)
                sendResept();            
            else if(e.getSource() == medisinFelt)
                setMedisinFelt();
            else
                tilbake();           
        }
    }
}
