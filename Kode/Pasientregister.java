/* Denne klassen er til for å legge Pasientobjekter in i en Collection
	og gjøre oprasjoner mot den.
	Laget av Hallbjørn Storruste, s165519
	Siste versjon: 04-04-2014*/

import java.io.*;
import java.util.*;

public class Pasientregister implements Serializable
{
	private Comparator komp;
	private SortedSet<Pasient> pasientregister;

	public Pasientregister()
	{
		komp = new PersonComparator();
		pasientregister = new TreeSet<>(komp);
	}


/*Oppretter en ny pasient og setter den inn i registeret.
	Returnerer true hvis det er vellykket.*/
	public boolean settInn(String fornavn, String etternavn, Long fødselsnr)
	{
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
	public Pasient[] finnPasient(String etternavn, String fornavn)
	{
            Iterator<Pasient> iterator = pasientregister.iterator();
            Pasient[] pasientarray = null;
            Pasient runner = null;
            
            while(iterator.hasNext())
            {
                runner = iterator.next();
                if(runner.getEtternavn().equals(etternavn)
                        && runner.getFornavn().equals(fornavn))
                {
                    if(pasientarray != null)
                    {
                        int antall = pasientarray.length + 1;
                        Pasient[] temp = new Pasient[antall];
                    
                        for(int i=0; i < pasientarray.length; i++)
                            temp[i] = pasientarray[i];
                        
                        pasientarray = temp;
                        pasientarray[pasientarray.length-1] = runner;
                    }        
                    else
                        pasientarray = new Pasient[]{runner};
                }
                
            }
            return pasientarray;

	}

//Finner en eller flere pasienter basert på navn. Søker både gjennom for og etternavn.
	public Pasient[] finnPasient(String navn)
	{          
            Pasient[] etternavnarray = finnPasient(navn + ".*", ".*");   
            Pasient[] fornavnarray = finnPasient(".*", navn + ".*");
            int dobble=0;
            for (Pasient etternavnarray1 : etternavnarray) {
                for (Pasient fornavnarray1 : fornavnarray) 
                {
                    if (etternavnarray1 == fornavnarray1) {
                        dobble++;
                        fornavnarray1 = null;
                    }
                }
            }
            Pasient[] pasientarray = new Pasient[etternavnarray.length 
                    + fornavnarray.length - dobble];
            
            int i=0;
            for (Pasient etternavnarray1 : etternavnarray) {
                if (etternavnarray1 != null) {
                    pasientarray[i] = etternavnarray1;
                    i++;
                }
            }
            for (Pasient fornavnarray1 : fornavnarray) {
                if (fornavnarray1 != null) {
                    pasientarray[i] = fornavnarray1;
                    i++;
                }
            }   
            return pasientarray;
	}

/*Finner og returnerer en pasient i registeret basert på fødselsnummer.
	Returnerer 'null' hvis ikke den finnes. */
	public Pasient finnPasient(Long fødselsnummer)
	{
		Iterator<Pasient> iterator = pasientregister.iterator();
		Pasient runner = null;

		while(iterator.hasNext())
		{
			runner = iterator.next();
			if(runner.getFnr() == fødselsnummer)
				return runner;
		}
		return null;
	}

//Returnerer pasientregisteret
	public SortedSet<Pasient> getRegister()
	{
		return pasientregister;
	}

	public String toString()
	{
                StringBuilder str = new StringBuilder();
		Iterator<Pasient> iter = pasientregister.iterator();

		while(iter.hasNext())
		{
			str.append(iter.next() + "\n");
		}

		return str.toString();
	}
}