package View;
/**Dette er en GUI for Legekontorvinduet arver JFrame-klassen.
 * Laget av Hallbjørn Storruste s165519
 * Siste versjon 28-04-2014
 *
 */
import Model.*;
import Controller.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;


public class LegekontorVindu extends LegeRegSuper
{
        private JTabbedPane GUI;
        private Lege innlogget;
        private Pasient pasient;
        
        private Legeregister legeregister;

	public LegekontorVindu()
	{
		super("Legekontor");

		setLayout(new GridLayout(0, 1));

                GUI = new LegekontorLogin(this);
                
                add(GUI);
                
                legeregister = new Legeregister();
                
                Komponent.endreFont(this);
                Komponent.bilde(this);
                pack();
		setVisible(true);
	}
        
        /*Denne metoden brukes for å logge inn. 
        Den sjekker om personen eksisterer i registeret, og returnerer
        null hvis den ikke gjør det.
        Deretter sjekker den om det er riktig passord, og returnerer null hvis
        det ikke er det. Hvis alt derimot stemmer returneres legeobjektet*/
        public Lege login(String epost, char[] passord)
        {
            Lege funnet = legeregister.finnLegeEpost(epost);
            if(funnet == null)
                return null;
            
            boolean riktig = legeregister.riktigPassord(epost, passord);
            if(!riktig)
                return null;
            else
                return funnet;        
        }
        /*Setter inn en ny lege i registeret. 
        Returnerer true hvis det er vellykket*/
        public boolean registrerLege(String fornavn, String etternavn, String ep, 
                String gadresse, int pNr, String psted, String as, char[] pass)
        {
            return legeregister.settInn(fornavn, etternavn, ep, 
                    gadresse, pNr, psted, as, pass);
        }
        /*Denne metoden repainter vinduet med finnPasientGUI,
        og sender med info om innlogget lege.*/
        public void tegnFinnPasientGUI(Lege innlogget)
        {
            
            this.innlogget = innlogget;
            String legenavn = this.innlogget.getNavn();
            String arbeidssted = this.innlogget.getArbetssted();
            if(arbeidssted.length() < 1)
                arbeidssted = this.innlogget.getGateadresse();
                        
            JLabel navn = new JLabel(legenavn);
            JLabel sted = new JLabel(arbeidssted);
            JPanel infoPanel = new JPanel(new BorderLayout(40,40));
            infoPanel.add(navn, BorderLayout.LINE_START);
            infoPanel.add(sted, BorderLayout.LINE_END);
            
            String nyTittel = getTitle() + navn;
            setTitle(nyTittel);
            
            remove(GUI);
            
            GUI = new LegekontorFinnPasient(this);
           
            add(GUI);
            pack();
            repaint();
        }
}
