/*	Dette er en abstrakt superklasse som inneholder Fornavn og Etternavn
		Laget av Hallbjørn Storruste
		Siste versjon 03-04-2014*/
import java.io.*;

public abstract class Person implements Serializable
{
	private static final long serialVersionUID = 1001L;

	private String fornavn, etternavn;

	public Person(String fnavn, String enavn)
	{
		fornavn = fnavn;
		etternavn = enavn;
	}

	public String getFornavn()
	{
		return fornavn;
	}

	public String getEtternavn()
	{
		return etternavn;
	}

	public void setFornavn(String f)
	{
		fornavn = f;
	}

	public void setEtternavn( String e)
	{
		etternavn = e;
	}

	public String toString()
	{
		return "Navn: " + etternavn + ", " + fornavn + "\n";
	}

}