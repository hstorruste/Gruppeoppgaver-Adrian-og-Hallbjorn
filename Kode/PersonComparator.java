/*Gir rekkefølge til Person-objekter sortert på etternavn og fornavn, deretter på
	fødselsnummer eller epost, henholdsvis hvis det er Pasient- eller Lege-objekt
	I tilfelle Pasient kontrollerer den at det ikke finnes noen med samme fødselsnummer.
	I tilfelle Lege kontrollerer den at det ikke finnes noen med samme epost.
	Laget av Hallbjørn Storruste, s165519
	Siste versjon 03-04-2014*/

import java.text.*;
import java.util.*;
import java.io.Serializable;

public class PersonComparator implements Comparator<Person>, Serializable
{
	private static final long serialVersionUID = 1006L;

	private String rekkefølge = "<\0<0<1<2<3<4<5<6<7<8<9" +
	                  "<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j" +
	                 "<K,k<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t" +
                 "<U,u<V,v<W,w<X,x<Y,y<Z,z<Æ,æ<Ø,ø<Å=AA,å=aa;AA,aa<Ä,ä<Ö,ö";

  private RuleBasedCollator kollator;

  public PersonComparator()
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

	public int compare( Person p1, Person p2 )
	{
		if(p1 instanceof Pasient)
		{
			Pasient pas1 = (Pasient)p1;
			Pasient pas2 = (Pasient)p2;

			String fnr1 = "" + pas1.getFnr();
			String fnr2 = "" + pas2.getFnr();

			int d = kollator.compare(fnr1, fnr2);

			if( d == 0 )
				return d;
			else
				d = compareNavn(pas1, pas2);

			if( d != 0 )
				return d;
			else
				return kollator.compare(fnr1, fnr2);
		}
		else
		{
			Lege lege1 = (Lege)p1;
			Lege lege2 = (Lege)p2;

			String ep1 = lege1.getEPost();
			String ep2 = lege2.getEPost();

			int d = kollator.compare(ep1, ep2);

			if( d == 0 )
				return d;
			else
				d = compareNavn(lege1, lege2);

			if( d != 0 )
				return d;
			else
				return kollator.compare(ep1, ep2);
		}
	}
	public int compareNavn(Person p1, Person p2)
	{
		String e1 = p1.getEtternavn();
		String e2 = p2.getEtternavn();
		String f1 = p1.getFornavn();
		String f2 = p2.getFornavn();
		int	d = kollator.compare( e1, e2 );

		if ( d != 0 )
			return d;
		else
			return kollator.compare( f1, f2 );
	}
}