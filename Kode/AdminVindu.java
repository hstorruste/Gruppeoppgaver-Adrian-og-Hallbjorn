/*  Denne klassen er et GUI til admin.
    Laget av Adrian Westlund s198571.
    Siste versjon 09-04-2014*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AdminVindu extends JFrame
{
	private Legeregister lege;
	private JTextField finnLege, fornavn, etternavn, ePost, gateadresse, postNr, poststed, arbetssted,
					   finnMedisin, medisinNavn, atc;
	private JButton finnLegeKnapp, seLegeListeKnapp, legeSpareKnapp,
					finnMedisinKnapp, seMedisinListeKnapp, medisinSpareKnapp;
	private JTextArea legeTextArea, medisinTextArea;
	private JCheckBox a, b, c;
	private JComboBox<String> gruppVelger, kategoriVelger;
	private ActionLytter actionLytter;
	private ItemLytter itemLytter;

    public AdminVindu()
    {
        super("Admin");
		actionLytter = new ActionLytter();

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

      	Komponent.bilde(this);

		Komponent.endreFont(this);
		pack();
		setVisible(true);

		lege = new Legeregister();
    }
	//Metoden upprättar alla element till fanan lege rediger.
	private JPanel legeRedigerGUI()
    {
		JPanel legeRediger = new JPanel(new GridLayout(0, 1, 5, 5));

		finnLege = new JTextField(5);
		JComponent kompFinnLege = Komponent.labelFieldRow("Navn", finnLege);
		legeRediger.add(kompFinnLege);


		finnLegeKnapp = new JButton("Finn");
		finnLegeKnapp.setPreferredSize(new Dimension(135, 20));
		finnLegeKnapp.addActionListener( actionLytter );
		JPanel finnLegeKnappPanel = new JPanel(new BorderLayout());
		finnLegeKnappPanel.add(finnLegeKnapp, BorderLayout.LINE_END);
		legeRediger.add(finnLegeKnappPanel);

		seLegeListeKnapp = new JButton("Se hele listen");
		seLegeListeKnapp.setPreferredSize(new Dimension(135, 20));
		//seLegeListeKnapp.addActionListener( actionLytter );
		JPanel seLegeListeKnappPanel = new JPanel(new BorderLayout());
		seLegeListeKnappPanel.add(seLegeListeKnapp, BorderLayout.LINE_END);
		legeRediger.add(seLegeListeKnappPanel);

		legeRegistrerGUI(legeRediger);

		JPanel legeInnFelt = new JPanel(new FlowLayout());
		legeInnFelt.add(legeRediger);

		legeTextArea = new JTextArea(20,30);
		JScrollPane scrollPane = new JScrollPane(legeTextArea);
		legeTextArea.setEditable(false);

		JPanel legeTextfeltPanel = new JPanel(new FlowLayout());
		legeTextfeltPanel.add(legeTextArea);

		JPanel toPanel = new JPanel(new BorderLayout());
		toPanel.add(legeInnFelt, BorderLayout.LINE_START);
		toPanel.add(legeTextfeltPanel, BorderLayout.LINE_END);

		JPanel legeRedigerTilFane = new JPanel(new FlowLayout());
		legeRedigerTilFane.add(toPanel);

		return legeRedigerTilFane;
	}
	/*	Denne metoden bynettes også av metoden legeRedigerGUI */
    private void legeRegistrerGUI(JPanel legeRegistrer)
    {
		fornavn = new JTextField(10);
		JComponent kompFornavn = Komponent.labelFieldRow("Fornavn", fornavn);
		legeRegistrer.add(kompFornavn);

		etternavn = new JTextField(5);
		JComponent kompEtternavn = Komponent.labelFieldRow("Etternavn", etternavn);
		legeRegistrer.add(kompEtternavn);

		ePost = new JTextField(5);
		JComponent kompEPost = Komponent.labelFieldRow("E-post", ePost);
		legeRegistrer.add(kompEPost);

		gateadresse = new JTextField(5);
		JComponent kompGate = Komponent.labelFieldRow("Gateadresse", gateadresse);
		legeRegistrer.add(kompGate);

		postNr = new JTextField(5);
		JComponent kompPostNr = Komponent.labelFieldRow("Postnummer", postNr);
		legeRegistrer.add(kompPostNr);

		poststed = new JTextField(5);
		JComponent kompPoststed = Komponent.labelFieldRow("Poststed", poststed);
        legeRegistrer.add(kompPoststed);

        arbetssted = new JTextField(5);
		JComponent kompArbetssted = Komponent.labelFieldRow("Arbetssted", arbetssted);
        legeRegistrer.add(kompArbetssted);

        JPanel checkbox = new JPanel(new GridLayout(1,0));
        a = new JCheckBox("A");
        //a.addItemListener( itemLytter );
        b = new JCheckBox("B");
        //b.addItemListener( itemLytter );
        c = new JCheckBox("C");
        //c.addItemListener( itemLytter );
        checkbox.add(a);
        checkbox.add(b);
        checkbox.add(c);
		legeRegistrer.add(checkbox);

		legeSpareKnapp = new JButton("Spare");
		JPanel spareKnappPanel= new JPanel(new BorderLayout());
		spareKnappPanel.add(legeSpareKnapp, BorderLayout.LINE_END);
		legeRegistrer.add(spareKnappPanel);
	}

	private JPanel medisinRedigerGUI()
    {
		JPanel medisinRediger = new JPanel(new GridLayout(0, 1, 5, 5));

		finnMedisin = new JTextField(5);
		JComponent kompFinnMedisin = Komponent.labelFieldRow("Navn", finnMedisin);
		medisinRediger.add(kompFinnMedisin);


		finnMedisinKnapp = new JButton("Finn");
		finnMedisinKnapp.setPreferredSize(new Dimension(135, 20));
		//finnMedisinKnapp.addActionListener( actionLytter );
		JPanel finnMedisinKnappPanel = new JPanel(new BorderLayout());
		finnMedisinKnappPanel.add(finnMedisinKnapp, BorderLayout.LINE_END);
		medisinRediger.add(finnMedisinKnappPanel);

		seMedisinListeKnapp = new JButton("Se hele listen");
		seMedisinListeKnapp.setPreferredSize(new Dimension(135, 20));
		//seMedisinListeKnapp.addActionListener( actionLytter );
		JPanel seMedisinListeKnappPanel = new JPanel(new BorderLayout());
		seMedisinListeKnappPanel.add(seMedisinListeKnapp, BorderLayout.LINE_END);
		medisinRediger.add(seMedisinListeKnappPanel);

		medisinRegistrerGUI(medisinRediger);

		JPanel medisinInnFelt = new JPanel(new FlowLayout());
		medisinInnFelt.add(medisinRediger);

		medisinTextArea = new JTextArea(20,30);
		JScrollPane scrollPane = new JScrollPane(medisinTextArea);
		medisinTextArea.setEditable(false);

		JPanel medisinTextfeltPanel = new JPanel(new FlowLayout());
		medisinTextfeltPanel.add(medisinTextArea);

		JPanel toPanel = new JPanel(new BorderLayout());
		toPanel.add(medisinInnFelt, BorderLayout.LINE_START);
		toPanel.add(medisinTextfeltPanel, BorderLayout.LINE_END);

		JPanel medisinRedigerTilFane = new JPanel(new FlowLayout());
		medisinRedigerTilFane.add(toPanel);

		return medisinRedigerTilFane;
	}

	private void medisinRegistrerGUI(JPanel medisinRegistrer)
    {
		String[] abc = {"A", "B", "C"};
		gruppVelger = new JComboBox<>(abc);
		gruppVelger.setSelectedIndex(0);
		//gruppVelger.addActionListener(actionLytter);
		JComponent grupp = Komponent.labelComboBoxRow("Grupp", gruppVelger);
		medisinRegistrer.add(grupp);


		String[] kategoriArray = {"Sövnmedel", "Antidepresiva", "Narkotikaklassade"};//Har bara lagt in något
		kategoriVelger = new JComboBox<>(kategoriArray);
		//gruppVelger.addActionListener(actionLytter);
		JComponent kategori = Komponent.labelComboBoxRow("Kategori", kategoriVelger);
		medisinRegistrer.add(kategori);

		medisinNavn = new JTextField(10);
		JComponent kompNavn = Komponent.labelFieldRow("Navn", medisinNavn);
		medisinRegistrer.add(kompNavn);

		atc = new JTextField(10);
		JComponent kompATC = Komponent.labelFieldRow("ATC-nr", atc);
		medisinRegistrer.add(kompATC);

		medisinSpareKnapp = new JButton("Spare");
		JPanel medisinSpareKnappPanel= new JPanel(new BorderLayout());
		medisinSpareKnappPanel.add(medisinSpareKnapp, BorderLayout.LINE_END);
		medisinRegistrer.add(medisinSpareKnappPanel);
	}
	/*public void registrerLege()
	{
		try
		{
				boolean innsatt = lege.settInn( fornavn.getText(), etternavn.getText(), adresse.getText(), Long.parseLong(nummerfelt.getText()));

				if(insatt)
				{
					String tekst = fornavn.getText() + " " + etternavn.getText() + " er registrert";
					Kompinent.popup(tekst);
				}
				else
				{

					utskrift.setText(navn.getText() + " er allerede registrert");

				navn.setText("");
				adresse.setText("");
				nummerfelt.setText("");
		}
	}*/
	public void finnLege()
	{
		String navn = finnLege.getText();
		lege.finnLege(navn);
	}
	public void skrivListe()
	{
		legeTextArea.setText( lege.toString());
	}

	private class ActionLytter implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			if( e.getSource() == finnLegeKnapp )
				finnLege();
			else if( e.getSource() == seLegeListeKnapp )
				skrivListe();
		}
	}
	private class ItemLytter implements ItemListener
	{
		public void itemStateChanged( ItemEvent e )
		{

		}
	}
}