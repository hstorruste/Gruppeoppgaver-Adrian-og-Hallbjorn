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

	//SortedSet<Medisin> medisin;
	Calendar dato;
	//SortedSet<Lege> lege;
	int reit;
	public static final int RESEPTNUMMER;
	String beskrivelse;
	boolean utlevert;

	public Resept(Calendar d, int r, )
	{
		//medisin = new TreeSet<>();
		dato = d;
		//lege = new TreeSet<>();
		reit = r;//Hær ær jag
	}

	public Calendar getBorn()
	{
		return born;
	}

	/*
	public TreeSet<Resept> getReseptliste()
	{
		return Reseptliste;
	}
	*/

	public String toString()
	{
		return super.toString() + "Født: " + born + "\n";
	}
}