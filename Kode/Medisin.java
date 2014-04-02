/*	Denne klassen er en medisinpost.
	Laget av Adrian Westlund
	Siste versjon 02-04-2014*/

import java.io.*;

public class Medisin implements Serializable
{
	private static final long serialVersionUID = 1004L;

	String Grupp, Kategori, Navn, ATCNr;

	public Medisin(String g, String k, String n, String a)
	{
		Grupp = g;
		Kategori = k;
		Navn = n;
		ATCNr = a;
	}

	public String getGrupp()
	{
		return Grupp;
	}
	public String getKategori()
	{
		return Kategori;
	}
	public String getNavn()
	{
		return Navn;
	}
	public String getATCNr()
	{
		return ATCNr;
	}

	public void setGrupp(String g)
	{
		Grupp = g;
	}
	public void setKategori(String k)
	{
		Kategori = k;
	}
	public void setNavn(String n)
	{
		Navn = n;
	}
	public void setATCNr(String a)
	{
		ATCNr = a;
	}

	public String toString()
	{
		return "Grupp: " + Grupp + "\nKategori: " + Kategori + "\nMedisin: " + Navn + "\nATC-nr: " + ATCNr;
	}
}//End of class Medisin