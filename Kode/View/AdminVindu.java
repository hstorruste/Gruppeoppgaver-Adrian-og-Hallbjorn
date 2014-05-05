package View;

import Model.*;
import Controller.*;
import java.awt.*;
import java.io.*;
import java.util.TreeSet;
import javax.swing.*;

/*Denne klassen er et GUI til admin. 
 Upprättar AdminLege och AdminMedisin.
 Laget av Adrian Westlund s198571.
 Siste versjon 29-04-2014*/
public class AdminVindu extends LegeRegSuper {

    private JTabbedPane legeGUI, medisinGUI;
    private Medisinregister medisinregister;
    private Legeregister legeregister;
    private Pasientregister pasientregister;

    public AdminVindu(Legeregister lreg, Pasientregister preg, Medisinregister mreg) {
        super("Admin");

        setLayout(new GridLayout(0, 1, 5, 5));
       
        legeregister = lreg;
        pasientregister = preg;
        medisinregister = mreg;
        //lesFil();
        
        legeGUI = new AdminLege(this);
        medisinGUI = new AdminMedisin(this);

        JTabbedPane gruppFane = new JTabbedPane();
        gruppFane.addTab("Lege", legeGUI);
        gruppFane.addTab("Medisin", medisinGUI);

        add(gruppFane);
        
        Komponent.endreFont(this);
        Komponent.bilde(this);
        pack();
        setVisible(true);
    }

    //Använder Legeregister sin finnLegeEpost och retunerar ett Lege objekt
    public Lege finnLege(String epost) {
        return legeregister.finnLegeEpost(epost);
    }

    //Registrerer lege med Legeregister sin settInn metode.
    @Override
    public boolean registrerLege(String fornavn, String etternavn, String ep,
            String gadresse, int pNr, String psted, String as, char[] pass) {

        return legeregister.settInn(fornavn, etternavn, ep, gadresse, pNr, psted, as, pass);
    }

    //Skriver Legeregister sin toString()
    public String skrivLegeListe() {
        return legeregister.toString();
    }

    //Använder Medisinregister sin finnMedisin för att hitta en medisin
    public Medisin finnMedisin(String navn) {
        return medisinregister.finnMedisinNavn(navn);
    }

    //Registrer medisin med Medisinregister sin settInn metode
    public boolean registrerMedisin(String navn, String kat, String gruppe, String atcNr) {

        return medisinregister.settInn(navn, kat, gruppe, atcNr);
    }
    public String[] skrivKatArray(){
        return medisinregister.getAlleKategorier();
    }

    //Skriver Medisinregister sin toString()
    public String skrivMedisinListe() {
        return medisinregister.toString();
    }
    /*Skriver medisinregister, legeregister og pasientregister til fil.*/

    @Override
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
    /*Leser medisinregister, legeregister og pasientregister fra fil.*/

   /* @Override
    public void lesFil() {
        try(ObjectInputStream innfil = new ObjectInputStream( new FileInputStream(Komponent.dataFil)))
        {
            medisinregister = (Medisinregister)innfil.readObject();
            legeregister = (Legeregister)innfil.readObject();
            pasientregister = (Pasientregister)innfil.readObject();
        }catch (ClassNotFoundException cnfe) {
            System.out.println("Oppretter tom liste");      
        } catch (FileNotFoundException fnfe) {
            System.out.println("Finner ikke fil. Oppretter tom liste");
        } catch (IOException ioe) {
            System.out.println("Leseproblemer. Oppretter tom liste");
            System.out.println(ioe.getMessage());
        }
    }*/
}
