public class BinarySearch <E extends Comparable <E>> {
    /*Sucht mittels binärer Suche das Element x in dem Array a und
    gibt bei Erfolg den Index i mit a[i].compareTo(x) == 0
    zurück, ansonsten−1.*/

    public int search (E[ ] a, E x) {
        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            int mitte = (low + high) / 2;

            if (a[mitte].compareTo(x) == 0) {
                return mitte;
            } else if (a[mitte].compareTo(x) < 0) {
                low = mitte + 1;
            } else {
                high = mitte - 1;
            }
        }
        return -1;
    }
}