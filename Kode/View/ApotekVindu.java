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
    /*Skriver medisinregister, legeregister og pasientregister til fil.*/

    public void skrivTilFil() {
        try (ObjectOutputStream pasientfil = new ObjectOutputStream(new FileOutputStream(Komponent.pasientFil));
                ObjectOutputStream legefil = new ObjectOutputStream(new FileOutputStream(Komponent.legeFil));
                ObjectOutputStream medisinfil = new ObjectOutputStream(new FileOutputStream(Komponent.medisinFil))) {
            medisinfil.writeObject(medisinregister);
            legefil.writeObject(legeregister);
            pasientfil.writeObject(pasientregister);
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
        try (ObjectInputStream medisinfil = new ObjectInputStream(new FileInputStream(Komponent.medisinFil));
                ObjectInputStream legefil = new ObjectInputStream(new FileInputStream(Komponent.legeFil));
                ObjectInputStream pasientfil = new ObjectInputStream(new FileInputStream(Komponent.pasientFil))) {
            medisinregister = (Medisinregister) medisinfil.readObject();
            legeregister = (Legeregister) legefil.readObject();
            pasientregister = (Pasientregister) pasientfil.readObject();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Oppretter tom liste");
            opprettTommeLister();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Finner ikke fil. Oppretter tom liste");
            opprettTommeLister();
        } catch (IOException ioe) {
            System.out.println("Leseproblemer. Oppretter tom liste");
            System.out.println(ioe.getMessage());
            opprettTommeLister();
        }
    }

    //Opretter tomme lister
    private void opprettTommeLister() {
        medisinregister = new Medisinregister();
        legeregister = new Legeregister();
        pasientregister = new Pasientregister();
    }
}
