package Controller;

import Model.*;
import java.io.*;
import java.text.DateFormat;
import java.text.RuleBasedCollator;
import java.util.Comparator;

/**
 * Dette er en comparator for resept-objekter. 
 * Laget av Hallbjørn Storruste s165519
 * Siste versjon: 09-04-2014
 *
 * @author Hallbjørn
 */
public class ReseptComparator implements Comparator<Resept>, Serializable {

    private static final long serialVersionUID = 1012L;

    private transient RuleBasedCollator kollator;

    public ReseptComparator() {
        initCollator();
    }

    //Initialiserer collatoren
    private void initCollator() {
        kollator = MyCollator.getCollator();
    }

    //Metode for serialisering. Skriver objektet til strømmen.
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
    /*Metode for serialisering. Leser objektet fra strømmen, 
     oppretter datafeltene og initialiserer collatoren på nytt.*/

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        initCollator();
    }

    @Override
    /*Sammenligner reseptene på dato de er utskrevet.*/
    public int compare(Resept r1, Resept r2) {
                
        String resept1 = String.valueOf(r1.getDato().getTimeInMillis());
        String resept2 = String.valueOf(r2.getDato().getTimeInMillis());

        int d = kollator.compare(resept1, resept2);

        return d;
    }
}
