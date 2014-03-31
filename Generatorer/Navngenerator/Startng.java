
import java.awt.event.*;

public class Startng
{
	public static void main(String[] args)
	{
		Navngenvindu vindu = new Navngenvindu();

		vindu.addWindowListener( new WindowAdapter()
		{
			public void windowClosing( WindowEvent e )
			{
				System.exit(0);
			}
		});
	}
}