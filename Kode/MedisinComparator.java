/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hallbjørn
 */
import java.io.*;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.Comparator;

public class MedisinComparator implements Comparator<Medisin>, Serializable {
    private static final long serialVersionUID = 1010L;
    
    private final String rekkefølge = "<\0<0<1<2<3<4<5<6<7<8<9" +
                      "<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j" +
                     "<K,k<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t" +
             "<U,u<V,v<W,w<X,x<Y,y<Z,z<Æ,æ<Ø,ø<Å=AA,å=aa;AA,aa<Ä,ä<Ö,ö";

    private RuleBasedCollator kollator;

    public MedisinComparator()
    {
        try
        {
                kollator = new RuleBasedCollator(rekkefølge);
        }
        catch( ParseException pe )
        {
                System.out.println("Feil i rekkefølge for kollator");
        }
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
