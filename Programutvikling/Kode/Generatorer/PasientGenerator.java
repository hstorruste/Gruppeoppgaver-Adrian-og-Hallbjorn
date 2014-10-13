package Generatorer;
/**Dette er et vindu som fungerer som en pasientgenerator.
 * Laget av Hallbjørn Storruste
 * Siste versjon 08-04-2014
 * @author Hallbjørn
 */

import Model.*;
import java.util.Random;


public class PasientGenerator {
    
    //Genererer og returnerer et nytt pasientobjekt
    public static Pasient nyPasient()
    {
        Navngenerator ngen = new Navngenerator();
        
        String fornavn = ngen.genererNavn();
        String etternavn = ngen.genererNavn();
        String fnr = nyttFnr();
        
        Pasient ny = new Pasient(fornavn, etternavn, fnr);
        
        return ny;
    }
    
    
    //Genererer et nytt fødselsnummer
    private static String nyttFnr()
    {
        String fnr = nydato();

        Random generator = new Random();

        int pnr = generator.nextInt(90000)+10000;
        fnr += pnr;

        return fnr;
    }
    // Genererer en fødslesdato.
    private static String nydato()
    {
        int[] mnd31 = {1,3,5,7,8,10,12};
        int[] mnd30 = {4,6,9,11};
        int feb = 2;
        Random generator = new Random();
        int dag = 0;
        int mnd = generator.nextInt(12) + 1;
        int aar = generator.nextInt(100);
        if(mnd == feb)
            dag = generator.nextInt(28) + 1;

        for(int m:mnd31)
            if(mnd == m)
                dag = generator.nextInt(31) + 1;

        for(int m:mnd30)
            if(mnd == m)
                dag = generator.nextInt(30) + 1;

        StringBuilder dato = new StringBuilder();
        //Legger til 0 foran dag, måned eller år hvis de er < 10.
        if(dag < 10)
            dato.append("0");

        dato.append(dag);

        if(mnd < 10)
            dato.append("0");

        dato.append(mnd);
        
        if(aar < 10)
            dato.append("0");
        
        dato.append(aar);

        return dato.toString();
    }

   
}
