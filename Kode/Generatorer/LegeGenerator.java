/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Generatorer;

import View.util.Postnummer;
import View.util.Poststed;
import Model.Lege;
import java.util.Random;

/**Denne klassen er til for å generere lege objekter til e-resept-sytemet.
 *Siste versjon: 13-05-2014
 *Laget av Hallbjørn Storruste s165519
 * @author Hallbjørn
 */
public class LegeGenerator {
    
    private final String[] gateadresseEtternavn = {"veien", " gate", " vei", "gata", " allé", "faret", "lia"};
    private String[] arbeidstedEtternavn = {"legesenter", "sykehus"};
    private Navngenerator navngenerator;
    private Random generator;
    
    public LegeGenerator()
    {
        navngenerator = new Navngenerator();
        
        generator = new Random();
        
    }
    
    public Lege nyLege()
    {
        String Fornavn = navngenerator.genererNavn();
        String Etternavn = navngenerator.genererNavn();
        String epost = Fornavn.substring(0, 1);
        epost += Etternavn + "@";
        String arbeidsted = navngenerator.genererNavn();
        epost += arbeidsted + ".no";
        epost = epost.toLowerCase();
        int arbSted = generator.nextInt(10);
        if(arbSted > 8)
            arbeidsted += " " + arbeidstedEtternavn[1];
        else if(arbSted < 2)
            arbeidsted = "";
        else
            arbeidsted += " " + arbeidstedEtternavn[0];
        char[] passord = {'1','2','3','4'};
        String gateadresse = gateAdresse();
        int index = (generator.nextInt(Postnummer.postnummer.length));
        String postNr = Postnummer.postnummer[index];
        String postSted = Poststed.postSted[index];
        Lege ny = new Lege(Fornavn, Etternavn, epost, gateadresse, postNr, postSted, arbeidsted, passord);
        
        return ny;  
    }
    
    private String gateAdresse()
    {
        String adresse = navngenerator.genererNavn();
        int index = generator.nextInt(7);
        adresse += gateadresseEtternavn[index];
        int nummer = generator.nextInt(70) + 1;
        adresse += " " + nummer;
        
        return adresse;
    }
    
    
}
