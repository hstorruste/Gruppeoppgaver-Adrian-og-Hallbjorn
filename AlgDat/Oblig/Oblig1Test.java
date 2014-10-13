package Oblig;
import java.util.*;

public class Oblig1Test
{
  public static void main(String[] args)
  {
    int antallFeil = 0;

    //antallFeil += oppgave1();
    //antallFeil += oppgave2();
    //antallFeil += oppgave3();
    //antallFeil += oppgave4();
    //antallFeil += oppgave5();
    //antallFeil += oppgave6();
    //antallFeil += oppgave7();
    //antallFeil += oppgave8();
    //antallFeil += oppgave9();
    //antallFeil += oppgave10();

    if (antallFeil == 0)
    {
      System.out.println("Gratulerer!! Du passerte testen!");
    }
    else
    {
      System.out.println("Dette må forbedres. Du har minst " + antallFeil + " feil!");
    }
  }

  ///// Oppgave 1 //////////////////////////////////////

  public static int oppgave1()
  {
    int antallFeil = 0;

    boolean unntak = false;
    int[] tom = {};
    try
    {
      Oblig1.maks(tom);  // kaller maks-metoden
    }
    catch (Exception e)
    {
      unntak = true;
      if (!(e instanceof java.util.NoSuchElementException))
      {
        System.out.println("Opgave 1: Feil unntak for en tom tabell!");
        antallFeil++;
      }
    }

    if (!unntak)
    {
      System.out.println("Opgave 1: Kast unntak for en tom tabell!");
      antallFeil++;
    }

    int[] a = {3};
    int[] b = {5,2,8,4,7,6};
    int[] c = {5,4,3,2,1};
    int[] d = {1,2,3,4,5};
    if (Oblig1.maks(a) != 3 || Oblig1.maks(b) != 8 ||
        Oblig1.maks(c) != 5 || Oblig1.maks(d) != 5)
    {
      System.out.println("Oppgave 1: a) Metoden gir feil resultat!");
      antallFeil++;
    }

    int[] e = {1,4,3,7,6,5,10,2,9,8};
    int[] f = {1,3,4,6,5,7,2,9,8,10};

    Oblig1.maks(e);
    if(!Arrays.equals(e,f))
    {
      System.out.println("Oppgave 1: b) Feil i ombyttingene!");
      antallFeil++;
    }

    return antallFeil;
  }

  ///// Oppgave 2 //////////////////////////////////////

  public static int oppgave2()
  {
    int antallFeil = 0;

    int[] a = {};
    int[] b = {5};
    int[] c = {7,2,8,3,10,5,9,1,6,4};
    int[] d = {1,2,3,4,5,6,7,8,9,10};
    int[] e = {10,9,8,7,6,5,4,3,2,1};
    int[] f = {1,2,2,3,3,3,4,4,4,4,5,5,5,6,6,7};
    int[] g = {4,6,2,4,7,5,6,4,3,5,4,2,5,3,1,3};

    try
    {
      Oblig1.sortering(a);
    }
    catch (Exception ex)
    {
      System.out.println("Oppgave 2: a) Skal ikke kaste unntak for tom tabell!");
      antallFeil++;
    }

    Oblig1.sortering(b);  // skal ikke kaste unntak her!
    Oblig1.sortering(c);
    Oblig1.sortering(e);
    Oblig1.sortering(g);
    if (!Arrays.equals(c,d) || !Arrays.equals(e,d) || !Arrays.equals(f,g))
    {
      System.out.println("Oppgave 2: b) Metoden gir feil resultat!");
      antallFeil++;
    }

    return antallFeil;
  }

  ///// Oppgave 3 //////////////////////////////////////

  public static int oppgave3()
  {
    int antallFeil = 0;

    int[] a = {};
    int[] b = {1};
    int[] c = {1,2,3,4,5,4};

    try
    {
      Oblig1.antallUlikeSortert(a);  // kaller metoden
      Oblig1.antallUlikeSortert(b);  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println
        ("Oppgave 3: a) Ikke unntak for tabell med 0 eller 1 verdi!");
      antallFeil++;
    }

    boolean unntak = false;

    try
    {
      Oblig1.antallUlikeSortert(c);  // kaller metoden
    }
    catch (Exception e)
    {
      unntak = true;
      if (!(e instanceof IllegalStateException))
      {
        System.out.println
          ("Oppgave 3: b) Feil unntak for en usortert tabell!");
        antallFeil++;
      }
    }

    if (!unntak)
    {
      System.out.println
        ("Oppgave 3: c) Kast et unntak for en usortert tabell!");
      antallFeil++;
    }

    int[] d = {1,1};
    int[] e = {1,2,3,4,5,6,7};
    int[] f = {1,1,2,2,2,3,4,4,5,6,6,6,6,7};

    if (Oblig1.antallUlikeSortert(a) != 0
        || Oblig1.antallUlikeSortert(b) != 1
        || Oblig1.antallUlikeSortert(d) != 1
        || Oblig1.antallUlikeSortert(e) != 7
        || Oblig1.antallUlikeSortert(f) != 7)
     {
       System.out.println("Oppgave 3: d) Metoden gir feil resultat!");
       antallFeil++;
     }
    return antallFeil;
  }

  ///// Oppgave 4 //////////////////////////////////////

  public static int oppgave4()
  {
    int antallFeil = 0;

    int[] a = {};   // skal ikke kaste unntak her!
    int[] b = {1};  // skal ikke kaste unntak her!
    int[] c = {1,1};
    int[] d = {6,2,4,6,9,1,4,9,10};
    int[] dkopi = {6,2,4,6,9,1,4,9,10};
    int[] e = {5,4,3,2,1};
    int[] f = {1,2,2,2,2,2,3};

    try
    {
      Oblig1.antallUlikeUsortert(a);  // kaller metoden
      Oblig1.antallUlikeUsortert(b);  // kaller metoden
    }
    catch (Exception ex)
    {
      System.out.println
        ("Oppgave 4: a) Ikke unntak for tabell med 0 eller 1 verdi!");
      antallFeil++;
    }

    if (Oblig1.antallUlikeUsortert(a) != 0
        || Oblig1.antallUlikeUsortert(b) != 1
        || Oblig1.antallUlikeUsortert(c) != 1
        || Oblig1.antallUlikeUsortert(d) != 6
        || Oblig1.antallUlikeUsortert(e) != 5
        || Oblig1.antallUlikeUsortert(f) != 3)
    {
      System.out.println("Oppgave 4: b) Metoden gir feil resultat!");
      antallFeil++;
    }

    if (!Arrays.equals(d,dkopi))
    {
      System.out.println("Oppgave 4: c) Metoden endrer tabellen!");
      antallFeil++;
    }

    return antallFeil;
  }

  ///// Oppgave 5 //////////////////////////////////////  

  public static int oppgave5()
  {
    int antallFeil = 0;

    char[] a = {};

    try
    {
      Oblig1.rotasjon(a);  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println
        ("Oppgave 5: a) Skal ikke kaste unntak for en tom tabell!!");
        antallFeil++;
    }

    char[] b = {'A'};
    char[] b0 = {'A'};
    Oblig1.rotasjon(b);
    if (!Arrays.equals(b, b0))
    {
      System.out.println("Oppgave 5: b) Feil hvis tabellen har ett element!");
      antallFeil++;
    }

    char[] c = {'A','B'};
    char[] c0 = {'B','A'};
    Oblig1.rotasjon(c);
    if (!Arrays.equals(c, c0))
    {
      System.out.println("Oppgave 5: c) Feil hvis tabellen har to elementer!");
      antallFeil++;
    }

    char[] d = {'A','B','C','D','E','F','G','H','I','J'};
    char[] d0 = {'J','A','B','C','D','E','F','G','H','I'};

    Oblig1.rotasjon(d);
    if (!Arrays.equals(d, d0))
    {
      System.out.println("Oppgave 5: d) Feil hvis tabellen har flere elementer!");
      antallFeil++;
    }

    return antallFeil;

  }

  ///// Oppgave 6 //////////////////////////////////////  

  public static int oppgave6()
  {
    int antallFeil = 0;

    char[] a = {};

    try
    {
      Oblig1.rotasjon(a,1);  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println(e);
      System.out.println
        ("Oppgave 6: a) Skal ikke kaste unntak for en tom tabell!!");
        antallFeil++;
    }

    char[] b = {'A'};
    char[] b0 = {'A'};
    Oblig1.rotasjon(b,0);
    if (!Arrays.equals(b, b0))
    {
      System.out.println("Oppgave 6: b) Feil hvis tabellen har ett element!");
      antallFeil++;
    }

    Oblig1.rotasjon(b,1);
    if (!Arrays.equals(b, b0))
    {
      System.out.println("Oppgave 6: c) Feil hvis tabellen har ett element!");
      antallFeil++;
    }

    Oblig1.rotasjon(b,-1);
    if (!Arrays.equals(b, b0))
    {
      System.out.println("Oppgave 6: d) Feil hvis tabellen har ett element!");
      antallFeil++;
    }

    char[] c = {'A','B'};
    char[] c0 = {'B','A'};
    Oblig1.rotasjon(c,1);

    if (!Arrays.equals(c, c0))
    {
      System.out.println("Oppgave 6: e) Feil hvis tabellen har to elementer!");
      antallFeil++;
    }

    c = new char[] {'A','B'};

    Oblig1.rotasjon(c,-1);
    if (!Arrays.equals(c, c0))
    {
      System.out.println("Oppgave 6: f) Feil hvis tabellen har to elementer!");
      antallFeil++;
    }

    char[] d = {'A','B','C','D','E','F','G','H','I','J'};
    char[] d0 = {'G','H','I','J','A','B','C','D','E','F'};

    Oblig1.rotasjon(d,4);
    if (!Arrays.equals(d, d0))
    {
      System.out.println("Oppgave 6: g) Feil hvis tabellen har flere elementer!");
      antallFeil++;
    }

    d = new char[]{'A','B','C','D','E','F','G','H','I','J'};
    Oblig1.rotasjon(d,-6);
    if (!Arrays.equals(d, d0))
    {
      System.out.println("Oppgave 6: h) Feil hvis tabellen har flere elementer!");
      antallFeil++;
    }

    char[] x = new char[100_000];
    long tid = System.currentTimeMillis();
    Oblig1.rotasjon(x,99_999);
    tid = System.currentTimeMillis() - tid;

    if (tid > 20)
    {
      System.out.println("Oppgave 6: i) Metoden "
        + "er for ineffektiv. Må forbedres!");
      antallFeil++;
    }

    tid = System.currentTimeMillis();
    Oblig1.rotasjon(x,199_999);
    tid = System.currentTimeMillis() - tid;

    if (tid > 20)
    {
      System.out.println("Oppgave 6: j) Metoden "
        + "er for ineffektiv. Må forbedres!");
      antallFeil++;
    }

    tid = System.currentTimeMillis();
    Oblig1.rotasjon(x,50_000);
    tid = System.currentTimeMillis() - tid;

    if (tid > 20)
    {
      System.out.println("Oppgave 6: k) Metoden "
        + "er for ineffektiv. Må forbedres!");
      antallFeil++;
    }

    tid = System.currentTimeMillis();
    Oblig1.rotasjon(x,-40_000);
    tid = System.currentTimeMillis() - tid;

    if (tid > 20)
    {
      System.out.println("Oppgave 6: l) Metoden "
        + "er for ineffektiv. Må forbedres!");
      antallFeil++;
    }

    return antallFeil;
  }

  ///// Oppgave 7 //////////////////////////////////////  

  public static int oppgave7()
  {
    int antallFeil = 0;
    String s = null;

    try
    {
      s = Oblig1.flett("","");  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println(e);
      System.out.println
        ("Oppgave 7a: a) Skal ikke kaste unntak for to tomme tegnstrenger!!");
        antallFeil++;
    }

    if (s.compareTo("") != 0)
    {
      System.out.println
        ("Oppgave 7a: b) Svaret skal bli lik en tom streng!");
        antallFeil++;
    }

    try
    {
      s = Oblig1.flett("","AB");  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println(e);
      System.out.println
        ("Oppgave 7a: c) Skal ikke kaste unntak for en tom tegnstreng!!");
        antallFeil++;
    }

    if (s.compareTo("AB") != 0)
    {
      System.out.println
        ("Oppgave 7a: d) Svaret skal bli lik AB");
        antallFeil++;
    }

    try
    {
      s = Oblig1.flett("AB","");  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println(e);
      System.out.println
        ("Oppgave 7a: e) Skal ikke kaste unntak for en tom tegnstreng!!");
        antallFeil++;
    }

    if (s.compareTo("AB") != 0)
    {
      System.out.println
        ("Oppgave 7a: f) Svaret skal bli lik A");
        antallFeil++;
    }

    s = Oblig1.flett("A", "BCDEF");

    if (s.compareTo("ABCDEF") != 0)
    {
      System.out.println
        ("Oppgave 7a: g) Svaret skal bli lik ABCDEF");
        antallFeil++;
    }

    s = Oblig1.flett("ABCDE", "F");

    if (s.compareTo("AFBCDE") != 0)
    {
      System.out.println
        ("Oppgave 7a: h) Svaret skal bli lik AFBCDE");
        antallFeil++;
    }

    s = Oblig1.flett("ACEGIK", "BDFHJLMN");

    if (s.compareTo("ABCDEFGHIJKLMN") != 0)
    {
      System.out.println
        ("Oppgave 7a: i) Svaret skal bli lik ABCDEFGHIJKLMN");
        antallFeil++;
    }

    String[] a = {};

    try
    {
      s = Oblig1.flett(a);  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println(e);
      System.out.println
        ("Oppgave 7b: a) Skal ikke kaste unntak for en tom tabell!");
        antallFeil++;
    }

    if (s.compareTo("") != 0)
    {
      System.out.println
        ("Oppgave 7b: b) Svaret skal bli lik en tom streng!");
        antallFeil++;
    }

    try
    {
      s = Oblig1.flett("","ABC","");  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println(e);
      System.out.println
        ("Oppgave 7b: c) Skal ikke kaste unntak for en tom streng!");
        antallFeil++;
    }

    if (s.compareTo("ABC") != 0)
    {
      System.out.println
        ("Oppgave 7b: d) Svaret skal bli lik ABC");
        antallFeil++;
    }

    s = Oblig1.flett("AM ","L","GEDS","ORATKRR","","R TRTE","IO","TGAUU");

    if (s.compareTo("ALGORITMER OG DATASTRUKTURER") != 0)
    {
      System.out.println
        ("Oppgave 7b: e) Svaret skal bli ALGORITMER OG DATASTRUKTURER!");
        antallFeil++;
    }

    s = Oblig1.flett("AFK","BGLP","CHMQT","DINRUW","EJOSVXY");

    if (s.compareTo("ABCDEFGHIJKLMNOPQRSTUVWXY") != 0)
    {
      System.out.println
        ("Oppgave 7b: f) Svaret skal bli ABCDEFGHIJKLMNOPQRSTUVWXY!");
        antallFeil++;
    }

    return antallFeil;
  }

  ///// Oppgave 8 //////////////////////////////////////  

  public static void bytt(int[] a, int i, int j)
  {
    int temp = a[i]; a[i] = a[j]; a[j] = temp;
  }

  public static boolean nestePermutasjon(int[] a)
  {
    int n = a.length;
    int i = n - 2;

    while (i >= 0 && a[i] > a[i+1]) i--;

    if (i < 0) return false;

    int verdi = a[i];
    int j = n - 1;

    while (verdi > a[j]) j--;
    bytt(a, i, j);

    i++; j = n - 1;
    while (i < j) bytt(a, i++, j--);
    return true;
  }

  public static int[] randPerm(int n)  // en effektiv versjon
  {
    Random r = new Random();         // en randomgenerator
    int[] a = new int[n];            // en tabell med plass til n tall
    for (int i = 0; i < n; i++)
      a[i] = i + 1;                  // legger inn tallene 1, 2, . , n

    for (int k = n - 1; k > 0; k--)  // løkke som går n - 1 ganger
    {
      int i = r.nextInt(k+1);        // en tilfeldig tall fra 0 til k

      int temp = a[k];
      a[k] = a[i];
      a[i] = temp;
    }

    return a;                        // permutasjonen returneres
  }


  public static int oppgave8()
  {
    int antallFeil = 0;

    boolean unntak = false;
    int[] tabell = {1,2};
    try
    {
      Oblig1.tredjeMaks(tabell);  // kaller metoden
    }
    catch (Exception e)
    {
      unntak = true;
      if (!(e instanceof IllegalArgumentException))
      {
        System.out.println("Opgave 8: a) Feil unntak!");
        antallFeil++;
      }
    }

    if (!unntak)
    {
      System.out.println
        ("Opgave 8: b) Det skal kastes unntak for tabeller med for få verdier!");
      antallFeil++;
    }

    int[] a1 = {1,2,3};
    boolean flere1 = true;

    while (flere1)
    {
      int[] c = Oblig1.tredjeMaks(a1);

      if (a1[c[0]] != 3 || a1[c[1]] != 2 || a1[c[2]] != 1)
      {
        System.out.println("Oppgave 8: c) Feil når a = " + Arrays.toString(a1));
        antallFeil++;
        break;
      }
      flere1 = nestePermutasjon(a1);
    }

    int[] b = randPerm(10);
    int[] d = Oblig1.tredjeMaks(b);


    if (b[d[0]] != 10 || b[d[1]] != 9 || b[d[2]] != 8)
    {
      System.out.println("Oppgave 8: d) Feil når a = " + Arrays.toString(b));
      antallFeil++;
    }

    int[] x = {6,3,9,1,10,5,2,8,4,7};
    int[] y = x.clone();
    Oblig1.tredjeMaks(x);

    if (!Arrays.equals(x,y))
    {
      System.out.println
        ("Oppgave 8: f) Metoden gjør endringer i parametertabellen!");
      antallFeil++;
    }

    int[] a = {1,2,3,4,5,6};
    boolean flere2 = true;

    while (flere2)
    {
      int[] c = Oblig1.tredjeMaks(a);

      int m = c[0];
      int nm = c[1];
      int tm = c[2];

      if (a[m] != 6 || a[nm] != 5 || a[tm] != 4)
      {
        System.out.println("Oppgave 8: g) Feil når a = " + Arrays.toString(a));
        antallFeil++;
        break;
      }
      flere2 = nestePermutasjon(a);
    }

    return antallFeil;
  }

  ///// Oppgave 9 //////////////////////////////////////   

  public static int oppgave9()
  {
    int antallFeil = 0;
    boolean unntak = false;

    int[] a = {3,2,8,5,6,10,4,9,1,7};

    try
    {
      Oblig1.kMinst(a,0);  // kaller kMinst-metoden
    }
    catch (Exception e)
    {
      unntak = true;
      if (!(e instanceof IllegalArgumentException))
      {
        System.out.println("Opgave 9: a) Feil unntak for k < 1!");
        antallFeil++;
      }
    }

    if (!unntak)
    {
      System.out.println("Opgave 9: b) Skal kaste unntak for k < 1!");
      antallFeil++;
    }

    unntak = false;

    try
    {
      Oblig1.kMinst(a,a.length + 1);  // kaller kMinst-metoden
    }
    catch (Exception e)
    {
      unntak = true;
      if (!(e instanceof IllegalArgumentException))
      {
        System.out.println("Opgave 9: c) Feil unntak for k > a.length!");
        antallFeil++;
      }
    }

    if (!unntak)
    {
      System.out.println("Opgave 9: d) Skal kaste unntak for k > a.length!");
      antallFeil++;
    }

    int[] b = {1};
    int[] c = {1,2};
    int[] d = {1,2,3};
    int[] e = {1,2,3,4};
    int[] f = {1,2,3,4,5,6,7,8,9,10};

    int[] ac = a.clone();

    if (!Arrays.equals(b,Oblig1.kMinst(a,1)) ||
        !Arrays.equals(c,Oblig1.kMinst(a,2)) ||
        !Arrays.equals(d,Oblig1.kMinst(a,3)) ||
        !Arrays.equals(e,Oblig1.kMinst(a,4))||
        !Arrays.equals(f,Oblig1.kMinst(a,a.length)))
    {
      System.out.println("Oppgave 9: e) Metoden gir feil resultat!");
      antallFeil++;
    }

    if (!Arrays.equals(a,ac))
    {
      System.out.println
        ("Oppgave 9: f) Metoden gjør endringer i koden!");
      antallFeil++;
    }

    int[] g = {1};
    if (!Arrays.equals(g,Oblig1.kMinst(b,1)))
    {
      System.out.println
        ("Oppgave 9: g) Feil hvis tabellen har kun en verdi!!");
      antallFeil++;
    }

    int[] x = randPerm(20000);
    long tid = System.currentTimeMillis();
    Oblig1.kMinst(x, 10000);
    tid = System.currentTimeMillis() - tid;

    if (tid > 200)
    {
      System.out.println
        ("Oppgave 9: h) Metoden er altfor ineffektiv!");
      antallFeil++;
    }

    x = randPerm(1_000_000);
    tid = System.currentTimeMillis();
    Oblig1.kMinst(x,1);
    tid = System.currentTimeMillis() - tid;

    if (tid > 50)
    {
      System.out.println
        ("Oppgave 9: i) Metoden er altfor ineffektiv!");
      antallFeil++;
    }

    return antallFeil;
  }

  ///// Oppgave 10 //////////////////////////////////////  

  public static int oppgave10()
  {
    int antallFeil = 0;
    boolean b = false;

    try
    {
      b = Oblig1.inneholdt("","");  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println(e);
      System.out.println
        ("Oppgave 10: a) Skal ikke kaste unntak for to tomme ord!!");
        antallFeil++;
    }

    if (b != true)
    {
      System.out.println
        ("Oppgave 10: b) Svaret skal bli lik true her!");
        antallFeil++;
    }

    try
    {
      b = Oblig1.inneholdt("","A");  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println(e);
      System.out.println
        ("Oppgave 10: c) Skal ikke kaste unntak for et tomt ord!!");
        antallFeil++;
    }

    if (b != true)
    {
      System.out.println
        ("Oppgave 10: d) Svaret skal bli lik true her!");
        antallFeil++;
    }

    try
    {
      b = Oblig1.inneholdt("A","");  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println(e);
      System.out.println
        ("Oppgave 10: e) Skal ikke kaste unntak for et tomt ord!!");
        antallFeil++;
    }

    if (b != false)
    {
      System.out.println
        ("Oppgave 10: d) Svaret skal bli lik false her!");
        antallFeil++;
    }

    b = Oblig1.inneholdt("XYYX", "AAAAAAAYXXY");
    if (b != true)
    {
      System.out.println
        ("Oppgave 10: e) Svaret skal bli lik true her!");
        antallFeil++;
    }

    b = Oblig1.inneholdt("ABBA", "RABARBRA");
    if (b != true)
    {
      System.out.println
        ("Oppgave 10: f) Svaret skal bli lik true her!");
        antallFeil++;
    }

    b = Oblig1.inneholdt("ABBA", "BARBERER");
    if (b != false)
    {
      System.out.println
        ("Oppgave 10: g) Svaret skal bli lik false her!");
        antallFeil++;
    }

    b = Oblig1.inneholdt("ABBA", "AKROBAT");
    if (b != false)
    {
      System.out.println
        ("Oppgave 10: h) Svaret skal bli lik false her!");
        antallFeil++;
    }

    b = Oblig1.inneholdt("ØÅÅØ", "ØØÅØØ");
    if (b != false)
    {
      System.out.println
        ("Oppgave 10: i) Svaret skal bli lik false her!");
        antallFeil++;
    }

    b = Oblig1.inneholdt("ØÅÅØ", "ÅØØÅØ");
    if (b == false)
    {
      System.out.println
        ("Oppgave 10: j) Svaret skal bli lik true her!");
        antallFeil++;
    }


    char[] x = new char[100000];
    for (int i = 0; i < 50000; i++)
    {
      x[2*i] = 'X'; x[2*i + 1] = 'Y';
    }
    String t = String.copyValueOf(x);

    char[] y = new char[100000];
    for (int i = 0; i < 49999; i++)
    {
      y[2*i] = 'X'; y[2*i + 1] = 'Y';
    }
    y[99998] = 'Z'; y[99999] = 'Z';
    String s = String.copyValueOf(y);

    long tid = System.currentTimeMillis();
    b = Oblig1.inneholdt(s, t);
    tid = System.currentTimeMillis() - tid;

    if (tid > 100)
    {
      System.out.println
        ("Oppgave 10: k) Dette (" + tid + " ms) gikk sakte! Finn en bedre algoritme!");
        antallFeil++;
    }

    if (b != false)
    {
      System.out.println
        ("Oppgave 10: l) Svaret skal bli lik false her!");
        antallFeil++;
    }
    return antallFeil;
  }

} // class Oblig1Test
