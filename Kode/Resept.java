/*	Denne klassen er en Reseptpost.
	Den har en Medisinpeker som peker på den medisin som reseptet er av
	och en Legepeker på den lege som har skrivit respetet.
	Laget av Adrian Westlund
	Siste versjon 02-04-2014*/

import java.io.*;
import java.util.Calendar;

public class Resept implements Serializable
{
	private static final long serialVersionUID = 1005L;

	Medisin medisin;
	Calendar dato;
	Lege lege;
	int reit;
	//public static final int RESEPTNUMMER;//Kontrollera och behøvs den?
	String beskrivelse;
	boolean utlevert;

	public Resept(Medisin m, Calendar d, Lege l, int r, String b)
	{
		medisin = m;
		dato = d;
		lege = l;
		reit = r;
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