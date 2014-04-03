/*	Denne klassen er en Reseptpost.
	Klassen har en Medisinpeker som peker p� medisinen for reseptet.
	Legepeker som peker p� den lege som har skrivit reseptet.
	Laget av Adrian Westlund
	Siste versjon 03-04-2014*/

import java.io.*;
import java.util.Calendar;

public class Resept implements Serializable
{
	private static final long serialVersionUID = 1005L;

	Medisin medisin;
	Calendar dato;
	Lege lege;
	int reit;
	private int reseptNr;
	String beskrivelse;
	boolean utlevert;

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

	/*public String toString()
	{
		return ;
	}*/
}