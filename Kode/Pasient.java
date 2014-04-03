/*	Denne klassen er en Pasientpost som er subklassen av Person.
		Den har en Reseptliste over alle resepter som er forskrevet
		den enkelte pasient.
		Laget av Hallbjørn Storruste
		Siste versjon 03-04-2014*/

import java.io.*;

public class Pasient extends Person implements Serializable
{
	private static final long serialVersionUID = 1002L;

	long fnr;
	//SortedSet<Resept> Reseptliste;
	public Pasient(String fnavn, String enavn, long f)
	{
		super(fnavn, enavn);

		fnr = f;

		//Reseptliste = new TreeSet<>();
	}

	public long getFnr()
	{
		return fnr;
	}

	/*
	public TreeSet<Resept> getReseptliste()
	{
		return Reseptliste;
	}
	*/

	public String toString()
	{
		return super.toString() + "Fødselsnummer: " + fnr + "\n";
	}
}