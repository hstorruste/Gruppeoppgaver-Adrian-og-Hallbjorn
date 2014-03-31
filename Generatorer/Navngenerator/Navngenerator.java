/*Dette er et program for å generere fiktive navn.
Laget av Hallbjørn Storruste*/

import java.util.Random;

public class Navngenerator
{
	private char[] vok, kon, dobble, vokAlene, konSlutt, ikkeSlutt;


	public Navngenerator()
	{

		vok = new char[]{'a','a','a','a','a','e','e','e','e','e','i','i','i','i','i','o','o','u','u','y','ø','å'};
		kon = new char[]{'b','b','d','d','f','g','g','h','j','k','k','l','l','m','m','n','n','n','n','p','r','r','r','r','s','s','s','s','s','s','t','t','t','t','t','t','v'};
		dobble = new char[]{'b','d','f','g','k','l','m','n','p','r','s','t'};
		vokAlene = new char[]{'o','y','ø','å'};
		konSlutt = new char[]{'m','r'};
		ikkeSlutt = new char[]{'j','u','h','v'};
	}

	public String nesteNavn()
	{
		String nyttNavn = genererNavn() + " " + genererNavn();

		return nyttNavn;
	}

	public String[] nesteNavn(int ant)
	{
		String navnliste[] = new String[ant];

		for(int i=0; i < ant; i++)
		{
			navnliste[i] = nesteNavn();
		}

		return navnliste;
	}

	public String genererNavn()
	{
		String navn = "";
		Random generator = new Random();
		int lengde = generator.nextInt(7) + 3;
		boolean vokal = false;

		for(int i=0;i<lengde;i++)
		{
			boolean dobbel = generator.nextBoolean();

			if(i==0)
				vokal = generator.nextBoolean(); //trekker om det er vokal eller konsonant først

			if(vokal)//så trekk vokal
			{
				int v = generator.nextInt(vok.length);

				if(i==lengde-1)//sjekker om det er siste bokstav
					while(iIkkeSlutt(vok[v]))
						v = generator.nextInt(vok.length);

				navn += vok[v];

				if(dobbel && i!=0 && generator.nextInt(4) < 1 && !iVokalAlene(vok[v]))
				{
					do
					{
						v =	generator.nextInt(vok.length);

					}while((vok[v] == navn.charAt(i)) || iVokalAlene(vok[v]));	//sjekker at det ikke blir to like vokaler etter hverandre

					navn += vok[v];
					i++;
				}
				vokal = false;
			}
			else // så trekk konsonant
			{
				int k = generator.nextInt(kon.length);

				if(i==lengde-1)
					while(iIkkeSlutt(kon[k]))
						k = generator.nextInt(kon.length);

				navn += kon[k];

				if(dobbel && i!=0 && iDobble(kon[k]))
				{

					if(i < lengde-2)//sjekker at vi ikke er på slutten av navnet.
					{
						navn += kon[k];
						i++;
					}
					else if(!iIkkeDobbelSlutt(kon[k]))
					{
						navn += kon[k];
						i++;
					}

				}
				vokal = true;
			}
		}

		navn = Character.toUpperCase(navn.charAt(0)) + navn.substring(1);
		return navn;
	}

	//Sjekk om vokalen må stå alene
	public boolean iVokalAlene(char c)
	{
		for(int j=0; j < vokAlene.length;j++)
			if(vokAlene[j] == c)
				return true;

		return false;
	}

	//Sjekk om konsonanten kan være dobbel
	public boolean iDobble(char c)
	{
		for(int j=0; j < dobble.length;j++)
			if(dobble[j] == c)
				return true;

		return false;
	}

	//Sjekk om konsonanten ikke kan være dobbel i slutten av et navn
	public boolean iIkkeDobbelSlutt(char c)
	{
		for(int m=0; m < konSlutt.length;m++)
			if(konSlutt[m] == c)
				return true;

		return false;
	}

	//Sjekk om navnet kan slutte på bokstaven
	public boolean iIkkeSlutt(char c)
	{
		for(int i=0;i<ikkeSlutt.length;i++)
			if(c == ikkeSlutt[i])
				return true;

		return false;
	}
}