/*  Hallbjørn Storruste - s165519, HINGDATA13H2AA
    Adrian Westlund - s198571, INFORMATIK13H2IA


*/

package Oblig;

import hjelpeklasser.Tabell;
import java.util.Arrays;
import java.util.NoSuchElementException;


public class Oblig1 
{
    /*Hjelpemetoder hentet fra forelesning*/
    public static boolean erSortertStigende(int[] a)
    {
      for (int i = 1; i < a.length; i++)
        if (a[i-1] > a[i]) return false;  // ikke sortert stigende

      return true;  // a er sortert stigende
    }
    
    public static void bytt(int[] a, int i, int j)
    {
        int temp = a[i]; a[i] = a[j]; a[j] = temp;
    }
    
    
    public static int parter(int[] a, int v, int h, int skilleverdi)
    {
      while (v <= h && a[v] < skilleverdi) v++;   // h er stoppverdi for v
      while (v <= h && skilleverdi <= a[h]) h--;  // v er stoppverdi for h

      while (true)
      {
        if (v < h) bytt(a,v++,h--);   // bytter om a[v] og a[h]
        else  return v;                      // partisjoneringen er ferdig
        while (a[v] < skilleverdi) v++;      // flytter v mot høyre
        while (skilleverdi <= a[h]) h--;     // flytter h mot venstre
      }
    }

    public static int parter(int[] a, int skilleverdi)
    {
      return parter(a,0,a.length-1,skilleverdi);  // kaller metoden over
    }
    public static int sParter(int[] a, int v, int h, int indeks)
    {
      if (indeks < v || indeks > h) throw new IllegalArgumentException();

      bytt(a,h,indeks);
      int k = parter(a,v,h-1,a[h]);
      bytt(a,h,k);
      return k;
    }

    public static int sParter(int[] a, int k)   // bruker hele tabellen
    {
      return sParter(a,0,a.length-1,k);   // v = 0 og h = a.lenght - 1
    }
    
    public static void kvikksortering(int[] a)
    {
      kvikksortering(a, 0, a.length - 1);
    }

    private static void kvikksortering(int[] a, int v, int h)
    {
      if (v >= h) return;

      int m = (v + h)/2;

      int p = sParter(a, v, h, m);

      kvikksortering(a, v, p - 1);
      kvikksortering(a, p + 1, h);
    }
    
    public static void fratilKontroll(int tablengde, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
          throw new ArrayIndexOutOfBoundsException
            ("fra(" + fra + ") er negativ!");

        if (til > tablengde)                          // til er utenfor tabellen
          throw new ArrayIndexOutOfBoundsException
            ("til(" + til + ") > tablengde(" + tablengde + ")");

        if (fra > til)                                // fra er større enn til
          throw new IllegalArgumentException
            ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }
    
    public static int binærsøk(int[] a, int fra, int til, int verdi)
    {
      fratilKontroll(a.length,fra,til);
      int v = fra, h = til - 1;  // v og h er intervallets endepunkter

      while (v < h)  // obs. må ha v < h her og ikke v <= h
      {
        int m = (v + h)/2;  // heltallsdivisjon - finner midten

        if (verdi > a[m]) v = m + 1;   // verdi må ligge i a[m+1:h]
        else  h = m;                   // verdi må ligge i a[v:m]
      }
      if (h < v || verdi < a[v]) return -(v + 1);  // ikke funnet
      else if (verdi == a[v]) return v;            // funnet
      else  return -(v + 2);                       // ikke funnet
    }
    
    
    /*  Oppgave 1.
        Det blir n - 1 sammenligninger av tabellverdier.
        Det blir færrest ombyttinger hvis verdiene ligger i kronologisk
        rekkefølge fra lavest til høyest( 0 ombyttinger ). Det blir flest 
        ombyttinger hvis tabellen har den største verdien på indeksplass 0 
        ( n-1 obyttinger).
        Gjennomsnittlig antall ombyttinger går mot n-1 hvis n er stor nok.
        For å finne maks er det ikke en god metode. Man trenger ikke gjøre 
        ombyttninger.
    */
    public static int maks(int [] a)
    {
        int n = a.length; //setter n lik tabellens lengde
        
        if(n == 0) throw //Gir feilmelding om tabellen er tom
            new NoSuchElementException("Parametertabell a er tom!");
        
        if(n == 1)
            return a[0];
        
        for(int i = 0; i < n - 1; i++)
        { 
           int v = a[i];
           int h = a[i+1];
           
            if (v > h)
            {
                a[i+1] = v;
                a[i] = h;
            }
        }
        return a[n-1];
    }
    
    /*Oppgave 2
    
    Antall sammenligninger: (n-1)*n/2
    Den er av kvadratisk orden.
    */
    public static void sortering(int [] a)
    {
        int n = a.length;
        for(int i = n; i > 1; i--)
        {
            for(int j = 0; j < i - 1; j++)
            { 
               int v = a[j];
               int h = a[j+1];

                if (v > h)
                {
                    a[j+1] = v;
                    a[j] = h;
                }
            }
        }        
    }
    
    /*Oppgave 3
    
    */
    public static int antallUlikeSortert(int[] a)
    {
        int n = a.length;
        if(n == 0)
            return 0;
        
        if(!erSortertStigende(a))
            throw new IllegalStateException("Tabellen er ikke sortert");
        
        int antall = 1;
        int tall = a[0];
        
        for(int i = 1; i < n; i++)
        {
            int x = a[i];
            if(x != tall)
            {
                tall = x;
                antall++;
            }
        }
        
        return antall;
    }
    
    /*Oppgave 4
    */
    public static int antallUlikeUsortert(int[] a)
    {
        int n = a.length;
        int antall = 0;
        
        
        for(int i = 0; i < n; i++)
        {
            boolean lik = false;
            
            for (int j = 0; j < i; j++) 
            {
                if(a[i] == a[j]){
                    lik = true;
                    break;
                }
            }
            if(!lik) antall++;
        }
        
        return antall;
    }
    
    /*Oppgave 5
    
    */
    public static void rotasjon(char[] a)
    {
       
        int n = a.length;
        
        if(n == 0)
            return;
        
        char temp = a[n-1]; // tar vare på siste verdi
        
        for(int i = n-1; i > 0; i--)
            a[i] = a[i-1];
        
        a[0] = temp; //legger siste verdi forrest
    }
    
    /*Oppgave 6
    
    */
    public static void rotasjon(char[] a, int k)
    {
        int n = a.length;
        
        if(n <= 1)
            return;
        
        k = k%n; //Fjerner overflødige fulle rotasjoner
        
        
        if(k > 0)
        {
            char[] temp = new char[k];
            for(int i = n, j = k; j > 0; i--, j--)
                temp[j-1] = a[i-1]; //Tar vare på de k bakerste bokstavene

            for(int i = n-1; i-k >= 0; i--)
                a[i] = a[i-k]; //roterer

            for(int i = 0; i < k; i++)
                a[i] = temp[i]; // Legger tilbake 
        }
        else
        {
            k = k *(-1);
            char[] temp = new char[k];
            for(int i = 0; i < k; i++)
                temp[i] = a[i]; //Tar vare på de k forreste bokstavene

            for(int i = k; i < n; i++)
                a[i-k] = a[i]; //roterer

            for(int i = 0; i < k; i++)
                a[n + i - k] = temp[i]; // Legger tilbake 
        }
        
        
    }
    
    /*Oppgave 7 a)
    
    */
    public static String flett(String s, String t)
    {
        int ns = s.length();
        int nt = t.length();
        StringBuilder ut = new StringBuilder(ns + nt);
        
        int lengde = ns >= nt ? ns : nt;
        for(int i = 0; i < lengde; i++)
        {
            if(i < ns)
                ut.append(s.charAt(i));
            
            if(i < nt)
                ut.append(t.charAt(i));
        }
        return ut.toString();
    }
    
    /*Oppgave 7 b)
    
    */
    public static String flett(String... s)
    {
        int n = s.length;
        
        if(n == 0) return "";
        
        int lengst = s[0].length();
        int lengde = lengst;
        
        for(int i = 1; i < n; i++)
        {
            lengde += s[i].length();
            if(s[i].length() > lengst)
                lengst = s[i].length();
        }
        StringBuilder ut = new StringBuilder(lengde);
        
        for (int i = 0; i < lengst; i++)
            for (int j = 0; j < n; j++)
                if(i < s[j].length())
                    ut.append(s[j].charAt(i));
            
        return ut.toString();
    }
    /*Oppgave 8 a)
    
    */
    public static int[] tredjeMaks(int[] a)
    {
      int n = a.length;     // tabellens lengde
      if (n < 3) throw      // må ha minst to verdier
        new IllegalArgumentException("a.length(" + n + ") < 3!");

      int m = 0;      // m er posisjonen til største verdi
      int nm = 1;     // nm er posisjonen til nest største verdi
      int tm = 2;     // nm er posisjonen til tredje største verdi
      int temp;
      
      if (a[tm] > a[nm]) { temp = nm; nm = tm; tm = temp; }
      
      if (a[nm] > a[m]) { temp = m; m = nm; nm = temp; }
      
      if (a[tm] > a[nm]) { temp = nm; nm = tm; tm = temp; }

      int maksverdi = a[m];                // største verdi
      int nestmaksverdi = a[nm];           // nest største verdi
      int tredjemaksverdi = a[tm];          // tredje største verdi

        for (int i = 3; i < n; i++)
        {
            if( a[i] > tredjemaksverdi)
            {
                if (a[i] > nestmaksverdi)
                {
                    if (a[i] > maksverdi)
                    {
                        tm = nm;
                        nm = m;
                        tredjemaksverdi = nestmaksverdi;
                        nestmaksverdi = maksverdi;     // ny nest størst

                        m = i;
                        maksverdi = a[m];              // ny størst
                    }
                    else
                    {
                        tm = nm;
                        nm = i;
                        tredjemaksverdi = nestmaksverdi;
                        nestmaksverdi = a[nm];         // ny nest størst
                    }
                }
                else
                {
                    tm = i;
                    tredjemaksverdi = a[tm];
                }
            }
        } // for

      return new int[] {m,nm,tm};    // n i posisjon 0, nm i posisjon 1

    } // nestMaks
    
    /*Oppgave 8 b)
    
    */
    public static void tredjeMaksTest()
    {
        int[] a = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        
        int[] b = tredjeMaks(a);
        if (b[0] != 0 || b[1] != 1 || b[2] != 2)
            System.out.println("Kodefeil: Gir feil posisjon for maksverdien!");
        
        bytt(a, 1, 2); //{10, 8, 9, ...}
        
        b = tredjeMaks(a);
        if (b[0] != 0 || b[1] != 2 || b[2] != 1)
            System.out.println("Kodefeil: Gir feil posisjon for maksverdien!");
        
        bytt(a, 0, 2); //{9, 8, 10, ...}
        
        b = tredjeMaks(a);
        if (b[0] != 2 || b[1] != 0 || b[2] != 1)
            System.out.println("Kodefeil: Gir feil posisjon for maksverdien!");
        
        bytt(a, 1, 2); //{9, 10, 8, ...}
        
        b = tredjeMaks(a);
        if (b[0] != 1 || b[1] != 0 || b[2] != 2)
            System.out.println("Kodefeil: Gir feil posisjon for maksverdien!");
        
        bytt(a, 0, 2); //{8, 10, 9, ...}
        
        b = tredjeMaks(a);
        if (b[0] != 1 || b[1] != 2 || b[2] != 0)
            System.out.println("Kodefeil: Gir feil posisjon for maksverdien!");
        
        bytt(a, 1, 2); //{8, 9, 10, ...}
        
        b = tredjeMaks(a);
        if (b[0] != 2 || b[1] != 1 || b[2] != 0)
            System.out.println("Kodefeil: Gir feil posisjon for maksverdien!");
        
        
        
        for(int lengde = 0; lengde < 3; lengde++)
        {
            a = new int[lengde];  
            boolean unntak = false;

            try
            {
                tredjeMaks(a);
            }
            catch (Exception e)
            {
                unntak = true;
                if (!(e instanceof IllegalArgumentException))
                    System.out.println("Kodefeil: Feil unntak for en tabell med lengde " + lengde + "!");
            }

            if (!unntak)
                System.out.println("Kodefeil: Mangler unntak for en tabell med lengde " + lengde + "!");
        }
    }
    
   
    
     /*Oppgave 9
    
    */
    public static int[] kMinst(int[] a, int k)
    {
        int n = a.length;
        
        if(k < 1)
            throw new IllegalArgumentException("Parameterverdien k(" + k + ") får ikke være mindre enn 1");
        if(k > n)
            throw new IllegalArgumentException("Parameterverdien k(" + k + ") er lengere enn parametertabellen");
        
        int[] verdier = new int[k];
        
        for(int i = 0; i < k; i++)
        {
            verdier[i] = a[i];
        }
        
        if(!erSortertStigende(verdier)) 
            kvikksortering(verdier);
        
        for(int i = k; i < n; i++)
        {
            if(verdier[k-1] > a[i])
            {
                int pos = binærsøk(verdier,0,k,a[i]);

               
                if (pos < 0) 
                    pos = -(pos + 1);  // pos er nå innsettingspunkt

                // flytter verdier mot høyre for å gjøre plass
                System.arraycopy(verdier, pos, verdier, pos + 1, k-1 - pos);

                verdier[pos] = a[i];  
            }
        }
        
        return verdier;        
    }
      
    /*Oppgave 10
    
    */
    public static boolean inneholdt(String a, String b)
    {
        if(a.length() > b.length()) 
            return false;
        
        char[] atable = a.toCharArray();
        char[] btable = b.toCharArray();
        Arrays.sort(atable);
        Arrays.sort(btable);
        
        int i = 0;
        int j = 0;
        
        for (; i < atable.length; i++, j++) 
        {
            for(; j < btable.length && atable[i] > btable[j]; j++);
            if(j >= btable.length || atable[i] < btable[j])
                return false;
        }
        
        return true;
    }
}
