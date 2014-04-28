/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Hallbjørn
 */
public class LegekontorFinnPasient extends JTabbedPane{
    
    LegekontorVindu parentFrame;
    
    private JTextField fodselsnrFinnFelt, fornavnFelt, etternavnFelt, fodselsnrFelt;
    private JButton finn, registrer;
    
    private KnappeLytter knappeLytter;
    
    private final int TEKSTFELTLENGDE = 20;
    
    public LegekontorFinnPasient(LegekontorVindu p)
    {
        super();
        parentFrame = p;
        
        knappeLytter = new KnappeLytter();
        
        JPanel finn = new JPanel(new FlowLayout());
        finn.add(finnGUI());
        
        JPanel registrer = new JPanel(new FlowLayout());
        registrer.add(registrerGUI());
       
        addTab("Finn pasient", finn);
        addTab("Registrer pasient", registrer);
    }
    
    private JPanel finnGUI()
    {
        
    }
    
    private JPanel registrerGUI()
    {
        
    }
    
     private class KnappeLytter implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent e)
        {
            if(e.getSource() == )
           
            else
              
        }
    }
}
