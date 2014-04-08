/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**Denne klassen legger medisiner inn i et register og gjør operasjoner mot den.
 * Laget av Hallbjørn Storruste
 * Siste versjon: 08-04-2014
 * @author Hallbjørn
 */
import java.io.*;
import java.util.*;

public class Medisinregister implements Serializable {
    
    private static final long serialVersionUID = 1009L;
    private Comparator komp;
    private SortedSet<Medisin> medisinregister;
    
    public Medisinregister()
    {
        komp = new MedisinComparator();
        medisinregister = new TreeSet<>(komp);
    }
    /*Oppretter og setter inn et Medisin-objekt. Returnerer true hvis vellykket.*/
    public boolean settInn(String navn, String kat, String gruppe, String actNr)
    {
        Medisin ny = new Medisin(navn, kat, gruppe, actNr);
        
        return settInn(ny);
    }
    /*Setter inn et nytt Medisin-objekt i medisinregisteret.*/
    public boolean settInn(Medisin ny)
    {
        if(ny == null)
            return false;
        return medisinregister.add(ny);
    }
    /*Finner en eller flere medisiner som begynner med det aktuelle navnet.*/
    public Medisin[] finnMedisin(String navn)
    {
        Iterator<Medisin> iterator = medisinregister.iterator();
        Medisin[] medisinarray = null;
        Medisin runner = null;
        navn = navn + ".*";

        while(iterator.hasNext())
        {
            runner = iterator.next();
            if(runner.getNavn().matches(navn))
            {
                if(medisinarray != null)
                {
                    int antall = medisinarray.length + 1;
                    Medisin[] temp = new Medisin[antall];

                    for(int i=0; i < medisinarray.length; i++)
                        temp[i] = medisinarray[i];

                    medisinarray = temp;
                    medisinarray[medisinarray.length-1] = runner;
                }        
                else
                    medisinarray = new Medisin[]{runner};
            }

        }
        return medisinarray;
    }
    /*Finner og returnerer alle medisiner i den eller de gruppene 
    som passer med det aktuelle regionære uttrykket.*/
     public Medisin[] finnMedisiniGruppe(String regex)
     {
        Iterator<Medisin> iterator = medisinregister.iterator();
        Medisin[] medisinarray = null;
        Medisin runner = null;

        while(iterator.hasNext())
        {
            runner = iterator.next();
            if(runner.getGrupp().matches(regex))
            {
                if(medisinarray == null)
                    medisinarray = new Medisin[]{runner};
                else {
                    int antall = medisinarray.length + 1;
                    Medisin[] temp = new Medisin[antall];

                    for(int i=0; i < medisinarray.length; i++)
                        temp[i] = medisinarray[i];

                    medisinarray = temp;
                    medisinarray[medisinarray.length-1] = runner;
                }
            }

        }
        return medisinarray;
     }
     /*Finner og returnerer alle medisiner innen cen eller de kategorier
     som passer med det aktuelle regulære uttrykket.*/
     public Medisin[] finnMedisiniKategori(String regex)
     {
        Iterator<Medisin> iterator = medisinregister.iterator();
        Medisin[] medisinarray = null;
        Medisin runner = null;

        while(iterator.hasNext())
        {
            runner = iterator.next();
            if(runner.getKategori().matches(regex))
            {
                if(medisinarray == null)
                    medisinarray = new Medisin[]{runner};
                else {
                    int antall = medisinarray.length + 1;
                    Medisin[] temp = new Medisin[antall];

                    for(int i=0; i < medisinarray.length; i++)
                        temp[i] = medisinarray[i];

                    medisinarray = temp;
                    medisinarray[medisinarray.length-1] = runner;
                }
            }

        }
        return medisinarray;
     }
     
    @Override
     public String toString()
     {
        StringBuilder str = new StringBuilder();
        Iterator<Medisin> iter = medisinregister.iterator();

        while(iter.hasNext())
        {
            str.append(iter.next());
            str.append("\n");
        }

        return str.toString();
     }
}
