/*Dette er en GUI for apotek.
 Laget av Adrian Westlund s198571.
 Siste versjon 14-05-2014*/
package View;

import View.util.Komponent;
import Controller.*;
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

    //Returnerer pasient objektet.
    public Pasient getKund() {
        return kund;
    }

    //Finner en pasient på fødselsnummer. 
    public Pasient hittad(String fnr) {
        Pasient hittad = pasientregister.finnPasientFnr(fnr);
        if (hittad == null) {
            return null;
        } else {
            return hittad;
        }
    }

    //Repainter vinduet med finnPasientGUI.
    public void tegnFinnPasientGUI() {
        if (GUI != null) {
            remove(GUI);
        }
        GUI = new ApotekFinnPasient(this);
        add(GUI);
        setTitle(tittel);
        Komponent.endreFont(this);
    }

    /*Tar imot objektet til pasientet som er funnen, setter
     tittelen på vinduet på nytt med pasientens navn og repainter vinduet med 
     apotekPasientGUI.*/
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

    //Skriver medisinregister, legeregister og pasientregister til fil.
    public void skrivTilFil() {
        try (ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream(Komponent.dataFil))) {
            utfil.writeObject(medisinregister);
            utfil.writeObject(legeregister);
            utfil.writeObject(pasientregister);
        } catch (NotSerializableException nse) {
            String melding = "Objektet er ikke serialisert!\n" + nse.getMessage();
            Komponent.popup(this, melding);
        } catch (IOException ioe) {
            String melding = "Problemer med utskrift til fil\n" + ioe.getMessage();
            Komponent.popup(this, melding);
        }
    }
}
