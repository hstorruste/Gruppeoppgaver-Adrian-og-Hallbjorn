/*	Denne klassen er en Pasientpost som er subklassen av Person.
		Den har en Reseptliste over alle resepter som er forskrevet
		den enkelte pasient. Laget av Hallbjørn Storruste*/

import java.io.*;
import java.util.Calendar;

public class Pasient extends Person implements Serializable
{
	private static final long serialVersionUID = 1002L;

	Calendar born;
	//SortedSet<Resept> Reseptliste;
	public Pasient(String fnavn, String enavn, Calendar b)
	{
		super(fnavn, enavn);

		born = b;

		//Reseptliste = new TreeSet<>();
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