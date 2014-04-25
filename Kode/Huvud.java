/*	Denne klassen er main.
    Laget av Adrian Westlund
    Siste versjon 04-04-2014*/

import java.awt.event.*;

public class Huvud
{
	public static void main(String[] args)
	{
		AdminVindu vindu = new AdminVindu();

		vindu.addWindowListener( new WindowAdapter()
		{
			public void windowClosing( WindowEvent e )
			{
				System.exit(0);
			}
		});
		/*ApotekVindu apVindu = new ApotekVindu();

		apVindu.addWindowListener( new WindowAdapter()
		{
			public void windowClosing( WindowEvent e )
			{
				System.exit(0);
			}
		});*/
	}
}