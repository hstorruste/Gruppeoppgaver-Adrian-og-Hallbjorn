package View;
/*  Denne klassen er et GUI til apotek.
 Laget av Adrian Westlund s198571.
 Siste versjon 05-05-2014*/

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

    private final int BREDDE = 800;
    private final int HØYDE = 600;
    private final String tittel = "Apotek";

    public ApotekVindu(Legeregister lreg, Pasientregister preg, Medisinregister mreg) {
        super("Apotek");

        setLayout(new GridLayout(0, 1));

        GUI = new ApotekFinnPasient(this);

        tegnFinnPasientGUI();

        legeregister = lreg;
        pasientregister = preg;
        medisinregister = mreg;
        
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
     public void tegnFinnPasientGUI()
        {
            if(GUI != null)
                remove(GUI);
            GUI = new ApotekFinnPasient(this);
            add(GUI);
            setTitle(tittel);
            Komponent.endreFont(this);
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
        try(ObjectOutputStream utfil = new ObjectOutputStream( new FileOutputStream(Komponent.dataFil)))
        {
            utfil.writeObject(medisinregister);
            utfil.writeObject(legeregister);
            utfil.writeObject(pasientregister);
        }
        catch (NotSerializableException nse) {
            System.out.println("Objektet er ikke serialisert!");
            System.out.println(nse.getMessage());
        } catch (IOException ioe) {
            System.out.println("Problemer med utskrift til fil");
            System.out.println(ioe.getMessage());
        }
    }
}