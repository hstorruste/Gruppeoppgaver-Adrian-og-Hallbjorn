/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hallbjørn
 */

import java.io.*;
import java.util.*;

public class Legeregister implements Serializable{
    
    private static final long serialVersionUID = 1008L;
    private Comparator komp;
    private SortedSet<Lege> legeregister;

    public Legeregister()
    {
            komp = new PersonComparator();
            legeregister = new TreeSet<>(komp);
    }
    
    /*Oppretter en ny lege og setter den inn i registeret.
    Returnerer true hvis det er vellykket.*/
    public boolean settInn(String fornavn, String etternavn, String ep, 
                String gadresse, int pNr, String psted, String as, char[] pass)
    {
            Lege ny = new Lege(fornavn, etternavn, ep, gadresse, pNr, psted, as, pass);

            return settInn(ny);
    }
    /*Setter inn ny lege i registeret. Returnerer true hvis vellykket*/
    public boolean settInn(Lege ny)
    {
        if(ny == null)
            return false;
        return legeregister.add(ny);
    }
    
    //Finner en eller flere leger basert på etternavn og fornavn.
    public Lege[] finnLege(String etternavn, String fornavn)
    {
        Iterator<Lege> iterator = legeregister.iterator();
        Lege[] legearray = null;
        Lege runner = null;

        while(iterator.hasNext())
        {
            runner = iterator.next();
            if(runner.getEtternavn().matches(etternavn)
                    && runner.getFornavn().matches(fornavn))
            {
                if(legearray != null)
                {
                    int antall = legearray.length + 1;
                    Lege[] temp = new Lege[antall];

                    for(int i=0; i < legearray.length; i++)
                        temp[i] = legearray[i];

                    legearray = temp;
                    legearray[legearray.length-1] = runner;
                }        
                else
                    legearray = new Lege[]{runner};
            }

        }
        return legearray;
    }
    
    /*Finner en eller flere leger som begynner med det aktuelle navn. 
        Søker først gjennom etternavn og så fornavn.*/
    public Lege[] finnLege(String navn)
    {          
        Lege[] etternavnarray = finnLege(navn + ".*", ".*");   
        Lege[] fornavnarray = finnLege(".*", navn + ".*");
        int dobble=0;
        for (Lege etternavnarray1 : etternavnarray) {
            for (Lege fornavnarray1 : fornavnarray) 
            {
                if (etternavnarray1 == fornavnarray1) {
                    dobble++;
                    fornavnarray1 = null;
                }
            }
        }
        Lege[] legearray = new Lege[etternavnarray.length 
                + fornavnarray.length - dobble];

        int i=0;
        for (Lege etternavnarray1 : etternavnarray) {
            if (etternavnarray1 != null) {
                legearray[i] = etternavnarray1;
                i++;
            }
        }
        for (Lege fornavnarray1 : fornavnarray) {
            if (fornavnarray1 != null) {
                legearray[i] = fornavnarray1;
                i++;
            }
        }   
        return legearray;
    }
    
/*Finner og returnerer en lege i registeret basert på epost.
    Returnerer 'null' hvis ikke den finnes. */
    public Lege finnLegeEpost(String epost)
    {
            Iterator<Lege> iterator = legeregister.iterator();
            Lege runner = null;

            while(iterator.hasNext())
            {
                    runner = iterator.next();
                    if(runner.getEPost().equals(epost))
                            return runner;
            }
            return null;
    }

    //Returnerer legeregisteret
    public SortedSet<Lege> getRegister()
    {
            return legeregister;
    }

//Returnerer en string med all informasjon om hver lege.
    @Override
    public String toString()
    {
            StringBuilder str = new StringBuilder();
            Iterator<Lege> iter = legeregister.iterator();

            while(iter.hasNext())
            {
                    str.append(iter.next() + "\n");
            }

            return str.toString();
    }
}
