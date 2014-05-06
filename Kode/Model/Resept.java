package Model;
/* Denne klassen er en Reseptpost.
	Klassen har en Medisinpeker som peker på medisinen for reseptet.
	Legepeker som peker på den lege som har skrivit reseptet.
	Laget av Adrian Westlund
	Siste versjon 06-05-2014*/

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
            if(reit == 0)
                utlevert = true;
            else
                reit--;
        }
	public String toString()
	{
            StringBuilder ut = new StringBuilder();
            ut.append("Reseptnr: ");
            ut.append(reseptNr);
            ut.append("\nDato: ");

            DateFormat datoformat = DateFormat.getDateInstance();
            ut.append(datoformat.format(dato.getTime()));
            if(medisin == null)
                ut.append("\nMedisinen finnes ikke!");
            else if(lege == null)
                ut.append("\nFinner ikke den forskrivende lege");
            else
            {
                ut.append("\nPreparat: ");
                ut.append(medisin.getNavn());
                ut.append(" ");
                ut.append(medisin.getStyrke());
                ut.append(", ");
                ut.append(medisin.getForm());
                ut.append("\nPakning: ");
                ut.append(medisin.getPakning());
                ut.append("\nBeskrivelse: ");
                ut.append(beskrivelse);
                ut.append("\nLege:\n");
                ut.append(lege.toString());
                ut.append("\nReit: ");
                ut.append(reit);
            }
            if(utlevert)
                ut.append("\nUtlevert: Ja\n");
            else
                ut.append("\nUtlevert: Nei\n");
 
            return ut.toString();
	}
}