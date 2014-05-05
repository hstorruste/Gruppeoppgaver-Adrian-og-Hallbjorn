package View;
/**Dette er en GUI for Legekontorvinduet arver JFrame-klassen.
 * Laget av Hallbjørn Storruste s165519
 * Siste versjon 29-04-2014
 *
 */
import Model.*;
import Controller.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.TreeSet;
import javax.swing.*;


public class LegekontorVindu extends LegeRegSuper
{
        private JTabbedPane GUI;
        private Lege innlogget;
        private Pasient pasient;
        
        private Legeregister legeregister;
        private Pasientregister pasientregister;
        private Medisinregister medisinregister;
        private final int BREDDE = 800;
        private final int HOYDE = 600;
        private final String tittel = "Legekontor";

	public LegekontorVindu(Legeregister lreg, Pasientregister preg, Medisinregister mreg)
	{
		super("Legekontor");

		setLayout(new GridLayout(0, 1));
                tegnLoginGUI();
                
                legeregister = lreg;
                pasientregister = preg;
                medisinregister = mreg;
                //lesFil();
                
                Komponent.bilde(this);
                setSize(BREDDE, HOYDE);
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
                String gadresse, String pNr, String psted, String as, char[] pass)
        {
            return legeregister.settInn(fornavn, etternavn, ep, 
                    gadresse, pNr, psted, as, pass);
        }
        /*Denne metoden tar imot objektet til legen som er innlogget, setter
        tittelen på vinduet på nytt med legens navn og repainter vinduet med 
        finnPasientGUI.*/
        public void tegnFinnPasientGUI(Lege innlogget)
        {
            
            this.innlogget = innlogget;
            String legenavn = this.innlogget.getNavn();
            
            String nyTittel = getTitle() + " - " + legenavn;
            setTitle(nyTittel);
            
            remove(GUI);
            
            GUI = new LegekontorFinnPasient(this);
           
            add(GUI);
            Komponent.endreFont(this);
            repaint();
        }
        //Finner en pasient på fødselsnummer. Returnerer pasientobjektet.
        public Pasient finnPasient(String fnr)
        { 
            return pasientregister.finnPasientFnr(fnr);
        }
        
        /*Setter inn en pasient i pasientregisteret. Hvis dette er vellykket 
        settes en peker til objektet inn i legen sitt eget pasientregister. 
        Returnerer false hvis en av innsettingene mislykkes.*/
        public boolean registrerPasient(String fornavn, String etternavn, String fnr)
        {
            boolean registrert = pasientregister.settInn(fornavn, etternavn, fnr);
            if(registrert)
            {
                Pasientregister egen = innlogget.getPasientliste();
                Pasient ny = pasientregister.finnPasientFnr(fnr);
                registrert = egen.settInn(ny);
            }
            return registrert;
        }
        
        //Returnerer pasientobjektet. (Pasienten det skal skrives ut resept til)
        public Pasient getPasient(){
            return pasient;
        }
        /*Denne metoden tar imot objektet til pasienten som det skal skrives ut
        resept til og repainter vinduet med skrivReseptGUI. Den sørger også for 
        at pasienten finnes i legens eget register.*/
        public void tegnSkrivReseptGUI(Pasient pasient){
            this.pasient = pasient;
            
            Pasientregister egen = innlogget.getPasientliste();
            Pasient alleredePasient = egen.finnPasientFnr(pasient.getFnr());
            
            if(alleredePasient == null) //Sjekker om pasienten er kunde hos legen
            {
                egen.settInn(pasient);
                skrivTilFil();
            }

            
            remove(GUI);
            
            GUI = new LegekontorSkrivResept(this);
            
            add(GUI);
            Komponent.endreFont(this);
            repaint();
        }
        
        //Returnerer medisinregisteret.
        public String[] getAlleMedisinnavn()
        {
            return medisinregister.getAlleMedisinnavn();
        }
        
        //Returnerer en String med alle resepter for pasienten.
        public String getPasientHistorikk()
        {
            Reseptregister reseptregister = pasient.getReseptliste();
            String historikk = reseptregister.toString();
            return historikk;
        }
        
        //Returnerer lege-objektet til den innloggete legen.
        public Lege getLege()
        {
            return innlogget;
        }
        
        //Finner og returnerer et medisinobjekt
        public Medisin finnMedisin(String navn)
        {
            Medisin funnet = medisinregister.finnMedisinNavn(navn);
            return funnet;
        }
        //Registrerer en resept på pasienten
        public boolean registrerResept(Medisin medisin, Calendar d, Lege lege, int reit, String beskrivelse)
        {
           return pasient.settInnResept(medisin, d, lege, reit, beskrivelse);
        }
        
        public void tegnLoginGUI()
        {
            if(GUI != null)
                remove(GUI);
            GUI = new LegekontorLogin(this);
            add(GUI);
            setTitle(tittel);
            Komponent.endreFont(this);
        }
        /*Skriver lmedisinregister, legeregister og pasientregister til fil.*/
        public void skrivTilFil()
        {
            try(ObjectOutputStream utfil = new ObjectOutputStream( new FileOutputStream(Komponent.dataFil)))
            {
                utfil.writeObject(medisinregister);
                utfil.writeObject(legeregister);
                utfil.writeObject(pasientregister);
            }
            catch( NotSerializableException nse)
            {
                System.out.println("Objektet er ikke serialisert!");
                System.out.println(nse.getMessage());
            }
            catch( IOException ioe )
            {
                System.out.println("Problemer med utskrift til fil");
                System.out.println(ioe.getMessage());
            }
        }
        
        /*Leser medisinregister, legeregister og pasientregister fra fil.*/
        /*public void lesFil()
        {
            try(ObjectInputStream innfil = new ObjectInputStream( new FileInputStream(Komponent.dataFil)))
            {
                medisinregister = (Medisinregister)innfil.readObject();
                legeregister = (Legeregister)innfil.readObject();
                pasientregister = (Pasientregister)innfil.readObject();
            }
            catch(ClassNotFoundException cnfe)
            {
                System.out.println("Oppretter tom liste");
            }
            catch(FileNotFoundException fnfe)
            {
                System.out.println("Finner ikke fil. Oppretter tom liste");
            }
            catch(IOException ioe)
            {
                System.out.println("Leseproblemer. Oppretter tom liste");
                System.out.println(ioe.getMessage());
            }
        }*/
}
