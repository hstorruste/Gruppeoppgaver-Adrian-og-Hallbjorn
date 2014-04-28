/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import View.*;
/**
 *
 * @author Hallbjørn
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        LegekontorVindu legeVindu = new LegekontorVindu();
        
        legeVindu.addWindowListener( new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }
    
}
