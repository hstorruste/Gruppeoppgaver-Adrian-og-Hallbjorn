package Controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**Denne klassen legger resepter inn i register og gjør oprasjoner mot den.
 * Laget av Hallbjørn Storruste
 * Siste versjon: 30-04-2014
 * @author Hallbjørn
 */
import Model.*;
import java.io.*;
import java.util.*;

public class Reseptregister implements Serializable {
    
    private static final long serialVersionUID = 1011L;
    private Comparator komp;
    private int antallResepter;
    private SortedSet<Resept> reseptregister;
    
    public Reseptregister()
    {
        komp = new ReseptComparator();
        antallResepter = 0;
        reseptregister = new TreeSet<>(komp);
    }
    public int getAntallResepter()
    {
        return antallResepter;
    }
    /*Oppretter og setter inn et Medisin-objekt. Returnerer true hvis vellykket.*/ 
    public boolean settInn(Medisin m, Calendar d, Lege l, Pasient p, int reit, String beskrivelse)
    {
        int reseptNr = ++antallResepter;
        Resept ny = new Resept(m, d, l, p, reit, reseptNr, beskrivelse);
        return settInn(ny);
    }
    /*Setter inn et nytt Medisin-objekt i medisinregisteret.*/
    public boolean settInn(Resept ny)
    {
        if(ny == null)
            return false;
        boolean vellykket = reseptregister.add(ny);
        if(!vellykket)
             antallResepter--;
        return vellykket;
    }
    //Finner og returnerer en String med respeter som ikke er utlevert.
    public String finnNyeResepterString()
    {
        TreeSet<Resept> liste = finnNyeResepter();
        Iterator<Resept> iterator = liste.iterator();
        StringBuilder resepter = new StringBuilder();
        Resept runner;
        while(iterator.hasNext())
        {
            runner = iterator.next();
            resepter.append(runner.toString());
            resepter.append("\n");
        }
        return resepter.toString();
    }
    //Finner og returnerer et array med respeter som ikke er utlevert.
    public TreeSet<Resept> finnNyeResepter()
    {
        TreeSet<Resept> liste = new TreeSet<>(komp);
        Iterator<Resept> iterator = reseptregister.iterator();
        Resept runner;
        while(iterator.hasNext())
        {
            runner = iterator.next();
            if(!runner.getUtlevert())
            {
                liste.add(runner);
            }
        }
        return liste;
    }
    
     //Finner og returnerer en String med respeter som er utlevert.
    public String finnGamleResepterString()
    {
        TreeSet<Resept> liste = finnGamleResepter();
        Iterator<Resept> iterator = liste.iterator();
        StringBuilder resepter = new StringBuilder();
        Resept runner;
        while(iterator.hasNext())
        {
            runner = iterator.next();
            resepter.append(runner.toString());
            resepter.append("\n");
        }
        return resepter.toString();
    }
     //Finner og returnerer et array med respeter som er utlevert.
    public TreeSet<Resept> finnGamleResepter()
    {
        TreeSet<Resept> liste = new TreeSet<>(komp);
        Iterator<Resept> iterator = reseptregister.iterator();
        Resept runner;
        while(iterator.hasNext())
        {
            runner = iterator.next();
            if(runner.getUtlevert())
            {
                liste.add(runner);
            }
        }
        return liste;
    }
    /*Finner og returnerer et array av resepter skrevet ut på den aktuelle 
    medisinen og for den aktuelle legen. Hvis lege objektet ikke eksisterer,
    er 'null', så returnerers alle resepter med den gitte medisinen.*/
    public Resept[] finnReseptMedisin(Medisin[] medisiner, Lege lege)
    {
        if(medisiner == null){
            Resept[] resepter = new Resept[reseptregister.size()];
            return reseptregister.toArray(resepter);
            
        }
        TreeSet<Resept> funnetResepter = new TreeSet<>();
        for(Medisin med: medisiner)
            funnetResepter.addAll(finnReseptAvMedisin(med));
        if(lege != null)
            funnetResepter = finnReseptAvLege(lege, funnetResepter);
        
        Resept[] resepter = new Resept[funnetResepter.size()];
        return funnetResepter.toArray(resepter);
            
    }
    
    /*Finner og returnerer et array av resepter forskrevet av den aktuelle legen
    Hvis det sendes med ett TreeSet<Resepter>, så er det denne reseptlisten som 
    gjennomgås, er dette TreeSet'et 'null', så returneres alle resepter av denne 
    legen */
    public TreeSet<Resept> finnReseptAvLege(Lege lege, TreeSet<Resept> resepter)
    {
        TreeSet<Resept> liste = new TreeSet<>(komp);
        Iterator<Resept> iterator;
        if(resepter == null)
            iterator = reseptregister.iterator();
        else
            iterator = resepter.iterator();
        Resept runner;
        while(iterator.hasNext())
        {
            runner = iterator.next();
            if(runner.getLege().equals(lege))
            {
                liste.add(runner);
            }
        }
        return liste;
    }
    //Finner og returnerer et array av resepter skrevet ut på den aktuelle medisinen.
    public TreeSet<Resept> finnReseptAvMedisin(Medisin med)
    {
        TreeSet<Resept> liste = new TreeSet<>(komp);
        Iterator<Resept> iterator = reseptregister.iterator();
        Resept runner;
        while(iterator.hasNext())
        {
            runner = iterator.next();
            if(runner.getMedisin().equals(med))
            {
               liste.add(runner);
            }
        }
        return liste;
    }
    //Finner og returnerer en resept med det aktuelle reseptnummeret.
    public Resept finnResept(int rNr)
    {
        Iterator<Resept> iterator = reseptregister.iterator();
        Resept runner;

        while(iterator.hasNext())
        {
                runner = iterator.next();
                if(runner.getReseptNr() == rNr)
                    return runner;
        }
        return null;
    }
    
    /*Returnerer reseptregisteret.*/
    public SortedSet<Resept> getRegister()
    {
        return reseptregister;
    }
    
    @Override
     public String toString()
     {
        StringBuilder str = new StringBuilder();
        Iterator<Resept> iter = reseptregister.iterator();

        while(iter.hasNext())
        {
            str.append(iter.next());
            str.append("\n");
        }

        return str.toString();
     }
    
}
