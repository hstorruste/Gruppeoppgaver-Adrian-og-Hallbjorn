/*	Denne klassen er en Pasientpost som er subklassen av Person.
		Den har en Reseptliste over alle resepter som er forskrevet
		den enkelte pasient.
		Laget av Hallbjørn Storruste
		Siste versjon 09-04-2014*/

import java.io.*;
import java.util.Calendar;

public class Pasient extends Person implements Serializable
{
	private static final long serialVersionUID = 1002L;

	private String fnr;
        private int antallResepter;
	private Reseptregister reseptliste;
        
	public Pasient(String fnavn, String enavn, String f)
	{
		super(fnavn, enavn);

		fnr = f;
                antallResepter = 0;
		reseptliste = new Reseptregister();
	}

	public String getFnr()
	{
		return fnr;
	}
        public int getAntallResepter()
        {
            return antallResepter;
        }
        
        //Returnerer pasienten sitt reseptregister.
	public Reseptregister getReseptliste()
	{
		return reseptliste;
	}
	
        //Registrerer en resept på pasienten. Returnerer true hvis vellykket.
        public boolean settInnResept(Medisin m,  Calendar d, Lege l, int reit, String beskrivelse)
        {
            int reseptNr = ++antallResepter;
            return reseptliste.settInn(m, d, l, reit, reseptNr, beskrivelse);
        }

        @Override
	public String toString()
	{
		return super.toString() + "Fødselsnummer: " + fnr + "\n";
	}
}