package Model;

/*	Denne klassen er en Pasientpost som er subklassen av Person.
 I tillegg til fornavn og etternavn, som Person inneholder har pasient et
 fødselsnummer som den tar imot i konstruktøren.
 Den har en Reseptliste over alle resepter som er forskrevet
 den enkelte pasient.
 Laget av Hallbjørn Storruste
 Siste versjon 09-04-2014*/
import Controller.Reseptregister;
import java.io.*;
import java.util.Calendar;

public class Pasient extends Person implements Serializable {

    private static final long serialVersionUID = 1002L;

    private String fnr;

    private Reseptregister reseptliste;

    public Pasient(String fnavn, String enavn, String f) {
        super(fnavn, enavn);

        fnr = f;

        reseptliste = new Reseptregister();
    }

    public String getFnr() {
        return fnr;
    }

    public int getAntallResepter() {
        return reseptliste.getAntallResepter();
    }

    //Returnerer pasienten sitt reseptregister.
    public Reseptregister getReseptliste() {
        return reseptliste;
    }

    @Override
    public String toString() {
        return super.toString() + "Fødselsnummer: " + fnr + "\n";
    }
}
