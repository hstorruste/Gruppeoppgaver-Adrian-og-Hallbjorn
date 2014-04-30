package Model;
/*	Denne klassen er en medisinpost.
	Laget av Adrian Westlund
	Siste versjon 02-04-2014*/

import java.io.*;

public class Medisin implements Serializable
{
	private static final long serialVersionUID = 1004L;

	private String navn, kategori, grupp, atcNr;

	public Medisin(String n, String k, String g, String a)
	{
		navn = n;
                kategori = k;
                grupp = g;
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
		return "Grupp: " + grupp + "\nKategori: " + kategori + "\nMedisin: " + navn + "\nATC-nr: " + atcNr + "\n";
	}
}//End of class Medisin