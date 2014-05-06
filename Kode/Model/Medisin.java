package Model;
/*	Denne klassen er en medisinpost.
	Laget av Adrian Westlund og Hallbjørn Storruste
	Siste versjon 06-05-2014*/

import java.io.*;

public class Medisin implements Serializable
{
	private static final long serialVersionUID = 1004L;

	private String navn, kategori, gruppe, styrke, form, pakning, atcNr;

	public Medisin(String navn, String kategori, String gruppe, String styrke, String form, String pakning, String atcNr)
	{
		this.navn = navn;
                this.kategori = kategori;
                this.gruppe = gruppe;
                this.styrke = styrke;
                this.form = form;
                this.pakning = pakning;
		this.atcNr = atcNr;
	}

	public String getGrupp()
	{
		return gruppe;
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
        public String getStyrke()
        {
            return styrke;
        }
        public String getForm()
        {
            return form;
        }
        public String getPakning()
        {
            return pakning;
        }
	public void setGrupp(String g)
	{
		gruppe = g;
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
        public void setStyrke(String s)
        {
            styrke = s;
        }
        public void setForm(String f)
        {
            form = f;
        }
        public void setPakning(String p)
        {
            pakning = p;
        }
        
	public String toString()
	{
            StringBuilder tekst = new StringBuilder();
            tekst.append("Medisin: ");
            tekst.append(navn);
            tekst.append("\nKategori: ");
            tekst.append(kategori);
            tekst.append("\nGrupp: ");
            tekst.append(gruppe);   
            tekst.append("\nStyrke: ");
            tekst.append(styrke);
            tekst.append("\nLegemiddelform: ");
            tekst.append(form);
            tekst.append("\nPakningsstr: ");
            tekst.append(pakning);
            tekst.append("\nATC-nr: ");
            tekst.append(atcNr);
            tekst.append("\n");
            
            return  tekst.toString();
	}
}//End of class Medisin