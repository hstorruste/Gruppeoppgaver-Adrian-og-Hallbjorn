/*Dette er GUI for å finne og skrive ut en resept til en pasient. Klassen arver
 JTabbedPane og innholder fanene "Skriv resept" og "Historikk". 
 Den er en del av Legekontorvinduet(Brukes av LegekontorVindu).
 Laget av Hallbjørn Storruste s165519
 Siste versjon 29-04-2014 */
package View;

import View.util.Komponent;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class LegekontorSkrivResept extends JTabbedPane {

    private LegekontorVindu parentFrame;

    private JComboBox<String> medisinFelt, styrkeFelt, formFelt, pakningFelt;
    private JTextField gruppeFelt, kategoriFelt, atcFelt, reitFelt;
    private JTextArea bruksanvOmrade, historikkOmrade;
    private JButton sendKnapp, tilbakeKnapp, tilbakeHistKnapp;
    private String[] labeltekst = {"Medisin", "Styrke", "Legemiddelform", "Pakningsstr", "Gruppe", "Kategori", "ATC-nr",
        "Reit", "Dosering//Bruksanvisning", "Tidligere resepter"};
    private String[] medisiner;

    private KnappeLytter lytter;

    private final int TEKSTFELTLENGDE = 20;

    /*Konstruktøren tar imot LegekontorVindu, dvs vindusklassen som oppretter
     objektet av denne klassen. Dette gjøres for å kunne benytte seg av metodene
     som ligger i dette objektet og kunne få tilgang til bl.a registerne, 
     registrere en resept på en pasient og gå tilbake til finn pasient GUI.
     Det vil si: å tegne vinduet på nytt med brukergrensesnittet for å finne en
     pasient.*/
    public LegekontorSkrivResept(LegekontorVindu p) {
        super();

        parentFrame = p;

        lytter = new KnappeLytter();

        String pasientnavn = parentFrame.getPasient().getNavn();

        addTab("Skriv resept til " + pasientnavn, skrivReseptGUI());
        addTab("Historikk for " + pasientnavn, historikkGUI());

    }

    //Oppretter alt i fane for "Skriv resept".
    private JPanel skrivReseptGUI() {

        medisiner = parentFrame.getAlleMedisinnavn();
        medisinFelt = new JComboBox<>(medisiner);
        medisinFelt.setSelectedIndex(-1);
        medisinFelt.setEditable(false);
        medisinFelt.addActionListener(lytter);
        JPanel medisinSkriv = (JPanel) Komponent.labelComboBoxColumb(labeltekst[0], medisinFelt);

        styrkeFelt = new JComboBox<>();
        styrkeFelt.setSelectedIndex(-1);
        styrkeFelt.setEditable(false);
        styrkeFelt.addActionListener(lytter);
        JPanel styrkeSkriv = Komponent.labelComboBoxColumb(labeltekst[1], styrkeFelt);

        formFelt = new JComboBox<>();
        formFelt.setSelectedIndex(-1);
        formFelt.setEditable(false);
        formFelt.addActionListener(lytter);
        JPanel formSkriv = Komponent.labelComboBoxColumb(labeltekst[2], formFelt);

        pakningFelt = new JComboBox<>();
        pakningFelt.setSelectedIndex(-1);
        pakningFelt.setEditable(false);
        pakningFelt.addActionListener(lytter);
        JPanel pakningSkriv = Komponent.labelComboBoxColumb(labeltekst[3], pakningFelt);

        JPanel medisinPanel1 = new JPanel(new GridLayout(1, 0));
        medisinPanel1.add(medisinSkriv);
        medisinPanel1.add(styrkeSkriv);
        medisinPanel1.add(formSkriv);
        medisinPanel1.add(pakningSkriv);

        JComponent[] felt = new JComponent[]{styrkeFelt, formFelt, pakningFelt};

        for (JComponent child : felt) {
            child.setVisible(false);
            JLabel merkelapp = Komponent.finnLabelTilFelt(medisinPanel1, child);
            if (merkelapp != null) {
                merkelapp.setVisible(false);
            }
        }

        gruppeFelt = new JTextField(TEKSTFELTLENGDE);
        gruppeFelt.setEditable(false);
        JPanel gruppeSkriv = (JPanel) Komponent.labelFieldColumb(labeltekst[4], gruppeFelt);

        kategoriFelt = new JTextField(TEKSTFELTLENGDE);
        kategoriFelt.setEditable(false);
        JPanel kategoriSkriv = (JPanel) Komponent.labelFieldColumb(labeltekst[5], kategoriFelt);

        atcFelt = new JTextField(TEKSTFELTLENGDE);
        atcFelt.setEditable(false);
        JPanel atcSkriv = (JPanel) Komponent.labelFieldColumb(labeltekst[6], atcFelt);

        reitFelt = new JTextField(TEKSTFELTLENGDE);
        JPanel reitSkriv = (JPanel) Komponent.labelFieldColumb(labeltekst[7], reitFelt);

        JPanel medisinPanel2 = new JPanel(new GridLayout(1, 0));

        medisinPanel2.add(gruppeSkriv);
        medisinPanel2.add(kategoriSkriv);
        medisinPanel2.add(atcSkriv);
        medisinPanel2.add(reitSkriv);

        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.add(new JLabel(labeltekst[8]), BorderLayout.LINE_START);

        bruksanvOmrade = new JTextArea(15, 20);

        JPanel bruksanvPanel = new JPanel(new GridLayout(1, 1));
        bruksanvPanel.add(new JScrollPane(bruksanvOmrade));

        tilbakeKnapp = new JButton("Tilbake");
        tilbakeKnapp.addActionListener(lytter);

        sendKnapp = new JButton("Send");
        sendKnapp.addActionListener(lytter);

        JPanel knappePanel = new JPanel(new BorderLayout());
        knappePanel.add(tilbakeKnapp, BorderLayout.LINE_START);
        knappePanel.add(sendKnapp, BorderLayout.LINE_END);

        JPanel medisinReseptPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        medisinReseptPanel.add(medisinPanel1);
        medisinReseptPanel.add(medisinPanel2);
        medisinReseptPanel.add(labelPanel);

        JPanel skrivReseptPanel = new JPanel(new BorderLayout());
        skrivReseptPanel.add(medisinReseptPanel, BorderLayout.PAGE_START);
        skrivReseptPanel.add(bruksanvPanel, BorderLayout.CENTER);
        skrivReseptPanel.add(knappePanel, BorderLayout.PAGE_END);

        return skrivReseptPanel;
    }

    //Oppretter alt i fane "historikk".
    private JPanel historikkGUI() {

        historikkOmrade = new JTextArea(15, 20);
        historikkOmrade.setEditable(false);
        String historikk = parentFrame.getPasientHistorikk();
        historikkOmrade.setText(historikk);

        JPanel historikkPanel = new JPanel(new GridLayout(1, 1));
        historikkPanel.add(new JScrollPane(historikkOmrade));

        tilbakeHistKnapp = new JButton("Tilbake");
        tilbakeHistKnapp.addActionListener(lytter);

        JPanel knappePanel = new JPanel(new BorderLayout());
        knappePanel.add(tilbakeHistKnapp, BorderLayout.LINE_START);

        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.add(new JLabel(labeltekst[9]), BorderLayout.LINE_START);

        JPanel historikkGUI = new JPanel(new BorderLayout());
        historikkGUI.add(labelPanel, BorderLayout.PAGE_START);
        historikkGUI.add(historikkPanel, BorderLayout.CENTER);
        historikkGUI.add(knappePanel, BorderLayout.PAGE_END);

        return historikkGUI;
    }

    //Oppdaterer innhold i ComboBox for pakning og gjør dem synlig/usynlig.
    private void oppdaterPakningComboBox() {
        if (formFelt.getSelectedIndex() > -1) {
            pakningFelt.setVisible(true);
            JLabel merkelapp = Komponent.finnLabelTilFelt(this, pakningFelt);
            merkelapp.setVisible(true);
            pakningFelt.removeAllItems();
            String navn = (String) medisinFelt.getSelectedItem();
            String styrke = (String) styrkeFelt.getSelectedItem();
            String form = (String) formFelt.getSelectedItem();
            String[] pakning = parentFrame.getAlleMedisinPakninger(navn, styrke, form);
            for (int i = 0; i < pakning.length; i++) {
                pakningFelt.insertItemAt(pakning[i], i);
            }
        } else {
            pakningFelt.setVisible(false);
            JLabel merkelapp = Komponent.finnLabelTilFelt(this, pakningFelt);
            merkelapp.setVisible(false);
            gruppeFelt.setText("");
            kategoriFelt.setText("");
            atcFelt.setText("");
        }
    }

    //Oppdaterer innhold i ComboBox for form og gjør dem synlig/usynlig.
    private void oppdaterFormComboBox() {
        if (styrkeFelt.getSelectedIndex() > -1) {
            formFelt.setVisible(true);
            JLabel merkelapp = Komponent.finnLabelTilFelt(this, formFelt);
            merkelapp.setVisible(true);
            formFelt.removeAllItems();
            String navn = (String) medisinFelt.getSelectedItem();
            String styrke = (String) styrkeFelt.getSelectedItem();
            String[] form = parentFrame.getAlleMedisinFormer(navn, styrke);
            for (int i = 0; i < form.length; i++) {
                formFelt.insertItemAt(form[i], i);
            }
        } else {
            JComponent[] felt = new JComponent[]{formFelt, pakningFelt};

            for (JComponent child : felt) {
                child.setVisible(false);
                JLabel merkelapp = Komponent.finnLabelTilFelt(this, child);
                if (merkelapp != null) {
                    merkelapp.setVisible(false);
                }
            }
            gruppeFelt.setText("");
            kategoriFelt.setText("");
            atcFelt.setText("");

        }
    }
    
    //Oppdaterer innhold i ComboBox for styrke og gjør dem synlig/usynlig.
    private void oppdaterStyrkeComboBox() {
        if (medisinFelt.getSelectedIndex() > -1) {
            styrkeFelt.setVisible(true);
            JLabel merkelapp = Komponent.finnLabelTilFelt(this, styrkeFelt);
            merkelapp.setVisible(true);
            styrkeFelt.removeAllItems();
            String navn = (String) medisinFelt.getSelectedItem();
            String[] styrke = parentFrame.getAlleMedisinStyrker(navn);
            for (int i = 0; i < styrke.length; i++) {
                styrkeFelt.insertItemAt(styrke[i], i);
            }

        } else {
            JComponent[] felt = new JComponent[]{styrkeFelt, formFelt, pakningFelt};

            for (JComponent child : felt) {
                child.setVisible(false);
                JLabel merkelapp = Komponent.finnLabelTilFelt(this, child);
                if (merkelapp != null) {
                    merkelapp.setVisible(false);
                }
            }
            gruppeFelt.setText("");
            kategoriFelt.setText("");
            atcFelt.setText("");
        }
    }

    /*Setter gruppeFelt kategoriFelt og atcFelt til de respektive verdiene som
     medisinen som er valgt har.*/
    private void setMedisinFelt() {
        String navn = (String) medisinFelt.getSelectedItem();
        String styrke = (String) styrkeFelt.getSelectedItem();
        String form = (String) formFelt.getSelectedItem();
        String pakning = (String) pakningFelt.getSelectedItem();
        Medisin medisin = parentFrame.finnMedisin(navn, styrke, form, pakning);
        if (medisin == null) {
            return;
        }

        String gruppe = medisin.getGrupp();
        String kategori = medisin.getKategori();
        String atc = medisin.getATCNr();
        gruppeFelt.setText(gruppe);
        kategoriFelt.setText(kategori);
        atcFelt.setText(atc);
    }
    
    //Setter inn  en resept i pasientens reseptregister.
    private void sendResept() {
        //registerer resept
        Lege lege = parentFrame.getLege();
        String navn = (String) medisinFelt.getSelectedItem();
        String styrke = (String) styrkeFelt.getSelectedItem();
        String form = (String) formFelt.getSelectedItem();
        String pakning = (String) pakningFelt.getSelectedItem();
        Medisin medisin = parentFrame.finnMedisin(navn, styrke, form, pakning);
        if (medisin == null) {
            String melding = "Finner ikke medisinen!";
            Komponent.popup(parentFrame, melding);
            return;
        }
        String gruppe = medisin.getGrupp();
        int reit = 0;
        String reitTekst = reitFelt.getText();
        if (!reitTekst.equals("")) {
            try {
                reit = Integer.parseInt(reitTekst);
            } catch (NumberFormatException nfe) {
                String melding = "Ugyldig antall reit! \nBruk kun tegnene 0-9.";
                Komponent.popup(parentFrame, melding);
                return;
            }
        }

        boolean bevilling = false;
        if (gruppe.equals("A")) {
            bevilling = lege.getA();
        } else if (gruppe.equals("B")) {
            bevilling = lege.getB();
        } else if (gruppe.equals("C")) {
            bevilling = lege.getC();
        }
        if (!bevilling) {
            String melding = "Du har ikke bevilling til å skrive ut medisiner"
                    + " i reseptgruppe: " + gruppe + ".";
            Komponent.popup(parentFrame, melding);
            return;
        }

        Calendar d = Calendar.getInstance();

        String beskrivelse = bruksanvOmrade.getText();

        boolean sendt = parentFrame.registrerResept(medisin, d, lege, reit, beskrivelse);
        if (sendt) {
            String melding = "Resepten er sendt";
            Komponent.popup(parentFrame, melding);
            parentFrame.skrivTilFil();
            parentFrame.tegnSkrivReseptGUI(parentFrame.getPasient());
        }
    }
    
    //Tegner GUI for å finne en pasient.
    private void tilbake() {
        parentFrame.tegnFinnPasientGUI(parentFrame.getLege());
    }

    private class KnappeLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == sendKnapp) {
                sendResept();
            } else if (e.getSource() == medisinFelt) {
                oppdaterStyrkeComboBox();
            } else if (e.getSource() == styrkeFelt) {
                oppdaterFormComboBox();
            } else if (e.getSource() == formFelt) {
                oppdaterPakningComboBox();
            } else if (e.getSource() == pakningFelt) {
                setMedisinFelt();
            } else {
                tilbake();
            }
        }
    }
}
