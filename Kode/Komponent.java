/*	Denne klassen lager vanligt anvendte komponenter.
    Laget av Adrian Westlund
    Siste versjon 07-04-2014*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Komponent
{
	//Lager en komponent med en label og en textField på samme rad
    protected static JComponent labelFieldRow(String text, JTextField field)
    {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(text);
        panel.setLayout(new GridLayout(1, 2, 5, 5));
        panel.add(label);
        panel.add(field);

        return panel;
	}
	//Lager en komponent med en label og en textField på två rader
    protected static JComponent labelFieldColumb(String text, JTextField field)
    {
        JPanel panel = new JPanel(false);
        JLabel label = new JLabel(text);
        panel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        panel.add(field);
        return panel;
    }

    protected static JComponent labelComboBoxRow(String text, JComboBox<String> comboBox)
    {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(text);
		panel.setLayout(new GridLayout(1, 2, 5, 5));
		panel.add(label);
		panel.add(comboBox);

        return panel;
    }

    protected static JComponent labelComboBoxColumb(String text, JComboBox<String> comboBox)
    {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(text);
		panel.setLayout(new GridLayout(2, 1, 5, 5));
		panel.add(label);
		panel.add(comboBox);

        return panel;
    }
}