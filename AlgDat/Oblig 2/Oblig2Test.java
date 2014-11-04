
package Oblig;

import hjelpeklasser.*;
import java.util.*;

public class Oblig2Test
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
      System.out.println
        ("Dette må forbedres. Du har minst " + antallFeil + " feil!");
    }
  }

  ////// Oppgave 1 /////////////////////

  public static int oppgave1()
  {
    int antallFeil = 0;
    Liste<Integer> liste = new DobbeltLenketListe<>();

    if (liste.antall() != 0)
    {
      antallFeil++;
      System.out.println("Oppgave 1a: Feil i metoden antall()!");
    }

    if (liste.tom() != true)
    {
      antallFeil++;
      System.out.println("Oppgave 1b: Feil i metoden tom()!");
    }

    if (liste.leggInn(1) != true)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 1c: leggInn-metoden skal returnere true!");
    }

    liste.leggInn(2);

    if (liste.antall() != 2)
    {
      antallFeil++;
      System.out.println("Oppgave 1d: Feil i metoden antall()!");
    }

    if (liste.tom() == true)
    {
      antallFeil++;
      System.out.println("Oppgave 1e: Feil i metoden tom()!");
    }

    try
    {
      liste.leggInn(null);
      antallFeil++;
      System.out.println
        ("Oppgave 1f: null-verdier skal stoppes!");
    }
    catch (Exception e)
    {
      if (!(e instanceof NullPointerException))
      {
        System.out.println
          ("Oppgave 1g: Kaster feil type unntak for null-verdier!");
        antallFeil++;
      }
    }

    return antallFeil;
  }

  ////// Oppgave 2 /////////////////////  

  public static int oppgave2()
  {
    int antallFeil = 0;
    DobbeltLenketListe<Integer> liste =
                                new DobbeltLenketListe<>();

    if (!liste.toString().equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 2a: Tom liste skal gi []!");
    }

    if (!liste.omvendtString().equals("[]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 2b: Tom liste skal gi []!");
    }

    liste.leggInn(1);

    String s = liste.toString();
    if (!s.equals("[1]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 2c: Du har " + s + ", skal være [1]!");
    }

    s = liste.omvendtString();
    if (!s.equals("[1]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 2d: Du har " + s + ", skal være [1]!");
    }

    liste.leggInn(2);

    s = liste.toString();
    if (!s.equals("[1, 2]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 2e: Du har " + s + ", skal være [1, 2]!");
    }

    s = liste.omvendtString();
    if (!s.equals("[2, 1]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 2f: Du har " + s + ", skal være [2, 1]!");
    }

    liste.leggInn(3);
    liste.leggInn(4);

    s = liste.toString();
    if (!s.equals("[1, 2, 3, 4]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 2g: Du har " + s + ", skal være [1, 2, 3, 4]!");
    }

    s = liste.omvendtString();
    if (!s.equals("[4, 3, 2, 1]"))
    {
      antallFeil++;
      System.out.println
         ("Oppgave 2h: Du har " + s + ", skal være [4, 3, 2, 1]!");
    }

    return antallFeil;
  }

  ////// Oppgave 3 /////////////////////  

  public static int oppgave3()
  {
    int antallFeil = 0;

    DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

    try
    {
      liste.hent(0);
      antallFeil++;
      System.out.println("Oppgave 3a: En tom liste har ikke indeks lik 0!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 3b: Metoden hent() kaster feil type unntak!");
        antallFeil++;
      }
    }

    liste.leggInn(1);

    try
    {
      liste.hent(-1);
      antallFeil++;
      System.out.println("Oppgave 3c: Feil i indeks-sjekken!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 3d: Metoden hent() kaster feil type unntak!");
        antallFeil++;
      }
    }

    try
    {
      liste.hent(1);
      antallFeil++;
      System.out.println("Oppgave 3e: Feil i indeks-sjekken!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 3f: Metoden hent() kaster feil type unntak!");
        antallFeil++;
      }
    }

    if (liste.hent(0) != 1)
    {
      antallFeil++;
      System.out.println("Oppgave 3g: Metoden hent() gir feil svar!");
    }

    liste.leggInn(2);
    liste.leggInn(3);
    liste.leggInn(4);

    if (liste.hent(3) != 4 || liste.hent(2) != 3
        || liste.hent(1) != 2 || liste.hent(0) != 1)
    {
      antallFeil++;
      System.out.println("Oppgave 3h: Metoden hent() gir feil svar!");
    }

    try
    {
      liste.oppdater(3, null);
      antallFeil++;
      System.out.println("Oppgave 3i: Ikke tillatt med nullverdier i oppdater!");
    }
    catch (Exception e)
    {
      if (!(e instanceof NullPointerException))
      {
        System.out.println(
          "Oppgave 3j: Feil unntak for nullverdier i oppdater()!");
        antallFeil++;
      }
    }

    try
    {
      liste.oppdater(4, 5);
      antallFeil++;
      System.out.println
        ("Oppgave 3k: En liste med fire verdier har ikke indeks lik 4");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println
          ("Oppgave 3l: Metoden kaster feil type unntak for indeksfeil!");
        antallFeil++;
      }
    }

    if (liste.oppdater(3, 5) != 4)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3m: Metoden oppdater() returnerer feil verdi!");
    }

    if (liste.antall() != 4)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3n: antallEndringer skal økes, men antall skal ikke endres!");
    }

    if (liste.hent(3) != 5)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3o: Metoden oppdater() setter feil verdi!");
    }

    if (liste.oppdater(0, -1) != 1)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3p: Metoden oppdater() returnerer feil verdi!");
    }

    if (liste.hent(0) != -1)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3q: Metoden oppdater() setter feil verdi!");
    }

    String s = liste.toString();
    if (!s.equals("[-1, 2, 3, 5]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3r: Du har " + s + ", skal være [-1, 2, 3, 5]!");
    }

    s = liste.omvendtString();
    if (!s.equals("[5, 3, 2, -1]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3s: Du har " + s + ", skal være [5, 3, 2, -1]!");
    }

    return antallFeil;
  }

  ////// Oppgave 4 /////////////////////  

  public static int oppgave4()
  {
    int antallFeil = 0;

    DobbeltLenketListe<String> sliste = new DobbeltLenketListe<>();
    sliste.leggInn("A");

    if (sliste.indeksTil(new String("A")) != 0)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 4a: Må bruke equals og ikke == i sammenligningen i indeksTil()!");
    }

    Liste<Integer> liste = new DobbeltLenketListe<>();

    if (liste.indeksTil(2) != -1)
    {
      antallFeil++;
      System.out.println("Oppgave 4b: En tom liste inneholder ikke verdien 2!");
    }

    liste.leggInn(1);

    if (liste.indeksTil(1) != 0)
    {
      antallFeil++;
      System.out.println("Oppgave 4c: Verdien 1 har indeks 0!");
    }

    liste.leggInn(3);
    liste.leggInn(5);
    liste.leggInn(7);

    if (liste.indeksTil(10) != -1 || liste.indeksTil(4) != -1)
    {
      antallFeil++;
      System.out.println("Oppgave 4d: Listen inneholder hverken 4 eller 10!");
    }

    if (liste.indeksTil(1) != 0 || liste.indeksTil(3) != 1
        || liste.indeksTil(5) != 2 || liste.indeksTil(7) != 3)
    {
      antallFeil++;
      System.out.println("Oppgave 4e: Feil i metoden indeksTil()!");
    }

    try
    {
      if (liste.indeksTil(null) != -1)
      {
        antallFeil++;
        System.out.println
          ("Oppgave 4f: Skal returnere -1 for null-verdier!");
      }
    }
    catch (Exception e)
    {
      System.out.println(
      "Oppgave 4g: Skal ikke kaste unntak, men returnere -1 for null-verdier!");
        antallFeil++;
    }

    if (liste.inneholder(1) != true || liste.inneholder(7) != true
      || liste.inneholder(0) != false || liste.inneholder(6) != false)
    {
      antallFeil++;
      System.out.println("Oppgave 4h: Feil i metoden inneholder()!");
    }

    sliste = new DobbeltLenketListe<>();
    sliste.leggInn("Kari");
    sliste.leggInn("Kari");

    if (sliste.indeksTil("Kari") != 0)
    {
      antallFeil++;
      System.out.println
      ("Oppgave 4i: Skal gi indeks til første forekomst hvis like verdier!");
    }

    return antallFeil;
  }

  ////// Oppgave 5 /////////////////////  

  public static int oppgave5()
  {
    int antallFeil = 0;

    DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

    try
    {
      liste.leggInn(-1, 1);
      antallFeil++;
      System.out.println(
        "Oppgave 5a: Kan ikke legge inn på indeks -1!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println
          ("Oppgave 5b: Kaster feil type unntak for indeksfeil!");
        antallFeil++;
      }
    }

    try
    {
      liste.leggInn(1, 1);
      antallFeil++;
      System.out.println("Oppgave 5c: Kan ikke bruke indeks 1 i en tom liste!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 5d: Metoden leggInn() kaster feil type unntak!");
        antallFeil++;
      }
    }

    try
    {
      liste.leggInn(0, null);
      antallFeil++;
      System.out.println("Oppgave 5e: Ikke tillatt å legge inn null-verdier!");
    }
    catch (Exception e)
    {
      if (!(e instanceof NullPointerException))
      {
        System.out.println
          ("Oppgave 5f: Det kastes feil type unntak for null-verdier!");
        antallFeil++;
      }
    }

    liste = new DobbeltLenketListe<>();

    try
    {
      liste.leggInn(0, 4);
    }
    catch (Exception e)
    {
      System.out.println
       ("Oppgave 5g: Feil i indekssjekken! 0 <= indeks <= antall er tillatt!");

      antallFeil++;
      return antallFeil;
    }

    liste = new DobbeltLenketListe<>();

    liste.leggInn(0, 4);  // ny verdi i tom liste
    liste.leggInn(0, 2);  // ny verdi legges forrest
    liste.leggInn(2, 6);  // ny verdi legges bakerst
    liste.leggInn(1, 3);  // ny verdi nest forrest
    liste.leggInn(3, 5);  // ny verdi nest bakerst
    liste.leggInn(0, 1);  // ny verdi forrest
    liste.leggInn(6, 7);  // ny verdi legges bakerst

    if (liste.antall() != 7)
    {
      antallFeil++;
      System.out.println(
        "Oppgave 5h: Feil i antall-oppdateringen i metoden leggInn()!");
    }

    if (!liste.toString().equals("[1, 2, 3, 4, 5, 6, 7]"))
    {
      antallFeil++;
      System.out.println("Oppgave 5i: Feil i metoden leggInn()!");
    }

    if (!liste.omvendtString().equals("[7, 6, 5, 4, 3, 2, 1]"))
    {
      antallFeil++;
      System.out.println("Oppgave 5j: Feil i metoden leggInn()!");
    }

    return antallFeil;
  }

  ///////// Oppgave 6 //////////////////////////

  public static int oppgave6()
  {
    int antallFeil = 0;

    DobbeltLenketListe<String> liste = new DobbeltLenketListe<>();

    try
    {
      liste.fjern(0);
      antallFeil++;
      System.out.println("Oppgave 6a: Indeks 0 finnes ikke i en tom liste!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println
          ("Oppgave 6b: Indekssjekken kaster feil unntak!");
        antallFeil++;
      }
    }

    String[] s = {"A","B","C","D","E","F","G"};

    for (int i = 0; i < s.length; i++)
    {
      liste.leggInn(s[i]);
    }

    try
    {
      liste.fjern(7);
      antallFeil++;
      System.out.println("Oppgave 6c: Indeks 7 finnes ikke!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 6d: Indekssjekken kaster feil unntak!!");
        antallFeil++;
      }
    }

    try
    {
      liste.fjern(-1);
      antallFeil++;
      System.out.println("Oppgave 6e: Indeks -1 finnes ikke!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 6f: Indekssjekken kaster feil unntak!!");
        antallFeil++;
      }
    }

    if (!liste.fjern(3).equals("D"))
    {
      antallFeil++;
      System.out.println("Oppgave 6g: Feil returverdi i metoden fjern(indeks)!");
    }

    liste.fjern(0);  // fjerner A
    liste.fjern(4);  // fjerner G

    if (liste.antall() != 4)
    {
      antallFeil++;
      System.out.println("Oppgave 6h: Feil i antall-oppdateringen i fjern(indeks)!");
    }

    if (!liste.toString().equals("[B, C, E, F]"))
    {
      antallFeil++;
      System.out.println("Oppgave 6i: Feil i fjern(indeks)-metoden!");
    }

    if (!liste.omvendtString().equals("[F, E, C, B]"))
    {
      antallFeil++;
      System.out.println("Oppgave 6j: Feil i fjern(indeks)-metoden!");
    }

    liste.leggInn("H");
    liste.leggInn("I");

    if (liste.fjern(" ") == true
        || liste.fjern("G") == true
        || liste.fjern("J") == true)
    {
      antallFeil++;
      System.out.println("Oppgave 6k: Feil returverdi i metoden fjern(T)!");
    }


    if (liste.fjern("B") != true
        || liste.fjern("F") != true
        || liste.fjern("I") != true)
    {
      antallFeil++;
      System.out.println("Oppgave 6l: Feil returverdi i metoden fjern(T)!");
    }

    if (!liste.toString().equals("[C, E, H]"))
    {
      antallFeil++;
      System.out.println("Oppgave 6m: Feil i metoden fjern(T)!");
    }

    if (!liste.omvendtString().equals("[H, E, C]"))
    {
      antallFeil++;
      System.out.println("Oppgave 6n: Feil i metoden fjern(T)!");
    }

    liste.fjern("H");
    liste.fjern("C");
    liste.fjern("E");

    if (!liste.toString().equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 6o: Feil i metoden fjern(T)!");
    }

    if (!liste.omvendtString().equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 6p: Feil i metoden fjern(T)!");
    }

    if (liste.antall() != 0)
    {
      antallFeil++;
      System.out.println(
        "Oppgave 6q: Feil i antall-oppdateringen metoden fjern(T)!");
    }

    try
    {
      if (liste.fjern(null) != false)
      {
      antallFeil++;
      System.out.println
        ("Oppgave 6r: skal returnere false for en null-verdi!");
      }
    }
    catch (Exception e)
    {
      System.out.println
          ("Oppgave 6s: Skal ikke kaste unntak, men gi false for en null-verdi!");
      antallFeil++;
    }

    liste = new DobbeltLenketListe<>();
    liste.leggInn("A");
    liste.leggInn("B");
    liste.leggInn("C");
    liste.leggInn("D");
    liste.leggInn("E");

    if (liste.fjern(new String("A")) != true
      || liste.fjern(new String("C")) != true
      || liste.fjern(new String("E")) != true)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 6t: Må bruke equals og ikke == i sammenligningene!");
    }

    return antallFeil;
  }

  //// Oppgave 7 ////////////////////////////

  public static int oppgave7()
  {
    int antallFeil = 0;

    DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

    for (int i = 1; i <= 7; i++)
    {
      liste.leggInn(i);
    }

    liste.nullstill();

    if (liste.antall() != 0)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 7a: Feil i antalloppdateringen i nullstill!");
    }

    if (!liste.toString().equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 7b: Feil i metoden nullstill()!");
    }

    if (!liste.omvendtString().equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 7c: Feil i metoden nullstill()!");
    }

    if (liste.indeksTil(1) != -1)
    {
      antallFeil++;
      System.out.println("Oppgave 7d: Feil i metoden nullstill()!");
    }

    return antallFeil;
  }

  //// Oppgave 8 ////////////////////////////  

  public static int oppgave8()
  {
    int antallFeil = 0;

    DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

    try
    {
      Iterator<Integer> i = liste.iterator();
      i.next();  // kaller next() i en tom liste
      System.out.println(
        "Oppgave 8a: Skal kastes unntak for next() i en tom liste!");
      antallFeil++;
    }
    catch (Exception e)
    {
      if (!(e instanceof NoSuchElementException))
      {
        System.out.println("Oppgave 8b: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    liste.leggInn(1);

    Iterator<Integer> i = liste.iterator();

    if (i.next() != 1)
    {
      System.out.println
        ("Oppgave 8c: Metoden next() gir feil verdi!");
      antallFeil++;
    }

    try
    {
      i.next();  // det er ikke flere i listen

      System.out.println(
        "Oppgave 8d: Skal kastes unntak for next() her!");
      antallFeil++;
    }
    catch (Exception e)
    {
      if (!(e instanceof NoSuchElementException))
      {
        System.out.println("Oppgave 8e: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    for (int k = 2; k <= 7; k++)
    {
      liste.leggInn(k);
    }

    int k = 1;
    for (Iterator<Integer> j = liste.iterator(); j.hasNext();)
    {
      if (j.next() != k)
      {
        System.out.println("Oppgave 8f: Metoden next() gir feil verdier!");
        antallFeil++;
      }
      k++;
    }

    i = liste.iterator();
    liste.fjern(0); // bruker fjern(indeks) etter at iteratoren er opprettet

    try
    {
      i.next();
      antallFeil++;
      System.out.println
        ("Oppgave 8g: ForventetAntallEndringer ikke endret i fjern(indeks)!");
    }
    catch (Exception e)
    {
      if (!(e instanceof ConcurrentModificationException))
      {
        System.out.println("Oppgave 8h: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    i = liste.iterator();
    liste.leggInn(8);  // bruker leggInn(T) etter at iteratoren er opprettet

    try
    {
      i.next();
      antallFeil++;
      System.out.println
        ("Oppgave 8i: ForventetAntallEndringer ikke endret i leggInn(T)!");
    }
    catch (Exception e)
    {
      if (!(e instanceof ConcurrentModificationException))
      {
        System.out.println("Oppgave 8j: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    i = liste.iterator();
    liste.fjern(new Integer(8));  // bruker fjern(T) etter at iteratoren er opprettet

    try
    {
      i.next();
      antallFeil++;
      System.out.println
        ("Oppgave 8k: ForventetAntallEndringer ikke endret i fjern(T)!");
    }
    catch (Exception e)
    {
      if (!(e instanceof ConcurrentModificationException))
      {
        System.out.println("Oppgave 8l: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    i = liste.iterator();
    liste.leggInn(0,1);  // bruker leggInn(indeks,T) etter at iteratoren er opprettet

    try
    {
      i.next();
      antallFeil++;
      System.out.println
        ("Oppgave 8m: ForventetAntallEndringer ikke endret i leggInn(indeks,T)!");
    }
    catch (Exception e)
    {
      if (!(e instanceof ConcurrentModificationException))
      {
        System.out.println("Oppgave 8n: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    i = liste.iterator();
    liste.oppdater(3, 9);  // oppdaterer etter at iteratoren er opprettet

    try
    {
      i.next();
      antallFeil++;
      System.out.println
        ("Oppgave 8o: ForventetAntallEndringer ikke endret i oppdater()!");
    }
    catch (Exception e)
    {
      if (!(e instanceof ConcurrentModificationException))
      {
        System.out.println("Oppgave 8p: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    i = liste.iterator();
    liste.nullstill();  // nullstiller etter at iteratoren er opprettet

    try
    {
      i.next();
      antallFeil++;
      System.out.println
        ("Oppgave 8q: ForventetAntallEndringer ikke endret i nullstill()!");
    }
    catch (Exception e)
    {
      if (!(e instanceof ConcurrentModificationException))
      {
        System.out.println("Oppgave 8r: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

   for (int j = 1; j <= 7; j++) liste.leggInn(j);

    try
    {
      liste.iterator(7);
      System.out.println("Oppgave 8s: Indeks 7 finnes ikke!");
      antallFeil++;
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 8t: Metoden kaster feil type unntak!");
        antallFeil++;
      }
    }

    try
    {
      liste.iterator(-1);
      System.out.println("Oppgave 8u: Indeks -1 finnes ikke!");
      antallFeil++;
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 8v: Metoden kaster feil type unntak!");
        antallFeil++;
      }
    }

    int m = 4;
    i = liste.iterator(3);
    for (; i.hasNext();)
    {
      if (i.next() != m)
      {
        antallFeil++;
        System.out.println("Oppgave 8w: Feil i metoden next()!");
      }
      m++;
    }

    try
    {
      i.next();
      antallFeil++;
      System.out.println("Oppgave 8x: Skal kaste unntak for next() her!");
    }
    catch (Exception e)
    {
      if (!(e instanceof NoSuchElementException))
      {
        System.out.println("Oppgave 8y: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    return antallFeil;
  }

  public static int oppgave9()
  {
    int antallFeil = 0;

    DobbeltLenketListe<String> liste = new DobbeltLenketListe<>();

    String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};
    for (String s : navn) liste.leggInn(s);

    try
    {
      liste.forEach(null);
      antallFeil++;
      System.out.println
        ("Oppgave 9a: Skal kaste unntak hvis handling er null!");
    }
    catch (Exception e)
    {
      if (!(e instanceof NullPointerException))
      {
        System.out.println("Oppgave 9b: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    StringBuilder sb = new StringBuilder();
    liste.forEach(s -> sb.append(s));

    if (!sb.toString().equals("LarsAndersBodilKariPerBerit"))
    {
      System.out.println
        ("Oppgave 9c: forEach virker ikke som den skal!");
      antallFeil++;
    }

    liste = new DobbeltLenketListe<>();
    StringBuilder sb2 = new StringBuilder();
    liste.forEach(s -> sb2.append(s));

    if (!sb2.toString().equals(""))
    {
      System.out.println
        ("Oppgave 9d: forEach virker ikke som den skal for tom liste!");
      antallFeil++;
    }

    liste = new DobbeltLenketListe<>();
    liste.leggInn("X");
    StringBuilder sb3 = new StringBuilder();

    liste.forEach(s -> sb3.append(s));

    if (!sb3.toString().equals("X"))
    {
      System.out.println
        ("Oppgave 9e: forEach virker ikke for liste med ett element!");
      antallFeil++;
    }

    liste = new DobbeltLenketListe<>();

    try
    {
      liste.iterator().forEachRemaining(null);
      antallFeil++;
      System.out.println
        ("Oppgave 9f: Skal kaste unntak hvis handling er null!");
    }
    catch (Exception e)
    {
      if (!(e instanceof NullPointerException))
      {
        System.out.println("Oppgave 9f: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    StringBuilder sb4 = new StringBuilder();
    liste.iterator().forEachRemaining(s -> sb4.append(s));

    if (!sb4.toString().equals(""))
    {
      System.out.println
        ("Oppgave 9g: forEach virker ikke som den skal for tom liste!");
      antallFeil++;
    }

    for (String s : navn) liste.leggInn(s);
    StringBuilder sb5 = new StringBuilder();
    Iterator<String> i = liste.iterator();
    sb5.append(i.next());
    i.forEachRemaining(s -> sb5.append(s));

    if (!sb5.toString().equals("LarsAndersBodilKariPerBerit"))
    {
      System.out.println
        ("Oppgave 9h: forEachRemaining virker ikke som den skal!");
      antallFeil++;
    }

    StringBuilder sb6 = new StringBuilder();
    i = liste.iterator(3);
    i.forEachRemaining(s -> sb6.append(s));

    if (!sb6.toString().equals("KariPerBerit"))
    {
      System.out.println
        ("Oppgave 9i: forEachRemaining virker ikke som den skal!");
      antallFeil++;
    }

    liste = new DobbeltLenketListe<>();
    liste.leggInn("A"); liste.leggInn("B");
    Iterator<String> iterator = liste.iterator();
    iterator.forEachRemaining(x -> {});
    StringBuilder sb7 = new StringBuilder();
    // iteratoren er nå tom - et nytt kall skal derfor ikke gi noe
    iterator.forEachRemaining(x -> sb7.append(x));
    if (!sb7.toString().equals(""))
    {
      System.out.println
        ("Oppgave 9j: I forEachRemaining skal denne flyttes!");
      antallFeil++;
    }

    return antallFeil;
  }

  public static int oppgave10()
  {
    int antallFeil = 0;

    DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

    for (int k = 1; k <= 13; k++)
    {
      liste.leggInn(k);
    }

    for (Iterator<Integer> i = liste.iterator(); i.hasNext();)
    {
      int verdi = i.next();
      if (verdi % 2 == 1)
      {
        i.remove(); // fjerner oddetallene
      }
    }

    if (liste.antall() != 6)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 10a: Feil i antall-oppdatering i remove()!");
    }

    if (!liste.toString().equals("[2, 4, 6, 8, 10, 12]"))
    {
      antallFeil++;
      System.out.println("Oppgave 10b: Feil i remove()!");
    }

    if (!liste.omvendtString().equals("[12, 10, 8, 6, 4, 2]"))
    {
      antallFeil++;
      System.out.println("Oppgave 10c: Feil i remove()!");
    }

    // fjerner alle i listen
    Iterator<Integer> j = liste.iterator();
    for (; j.hasNext();)
    {
      j.next();
      j.remove();
    }

    if (!liste.toString().equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 10d: Feil når remove() har slettet alle!");
    }

    try
    {
      Iterator<Integer> i = liste.iterator();
      i.remove();  // kaller remove() i tom liste
      antallFeil++;
      System.out.println("Oppgave 10e: Her skal det kastes unntak");
    }
    catch (Exception e)
    {
      if (!(e instanceof IllegalStateException))
      {
        System.out.println("Oppgave 10f: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    for (int k = 1; k <= 5; k++)
    {
      liste.leggInn(k);
    }
    Iterator<Integer> i1 = liste.iterator();
    Iterator<Integer> i2 = liste.iterator();
    i1.next();
    i1.remove();
    try
    {
      i2.next();
      System.out.println("Oppgave 10g: Her skal det kastes et unntak!");
      antallFeil++;
    }
    catch (Exception e)
    {
      if (!(e instanceof ConcurrentModificationException))
      {
        System.out.println("Oppgave 10h: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    j = liste.iterator();

    try
    {
      j.next();
      j.remove();
      j.remove();
      System.out.println("Oppgave 10i: Her skal det kastes et unntak!");
      antallFeil++;
    }
    catch (Exception e)
    {
      if (!(e instanceof IllegalStateException))
      {
        System.out.println("Oppgave 10j: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    return antallFeil++;
  }

} // Oblig2Test
