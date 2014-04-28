package Model;
/* Denne klassen er en Reseptpost.
	Klassen har en Medisinpeker som peker på medisinen for reseptet.
	Legepeker som peker på den lege som har skrivit reseptet.
	Laget av Adrian Westlund
	Siste versjon 03-04-2014*/

import java.io.*;
import java.util.Calendar;
import java.text.DateFormat;

public class Resept implements Serializable
{
	private static final long serialVersionUID = 1005L;

	private Medisin medisin;
	private int reseptNr, reit;
        private Calendar dato;
	private Lege lege;	
	private String beskrivelse;
	private boolean utlevert;

	public Resept(Medisin m, Calendar d, Lege l, int r, int rNr, String b)
	{
		medisin = m;
		dato = d;
		lege = l;
		reit = r;
		reseptNr = rNr;
		beskrivelse = b;
		utlevert = false;
	}

	public int getReseptNr()
        {
            return reseptNr;
        }
        public Medisin getMedisin(){
		return medisin;
	}
	public Calendar getDato()
	{
		return dato;
	}
	public Lege getLege()
	{
                return lege;
	}
	public int getReit()
	{
                return reit;
	}
	public String getBeskrivelse()
	{
			return beskrivelse;
	}
	public boolean getUtlevert()
	{
			return utlevert;
	}
        
        public void setUtlevert()
        {
            utlevert = true;
        }
	public String toString()
	{
		String ut = "Reseptnr: " + reseptNr + "\nDato: ";
                
                DateFormat datoformat = DateFormat.getDateInstance();
                ut += datoformat.format(dato.getTime());
                
                ut += "\nPreparat: " + medisin.toString()
                        + "\nBeskrivelse: " + beskrivelse + "\nLege: " + lege.toString()
                        + "\nReit: " + reit + "\nUtlevert: ";
                
                if(utlevert)
                    ut += "Ja";
                else
                    ut += "Nei";
                
                return ut;
	}
}