package Generatorer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**Dette er et vindu som fungerer som en pasientgenerator
 * Laget av Hallbjørn Storruste
 * Siste versjon 08-04-2014
 * @author Hallbjørn
 */

import Model.*;


public class PasientGenerator {
    
    
    public Pasient nyPasient()
    {
        Navngenerator ngen = new Navngenerator();
        
        String fornavn = ngen.genererNavn();
        String etternavn = ngen.genererNavn();
        String fnr = NummerGenerator.nyttFnr();
        
        Pasient ny = new Pasient(fornavn, etternavn, fnr);
        
        return ny;
    }
   
}
