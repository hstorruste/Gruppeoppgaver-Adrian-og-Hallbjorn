
import java.awt.event.*;

public class startGenPasient
{
	public static void main(String[] args)
	{
		PasientGenerator vindu = new PasientGenerator();

		vindu.addWindowListener( new WindowAdapter()
		{
			public void windowClosing( WindowEvent e )
			{
				System.exit(0);
			}
		});
	}
}