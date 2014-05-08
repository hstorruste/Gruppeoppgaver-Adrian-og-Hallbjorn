package Controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Denne klassen legger medisiner inn i et register og gjør operasjoner mot den.
 * Laget av Hallbjørn Storruste 
 * Siste versjon: 06-05-2014
 *
 * @author Hallbjørn
 */
import Model.*;
import java.io.*;
import java.util.*;

public class Medisinregister implements Serializable {

    private static final long serialVersionUID = 1009L;
    private Comparator komp;
    private SortedSet<Medisin> medisinregister;

    public Medisinregister() {
        komp = new MedisinComparator();
        medisinregister = new TreeSet<>(komp);
    }
   
    //Returnerer medisinregisteret
    public SortedSet<Medisin> getMedisinregister()
    {
        return medisinregister;
    }
    
    /*Oppretter og setter inn et Medisin-objekt. Returnerer true hvis vellykket.*/
    public boolean settInn(String navn, String kat, String gruppe,
            String styrke, String form, String pakning, String actNr) {
        Medisin ny = new Medisin(navn, kat, gruppe, styrke, form, pakning, actNr);

        return settInn(ny);
    }
    /*Setter inn et nytt Medisin-objekt i medisinregisteret.*/

    public boolean settInn(Medisin ny) {
        if (ny == null) {
            return false;
        }
        return medisinregister.add(ny);
    }
    
    //Returnerer et Stringarray med alle medisinnavn.
    public String[] getAlleMedisinnavn()
    {
        int size = medisinregister.size();
        
        String[] navn = new String[size];
        int i =0;
        
        Iterator<Medisin> iterator = medisinregister.iterator();
        Medisin runner = null;
        while (iterator.hasNext()) {
            runner = iterator.next();
            String runnerNavn = runner.getNavn();
            for(int j = 0; j <= i; j++)
            {
                if(navn[j] == null)
                {
                    navn[i] = runner.getNavn();
                    i++;
                    break;
                }
                else
                    if(navn[j].equals(runnerNavn))
                        break;   
            } 
        }
        navn = Arrays.copyOfRange(navn, 0, i);
        return navn;
    }
    
    
    //Returnerer et Stringarray med alle medisinnavn innen en kategori.
    public String[] getAlleMedisinnavniKategori(String kategori)
    {
        TreeSet<Medisin> medSet = this.finnMedisiniKategori(kategori);
        String[] navn = this.finnMedisinnavn(medSet);
        
        return navn;
    }
    
    
    //Returnerer et Stringarray med alle medisinnavn innen en gruppe.
    public String[] getAlleMedisinnavniGruppe(String gruppe)
    {
        TreeSet<Medisin> medGruppe = this.finnMedisiniGruppe(gruppe);

        String[] navn = this.finnMedisinnavn(medGruppe);

        return navn;
    }
    
     //Returnerer et Stringarray med alle medisinnavn innen en gruppe og en kategori.
    public String[] getAlleMedisinnavniGruppeKategori(String gruppe, String kategori)
    {
        TreeSet<Medisin> medSet = this.finnMedisiniGruppe(gruppe);
        medSet = this.finnMedisiniKategori(kategori, medSet);
        String[] navn = this.finnMedisinnavn(medSet);
        
        return navn;
    }
    //Returnerer en Stringarray med alle pakninger innen en medisin, styrke og legemiddelform.
    public String[] getAlleMedisinPakninger(String navn, String styrke, String form)
    {
        TreeSet<Medisin> navnSett = finnMedisin(navn);
        
        int size = navnSett.size();
        
        String[] pakning = new String[size];
        int i =0;
        
        Iterator<Medisin> iterator = navnSett.iterator();
        Medisin runner = null;
        while (iterator.hasNext()) {
            runner = iterator.next();
            if(runner.getStyrke().equals(styrke) 
                    && runner.getForm().equals(form)){ 
                String runnerPakning = runner.getPakning();
                for(int j = 0; j <= i; j++)
                {
                    if(pakning[j] == null)
                    {
                        pakning[i] = runnerPakning;
                        i++;
                        break;
                    }
                    else
                        if(pakning[j].equals(runnerPakning))
                            break;   
                } 
            }
        }
        pakning = Arrays.copyOfRange(pakning, 0, i);
        return pakning;
    }
    //Returnerer en Stringarray med alle legemiddelformer innen en medisin og styrke.
    public String[] getAlleMedisinFormer(String navn, String styrke)
    {
        TreeSet<Medisin> navnSett = finnMedisin(navn);
        
        int size = navnSett.size();
        
        String[] form = new String[size];
        int i =0;
        
        Iterator<Medisin> iterator = navnSett.iterator();
        Medisin runner = null;
        while (iterator.hasNext()) {
            runner = iterator.next();
            if(runner.getStyrke().equals(styrke)){ 
                String runnerForm = runner.getForm();
                for(int j = 0; j <= i; j++)
                {
                    if(form[j] == null)
                    {
                        form[i] = runnerForm;
                        i++;
                        break;
                    }
                    else
                        if(form[j].equals(runnerForm))
                            break;   
                } 
            }
        }
        form = Arrays.copyOfRange(form, 0, i);
        return form;
    }
     //Returnerer et Stringarray med alle styrker innen en medisin.
    public String[] getAlleMedisinStyrker(String navn)
    {
        TreeSet<Medisin> navnSett = finnMedisin(navn);
        
        int size = navnSett.size();
        
        String[] styrke = new String[size];
        int i =0;
        
        Iterator<Medisin> iterator = navnSett.iterator();
        Medisin runner = null;
        while (iterator.hasNext()) {
            runner = iterator.next();
            String runnerStyrke = runner.getStyrke();
            for(int j = 0; j <= i; j++)
            {
                if(styrke[j] == null)
                {
                    styrke[i] = runnerStyrke;
                    i++;
                    break;
                }
                else
                    if(styrke[j].equals(runnerStyrke))
                        break;   
            } 
        }
        styrke = Arrays.copyOfRange(styrke, 0, i);
        return styrke;
    }
    
    //Returnerer et array med alle de ulike kategorinavnene til medisinene.
    public String[] getAlleKategorier()
    {
        int size = medisinregister.size();
        String[] kategorier = new String[size]; 
        int i = 0;
        
        Iterator<Medisin> iterator = medisinregister.iterator();
        Medisin runner = null;
        while (iterator.hasNext()) {
            runner = iterator.next();
            String runnerKat = runner.getKategori();
            for(int j = 0; j <= i; j++)
            {
                if(kategorier[j] == null)
                {
                    kategorier[j] = runner.getKategori();
                    i++;
                    break;
                }
                else
                    if(kategorier[j].equals(runnerKat))
                        break;    
            }
        }
        kategorier = Arrays.copyOfRange(kategorier, 0, i);
        
        return kategorier;   
    }
    
    //Returnerer et array med alle de ulike kategorinavnene innen en gruppe.
    public String[] getAlleKategorieriGruppe(String gruppe)
    {
        int size = medisinregister.size();
        String[] kategorier = new String[size]; 
        int i = 0;
        
        Iterator<Medisin> iterator = medisinregister.iterator();
        Medisin runner = null;
        while (iterator.hasNext()) {
            runner = iterator.next();
            if(runner.getGrupp().equals(gruppe))
            {
                String runnerKat = runner.getKategori();
                for(int j = 0; j <= i; j++)
                {
                    if(kategorier[j] == null)
                    {
                        kategorier[j] = runner.getKategori();
                        i++;
                        break;
                    }
                    else
                        if(kategorier[j].equals(runnerKat))
                            break;    
                }
            }
        }
        kategorier = Arrays.copyOfRange(kategorier, 0, i);
        
        return kategorier;   
    }
    
    //Finner alle medisinnavn i en TreeSet<Medisin>.
    private String[] finnMedisinnavn(TreeSet<Medisin> medisiner)
    {
        int size = medisiner.size();
        
        String[] navn = new String[size];
        int i =0;
        
        Iterator<Medisin> iterator = medisiner.iterator();
        Medisin runner = null;
        while (iterator.hasNext()) {
            runner = iterator.next();
            String runnerNavn = runner.getNavn();
            for(int j = 0; j <= i; j++)
            {
                if(navn[j] == null)
                {
                    navn[i] = runner.getNavn();
                    i++;
                    break;
                }
                else
                    if(navn[j].equals(runnerNavn))
                        break;   
             }
        }
        navn = Arrays.copyOfRange(navn, 0, i);
        return navn;
    }
   
    /*Finner og returnerer en medisin i registeret basert på navnet, styrken,
     legemiddelformen og pakningsstørrelsen.
     Returnerer 'null' hvis ikke den finnes. */

    public Medisin finnMedisin(String navn, String styrke, String form, String pakning) {
        Iterator<Medisin> iterator = medisinregister.iterator();
        Medisin runner = null;

        while (iterator.hasNext()) {
            runner = iterator.next();
            if (runner.getNavn().equals(navn) && runner.getStyrke().equals(styrke)
                    &&runner.getForm().equals(form) && runner.getPakning().equals(pakning)) {
                return runner;
            }
        }
        return null;
    }
    
     /*Finner en eller flere medisiner som begynner med det aktuelle navnet.*/
    public Medisin[] finnMedisinNavn(String navn) {
        TreeSet<Medisin> medisinSet = finnMedisin(navn);
        return (Medisin[]) medisinSet.toArray();
        
    }
    /*Finner en eller flere medisiner som begynner med det aktuelle navnet.*/
    public TreeSet<Medisin> finnMedisin(String navn) {
        Iterator<Medisin> iterator = medisinregister.iterator();
        TreeSet<Medisin> medisinSet = new TreeSet<>(komp);
        Medisin runner;
        navn = navn + ".*";

        while (iterator.hasNext()) {
            runner = iterator.next();
            if (runner.getNavn().matches(navn)) {
                medisinSet.add(runner);
            }

        }
        return medisinSet;
    }
    
    /*Finner og returnerer alle medisiner i den eller de gruppene 
     som passer med det aktuelle regulære uttrykket.*/
    public Medisin[] finnMedisinGruppe(String gruppe) {
        TreeSet<Medisin> medisinSet = finnMedisiniGruppe(gruppe);     
        return (Medisin[]) medisinSet.toArray();
        
    }
    /*Finner og returnerer alle medisiner i den eller de gruppene 
     som passer med det aktuelle regulære uttrykket.*/
    public TreeSet<Medisin> finnMedisiniGruppe(String regex) {
        Iterator<Medisin> iterator = medisinregister.iterator();
        TreeSet<Medisin> medisinSet = new TreeSet<>(komp);
        Medisin runner;

        while (iterator.hasNext()) {
            runner = iterator.next();
            if (runner.getGrupp().matches(regex)) {
                medisinSet.add(runner);
            }

        }
        return medisinSet;
    }
    
    /*Finner og returnerer alle medisiner innen den eller de kategorier
     som passer med det aktuelle regulære uttrykket.*/
   public Medisin[] finnMedisinKategori(String kategori) {
        TreeSet<Medisin> medisinSet = finnMedisiniKategori(kategori);
        
        return (Medisin[]) medisinSet.toArray();
    }
    /*Finner og returnerer alle medisiner innen den eller de kategorier
     som passer med det aktuelle regulære uttrykket i det TreeSet som sendes med*/

    public TreeSet<Medisin> finnMedisiniKategori(String regex, TreeSet<Medisin> medreg) {
        Iterator<Medisin> iterator = medreg.iterator();
        TreeSet<Medisin> medisinSet = new TreeSet<>(komp);
        Medisin runner;

        while (iterator.hasNext()) {
            runner = iterator.next();
            if (runner.getKategori().matches(regex)) {
                medisinSet.add(runner);
            }

        }
        return medisinSet;
    }
    
    /*Finner og returnerer alle medisiner innen den eller de kategorier
     som passer med det aktuelle regulære uttrykket.*/

    public TreeSet<Medisin> finnMedisiniKategori(String regex) {
        Iterator<Medisin> iterator = medisinregister.iterator();
        TreeSet<Medisin> medisinSet = new TreeSet<>(komp);
        Medisin runner;

        while (iterator.hasNext()) {
            runner = iterator.next();
            if (runner.getKategori().matches(regex)) {
                medisinSet.add(runner);
            }

        }
        return medisinSet;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Iterator<Medisin> iter = medisinregister.iterator();

        while (iter.hasNext()) {
            str.append(iter.next());
            str.append("\n");
        }

        return str.toString();
    }
}
