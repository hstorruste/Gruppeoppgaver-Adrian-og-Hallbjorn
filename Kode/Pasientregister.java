/* Denne klassen er til for å legge Pasientobjekter in i en Collection
	og gjøre oprasjoner mot den.
	Laget av Hallbjørn Storruste, s165519
	Siste versjon: 06-04-2014*/

import java.io.*;
import java.util.*;

public class Pasientregister implements Serializable
{
    private static final long serialVersionUID = 1007L;
    private Comparator komp;
    private SortedSet<Pasient> pasientregister;
    private final String korrektFnr;

    public Pasientregister()
    {
        korrektFnr = "[0-3]\\d[01]\\d{8}";
        komp = new PersonComparator();
        pasientregister = new TreeSet<>(komp);
    }


/*Oppretter en ny pasient og setter den inn i registeret.
	Returnerer true hvis det er vellykket.*/
	public boolean settInn(String fornavn, String etternavn, String fødselsnr)
	{
		if(!fødselsnr.matches(korrektFnr))
                    return false;
            
                Pasient ny = new Pasient(fornavn, etternavn, fødselsnr);

		return settInn(ny);
	}

//Setter inn ny pasient i registeret. Returnerer true hvis det er vellykket.
	public boolean settInn(Pasient ny)
	{
                if(ny == null)
                    return false;

		return pasientregister.add(ny);
	}

//Finner en eller flere pasienter basert på etternavn og fornavn.
	public TreeSet<Pasient> finnPasient(String etternavn, String fornavn)
	{
            Iterator<Pasient> iterator = pasientregister.iterator();
            TreeSet<Pasient> pasientSet = new TreeSet<>(komp);
            Pasient runner;
            
            while(iterator.hasNext())
            {
                runner = iterator.next();
                if(runner.getEtternavn().matches(etternavn)
                        && runner.getFornavn().matches(fornavn))
                {
                   pasientSet.add(runner);
                }
                
            }
            return pasientSet;

	}

/*Finner en eller flere pasienter som begynner med det aktuelle navn. 
        Søker først gjennom etternavn og så fornavn.*/
	public TreeSet<Pasient> finnPasient(String navn)
	{          
            TreeSet<Pasient> etternavnSet = finnPasient(navn + ".*", ".*");   
            TreeSet<Pasient> fornavnSet = finnPasient(".*", navn + ".*");
            TreeSet<Pasient> pasientSet;
            pasientSet = etternavnSet;
            pasientSet.addAll(fornavnSet);
           
            return pasientSet;
	}

/*Finner og returnerer en pasient i registeret basert på fødselsnummer.
	Returnerer 'null' hvis ikke den finnes. */
	public Pasient finnPasientFnr(String fødselsnummer)
	{
		Iterator<Pasient> iterator = pasientregister.iterator();
		Pasient runner;

		while(iterator.hasNext())
		{
			runner = iterator.next();
			if(runner.getFnr().equals(fødselsnummer))
				return runner;
		}
		return null;
	}

//Returnerer pasientregisteret
	public SortedSet<Pasient> getRegister()
	{
		return pasientregister;
	}

//Returnerer en string med all informasjon om hver pasient.
        @Override
	public String toString()
	{
            StringBuilder str = new StringBuilder();
            Iterator<Pasient> iter = pasientregister.iterator();

            while(iter.hasNext())
            {
                str.append(iter.next());
                str.append("\n");
            }

            return str.toString();
	}
}