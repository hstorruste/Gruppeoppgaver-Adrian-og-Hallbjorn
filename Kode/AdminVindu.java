/*  Denne klassen er et GUI til admin.
    Laget av Adrian Westlund s198571.
    Siste versjon 09-04-2014*/

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class AdminVindu extends JFrame
{
	Font font = new Font("SansSerif", Font.PLAIN, 16);

    public AdminVindu()
    {
        super("Admin");

		setLayout(new GridLayout(0, 1, 40, 40));

		JPanel legeRedigerTilFane = new JPanel(new FlowLayout());
		legeRedigerTilFane.add(legeRedigerGUI());

		JPanel legeRegistrer = new JPanel(new GridLayout(0, 1, 5, 5));
		legeRegistrerGUI(legeRegistrer);
		JPanel legeRegistrerTilFane = new JPanel(new FlowLayout());
		legeRegistrerTilFane.add(legeRegistrer);

		JTabbedPane gruppFaneLege = new JTabbedPane();
		gruppFaneLege.addTab("Rediger", legeRedigerTilFane);
		gruppFaneLege.addTab("Registrer", legeRegistrerTilFane);


		JPanel medisinRedigerTilFane = new JPanel(new FlowLayout());
		medisinRedigerTilFane.add(medisinRedigerGUI());

		JPanel medisinRegistrer = new JPanel(new GridLayout(0, 1, 5, 5));
		medisinRegistrerGUI(medisinRegistrer);
		JPanel medisinRegistrerTilFane = new JPanel(new FlowLayout());
		medisinRegistrerTilFane.add(medisinRegistrer);

		JTabbedPane gruppFaneMedisin = new JTabbedPane();
		gruppFaneMedisin.addTab("Rediger", medisinRedigerTilFane);
		gruppFaneMedisin.addTab("Registrer", medisinRegistrerTilFane);

		JTabbedPane gruppFane = new JTabbedPane();
		gruppFane.addTab("Lege", gruppFaneLege);
		gruppFane.addTab("Medisin", gruppFaneMedisin);

        add(gruppFane);

        String bildefil = "Handprint.png";
		URL kilde = AdminVindu.class.getResource(bildefil);
		if (kilde != null)
		{
			ImageIcon bilde = new ImageIcon(kilde);
			Image ikon = bilde.getImage();
			setIconImage(ikon);
		}

		endreFont(this, font);
		pack();
		setVisible(true);
    }
    /*	Metoden endrer font genom at alle komponenter av Container
    	blir kallet på og setter bestemd font.
    */
    public static void endreFont( Component komponent, Font font )
	{
	    komponent.setFont( font );
	    if ( komponent instanceof Container )
	    {
	        for ( Component child : (( Container ) komponent ).getComponents ())
	        {
	            endreFont( child, font );
	        }
	    }
	}
	/*	Metoden legger til alle komponenter som ikke skal vare i legeRedigerGUI
		och legger sen til legeRedigerGUI.
		Tilslut legger den in komponenterna i en BorderLayout och plaserar den
		til venstre och legger til en textArea som legges til høyre i samme layout.*/
	 private JPanel legeRedigerGUI()
    {
		JPanel legeRediger = new JPanel(new GridLayout(0, 1, 5, 5));

		JTextField finnLege = new JTextField(5);
		JComponent kompFinnLege = Komponent.labelFieldRow("Navn", finnLege);
		legeRediger.add(kompFinnLege);


		JButton finnLegeKnapp = new JButton("Finn");
		finnLegeKnapp.setPreferredSize(new Dimension(135, 20));
		//finnLegeKnapp.addActionListener( actionLytter );
		JPanel finnLegeKnappPanel = new JPanel(new BorderLayout());
		finnLegeKnappPanel.add(finnLegeKnapp, BorderLayout.LINE_END);
		legeRediger.add(finnLegeKnappPanel);

		JButton seLegeListeKnapp = new JButton("Se hele listen");
		seLegeListeKnapp.setPreferredSize(new Dimension(135, 20));
		//seLegeListeKnapp.addActionListener( actionLytter );
		JPanel seLegeListeKnappPanel = new JPanel(new BorderLayout());
		seLegeListeKnappPanel.add(seLegeListeKnapp, BorderLayout.LINE_END);
		legeRediger.add(seLegeListeKnappPanel);

		legeRegistrerGUI(legeRediger);

		JPanel legeInnFelt = new JPanel(new FlowLayout());
		legeInnFelt.add(legeRediger);

		JTextArea textArea = new JTextArea(20,30);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);

		JPanel legeTextfeltPanel = new JPanel(new FlowLayout());
		legeTextfeltPanel.add(textArea);

		JPanel toPanel = new JPanel(new BorderLayout());
		toPanel.add(legeInnFelt, BorderLayout.LINE_START);
		toPanel.add(legeTextfeltPanel, BorderLayout.LINE_END);

		JPanel legeRedigerTilFane = new JPanel(new FlowLayout());
		legeRedigerTilFane.add(toPanel);

		return legeRedigerTilFane;
	}
	/*	Metoden tar emot et JPanel for at de skal gå at legge til denne metode
		til legeRegistrerGUI.*/
    private void legeRegistrerGUI(JPanel legeRegistrer)
    {
		JTextField fornavn = new JTextField(10);
		JComponent kompFornavn = Komponent.labelFieldRow("Fornavn", fornavn);
		legeRegistrer.add(kompFornavn);

		JTextField etternavn = new JTextField(5);
		JComponent kompEtternavn = Komponent.labelFieldRow("Etternavn", etternavn);
		legeRegistrer.add(kompEtternavn);

		JTextField ePost = new JTextField(5);
		JComponent kompEPost = Komponent.labelFieldRow("E-post", ePost);
		legeRegistrer.add(kompEPost);

		JTextField gateadresse = new JTextField(5);
		JComponent kompGate = Komponent.labelFieldRow("Gateadresse", gateadresse);
		legeRegistrer.add(kompGate);

		JTextField postNr = new JTextField(5);
		JComponent kompPostNr = Komponent.labelFieldRow("Postnummer", postNr);
		legeRegistrer.add(kompPostNr);

		JTextField poststed = new JTextField(5);
		JComponent kompPoststed = Komponent.labelFieldRow("Poststed", poststed);
        legeRegistrer.add(kompPoststed);

        JTextField arbetssted = new JTextField(5);
		JComponent kompArbetssted = Komponent.labelFieldRow("Poststed", arbetssted);
        legeRegistrer.add(kompArbetssted);

        JPanel checkbox = new JPanel(new GridLayout(1,0));
        JCheckBox a = new JCheckBox("A");
        //a.addItemListener( itemLytter );
        JCheckBox b = new JCheckBox("B");
        //b.addItemListener( itemLytter );
        JCheckBox c = new JCheckBox("C");
        //c.addItemListener( itemLytter );
        checkbox.add(a);
        checkbox.add(b);
        checkbox.add(c);
		legeRegistrer.add(checkbox);

		JButton spareKnapp = new JButton("Spare");
		JPanel spareKnappPanel= new JPanel(new BorderLayout());
		spareKnappPanel.add(spareKnapp, BorderLayout.LINE_END);
		legeRegistrer.add(spareKnappPanel);
	}

	private JPanel medisinRedigerGUI()
    {
		JPanel medisinRediger = new JPanel(new GridLayout(0, 1, 5, 5));

		JTextField finnMedisin = new JTextField(5);
		JComponent kompFinnMedisin = Komponent.labelFieldRow("Navn", finnMedisin);
		medisinRediger.add(kompFinnMedisin);


		JButton finnMedisinKnapp = new JButton("Finn");
		finnMedisinKnapp.setPreferredSize(new Dimension(135, 20));
		//finnMedisinKnapp.addActionListener( actionLytter );
		JPanel finnMedisinKnappPanel = new JPanel(new BorderLayout());
		finnMedisinKnappPanel.add(finnMedisinKnapp, BorderLayout.LINE_END);
		medisinRediger.add(finnMedisinKnappPanel);

		JButton seMedisinListeKnapp = new JButton("Se hele listen");
		seMedisinListeKnapp.setPreferredSize(new Dimension(135, 20));
		//seMedisinListeKnapp.addActionListener( actionLytter );
		JPanel seMedisinListeKnappPanel = new JPanel(new BorderLayout());
		seMedisinListeKnappPanel.add(seMedisinListeKnapp, BorderLayout.LINE_END);
		medisinRediger.add(seMedisinListeKnappPanel);

		medisinRegistrerGUI(medisinRediger);

		JPanel medisinInnFelt = new JPanel(new FlowLayout());
		medisinInnFelt.add(medisinRediger);

		JTextArea textArea = new JTextArea(20,30);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);

		JPanel medisinTextfeltPanel = new JPanel(new FlowLayout());
		medisinTextfeltPanel.add(textArea);

		JPanel toPanel = new JPanel(new BorderLayout());
		toPanel.add(medisinInnFelt, BorderLayout.LINE_START);
		toPanel.add(medisinTextfeltPanel, BorderLayout.LINE_END);

		JPanel medisinRedigerTilFane = new JPanel(new FlowLayout());
		medisinRedigerTilFane.add(toPanel);

		return medisinRedigerTilFane;
	}

	private void medisinRegistrerGUI(JPanel medisinRegistrer)
    {
		String[] kategorier = {"A", "B", "C"};
		JComboBox<String> gruppVelger = new JComboBox<>(kategorier);
		gruppVelger.setSelectedIndex(0);
		//gruppVelger.addActionListener(actionLytter);
		JComponent grupp = Komponent.labelComboBoxRow("Grupp", gruppVelger);
		medisinRegistrer.add(grupp);

		String[] abc = {"Sövnmedel", "Antidepresiva", "Narkotikaklassade"};//Har bara lagt in något
		JComboBox<String> kategoriVelger = new JComboBox<>(abc);
		//gruppVelger.addActionListener(actionLytter);
		JComponent kategori = Komponent.labelComboBoxRow("Kategori", kategoriVelger);
		medisinRegistrer.add(kategori);

		JTextField navn = new JTextField(10);
		JComponent kompNavn = Komponent.labelFieldRow("Navn", navn);
		medisinRegistrer.add(kompNavn);

		JTextField atc = new JTextField(10);
		JComponent kompATC = Komponent.labelFieldRow("ATC-nr", atc);
		medisinRegistrer.add(kompATC);

		JButton medisinSpareKnapp = new JButton("Spare");
		JPanel medisinSpareKnappPanel= new JPanel(new BorderLayout());
		medisinSpareKnappPanel.add(medisinSpareKnapp, BorderLayout.LINE_END);
		medisinRegistrer.add(medisinSpareKnappPanel);
	}
}