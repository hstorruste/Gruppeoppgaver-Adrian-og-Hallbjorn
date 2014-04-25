/*	Denne klassen lager vanligt anvendte komponenter.
    Laget av Adrian Westlund
    Siste versjon 25-04-2014*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;

public class Komponent
{
	private static Font font = new Font("SansSerif", Font.PLAIN, 16);

	//	Lager en komponent med en label og en textField p� samme rad
    protected static JComponent labelFieldRow(String text, JTextField field)
    {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(text);
        panel.setLayout(new GridLayout(1, 2, 5, 5));
        panel.add(label);
        panel.add(field);

        return panel;
	}
	//	Lager en komponent med en label og en textField p� tv� rader
    protected static JComponent labelFieldColumb(String text, JTextField field)
    {
        JPanel panel = new JPanel(false);
        JLabel label = new JLabel(text);
        panel.setLayout(new GridLayout(2, 1, 5, 5));
        panel.add(label);
        panel.add(field);
        return panel;
    }
	//	Lager en komponent med en label og en combobox p� samme rad
    protected static JComponent labelComboBoxRow(String text, JComboBox<String> comboBox)
    {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(text);
		panel.setLayout(new GridLayout(1, 2, 5, 5));
		panel.add(label);
		panel.add(comboBox);

        return panel;
    }
	//	Lager en komponent med en label og en combobox p� tv� rader
    protected static JComponent labelComboBoxColumb(String text, JComboBox<String> comboBox)
    {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(text);
		panel.setLayout(new GridLayout(2, 1, 5, 5));
		panel.add(label);
		panel.add(comboBox);

        return panel;
    }
    /*	Metoden �ndrar font genom att alla komponenter av Container
    	blir kallade p� och s�tter den best�mda fonten p� dessa.*/
    protected static void endreFont( Component komponent )
	{
		komponent.setFont( font );
		if ( komponent instanceof Container )
		{
			for ( Component child : (( Container ) komponent ).getComponents ())
			{
				endreFont( child);
			}
		}
	}
	// Endrer ikon
	protected static void bilde(JFrame klass)
	{
		String bildefil = "Handprint.png";
		URL kilde = Komponent.class.getResource(bildefil);

		if (kilde != null)
		{
			ImageIcon bilde = new ImageIcon(kilde);
			Image ikon = bilde.getImage();
			klass.setIconImage(ikon);
		}
	}
	//	Lager ett popup box
	protected static void popup(JFrame klass, String tekst)
	{
		 JOptionPane.showMessageDialog(klass, tekst);
	}
}