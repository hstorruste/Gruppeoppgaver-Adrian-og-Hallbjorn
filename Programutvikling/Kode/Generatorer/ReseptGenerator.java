package Generatorer;

import Controller.*;
import Model.*;
import java.util.Calendar;
import java.util.Random;

/**
 * Denne klassen genererer en resept. Det blir generert en tilfeldig resept på
 * en tilfeldig medisin. Siste versjon: 13-05-2014 Laget av Hallbjørn Storruste
 * s165519
 *
 * @author Hallbjørn
 */
public class ReseptGenerator {

    private Medisin[] medisinListe;

    private final long STARTDATO = 1325376000000L;
    private String[] beskrivelse = {"1 tablett 3 ganger daglig",
        "1 tablett etter behov", "Påsmøres 3 ganger daglig.",
        "Behandling gjennomføres 3 ganger daglig"};

    public ReseptGenerator(Medisinregister medisinregister) {
        int size = medisinregister.getMedisinregister().size();
        this.medisinListe = new Medisin[size];
        this.medisinListe = medisinregister.getMedisinregister().toArray(medisinListe);
    }

    /* Genererer en resept for den aktuelle pasienten forskrevet av den 
     aktuelle legen. Det blir generert en tilfeldig resept på en tilfeldig medisin */
    public Resept nyResept(Pasient pasient, Lege lege) {
        if(medisinListe.length == 0)
            return null;
        Random gen = new Random();
        int medIndex = gen.nextInt(medisinListe.length);
        Medisin medisin = medisinListe[medIndex];
        String k = pasient.getFnr().substring(8, 9);
        int kjonn = Integer.parseInt(k);
        if (kjonn % 2 == 1) //sjekker om det er gutt
        {
            while (medisin.getKategori().equals("Antiandrogen")) {// sjekker om det er p-piller
                medIndex = gen.nextInt(medisinListe.length);
                medisin = medisinListe[medIndex];
            }
        }

        Calendar dato = reseptDato();
        int reit = nyReit();
        String beskriv;
        if (medisin.getForm().matches(".*ablett")) {
            int indeks = gen.nextInt(2);
            if (medisin.getKategori().equals("Antibiotikum")) {
                indeks = 0;
            }
            beskriv = beskrivelse[indeks];
        } else if (medisin.getForm().matches(".*salve.*")) {
            beskriv = beskrivelse[2];
        } else {
            beskriv = beskrivelse[3];
        }

        boolean bevilling = true;
        if (medisin.getGrupp().equals("A")) {
            bevilling = lege.getA();
        } else if (medisin.getGrupp().equals("B")) {
            bevilling = lege.getB();
        } else {
            bevilling = lege.getC();
        }

        if (!bevilling) {
            return null;
        }

        Pasientregister egetPRegister = lege.getPasientliste();
        egetPRegister.settInn(pasient);

        Reseptregister reseptregister = pasient.getReseptliste();
        Boolean registrert = reseptregister.settInn(medisin, dato, lege, pasient, reit, beskriv);
        Resept ny = null;
        if (registrert) {
            ny = reseptregister.finnResept(reseptregister.getAntallResepter());
            long maaned = 2629743830L;
            Calendar enMaanedSiden = Calendar.getInstance();
            enMaanedSiden.setTimeInMillis(enMaanedSiden.getTimeInMillis() - maaned);
            if (dato.after(enMaanedSiden)) {
                boolean utlevere = gen.nextBoolean();
                if (utlevere) {
                    while (!ny.getUtlevert()) {
                        ny.setUtlevert();
                    }
                }
            } else {
                while (!ny.getUtlevert()) {
                    ny.setUtlevert();
                }
            }
        }

        return ny;
    }

    //Returnerer et Calenderobjekt  med dato fra 01-01-2012 frem til dags dato.
    private Calendar reseptDato() {

        Calendar dato = Calendar.getInstance();
        dato.setTimeInMillis(STARTDATO);
        Calendar dagsdato = Calendar.getInstance();
        long diff = dagsdato.getTimeInMillis() - dato.getTimeInMillis();
        diff = (long) (Math.random() * diff);
        dato.setTimeInMillis(STARTDATO + diff);

        return dato;
    }

    //Returner reit til en resept (0, 1, 2 eller 3) med størst mulighet for 0.
    private int nyReit() {
        Random tallgen = new Random();
        int slump = tallgen.nextInt(10);
        int reit;
        if (slump < 5)          //50%
        {
            reit = 0;
        } else if (slump < 7)   //20%
        {
            reit = 1;
        } else if (slump < 9)   //20%
        {
            reit = 2;
        } else                  //10%
        {
            reit = 3;
        }

        return reit;
    }
}
