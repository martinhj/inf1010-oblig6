package logic;
import java.util.Iterator;
import java.lang.Comparable;
/**
 * En klasse for å implementere en sortert samling. Implementerer også 
 * Iterable slik at det er mulig å iterere over.
 */
public class SSLLcontainer <K extends Comparable<K>, V> {
    private Entry header;
    private Entry tail;
    private int size;

    /**
     * Konstruktøren til beholderen. Lager hodenode og halenode.
     */
    public SSLLcontainer () {
        header = new Entry (null, null, null, null);
        tail = new Entry (null, null, null, null);
        header.next = tail;
        tail.previous = header;
    }
    /**
     * Metode for å legge inn verdi @code v med noekkel @n i samlingen.
     * Metoden skal gå gjennom den sorterte listen fra den minste og fram til
     * og med det objektet som er større og legge inn en node med @code k og
     * @code v der.
     */
    public void leggInn(K k, V v) {
        insert (k, v);
    }
    public void insert(K k, V v) {
        // Gå gjennom listen og finne den første node med en nøkkel større enn
        // nøkkel k
        // skal ikke legge inn node hvis den har en node med samme nøkkel fra 
        // før.
        //if (contains(k)) return;
        // UnsupportedOperationException fra java.lang
        //throw new UnsupportedOperationException(
        //"Can't insert a object with a key that is the same ass a key in the"
        //+ " container");
        Entry firstBiggerThanKey = findBiggerThanKey(k, true);
        Entry entry = new Entry(k, v, firstBiggerThanKey
                , firstBiggerThanKey.previous);
        firstBiggerThanKey.previous.next = entry;
        firstBiggerThanKey.previous = entry;
        size++;
    }
    /** 
     * Finner noden som har nøkkel @code key større enn k. Ved å sette inn et
     * objekt før det første objektet som er større er listen alltid sortert.
     */
    private Entry findBiggerThanKey(K k) {
        Entry entry = header.next;
        while (entry != tail) {
            if (entry.key.compareTo(k) > 0) return entry;
            entry = entry.next;
        }
        return tail;
    }
    private Entry findBiggerThanKey(K k, boolean b) {
        return findBiggerThanKeyRecursive(k, header.next);
    }
    private Entry findBiggerThanKeyRecursive(K k, Entry entryToWorkFrom) {
        if (entryToWorkFrom == tail) return entryToWorkFrom;
        if (k.compareTo(entryToWorkFrom.key) > 0)
            return findBiggerThanKeyRecursive(k, entryToWorkFrom.next);
        return entryToWorkFrom;
    }
    /**
     * Metode som returnerer hvor mange elementer det er i samlingen.
     * Returnerer size-variabelen.
     */
    public int antall() {
        return size();
    }
    public int size() {
        return size;
    }
    /** 
     * Metode som returnerer objektet tilknyttet nøkkelen @code n.
     * Traverserer listen fra head til den finner objektet. Ellers returnerer
     * den null.
     * @return k eller null.
     */
    public V hent (K k) {
        return get(k);
    }
    public V get (K k) {
        Entry entry;
        if (null != (entry = getEntry(k))) return entry.value;
        //if (temp.value != null) return temp;
        return null;
    }
    /**
     * Metode som returnerer objetet på plass @code num.
     * Teller seg fra head til den finner objektet på plass @code num. Første 
     * objektet etter head har plass 0.
     * Returnerer null hvis num < 0 eller num > size - 1; 
     */
    public V hent(int num) {
        // Her brukes num - 1 for å støtte Testprogrammets forventning om at
        // første objekt har index 1. I resten av denne implementasjonen har
        // første objekt index 0.
        return get(num - 1);
    }
    public V get (int num) {
        // IndexOutOfBoundsException fra java.lang
        if (num < 0 || num > size - 1) return null;
            /*throw new IndexOutOfBoundsException("Index: "+num+
                                                ", Size: "+size); */
        Entry entry = header.next;
        int counter = 0;
        while (entry != tail) {
            if (counter == num) return entry.value;
            counter++;
            entry = entry.next;
        }
        return null;
    }
    /**
     * Returnerer det objektet som er sortert med den minste nøkkelen.
     * Altså head's next.
     */
    public V hentMinste() {
        return getLeast();
    }
    public V getLeast() {
        if (header.next != tail) return header.next.value;
        return null;
    }
    /**
     * Returnerer det objektet som er stortert med den største nøkkelen.
     * Altså tail's previous.
     */
    public V hentStorste() {
        return getLargest();
    }
    public V getLargest() {
        if (tail.previous != header) return tail.previous.value;
        return null;
    }
    /**
     * Metode som tester om samlingen inneholder et objekt med nøkkelen @code
     * n.
     */
    public boolean inneholder(K k) {
        return contains(k);
    }
    public boolean contains(K k) {
        Entry entry = header.next;
        while (entry != tail) {
            if (entry.key.compareTo(k) == 0) return true;
            entry = entry.next;
        }
        return false;
    }
    /**
     * Metode som fjerner objektet med nøkkel @code n.
     * @return true hvis den har fjernet objektet.
     * @return false hvis den ikke fjerner objektet.
     */
     public boolean fjernElement(K k) {
         return removeElement(k);
     }
    //bruker (get k) og endrer k.previous.next til k.next og k.next.previous til
    //k.previous.
    public boolean removeElement(K k) {
        Entry elementToBeRemoved;
        if (null == (elementToBeRemoved = getEntry(k))) return false;
        elementToBeRemoved.previous.next = elementToBeRemoved.next; 
        elementToBeRemoved.next.previous = elementToBeRemoved.previous;
        size--;
        return true;
    }
    /**
     * Metode som fjerner alle objektene i samlingen.
     */
    public void fjernAlle() {
        reset();
    }
    public void reset() {
        header.next = tail;
        tail.previous = header;
        size = 0;
    }
    /**
     * Metode som returnerer alle elementene i et array.
     */
    public V[] tilArray(V[] a) {
        return toArray(a);
    }
    // refakturere
    public V[] toArray(V[] a) {
        Entry entry = header.next;
        for (int i = 0; i < a.length; i++) {
            a[i] = entry.value;
            entry = entry.next;
        }
        return a;
    }
    /**
     * Returnerer en iterator for listen.
     */
    public Iterator<V> iterator() {
        return (Iterator<V>) new It();
    }

    private class It implements Iterator<V> {
        private Entry lastReturned = header;
        private boolean beenRemoved = true;
        public boolean hasNext() {
            return lastReturned.next != tail;
        }
        public V next() {
            if (!hasNext()) return null;
            lastReturned = lastReturned.next;
            beenRemoved = false;
            return lastReturned.value;
        }
        public void remove() {
            if (beenRemoved) throw new IllegalStateException();
            lastReturned.previous.next = lastReturned.next;
            lastReturned.next.previous = lastReturned.previous;
            size--;
            beenRemoved = true;
        }
    }
    // Interne metoder for å gjøre noe med datastrukturen.
    /**
     * Returnerer noden med nøkkel @code k.
     */
    private Entry getEntry(K k) {
        Entry entry = header.next;
        while (entry != tail) {
            if (entry.key.compareTo(k) == 0) return entry;
            entry = entry.next;
        }
        return null;
    }
    /**
     * Node for å holde på pekerene til objektene. Setter pekerene for neste og
     * forrige node i konstruktøren.
     * @param key Nøkkelen til objektet (finner igjen objekter med denne).
     * @param value Objektet som noden skal holde på.
     * @param next Peker til den neste noden i listen.
     * @param previous Peker til den forrige noden i listen.
     */
    private class Entry {
        private K key;
        private V value;
        private Entry next;
        private Entry previous;
        private Entry(K key, V value, Entry next, Entry previous) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }
}