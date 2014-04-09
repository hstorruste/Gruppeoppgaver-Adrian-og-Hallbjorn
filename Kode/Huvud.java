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
	}
}