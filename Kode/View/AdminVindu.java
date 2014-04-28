package View;

import Model.*;
import Controller.*;
import java.awt.*;
import javax.swing.*;

/*  Denne klassen er et GUI til admin.
 Laget av Adrian Westlund s198571.
 Siste versjon 09-04-2014*/
public class AdminVindu extends LegeRegSuper {

    private JTabbedPane legeGUI, medisinGUI;
    private Legeregister legeregister;

    public AdminVindu() {
        super("Admin");

        setLayout(new GridLayout(0, 1, 40, 40));

        legeGUI = new AdminLege(this);
        medisinGUI = new AdminMedisin(this);

        JTabbedPane gruppFane = new JTabbedPane();
        gruppFane.addTab("Lege", legeGUI);
        gruppFane.addTab("Medisin", medisinGUI);

        add(gruppFane);

        legeregister = new Legeregister();

        Komponent.endreFont(this);
        Komponent.bilde(this);
        pack();
        setVisible(true);
    }
    public Lege finnLege(String epost){
        return legeregister.finnLegeEpost(epost);
    }
    public String skrivListe(){
        return legeregister.toString();
    }
    public boolean registrerLege(String fornavn, String etternavn, String ep,
            String gadresse, int pNr, String psted, String as, char[] pass) {

        return legeregister.settInn(fornavn, etternavn, ep, gadresse, pNr, psted, as, pass);
    }
}
