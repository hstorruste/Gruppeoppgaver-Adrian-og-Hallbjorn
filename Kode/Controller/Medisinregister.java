package Controller;
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
import Model.*;
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
    public TreeSet<Medisin> finnMedisin(String navn)
    {
        Iterator<Medisin> iterator = medisinregister.iterator();
        TreeSet<Medisin> medisinSet = new TreeSet<>(komp);
        Medisin runner;
        navn = navn + ".*";

        while(iterator.hasNext())
        {
            runner = iterator.next();
            if(runner.getNavn().matches(navn))
            {
                medisinSet.add(runner);
            }

        }
        return medisinSet;
    }
    /*Finner og returnerer alle medisiner i den eller de gruppene 
    som passer med det aktuelle regionære uttrykket.*/
     public TreeSet<Medisin> finnMedisiniGruppe(String regex)
     {
        Iterator<Medisin> iterator = medisinregister.iterator();
        TreeSet<Medisin> medisinSet = new TreeSet<>(komp);
        Medisin runner;

        while(iterator.hasNext())
        {
            runner = iterator.next();
            if(runner.getGrupp().matches(regex))
            {
                medisinSet.add(runner);
            }

        }
        return medisinSet;
     }
     /*Finner og returnerer alle medisiner innen cen eller de kategorier
     som passer med det aktuelle regulære uttrykket.*/
     public TreeSet<Medisin> finnMedisiniKategori(String regex)
     {
        Iterator<Medisin> iterator = medisinregister.iterator();
        TreeSet<Medisin> medisinSet = new TreeSet<>(komp);
        Medisin runner;

        while(iterator.hasNext())
        {
            runner = iterator.next();
            if(runner.getKategori().matches(regex))
            {
                medisinSet.add(runner);
            }

        }
        return medisinSet;
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