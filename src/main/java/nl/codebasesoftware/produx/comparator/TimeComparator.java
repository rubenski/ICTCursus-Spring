package nl.codebasesoftware.produx.comparator;

import java.util.Comparator;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 16:08
 */
public class TimeComparator implements Comparator<TimeComparable> {

    @Override
    public int compare(TimeComparable o1, TimeComparable o2) {
        return o1.getTime().compareTo(o2.getTime());
    }
}
