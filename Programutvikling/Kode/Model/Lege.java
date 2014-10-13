package Model;
/*	Denne klassen er en Legepost som er subklassen av Person.
 Laget av Adrian Westlund
 Siste versjon 09-04-2014*/

import Controller.Pasientregister;
import Model.Pasient;
import java.io.*;

public class Lege extends Person implements Serializable {

    private static final long serialVersionUID = 1003L;

    String gateadresse, poststed, arbetssted, ePost;
    String postnummer;
    boolean a, b, c;
    char[] passord;
    Pasientregister pasientliste;

    public Lege(String fnavn, String enavn, String ep, String g, String pNr,
            String ps, String as, char[] pass) {
        super(fnavn, enavn);

        ePost = ep;
        gateadresse = g;
        postnummer = pNr;
        poststed = ps;
        arbetssted = as;
        a = true;
        b = true;
        c = true;

        passord = pass;

        pasientliste = new Pasientregister();
    }

    public String getGateadresse() {
        return gateadresse;
    }

    public String getPoststed() {
        return poststed;
    }

    public String getArbetssted() {
        return arbetssted;
    }

    public String getEPost() {
        return ePost;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public boolean getA() {
        return a;
    }

    public boolean getB() {
        return b;
    }

    public boolean getC() {
        return c;
    }

    public char[] getPassord() {
        return passord;
    }

    public Pasientregister getPasientliste() {
        return pasientliste;
    }

    public void setGateadresse(String g) {
        gateadresse = g;
    }

    public void setPoststed(String p) {
        poststed = p;
    }

    public void setArbetssted(String as) {
        arbetssted = as;
    }

    public void setEPost(String e) {
        ePost = e;
    }

    public void setPostnummer(String pNr) {
        postnummer = pNr;
    }

    public void setA(boolean nyA) {
        a = nyA;
    }

    public void setB(boolean nyB) {
        b = nyB;
    }

    public void setC(boolean nyC) {
        c = nyC;
    }

    public void setPassord(char[] p) {
        passord = p;
    }

    //Setter inn ny pasient i legens eget pasientregister.
    public boolean settInnPasient(Pasient ny) {
        return pasientliste.settInn(ny);
    }

    public String toStringResept() {
        String ut = super.toString() + "E-post: " + ePost + "\n";
        if (!gateadresse.equals("")) {
            ut += "Gateadresse: " + gateadresse + "\n";
        }
        if (!postnummer.equals("")) {
            ut += postnummer + " " + poststed + "\n";
        }
        if (!arbetssted.equals("")) {
            ut += "Arbeidssted: " + arbetssted + "\n";
        }
        return ut;
    }

    public String toString() {
        String ut = toStringResept();
        ut += "Bevillning: ";
        if (a && (b || c)) {
            ut += "A, ";
        }
        if (a && !(b || c)) {
            ut += "A.";
        }
        if (b && c) {
            ut += "B, ";
        }
        if (b && !c) {
            ut += "B.";
        }
        if (c) {
            ut += "C.";
        }
        ut += "\n";
        return ut;
    }
}
