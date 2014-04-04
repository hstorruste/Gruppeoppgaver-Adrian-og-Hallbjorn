/*	Denne klassen er en medisinpost.
	Laget av Adrian Westlund
	Siste versjon 02-04-2014*/

import java.io.*;

public class Medisin implements Serializable
{
	private static final long serialVersionUID = 1004L;

	String grupp, kategori, navn, atcNr;

	public Medisin(String g, String k, String n, String a)
	{
		grupp = g;
		kategori = k;
		navn = n;
		atcNr = a;
	}

	public String getGrupp()
	{
		return grupp;
	}
	public String getKategori()
	{
		return kategori;
	}
	public String getNavn()
	{
		return navn;
	}
	public String getATCNr()
	{
		return atcNr;
	}

	public void setGrupp(String g)
	{
		grupp = g;
	}
	public void setKategori(String k)
	{
		kategori = k;
	}
	public void setNavn(String n)
	{
		navn = n;
	}
	public void setATCNr(String a)
	{
		atcNr = a;
	}

	public String toString()
	{
		return "Grupp: " + grupp + "\nKategori: " + kategori + "\nMedisin: " + navn + "\nATC-nr: " + atcNr;
	}
}//End of class Medisin