/*Denne klassen lager vanligt anvendte komponenter.
 Den har også noen andre hjelpemetoder for å forandre utseende til 
 brukergrensesnittet. Og metoder for å kontrollere gyldig epost og fødselsnummer.
 Laget av Adrian Westlund 
 Siste versjon 25-04-2014*/
package View.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;

public class Komponent {

    private static Font font = new Font("SansSerif", Font.PLAIN, 16);
    private static final String riktigEpost = "\\w+\\@\\w+\\.\\w+";
    private static final String korrektFnr = "[0-3]\\d[01]\\d{8}";
    public static final String dataFil = "data.dat";
    public static Color rettTekst = Color.black;
    public static Color feilTekst = Color.red;

    //Sjekker om Epost er riktig
    public static boolean riktigEpost(String epost) {
        return epost.matches(riktigEpost);
    }

    //Sjekker om Fødselsnummer er riktig
    public static boolean riktigFNr(String Fnr) {
        return Fnr.matches(korrektFnr);
    }

    //Lager en komponent med en label og en textField på samme rad
    public static JPanel labelFieldRow(String text, JTextField field) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(text);
        panel.setLayout(new GridLayout(1, 2, 5, 5));
        panel.add(label);
        panel.add(field);

        return panel;
    }

    //Lager en komponent med en label og en textField på två rader
    public static JPanel labelFieldColumb(String text, JTextField field) {
        JPanel panel = new JPanel(false);
        JLabel label = new JLabel(text);
        panel.setLayout(new GridLayout(2, 1, 5, 5));
        panel.add(label);
        panel.add(field);
        return panel;
    }

    //Lager en komponent med en label og en combobox på samme rad
    public static JPanel labelComboBoxRow(String text, JComboBox<String> comboBox) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(text);
        panel.setLayout(new GridLayout(1, 2, 5, 5));
        panel.add(label);
        panel.add(comboBox);

        return panel;
    }

    //Lager en komponent med en label og en combobox på två rader
    public static JPanel labelComboBoxColumb(String text, JComboBox<String> comboBox) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(text);
        panel.setLayout(new GridLayout(2, 1, 5, 5));
        panel.add(label);
        panel.add(comboBox);

        return panel;
    }

    /*Finner og returnerer JLabel som er knyttet til et JTextField eller JComboBox
     metoden tar imot en conatiner som skal søkes i og det feltet som 
     JLabel'en er knyttet til*/
    public static JLabel finnLabelTilFelt(Container komponent, Component felt) {

        Component[] element = ((Container) komponent).getComponents();
        for (int i = 0; i < element.length; i++) {
            if (element[i] == felt) {
                if (element[i - 1] instanceof JLabel) {
                    return (JLabel) element[i - 1];
                }
            }

            if (element[i] instanceof Container) {
                JLabel funnet = finnLabelTilFelt((Container) element[i], felt);
                if (funnet != null) {
                    return funnet;
                }
            }
        }
        return null;
    }

    /*Metoden ändrar font genom att alla komponenter av Container
     blir kallade på och sätter den bestämda fonten på dessa.*/
    public static void endreFont(Component komponent) {
        komponent.setFont(font);
        if (komponent instanceof Container) {
            for (Component child : ((Container) komponent).getComponents()) {
                endreFont(child);
            }
        }
    }

    //Endrer ikon
    public static void bilde(JFrame klass) {
        String bildefil = "Handprint.png";
        URL kilde = Komponent.class.getResource(bildefil);

        if (kilde != null) {
            ImageIcon bilde = new ImageIcon(kilde);
            Image ikon = bilde.getImage();
            klass.setIconImage(ikon);
        }
    }

    //Lager ett popup box
    public static void popup(JFrame klass, String tekst) {
        JOptionPane.showMessageDialog(klass, tekst);
    }
}
