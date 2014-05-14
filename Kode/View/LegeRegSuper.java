/*Dette er en abstrakt superklasse for LegekontorVindu og AdminVindu.  
 Ved at de er subklasser av denne klassen vil de begge kunne bruke 
 LegeRegistrer som er et JPanel som tar imot et LegeRegSuper - objekt.
 Den inneholder tre abstrakte metoder som må implementeres av subklassene.
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
