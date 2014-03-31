/*Dette er et program for å generere fiktive pasienter.
Laget av Hallbjørn Storruste*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class Navngenvindu extends JFrame
{
	private JButton start,generer10;
	private JTextArea utskrift;
	private KnappeLytter lytter;
	private Navngenerator generator;

	public Navngenvindu()
	{
		super("NavnGenerator");

		setLayout(new FlowLayout() );

		lytter = new KnappeLytter();
		generator = new Navngenerator();


		start = new JButton("Generer navn");
		start.addActionListener(lytter);
		add(start);

		generer10 = new JButton("Generer 10 navn");
		generer10.addActionListener(lytter);
		add(generer10);

		utskrift = new JTextArea(20,20);
		add(new JScrollPane(utskrift));

		setSize(400,400);
		setVisible(true);
	}

	public void generer()
	{

		String nyttNavn = generator.nesteNavn();

		skrivUt(nyttNavn);
	}

	public void gen10()
	{
		String[] navneliste= generator.nesteNavn(10);

		for(int i =0; i<navneliste.length;i++)
		{
			skrivUt(navneliste[i]);
		}
	}

	public void skrivUt(String tekst)
	{
		utskrift.append(tekst + "\n");
	}

	private class KnappeLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == start)
				generer();

			if(e.getSource() == generer10)
				gen10();
		}
	}
}