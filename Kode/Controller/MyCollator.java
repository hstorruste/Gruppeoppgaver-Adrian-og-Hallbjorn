package Controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**Denne klassen lager en tegnrekkefølge som gjelder for Norge og Sverige.
 * Den har static metode som returnerer en RuleBasedCollator.
 * Den brukes for å initialisere collatorer i serialiserte objekter.
 * Laget av Hallbjørn Storruste
 * Siste versjon: 08-04-2014
 * @author Hallbjørn
 */
import java.text.*;

public class MyCollator{
       
    private static final String rekkefølge = "<\0<0<1<2<3<4<5<6<7<8<9" +
                      "<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j" +
                     "<K,k<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t" +
             "<U,u<V,v<W,w<X,x<Y,y<Z,z<Æ,æ<Ø,ø<Å=AA,å=aa;AA,aa<Ä,ä<Ö,ö";
    
    public static RuleBasedCollator getCollator()
    {
        RuleBasedCollator personcollator = null;
        try
        {
            personcollator = new RuleBasedCollator(rekkefølge);
        }
        catch(ParseException pe)
        {
            System.out.println(pe.getMessage());
        }
        return personcollator;
    }
}
