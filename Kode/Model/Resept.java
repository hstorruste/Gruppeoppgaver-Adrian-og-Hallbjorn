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
        private final int opprinneligReit;
	private int reseptNr, resterendeReit;
        private Calendar dato;
	private Lege lege;	
        private Pasient pasient;
	private String beskrivelse;
	private boolean utlevert;

	public Resept(Medisin m, Calendar d, Lege l, Pasient p, int r, int rNr, String b)
	{
		medisin = m;
		dato = d;
		lege = l;
                pasient = p;
                opprinneligReit = r;
		resterendeReit = r;
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
        public Pasient getPasient()
        {
            return pasient;
        }
        public int getOpprinneligReit()
        {
            return opprinneligReit;
        }
	public int getResterendeReit()
	{
                return resterendeReit;
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
            if(resterendeReit == 0)
                utlevert = true;
            else
                resterendeReit--;
        }
	public String toString()
	{
            StringBuilder ut = new StringBuilder();
            ut.append("RESEPTNUMMER: ");
            ut.append(reseptNr);
            ut.append("\nFor: ");
            ut.append(pasient.getNavn());
            ut.append("\t");
            ut.append(pasient.getFnr());
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
                ut.append(lege.toStringResept());
                ut.append("\nOpprinnelig reit: ");
                ut.append(opprinneligReit);
                if(opprinneligReit > 0){
                    ut.append("\nGjennværende Reit: ");
                    ut.append(resterendeReit);
                }
            }
            if(utlevert)
                ut.append("\nUtlevert: Ja\n");
            else
                ut.append("\nUtlevert: Nei\n");
 
            return ut.toString();
	}
}