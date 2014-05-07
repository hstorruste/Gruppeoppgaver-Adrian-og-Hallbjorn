package View;

import Controller.*;
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
