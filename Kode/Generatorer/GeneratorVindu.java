/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Generatorer;

import Controller.*;
import Model.*;
import javax.swing.JFrame;


/**Dette vinduet, og dennne delen av programmet, skal generere fiktive data i
 * systemet. Det gjøres på følgende måte:
 * Det opprettes ett register med leger og ett register med pasienter.
 * Medisiner må man legge inn manuelt i eget register på forhånd.
 * Deretter genereres resepter som legges i pasientenes reseptregister. 
 * Det sendes med et legeobjekt og generatoren sørger for at pasienten finnes i 
 * legens eget pasientregister.
 * For hver pasient velges det ut 1-4 forskjellige leger som det blir skrevet ut 
 * et tilfeldig anntall resepter for (1-30). Dette gjøres for periode
 * tilbakedatert to år.
 * Gerneratorprogrammet består av følgene delgeneratorer:
 * Navngenerator
 * FNummerGenerator
 * PasientGenerator
 * epost og arbeidssted- generator
 * gateadresse-generator
 * postnummer(+ poststed)-generator( Kan kanskje bruke liste fra database???)
 * (alle passord settes til '1234' for lettere å kunne teste programmet)
 * Reseptgenerator
 * 
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
        
    }
    
    private void genererLeger(int antall)
    {
        for(int i = 0; i < antall; i++)
        {
            Lege ny = legegenerator.nyLege();
            boolean sattInn = legeregister.settInn(ny);
            if(sattInn)
                antallLeger++;
        }
        
    }
    
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
    
    private String resultater()
    {
        String utskrift = "Antall leger: " + antallLeger;
        utskrift += "\nAntall pasineter: " + antallPasienter;
        utskrift += "\nAntall resepter: " + antallResepter;
        
        return utskrift;
    }
    

}
