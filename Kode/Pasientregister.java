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
        korrektFnr = "[0-3]\\d[01]\\d{8]";
        komp = new PersonComparator();
        pasientregister = new TreeSet<>(komp);
    }


/*Oppretter en ny pasient og setter den inn i registeret.
	Returnerer true hvis det er vellykket.*/
	public boolean settInn(String fornavn, String etternavn, String fødselsnr)
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
            Pasient runner;
            
            while(iterator.hasNext())
            {
                runner = iterator.next();
                if(runner.getEtternavn().matches(etternavn)
                        && runner.getFornavn().matches(fornavn))
                {
                    if(pasientarray != null)
                    {
                        int antall = pasientarray.length + 1;
                        Pasient[] temp = new Pasient[antall];
                        System.arraycopy(pasientarray, 0, temp, 0, pasientarray.length);
                        
                        pasientarray = temp;
                        pasientarray[pasientarray.length-1] = runner;
                    }        
                    else
                        pasientarray = new Pasient[]{runner};
                }
                
            }
            return pasientarray;

	}

/*Finner en eller flere pasienter som begynner med det aktuelle navn. 
        Søker først gjennom etternavn og så fornavn.*/
	public Pasient[] finnPasient(String navn)
	{          
            Pasient[] etternavnarray = finnPasient(navn + ".*", ".*");   
            Pasient[] fornavnarray = finnPasient(".*", navn + ".*");
            Pasient[] pasientarray;
            int dobble=0;
            if(etternavnarray == null)
                pasientarray = fornavnarray;
            else if(fornavnarray == null)
                pasientarray = etternavnarray;
            else
            {
                for (Pasient etternavnarray1 : etternavnarray) {
                   for (Pasient fornavnarray1 : fornavnarray) 
                        if (etternavnarray1 == fornavnarray1) {
                            dobble++;
                            fornavnarray1 = null;
                        }
                }
                pasientarray = new Pasient[etternavnarray.length 
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
            }//end of else
           
            return pasientarray;
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