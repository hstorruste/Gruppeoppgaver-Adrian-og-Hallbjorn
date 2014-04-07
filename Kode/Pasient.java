/*	Denne klassen er en Pasientpost som er subklassen av Person.
		Den har en Reseptliste over alle resepter som er forskrevet
		den enkelte pasient.
		Laget av Hallbjørn Storruste
		Siste versjon 03-04-2014*/

import java.io.*;
import java.util.*;

public class Pasient extends Person implements Serializable
{
	private static final long serialVersionUID = 1002L;

	String fnr;
	SortedSet<Resept> reseptliste;
	public Pasient(String fnavn, String enavn, String f)
	{
		super(fnavn, enavn);

		fnr = f;

		reseptliste = new TreeSet<>();
	}

	public String getFnr()
	{
		return fnr;
	}

	
	public SortedSet<Resept> getReseptliste()
	{
		return reseptliste;
	}
	

        @Override
	public String toString()
	{
		return super.toString() + "Fødselsnummer: " + fnr + "\n";
	}
}