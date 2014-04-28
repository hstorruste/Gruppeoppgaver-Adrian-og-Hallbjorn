package Controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**Dette er en comporator for medisin-objekter.
 * Laget av Hallbjørn Storruste
 * Siste versjon: 08-04-2014
 * @author Hallbjørn
 */
import Model.*;
import java.io.*;
import java.text.RuleBasedCollator;
import java.util.Comparator;

public class MedisinComparator implements Comparator<Medisin>, Serializable {
    private static final long serialVersionUID = 1010L;

    private transient RuleBasedCollator kollator;

    public MedisinComparator()
    {
       initCollator();
    }
    //Initialiserer collatoren
    private void initCollator()
    {
        kollator = MyCollator.getCollator();
    }
    //Skriver objektet til strømmen
    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
    }
    //leser objektet fra strømmen og initialiserer collatoren
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        initCollator();     
    }
    
    @Override
    public int compare( Medisin m1, Medisin m2 )
    {
        String med1 = m1.getNavn();
        String med2 = m2.getNavn();
        
        int d = kollator.compare(med1, med2);
        
        return d;
    }

    
}
