/*	Denne klassen er en Legepost som er subklassen av Person.
	Laget av Adrian Westlund
	Siste versjon 02-04-2014*/

import java.io.*;

public class Lege extends Person implements Serializable
{
	private static final long serialVersionUID = 1003L;

	String Gateadresse, Poststed, Arbetssted, EPost;
	int Postnummer;
	boolean A,B,C;
	//SortedSet<Pasient> Pasientliste;

	public Lege(String fnavn, String enavn, String gate, String pSted, String aSted,
				String ePost, int pNr)
	{
		super(fnavn, enavn);

		Gateadresse = gate;
		Poststed = pSted;
		Arbetssted = aSted;
		EPost = ePost;
		Postnummer = pNr;
		A = true;
		B = true;
		C = true;
		//Pasientliste = new TreeSet<>();
	}

	public String getGateadresse()
	{
		return Gateadresse;
	}
	public String getPoststed()
	{
		return Poststed;
	}
	public String getArbetssted()
	{
		return Arbetssted;
	}
	public String getEPost()
	{
		return EPost;
	}
	public int getPostnummer()
	{
		return Postnummer;
	}
	public boolean getA()
	{
		return A;
	}
	public boolean getB()
	{
		return B;
	}
	public boolean getC()
	{
		return C;
	}
	/*
	public TreeSet<Pasient> getPasientliste()
	{
		return Pasientliste;
	}
	*/

	public void setGateadresse(String g)
	{
		Gateadresse = g;
	}
	public void setPoststed(String p)
	{
		Poststed = p;
	}
	public void setArbetssted(String as)
	{
		Arbetssted = as;
	}
	public void setEPost(String e)
	{
		EPost = e;
	}
	public void setPostnummer(int pNr)
	{
		Postnummer = pNr;
	}
	public void setA(boolean nyA)
	{
		A = nyA;
	}
	public void setB(boolean nyB)
	{
		B = nyB;
	}
	public void setC(boolean nyC)
	{
		C = nyC;
	}

	public String toString()
	{
		String ut = super.toString() + "E-post: " + EPost + "\n";
					if(!Gateadresse.equals(""))
						ut += "Gateadresse: " + Gateadresse + " " + Postnummer + " " + Poststed + "\n";
					if(!Arbetssted.equals(""))
		   				ut += "Arbetssted: " + Arbetssted + "\n";
		   			ut += "Bevilning: ";
		   			if(A && (B || C))
		   				ut += "A, ";
		   			if(A && !(B || C))
		   				ut += "A.";
		   			if(B && C)
		   				ut += "B, ";
		   			if(B && !C)
		   				ut += "B.";
		   			if(C)
		   				ut += "C.";
		return ut;
	}
}