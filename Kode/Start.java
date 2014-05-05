/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Controller.Legeregister;
import Controller.Medisinregister;
import Controller.Pasientregister;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import View.*;
import java.io.*;
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
        
        Legeregister legeregister = new Legeregister();
        Pasientregister pasientregister = new Pasientregister();
        Medisinregister medisinregister = new Medisinregister();

        try(ObjectInputStream innfil = new ObjectInputStream( new FileInputStream(Komponent.dataFil)))
        {
            medisinregister = (Medisinregister)innfil.readObject();
            legeregister = (Legeregister)innfil.readObject();
            pasientregister = (Pasientregister)innfil.readObject();
        }catch (ClassNotFoundException cnfe) {
            System.out.println("Oppretter tom liste");      
        } catch (FileNotFoundException fnfe) {
            System.out.println("Finner ikke fil. Oppretter tom liste");
        } catch (IOException ioe) {
            System.out.println("Leseproblemer. Oppretter tom liste");
            System.out.println(ioe.getMessage());
        }
        
        final LegekontorVindu legeVindu = new LegekontorVindu(legeregister, pasientregister, medisinregister);
        final AdminVindu adminVindu = new AdminVindu(legeregister, pasientregister, medisinregister);
        final ApotekVindu apotekVindu = new ApotekVindu(legeregister, pasientregister, medisinregister);
        
        legeVindu.addWindowListener( new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                legeVindu.skrivTilFil();
                System.exit(0);  
            }
        });
        adminVindu.addWindowListener( new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                adminVindu.skrivTilFil();
                System.exit(0);
            }
        });
        apotekVindu.addWindowListener( new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                apotekVindu.skrivTilFil();
                System.exit(0);
            }
        });
    }
    
}
