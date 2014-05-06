package View;
/**Detta är GUI för nya resepter och historik över gamla resepter. Klassen arver
 * JTabbedPane och har fanene nye Resepter och historikk. Den är en del av 
 * AdminVindu.
 * Laget av Adrian Westlund s198571
 * Siste versjon 05-05-2014
 */
import Model.Resept;
import java.awt.*;
import java.awt.event.*;
import java.util.TreeSet;
import javax.swing.*;

public class ApotekPasient extends JTabbedPane {

    private ApotekVindu parentFrame;
    private JTextField reseptNummerFelt;
    private JButton registrerKnapp, tillbakaKnappNye, tillbakaKnappHis;
    private JTextArea reseptArea, historikkArea;

    private KnappeLytter knappeLytter;

    public ApotekPasient(ApotekVindu a) {

        super();
        parentFrame = a;

        knappeLytter = new KnappeLytter();

        JPanel nyeResepter = nyeResepterGUI();

        JPanel pasientHistorik = pasientHistorikGUI();

        addTab("Nye resepter", nyeResepter);
        addTab("Historikk", pasientHistorik);
    }

    public JPanel nyeResepterGUI() {
        JPanel nyeResepterFelt = new JPanel(new GridLayout(0, 1, 5, 5));

        reseptNummerFelt = new JTextField(5);
        reseptNummerFelt.addActionListener(knappeLytter);
        JComponent kompRegistrerResepter = Komponent.labelFieldRow("Reseptnummer", reseptNummerFelt);
        nyeResepterFelt.add(kompRegistrerResepter);

        registrerKnapp = new JButton("Utlever");
        registrerKnapp.setPreferredSize(new Dimension(113, 20));
        registrerKnapp.addActionListener(knappeLytter);
        JPanel registrerKnappPanel = new JPanel(new BorderLayout());
        registrerKnappPanel.add(registrerKnapp, BorderLayout.LINE_END);
        nyeResepterFelt.add(registrerKnappPanel);
        
        reseptArea = new JTextArea(20, 20);
        JScrollPane scrollPane = new JScrollPane(reseptArea);
        reseptArea.setEditable(false);
        reseptArea.setText(finnIkkeUtlevert());

        tillbakaKnappNye = new JButton("Tilbake");
        tillbakaKnappNye.setPreferredSize(new Dimension(113, 20));
        tillbakaKnappNye.addActionListener(knappeLytter);
        JPanel tillbakaKnappNyePanel = new JPanel(new BorderLayout());
        tillbakaKnappNyePanel.add(tillbakaKnappNye, BorderLayout.LINE_END);
        
        JPanel nyeResepter = new JPanel(new BorderLayout());
        
        nyeResepter.add(nyeResepterFelt, BorderLayout.PAGE_START);
        nyeResepter.add(reseptArea, BorderLayout.CENTER);
        nyeResepter.add(tillbakaKnappNyePanel, BorderLayout.PAGE_END);

        return nyeResepter;
    }

    public JPanel pasientHistorikGUI() {

        historikkArea = new JTextArea(20, 20);
        JScrollPane scrollPane = new JScrollPane(historikkArea);
        historikkArea.setEditable(false);
        historikkArea.setText(finnUtlevert());
        

        tillbakaKnappHis = new JButton("Tilbake");
        tillbakaKnappHis.setPreferredSize(new Dimension(113, 20));
        tillbakaKnappHis.addActionListener(knappeLytter);
        JPanel tillbakaKnappHisPanel = new JPanel(new BorderLayout());
        tillbakaKnappHisPanel.add(tillbakaKnappHis, BorderLayout.LINE_END);
        
        JPanel historik = new JPanel(new BorderLayout());
        historik.add(historikkArea, BorderLayout.CENTER);
        historik.add(tillbakaKnappHisPanel, BorderLayout.PAGE_END);

        return historik;
    }
    private String finnIkkeUtlevert(){
        String nyeResepterText = parentFrame.getKund().getReseptliste().finnNyeResepterString();
        return nyeResepterText;
    }
     private String finnUtlevert(){
        String nyeResepterText = parentFrame.getKund().getReseptliste().finnGamleResepterString();
        return nyeResepterText;
    }
    private void reseptUtlevert() {
        try
        {
            Resept resept = parentFrame.getKund().getReseptliste().finnResept(Integer.parseInt(reseptNummerFelt.getText()));
            if (resept == null) {
                String melding = "Finner ikke reseptet";
                Komponent.popup(parentFrame, melding);
            } else {
                resept.setUtlevert(); 
                parentFrame.skrivTilFil();
                String melding = "Reseptet er utlevert";
                Komponent.popup(parentFrame, melding);
                reseptNummerFelt.setText("");
                reseptArea.setText(finnIkkeUtlevert());
                historikkArea.setText(finnUtlevert());
            }
        }
        catch(NumberFormatException nfe){
            String melding = "Feil i feltet, må vare siffer";
            Komponent.popup(parentFrame, melding);
            return;
        }
    }

    private class KnappeLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registrerKnapp || e.getSource() == reseptNummerFelt) {
                reseptUtlevert();
            }
            if (e.getSource() == tillbakaKnappNye ) {
                parentFrame.tegnFinnPasientGUI();
            }
            if (e.getSource() == tillbakaKnappHis) {
                parentFrame.tegnFinnPasientGUI();
            }
        }
    }
}
