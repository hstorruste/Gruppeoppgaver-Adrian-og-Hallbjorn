/*	Dette er en abstrakt superklasse som inneholder Fornavn og Etternavn
		laget av Hallbjørn Storruste*/
import java.io.*;

public abstract class Person implements Serializable
{
	private static final long serialVersionUID = 1001L;

	private String Fornavn, Etternavn;

	public Person(String fnavn, String enavn)
	{
		Fornavn = fnavn;
		Etternavn = enavn;
	}

	public String getFornavn()
	{
		return Fornavn;
	}

	public String getEtternavn()
	{
		return Etternavn;
	}

	public void setFornavn(String f)
	{
		Fornavn = f;
	}

	public void setEtternavn( String e)
	{
		Etternavn = e;
	}

	public String toString()
	{
		return "Fornavn: " + Fornavn + "\nEtternavn: " + Etternavn + "\n";
	}

}