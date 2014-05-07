package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StatistikkInfoLeger extends JPanel {

    private StatistikkVindu parentFrame;
    private ButtonGroup antallGrupp;
    private JRadioButton alleRadio, sokRadio;
    private JTextField fornavnFelt, etternavnFelt;
    private JCheckBox a, b, c;
    private JButton sokKnapp;
    private JTextArea textArea;
    private String[] navnString = {"Alle", "Søk", "Reseptgruppe", "Legeliste", "Info om legen"};
    private JList legeList;
    private String[] legeAlt;
    private String[] eksempel = {"Gunnar", "Joppe", "Anders", "Gøran", "Adrian"};

    private final int TEKSTFELTLENGDE = 10;

    private KnappeLytter knappeLytter;

    public StatistikkInfoLeger(StatistikkVindu a) {
        super();
        parentFrame = a;
        knappeLytter = new KnappeLytter();

        setLayout(new FlowLayout());

        add(infoLegerGUI());
    }

    private JPanel infoLegerGUI() {

        antallGrupp = new ButtonGroup();
        alleRadio = new JRadioButton(navnString[0], false);
        sokRadio = new JRadioButton(navnString[1], false);
        antallGrupp.add(alleRadio);
        antallGrupp.add(sokRadio);
        
        JPanel alleSokPanel = new JPanel(new GridLayout(2, 0, 5, 5));
        alleSokPanel.add(alleRadio);
        alleSokPanel.add(sokRadio);
        
        fornavnFelt = new JTextField(TEKSTFELTLENGDE);
        JComponent kompFornavnFelt = Komponent.labelFieldColumb("Fornavn", fornavnFelt);

        etternavnFelt = new JTextField(TEKSTFELTLENGDE);
        JComponent kompEtternavnFelt = Komponent.labelFieldColumb("Etternavn", etternavnFelt);
        

        JPanel radioNavnPanel = new JPanel(new GridLayout(0, 4, 5, 5));
        radioNavnPanel.add(alleSokPanel);
        radioNavnPanel.add(kompFornavnFelt);
        radioNavnPanel.add(kompEtternavnFelt);

        JLabel labelNavn = new JLabel(navnString[2]);

        a = new JCheckBox("A");
        b = new JCheckBox("B");
        c = new JCheckBox("C");

        JPanel checkbox = new JPanel(new GridLayout(0, 3, 5, 5));
        checkbox.add(a);
        checkbox.add(b);
        checkbox.add(c);
        
        JPanel labelCheckPanel = new JPanel(new GridLayout(2,0,5,5));
        labelCheckPanel.add(labelNavn);
        labelCheckPanel.add(checkbox);

        sokKnapp = new JButton("Finn");
        sokKnapp.addActionListener(knappeLytter);
        JLabel tomt = new JLabel("");

        JPanel sokKnappPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        sokKnappPanel.add(sokKnapp);
        sokKnappPanel.add(tomt);

        JPanel sokPanel = new JPanel(new BorderLayout());
        sokPanel.add(sokKnappPanel, BorderLayout.LINE_START);

        JPanel topPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        topPanel.add(radioNavnPanel);
        topPanel.add(labelCheckPanel);
        topPanel.add(sokPanel);
        
        JLabel legeListNavn = new JLabel(navnString[3]);
        JLabel legeInfoAreaNavn = new JLabel(navnString[4]);
        
        JPanel navnListAreaPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        navnListAreaPanel.add(legeListNavn);
        navnListAreaPanel.add(legeInfoAreaNavn);
        
        legeList = new JList<>(eksempel);
        
        textArea = new JTextArea(20,15);
        textArea.setEditable(false);
        
        JPanel listAreaPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        listAreaPanel.add(new JScrollPane(legeList));
        listAreaPanel.add(new JScrollPane(textArea));
        
        JPanel infoLeger = new JPanel(new BorderLayout());
        infoLeger.add(topPanel, BorderLayout.PAGE_START);
        infoLeger.add(navnListAreaPanel, BorderLayout.CENTER);
        infoLeger.add(listAreaPanel, BorderLayout.PAGE_END);
        
        return infoLeger;
    }

    private class KnappeLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //if (e.getSource() ==)
        }
    }
}
