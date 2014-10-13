/*Dette er en abstrakt superklasse for LegekontorVindu og AdminVindu.  
 Den inneholder to abstrakte metoder som må implementeres av subklassene. 
 Subklasser av denne klassen vil da kunne bruke LegeRegistrer som tar imot 
 et LegeRegSuper - objekt. Dette er fordi LegeRegister benytter seg av 
 metodene som LegeRegSuper - og dermed også LegekontorVindu og AdminVindu har.
 
 Laget av Hallbjørn Storruste s165519
 Siste versjon 28-04-2014*/
package View;

import javax.swing.JFrame;

public abstract class LegeRegSuper extends JFrame {

    public LegeRegSuper(String tittel) {
        super(tittel);
    }

    public abstract boolean registrerLege(String fornavn, String etternavn, String ep,
            String gadresse, String pNr, String psted, String as, char[] pass);

    public abstract void skrivTilFil();
}
