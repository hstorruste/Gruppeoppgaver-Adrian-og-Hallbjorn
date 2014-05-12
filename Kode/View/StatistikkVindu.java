package View;

import Controller.*;
import Model.*;
import View.util.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class StatistikkVindu extends JFrame {

    private JPanel medisinHistorikkGUI, infoLegerGUI, statistikkGUI;
    private Medisinregister medisinregister;
    private Legeregister legeregister;
    private Pasientregister pasientregister;

    public StatistikkVindu(Legeregister lreg, Pasientregister preg, Medisinregister mreg) {
        super("Statistikk");

        setLayout(new GridLayout(0, 1, 5, 5));

        legeregister = lreg;
        pasientregister = preg;
        medisinregister = mreg;

        medisinHistorikkGUI = new StatistikkMedHistorikk(this);
        infoLegerGUI = new StatistikkInfoLeger(this);
        statistikkGUI = new Statistikk(this);

        JTabbedPane gruppFane = new JTabbedPane();
        gruppFane.addTab("Medisinhistorikk", medisinHistorikkGUI);
        gruppFane.addTab("Info om leger", infoLegerGUI);
        gruppFane.addTab("Statistikk", statistikkGUI);

        add(gruppFane);

        Komponent.endreFont(this);
        Komponent.bilde(this);
        pack();
        setVisible(true);
    }

    //Retunerer alle kategorier.
    public String[] getAlleKategori() {
        return medisinregister.getAlleKategorier();
    }

    //Retunerer alle kategorier i en bestemd gruppe.
    public String[] getAlleKategoriGruppe(String gruppe) {
        return medisinregister.getAlleKategorieriGruppe(gruppe);
    }

    //Returnerer medisinregisteret.
    public String[] getAlleMedisinnavn() {
        return medisinregister.getAlleMedisinnavn();
    }

    //Retunerer alle medisiner i en bestemd gruppe.
    public String[] getAlleMedisinnavnGruppe(String gruppe) {
        return medisinregister.getAlleMedisinnavniGruppe(gruppe);
    }

    //Retunerer alle medisiner i en bestemd kategori.
    public String[] getAlleMedisinnavnKategori(String kat) {
        return medisinregister.getAlleMedisinnavniKategori(kat);
    }

    //Retunerer alle medisiner i en bestemd gruppe og kategori.
    public String[] getAlleMedisinnavnGruppeKategori(String gruppe, String kat) {
        return medisinregister.getAlleMedisinnavniGruppeKategori(gruppe, kat);
    }

    //Retunerer alle medisiner med et bestemt navn.
    public Medisin[] finnMedisinNavn(String medisinNavn) {
        return medisinregister.finnMedisinNavn(medisinNavn);
    }

    //Retunerer alle medisiner med et bestemt kategori.
    public Medisin[] finnMedisinKategori(String kategoriNavn) {
        return medisinregister.finnMedisinKategori(kategoriNavn);
    }
    
    /*Retunerer alle leger som har skrivit ut resept på medisinene i arrayet.
     **Om man sender med null får man alle leger.
     */
    public Lege[] getLegeListe(Medisin[] medisiner) {
        if (medisiner == null) {
            return legeregister.getAlleLeger();
        } else {
            return legeregister.finnLegeMedisin(medisiner);
        }
    }

    //Retunerer alle leger som overensstemmer med navn.
    public Lege[] finnLegeNavn(String etternavn, String fornavn) {
        return legeregister.finnLegeNavn(etternavn, fornavn);
    }

    /*Retunerer alle leger som har skrivit ut resept på medisinene i arrayet
     **og som overensstemmer med navn.
     */
    public Lege[] finnLegeNavn(String etternavn, String fornavn, Medisin[] medisin) {
        return legeregister.finnLegeNavn(etternavn, fornavn, medisin);
    }

    /*Retunerer alle pasienter som har fått resept på medisinene i arrayet.
     **Om man sender med null får man alle pasienter.
     */
    public Pasient[] getPasientListe(Medisin[] medisiner) {
        if (medisiner == null) {
            return pasientregister.getAllePasienter();
        } else {
            return pasientregister.finnPasientMedisin(medisiner);
        }
    }
    
    //Retunerer alle pasienter som overensstemmer med navn.
    public Pasient[] finnPasientNavn(String etternavn, String fornavn){
        return pasientregister.finnPasientNavn(etternavn, fornavn);
    }
    
    /*Retunerer alle pasienter som har fått resept på medisinene i arrayet
    **og som overensstemmer med navn.
    */
    public Pasient[] finnPasientNavn(String etternavn, String fornavn, Medisin[] medisin){
        return pasientregister.finnPasientNavn(etternavn, fornavn, medisin);
    }

    public void skrivTilFil() {
        try (ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream(Komponent.dataFil))) {
            utfil.writeObject(medisinregister);
            utfil.writeObject(legeregister);
            utfil.writeObject(pasientregister);
        } catch (NotSerializableException nse) {
            System.out.println("Objektet er ikke serialisert!");
            System.out.println(nse.getMessage());
        } catch (IOException ioe) {
            System.out.println("Problemer med utskrift til fil");
            System.out.println(ioe.getMessage());
        }
    }
}
