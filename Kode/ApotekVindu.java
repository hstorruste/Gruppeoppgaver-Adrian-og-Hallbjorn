/*  Denne klassen er et GUI til apotek.
    Laget av Adrian Westlund s198571.
    Siste versjon 22-04-2014*/

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ApotekVindu extends JFrame
{
	Font font = new Font("SansSerif", Font.PLAIN, 16);

    public ApotekVindu()
    {
        super("Apotek");

		setLayout(new GridLayout(0, 1, 40, 40));

		JPanel apotekFinn = new JPanel(new GridLayout(0, 1, 5, 5));
		apotekFinn.add(apotekLoggInnGUI());

		add(apotekFinn);
      	bilde();

		endreFont(this, font);
		pack();
		setVisible(true);
    }
    private void bilde()
    {
		String bildefil = "Handprint.png";
		URL kilde = AdminVindu.class.getResource(bildefil);

		if (kilde != null)
		{
			ImageIcon bilde = new ImageIcon(kilde);
			Image ikon = bilde.getImage();
			setIconImage(ikon);
		}
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
	public JPanel apotekLoggInnGUI()
	{
		JPanel apotekFinn = new JPanel(new GridLayout(0, 1, 5, 5));

		JTextField finnPasient = new JTextField(5);
		JComponent kompFinnPasient = Komponent.labelFieldRow("Fødselsnummer", finnPasient);
		apotekFinn.add(kompFinnPasient);


		JButton finnPasientKnapp = new JButton("Finn");
		finnPasientKnapp.setPreferredSize(new Dimension(113, 20));
		//finnPasientKnapp.addActionListener( actionLytter );
		JPanel finnPasientKnappPanel = new JPanel(new BorderLayout());
		finnPasientKnappPanel.add(finnPasientKnapp, BorderLayout.LINE_END);
		apotekFinn.add(finnPasientKnappPanel);

		return apotekFinn;
	}
}