/*
    Hallbjørn Storruste s165519 HINGDATA13H2AA
    Adrian Westlund s198571 INFORMATIK13H2IA
*/

package oblig;

import java.util.*;
import java.util.function.Consumer;
import hjelpeklasser.Liste;

public class DobbeltLenketListe<T> implements Liste<T> {

    private static final class Node<T> // en indre nodeklasse
    {

        // instansvariabler
        private T verdi;
        private Node<T> forrige, neste;

        // konstruktør
        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int antallEndringer;   // antall endringer i listen

    // hjelpemetode
    private void indeksKontroll(int indeks) {
        if (indeks < 0) {
            throw new IndexOutOfBoundsException("Indeks "
                    + indeks + " er negativ!");
        } else if (indeks >= antall) {
            throw new IndexOutOfBoundsException("Indeks "
                    + indeks + " >= antall(" + antall + ") noder!");
        }
    }

    // hjelpemetode
    private Node<T> finnNode(int indeks) {

        int teller;
        Node<T> runner;

        if (indeks < antall / 2) {
            teller = 0;
            runner = hode;

            while (true) {
                if (indeks == teller) {
                    return runner;
                }
                teller++;
                runner = runner.neste;
            }
        } else {
            teller = antall - 1;
            runner = hale;

            while (true) {
                if (indeks == teller) {
                    return runner;
                }
                teller--;
                runner = runner.forrige;
            }
        }
    }

    // konstruktør
    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        antallEndringer = 0;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Får ikke vare null");

        Node<T> node = new Node(verdi, hale, null);;

        if (tom()) {
            hode = node;
            hale = hode;
        } else {
            hale.neste = node;
            hale = node;
        }
        antall++;
        antallEndringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {

        Objects.requireNonNull(verdi, "Verdi kan ikke vare null");

        if (indeks < 0) {
            throw new IndexOutOfBoundsException("Indeks "
                    + indeks + " er negativ!");
        } else if (indeks > antall) {
            throw new IndexOutOfBoundsException("Indeks "
                    + indeks + " > antall(" + antall + ") noder!");
        }

        Node<T> node = new Node(verdi, null, null);

        if (antall == 0) {
            hode = node;
            hale = node;
        } else if (indeks == 0) {
            node.neste = hode;
            node.neste.forrige = node;
            hode = node;
        } else if (indeks == antall) {
            node.forrige = hale;
            node.forrige.neste = node;
            hale = node;
        } else {
            node.neste = finnNode(indeks);
            node.forrige = node.neste.forrige;
            node.forrige.neste = node;
            node.neste.forrige = node;
        }
        antall++;
        antallEndringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) >= 0;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) {
            return -1;
        }

        Node<T> runner = hode;
        int teller = 0;

        while (runner != null) {
            if (runner.verdi.equals(verdi)) {
                return teller;
            }
            runner = runner.neste;
            teller++;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        
        Objects.requireNonNull(nyverdi, "Nyverdi kan ikke vare null");
        
        indeksKontroll(indeks);
        Node<T> node = finnNode(indeks);
        T temp = node.verdi;
        node.verdi = nyverdi;

        antallEndringer++;
        return temp;
    }

    @Override
    public boolean fjern(T verdi) {

        if (verdi == null) {
            return false;
        }

        Node<T> runner = hode;

        while (runner != null) {
            if (runner.verdi.equals(verdi)) {
                if (antall == 1) {
                    hode = hale = null;
                } else if (runner.forrige == null) {
                    hode = hode.neste;
                    hode.forrige = null;
                } else if (runner.neste == null) {
                    hale = hale.forrige;
                    hale.neste = null;
                } else {
                    runner.forrige.neste = runner.neste;
                    runner.neste.forrige = runner.forrige;
                }
                antall--;
                antallEndringer++;
                return true;
            }
            runner = runner.neste;
        }
        return false;
    }

    @Override
    public T fjern(int indeks) {

        indeksKontroll(indeks);

        Node<T> node;

        if (indeks == 0) {
            node = hode;
            hode = hode.neste;
            hode.forrige = null;
        } else if (indeks == antall - 1) {
            node = hale;
            hale = hale.forrige;
            hale.neste = null;
        } else {
            node = finnNode(indeks);
            node.forrige.neste = node.neste;
            node.neste.forrige = node.forrige;
        }
        antall--;
        antallEndringer++;
        return node.verdi;
    }

    @Override
    public void nullstill() {
        hode = hale = null;
        antall = 0;
        antallEndringer++;
    }

    @Override
    public String toString() {

        StringJoiner s = new StringJoiner(", ", "[", "]");
        for (Node<T> p = hode; p != null; p = p.neste) {
            s.add(p.verdi.toString());
        }
        return s.toString();
    }

    public String omvendtString() {
        /*StringBuilder ut = new StringBuilder();
         ut.append("[");
         Node<T> runner = hale;

         while (runner != null) {
         ut.append(runner.verdi);

         if (runner.forrige != null) {
         ut.append(", ");
         }
         runner = runner.forrige;
         }
         ut.append("]");
         return ut.toString();*/

        StringJoiner s = new StringJoiner(", ", "[", "]");
        for (Node<T> p = hale; p != null; p = p.forrige) {
            s.add(p.verdi.toString());
        }
        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {

        private Node<T> denne;
        private boolean fjernOK;
        private int forventetAntallEndringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // denne starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            forventetAntallEndringer = antallEndringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            this();
            denne = finnNode(indeks);
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            
            if (antallEndringer != forventetAntallEndringer) {
                throw new ConcurrentModificationException("Listen har hatt endringer.");
            }
            
            if (denne == null) {
                throw new NoSuchElementException("Det finnes ingen neste!");
            }

            fjernOK = true;
            
            Node<T> temp = denne;
            denne = denne.neste;

            return temp.verdi;
        }

        @Override
        public void remove() {

            if (!fjernOK) {
                throw new IllegalStateException("Ikke lov å fjerne verdi!");
            }
            if (antallEndringer != forventetAntallEndringer) {
                throw new ConcurrentModificationException("Listen har hatt endringer.");
            }

            fjernOK = false;

            if (antall == 1) {
                hode = hale = null;
            } else if (denne == null) {
                hale = hale.forrige;
                hale.neste = null;
            } else if (denne.forrige == hode) {
                hode = hode.neste;
                hode.forrige = null;
            } else {
                denne.forrige.forrige.neste = denne;
                denne.forrige = denne.forrige.forrige;
            }

            antall--;
            antallEndringer++;
            forventetAntallEndringer++;
        }

        public void forEach(Consumer<? super T> handling) {

            Objects.requireNonNull(handling, "Handling kan ikke vare null");

            Node<T> runner = hode;

            while (runner != null) {
                handling.accept(runner.verdi);
                runner = runner.neste;
            }
        }

        public void forEachRemaining(Consumer<? super T> handling) {

            Objects.requireNonNull(handling, "Handling kan ikke vare null");

            while (denne != null) {
                handling.accept(denne.verdi);
                denne = denne.neste;
            }
        }

    } // DobbeltLenketListeIterator  

} // DobbeltLenketListe
