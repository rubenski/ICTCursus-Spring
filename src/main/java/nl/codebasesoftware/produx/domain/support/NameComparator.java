package nl.codebasesoftware.produx.domain.support;

import java.util.Comparator;

/**
 * User: rvanloen
 * Date: 19-12-12
 * Time: 14:11
 */
public class NameComparator implements Comparator {


    @Override
    public int compare(Object o1, Object o2) {
        if(!(o1 instanceof NameComparable)) return -1;
        if(!(o2 instanceof NameComparable)) return 1;
        return ((NameComparable) o1).getName().compareTo(((NameComparable) o2).getName());
    }
}
