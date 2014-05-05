package View;
/*  Denne klassen er et GUI til apotek.
 Laget av Adrian Westlund s198571.
 Siste versjon 22-04-2014*/

import Controller.Legeregister;
import Controller.Medisinregister;
import Controller.Pasientregister;
import Model.Pasient;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;

public class ApotekVindu extends JFrame {

    private JComponent GUI;
    private Pasient kund;

    private Legeregister legeregister;
    private Pasientregister pasientregister;
    private Medisinregister medisinregister;

    private final int BREDDE = 700;
    private final int HØYDE = 500;

    public ApotekVindu() {
        super("Apotek");

        setLayout(new GridLayout(0, 1));

        GUI = new ApotekFinnPasient(this);

        add(GUI);

        lesFil();

        Komponent.endreFont(this);
        Komponent.bilde(this);
        setSize(BREDDE, HØYDE);
        setVisible(true);
    }

    //Returnerer pasientobjektet.
    public Pasient getKund() {
        return kund;
    }

    public Pasient hittad(String fnr) {
        Pasient hittad = pasientregister.finnPasientFnr(fnr);
        if (hittad == null) {
            return null;
        } else {
            return hittad;
        }
    }

    public void tegnApotekPasientGUI(Pasient kund) {

        this.kund = kund;
        String kundNavn = this.kund.getNavn();

        String nyTittel = getTitle() + " - " + kundNavn;
        setTitle(nyTittel);

        remove(GUI);

        GUI = new ApotekPasient(this);

        add(GUI);
        Komponent.endreFont(this);
        repaint();
    }
    
    /*Skriver lmedisinregister, legeregister og pasientregister til fil.*/
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
    
    /*Leser medisinregister, legeregister og pasientregister fra fil.*/
    public void lesFil() {
        try (ObjectInputStream innfil = new ObjectInputStream(new FileInputStream(Komponent.dataFil))) {
            medisinregister = (Medisinregister) innfil.readObject();
            legeregister = (Legeregister) innfil.readObject();
            pasientregister = (Pasientregister) innfil.readObject();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Oppretter tom liste");
        } catch (FileNotFoundException fnfe) {
            System.out.println("Finner ikke fil. Oppretter tom liste");
        } catch (IOException ioe) {
            System.out.println("Leseproblemer. Oppretter tom liste");
            System.out.println(ioe.getMessage());
        }
    }

    //Opretter tomme lister
    private void opprettTommeLister() {
        medisinregister = new Medisinregister();
        legeregister = new Legeregister();
        pasientregister = new Pasientregister();
    }
}
